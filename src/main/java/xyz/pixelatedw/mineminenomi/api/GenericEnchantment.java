package xyz.pixelatedw.mineminenomi.api;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class GenericEnchantment
  extends Enchantment
{
  private final boolean isTreasure;
  
  public GenericEnchantment(Enchantment.Rarity rarityIn, boolean isTreasure, EquipmentSlotType... slots) {
    super(rarityIn, EnchantmentType.WEAPON, slots);
    this.isTreasure = isTreasure;
  }


  
  public int getMaxLevel() {
    return 1;
  }

  
  public boolean isTreasureEnchantment() {
    return this.isTreasure;
  }
}


