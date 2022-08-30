/*     */ package xyz.pixelatedw.mineminenomi.abilities.goro;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.VoltAmaruZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.goro.ElThorParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.animations.RaiseArmAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ 
/*     */ public class ElThorAbility extends ChargeableAbility implements IAnimatedAbility {
/*  27 */   public static final ElThorAbility INSTANCE = new ElThorAbility();
/*     */   
/*  29 */   private static final ElThorParticleEffect PARTICLES = new ElThorParticleEffect();
/*  30 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setRenderType(AbilityOverlay.RenderType.ENERGY).setColor(new Color(120, 220, 255));
/*     */ 
/*     */   
/*  33 */   private LightningEntity bolt = null;
/*     */   boolean playedSound = false;
/*  35 */   private List<Vec3d> positions = new ArrayList<>();
/*     */   
/*  37 */   private Mode mode = Mode.THOR;
/*     */   float multi; private boolean onStartChargingEvent(PlayerEntity player) { this.positions.clear(); this.bolt = null; this.playedSound = false;
/*     */     PARTICLES.aiming = true;
/*     */     PARTICLES.ownerID = player.getEntityId();
/*  41 */     return true; } public ElThorAbility() { super("El Thor", AbilityHelper.getDevilFruitCategory());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     this.multi = 1.0F; setDescription("Focuses a large cluster of electricity above the target, then sends a powerful lightning bolt crashing down from the sky"); setMaxCooldown(12.0D); setMaxChargeTime(4.0D); setCancelable();
/*     */     this.onStartChargingEvent = this::onStartChargingEvent;
/*     */     this.duringChargingEvent = this::duringChargingEvent;
/*     */     this.onEndChargingEvent = this::onEndChargingEvent;
/*  81 */     this.duringCooldownEvent = this::duringCooldownEvent; } private boolean onEndChargingEvent(PlayerEntity player) { if (getChargeTime() > getMaxChargeTime() - ((this.mode == Mode.MAMARAGAN) ? 50 : 10)) {
/*  82 */       return false;
/*     */     }
/*  84 */     PARTICLES.aiming = false;
/*  85 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player);
/*  86 */     boolean isAmaru = VoltAmaruZoanInfo.INSTANCE.isActive((LivingEntity)player);
/*     */     
/*  88 */     float time = getChargeTime() / getMaxChargeTime();
/*  89 */     this.multi = 0.4F + (1.0F - time) * 0.6F;
/*     */     
/*  91 */     double i = (mop.getHitVec()).x;
/*  92 */     double j = (mop.getHitVec()).y;
/*  93 */     double k = (mop.getHitVec()).z;
/*     */     
/*  95 */     int f = 0;
/*  96 */     int max = 1;
/*     */     
/*  98 */     if (this.mode == Mode.MAMARAGAN) {
/*     */       
/* 100 */       max = (int)(max + 50.0F * (1.0F - time));
/* 101 */       this.multi = Math.max(this.multi / 2.0F, 0.4F);
/*     */     } 
/*     */     
/* 104 */     double x = 0.0D;
/* 105 */     double z = 0.0D;
/*     */     
/* 107 */     while (f < max) {
/*     */       
/* 109 */       if (f > 0) {
/*     */         
/* 111 */         x = WyHelper.randomWithRange(-90, 90);
/* 112 */         z = WyHelper.randomWithRange(-90, 90);
/*     */       } 
/*     */       
/* 115 */       PARTICLES.spawn(player.world, i + x, j, k + z, 0.0D, 0.0D, 0.0D);
/* 116 */       this.positions.add(new Vec3d(i + x, j, k + z));
/*     */       
/* 118 */       LightningEntity bolt = new LightningEntity((Entity)player, i + x, j + 72.0D, k + z, 0.0F, 90.0F, 72.0F + 16.0F * this.multi, 24.0F);
/* 119 */       setBoltPropieties(bolt, isAmaru ? 1.5F : 1.4F, 0.0F, 90, 40, false, Color.WHITE);
/* 120 */       player.world.addEntity((Entity)bolt);
/*     */       
/* 122 */       long seed = bolt.seed;
/* 123 */       this.bolt = new LightningEntity((Entity)player, i + x, j + 72.0D, k + z, 0.0F, 90.0F, 72.0F + 16.0F * this.multi, 24.0F);
/* 124 */       this.bolt.seed = seed;
/* 125 */       setBoltPropieties(this.bolt, isAmaru ? 1.6F : 1.45F, isAmaru ? 70.0F : 50.0F, 100, 9999, true, (Color)null);
/* 126 */       player.world.addEntity((Entity)this.bolt);
/*     */       
/* 128 */       player.world.playSound(null, player.getPosition(), ModSounds.EL_THOR_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */       
/* 130 */       f++;
/*     */     } 
/*     */     
/* 133 */     return true; } private void duringChargingEvent(PlayerEntity player, int chargeTimer) { RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player); double i = (mop.getHitVec()).x; double j = (mop.getHitVec()).y;
/*     */     double k = (mop.getHitVec()).z;
/*     */     if (chargeTimer % 2 == 0)
/*     */       PARTICLES.spawn(player.world, i, j, k, 0.0D, 0.0D, 0.0D); 
/*     */     AbilityHelper.slowEntityFall((LivingEntity)player); }
/* 138 */   public void setBoltPropieties(LightningEntity bolt, float size, float damage, int timeAlive, int resetTime, boolean explodes, @Nullable Color color) { bolt.setAngle(160);
/* 139 */     bolt.setBranches(1);
/* 140 */     bolt.setSegments(1);
/* 141 */     bolt.setSize(size * this.multi);
/* 142 */     bolt.setBoxSizeDivision(0.22499999403953552D);
/* 143 */     bolt.disableLightningMovement();
/* 144 */     bolt.setExplosion(explodes ? (int)(10.0F * this.multi) : 0, true, 0.25F);
/* 145 */     if (color != null) {
/* 146 */       bolt.setColor(color);
/*     */     }
/* 148 */     bolt.setAliveTicks(timeAlive);
/* 149 */     bolt.setDamage(damage * this.multi);
/*     */     
/* 151 */     bolt.setTargetTimeToReset(resetTime); }
/*     */ 
/*     */ 
/*     */   
/*     */   private void duringCooldownEvent(PlayerEntity player, int i) {}
/*     */ 
/*     */   
/*     */   public enum Mode
/*     */   {
/* 160 */     THOR, MAMARAGAN;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimation getAnimation() {
/* 166 */     return (IAnimation)RaiseArmAnimation.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive() {
/* 172 */     return (isCharging() && getChargeTime() > 10);
/*     */   }
/*     */ 
/*     */   
/*     */   public void enableVoltMode() {
/* 177 */     setDisplayName("Mamaragan");
/* 178 */     this.mode = Mode.MAMARAGAN;
/* 179 */     setMaxCooldown(20.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void disableVoltMode() {
/* 184 */     setDisplayName("");
/* 185 */     this.mode = Mode.THOR;
/* 186 */     setMaxCooldown(12.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\ElThorAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */