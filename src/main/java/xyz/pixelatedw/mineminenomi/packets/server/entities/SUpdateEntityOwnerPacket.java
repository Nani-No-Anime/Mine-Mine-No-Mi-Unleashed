package xyz.pixelatedw.mineminenomi.packets.server.entities;

import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.entities.projectiles.hana.HanaHandsEntity;





public class SUpdateEntityOwnerPacket
{
  private int entityId;
  private int ownerId;
  
  public SUpdateEntityOwnerPacket() {}
  
  public SUpdateEntityOwnerPacket(int entityId, int ownerId) {
    this.entityId = entityId;
    this.ownerId = ownerId;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.entityId);
    buffer.writeInt(this.ownerId);
  }

  
  public static SUpdateEntityOwnerPacket decode(PacketBuffer buffer) {
    SUpdateEntityOwnerPacket msg = new SUpdateEntityOwnerPacket();
    msg.entityId = buffer.readInt();
    msg.ownerId = buffer.readInt();
    return msg;
  }

  
  public static void handle(SUpdateEntityOwnerPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SUpdateEntityOwnerPacket message) {
      ClientWorld clientWorld = (Minecraft.getInstance()).world;
      Entity entity = clientWorld.getEntityByID(message.entityId);

      
      if (entity == null || !(entity instanceof HanaHandsEntity)) {
        return;
      }
      HanaHandsEntity clutch = (HanaHandsEntity)entity;
      Entity owner = clientWorld.getEntityByID(message.ownerId);
      
      if (owner == null || !(owner instanceof LivingEntity)) {
        return;
      }
      clutch.setCaster((LivingEntity)owner);
    }
  }
}


