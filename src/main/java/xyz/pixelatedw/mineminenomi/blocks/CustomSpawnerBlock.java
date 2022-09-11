package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;

public class CustomSpawnerBlock
  extends Block
{
  public CustomSpawnerBlock() {
    super(Block.Properties.create(Material.BARRIER).hardnessAndResistance(Float.MAX_VALUE).doesNotBlockMovement().noDrops());
  }


  
  public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
    return true;
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
    return true;
  }


  
  public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
    return 0;
  }


  
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return (TileEntity)new CustomSpawnerTileEntity();
  }


  
  public boolean hasTileEntity(BlockState state) {
    return true;
  }
}


