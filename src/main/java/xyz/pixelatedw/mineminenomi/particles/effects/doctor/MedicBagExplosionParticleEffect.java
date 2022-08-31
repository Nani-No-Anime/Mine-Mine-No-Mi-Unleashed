/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.doctor;
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
/*    */ public class MedicBagExplosionParticleEffect
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
/* 29 */         double y = rand.nextInt(1);
/* 30 */         double z = t * Math.sin(theta);
/*    */         
/* 32 */         motionX = x / 4.0D;
/* 33 */         motionY = 0.05D + rand.nextDouble() / 7.0D;
/* 34 */         motionZ = z / 4.0D;
/*    */         
/* 36 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MEDIC);
/* 37 */         data.setLife(4);
/* 38 */         data.setSize(2.0F);
/* 39 */         data.setMotion(motionX, motionY, motionZ);
/* 40 */         data.setColor(0.0F, 0.8F, 0.0F);
/* 41 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 0.75D + WyHelper.randomDouble(), posY + y, posZ + z * 0.75D + WyHelper.randomDouble());
/*    */         
/* 43 */         data = new GenericParticleData(ModParticleTypes.MEDIC);
/* 44 */         data.setLife(7);
/* 45 */         data.setSize(2.5F);
/* 46 */         data.setMotion(motionX, motionY, motionZ);
/* 47 */         data.setColor(0.0F, 0.8F, 0.0F);
/* 48 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 2.0D + WyHelper.randomDouble(), posY + y, posZ + z * 2.0D + WyHelper.randomDouble());
/*    */         
/* 50 */         data = new GenericParticleData(ModParticleTypes.MEDIC);
/* 51 */         data.setLife(10);
/* 52 */         data.setSize(4.5F);
/* 53 */         data.setMotion(motionX, motionY * 2.25D, motionZ);
/* 54 */         data.setColor(0.0F, 0.8F, 0.0F);
/* 55 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 3.25D + WyHelper.randomDouble(), posY + y, posZ + z * 3.25D + WyHelper.randomDouble());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\doctor\MedicBagExplosionParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */