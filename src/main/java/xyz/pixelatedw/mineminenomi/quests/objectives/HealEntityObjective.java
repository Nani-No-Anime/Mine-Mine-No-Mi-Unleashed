package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.IHealEntityObjective;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;

import javax.annotation.Nullable;

public class HealEntityObjective
  extends Objective
  implements IHealEntityObjective
{
  protected ICheckHeal healEvent = (player, target, amount) -> true;
  
  public HealEntityObjective(String title, int count) {
    this(title, count, (ICheckHeal)null);
  }

  
  public HealEntityObjective(String title, int count, EntityType entityType) {
    this(title, count, (player, target, amount) -> (target.getType() == entityType));
  }

  
  public HealEntityObjective(String title, int count, @Nullable ICheckHeal check) {
    super(title);
    setMaxProgress(count);
    if (check != null) {
      this.healEvent = check;
    }
  }

  
  public boolean checkHeal(PlayerEntity player, LivingEntity target, float amount) {
    return this.healEvent.test(player, target, amount);
  }



  
  @FunctionalInterface
  public static interface ICheckHeal
  {
    default ICheckHeal and(ICheckHeal check) {
      return (player, target, amount) -> 
        (test(player, target, amount) && check.test(player, target, amount));
    }


    
    default ICheckHeal or(ICheckHeal check) {
      return (player, target, amount) -> 
        (test(player, target, amount) || check.test(player, target, amount));
    }
    
    boolean test(PlayerEntity param1PlayerEntity, LivingEntity param1LivingEntity, float param1Float);
  }
}


