package xyz.pixelatedw.mineminenomi.abilities.bomu;

import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class BreezeBreathBombAbility
  extends ContinuousAbility
{
  public static final BreezeBreathBombAbility INSTANCE = new BreezeBreathBombAbility();

  
  public BreezeBreathBombAbility() {
    super("Breeze Breath Bomb", AbilityHelper.getDevilFruitCategory());
    setDescription("Load a gun with explosive breath and shoot a chain explosion");
    setMaxCooldown(15.0D);
  }
}


