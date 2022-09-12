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


public class SFlySpeedPacket
{
  private float speed;
  
  public SFlySpeedPacket() {}
  
  public SFlySpeedPacket(float speed) {
    this.speed = speed;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeFloat(this.speed);
  }

  
  public static SFlySpeedPacket decode(PacketBuffer buffer) {
    SFlySpeedPacket msg = new SFlySpeedPacket();
    msg.speed = buffer.readFloat();
    return msg;
  }

  
  public static void handle(SFlySpeedPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SFlySpeedPacket message) {
      ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
      
      ((PlayerEntity)clientPlayerEntity).abilities.setFlySpeed(message.speed);
    }
  }
}


