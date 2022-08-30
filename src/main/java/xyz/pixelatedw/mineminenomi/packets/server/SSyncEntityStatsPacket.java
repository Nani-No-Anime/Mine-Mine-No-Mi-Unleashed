/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SSyncEntityStatsPacket
/*    */ {
/*    */   private int entityId;
/*    */   private INBT data;
/*    */   
/*    */   public SSyncEntityStatsPacket() {}
/*    */   
/*    */   public SSyncEntityStatsPacket(int entityId, IEntityStats props) {
/* 32 */     this.data = (INBT)new CompoundNBT();
/* 33 */     this.data = EntityStatsCapability.INSTANCE.getStorage().writeNBT(EntityStatsCapability.INSTANCE, props, null);
/* 34 */     this.entityId = entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 39 */     buffer.writeInt(this.entityId);
/* 40 */     buffer.writeCompoundTag((CompoundNBT)this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SSyncEntityStatsPacket decode(PacketBuffer buffer) {
/* 45 */     SSyncEntityStatsPacket msg = new SSyncEntityStatsPacket();
/* 46 */     msg.entityId = buffer.readInt();
/* 47 */     msg.data = (INBT)buffer.readCompoundTag();
/* 48 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SSyncEntityStatsPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 53 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 55 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 60 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSyncEntityStatsPacket message) {
/* 68 */       Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
/* 69 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/* 72 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)target);
/* 73 */       EntityStatsCapability.INSTANCE.getStorage().readNBT(EntityStatsCapability.INSTANCE, props, null, message.data);
/*    */       
/* 75 */       if (CommonConfig.INSTANCE.isExtraHeartsEnabled()) {
/*    */         
/* 77 */         IAttributeInstance maxHpAttribute = ((LivingEntity)target).getAttribute(SharedMonsterAttributes.MAX_HEALTH);
/*    */         
/* 79 */         if (props.getDoriki() / 100 <= 20) {
/* 80 */           maxHpAttribute.setBaseValue(20.0D);
/*    */         } else {
/* 82 */           maxHpAttribute.setBaseValue((props.getDoriki() / 100));
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSyncEntityStatsPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */