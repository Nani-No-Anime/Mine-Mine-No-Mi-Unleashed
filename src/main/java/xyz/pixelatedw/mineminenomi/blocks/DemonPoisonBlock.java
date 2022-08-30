/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DemonPoisonBlock
/*    */   extends PoisonBlock
/*    */ {
/*    */   public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
/* 17 */     if (entity instanceof LivingEntity)
/*    */     {
/* 19 */       if (!((LivingEntity)entity).isPotionActive(Effects.POISON))
/*    */       {
/* 21 */         ((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.POISON, 300, 2));
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\DemonPoisonBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */