/*     */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertEncierroParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ 
/*     */ public class DesertEncierroAbility extends ChargeableAbility {
/*  25 */   public static final Ability INSTANCE = (Ability)new DesertEncierroAbility();
/*     */   
/*  27 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new DesertEncierroParticleEffect();
/*  28 */   private LivingEntity grabbedEntity = null;
/*     */ 
/*     */   
/*     */   public DesertEncierroAbility() {
/*  32 */     super("Desert Encierro", AbilityHelper.getDevilFruitCategory());
/*  33 */     setDescription("Quickly drains the enemy in front of the user of their moisture, leaving them weak for a few seconds");
/*     */     
/*  35 */     setMaxCooldown(15.0D);
/*  36 */     setMaxChargeTime(5.0D);
/*     */     
/*  38 */     this.onStartChargingEvent = this::onUseEvent;
/*  39 */     this.duringChargingEvent = this::duringChargingEvent;
/*  40 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  45 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 3.0D);
/*  46 */     if (mop instanceof EntityRayTraceResult) {
/*     */       
/*  48 */       EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult)mop;
/*  49 */       if (entityRayTraceResult.getEntity() instanceof LivingEntity)
/*     */       {
/*  51 */         LivingEntity e = (LivingEntity)entityRayTraceResult.getEntity();
/*  52 */         if (!e.isAlive() || (DevilFruitCapability.get(e).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || AbilityHelper.isTargetBlockingAbility((LivingEntity)player, e)) {
/*  53 */           return false;
/*     */         }
/*  55 */         this.grabbedEntity = e;
/*  56 */         SunaHelper.drainLiquids(this.grabbedEntity, (int)WyHelper.randomWithRange(0, 1), (int)WyHelper.randomWithRange(1, 3), (int)WyHelper.randomWithRange(0, 1));
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  61 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NO_TARGET, new Object[] { getName() }));
/*  62 */       return false;
/*     */     } 
/*     */     
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
/*  70 */     if (!this.grabbedEntity.isAlive() || !AbilityHelper.canUseMomentumAbility(player) || (
/*  71 */       DevilFruitCapability.get(this.grabbedEntity).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || 
/*  72 */       AbilityHelper.isTargetBlockingAbility((LivingEntity)player, this.grabbedEntity)) {
/*     */       
/*  74 */       endCharging(player);
/*     */       
/*     */       return;
/*     */     } 
/*  78 */     PARTICLES.spawn(this.grabbedEntity.world, this.grabbedEntity.getPosX(), this.grabbedEntity.getPosY(), this.grabbedEntity.getPosZ(), 0.0D, 0.0D, 0.0D);
/*  79 */     this.grabbedEntity.rotationPitch = this.grabbedEntity.prevRotationPitch;
/*  80 */     this.grabbedEntity.rotationYaw = this.grabbedEntity.prevRotationYaw;
/*     */     
/*  82 */     this.grabbedEntity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
/*  83 */     this.grabbedEntity.attackEntityFrom(ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this).setDamageBypassesArmor().setDamageIsAbsolute(), 2.0F);
/*     */     
/*  85 */     float distance = 2.0F;
/*  86 */     Vec3d lookVec = player.getLookVec();
/*  87 */     Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + (player.getEyeHeight() / 2.0F) + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
/*  88 */     this.grabbedEntity.setPositionAndUpdate(pos.x, pos.y, pos.z);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndChargingEvent(PlayerEntity player) {
/*  93 */     if (this.grabbedEntity == null) {
/*  94 */       return false;
/*     */     }
/*  96 */     this.grabbedEntity.attackEntityFrom(ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this).setDamageBypassesArmor().setDamageIsAbsolute(), 20.0F);
/*  97 */     this.grabbedEntity.addPotionEffect(new EffectInstance(Effects.WITHER, 200, 4, false, false));
/*  98 */     this.grabbedEntity.addPotionEffect(new EffectInstance(Effects.HUNGER, 300, 5, false, false));
/*  99 */     this.grabbedEntity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 300, 1, false, false));
/* 100 */     this.grabbedEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 300, 1, false, false));
/* 101 */     this.grabbedEntity.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 300, 1, false, false));
/*     */     
/* 103 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\DesertEncierroAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */