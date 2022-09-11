package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.RunningSmashAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonFlyZoanInfo;

public class PteranodonSmashAbility
  extends RunningSmashAbility implements IFormRequiredAbility {
  public static final PteranodonSmashAbility INSTANCE = new PteranodonSmashAbility();

  
  public PteranodonSmashAbility() {
    super("Pteranodon Smash", AbilityHelper.getDevilFruitCategory(), 1.9D, 4.0F);
    setDescription("Flying into enemies at great speeds deals damage and knocks them back");
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)PteranodonFlyZoanInfo.INSTANCE };
  }
}


