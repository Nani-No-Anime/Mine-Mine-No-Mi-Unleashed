package xyz.pixelatedw.mineminenomi.abilities.oto;

import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.renderers.animations.oto.BonAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class BonAbility extends Ability implements IAnimatedAbility {
  public static final Ability INSTANCE = new BonAbility();
  
  private static final float VOLUME = 1.5F;
  
  private static final double DISTANCE = 22.5D;
  
  public BonAbility() {
    super("Bon", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(3.0D);
    addInPool(new AbilityPool[] { AbilityPool.OTO_ABILITY });
    setDescription("The user plucks a string created from their arm, creating a sound wave that internally damages all who hear it");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    player.world.playSound(null, player.getPosition(), ModSounds.BON, SoundCategory.PLAYERS, 1.5F, 0.2F + player.getRNG().nextFloat());
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 22.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    
    getAnimation().start();
    
    for (LivingEntity target : targets) {
      
      AbilityDamageSource ds = ModDamageSource.causeAbilityDamage((LivingEntity)player, this);
      ds.setInternalDamage();
      ds.markDamageAsSlash();
      target.attackEntityFrom((DamageSource)ds, 15.0F);
      
      Vec3d dist = target.getPositionVec().subtract(player.getPositionVec()).add(0.0D, -1.0D, 0.0D).normalize();
      double power = 4.5D;
      double xSpeed = -dist.x * power;
      double zSpeed = -dist.z * power;
      target.setMotion(-xSpeed, 0.10000000149011612D, -zSpeed);
      target.velocityChanged = true;
      
      for (int i = 0; i < 5; i++) {
        
        double offsetX = target.getRNG().nextDouble() / 2.0D;
        double offsetZ = target.getRNG().nextDouble() / 2.0D;
        WyHelper.spawnParticles(ParticleTypes.SWEEP_ATTACK, (ServerWorld)player.world, target.getPosX() + offsetX, target.getPosY() + target.getEyeHeight() + offsetX, target.getPosZ() + offsetZ);
      } 
      WyHelper.spawnParticles(ParticleTypes.EXPLOSION, (ServerWorld)player.world, target.getPosX(), target.getPosY() + target.getEyeHeight(), target.getPosZ());
    } 
    
    return true;
  }


  
  public TimeAnimation getAnimation() {
    return (TimeAnimation)BonAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return (isOnCooldown() && !isStateForced() && getCooldown() > WyHelper.percentage(70.0D, getMaxCooldown()));
  }
}


