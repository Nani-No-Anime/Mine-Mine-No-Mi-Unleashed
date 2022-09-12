package xyz.pixelatedw.mineminenomi.api.quests.objectives;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public interface IObtainItemObjective {
  boolean checkItem(ItemStack paramItemStack);
  
  int checkItems(ArrayList<ItemStack> paramArrayList);
}


