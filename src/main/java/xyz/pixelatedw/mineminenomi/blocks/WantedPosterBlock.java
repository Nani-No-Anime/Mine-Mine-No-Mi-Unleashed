/*     */ package xyz.pixelatedw.mineminenomi.blocks;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.HorizontalBlock;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.BlockItemUseContext;
/*     */ import net.minecraft.state.DirectionProperty;
/*     */ import net.minecraft.state.IProperty;
/*     */ import net.minecraft.state.StateContainer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.Mirror;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.shapes.ISelectionContext;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WantedPosterBlock
/*     */   extends Block
/*     */ {
/*  40 */   public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
/*  41 */   private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(
/*  42 */       (Map)ImmutableMap.of(Direction.NORTH, 
/*  43 */         Block.makeCuboidShape(0.0D, -2.5D, 14.0D, 16.0D, 18.5D, 16.0D), Direction.SOUTH, 
/*  44 */         Block.makeCuboidShape(0.0D, -2.5D, 0.0D, 16.0D, 18.5D, 2.0D), Direction.EAST, 
/*  45 */         Block.makeCuboidShape(0.0D, -2.5D, 0.0D, 2.0D, 18.5D, 16.0D), Direction.WEST, 
/*  46 */         Block.makeCuboidShape(14.0D, -2.5D, 0.0D, 16.0D, 18.5D, 16.0D)));
/*     */ 
/*     */ 
/*     */   
/*     */   public WantedPosterBlock() {
/*  51 */     super(Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F).doesNotBlockMovement().notSolid());
/*  52 */     setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)FACING, (Comparable)Direction.NORTH));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  58 */     return SHAPES.get(state.get((IProperty)FACING));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
/*  64 */     return worldIn.getBlockState(pos.offset(((Direction)state.get((IProperty)FACING)).getOpposite())).getMaterial().isSolid();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockState getStateForPlacement(BlockItemUseContext context) {
/*  71 */     BlockState blockstate = getDefaultState();
/*  72 */     BlockPos blockpos = context.getPos();
/*  73 */     Direction[] adirection = context.getNearestLookingDirections();
/*     */     
/*  75 */     for (Direction direction : adirection) {
/*     */       
/*  77 */       if (direction.getAxis().isHorizontal()) {
/*     */         
/*  79 */         Direction direction1 = direction.getOpposite();
/*  80 */         blockstate = (BlockState)blockstate.with((IProperty)FACING, (Comparable)direction1);
/*  81 */         if (blockstate.isValidPosition((IWorldReader)context.getWorld(), blockpos))
/*     */         {
/*  83 */           return blockstate;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  88 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
/*  94 */     return (facing.getOpposite() == stateIn.get((IProperty)FACING) && !stateIn.isValidPosition((IWorldReader)worldIn, currentPos)) ? Blocks.AIR.getDefaultState() : stateIn;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState rotate(BlockState state, Rotation rot) {
/* 100 */     return (BlockState)state.with((IProperty)FACING, (Comparable)rot.rotate((Direction)state.get((IProperty)FACING)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState mirror(BlockState state, Mirror mirrorIn) {
/* 106 */     return state.rotate(mirrorIn.toRotation((Direction)state.get((IProperty)FACING)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
/* 112 */     builder.add(new IProperty[] { (IProperty)FACING });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
/* 118 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 125 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
/* 137 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
/* 149 */     return ActionResultType.FAIL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
/* 155 */     return (TileEntity)new WantedPosterTileEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasTileEntity(BlockState state) {
/* 161 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\WantedPosterBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */