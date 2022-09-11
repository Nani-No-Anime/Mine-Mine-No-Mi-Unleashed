package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class OceanPlantsProtectionRule extends BlockProtectionRule {
  public static final OceanPlantsProtectionRule INSTANCE = new OceanPlantsProtectionRule();
  
  public OceanPlantsProtectionRule() {
    super(new BlockProtectionRule[0]);
    addApprovedMaterials(new Material[] { Material.OCEAN_PLANT });
    addApprovedBlocks(new Block[] { Blocks.DEAD_BRAIN_CORAL, Blocks.DEAD_BRAIN_CORAL_FAN, Blocks.DEAD_BRAIN_CORAL_WALL_FAN, Blocks.DEAD_BUBBLE_CORAL, Blocks.DEAD_BUBBLE_CORAL_FAN, Blocks.DEAD_BUBBLE_CORAL_WALL_FAN, Blocks.DEAD_FIRE_CORAL, Blocks.DEAD_FIRE_CORAL_FAN, Blocks.DEAD_FIRE_CORAL_WALL_FAN, Blocks.DEAD_TUBE_CORAL, Blocks.DEAD_TUBE_CORAL_FAN, Blocks.DEAD_TUBE_CORAL_WALL_FAN, Blocks.DEAD_HORN_CORAL, Blocks.DEAD_HORN_CORAL_FAN, Blocks.DEAD_HORN_CORAL_WALL_FAN });
  }
}


