/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.ushibison;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KokuteiCrossParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     double t = 0.0D;
/*    */     
/* 18 */     Random rand = world.rand;
/*    */     
/* 20 */     while (t < 1.0D) {
/*    */       
/* 22 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 24 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.09817477042468103D) {
/*    */         
/* 26 */         double x = t * Math.cos(theta);
/* 27 */         double z = t * Math.sin(theta);
/*    */         
/* 29 */         motionX = x / 6.0D;
/* 30 */         motionY = -0.1D + rand.nextDouble() / 10.0D;
/* 31 */         motionZ = z / 6.0D;
/*    */         
/* 33 */         ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.CLOUD, posX + x * 1.85D, posY + 1.2D, posZ + z * 1.85D, 1, motionX, motionY, motionZ, 0.02D);
/* 34 */         ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.CLOUD, posX + x * 1.85D, posY + 2.2D, posZ + z * 1.85D, 1, motionX, motionY, motionZ, 0.02D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effect\\ushibison\KokuteiCrossParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */