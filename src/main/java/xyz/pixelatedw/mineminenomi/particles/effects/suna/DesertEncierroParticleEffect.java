/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.suna;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class DesertEncierroParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     double t = 0.0D;
/*    */     
/* 19 */     Random rand = world.rand;
/*    */     
/* 21 */     while (t < 1.0D) {
/*    */       
/* 23 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 25 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.09817477042468103D) {
/*    */         
/* 27 */         double x = t * Math.cos(theta);
/* 28 */         double z = t * Math.sin(theta);
/*    */         
/* 30 */         motionX = -x / 6.0D;
/* 31 */         motionY = -0.1D + rand.nextDouble() / 10.0D;
/* 32 */         motionZ = -z / 6.0D;
/*    */         
/* 34 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA);
/* 35 */         data.setLife(-1);
/* 36 */         data.setSize(3.3F);
/* 37 */         data.setMotion(motionX, motionY, motionZ);
/* 38 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.25D, posY + 1.2D, posZ + z * 1.25D);
/*    */         
/* 40 */         data = new GenericParticleData(ModParticleTypes.SUNA);
/* 41 */         data.setLife(-1);
/* 42 */         data.setSize(3.3F);
/* 43 */         data.setMotion(motionX, motionY, motionZ);
/* 44 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.25D, posY + 2.2D, posZ + z * 1.25D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\suna\DesertEncierroParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */