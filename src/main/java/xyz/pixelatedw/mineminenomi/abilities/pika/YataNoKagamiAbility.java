/*     */ package xyz.pixelatedw.mineminenomi.abilities.pika;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.pika.ChargingPikaParticleEvent;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.pika.FlashParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.pika.YataNoKagamiParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.animations.PointArmAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ 
/*     */ public class YataNoKagamiAbility extends ChargeableAbility implements IFallDamageBlockingAbility, IAnimatedAbility {
/*  32 */   public static final Ability INSTANCE = (Ability)new YataNoKagamiAbility();
/*     */   
/*  34 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new YataNoKagamiParticleEffect();
/*  35 */   private static final ParticleEffect FLASH_PARTICLES = (ParticleEffect)new FlashParticleEffect();
/*  36 */   private static final ParticleEffect CHARGE_PARTICLE = (ParticleEffect)new ChargingPikaParticleEvent();
/*     */   
/*     */   private boolean hasFallDamage = true;
/*     */   private static final float MAX_TELEPORT_DISTANCE = 200.0F;
/*  40 */   private LightningEntity bolt = null;
/*     */ 
/*     */   
/*     */   public YataNoKagamiAbility() {
/*  44 */     super("Yata no Kagami", AbilityHelper.getDevilFruitCategory());
/*  45 */     setMaxCooldown(3.0D);
/*  46 */     setMaxChargeTime(1.5D);
/*  47 */     setCancelable();
/*  48 */     setDescription("The user forms light between his hands, and reflects it off any surface he wishes, instantly teleporting it towards where the light hit.");
/*     */     
/*  50 */     this.duringChargingEvent = this::duringChargingEvent;
/*  51 */     this.onEndChargingEvent = this::endChargeEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int timer) {
/*  56 */     BlockRayTraceResult trace = WyHelper.rayTraceBlocks((Entity)player, 1.0D);
/*     */     
/*  58 */     if (this.bolt == null) {
/*     */       
/*  60 */       Direction dir = Direction.fromAngle(player.rotationYaw);
/*  61 */       Vec3d hitVec = trace.getHitVec().add(dir.getXOffset(), dir.getYOffset(), dir.getZOffset());
/*  62 */       this.bolt = new LightningEntity((Entity)player, hitVec.x, hitVec.y, hitVec.z, player.rotationYaw, player.rotationPitch, 200.0F, 30.0F);
/*     */       
/*  64 */       this.bolt.setColor(new Color(255, 242, 0, 188));
/*  65 */       this.bolt.setAliveTicks((int)(getMaxChargeTime() * 1.2D));
/*  66 */       this.bolt.setDamage(0.0F);
/*  67 */       this.bolt.setSize(0.1F);
/*  68 */       this.bolt.setBranches(8);
/*  69 */       this.bolt.setSegments(1);
/*  70 */       this.bolt.disableLightningMimic();
/*  71 */       player.world.addEntity((Entity)this.bolt);
/*     */     }
/*     */     else {
/*     */       
/*  75 */       Direction dir = Direction.fromAngle(player.rotationYaw);
/*  76 */       Vec3d hitVec = trace.getHitVec().add(dir.getXOffset(), dir.getYOffset(), dir.getZOffset());
/*  77 */       this.bolt.setLocationAndAngles(hitVec.x, hitVec.y, hitVec.z, player.rotationYaw, player.rotationPitch);
/*     */     } 
/*     */     
/*  80 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1, false, false));
/*  81 */     AbilityHelper.slowEntityFall((LivingEntity)player, 15);
/*     */     
/*  83 */     CHARGE_PARTICLE.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   private boolean endChargeEvent(PlayerEntity player) {
/*     */     BlockPos blockpos;
/*  88 */     if (this.bolt != null) {
/*     */       
/*  90 */       this.bolt.remove();
/*  91 */       this.bolt = null;
/*     */     } 
/*     */     
/*  94 */     BlockRayTraceResult mop = WyHelper.rayTraceBlocks((Entity)player, 200.0D);
/*     */ 
/*     */     
/*  97 */     if (mop == null || mop.getType() == RayTraceResult.Type.MISS) {
/*  98 */       blockpos = WyHelper.rayTraceBlockSafe(player, 64.0F);
/*     */     } else {
/* 100 */       blockpos = WyHelper.getClearPositionForPlayer(player, mop.getPos());
/*     */     } 
/* 102 */     if (blockpos == null) {
/* 103 */       blockpos = WyHelper.rayTraceBlockSafe(player, 64.0F);
/*     */     }
/* 105 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/* 106 */     FLASH_PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/* 107 */     PARTICLES.spawn(player.world, blockpos.getX(), blockpos.getY(), blockpos.getZ(), 0.0D, 0.0D, 0.0D);
/*     */     
/* 109 */     player.teleportKeepLoaded(blockpos.getX(), blockpos.getY(), blockpos.getZ());
/* 110 */     this.hasFallDamage = false;
/*     */     
/* 112 */     player.world.playSound(null, player.getPosition(), ModSounds.PIKA_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/* 113 */     player.world.playSound(null, player.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */     
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetFallDamage(LivingEntity player) {
/* 121 */     this.hasFallDamage = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFallDamage() {
/* 127 */     return this.hasFallDamage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimation getAnimation() {
/* 133 */     return (IAnimation)PointArmAnimation.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive() {
/* 139 */     return isCharging();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\pika\YataNoKagamiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */