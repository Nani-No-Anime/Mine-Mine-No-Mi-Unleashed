package xyz.pixelatedw.mineminenomi.packets.server.ui;

import java.util.function.Supplier;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.screens.JollyRogerCreatorScreen;





public class SOpenJollyRogerCreatorScreenPacket
{
  private boolean isEditing;
  
  public SOpenJollyRogerCreatorScreenPacket() {}
  
  public SOpenJollyRogerCreatorScreenPacket(boolean isEditing) {
    this.isEditing = isEditing;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeBoolean(this.isEditing);
  }

  
  public static SOpenJollyRogerCreatorScreenPacket decode(PacketBuffer buffer) {
    SOpenJollyRogerCreatorScreenPacket msg = new SOpenJollyRogerCreatorScreenPacket();
    msg.isEditing = buffer.readBoolean();
    return msg;
  }

  
  public static void handle(SOpenJollyRogerCreatorScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SOpenJollyRogerCreatorScreenPacket message) {
      JollyRogerCreatorScreen.open(message.isEditing);
    }
  }
}


