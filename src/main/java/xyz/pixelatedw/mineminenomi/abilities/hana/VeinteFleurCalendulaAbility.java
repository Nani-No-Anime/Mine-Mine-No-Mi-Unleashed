package xyz.pixelatedw.mineminenomi.abilities.hana;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.renderers.animations.CrossedArmsAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class VeinteFleurCalendulaAbility extends ContinuousAbility implements IAnimatedAbility {
  public static final VeinteFleurCalendulaAbility INSTANCE = new VeinteFleurCalendulaAbility();

  
  public VeinteFleurCalendulaAbility() {
    super("Veinte Fleur: Calendula", AbilityHelper.getDevilFruitCategory());
    setThreshold(10.0D);
    setDescription("Using newly sprouted arms in the form of a shield the user can partially block attacks.");
    
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int passiveTimer) {
    player.addPotionEffect(new EffectInstance(ModEffects.PHYSICAL_MOVING_GUARD, 2, 3, false, false));
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


