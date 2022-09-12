package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.IObtainItemObjective;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;

import java.util.ArrayList;
import java.util.function.Predicate;

public class ObtainItemObjective
  extends Objective
  implements IObtainItemObjective
{
  private Predicate<ItemStack> check = itemStack -> false;
  
  public ObtainItemObjective(String title, int count, Item itemTarget) {
    super(title);
    setMaxProgress(count);
    this.check = (itemStack -> (itemStack.getItem() == itemTarget));
  }

  
  public ObtainItemObjective(String title, int count, Predicate<ItemStack> check) {
    super(title);
    setMaxProgress(count);
    this.check = check;
  }


  
  public boolean checkItem(ItemStack stack) {
    return this.check.test(stack);
  }


  
  public int checkItems(ArrayList<ItemStack> stacks) {
    return stacks.stream().filter(this::checkItem).mapToInt(stack -> stack.getCount()).sum();
  }
}


