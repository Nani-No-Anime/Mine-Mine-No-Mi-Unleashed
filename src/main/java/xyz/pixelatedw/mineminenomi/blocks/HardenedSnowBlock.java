/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.shapes.ISelectionContext;
/*    */ import net.minecraft.util.math.shapes.VoxelShape;
/*    */ import net.minecraft.util.math.shapes.VoxelShapes;
/*    */ import net.minecraft.world.IBlockReader;
/*    */ import net.minecraftforge.common.ToolType;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMaterials;
/*    */ 
/*    */ public class HardenedSnowBlock
/*    */   extends Block {
/*    */   public HardenedSnowBlock() {
/* 22 */     super(Block.Properties.create(ModMaterials.HARDENED_SNOW_BLOCK).doesNotBlockMovement().hardnessAndResistance(8.0F, 4.0F).sound(SoundType.SNOW).harvestTool(ToolType.PICKAXE));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
/* 28 */     Entity entity = context.getEntity();
/* 29 */     if (entity instanceof PlayerEntity) {
/*    */       
/* 31 */       PlayerEntity player = (PlayerEntity)entity;
/* 32 */       if (DevilFruitCapability.get((LivingEntity)player).hasDevilFruit(ModAbilities.YUKI_YUKI_NO_MI))
/*    */       {
/* 34 */         return VoxelShapes.empty();
/*    */       }
/*    */     } 
/*    */     
/* 38 */     return VoxelShapes.fullCube();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\HardenedSnowBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */