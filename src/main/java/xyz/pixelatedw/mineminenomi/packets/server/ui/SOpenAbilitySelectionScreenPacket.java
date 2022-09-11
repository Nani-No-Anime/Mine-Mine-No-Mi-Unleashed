package xyz.pixelatedw.mineminenomi.packets.server.ui;

import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.screens.SelectHotbarAbilitiesScreen;







public class SOpenAbilitySelectionScreenPacket
{
  public void encode(PacketBuffer buffer) {}
  
  public static SOpenAbilitySelectionScreenPacket decode(PacketBuffer buffer) {
    SOpenAbilitySelectionScreenPacket msg = new SOpenAbilitySelectionScreenPacket();
    return msg;
  }

  
  public static void handle(SOpenAbilitySelectionScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SOpenAbilitySelectionScreenPacket message) {
      ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
      
      if (clientPlayerEntity == null) {
        return;
      }
      Minecraft.getInstance().displayGuiScreen((Screen)new SelectHotbarAbilitiesScreen((PlayerEntity)clientPlayerEntity));
    }
  }
}


