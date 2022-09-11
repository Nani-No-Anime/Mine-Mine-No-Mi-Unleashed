package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.material.Material;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class AirBlockProtectionRule
  extends BlockProtectionRule {
  public static final AirBlockProtectionRule INSTANCE = new AirBlockProtectionRule();
  
  public AirBlockProtectionRule() {
    super(new BlockProtectionRule[0]);
    addApprovedMaterials(new Material[] { Material.AIR });
  }
}


