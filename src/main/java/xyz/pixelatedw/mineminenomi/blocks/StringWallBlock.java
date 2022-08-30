/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.shapes.ISelectionContext;
/*    */ import net.minecraft.util.math.shapes.VoxelShape;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ public class StringWallBlock
/*    */   extends Block {
/* 19 */   protected static final VoxelShape field_196400_b = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
/* 20 */   protected static final VoxelShape field_196401_c = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
/*    */ 
/*    */   
/*    */   public StringWallBlock() {
/* 24 */     super(Block.Properties.create(Material.BARRIER).hardnessAndResistance(-1.0F, 10000.0F).noDrops());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/* 30 */     return field_196400_b;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/* 36 */     return field_196401_c;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 49 */     return (adjacentBlockState.getBlock() == this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
/* 55 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
/* 61 */     entityIn.attackEntityFrom(DamageSource.MAGIC, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\StringWallBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */