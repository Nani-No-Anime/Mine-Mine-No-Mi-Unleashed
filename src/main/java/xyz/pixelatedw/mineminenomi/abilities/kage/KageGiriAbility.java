package xyz.pixelatedw.mineminenomi.abilities.kage;

import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;

public class KageGiriAbility
  extends PassiveAbility {
  public static final KageGiriAbility INSTANCE = new KageGiriAbility();

  
  public KageGiriAbility() {
    super("Kage Giri", AbilityHelper.getDevilFruitCategory());
    setDescription("Allows the user to cut an enemy's shadow using a pair of Scissors");
    hideInGUI(false);
  }
}


