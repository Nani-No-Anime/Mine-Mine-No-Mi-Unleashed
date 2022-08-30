/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.TorikagoTileEntity;
/*    */ 
/*    */ 
/*    */ public class StringMidBlock
/*    */   extends Block
/*    */ {
/*    */   public StringMidBlock() {
/* 19 */     super(Block.Properties.create(Material.BARRIER).hardnessAndResistance(-1.0F, 10000.0F).doesNotBlockMovement().noDrops());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
/* 25 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 32 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
/* 38 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
/* 44 */     return (TileEntity)new TorikagoTileEntity();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasTileEntity(BlockState state) {
/* 50 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\StringMidBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */