package xyz.pixelatedw.mineminenomi.packets.server.ui;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.screens.ArenaSetupScreen;

import java.util.function.Supplier;



public class SOpenArenaSetupScreenPacket
{
  private String arenaName;
  
  public SOpenArenaSetupScreenPacket() {}
  
  public SOpenArenaSetupScreenPacket(String arenaName) {
    this.arenaName = arenaName;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.arenaName.length());
    buffer.writeString(this.arenaName, this.arenaName.length());
  }

  
  public static SOpenArenaSetupScreenPacket decode(PacketBuffer buffer) {
    SOpenArenaSetupScreenPacket msg = new SOpenArenaSetupScreenPacket();
    int len = buffer.readInt();
    msg.arenaName = buffer.readString(len);
    return msg;
  }

  
  public static void handle(SOpenArenaSetupScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SOpenArenaSetupScreenPacket message) {
      ArenaSetupScreen.open(message.arenaName);
    }
  }
}


