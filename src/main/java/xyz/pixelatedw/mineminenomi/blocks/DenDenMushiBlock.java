/*     */ package xyz.pixelatedw.mineminenomi.blocks;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.HorizontalBlock;
/*     */ import net.minecraft.block.IWaterLoggable;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.fluid.Fluids;
/*     */ import net.minecraft.fluid.IFluidState;
/*     */ import net.minecraft.item.BlockItemUseContext;
/*     */ import net.minecraft.state.BooleanProperty;
/*     */ import net.minecraft.state.DirectionProperty;
/*     */ import net.minecraft.state.IProperty;
/*     */ import net.minecraft.state.IntegerProperty;
/*     */ import net.minecraft.state.StateContainer;
/*     */ import net.minecraft.state.properties.BlockStateProperties;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.Mirror;
/*     */ import net.minecraft.util.Rotation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.shapes.ISelectionContext;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.DenDenMushiTileEntity;
/*     */ 
/*     */ public class DenDenMushiBlock extends Block implements IWaterLoggable {
/*  34 */   private static final VoxelShape SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D);
/*  35 */   public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
/*  36 */   public static final IntegerProperty TYPE = IntegerProperty.create("den_type", 0, 2);
/*  37 */   public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
/*     */ 
/*     */   
/*     */   public DenDenMushiBlock() {
/*  41 */     super(Block.Properties.create(Material.DRAGON_EGG).hardnessAndResistance(0.4F).notSolid());
/*  42 */     setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)FACING, (Comparable)Direction.NORTH)).with((IProperty)TYPE, Integer.valueOf(0))).with((IProperty)WATERLOGGED, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockState getStateForPlacement(BlockItemUseContext context) {
/*  49 */     BlockState blockstate = getDefaultState();
/*  50 */     BlockPos blockpos = context.getPos();
/*  51 */     Direction[] adirection = context.getNearestLookingDirections();
/*  52 */     IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
/*     */     
/*  54 */     int type = context.getItem().getOrCreateTag().getInt("type");
/*  55 */     blockstate = (BlockState)blockstate.with((IProperty)TYPE, Integer.valueOf(type));
/*     */     
/*  57 */     for (Direction direction : adirection) {
/*     */       
/*  59 */       if (direction.getAxis().isHorizontal()) {
/*     */         
/*  61 */         Direction direction1 = direction.getOpposite();
/*  62 */         blockstate = (BlockState)blockstate.with((IProperty)FACING, (Comparable)direction1);
/*  63 */         blockstate = (BlockState)blockstate.with((IProperty)WATERLOGGED, Boolean.valueOf((ifluidstate.getFluid() == Fluids.WATER)));
/*  64 */         if (blockstate.isValidPosition((IWorldReader)context.getWorld(), blockpos))
/*     */         {
/*  66 */           return blockstate;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  71 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  77 */     return SHAPE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  83 */     return SHAPE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IFluidState getFluidState(BlockState state) {
/*  89 */     return ((Boolean)state.get((IProperty)WATERLOGGED)).booleanValue() ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
/*  95 */     if (((Boolean)stateIn.get((IProperty)WATERLOGGED)).booleanValue())
/*     */     {
/*  97 */       worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate((IWorldReader)worldIn));
/*     */     }
/*  99 */     return (facing.getOpposite() == stateIn.get((IProperty)FACING) && !stateIn.isValidPosition((IWorldReader)worldIn, currentPos)) ? Blocks.AIR.getDefaultState() : stateIn;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState rotate(BlockState state, Rotation rot) {
/* 105 */     return (BlockState)state.with((IProperty)FACING, (Comparable)rot.rotate((Direction)state.get((IProperty)FACING)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState mirror(BlockState state, Mirror mirrorIn) {
/* 111 */     return state.rotate(mirrorIn.toRotation((Direction)state.get((IProperty)FACING)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
/* 117 */     builder.add(new IProperty[] { (IProperty)WATERLOGGED });
/* 118 */     builder.add(new IProperty[] { (IProperty)FACING });
/* 119 */     builder.add(new IProperty[] { (IProperty)TYPE });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
/* 125 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 132 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
/* 144 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
/* 150 */     return (TileEntity)new DenDenMushiTileEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasTileEntity(BlockState state) {
/* 156 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\DenDenMushiBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */