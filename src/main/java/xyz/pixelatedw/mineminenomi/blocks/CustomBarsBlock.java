package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class CustomBarsBlock
  extends PaneBlock {
  public CustomBarsBlock() {
    this(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE));
  }

  
  public CustomBarsBlock(Block.Properties props) {
    super(props);
  }
}


