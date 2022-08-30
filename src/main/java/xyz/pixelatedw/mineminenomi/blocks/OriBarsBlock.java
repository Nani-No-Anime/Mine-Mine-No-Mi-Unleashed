/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.PaneBlock;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.shapes.ISelectionContext;
/*    */ import net.minecraft.util.math.shapes.VoxelShape;
/*    */ import net.minecraft.util.math.shapes.VoxelShapes;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ 
/*    */ public class OriBarsBlock extends PaneBlock {
/*    */   public OriBarsBlock() {
/* 20 */     super(Block.Properties.create(Material.IRON).hardnessAndResistance(50.0F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
/* 26 */     Entity entity = context.getEntity();
/* 27 */     if (entity instanceof PlayerEntity) {
/*    */       
/* 29 */       PlayerEntity player = (PlayerEntity)entity;
/* 30 */       if (DevilFruitCapability.get((LivingEntity)player).hasDevilFruit(ModAbilities.ORI_ORI_NO_MI))
/*    */       {
/* 32 */         return VoxelShapes.empty();
/*    */       }
/*    */     } 
/*    */     
/* 36 */     if (getIndex(state) == 15) {
/* 37 */       return VoxelShapes.fullCube();
/*    */     }
/* 39 */     return this.collisionShapes[getIndex(state)];
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\OriBarsBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */