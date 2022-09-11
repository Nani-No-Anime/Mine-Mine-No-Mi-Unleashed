package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class FoliageBlockProtectionRule extends BlockProtectionRule {
  public static final FoliageBlockProtectionRule INSTANCE = new FoliageBlockProtectionRule();
  
  public FoliageBlockProtectionRule() {
    super(new BlockProtectionRule[0]);
    addApprovedMaterials(new Material[] { Material.LEAVES, Material.PLANTS, Material.OCEAN_PLANT, Material.TALL_PLANTS, Material.BAMBOO, Material.BAMBOO_SAPLING });
    addApprovedBlocks(new Block[] { Blocks.MUSHROOM_STEM, Blocks.BROWN_MUSHROOM_BLOCK, Blocks.RED_MUSHROOM_BLOCK, Blocks.PUMPKIN, Blocks.MELON, Blocks.MELON_STEM, Blocks.DEAD_BRAIN_CORAL, Blocks.DEAD_BRAIN_CORAL_FAN, Blocks.DEAD_BRAIN_CORAL_WALL_FAN, Blocks.DEAD_BUBBLE_CORAL, Blocks.DEAD_BUBBLE_CORAL_FAN, Blocks.DEAD_BUBBLE_CORAL_WALL_FAN, Blocks.DEAD_FIRE_CORAL, Blocks.DEAD_FIRE_CORAL_FAN, Blocks.DEAD_FIRE_CORAL_WALL_FAN, Blocks.DEAD_TUBE_CORAL, Blocks.DEAD_TUBE_CORAL_FAN, Blocks.DEAD_TUBE_CORAL_WALL_FAN, Blocks.DEAD_HORN_CORAL, Blocks.DEAD_HORN_CORAL_FAN, Blocks.DEAD_HORN_CORAL_WALL_FAN, Blocks.SNOW_BLOCK, Blocks.COBWEB });
  }
}


