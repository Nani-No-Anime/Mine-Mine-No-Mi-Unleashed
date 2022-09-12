package xyz.pixelatedw.mineminenomi.abilities.oto;

import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
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
import xyz.pixelatedw.mineminenomi.renderers.animations.oto.DonAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class DonAbility extends Ability implements IAnimatedAbility {
  public static final Ability INSTANCE = new DonAbility();
  
  private static final float VOLUME = 1.5F;
  
  private static final double DISTANCE = 22.5D;
  
  public DonAbility() {
    super("Don", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(3.0D);
    addInPool(new AbilityPool[] { AbilityPool.OTO_ABILITY });
    setDescription("The user plays the drum, creating a explosion inside all who hear it");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    player.world.playSound(null, player.getPosition(), ModSounds.DON, SoundCategory.PLAYERS, 1.5F, 0.2F + player.getRNG().nextFloat());
    
    List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 22.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
    targets.remove(player);
    
    getAnimation().start();
    
    for (LivingEntity target : targets) {
      
      AbilityDamageSource ds = ModDamageSource.causeAbilityDamage((LivingEntity)player, this);
      ds.setInternalDamage();
      target.attackEntityFrom((DamageSource)ds, 20.0F);
      
      WyHelper.spawnParticles(ParticleTypes.EXPLOSION, (ServerWorld)player.world, target.getPosX(), target.getPosY() + target.getEyeHeight(), target.getPosZ());
    } 
    
    return true;
  }


  
  public TimeAnimation getAnimation() {
    return (TimeAnimation)DonAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return (isOnCooldown() && !isStateForced() && getCooldown() > WyHelper.percentage(50.0D, getMaxCooldown()));
  }
}


