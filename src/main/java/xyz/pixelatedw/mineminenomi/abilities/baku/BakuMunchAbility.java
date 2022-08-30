/*    */ package xyz.pixelatedw.mineminenomi.abilities.baku;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.BlockRayTraceResult;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.baku.BakuMunchParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class BakuMunchAbility extends Ability {
/* 19 */   public static final BakuMunchAbility INSTANCE = new BakuMunchAbility();
/* 20 */   public static final BlockProtectionRule GRIEF_RULE = DefaultProtectionRules.CORE_FOLIAGE_ORE;
/*    */   
/* 22 */   private static final BakuMunchParticleEffect PARTICLES = new BakuMunchParticleEffect();
/*    */ 
/*    */   
/*    */   public BakuMunchAbility() {
/* 26 */     super("Baku Munch", AbilityHelper.getDevilFruitCategory());
/* 27 */     setMaxCooldown(3.0D);
/* 28 */     setDescription("Allows the user to eat a big chunk of blocks in front of him, obtaining all of them as blocks in their inventory");
/*    */     
/* 30 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 35 */     BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)player, 16.0D);
/* 36 */     if (MathHelper.sqrt(player.getDistanceSq((blockRayTraceResult.getHitVec()).x, (blockRayTraceResult.getHitVec()).y, (blockRayTraceResult.getHitVec()).z)) < 5.0F)
/*    */     {
/* 38 */       for (int x = -2; x < 2; x++) {
/*    */         
/* 40 */         for (int y = 0; y < 3; y++) {
/*    */           
/* 42 */           for (int z = -2; z < 2; z++) {
/*    */             
/* 44 */             int posX = (int)(blockRayTraceResult.getHitVec()).x + x;
/* 45 */             int posY = (int)(blockRayTraceResult.getHitVec()).y - y;
/* 46 */             int posZ = (int)(blockRayTraceResult.getHitVec()).z + z;
/* 47 */             Block tempBlock = player.world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock();
/* 48 */             if (AbilityHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, Blocks.AIR, GRIEF_RULE)) {
/*    */               
/* 50 */               player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)tempBlock));
/* 51 */               PARTICLES.spawn(player.world, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     }
/*    */     
/* 58 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\baku\BakuMunchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */