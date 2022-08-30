/*     */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.animations.PointBothArmsAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ 
/*     */ public class SpinningBrawlAbility extends ChargeableAbility implements IAnimatedAbility {
/*  28 */   public static final SpinningBrawlAbility INSTANCE = new SpinningBrawlAbility();
/*  29 */   private LivingEntity grabbedEntity = null;
/*  30 */   private int yaw = 0;
/*     */ 
/*     */   
/*     */   public SpinningBrawlAbility() {
/*  34 */     super("Spinning Brawl", AbilityHelper.getStyleCategory());
/*  35 */     setDescription("Grabs an opponent from the back and launches it into the ground");
/*  36 */     setMaxCooldown(12.0D);
/*  37 */     setMaxChargeTime(5.0D);
/*     */     
/*  39 */     this.onStartChargingEvent = this::onUseEvent;
/*  40 */     this.duringChargingEvent = this::duringChargingEvent;
/*  41 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  46 */     if (!AbilityHelper.canUseBrawlerAbilities((LivingEntity)player)) {
/*     */       
/*  48 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_FIST, new Object[0]));
/*  49 */       return false;
/*     */     } 
/*     */     
/*  52 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 3.0D);
/*  53 */     if (mop instanceof EntityRayTraceResult) {
/*     */       
/*  55 */       EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult)mop;
/*  56 */       if (entityRayTraceResult.getEntity() instanceof LivingEntity) {
/*     */         
/*  58 */         LivingEntity e = (LivingEntity)entityRayTraceResult.getEntity();
/*  59 */         if (!e.isAlive() || (DevilFruitCapability.get(e).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || AbilityHelper.isTargetBlockingAbility((LivingEntity)player, e))
/*  60 */           return false; 
/*  61 */         this.grabbedEntity = e;
/*     */       } 
/*     */     } else {
/*     */       
/*  65 */       return false;
/*     */     } 
/*  67 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
/*  72 */     if (!this.grabbedEntity.isAlive() || (DevilFruitCapability.get(this.grabbedEntity).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || AbilityHelper.isTargetBlockingAbility((LivingEntity)player, this.grabbedEntity)) {
/*     */       
/*  74 */       endCharging(player);
/*     */       
/*     */       return;
/*     */     } 
/*  78 */     this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
/*  79 */     this.grabbedEntity.setMotion(0.0D, 0.0D, 0.0D);
/*  80 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
/*     */     
/*  82 */     if (this.yaw + 10 > 360)
/*  83 */       this.yaw = 0; 
/*  84 */     this.yaw += 10;
/*     */     
/*  86 */     float distance = 2.0F;
/*  87 */     Vec3d lookVec = player.getLookVec();
/*  88 */     Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + (player.getEyeHeight() / 2.0F) + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
/*  89 */     player.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), this.yaw, 0.0F);
/*  90 */     player.setPositionAndUpdate(player.getPosX(), player.getPosY(), player.getPosZ());
/*  91 */     this.grabbedEntity.setPositionAndUpdate(pos.x, pos.y, pos.z);
/*     */     
/*  93 */     List<LivingEntity> list = WyHelper.getEntitiesNearSphere(player.getPosition(), player.world, 4.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*  94 */     list.remove(player);
/*  95 */     list.remove(this.grabbedEntity);
/*  96 */     if (!HakiHelper.hasHardeningActive((LivingEntity)player)) {
/*  97 */       list.removeIf(entity -> DevilFruitCapability.get(entity).isLogia());
/*     */     }
/*  99 */     list.forEach(e -> {
/*     */           boolean hit = e.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"), 8.0F);
/*     */           if (hit) {
/*     */             Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.0D, 2.0D);
/*     */             e.setMotion(speed.x, 1.0D, speed.z);
/*     */             e.velocityChanged = true;
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 113 */     if (this.grabbedEntity == null || getChargeTime() >= getMaxChargeTime()) {
/* 114 */       return false;
/*     */     }
/* 116 */     this.grabbedEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), 20.0F);
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimation getAnimation() {
/* 123 */     return (IAnimation)PointBothArmsAnimation.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive() {
/* 129 */     return isCharging();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\SpinningBrawlAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */