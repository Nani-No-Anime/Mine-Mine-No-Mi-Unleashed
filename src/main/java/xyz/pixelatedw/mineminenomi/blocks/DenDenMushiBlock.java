package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.*;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.DenDenMushiTileEntity;

import javax.annotation.Nullable;

public class DenDenMushiBlock extends Block implements IWaterLoggable {
  private static final VoxelShape SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D);
  public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
  public static final IntegerProperty TYPE = IntegerProperty.create("den_type", 0, 2);
  public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

  
  public DenDenMushiBlock() {
    super(Block.Properties.create(Material.DRAGON_EGG).hardnessAndResistance(0.4F).notSolid());
    setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)FACING, (Comparable)Direction.NORTH)).with((IProperty)TYPE, Integer.valueOf(0))).with((IProperty)WATERLOGGED, Boolean.valueOf(false)));
  }


  
  @Nullable
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    BlockState blockstate = getDefaultState();
    BlockPos blockpos = context.getPos();
    Direction[] adirection = context.getNearestLookingDirections();
    IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
    
    int type = context.getItem().getOrCreateTag().getInt("type");
    blockstate = (BlockState)blockstate.with((IProperty)TYPE, Integer.valueOf(type));
    
    for (Direction direction : adirection) {
      
      if (direction.getAxis().isHorizontal()) {
        
        Direction direction1 = direction.getOpposite();
        blockstate = (BlockState)blockstate.with((IProperty)FACING, (Comparable)direction1);
        blockstate = (BlockState)blockstate.with((IProperty)WATERLOGGED, Boolean.valueOf((ifluidstate.getFluid() == Fluids.WATER)));
        if (blockstate.isValidPosition((IWorldReader)context.getWorld(), blockpos))
        {
          return blockstate;
        }
      } 
    } 
    
    return null;
  }


  
  public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return SHAPE;
  }


  
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return SHAPE;
  }


  
  public IFluidState getFluidState(BlockState state) {
    return ((Boolean)state.get((IProperty)WATERLOGGED)).booleanValue() ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
  }


  
  public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
    if (((Boolean)stateIn.get((IProperty)WATERLOGGED)).booleanValue())
    {
      worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate((IWorldReader)worldIn));
    }
    return (facing.getOpposite() == stateIn.get((IProperty)FACING) && !stateIn.isValidPosition((IWorldReader)worldIn, currentPos)) ? Blocks.AIR.getDefaultState() : stateIn;
  }


  
  public BlockState rotate(BlockState state, Rotation rot) {
    return (BlockState)state.with((IProperty)FACING, (Comparable)rot.rotate((Direction)state.get((IProperty)FACING)));
  }


  
  public BlockState mirror(BlockState state, Mirror mirrorIn) {
    return state.rotate(mirrorIn.toRotation((Direction)state.get((IProperty)FACING)));
  }


  
  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(new IProperty[] { (IProperty)WATERLOGGED });
    builder.add(new IProperty[] { (IProperty)FACING });
    builder.add(new IProperty[] { (IProperty)TYPE });
  }


  
  public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
    return true;
  }


  
  @OnlyIn(Dist.CLIENT)
  public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
    return true;
  }


  
  public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
    return false;
  }


  
  public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
    return false;
  }


  
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return (TileEntity)new DenDenMushiTileEntity();
  }


  
  public boolean hasTileEntity(BlockState state) {
    return true;
  }
}


