package xyz.pixelatedw.mineminenomi.packets.server.ability;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;



public class SAnimeScreamPacket
{
  private int entityId;
  private String message;
  
  public SAnimeScreamPacket() {}
  
  public SAnimeScreamPacket(int entityId, String message) {
    this.entityId = entityId;
    this.message = message;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.entityId);
    buffer.writeInt(this.message.length());
    buffer.writeString(this.message, this.message.length());
  }

  
  public static SAnimeScreamPacket decode(PacketBuffer buffer) {
    SAnimeScreamPacket msg = new SAnimeScreamPacket();
    msg.entityId = buffer.readInt();
    int len = buffer.readInt();
    msg.message = buffer.readString(len);
    return msg;
  }

  
  public static void handle(SAnimeScreamPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SAnimeScreamPacket message) {
      Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
      if (target == null || !(target instanceof net.minecraft.entity.player.PlayerEntity)) {
        return;
      }
      (Minecraft.getInstance()).player.sendMessage((ITextComponent)new StringTextComponent("<" + target.getDisplayName().getFormattedText() + "> " + message.message.toUpperCase()));
    }
  }
}


