package xyz.pixelatedw.mineminenomi.api.protection;

import xyz.pixelatedw.mineminenomi.api.protection.block.*;

public class DefaultProtectionRules
{
  public static final BlockProtectionRule AIR_FOLIAGE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE });
  public static final BlockProtectionRule CORE_FOLIAGE_ORE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE });
  public static final BlockProtectionRule CORE_FOLIAGE_ORE_LIQUID = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE });
}


