/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.zou;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.particles.BlockParticleData;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class GreatStompParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 20 */     double phi = 0.0D;
/*    */ 
/*    */     
/* 23 */     while (phi < 10.0D) {
/*    */       
/* 25 */       phi += 0.3141592653589793D;
/*    */       double theta;
/* 27 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/*    */         
/* 29 */         double x = phi * Math.cos(theta);
/* 30 */         double y = WyHelper.randomDouble();
/* 31 */         double z = phi * Math.sin(theta);
/*    */         
/* 33 */         motionX = WyHelper.randomDouble() / 2.0D;
/* 34 */         motionY = 0.0D;
/* 35 */         motionZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 37 */         BlockState blockState = world.getBlockState((new BlockPos(posX, posY, posZ)).down());
/*    */         
/* 39 */         if (blockState.getMaterial() == Material.AIR) {
/* 40 */           blockState = Blocks.DIRT.getDefaultState();
/*    */         }
/* 42 */         ((ServerWorld)world).spawnParticle((IParticleData)new BlockParticleData(ParticleTypes.BLOCK, blockState), posX + 
/* 43 */             WyHelper.randomWithRange(-3, 3) + x, posY + y, posZ + 
/*    */             
/* 45 */             WyHelper.randomWithRange(-3, 3) + z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\zou\GreatStompParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */