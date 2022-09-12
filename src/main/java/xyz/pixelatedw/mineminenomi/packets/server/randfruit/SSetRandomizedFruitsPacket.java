package xyz.pixelatedw.mineminenomi.packets.server.randfruit;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.events.devilfruits.RandomFruitEvents;

import java.util.function.Supplier;



public class SSetRandomizedFruitsPacket
{
  private boolean flag;
  
  public SSetRandomizedFruitsPacket() {}
  
  public SSetRandomizedFruitsPacket(boolean flag) {
    this.flag = flag;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeBoolean(this.flag);
  }

  
  public static SSetRandomizedFruitsPacket decode(PacketBuffer buffer) {
    SSetRandomizedFruitsPacket msg = new SSetRandomizedFruitsPacket();
    msg.flag = buffer.readBoolean();
    return msg;
  }

  
  public static void handle(SSetRandomizedFruitsPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SSetRandomizedFruitsPacket message) {
      RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT = true;
    }
  }
}


