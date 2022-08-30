/*    */ package xyz.pixelatedw.mineminenomi.abilities.yami;
/*    */ import java.lang.invoke.SerializedLambda;

import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.yami.LiberationProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class LiberationAbility extends Ability {
/* 15 */   public static final LiberationAbility INSTANCE = new LiberationAbility();
/*    */   
/* 17 */   private int liberationCount = 0;
/*    */ 
/*    */   
/*    */   public LiberationAbility() {
/* 21 */     super("Liberation", AbilityHelper.getDevilFruitCategory());
/* 22 */     setDescription("The user sucks up any Darkness they placed, then expells everything sucked up at the target location");
/* 23 */     setMaxCooldown(4.0D);
/*    */     
/* 25 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 30 */     if (this.liberationCount > 0) {
/*    */       
/* 32 */       RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player);
/*    */       
/* 34 */       double x = (mop.getHitVec()).x;
/* 35 */       double y = (mop.getHitVec()).y;
/* 36 */       double z = (mop.getHitVec()).z;
/*    */       
/* 38 */       while (this.liberationCount > 0)
/*    */       {
/* 40 */         LiberationProjectile proj = new LiberationProjectile(player.world, (LivingEntity)player);
/* 41 */         proj.setLocationAndAngles(x + WyHelper.randomWithRange(-3, 3), y + 14.0D + WyHelper.randomWithRange(0, 4), z + WyHelper.randomWithRange(-3, 3), 0.0F, 0.0F);
/* 42 */         proj.setMotion(0.0D, -0.7D - player.world.rand.nextDouble(), 0.0D);
/* 43 */         player.world.addEntity((Entity)proj);
/* 44 */         this.liberationCount--;
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 49 */       int libCount = 0;
/*    */       
/* 51 */       for (int x = -40; x < 40; x++) {
/*    */         
/* 53 */         for (int y = -40; y < 40; y++) {
/*    */           
/* 55 */           for (int z = -40; z < 40; z++) {
/*    */             
/* 57 */             BlockPos pos = new BlockPos((int)player.getPosX() + x, (int)player.getPosY() + y, (int)player.getPosZ() + z);
/* 58 */             if (player.world.getBlockState(pos).getBlock() == ModBlocks.DARKNESS) {
/*    */               
/* 60 */               player.world.setBlockState(pos, Blocks.AIR.getDefaultState());
/* 61 */               libCount++;
/* 62 */               if (libCount % 10 == 0) {
/* 63 */                 this.liberationCount++;
/*    */               }
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/* 70 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\yami\LiberationAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */