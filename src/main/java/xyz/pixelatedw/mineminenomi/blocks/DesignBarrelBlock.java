/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockRenderType;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.item.BlockItemUseContext;
/*    */ import net.minecraft.state.BooleanProperty;
/*    */ import net.minecraft.state.DirectionProperty;
/*    */ import net.minecraft.state.IProperty;
/*    */ import net.minecraft.state.StateContainer;
/*    */ import net.minecraft.state.properties.BlockStateProperties;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.Mirror;
/*    */ import net.minecraft.util.Rotation;
/*    */ 
/*    */ public class DesignBarrelBlock extends Block {
/* 19 */   public static final DirectionProperty PROPERTY_FACING = BlockStateProperties.FACING;
/* 20 */   public static final BooleanProperty PROPERTY_OPEN = BlockStateProperties.OPEN;
/*    */ 
/*    */   
/*    */   public DesignBarrelBlock() {
/* 24 */     super(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD));
/* 25 */     setDefaultState((BlockState)((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)PROPERTY_FACING, (Comparable)Direction.NORTH)).with((IProperty)PROPERTY_OPEN, Boolean.valueOf(false)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public BlockRenderType getRenderType(BlockState state) {
/* 38 */     return BlockRenderType.MODEL;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public BlockState rotate(BlockState state, Rotation rot) {
/* 52 */     return (BlockState)state.with((IProperty)PROPERTY_FACING, (Comparable)rot.rotate((Direction)state.get((IProperty)PROPERTY_FACING)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public BlockState mirror(BlockState state, Mirror mirrorIn) {
/* 65 */     return state.rotate(mirrorIn.toRotation((Direction)state.get((IProperty)PROPERTY_FACING)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
/* 71 */     builder.add(new IProperty[] { (IProperty)PROPERTY_FACING, (IProperty)PROPERTY_OPEN });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockState getStateForPlacement(BlockItemUseContext context) {
/* 77 */     return (BlockState)getDefaultState().with((IProperty)PROPERTY_FACING, (Comparable)context.getNearestLookingDirection().getOpposite());
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\DesignBarrelBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */