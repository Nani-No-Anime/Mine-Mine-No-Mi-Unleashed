package xyz.pixelatedw.mineminenomi.items.weapons;

import net.minecraft.item.IDyeableArmorItem;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityItemTier;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class AbilityColoredPickaxeItem
  extends AbilityPickaxeItem
  implements IDyeableArmorItem {
  public AbilityColoredPickaxeItem(Ability ability, AbilityItemTier tier, int attackDamage, float attackSpeed) {
    super(ability, tier, attackDamage, attackSpeed);
  }
}


