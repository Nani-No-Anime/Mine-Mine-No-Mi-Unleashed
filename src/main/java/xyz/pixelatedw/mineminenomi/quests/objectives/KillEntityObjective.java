package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.IKillEntityObjective;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;




public class KillEntityObjective
  extends Objective
  implements IKillEntityObjective
{
  protected ICheckKill killEvent;
  
  public KillEntityObjective(String title, int count) {
    this(title, count, (ICheckKill)null);
  }

  
  public KillEntityObjective(String title, int count, EntityType entityType) {
    this(title, count, (player, target, source) -> (target.getType() == entityType));
  }

  
  public KillEntityObjective(String title, int count, ICheckKill check) {
    super(title); this.killEvent = ((player, target, source) -> { IAttributeInstance attackAttribute = target.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE); return (attackAttribute != null && attackAttribute.getValue() > 0.0D);
      }); setMaxProgress(count);
    if (check != null) {
      this.killEvent = check;
    }
  }

  
  public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source) {
    return this.killEvent.test(player, target, source);
  }



  
  @FunctionalInterface
  public static interface ICheckKill
  {
    default ICheckKill and(ICheckKill check) {
      return (player, target, source) -> 
        (test(player, target, source) && check.test(player, target, source));
    }


    
    default ICheckKill or(ICheckKill check) {
      return (player, target, source) -> 
        (test(player, target, source) || check.test(player, target, source));
    }
    
    boolean test(PlayerEntity param1PlayerEntity, LivingEntity param1LivingEntity, DamageSource param1DamageSource);
  }
}


