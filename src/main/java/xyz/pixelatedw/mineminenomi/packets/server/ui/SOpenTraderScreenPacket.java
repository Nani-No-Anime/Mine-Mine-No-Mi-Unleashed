package xyz.pixelatedw.mineminenomi.packets.server.ui;

import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
import xyz.pixelatedw.mineminenomi.screens.TraderScreen;





public class SOpenTraderScreenPacket
{
  private int traderEntity;
  
  public SOpenTraderScreenPacket() {}
  
  public SOpenTraderScreenPacket(int traderEntity) {
    this.traderEntity = traderEntity;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.traderEntity);
  }

  
  public static SOpenTraderScreenPacket decode(PacketBuffer buffer) {
    SOpenTraderScreenPacket msg = new SOpenTraderScreenPacket();
    msg.traderEntity = buffer.readInt();
    return msg;
  }

  
  public static void handle(SOpenTraderScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SOpenTraderScreenPacket message) {
      Entity trader = (Minecraft.getInstance()).world.getEntityByID(message.traderEntity);
      if (trader instanceof TraderEntity)
        TraderScreen.open((TraderEntity)trader); 
    }
  }
}


