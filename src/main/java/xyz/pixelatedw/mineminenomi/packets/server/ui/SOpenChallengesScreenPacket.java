package xyz.pixelatedw.mineminenomi.packets.server.ui;

import java.util.function.Supplier;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.screens.ChallengesScreen;



public class SOpenChallengesScreenPacket
{
  private boolean canRetakeChallanges;
  
  public SOpenChallengesScreenPacket() {}
  
  public SOpenChallengesScreenPacket(boolean canRetakeChallanges) {
    this.canRetakeChallanges = canRetakeChallanges;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeBoolean(this.canRetakeChallanges);
  }

  
  public static SOpenChallengesScreenPacket decode(PacketBuffer buffer) {
    SOpenChallengesScreenPacket msg = new SOpenChallengesScreenPacket();
    msg.canRetakeChallanges = buffer.readBoolean();
    return msg;
  }

  
  public static void handle(SOpenChallengesScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SOpenChallengesScreenPacket message) {
      ChallengesScreen.open(message.canRetakeChallanges);
    }
  }
}


