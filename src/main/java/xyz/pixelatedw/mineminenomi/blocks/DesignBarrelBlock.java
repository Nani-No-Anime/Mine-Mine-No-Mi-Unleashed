package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;

public class DesignBarrelBlock extends Block {
  public static final DirectionProperty PROPERTY_FACING = BlockStateProperties.FACING;
  public static final BooleanProperty PROPERTY_OPEN = BlockStateProperties.OPEN;

  
  public DesignBarrelBlock() {
    super(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD));
    setDefaultState((BlockState)((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)PROPERTY_FACING, (Comparable)Direction.NORTH)).with((IProperty)PROPERTY_OPEN, Boolean.valueOf(false)));
  }








  
  @Deprecated
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.MODEL;
  }









  
  @Deprecated
  public BlockState rotate(BlockState state, Rotation rot) {
    return (BlockState)state.with((IProperty)PROPERTY_FACING, (Comparable)rot.rotate((Direction)state.get((IProperty)PROPERTY_FACING)));
  }








  
  @Deprecated
  public BlockState mirror(BlockState state, Mirror mirrorIn) {
    return state.rotate(mirrorIn.toRotation((Direction)state.get((IProperty)PROPERTY_FACING)));
  }


  
  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(new IProperty[] { (IProperty)PROPERTY_FACING, (IProperty)PROPERTY_OPEN });
  }


  
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    return (BlockState)getDefaultState().with((IProperty)PROPERTY_FACING, (Comparable)context.getNearestLookingDirection().getOpposite());
  }
}


