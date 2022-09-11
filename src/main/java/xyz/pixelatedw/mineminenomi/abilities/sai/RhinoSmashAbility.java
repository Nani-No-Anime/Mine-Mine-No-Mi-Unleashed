package xyz.pixelatedw.mineminenomi.abilities.sai;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.RunningSmashAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.SaiWalkZoanInfo;

public class RhinoSmashAbility
  extends RunningSmashAbility implements IFormRequiredAbility {
  public static final RhinoSmashAbility INSTANCE = new RhinoSmashAbility();

  
  public RhinoSmashAbility() {
    super("Rhino Smash", AbilityHelper.getDevilFruitCategory(), 1.5D, 4.0F);
    setDescription("Running into enemies deals damage and knocks them back");
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)SaiWalkZoanInfo.INSTANCE };
  }
}


