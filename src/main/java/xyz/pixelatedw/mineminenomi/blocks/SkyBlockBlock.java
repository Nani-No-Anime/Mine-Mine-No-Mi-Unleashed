package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SkyBlockBlock
  extends Block {
  public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 3);

  
  public SkyBlockBlock() {
    super(Block.Properties.create(Material.EARTH).lightValue(1).hardnessAndResistance(0.7F));
    setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)TYPE, Integer.valueOf(0)));
  }


  
  @Nullable
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    return (BlockState)getDefaultState().with((IProperty)TYPE, Integer.valueOf((context.getWorld()).rand.nextInt(4)));
  }


  
  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(new IProperty[] { (IProperty)TYPE });
  }


  
  public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
    return true;
  }


  
  public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
    return false;
  }

  
  public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
    entityIn.onLivingFall(fallDistance, 0.05F);
  }
}


