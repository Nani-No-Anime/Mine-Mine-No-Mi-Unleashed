/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.swordsman;
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
/*    */ public class HiryuKaenParticleEffect
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
/*    */         GenericParticleData data;
/* 27 */         double x = t * Math.cos(theta);
/* 28 */         double z = t * Math.sin(theta);
/*    */         
/* 30 */         motionX = x / 5.0D;
/* 31 */         motionY = 0.01D + rand.nextDouble() / 4.0D;
/* 32 */         motionZ = z / 5.0D;
/*    */ 
/*    */ 
/*    */         
/* 36 */         if (rand.nextInt(10) % 2 == 0) {
/* 37 */           data = new GenericParticleData(ModParticleTypes.MERA);
/*    */         } else {
/* 39 */           data = new GenericParticleData(ModParticleTypes.MERA2);
/* 40 */         }  data.setLife(5);
/* 41 */         data.setSize(2.0F);
/* 42 */         data.setColor(1.0F, 1.0F, 1.0F, 0.5F);
/* 43 */         data.setMotion(motionX, motionY, motionZ);
/* 44 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 0.85D, posY + 1.2D, posZ + z * 0.85D);
/* 45 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 0.45D, posY + 1.8D, posZ + z * 0.45D);
/* 46 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 0.85D, posY + 2.2D, posZ + z * 0.85D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\swordsman\HiryuKaenParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */