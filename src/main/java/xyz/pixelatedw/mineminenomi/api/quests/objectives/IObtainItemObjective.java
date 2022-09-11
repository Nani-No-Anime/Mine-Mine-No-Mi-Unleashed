package xyz.pixelatedw.mineminenomi.api.quests.objectives;

import java.util.ArrayList;
import net.minecraft.item.ItemStack;

public interface IObtainItemObjective {
  boolean checkItem(ItemStack paramItemStack);
  
  int checkItems(ArrayList<ItemStack> paramArrayList);
}


