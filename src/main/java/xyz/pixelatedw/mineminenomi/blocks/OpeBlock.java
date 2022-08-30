/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ public class OpeBlock
/*    */   extends Block
/*    */ {
/*    */   public OpeBlock() {
/* 16 */     super(Block.Properties.create(Material.BARRIER).hardnessAndResistance(Float.MAX_VALUE).doesNotBlockMovement().noDrops());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
/* 22 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 29 */     return (adjacentBlockState.getBlock() == this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
/* 35 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\OpeBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */