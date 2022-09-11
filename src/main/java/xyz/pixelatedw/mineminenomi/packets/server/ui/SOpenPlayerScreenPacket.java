package xyz.pixelatedw.mineminenomi.packets.server.ui;

import java.util.function.Supplier;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.screens.PlayerStatsScreen;


public class SOpenPlayerScreenPacket
{
  private boolean hasQuests;
  private boolean hasChallenges;
  
  public SOpenPlayerScreenPacket() {}
  
  public SOpenPlayerScreenPacket(boolean hasQuests, boolean hasChallenges) {
    this.hasQuests = hasQuests;
    this.hasChallenges = hasChallenges;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeBoolean(this.hasQuests);
    buffer.writeBoolean(this.hasChallenges);
  }

  
  public static SOpenPlayerScreenPacket decode(PacketBuffer buffer) {
    SOpenPlayerScreenPacket msg = new SOpenPlayerScreenPacket();
    msg.hasQuests = buffer.readBoolean();
    msg.hasChallenges = buffer.readBoolean();
    return msg;
  }

  
  public static void handle(SOpenPlayerScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SOpenPlayerScreenPacket message) {
      PlayerStatsScreen.open(message.hasQuests, message.hasChallenges);
    }
  }
}


