/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.HorizontalBlock;
/*    */ import net.minecraft.block.IWaterLoggable;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.fluid.Fluids;
/*    */ import net.minecraft.fluid.IFluidState;
/*    */ import net.minecraft.item.BlockItemUseContext;
/*    */ import net.minecraft.state.BooleanProperty;
/*    */ import net.minecraft.state.IProperty;
/*    */ import net.minecraft.state.StateContainer;
/*    */ import net.minecraft.state.properties.BlockStateProperties;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.shapes.ISelectionContext;
/*    */ import net.minecraft.util.math.shapes.VoxelShape;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.IWorldReader;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ public class DialBlock extends HorizontalBlock implements IWaterLoggable {
/* 25 */   protected static final VoxelShape SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 4.0D, 12.0D);
/* 26 */   public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
/*    */ 
/*    */   
/*    */   public DialBlock() {
/* 30 */     super(Block.Properties.create(Material.CLAY).hardnessAndResistance(0.2F));
/* 31 */     setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)WATERLOGGED, Boolean.valueOf(false)));
/*    */   }
/*    */ 
/*    */   
/*    */   public DialBlock(Block.Properties props) {
/* 36 */     super(props);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/* 42 */     return SHAPE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
/* 61 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
/* 67 */     builder.add(new IProperty[] { (IProperty)WATERLOGGED });
/* 68 */     builder.add(new IProperty[] { (IProperty)BlockStateProperties.HORIZONTAL_FACING });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
/* 74 */     if (((Boolean)stateIn.get((IProperty)WATERLOGGED)).booleanValue())
/*    */     {
/* 76 */       worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate((IWorldReader)worldIn));
/*    */     }
/*    */     
/* 79 */     return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IFluidState getFluidState(BlockState state) {
/* 85 */     return ((Boolean)state.get((IProperty)WATERLOGGED)).booleanValue() ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockState getStateForPlacement(BlockItemUseContext ctx) {
/* 91 */     IFluidState ifluidstate = ctx.getWorld().getFluidState(ctx.getPos());
/*    */     
/* 93 */     return (BlockState)((BlockState)getDefaultState()
/* 94 */       .with((IProperty)HORIZONTAL_FACING, (Comparable)ctx.getPlacementHorizontalFacing()))
/* 95 */       .with((IProperty)WATERLOGGED, Boolean.valueOf((ifluidstate.getFluid() == Fluids.WATER)));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\DialBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */