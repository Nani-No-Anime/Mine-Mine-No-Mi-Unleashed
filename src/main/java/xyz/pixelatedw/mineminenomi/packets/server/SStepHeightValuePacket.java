package xyz.pixelatedw.mineminenomi.packets.server;

import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;




public class SStepHeightValuePacket
{
  private float value;
  
  public SStepHeightValuePacket() {}
  
  public SStepHeightValuePacket(float value) {
    this.value = value;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeFloat(this.value);
  }

  
  public static SStepHeightValuePacket decode(PacketBuffer buffer) {
    SStepHeightValuePacket msg = new SStepHeightValuePacket();
    msg.value = buffer.readFloat();
    return msg;
  }

  
  public static void handle(SStepHeightValuePacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SStepHeightValuePacket message) {
      ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
      
      ((PlayerEntity)clientPlayerEntity).stepHeight = message.value;
    }
  }
}


