package xyz.pixelatedw.mineminenomi.packets.server;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SFlightValuePacket
{
  private boolean value;
  
  public SFlightValuePacket() {}
  
  public SFlightValuePacket(boolean value) {
    this.value = value;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeBoolean(this.value);
  }

  
  public static SFlightValuePacket decode(PacketBuffer buffer) {
    SFlightValuePacket msg = new SFlightValuePacket();
    msg.value = buffer.readBoolean();
    return msg;
  }

  
  public static void handle(SFlightValuePacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SFlightValuePacket message) {
      ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
      
      ((PlayerEntity)clientPlayerEntity).abilities.allowFlying = message.value;
    }
  }
}


