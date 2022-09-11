package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BarrierBlock
  extends Block
{
  public BarrierBlock() {
    super(Block.Properties.create(Material.BARRIER).hardnessAndResistance(-1.0F, 10000.0F).notSolid().noDrops());
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


