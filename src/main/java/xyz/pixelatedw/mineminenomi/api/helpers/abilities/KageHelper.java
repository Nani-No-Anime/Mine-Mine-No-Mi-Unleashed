package xyz.pixelatedw.mineminenomi.api.helpers.abilities;

import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModItems;

public class KageHelper
{
  public static final ITextComponent NOT_ENOUGH_SHADOWS_WARN = (ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_SHADOWS, new Object[0]);

  
  public static void removeShadows(PlayerEntity player, int amount) {
    ArrayList<ItemStack> slots = new ArrayList<>();
    slots.addAll((Collection<? extends ItemStack>)player.inventory.mainInventory);
    slots.addAll((Collection<? extends ItemStack>)player.inventory.offHandInventory);
    for (ItemStack stack : slots) {
      
      if (amount <= 0)
        break; 
      if (stack.getItem().equals(ModItems.SHADOW)) {
        
        if (stack.getCount() >= amount) {
          
          stack.shrink(amount);
          
          break;
        } 
        
        amount -= stack.getCount();
        stack.shrink(amount);
      } 
    } 
  }


  
  public static int getAvailableShadows(PlayerEntity player) {
    return player.inventory.count(ModItems.SHADOW);
  }
}


