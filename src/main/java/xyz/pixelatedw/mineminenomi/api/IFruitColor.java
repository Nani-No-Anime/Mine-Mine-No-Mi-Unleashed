package xyz.pixelatedw.mineminenomi.api;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;


public interface IFruitColor
{
  default boolean hasBaseColor(ItemStack stack) {
    CompoundNBT compoundnbt = stack.getChildTag("display");
    return (compoundnbt != null && compoundnbt.contains("color", 99));
  }

  
  default int getBaseColor(ItemStack stack) {
    CompoundNBT compoundnbt = stack.getChildTag("display");
    return (compoundnbt != null && compoundnbt.contains("color", 99)) ? compoundnbt.getInt("color") : -1;
  }

  
  default void removeBaseColor(ItemStack stack) {
    CompoundNBT compoundnbt = stack.getChildTag("display");
    if (compoundnbt != null && compoundnbt.contains("color"))
    {
      compoundnbt.remove("color");
    }
  }

  
  default void setBaseColor(ItemStack stack, int color) {
    stack.getOrCreateChildTag("display").putInt("color", color);
  }


  
  default boolean hasStemColor(ItemStack stack) {
    CompoundNBT compoundnbt = stack.getChildTag("display");
    return (compoundnbt != null && compoundnbt.contains("stem_color", 99));
  }

  
  default int getStemColor(ItemStack stack) {
    CompoundNBT compoundnbt = stack.getChildTag("display");
    return (compoundnbt != null && compoundnbt.contains("stem_color", 99)) ? compoundnbt.getInt("stem_color") : 10511680;
  }

  
  default void removeStemColor(ItemStack stack) {
    CompoundNBT compoundnbt = stack.getChildTag("display");
    if (compoundnbt != null && compoundnbt.contains("stem_color"))
    {
      compoundnbt.remove("stem_color");
    }
  }

  
  default void setStemColor(ItemStack stack, int color) {
    stack.getOrCreateChildTag("display").putInt("stem_color", color);
  }
}


