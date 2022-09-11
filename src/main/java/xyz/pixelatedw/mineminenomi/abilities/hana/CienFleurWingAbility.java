package xyz.pixelatedw.mineminenomi.abilities.hana;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.renderers.animations.CrossedArmsAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

public class CienFleurWingAbility extends ContinuousAbility implements IFallDamageBlockingAbility, IAnimatedAbility, IParallelContinuousAbility {
  public static final CienFleurWingAbility INSTANCE = new CienFleurWingAbility();
  
  private boolean hasFallDamage = true;

  
  public CienFleurWingAbility() {
    super("Cien Fleur: Wing", AbilityHelper.getDevilFruitCategory());
    setDescription("While active and the user is in air they will float and not receive any fall damage.");
    setThreshold(10.0D);
    needsClientSide();
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (player.onGround) {
      return false;
    }
    MilFleurAbility.spawnBlossomEffect((LivingEntity)player);
    
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int time) {
    if (player.onGround) {
      endContinuity(player);
    }
    player.addVelocity(0.0D, -((player.getMotion()).y / 6.0D), 0.0D);
    this.hasFallDamage = false;
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)Math.round(this.continueTime / 20.0D) + 5;
    setMaxCooldown(cooldown);
    return true;
  }


  
  public void resetFallDamage(LivingEntity player) {
    this.hasFallDamage = true;
  }


  
  public boolean hasFallDamage() {
    return this.hasFallDamage;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)CrossedArmsAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isContinuous();
  }
}


