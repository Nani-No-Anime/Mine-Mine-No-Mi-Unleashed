package xyz.pixelatedw.mineminenomi.items.weapons;

import net.minecraft.item.IDyeableArmorItem;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class AbilityColoredSwordItem
  extends AbilitySwordItem
  implements IDyeableArmorItem {
  public AbilityColoredSwordItem(Ability ability, int damage) {
    super(ability, damage);
  }
}


