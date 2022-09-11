package xyz.pixelatedw.mineminenomi.abilities.gura;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.gura.GekishinProjectile;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.particles.effects.gura.AirCrackParticleEffect;
import xyz.pixelatedw.mineminenomi.renderers.animations.gura.KaishinAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

public class GekishinAbility extends ChargeableAbility implements IAnimatedAbility {
  public static final Ability INSTANCE = (Ability)new GekishinAbility();
  
  private static final AirCrackParticleEffect PARTICLES = new AirCrackParticleEffect();
  private MODE activeMode = MODE.GEKISHIN;

  
  public GekishinAbility() {
    super("Gekishin", AbilityHelper.getDevilFruitCategory());
    setDescription("The user 'cracks' the air, launching vibrations which send blocks flying \n\n§2SHIFT-USE§r: Switches between Gekishin and Kaishin mode");
    setMaxCooldown(12.0D);
    setMaxChargeTime(1.0D);
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    if (player.isSneaking()) {
      
      if (this.activeMode == MODE.GEKISHIN) {
        
        this.activeMode = MODE.KAISHIN;
        setMaxCooldown(24.0D);
        setMaxChargeTime(2.0D);
        setCustomTexture("kaishin");
      }
      else {
        
        this.activeMode = MODE.GEKISHIN;
        setMaxCooldown(12.0D);
        setMaxChargeTime(1.0D);
        setCustomTexture("gekishin");
      } 
      
      player.sendMessage((ITextComponent)new TranslationTextComponent("Ability mode set to: " + this.activeMode, new Object[0]));
      IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
      WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
      WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
      return false;
    } 
    getAnimation().start();
    return true;
  }


  
  private void duringChargingEvent(PlayerEntity player, int i) {}

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    float time = getChargeTime() / getMaxChargeTime();
    float multiplier = 1.0F - time;
    
    if (multiplier < 0.2D) {
      return false;
    }
    if (this.activeMode == MODE.GEKISHIN) {
      
      EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 1.5D);
      PARTICLES.spawn(player.world, trace.getHitVec().getX(), player.getPosY() + 0.5D, trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D);
      
      player.world.playSound(null, player.getPosition(), ModSounds.GURA_SFX, SoundCategory.PLAYERS, 2.0F, 1.0F);
      
      GekishinProjectile proj = new GekishinProjectile(player.world, (LivingEntity)player);
      proj.setMaxLife((int)(proj.getMaxLife() * multiplier));
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
    }
    else {
      
      multiplier = (float)(multiplier * 0.5D);
      
      Vec3d v1 = player.getPositionVec().add(0.0D, player.getEyeHeight(), 0.0D).add(player.getLookVec().scale(2.5D).rotateYaw(180.0F));
      Vec3d v2 = player.getPositionVec().add(0.0D, player.getEyeHeight(), 0.0D).add(player.getLookVec().scale(2.5D).rotateYaw(-180.0F));
      PARTICLES.spawn(player.world, v1.getX(), player.getPosY() + 0.5D, v1.getZ(), 0.0D, 0.0D, 0.0D);
      PARTICLES.spawn(player.world, v2.getX(), player.getPosY() + 0.5D, v2.getZ(), 0.0D, 0.0D, 0.0D);
      
      List<Entity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, (16.0F * multiplier), FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { Entity.class });
      list.remove(player);
      
      for (Entity target : list) {
        
        if (target instanceof AbilityProjectileEntity && ((AbilityProjectileEntity)target).getDamage() < 40.0F * multiplier && ((AbilityProjectileEntity)target).isPhysical()) {
          target.remove();
        }
        if (target instanceof LivingEntity) {
          
          Vec3d speed = target.getLook(1.0F).mul(3.0D, 2.0D, 3.0D);
          target.setMotion(speed.x, speed.y, speed.z);
          target.velocityChanged = true;
          target.fallDistance = 0.0F;
        } 
      } 
      
      player.world.playSound(null, player.getPosition(), ModSounds.GURA_SFX, SoundCategory.PLAYERS, 2.0F, 1.0F);
      
      GekishinProjectile p1 = new GekishinProjectile(player.world, (LivingEntity)player);
      p1.setMaxLife((int)(p1.getMaxLife() * multiplier));
      player.world.addEntity((Entity)p1);
      p1.shoot((Entity)player, player.rotationPitch, player.rotationYaw + 90.0F, 0.0F, 1.75F, 1.0F);
      GekishinProjectile p2 = new GekishinProjectile(player.world, (LivingEntity)player);
      p2.setMaxLife((int)(p2.getMaxLife() * multiplier));
      player.world.addEntity((Entity)p2);
      p2.shoot((Entity)player, player.rotationPitch, player.rotationYaw - 90.0F, 0.0F, 1.75F, 1.0F);
    } 
    
    setMaxCooldown((this.activeMode == MODE.GEKISHIN) ? (6.0F + 6.0F * multiplier) : (12.0F + 12.0F * multiplier));
    return true;
  }
  
  public enum MODE
  {
    GEKISHIN, KAISHIN;
  }


  
  public TimeAnimation getAnimation() {
    return (TimeAnimation)KaishinAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return (isCharging() && getChargeTime() < WyHelper.percentage(75.0D, getMaxChargeTime()));
  }
}


