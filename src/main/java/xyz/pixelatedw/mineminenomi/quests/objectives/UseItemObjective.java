package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.IUseItemObjective;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;

public class UseItemObjective
  extends Objective
  implements IUseItemObjective {
  protected ICheckItemUse useEvent = (player, itemStack, duration) -> true;
  
  public UseItemObjective(String title, int count) {
    this(title, count, (ICheckItemUse)null);
  }

  
  public UseItemObjective(String title, int count, Item item) {
    this(title, count, (player, itemStack, duration) -> (itemStack.getItem() == item));
  }

  
  public UseItemObjective(String title, int count, ICheckItemUse check) {
    super(title);
    setMaxProgress(count);
    if (check != null) {
      this.useEvent = check;
    }
  }

  
  public boolean checkItem(PlayerEntity player, ItemStack itemStack, int duration) {
    return this.useEvent.test(player, itemStack, duration);
  }



  
  @FunctionalInterface
  public static interface ICheckItemUse
  {
    default ICheckItemUse and(ICheckItemUse check) {
      return (player, itemStack, duration) -> 
        (test(player, itemStack, duration) && check.test(player, itemStack, duration));
    }


    
    default ICheckItemUse or(ICheckItemUse check) {
      return (player, itemStack, duration) -> 
        (test(player, itemStack, duration) || check.test(player, itemStack, duration));
    }
    
    boolean test(PlayerEntity param1PlayerEntity, ItemStack param1ItemStack, int param1Int);
  }
}


