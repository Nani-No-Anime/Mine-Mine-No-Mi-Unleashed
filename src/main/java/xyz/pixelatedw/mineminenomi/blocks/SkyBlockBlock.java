/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.item.BlockItemUseContext;
/*    */ import net.minecraft.state.IProperty;
/*    */ import net.minecraft.state.IntegerProperty;
/*    */ import net.minecraft.state.StateContainer;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class SkyBlockBlock
/*    */   extends Block {
/* 19 */   public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 3);
/*    */ 
/*    */   
/*    */   public SkyBlockBlock() {
/* 23 */     super(Block.Properties.create(Material.EARTH).lightValue(1).hardnessAndResistance(0.7F));
/* 24 */     setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with((IProperty)TYPE, Integer.valueOf(0)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public BlockState getStateForPlacement(BlockItemUseContext context) {
/* 31 */     return (BlockState)getDefaultState().with((IProperty)TYPE, Integer.valueOf((context.getWorld()).rand.nextInt(4)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
/* 37 */     builder.add(new IProperty[] { (IProperty)TYPE });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
/* 49 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
/* 54 */     entityIn.onLivingFall(fallDistance, 0.05F);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\SkyBlockBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */