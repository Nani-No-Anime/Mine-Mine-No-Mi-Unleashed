package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class CandyBlock
  extends Block
{
  public CandyBlock() {
    super(Block.Properties.create(Material.CAKE).hardnessAndResistance(10.0F, 4.0F).notSolid().noDrops().harvestTool(ToolType.PICKAXE));
  }


  
  public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
    return true;
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
    return (adjacentBlockState.getBlock() == this);
  }


  
  public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
    return 0;
  }
}


