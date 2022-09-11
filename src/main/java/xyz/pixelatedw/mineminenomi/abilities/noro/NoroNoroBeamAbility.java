package xyz.pixelatedw.mineminenomi.abilities.noro;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.noro.NoroNoroBeamProjectile;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.renderers.animations.PointArmAnimation;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class NoroNoroBeamAbility extends Ability implements IAnimatedAbility {
  public static final Ability INSTANCE = new NoroNoroBeamAbility();

  
  public NoroNoroBeamAbility() {
    super("Noro Noro Beam", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(3.0D);
    setDescription("Shoots a beam of photons at the opponent, completely slowing them down (multiple hits stack the Slowness effect)");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    NoroNoroBeamProjectile proj = new NoroNoroBeamProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 4.0F, 1.0F);
    player.world.playSound(null, player.getPosition(), ModSounds.NORO_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    
    return true;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)PointArmAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return (isOnCooldown() && getCooldown() > WyHelper.percentage(80.0D, getMaxCooldown()));
  }
}


