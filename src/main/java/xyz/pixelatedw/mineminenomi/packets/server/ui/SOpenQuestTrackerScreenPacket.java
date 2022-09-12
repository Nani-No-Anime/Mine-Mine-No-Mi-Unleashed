package xyz.pixelatedw.mineminenomi.packets.server.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.screens.QuestsTrackerScreen;

import java.util.function.Supplier;







public class SOpenQuestTrackerScreenPacket
{
  public void encode(PacketBuffer buffer) {}
  
  public static SOpenQuestTrackerScreenPacket decode(PacketBuffer buffer) {
    SOpenQuestTrackerScreenPacket msg = new SOpenQuestTrackerScreenPacket();
    return msg;
  }

  
  public static void handle(SOpenQuestTrackerScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SOpenQuestTrackerScreenPacket message) {
      ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
      
      Minecraft.getInstance().displayGuiScreen((Screen)new QuestsTrackerScreen((PlayerEntity)clientPlayerEntity));
    }
  }
}


