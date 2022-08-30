/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.brawler;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TatsumakiParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     double t = 0.0D;
/*    */     
/* 19 */     Random rand = world.rand;
/* 20 */     double size = 2.0D;
/*    */     
/* 22 */     while (t < 1.0D) {
/*    */       
/* 24 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 26 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/*    */         
/* 28 */         double x = t * Math.cos(theta);
/* 29 */         double z = t * Math.sin(theta);
/*    */         
/* 31 */         motionX = Math.sin(theta) / 10.0D;
/* 32 */         motionY = 0.3D + rand.nextDouble() / 10.0D;
/* 33 */         motionZ = -Math.cos(theta) / 10.0D;
/* 34 */         for (int i = -10; i < 10; i++)
/* 35 */           ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.SNEEZE, posX + x * size + WyHelper.randomDouble() / 2.0D, posY + i, posZ + z * size + WyHelper.randomDouble() / 2.0D, 1, motionX, motionY, motionZ, 0.03D); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\brawler\TatsumakiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */