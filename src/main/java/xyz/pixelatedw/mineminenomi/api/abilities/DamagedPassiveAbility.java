package xyz.pixelatedw.mineminenomi.api.abilities;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;




@Deprecated
public class DamagedPassiveAbility
  extends PassiveAbility
{
  protected IOnDamaged onDamagedEvent = (player, attacker) -> false;
  
  public DamagedPassiveAbility(String name, APIConfig.AbilityCategory category) {
    super(name, category);
  }

  
  public boolean damage(LivingEntity entity, DamageSource source) {
    if (isPaused()) {
      return true;
    }
    return this.onDamagedEvent.onDamage(entity, source);
  }
  
  public static interface IOnDamaged extends Serializable {
    boolean onDamage(LivingEntity param1LivingEntity, DamageSource param1DamageSource);
  }
}


