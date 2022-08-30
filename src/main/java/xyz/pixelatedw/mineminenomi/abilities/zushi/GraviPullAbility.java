/*    */ package xyz.pixelatedw.mineminenomi.abilities.zushi;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class GraviPullAbility extends ChargeableAbility {
/* 18 */   public static final GraviPullAbility INSTANCE = new GraviPullAbility();
/*    */ 
/*    */   
/*    */   public GraviPullAbility() {
/* 22 */     super("Gravi Pull", AbilityHelper.getDevilFruitCategory());
/* 23 */     setDescription("Pulls all enemies in a radius towards the user");
/* 24 */     this.onStartChargingEvent = this::onStartChargingEvent;
/* 25 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */     
/* 27 */     setMaxCooldown(17.0D);
/* 28 */     setMaxChargeTime(3.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartChargingEvent(PlayerEntity player) {
/* 33 */     for (double i = 0.0D; i < 7.283185307179586D; i += 0.09817477042468103D) {
/*    */       
/* 35 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.GASU);
/* 36 */       data.setLife(100);
/* 37 */       data.setSize(2.0F);
/* 38 */       double offsetX = Math.cos(i);
/* 39 */       double offsetZ = Math.sin(i);
/* 40 */       data.setMotion(offsetX / 5.0D, 0.0D, offsetZ / 5.0D);
/* 41 */       data.setHasMotionDecay(false);
/* 42 */       WyHelper.spawnParticles(data, (ServerWorld)player.world, player.getPosX() + offsetX, player.getPosY() + 1.0D, player.getPosZ() + offsetZ);
/*    */     } 
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 49 */     for (double i = 0.0D; i < 7.283185307179586D; i += 0.09817477042468103D) {
/*    */       
/* 51 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.GASU);
/* 52 */       data.setLife(5);
/* 53 */       data.setSize(2.0F);
/* 54 */       double offsetX = Math.cos(i) * 20.0D;
/* 55 */       double offsetZ = Math.sin(i) * 20.0D;
/* 56 */       data.setMotion(-offsetX / 10.0D, 0.0D, -offsetZ / 10.0D);
/* 57 */       WyHelper.spawnParticles(data, (ServerWorld)player.world, player.getPosX() + offsetX, player.getPosY() + 1.0D, player.getPosZ() + offsetZ);
/*    */     } 
/*    */     
/* 60 */     List<Entity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 16.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 61 */     list.forEach(e -> {
/*    */           double offsetX = player.getPosX() - e.getPosX();
/*    */           
/*    */           double offsetZ = player.getPosZ() - e.getPosZ();
/*    */           e.setMotion(offsetX / 2.0D, (player.getPosY() - e.getPosY()) / 4.0D, offsetZ / 2.0D);
/*    */           e.velocityChanged = true;
/*    */         });
/* 68 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\zushi\GraviPullAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */