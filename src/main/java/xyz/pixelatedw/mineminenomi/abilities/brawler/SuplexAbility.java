/*     */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.animations.PointBothArmsAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ 
/*     */ public class SuplexAbility extends ChargeableAbility implements IAnimatedAbility {
/*  27 */   public static final SuplexAbility INSTANCE = new SuplexAbility();
/*  28 */   private LivingEntity grabbedEntity = null;
/*     */ 
/*     */   
/*     */   public SuplexAbility() {
/*  32 */     super("Suplex", AbilityHelper.getStyleCategory());
/*  33 */     setDescription("Grabs an opponent from the back and launches it into the ground");
/*  34 */     setMaxCooldown(7.0D);
/*  35 */     setMaxChargeTime(1.0D);
/*     */     
/*  37 */     this.onStartChargingEvent = this::onUseEvent;
/*  38 */     this.duringChargingEvent = this::duringChargingEvent;
/*  39 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  44 */     if (!AbilityHelper.canUseBrawlerAbilities((LivingEntity)player)) {
/*     */       
/*  46 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_FIST, new Object[0]));
/*  47 */       return false;
/*     */     } 
/*     */     
/*  50 */     List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.2D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*  51 */     list.remove(player);
/*  52 */     list.removeIf(entity -> AbilityHelper.isTargetBlockingAbility((LivingEntity)player, entity));
/*     */     
/*  54 */     if (list.size() < 1) {
/*  55 */       return false;
/*     */     }
/*  57 */     this.grabbedEntity = (list.size() > 0) ? list.get(0) : null;
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
/*  63 */     if (!this.grabbedEntity.isAlive() || (DevilFruitCapability.get(this.grabbedEntity).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || AbilityHelper.isTargetBlockingAbility((LivingEntity)player, this.grabbedEntity)) {
/*     */       
/*  65 */       endCharging(player);
/*     */       
/*     */       return;
/*     */     } 
/*  69 */     this.grabbedEntity.rotationPitch = this.grabbedEntity.prevRotationPitch;
/*  70 */     this.grabbedEntity.rotationYaw = this.grabbedEntity.prevRotationYaw;
/*     */     
/*  72 */     this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.PARALYSIS, 5, 3));
/*  73 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
/*     */     
/*  75 */     float distance = 1.0F;
/*  76 */     Vec3d lookVec = this.grabbedEntity.getLookVec();
/*  77 */     Vec3d pos = new Vec3d(this.grabbedEntity.getPosX() - lookVec.x * distance, this.grabbedEntity.getPosY(), this.grabbedEntity.getPosZ() - lookVec.z * distance);
/*  78 */     player.setPositionAndUpdate(pos.getX(), player.getPosY(), pos.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndChargingEvent(PlayerEntity player) {
/*  83 */     if (this.grabbedEntity == null || getChargeTime() >= getMaxChargeTime()) {
/*  84 */       return false;
/*     */     }
/*  86 */     this.grabbedEntity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"), 6.0F);
/*     */     
/*  88 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, this.grabbedEntity.getPosX(), this.grabbedEntity.getPosY(), this.grabbedEntity.getPosZ(), 1.0F);
/*  89 */     explosion.setStaticDamage(6.0F);
/*  90 */     explosion.setExplosionSound(true);
/*  91 */     explosion.setDamageOwner(false);
/*  92 */     explosion.setDestroyBlocks(false);
/*  93 */     explosion.setFireAfterExplosion(false);
/*  94 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(1));
/*  95 */     explosion.setDamageEntities(true);
/*  96 */     explosion.setDamageSource((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"));
/*  97 */     explosion.doExplosion();
/*     */     
/*  99 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimation getAnimation() {
/* 105 */     return (IAnimation)PointBothArmsAnimation.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive() {
/* 111 */     return isCharging();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\SuplexAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */