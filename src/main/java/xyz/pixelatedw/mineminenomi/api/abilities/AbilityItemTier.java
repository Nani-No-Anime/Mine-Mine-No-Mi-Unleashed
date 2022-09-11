package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum AbilityItemTier implements IItemTier {
  DORU(500, 8.0F, 3.0F, 2, 10),
  WEAPON(1000, 8.0F, 0.0F, 1, 20);
  private int maxUses;
  private float efficiency;
  private float attackDamage;
  private int harvestLevel;
  private int enchantability;
  
  AbilityItemTier(int maxUses, float efficiency, float attackDamage, int harvestLevel, int enchantability) {
    this.maxUses = maxUses;
    this.efficiency = efficiency;
    this.attackDamage = attackDamage;
    this.harvestLevel = harvestLevel;
    this.enchantability = enchantability;
  }

  
  public int getMaxUses() {
    return this.maxUses;
  }

  
  public float getEfficiency() {
    return this.efficiency;
  }

  
  public float getAttackDamage() {
    return this.attackDamage;
  }

  
  public int getHarvestLevel() {
    return this.harvestLevel;
  }

  
  public int getEnchantability() {
    return this.enchantability;
  }

  
  public Ingredient getRepairMaterial() {
    return null;
  }
}


