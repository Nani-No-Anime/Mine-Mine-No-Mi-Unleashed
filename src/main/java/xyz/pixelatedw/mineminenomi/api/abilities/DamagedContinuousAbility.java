package xyz.pixelatedw.mineminenomi.api.abilities;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;




@Deprecated
public class DamagedContinuousAbility
  extends ContinuousAbility
{
  protected IOnDamaged onDamagedEvent = (player, attacker, amount) -> false;
  
  public DamagedContinuousAbility(String name, APIConfig.AbilityCategory category) {
    super(name, category);
  }

  
  public boolean damage(LivingEntity entity, DamageSource source, double amount) {
    return this.onDamagedEvent.onDamage(entity, source, amount);
  }
  
  public static interface IOnDamaged extends Serializable {
    boolean onDamage(LivingEntity param1LivingEntity, DamageSource param1DamageSource, double param1Double);
  }
}


