package xyz.pixelatedw.mineminenomi.abilities.goro;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.particles.effects.goro.GenericUseLightningEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

import java.awt.*;

public class SparkStepAbility extends ChargeableAbility implements IFallDamageBlockingAbility {
  public static final Ability INSTANCE = (Ability)new SparkStepAbility();
  
  private static final GenericUseLightningEffect PARTICLES = new GenericUseLightningEffect();
  
  private boolean hasFallDamage = true;
  private static final float MAX_TELEPORT_DISTANCE = 100.0F;
  private LightningEntity bolt = null;

  
  public SparkStepAbility() {
    super("Spark Step", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(5.0D);
    setMaxChargeTime(1.5D);
    setCancelable();
    setDescription("Teleport the user to their desired location");
    
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::endChargeEvent;
  }

  
  private void duringChargingEvent(PlayerEntity player, int timer) {
    BlockRayTraceResult trace = WyHelper.rayTraceBlocks((Entity)player, 1.0D);
    
    if (this.bolt == null) {
      
      Direction dir = Direction.fromAngle(player.rotationYaw);
      Vec3d hitVec = trace.getHitVec().add(dir.getXOffset(), dir.getYOffset(), dir.getZOffset());
      this.bolt = new LightningEntity((Entity)player, hitVec.x, hitVec.y, hitVec.z, player.rotationYaw, player.rotationPitch, 100.0F, 30.0F);
      
      this.bolt.setColor(new Color(0, 100, 255, 188));
      this.bolt.setAliveTicks((int)(getMaxChargeTime() * 1.2D));
      this.bolt.setDamage(0.0F);
      this.bolt.setSize(0.1F);
      this.bolt.setBranches(8);
      this.bolt.setSegments(1);
      this.bolt.disableLightningMimic();
      player.world.addEntity((Entity)this.bolt);
    }
    else {
      
      Direction dir = Direction.fromAngle(player.rotationYaw);
      Vec3d hitVec = trace.getHitVec().add(dir.getXOffset(), dir.getYOffset(), dir.getZOffset());
      this.bolt.setLocationAndAngles(hitVec.x, hitVec.y, hitVec.z, player.rotationYaw, player.rotationPitch);
    } 
    
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1, false, false));
    AbilityHelper.slowEntityFall((LivingEntity)player, 15);
    
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
  }
  
  private boolean endChargeEvent(PlayerEntity player) {
    BlockPos blockpos;
    if (this.bolt != null) {
      
      this.bolt.remove();
      this.bolt = null;
    } 
    
    BlockRayTraceResult mop = WyHelper.rayTraceBlocks((Entity)player, 100.0D);

    
    if (mop == null || mop.getType() == RayTraceResult.Type.MISS) {
      blockpos = WyHelper.rayTraceBlockSafe(player, 64.0F);
    } else {
      blockpos = WyHelper.getClearPositionForPlayer(player, mop.getPos());
    } 
    if (blockpos == null) {
      blockpos = WyHelper.rayTraceBlockSafe(player, 64.0F);
    }
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    PARTICLES.spawn(player.world, blockpos.getX(), blockpos.getY(), blockpos.getZ(), 0.0D, 0.0D, 0.0D);
    
    player.teleportKeepLoaded(blockpos.getX(), blockpos.getY(), blockpos.getZ());
    this.hasFallDamage = false;
    
    player.world.playSound(null, player.getPosition(), ModSounds.LIGHTNING_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
    player.world.playSound(null, player.getPosition(), ModSounds.TELEPORT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    return true;
  }


  
  public void resetFallDamage(LivingEntity player) {
    this.hasFallDamage = true;
  }


  
  public boolean hasFallDamage() {
    return this.hasFallDamage;
  }
}


