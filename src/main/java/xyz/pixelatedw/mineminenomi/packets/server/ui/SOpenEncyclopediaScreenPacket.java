package xyz.pixelatedw.mineminenomi.packets.server.ui;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.screens.EncyclopediaScreen;

import java.util.function.Supplier;



public class SOpenEncyclopediaScreenPacket
{
  private ItemStack book;
  
  public SOpenEncyclopediaScreenPacket() {}
  
  public SOpenEncyclopediaScreenPacket(ItemStack book) {
    this.book = book;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeItemStack(this.book);
  }

  
  public static SOpenEncyclopediaScreenPacket decode(PacketBuffer buffer) {
    SOpenEncyclopediaScreenPacket msg = new SOpenEncyclopediaScreenPacket();
    msg.book = buffer.readItemStack();
    return msg;
  }

  
  public static void handle(SOpenEncyclopediaScreenPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message)); 
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SOpenEncyclopediaScreenPacket message) {
      EncyclopediaScreen.open(message.book);
    }
  }
}


