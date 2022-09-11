package xyz.pixelatedw.mineminenomi.abilities.nikyu;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.PadHoProjectile;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.renderers.animations.PointArmAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class PadHoAbility extends Ability implements IAnimatedAbility {
  public static final Ability INSTANCE = new PadHoAbility();

  
  public PadHoAbility() {
    super("Pad Ho", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(1.5D);
    setDescription("Launches a paw-shaped shockwave at the opponent");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    player.world.playMovingSound(null, (Entity)player, ModSounds.PAD_HO_SFX, SoundCategory.PLAYERS, 2.0F, 2.0F);
    PadHoProjectile proj = new PadHoProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)proj);
    proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.5F, 1.0F);
    
    return true;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)PointArmAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return (isOnCooldown() && getCooldown() > getMaxCooldown() - 5.0D);
  }
}


