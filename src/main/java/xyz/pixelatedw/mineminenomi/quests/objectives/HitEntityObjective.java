package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.IHitEntityObjective;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;

import javax.annotation.Nullable;

public class HitEntityObjective
  extends Objective
  implements IHitEntityObjective
{
  protected ICheckHit hitEvent = (player, target, source, amount) -> true;
  
  public HitEntityObjective(String title, int count) {
    this(title, count, (ICheckHit)null);
  }

  
  public HitEntityObjective(String title, int count, EntityType entityType) {
    this(title, count, (player, target, source, amount) -> (target.getType() == entityType));
  }

  
  public HitEntityObjective(String title, int count, @Nullable ICheckHit check) {
    super(title);
    setMaxProgress(count);
    if (check != null) {
      this.hitEvent = check;
    }
  }

  
  public boolean checkHit(PlayerEntity player, LivingEntity target, DamageSource source, float amount) {
    return this.hitEvent.test(player, target, source, amount);
  }



  
  @FunctionalInterface
  public static interface ICheckHit
  {
    default ICheckHit and(ICheckHit check) {
      return (player, target, source, amount) -> 
        (test(player, target, source, amount) && check.test(player, target, source, amount));
    }


    
    default ICheckHit or(ICheckHit check) {
      return (player, target, source, amount) -> 
        (test(player, target, source, amount) || check.test(player, target, source, amount));
    }
    
    boolean test(PlayerEntity param1PlayerEntity, LivingEntity param1LivingEntity, DamageSource param1DamageSource, float param1Float);
  }
}


