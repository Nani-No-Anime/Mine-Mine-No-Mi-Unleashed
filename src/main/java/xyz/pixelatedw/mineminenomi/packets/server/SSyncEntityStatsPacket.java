package xyz.pixelatedw.mineminenomi.packets.server;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;

import java.util.function.Supplier;





public class SSyncEntityStatsPacket
{
  private int entityId;
  private INBT data;
  
  public SSyncEntityStatsPacket() {}
  
  public SSyncEntityStatsPacket(int entityId, IEntityStats props) {
    this.data = (INBT)new CompoundNBT();
    this.data = EntityStatsCapability.INSTANCE.getStorage().writeNBT(EntityStatsCapability.INSTANCE, props, null);
    this.entityId = entityId;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.entityId);
    buffer.writeCompoundTag((CompoundNBT)this.data);
  }

  
  public static SSyncEntityStatsPacket decode(PacketBuffer buffer) {
    SSyncEntityStatsPacket msg = new SSyncEntityStatsPacket();
    msg.entityId = buffer.readInt();
    msg.data = (INBT)buffer.readCompoundTag();
    return msg;
  }

  
  public static void handle(SSyncEntityStatsPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SSyncEntityStatsPacket message) {
      Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
      if (target == null || !(target instanceof LivingEntity)) {
        return;
      }
      IEntityStats props = EntityStatsCapability.get((LivingEntity)target);
      EntityStatsCapability.INSTANCE.getStorage().readNBT(EntityStatsCapability.INSTANCE, props, null, message.data);
      
      if (CommonConfig.INSTANCE.isExtraHeartsEnabled()) {
        
        IAttributeInstance maxHpAttribute = ((LivingEntity)target).getAttribute(SharedMonsterAttributes.MAX_HEALTH);
        
        if (Math.round(props.getDoriki() / 1000) <= 0) {
          maxHpAttribute.setBaseValue(20.0D);
        } else {
          System.out.println("hp to doriki "  + (props.getDoriki() / 1000)+20.0D );
          maxHpAttribute.setBaseValue((props.getDoriki() / 1000)+20);
        } 
      } 
    }
  }
}


