package xyz.pixelatedw.mineminenomi.api.abilities;

import java.util.function.Predicate;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public interface IAnimatedAbility
{
  
  public static final Predicate<Ability> IS_ACTIVE = (ability -> !(ability instanceof IAnimatedAbility) ? false : ((IAnimatedAbility)ability).isAnimationActive());
  
  boolean isAnimationActive();
  
  IAnimation getAnimation();
}


