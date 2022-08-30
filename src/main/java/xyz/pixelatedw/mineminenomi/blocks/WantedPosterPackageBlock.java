/*     */ package xyz.pixelatedw.mineminenomi.blocks;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.IWaterLoggable;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.fluid.Fluids;
/*     */ import net.minecraft.fluid.IFluidState;
/*     */ import net.minecraft.item.BlockItemUseContext;
/*     */ import net.minecraft.state.BooleanProperty;
/*     */ import net.minecraft.state.IProperty;
/*     */ import net.minecraft.state.StateContainer;
/*     */ import net.minecraft.state.properties.BlockStateProperties;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.shapes.ISelectionContext;
/*     */ import net.minecraft.util.math.shapes.VoxelShape;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterPackageTileEntity;
/*     */ 
/*     */ public class WantedPosterPackageBlock extends Block implements IWaterLoggable {
/*  29 */   protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
/*  30 */   public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
/*     */ 
/*     */   
/*     */   public WantedPosterPackageBlock() {
/*  34 */     super(Block.Properties.create(Material.BAMBOO).hardnessAndResistance(0.2F).notSolid());
/*  35 */     setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)WATERLOGGED, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
/*  41 */     builder.add(new IProperty[] { (IProperty)WATERLOGGED });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
/*  47 */     if (((Boolean)stateIn.get((IProperty)WATERLOGGED)).booleanValue())
/*     */     {
/*  49 */       worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate((IWorldReader)worldIn));
/*     */     }
/*     */     
/*  52 */     return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IFluidState getFluidState(BlockState state) {
/*  58 */     return ((Boolean)state.get((IProperty)WATERLOGGED)).booleanValue() ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState getStateForPlacement(BlockItemUseContext ctx) {
/*  64 */     IFluidState ifluidstate = ctx.getWorld().getFluidState(ctx.getPos());
/*     */     
/*  66 */     return (BlockState)getDefaultState()
/*  67 */       .with((IProperty)WATERLOGGED, Boolean.valueOf((ifluidstate.getFluid() == Fluids.WATER)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/*  73 */     return SHAPE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
/*  86 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
/* 104 */     return (TileEntity)new WantedPosterPackageTileEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasTileEntity(BlockState state) {
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, IFluidState fluid) {
/* 116 */     ItemsHelper.dropWantedPosters(world, pos.getX(), pos.getY(), pos.getZ());
/* 117 */     return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\WantedPosterPackageBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */