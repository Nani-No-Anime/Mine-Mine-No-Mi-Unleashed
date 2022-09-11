package xyz.pixelatedw.mineminenomi.abilities.cyborg;

import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;

public class ColaBackpackBonusAbility
  extends PassiveAbility {
  public static final ColaBackpackBonusAbility INSTANCE = new ColaBackpackBonusAbility();

  
  public ColaBackpackBonusAbility() {
    super("Cola Backpack Bonus", AbilityHelper.getRacialCategory());
  }
}


