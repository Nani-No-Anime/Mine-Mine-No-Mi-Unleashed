package xyz.pixelatedw.mineminenomi.api.abilities;

import java.io.Serializable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;



public class HurtPassiveAbility
  extends PassiveAbility
{
  protected IOnHurt onHurtEvent = (player, attacker) -> true;
  private float amount;
  
  public HurtPassiveAbility(String name, APIConfig.AbilityCategory category) {
    super(name, category);
  }

  
  public boolean hurt(LivingEntity entity, Entity source, float amount) {
    this.amount = amount;
    
    if (isPaused()) {
      return true;
    }
    return this.onHurtEvent.onHurt(entity, source);
  }

  
  public float getAmount() {
    return this.amount;
  }
  
  public static interface IOnHurt extends Serializable {
    boolean onHurt(LivingEntity param1LivingEntity, Entity param1Entity);
  }
}


