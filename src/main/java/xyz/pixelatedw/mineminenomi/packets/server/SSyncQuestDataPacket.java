package xyz.pixelatedw.mineminenomi.packets.server;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;

import java.util.function.Supplier;





public class SSyncQuestDataPacket
{
  private int entityId;
  private INBT data;
  
  public SSyncQuestDataPacket() {}
  
  public SSyncQuestDataPacket(int entityId, IQuestData props) {
    this.data = (INBT)new CompoundNBT();
    this.data = QuestDataCapability.INSTANCE.getStorage().writeNBT(QuestDataCapability.INSTANCE, props, null);
    this.entityId = entityId;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.entityId);
    buffer.writeCompoundTag((CompoundNBT)this.data);
  }

  
  public static SSyncQuestDataPacket decode(PacketBuffer buffer) {
    SSyncQuestDataPacket msg = new SSyncQuestDataPacket();
    msg.entityId = buffer.readInt();
    msg.data = (INBT)buffer.readCompoundTag();
    return msg;
  }

  
  public static void handle(SSyncQuestDataPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SSyncQuestDataPacket message) {
      Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
      if (target == null || !(target instanceof PlayerEntity)) {
        return;
      }
      IQuestData props = QuestDataCapability.get((PlayerEntity)target);
      
      QuestDataCapability.INSTANCE.getStorage().readNBT(QuestDataCapability.INSTANCE, props, null, message.data);
    }
  }
}


