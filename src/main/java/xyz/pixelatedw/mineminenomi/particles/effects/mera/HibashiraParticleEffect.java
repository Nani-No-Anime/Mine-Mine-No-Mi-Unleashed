/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.mera;
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
/*    */ public class HibashiraParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     double t = 0.0D;
/*    */     
/* 19 */     Random rand = world.rand;
/* 20 */     double size = 2.5D;
/*    */     
/* 22 */     while (t < 1.0D) {
/*    */       
/* 24 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 26 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/*    */         GenericParticleData data;
/* 28 */         double x = t * Math.cos(theta);
/* 29 */         double z = t * Math.sin(theta);
/*    */         
/* 31 */         motionX = Math.sin(theta) / 10.0D;
/* 32 */         motionY = 0.3D + rand.nextDouble() / 10.0D;
/* 33 */         motionZ = -Math.cos(theta) / 10.0D;
/*    */ 
/*    */ 
/*    */         
/* 37 */         if (rand.nextInt(10) % 2 == 0) {
/* 38 */           data = new GenericParticleData(ModParticleTypes.MERA);
/*    */         } else {
/* 40 */           data = new GenericParticleData(ModParticleTypes.MERA2);
/* 41 */         }  data.setLife((int)WyHelper.randomWithRange(5, 20));
/* 42 */         data.setSize((float)WyHelper.randomWithRange(3, 5));
/* 43 */         data.setColor(1.0F, 1.0F, 1.0F, 0.5F);
/* 44 */         data.setMotion(motionX, motionY, motionZ);
/* 45 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * size + WyHelper.randomDouble() / 2.0D, posY - 10.0D, posZ + z * size + WyHelper.randomDouble() / 2.0D);
/* 46 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * size + WyHelper.randomDouble() / 2.0D, posY - 5.0D, posZ + z * size + WyHelper.randomDouble() / 2.0D);
/* 47 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * size + WyHelper.randomDouble() / 2.0D, posY, posZ + z * size + WyHelper.randomDouble() / 2.0D);
/* 48 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * size + WyHelper.randomDouble() / 2.0D, posY + 5.0D + WyHelper.randomWithRange(-2, 0), posZ + z * size + WyHelper.randomDouble() / 2.0D);
/* 49 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * size + WyHelper.randomDouble() / 2.0D, posY + 10.0D + WyHelper.randomWithRange(-2, 0), posZ + z * size + WyHelper.randomDouble() / 2.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\mera\HibashiraParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */