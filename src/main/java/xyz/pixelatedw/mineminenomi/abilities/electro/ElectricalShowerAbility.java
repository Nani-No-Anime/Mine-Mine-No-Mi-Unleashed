/*     */ package xyz.pixelatedw.mineminenomi.abilities.electro;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceContext;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningBallEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.electro.ElectroChargingParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ 
/*     */ public class ElectricalShowerAbility extends ChargeableAbility implements IFallDamageBlockingAbility {
/*  24 */   public static final ElectricalShowerAbility INSTANCE = new ElectricalShowerAbility();
/*     */   
/*  26 */   private int boltsLeft = 9;
/*  27 */   private static final ElectroChargingParticleEffect PARTICLES = new ElectroChargingParticleEffect();
/*  28 */   private LightningBallEntity ballEntity = null;
/*     */   
/*     */   private static final int COOLDOWN = 12;
/*     */   private static final float CHARGE_TIME = 3.0F;
/*     */   boolean hasFallDamage = true;
/*     */   
/*     */   public ElectricalShowerAbility() {
/*  35 */     super("Electrical Shower", AbilityHelper.getRacialCategory());
/*  36 */     setMaxCooldown(12.0D);
/*  37 */     setMaxChargeTime(3.0D);
/*  38 */     addInPool(new AbilityPool[] { AbilityPool.MINK_ELECTRO });
/*  39 */     setDescription("Launches the user into the air and showers down lightning bolts underneath");
/*     */     
/*  41 */     this.onStartChargingEvent = this::onStartChargingEvent;
/*  42 */     this.duringChargingEvent = this::duringChargingEvent;
/*  43 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartChargingEvent(PlayerEntity player) {
/*  48 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/*  49 */       return false;
/*     */     }
/*  51 */     this.boltsLeft = (int)WyHelper.randomWithRange(8, 10);
/*     */     
/*  53 */     this.hasFallDamage = false;
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int i) {
/*  59 */     if (i % 5 == 0) {
/*  60 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     }
/*  62 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 4, 1, false, false));
/*     */     
/*  64 */     float percentage = 1.0F - getChargeTime() / getMaxChargeTime();
/*     */     
/*  66 */     if (this.ballEntity == null) {
/*     */       
/*  68 */       LightningBallEntity ball = new LightningBallEntity((Entity)player, player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
/*     */       
/*  70 */       player.world.addEntity((Entity)ball);
/*  71 */       this.ballEntity = ball;
/*     */     } else {
/*     */       
/*  74 */       float distance = percentage * 2.0F;
/*  75 */       Vec3d lookVec = player.getLookVec();
/*  76 */       Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + player.getEyeHeight() * 0.85D + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
/*  77 */       this.ballEntity.setSize(percentage * 0.3F);
/*  78 */       this.ballEntity.setLightningLength(3.0F);
/*  79 */       this.ballEntity.setPositionAndRotation(pos.getX(), pos.getY(), pos.getZ(), player.rotationYaw, player.rotationPitch);
/*     */     } 
/*     */ 
/*     */     
/*  83 */     if (percentage > 0.65D) {
/*     */       
/*  85 */       Vec3d startVec = player.getPositionVec();
/*     */ 
/*     */       
/*  88 */       boolean blockUnder = player.world.rayTraceBlocks(new RayTraceContext(startVec, startVec.add(0.0D, -15.0D, 0.0D), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, (Entity)player)).getType().equals(RayTraceResult.Type.BLOCK);
/*     */       
/*  90 */       if (blockUnder) {
/*     */         
/*  92 */         player.setMotion((player.getMotion()).x, 1.0D, (player.getMotion()).z);
/*  93 */         player.velocityChanged = true;
/*     */       } else {
/*  95 */         AbilityHelper.slowEntityFall((LivingEntity)player);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean onEndChargingEvent(PlayerEntity player) {
/* 101 */     if (this.ballEntity != null) {
/*     */       
/* 103 */       this.ballEntity.remove();
/* 104 */       this.ballEntity = null;
/*     */     } 
/* 106 */     Vec3d startVec = player.getPositionVec();
/*     */     
/* 108 */     boolean canShoot = !player.world.rayTraceBlocks(new RayTraceContext(startVec, startVec.add(0.0D, -10.0D, 0.0D), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, (Entity)player)).getType().equals(RayTraceResult.Type.BLOCK);
/* 109 */     if (!canShoot) {
/* 110 */       return true;
/*     */     }
/* 112 */     EleclawAbility eleclawAbility = (EleclawAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)EleclawAbility.INSTANCE);
/*     */     
/* 114 */     SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SulongAbility.INSTANCE);
/* 115 */     boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
/*     */     
/* 117 */     if (eleclawAbility != null && eleclawAbility.isContinuous()) {
/*     */       
/* 119 */       BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)player, 50.0D);
/* 120 */       double beamDistance = Math.sqrt(player.getDistanceSq((blockRayTraceResult.getHitVec()).x, (blockRayTraceResult.getHitVec()).y, (blockRayTraceResult.getHitVec()).z));
/*     */       
/* 122 */       ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/*     */       
/* 124 */       for (int z = 0; z < this.boltsLeft; z++) {
/*     */         
/* 126 */         LightningEntity bolt = new LightningEntity((Entity)player, player.getPosX() + WyHelper.randomWithRange(-3, 3), player.getPosY(), player.getPosZ() + WyHelper.randomWithRange(-3, 3), player.rotationYaw, player.rotationPitch, ((int)beamDistance + 2), 8.0F);
/*     */         
/* 128 */         bolt.setAliveTicks(5);
/* 129 */         bolt.setDamage((10 + (sulongEnabled ? 10 : 0)));
/* 130 */         bolt.setExplosion(2, false, 0.0F);
/* 131 */         bolt.setSize(0.025F);
/* 132 */         bolt.disableLightningMimic();
/* 133 */         bolt.setBoxSizeDivision(0.025D);
/* 134 */         bolt.disableExplosionKnockback();
/* 135 */         bolt.setSegments((int)(beamDistance + WyHelper.randomWithRange(-4, 8)));
/* 136 */         player.world.addEntity((Entity)bolt);
/*     */       } 
/*     */       
/* 139 */       eleclawAbility.reduceUsage(player, 2);
/*     */     } 
/*     */     
/* 142 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetFallDamage(LivingEntity player) {
/* 147 */     this.hasFallDamage = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFallDamage() {
/* 153 */     return this.hasFallDamage;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\electro\ElectricalShowerAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */