package xyz.pixelatedw.mineminenomi.packets.server.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;







public class SCloseScreenPacket
{
  public void encode(PacketBuffer buffer) {}
  
  public static SCloseScreenPacket decode(PacketBuffer buffer) {
    SCloseScreenPacket msg = new SCloseScreenPacket();
    return msg;
  }

  
  public static void handle(SCloseScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SCloseScreenPacket message) {
      Minecraft.getInstance().displayGuiScreen((Screen)null);
    }
  }
}


