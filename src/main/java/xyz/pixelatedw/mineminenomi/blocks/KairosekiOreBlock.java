package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class KairosekiOreBlock
  extends OreBlock
{
  public KairosekiOreBlock() {
    super(Block.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).harvestLevel(0).hardnessAndResistance(8.0F));
  }
}


