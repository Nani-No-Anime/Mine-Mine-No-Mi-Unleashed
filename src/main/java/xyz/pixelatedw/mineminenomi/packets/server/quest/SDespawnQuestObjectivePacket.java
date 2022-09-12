package xyz.pixelatedw.mineminenomi.packets.server.quest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;





public class SDespawnQuestObjectivePacket
{
  private UUID ownerUUID;
  private int targetId;
  
  public SDespawnQuestObjectivePacket() {}
  
  public SDespawnQuestObjectivePacket(UUID uuid, int targetId) {
    this.ownerUUID = uuid;
    this.targetId = targetId;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeUniqueId(this.ownerUUID);
    buffer.writeInt(this.targetId);
  }

  
  public static SDespawnQuestObjectivePacket decode(PacketBuffer buffer) {
    SDespawnQuestObjectivePacket msg = new SDespawnQuestObjectivePacket();
    msg.ownerUUID = buffer.readUniqueId();
    msg.targetId = buffer.readInt();
    return msg;
  }

  
  public static void handle(SDespawnQuestObjectivePacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SDespawnQuestObjectivePacket message) {
      ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
      ClientWorld clientWorld = (Minecraft.getInstance()).world;
      Entity target = clientWorld.getEntityByID(message.targetId);
      
      if (clientPlayerEntity.getUniqueID().equals(message.ownerUUID)) {
        return;
      }
      target.remove();
    }
  }
}


