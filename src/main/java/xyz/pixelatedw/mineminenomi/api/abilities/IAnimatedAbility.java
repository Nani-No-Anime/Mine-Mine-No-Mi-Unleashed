package xyz.pixelatedw.mineminenomi.api.abilities;

import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.function.Predicate;

public interface IAnimatedAbility
{
  
  public static final Predicate<Ability> IS_ACTIVE = (ability -> !(ability instanceof IAnimatedAbility) ? false : ((IAnimatedAbility)ability).isAnimationActive());
  
  boolean isAnimationActive();
  
  IAnimation getAnimation();
}


