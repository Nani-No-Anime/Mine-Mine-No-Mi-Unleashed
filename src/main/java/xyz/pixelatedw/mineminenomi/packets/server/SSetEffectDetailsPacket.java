/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.mixins.EffectInstanceMixin;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SSetEffectDetailsPacket
/*    */ {
/*    */   private int entityId;
/*    */   private int effectId;
/*    */   private int duration;
/*    */   
/*    */   public SSetEffectDetailsPacket() {}
/*    */   
/*    */   public SSetEffectDetailsPacket(int entityId, EffectInstance instance) {
/* 27 */     this.entityId = entityId;
/* 28 */     this.effectId = (byte)(Effect.getId(instance.getPotion()) & 0xFF);
/* 29 */     this.duration = instance.getDuration();
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 34 */     buffer.writeInt(this.entityId);
/* 35 */     buffer.writeInt(this.effectId);
/* 36 */     buffer.writeInt(this.duration);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SSetEffectDetailsPacket decode(PacketBuffer buffer) {
/* 41 */     SSetEffectDetailsPacket msg = new SSetEffectDetailsPacket();
/* 42 */     msg.entityId = buffer.readInt();
/* 43 */     msg.effectId = buffer.readInt();
/* 44 */     msg.duration = buffer.readInt();
/* 45 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SSetEffectDetailsPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 50 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 52 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 57 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSetEffectDetailsPacket message) {
/* 65 */       Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
/*    */       
/* 67 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/* 70 */       LivingEntity livingTarget = (LivingEntity)target;
/* 71 */       Effect effect = Effect.get(message.effectId & 0xFF);
/*    */       
/* 73 */       ((EffectInstanceMixin)livingTarget.getActivePotionEffect(effect)).setDuration(message.duration);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSetEffectDetailsPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */