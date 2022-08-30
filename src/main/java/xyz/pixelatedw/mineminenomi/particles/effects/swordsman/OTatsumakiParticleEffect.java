/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.swordsman;
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
/*    */ public class OTatsumakiParticleEffect
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
/* 24 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/*    */         
/* 26 */         double x = t * Math.cos(theta);
/* 27 */         double z = t * Math.sin(theta);
/*    */         
/* 29 */         motionX = x / 6.0D;
/* 30 */         motionY = -0.1D + rand.nextDouble() / 10.0D;
/* 31 */         motionZ = z / 6.0D;
/*    */         
/* 33 */         WyHelper.spawnParticles(ParticleTypes.SWEEP_ATTACK, (ServerWorld)world, posX + x * 1.85D, posY + 1.2D + WyHelper.randomDouble(), posZ + z * 1.85D, (float)motionX, (float)motionY, (float)motionZ);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\swordsman\OTatsumakiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */