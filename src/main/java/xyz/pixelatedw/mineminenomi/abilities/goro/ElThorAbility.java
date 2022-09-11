package xyz.pixelatedw.mineminenomi.abilities.goro;

import java.awt.Color;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
import xyz.pixelatedw.mineminenomi.entities.zoan.VoltAmaruZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.particles.effects.goro.ElThorParticleEffect;
import xyz.pixelatedw.mineminenomi.renderers.animations.RaiseArmAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class ElThorAbility extends ChargeableAbility implements IAnimatedAbility {
  public static final ElThorAbility INSTANCE = new ElThorAbility();
  
  private static final ElThorParticleEffect PARTICLES = new ElThorParticleEffect();
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setRenderType(AbilityOverlay.RenderType.ENERGY).setColor(new Color(120, 220, 255));

  
  private LightningEntity bolt = null;
  boolean playedSound = false;
  private List<Vec3d> positions = new ArrayList<>();
  
  private Mode mode = Mode.THOR;
  float multi; private boolean onStartChargingEvent(PlayerEntity player) { this.positions.clear(); this.bolt = null; this.playedSound = false;
    PARTICLES.aiming = true;
    PARTICLES.ownerID = player.getEntityId();
    return true; } public ElThorAbility() { super("El Thor", AbilityHelper.getDevilFruitCategory());


































    
    this.multi = 1.0F; setDescription("Focuses a large cluster of electricity above the target, then sends a powerful lightning bolt crashing down from the sky"); setMaxCooldown(12.0D); setMaxChargeTime(4.0D); setCancelable();
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
    this.duringCooldownEvent = this::duringCooldownEvent; } private boolean onEndChargingEvent(PlayerEntity player) { if (getChargeTime() > getMaxChargeTime() - ((this.mode == Mode.MAMARAGAN) ? 50 : 10)) {
      return false;
    }
    PARTICLES.aiming = false;
    RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player);
    boolean isAmaru = VoltAmaruZoanInfo.INSTANCE.isActive((LivingEntity)player);
    
    float time = getChargeTime() / getMaxChargeTime();
    this.multi = 0.4F + (1.0F - time) * 0.6F;
    
    double i = (mop.getHitVec()).x;
    double j = (mop.getHitVec()).y;
    double k = (mop.getHitVec()).z;
    
    int f = 0;
    int max = 1;
    
    if (this.mode == Mode.MAMARAGAN) {
      
      max = (int)(max + 50.0F * (1.0F - time));
      this.multi = Math.max(this.multi / 2.0F, 0.4F);
    } 
    
    double x = 0.0D;
    double z = 0.0D;
    
    while (f < max) {
      
      if (f > 0) {
        
        x = WyHelper.randomWithRange(-90, 90);
        z = WyHelper.randomWithRange(-90, 90);
      } 
      
      PARTICLES.spawn(player.world, i + x, j, k + z, 0.0D, 0.0D, 0.0D);
      this.positions.add(new Vec3d(i + x, j, k + z));
      
      LightningEntity bolt = new LightningEntity((Entity)player, i + x, j + 72.0D, k + z, 0.0F, 90.0F, 72.0F + 16.0F * this.multi, 24.0F);
      setBoltPropieties(bolt, isAmaru ? 1.5F : 1.4F, 0.0F, 90, 40, false, Color.WHITE);
      player.world.addEntity((Entity)bolt);
      
      long seed = bolt.seed;
      this.bolt = new LightningEntity((Entity)player, i + x, j + 72.0D, k + z, 0.0F, 90.0F, 72.0F + 16.0F * this.multi, 24.0F);
      this.bolt.seed = seed;
      setBoltPropieties(this.bolt, isAmaru ? 1.6F : 1.45F, isAmaru ? 70.0F : 50.0F, 100, 9999, true, (Color)null);
      player.world.addEntity((Entity)this.bolt);
      
      player.world.playSound(null, player.getPosition(), ModSounds.EL_THOR_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
      
      f++;
    } 
    
    return true; } private void duringChargingEvent(PlayerEntity player, int chargeTimer) { RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player); double i = (mop.getHitVec()).x; double j = (mop.getHitVec()).y;
    double k = (mop.getHitVec()).z;
    if (chargeTimer % 2 == 0)
      PARTICLES.spawn(player.world, i, j, k, 0.0D, 0.0D, 0.0D); 
    AbilityHelper.slowEntityFall((LivingEntity)player); }
  public void setBoltPropieties(LightningEntity bolt, float size, float damage, int timeAlive, int resetTime, boolean explodes, @Nullable Color color) { bolt.setAngle(160);
    bolt.setBranches(1);
    bolt.setSegments(1);
    bolt.setSize(size * this.multi);
    bolt.setBoxSizeDivision(0.22499999403953552D);
    bolt.disableLightningMovement();
    bolt.setExplosion(explodes ? (int)(10.0F * this.multi) : 0, true, 0.25F);
    if (color != null) {
      bolt.setColor(color);
    }
    bolt.setAliveTicks(timeAlive);
    bolt.setDamage(damage * this.multi);
    
    bolt.setTargetTimeToReset(resetTime); }


  
  private void duringCooldownEvent(PlayerEntity player, int i) {}

  
  public enum Mode
  {
    THOR, MAMARAGAN;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)RaiseArmAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return (isCharging() && getChargeTime() > 10);
  }

  
  public void enableVoltMode() {
    setDisplayName("Mamaragan");
    this.mode = Mode.MAMARAGAN;
    setMaxCooldown(20.0D);
  }

  
  public void disableVoltMode() {
    setDisplayName("");
    this.mode = Mode.THOR;
    setMaxCooldown(12.0D);
  }
}


