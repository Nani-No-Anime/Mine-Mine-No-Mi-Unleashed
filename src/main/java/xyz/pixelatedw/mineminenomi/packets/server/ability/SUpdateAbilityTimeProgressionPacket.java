/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SUpdateAbilityTimeProgressionPacket
/*    */ {
/*    */   private int entityId;
/*    */   private int abilityId;
/*    */   private double timeProgression;
/*    */   
/*    */   public SUpdateAbilityTimeProgressionPacket() {}
/*    */   
/*    */   public SUpdateAbilityTimeProgressionPacket(PlayerEntity player, Ability ability) {
/* 33 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 35 */     this.entityId = player.getEntityId();
/* 36 */     this.abilityId = props.getEquippedAbilitySlot(ability);
/*    */     
/* 38 */     this.timeProgression = player.getAttribute(ModAttributes.TIME_PROGRESSION).getValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 43 */     buffer.writeInt(this.entityId);
/* 44 */     buffer.writeInt(this.abilityId);
/*    */     
/* 46 */     buffer.writeDouble(this.timeProgression);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SUpdateAbilityTimeProgressionPacket decode(PacketBuffer buffer) {
/* 51 */     SUpdateAbilityTimeProgressionPacket msg = new SUpdateAbilityTimeProgressionPacket();
/* 52 */     msg.entityId = buffer.readInt();
/* 53 */     msg.abilityId = buffer.readInt();
/*    */     
/* 55 */     msg.timeProgression = buffer.readDouble();
/*    */     
/* 57 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SUpdateAbilityTimeProgressionPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 62 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 64 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 69 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SUpdateAbilityTimeProgressionPacket message) {
/* 77 */       Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
/* 78 */       if (target == null || !(target instanceof PlayerEntity) || message.abilityId < 0) {
/*    */         return;
/*    */       }
/* 81 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)target);
/* 82 */       Ability ability = props.getEquippedAbility(message.abilityId);
/*    */       
/* 84 */       if (ability == null) {
/*    */         return;
/*    */       }
/* 87 */       ability.setTimeProgression(message.timeProgression);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SUpdateAbilityTimeProgressionPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */