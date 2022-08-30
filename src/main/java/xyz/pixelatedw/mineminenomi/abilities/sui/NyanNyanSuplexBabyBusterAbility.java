/*     */ package xyz.pixelatedw.mineminenomi.abilities.sui;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;

import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class NyanNyanSuplexBabyBusterAbility extends ChargeableAbility {
/*  22 */   public static final NyanNyanSuplexBabyBusterAbility INSTANCE = new NyanNyanSuplexBabyBusterAbility();
/*     */   
/*  24 */   private LivingEntity grabbedEntity = null;
/*     */   
/*     */   public NyanNyanSuplexBabyBusterAbility() {
/*  27 */     super("Nyan Nyan Suplex: Baby Buster", AbilityHelper.getDevilFruitCategory());
/*  28 */     setDescription("While swimming, grabs an opponent from the back and launches them into the sky and then to the ground, creating a huge explosion");
/*  29 */     setMaxCooldown(40.0D);
/*  30 */     setMaxChargeTime(8.0D);
/*     */     
/*  32 */     this.onStartChargingEvent = this::onUseEvent;
/*  33 */     this.duringChargingEvent = this::duringChargingEvent;
/*  34 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  39 */     this.grabbedEntity = null;
/*     */     
/*  41 */     FreeSwimmingAbility ability = (FreeSwimmingAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)FreeSwimmingAbility.INSTANCE);
/*  42 */     if (ability != null && ability.isSwimming) {
/*     */       
/*  44 */       List<LivingEntity> list = WyHelper.getEntitiesNearSphere(player.getPosition(), player.world, 1.2D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*  45 */       list.remove(player);
/*  46 */       list.removeIf(entity -> AbilityHelper.isTargetBlockingAbility((LivingEntity)player, entity));
/*  47 */       if (!HakiHelper.hasHardeningActive((LivingEntity)player))
/*  48 */         list.removeIf(entity -> DevilFruitCapability.get(entity).isLogia()); 
/*  49 */       this.grabbedEntity = (list.size() > 0) ? list.get(0) : null;
/*  50 */       return (list.size() > 0);
/*     */     } 
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
/*  58 */     if (!this.grabbedEntity.isAlive() || (DevilFruitCapability.get(this.grabbedEntity).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || AbilityHelper.isTargetBlockingAbility((LivingEntity)player, this.grabbedEntity)) {
/*     */       
/*  60 */       endCharging(player);
/*     */       return;
/*     */     } 
/*  63 */     this.grabbedEntity.fallDistance = 0.0F;
/*  64 */     this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 20, 0));
/*  65 */     FreeSwimmingAbility ability = (FreeSwimmingAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)FreeSwimmingAbility.INSTANCE);
/*  66 */     boolean isActive = (ability != null && ability.isContinuous());
/*  67 */     if (isActive) {
/*  68 */       ability.endContinuity(player);
/*     */     }
/*  70 */     if (chargeTimer >= getMaxChargeTime() * 0.5D) {
/*  71 */       this.grabbedEntity.setMotion((this.grabbedEntity.getMotion()).x, 1.6D, (this.grabbedEntity.getMotion()).z);
/*     */     } else {
/*  73 */       this.grabbedEntity.setMotion((this.grabbedEntity.getMotion()).x, -1.6D, (this.grabbedEntity.getMotion()).z);
/*  74 */     }  this.grabbedEntity.velocityChanged = true;
/*     */     
/*  76 */     float distance = 1.0F;
/*  77 */     Vec3d lookVec = this.grabbedEntity.getLookVec();
/*     */ 
/*     */     
/*  80 */     Vec3d pos = new Vec3d(this.grabbedEntity.getPosX() - lookVec.x * distance, this.grabbedEntity.getPosY(), this.grabbedEntity.getPosZ() - lookVec.z * distance);
/*  81 */     player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndChargingEvent(PlayerEntity player) {
/*  86 */     if (this.grabbedEntity == null || getChargeTime() >= getMaxChargeTime()) {
/*  87 */       return false;
/*     */     }
/*  89 */     this.grabbedEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), 25.0F);
/*  90 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, this.grabbedEntity.getPosX(), this.grabbedEntity.getPosY(), this.grabbedEntity.getPosZ(), 6.0F);
/*  91 */     explosion.setStaticDamage(75.0F);
/*  92 */     explosion.setExplosionSound(true);
/*  93 */     explosion.setDamageOwner(false);
/*  94 */     explosion.setDestroyBlocks(true);
/*  95 */     explosion.setFireAfterExplosion(false);
/*  96 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(5));
/*  97 */     explosion.setDamageEntities(true);
/*  98 */     explosion.doExplosion();
/*     */     
/* 100 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\sui\NyanNyanSuplexBabyBusterAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */