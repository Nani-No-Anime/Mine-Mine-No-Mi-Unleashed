/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.fluid.IFluidState;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.shapes.ISelectionContext;
/*    */ import net.minecraft.util.math.shapes.VoxelShape;
/*    */ import net.minecraft.util.math.shapes.VoxelShapes;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.AblProtectionTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ 
/*    */ public class AblProtectionBlock
/*    */   extends Block
/*    */ {
/*    */   public AblProtectionBlock() {
/* 25 */     super(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.5F).doesNotBlockMovement());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
/* 31 */     return VoxelShapes.fullCube();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
/* 50 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TileEntity createTileEntity(BlockState state, IBlockReader world) {
/* 56 */     return (TileEntity)new AblProtectionTileEntity();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasTileEntity(BlockState state) {
/* 62 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, IFluidState fluid) {
/* 68 */     ExtendedWorldData worldData = ExtendedWorldData.get(world);
/* 69 */     worldData.removeRestrictedArea(pos.getX(), pos.getY(), pos.getZ());
/* 70 */     return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\AblProtectionBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */