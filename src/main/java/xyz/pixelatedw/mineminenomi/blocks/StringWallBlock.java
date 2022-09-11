package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class StringWallBlock
  extends Block {
  protected static final VoxelShape field_196400_b = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
  protected static final VoxelShape field_196401_c = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

  
  public StringWallBlock() {
    super(Block.Properties.create(Material.BARRIER).hardnessAndResistance(-1.0F, 10000.0F).noDrops());
  }


  
  public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return field_196400_b;
  }


  
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return field_196401_c;
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


  
  public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
    entityIn.attackEntityFrom(DamageSource.MAGIC, 1.0F);
  }
}


