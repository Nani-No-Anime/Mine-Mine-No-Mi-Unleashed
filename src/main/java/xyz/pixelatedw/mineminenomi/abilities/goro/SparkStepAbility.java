/*     */ package xyz.pixelatedw.mineminenomi.abilities.goro;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.goro.GenericUseLightningEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ 
/*     */ public class SparkStepAbility extends ChargeableAbility implements IFallDamageBlockingAbility {
/*  26 */   public static final Ability INSTANCE = (Ability)new SparkStepAbility();
/*     */   
/*  28 */   private static final GenericUseLightningEffect PARTICLES = new GenericUseLightningEffect();
/*     */   
/*     */   private boolean hasFallDamage = true;
/*     */   private static final float MAX_TELEPORT_DISTANCE = 100.0F;
/*  32 */   private LightningEntity bolt = null;
/*     */ 
/*     */   
/*     */   public SparkStepAbility() {
/*  36 */     super("Spark Step", AbilityHelper.getDevilFruitCategory());
/*  37 */     setMaxCooldown(5.0D);
/*  38 */     setMaxChargeTime(1.5D);
/*  39 */     setCancelable();
/*  40 */     setDescription("Teleport the user to their desired location");
/*     */     
/*  42 */     this.duringChargingEvent = this::duringChargingEvent;
/*  43 */     this.onEndChargingEvent = this::endChargeEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int timer) {
/*  48 */     BlockRayTraceResult trace = WyHelper.rayTraceBlocks((Entity)player, 1.0D);
/*     */     
/*  50 */     if (this.bolt == null) {
/*     */       
/*  52 */       Direction dir = Direction.fromAngle(player.rotationYaw);
/*  53 */       Vec3d hitVec = trace.getHitVec().add(dir.getXOffset(), dir.getYOffset(), dir.getZOffset());
/*  54 */       this.bolt = new LightningEntity((Entity)player, hitVec.x, hitVec.y, hitVec.z, player.rotationYaw, player.rotationPitch, 100.0F, 30.0F);
/*     */       
/*  56 */       this.bolt.setColor(new Color(0, 100, 255, 188));
/*  57 */       this.bolt.setAliveTicks((int)(getMaxChargeTime() * 1.2D));
/*  58 */       this.bolt.setDamage(0.0F);
/*  59 */       this.bolt.setSize(0.1F);
/*  60 */       this.bolt.setBranches(8);
/*  61 */       this.bolt.setSegments(1);
/*  62 */       this.bolt.disableLightningMimic();
/*  63 */       player.world.addEntity((Entity)this.bolt);
/*     */     }
/*     */     else {
/*     */       
/*  67 */       Direction dir = Direction.fromAngle(player.rotationYaw);
/*  68 */       Vec3d hitVec = trace.getHitVec().add(dir.getXOffset(), dir.getYOffset(), dir.getZOffset());
/*  69 */       this.bolt.setLocationAndAngles(hitVec.x, hitVec.y, hitVec.z, player.rotationYaw, player.rotationPitch);
/*     */     } 
/*     */     
/*  72 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1, false, false));
/*  73 */     AbilityHelper.slowEntityFall((LivingEntity)player, 15);
/*     */     
/*  75 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   private boolean endChargeEvent(PlayerEntity player) {
/*     */     BlockPos blockpos;
/*  80 */     if (this.bolt != null) {
/*     */       
/*  82 */       this.bolt.remove();
/*  83 */       this.bolt = null;
/*     */     } 
/*     */     
/*  86 */     BlockRayTraceResult mop = WyHelper.rayTraceBlocks((Entity)player, 100.0D);
/*     */ 
/*     */     
/*  89 */     if (mop == null || mop.getType() == RayTraceResult.Type.MISS) {
/*  90 */       blockpos = WyHelper.rayTraceBlockSafe(player, 64.0F);
/*     */     } else {
/*  92 */       blockpos = WyHelper.getClearPositionForPlayer(player, mop.getPos());
/*     */     } 
/*  94 */     if (blockpos == null) {
/*  95 */       blockpos = WyHelper.rayTraceBlockSafe(player, 64.0F);
/*     */     }
/*  97 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*  98 */     PARTICLES.spawn(player.world, blockpos.getX(), blockpos.getY(), blockpos.getZ(), 0.0D, 0.0D, 0.0D);
/*     */     
/* 100 */     player.teleportKeepLoaded(blockpos.getX(), blockpos.getY(), blockpos.getZ());
/* 101 */     this.hasFallDamage = false;
/*     */     
/* 103 */     player.world.playSound(null, player.getPosition(), ModSounds.LIGHTNING_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
/* 104 */     player.world.playSound(null, player.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/* 105 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetFallDamage(LivingEntity player) {
/* 111 */     this.hasFallDamage = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFallDamage() {
/* 117 */     return this.hasFallDamage;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\SparkStepAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */