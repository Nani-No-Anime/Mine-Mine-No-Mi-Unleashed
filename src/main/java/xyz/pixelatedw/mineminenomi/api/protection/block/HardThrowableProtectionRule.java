package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class HardThrowableProtectionRule extends BlockProtectionRule {
  public static final HardThrowableProtectionRule INSTANCE = new HardThrowableProtectionRule();
  
  public HardThrowableProtectionRule() {
    super(new BlockProtectionRule[0]);
    addBannedBlocks(new Block[] { Blocks.COAL_BLOCK, Blocks.DIAMOND_BLOCK, Blocks.EMERALD_BLOCK, Blocks.OBSIDIAN, Blocks.IRON_BLOCK, Blocks.ANVIL, Blocks.ENCHANTING_TABLE, Blocks.ENDER_CHEST, Blocks.WATER, Blocks.LAVA, Blocks.BEACON, Blocks.DRAGON_EGG, Blocks.SLIME_BLOCK, Blocks.HONEY_BLOCK });

    
    addApprovedMaterials(new Material[] { Material.IRON, Material.PORTAL });
  }
}


