package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.material.Material;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModMaterials;

public class SnowLayerBlockProtectionRule
  extends BlockProtectionRule {
  public static final SnowLayerBlockProtectionRule INSTANCE = new SnowLayerBlockProtectionRule();
  
  public SnowLayerBlockProtectionRule() {
    super(new BlockProtectionRule[0]);
    addApprovedMaterials(new Material[] { Material.SNOW, Material.SNOW_BLOCK, ModMaterials.HARDENED_SNOW_BLOCK });
  }
}


