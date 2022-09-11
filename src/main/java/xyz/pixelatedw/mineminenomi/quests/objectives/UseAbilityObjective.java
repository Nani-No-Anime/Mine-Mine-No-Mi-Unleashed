package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.IUseAbilityObjective;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class UseAbilityObjective
  extends Objective
  implements IUseAbilityObjective {
  protected ICheckAbilityUse useEvent = (player, ability) -> true;
  
  public UseAbilityObjective(String title, int count) {
    this(title, count, (ICheckAbilityUse)null);
  }

  
  public UseAbilityObjective(String title, int count, Ability ability) {
    this(title, count, (player, abl) -> abl.equals(ability));
  }

  
  public UseAbilityObjective(String title, int count, ICheckAbilityUse check) {
    super(title);
    setMaxProgress(count);
    if (check != null) {
      this.useEvent = check;
    }
  }

  
  public boolean checkAbility(PlayerEntity player, Ability ability) {
    return this.useEvent.test(player, ability);
  }



  
  @FunctionalInterface
  public static interface ICheckAbilityUse
  {
    default ICheckAbilityUse and(ICheckAbilityUse check) {
      return (player, ability) -> 
        (test(player, ability) && check.test(player, ability));
    }


    
    default ICheckAbilityUse or(ICheckAbilityUse check) {
      return (player, ability) -> 
        (test(player, ability) || check.test(player, ability));
    }
    
    boolean test(PlayerEntity param1PlayerEntity, Ability param1Ability);
  }
}


