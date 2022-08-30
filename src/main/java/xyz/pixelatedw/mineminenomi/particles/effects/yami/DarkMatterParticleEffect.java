/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.yami;
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
/*    */ 
/*    */ public class DarkMatterParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 18 */     double t = 0.0D;
/*    */     
/* 20 */     Random rand = world.rand;
/*    */     
/* 22 */     while (t < 1.0D) {
/*    */       
/* 24 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 26 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.09817477042468103D) {
/*    */         
/* 28 */         double x = t * Math.cos(theta);
/* 29 */         double z = t * Math.sin(theta);
/*    */         
/* 31 */         motionX = -x / 3.0D;
/* 32 */         motionY = -0.1D + rand.nextDouble() / 10.0D;
/* 33 */         motionZ = -z / 3.0D;
/*    */         
/* 35 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.DARKNESS);
/* 36 */         data.setLife(10);
/* 37 */         data.setSize(3.3F);
/* 38 */         data.setMotion(motionX, motionY, motionZ);
/* 39 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 5.25D, posY + 1.2D, posZ + z * 5.25D);
/*    */         
/* 41 */         data.setLife(10);
/* 42 */         data.setSize(3.3F);
/* 43 */         data.setMotion(motionX, motionY, motionZ);
/* 44 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 5.25D, posY + 3.2D, posZ + z * 5.25D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yami\DarkMatterParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */