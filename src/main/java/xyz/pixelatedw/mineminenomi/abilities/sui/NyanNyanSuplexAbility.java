/*    */ package xyz.pixelatedw.mineminenomi.abilities.sui;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;

import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class NyanNyanSuplexAbility extends ChargeableAbility {
/* 22 */   public static final NyanNyanSuplexAbility INSTANCE = new NyanNyanSuplexAbility();
/* 23 */   private LivingEntity grabbedEntity = null;
/*    */ 
/*    */   
/*    */   public NyanNyanSuplexAbility() {
/* 27 */     super("Nyan Nyan Suplex", AbilityHelper.getDevilFruitCategory());
/* 28 */     setDescription("While swimming, grabs an opponent from the back and launches them into the ground, dealing moderate damage and creating a small crater");
/* 29 */     setMaxCooldown(10.0D);
/* 30 */     setMaxChargeTime(2.0D);
/*    */     
/* 32 */     this.onStartChargingEvent = this::onUseEvent;
/* 33 */     this.duringChargingEvent = this::duringChargingEvent;
/* 34 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 39 */     this.grabbedEntity = null;
/* 40 */     FreeSwimmingAbility ability = (FreeSwimmingAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)FreeSwimmingAbility.INSTANCE);
/* 41 */     if (ability != null && ability.isSwimming) {
/* 42 */       List<LivingEntity> list = WyHelper.getEntitiesNearSphere(player.getPosition(), player.world, 1.2D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 43 */       list.remove(player);
/* 44 */       list.removeIf(entity -> AbilityHelper.isTargetBlockingAbility((LivingEntity)player, entity));
/* 45 */       if (!HakiHelper.hasHardeningActive((LivingEntity)player))
/* 46 */         list.removeIf(entity -> DevilFruitCapability.get(entity).isLogia()); 
/* 47 */       this.grabbedEntity = (list.size() > 0) ? list.get(0) : null;
/* 48 */       return (list.size() > 0);
/*    */     } 
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
/* 56 */     if (!this.grabbedEntity.isAlive() || (DevilFruitCapability.get(this.grabbedEntity).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || AbilityHelper.isTargetBlockingAbility((LivingEntity)player, this.grabbedEntity)) {
/*    */       
/* 58 */       endCharging(player);
/*    */       
/*    */       return;
/*    */     } 
/* 62 */     this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 20, 0));
/* 63 */     FreeSwimmingAbility ability = (FreeSwimmingAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)FreeSwimmingAbility.INSTANCE);
/* 64 */     boolean isActive = (ability != null && ability.isContinuous());
/* 65 */     if (isActive) {
/* 66 */       ability.endContinuity(player);
/*    */     }
/* 68 */     float distance = 1.0F;
/* 69 */     Vec3d lookVec = this.grabbedEntity.getLookVec();
/*    */ 
/*    */     
/* 72 */     Vec3d pos = new Vec3d(this.grabbedEntity.getPosX() - lookVec.x * distance, this.grabbedEntity.getPosY(), this.grabbedEntity.getPosZ() - lookVec.z * distance);
/* 73 */     player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 78 */     if (this.grabbedEntity == null || getChargeTime() >= getMaxChargeTime()) {
/* 79 */       return false;
/*    */     }
/* 81 */     this.grabbedEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10.0F);
/* 82 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, this.grabbedEntity.getPosX(), this.grabbedEntity.getPosY(), this.grabbedEntity.getPosZ(), 2.0F);
/* 83 */     explosion.setStaticDamage(30.0F);
/* 84 */     explosion.setExplosionSound(true);
/* 85 */     explosion.setDamageOwner(false);
/* 86 */     explosion.setDestroyBlocks(true);
/* 87 */     explosion.setFireAfterExplosion(false);
/* 88 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 89 */     explosion.setDamageEntities(true);
/* 90 */     explosion.doExplosion();
/*    */     
/* 92 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\sui\NyanNyanSuplexAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */