/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.baku;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.particles.BlockParticleData;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class BakuMunchParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 3; i++) {
/*    */       
/* 18 */       double offsetX = world.rand.nextDouble();
/* 19 */       double offsetY = 1.0D;
/* 20 */       double offsetZ = world.rand.nextDouble();
/*    */       
/* 22 */       BlockState blockState = world.getBlockState((new BlockPos(posX + offsetX, posY, posZ + offsetZ)).down());
/*    */       
/* 24 */       ((ServerWorld)world).spawnParticle((IParticleData)new BlockParticleData(ParticleTypes.BLOCK, blockState), posX + offsetX, posY + offsetY, posZ + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\baku\BakuMunchParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */