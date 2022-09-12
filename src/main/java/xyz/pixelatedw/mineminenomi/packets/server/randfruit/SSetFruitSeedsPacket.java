package xyz.pixelatedw.mineminenomi.packets.server.randfruit;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.events.devilfruits.RandomFruitEvents;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


public class SSetFruitSeedsPacket
{
  private HashMap<Integer, Long> seeds = new HashMap<>();



  
  public SSetFruitSeedsPacket(HashMap<Integer, Long> seeds) {
    this.seeds = seeds;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.seeds.entrySet().size());
    for (Map.Entry<Integer, Long> entry : this.seeds.entrySet()) {
      
      buffer.writeInt(((Integer)entry.getKey()).intValue());
      buffer.writeLong(((Long)entry.getValue()).longValue());
    } 
  }

  
  public static SSetFruitSeedsPacket decode(PacketBuffer buffer) {
    SSetFruitSeedsPacket msg = new SSetFruitSeedsPacket();
    HashMap<Integer, Long> seeds = new HashMap<>();
    int size = buffer.readInt();
    for (int i = 0; i < size; i++) {
      
      int key = buffer.readInt();
      long seed = buffer.readLong();
      seeds.put(Integer.valueOf(key), Long.valueOf(seed));
    } 
    msg.seeds = seeds;
    return msg;
  }

  
  public static void handle(SSetFruitSeedsPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
  
  public SSetFruitSeedsPacket() {}
  
  public static class ClientHandler {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SSetFruitSeedsPacket message) {
      RandomFruitEvents.Client.FRUIT_SEEDS = message.seeds;
      RandomFruitEvents.Client.DIRTY = true;
    }
  }
}


