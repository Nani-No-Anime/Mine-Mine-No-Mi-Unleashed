package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.ChallengeArenaTileEntity;

public class ChallengeArenaBlock
  extends Block
{
  public ChallengeArenaBlock() {
    super(Block.Properties.create(Material.BARRIER).doesNotBlockMovement().hardnessAndResistance(Float.MAX_VALUE).noDrops());
  }


  
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return VoxelShapes.fullCube();
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
    return (TileEntity)new ChallengeArenaTileEntity();
  }


  
  public boolean hasTileEntity(BlockState state) {
    return true;
  }
}


