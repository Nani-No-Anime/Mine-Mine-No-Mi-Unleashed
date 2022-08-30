/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.shapes.ISelectionContext;
/*    */ import net.minecraft.util.math.shapes.VoxelShape;
/*    */ import net.minecraft.util.math.shapes.VoxelShapes;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.ChallengeArenaTileEntity;
/*    */ 
/*    */ public class ChallengeArenaBlock
/*    */   extends Block
/*    */ {
/*    */   public ChallengeArenaBlock() {
/* 21 */     super(Block.Properties.create(Material.BARRIER).doesNotBlockMovement().hardnessAndResistance(Float.MAX_VALUE).noDrops());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/* 27 */     return VoxelShapes.fullCube();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
/* 46 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
/* 52 */     return (TileEntity)new ChallengeArenaTileEntity();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasTileEntity(BlockState state) {
/* 58 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\ChallengeArenaBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */