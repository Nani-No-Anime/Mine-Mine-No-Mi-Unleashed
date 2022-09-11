package xyz.pixelatedw.mineminenomi.abilities.ushibison;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.RunningSmashAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonWalkZoanInfo;

public class BisonSmashAbility
  extends RunningSmashAbility implements IFormRequiredAbility {
  public static final BisonSmashAbility INSTANCE = new BisonSmashAbility();

  
  public BisonSmashAbility() {
    super("Bison Smash", AbilityHelper.getDevilFruitCategory(), 1.5D, 2.0F);
    setDescription("Running into enemies deals damage and knocks them back");
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)BisonWalkZoanInfo.INSTANCE };
  }
}


