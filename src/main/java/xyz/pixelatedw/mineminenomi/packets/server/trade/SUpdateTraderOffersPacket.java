package xyz.pixelatedw.mineminenomi.packets.server.trade;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.TradeEntry;
import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.SkypieanTraderEntity;





public class SUpdateTraderOffersPacket
{
  private int traderEntity;
  private List<TradeEntry> tradeEntries;
  private int vearthTrades;
  
  public SUpdateTraderOffersPacket() {}
  
  public SUpdateTraderOffersPacket(int traderEntity, List<TradeEntry> tradeEntries) {
    this.traderEntity = traderEntity;
    this.tradeEntries = tradeEntries;
  }

  
  public SUpdateTraderOffersPacket(int traderEntity, List<TradeEntry> tradeEntries, int vearthTrades) {
    this.traderEntity = traderEntity;
    this.tradeEntries = tradeEntries;
    this.vearthTrades = vearthTrades;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.traderEntity);
    buffer.writeInt(this.tradeEntries.size());
    for (TradeEntry entry : this.tradeEntries)
    {
      buffer.writeItemStack(entry.getItemStack());
    }
    buffer.writeInt(this.vearthTrades);
  }

  
  public static SUpdateTraderOffersPacket decode(PacketBuffer buffer) {
    SUpdateTraderOffersPacket msg = new SUpdateTraderOffersPacket();
    msg.traderEntity = buffer.readInt();
    int size = buffer.readInt();
    List<TradeEntry> entries = new ArrayList<>();
    for (int i = 0; i < size; i++)
    {
      entries.add(new TradeEntry(buffer.readItemStack()));
    }
    msg.tradeEntries = entries;
    msg.vearthTrades = buffer.readInt();
    return msg;
  }

  
  public static void handle(SUpdateTraderOffersPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SUpdateTraderOffersPacket message) {
      Entity entity = (Minecraft.getInstance()).world.getEntityByID(message.traderEntity);
      if (entity instanceof TraderEntity) {
        
        ((TraderEntity)entity).setTradingItems(message.tradeEntries);
        if (entity instanceof SkypieanTraderEntity)
        {
          ((SkypieanTraderEntity)entity).setTradesLeft(message.vearthTrades);
        }
      } 
    }
  }
}


