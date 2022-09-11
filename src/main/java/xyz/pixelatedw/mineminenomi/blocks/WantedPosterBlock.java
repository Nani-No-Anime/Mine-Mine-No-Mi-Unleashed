package xyz.pixelatedw.mineminenomi.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;



public class WantedPosterBlock
  extends Block
{
  public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
  private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(
      (Map)ImmutableMap.of(Direction.NORTH, 
        Block.makeCuboidShape(0.0D, -2.5D, 14.0D, 16.0D, 18.5D, 16.0D), Direction.SOUTH, 
        Block.makeCuboidShape(0.0D, -2.5D, 0.0D, 16.0D, 18.5D, 2.0D), Direction.EAST, 
        Block.makeCuboidShape(0.0D, -2.5D, 0.0D, 2.0D, 18.5D, 16.0D), Direction.WEST, 
        Block.makeCuboidShape(14.0D, -2.5D, 0.0D, 16.0D, 18.5D, 16.0D)));


  
  public WantedPosterBlock() {
    super(Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F).doesNotBlockMovement().notSolid());
    setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)FACING, (Comparable)Direction.NORTH));
  }


  
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return SHAPES.get(state.get((IProperty)FACING));
  }


  
  public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
    return worldIn.getBlockState(pos.offset(((Direction)state.get((IProperty)FACING)).getOpposite())).getMaterial().isSolid();
  }


  
  @Nullable
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    BlockState blockstate = getDefaultState();
    BlockPos blockpos = context.getPos();
    Direction[] adirection = context.getNearestLookingDirections();
    
    for (Direction direction : adirection) {
      
      if (direction.getAxis().isHorizontal()) {
        
        Direction direction1 = direction.getOpposite();
        blockstate = (BlockState)blockstate.with((IProperty)FACING, (Comparable)direction1);
        if (blockstate.isValidPosition((IWorldReader)context.getWorld(), blockpos))
        {
          return blockstate;
        }
      } 
    } 
    
    return null;
  }


  
  public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
    return (facing.getOpposite() == stateIn.get((IProperty)FACING) && !stateIn.isValidPosition((IWorldReader)worldIn, currentPos)) ? Blocks.AIR.getDefaultState() : stateIn;
  }


  
  public BlockState rotate(BlockState state, Rotation rot) {
    return (BlockState)state.with((IProperty)FACING, (Comparable)rot.rotate((Direction)state.get((IProperty)FACING)));
  }


  
  public BlockState mirror(BlockState state, Mirror mirrorIn) {
    return state.rotate(mirrorIn.toRotation((Direction)state.get((IProperty)FACING)));
  }


  
  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(new IProperty[] { (IProperty)FACING });
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








  
  public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
    return ActionResultType.FAIL;
  }


  
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return (TileEntity)new WantedPosterTileEntity();
  }


  
  public boolean hasTileEntity(BlockState state) {
    return true;
  }
}


