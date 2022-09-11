package xyz.pixelatedw.mineminenomi.abilities.rokushiki;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.renderers.animations.CrossedArmsAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class TekkaiAbility extends ContinuousAbility implements IAnimatedAbility {
  public static final Ability INSTANCE = (Ability)new TekkaiAbility();

  
  public TekkaiAbility() {
    super("Tekkai", AbilityHelper.getRacialCategory());
    setThreshold(10.0D);
    setDescription("Hardens the user's body to protect themselves, but they're unable to move");
    addInPool(new AbilityPool[] { AbilityPool.TEKKAI_LIKE });
    
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int passiveTimer) {
    player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, 1, false, false));
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    int cooldown = (int)Math.round(this.continueTime / 20.0D) + 3;
    setMaxCooldown(cooldown);
    return true;
  }


  
  public IAnimation getAnimation() {
    return (IAnimation)CrossedArmsAnimation.INSTANCE;
  }


  
  public boolean isAnimationActive() {
    return isContinuous();
  }
}


