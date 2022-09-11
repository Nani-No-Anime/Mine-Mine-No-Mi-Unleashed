package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class LiquidBlockProtectionRule extends BlockProtectionRule {
  public static final LiquidBlockProtectionRule INSTANCE = new LiquidBlockProtectionRule();
  
  public LiquidBlockProtectionRule() {
    super(new BlockProtectionRule[0]);
    addApprovedBlocks(new Block[] { Blocks.SEAGRASS });
    addApprovedMaterials(new Material[] { Material.WATER, Material.LAVA });
  }
}


