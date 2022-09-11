package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.state.IProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class SpongeCakeBlock extends CakeBlock {
  protected static final VoxelShape[] SHAPES = new VoxelShape[] { Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.makeCuboidShape(3.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.makeCuboidShape(5.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.makeCuboidShape(7.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.makeCuboidShape(9.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), 
      Block.makeCuboidShape(11.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.makeCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D) };

  
  public SpongeCakeBlock() {
    super(Block.Properties.create(Material.CAKE).lightValue(0).hardnessAndResistance(0.7F).sound(SoundType.CLOTH));
  }


  
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return SHAPES[((Integer)state.get((IProperty)BITES)).intValue()];
  }


  
  public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
    return true;
  }


  
  public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
    return false;
  }


  
  public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
    entityIn.onLivingFall(fallDistance, 0.01F);
  }
}


