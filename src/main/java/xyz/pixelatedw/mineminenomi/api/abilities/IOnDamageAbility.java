package xyz.pixelatedw.mineminenomi.api.abilities;

import java.util.function.Predicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;

public interface IOnDamageAbility {
  public static final Predicate<Ability> IS_ACTIVE = (ability -> !(ability instanceof IOnDamageAbility) ? false : ((ability instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility) ? ability.isContinuous() : ((ability instanceof PassiveAbility) ? (!((PassiveAbility)ability).isPaused()) : false)));
  
  
  boolean onDamage(LivingEntity paramLivingEntity, DamageSource paramDamageSource, double paramDouble);
}


