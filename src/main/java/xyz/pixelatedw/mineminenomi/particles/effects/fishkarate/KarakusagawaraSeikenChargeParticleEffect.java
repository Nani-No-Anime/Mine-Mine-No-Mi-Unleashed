/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.fishkarate;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class KarakusagawaraSeikenChargeParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 18 */     int i = 0;
/* 19 */     double t = 0.0D;
/*    */     
/* 21 */     Random rand = world.rand;
/*    */     
/* 23 */     while (t < 1.0D) {
/*    */       
/* 25 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 27 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/*    */         
/* 29 */         double x = t * Math.cos(theta);
/* 30 */         double y = rand.nextInt(1);
/* 31 */         double z = t * Math.sin(theta);
/*    */         
/* 33 */         motionX = -x / 20.0D;
/* 34 */         motionY = 0.05D + rand.nextDouble() / 10.0D;
/* 35 */         motionZ = -z / 20.0D;
/*    */         
/* 37 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.AWA);
/*    */         
/* 39 */         if (i % 2 == 0) {
/* 40 */           data = new GenericParticleData(ModParticleTypes.AWA);
/*    */         }
/* 42 */         double offsetX = world.rand.nextDouble() * WyHelper.randomWithRange(7, 9);
/* 43 */         double offsetY = 1.0D;
/* 44 */         double offsetZ = world.rand.nextDouble() * WyHelper.randomWithRange(7, 9);
/*    */         
/* 46 */         ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.DRIPPING_WATER, posX - 4.0D + offsetX, posY - 1.0D + offsetY, posZ - 4.0D + offsetZ, 1, 0.0D, 1.2D, 0.0D, 1.2D);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 52 */         data.setLife(1);
/* 53 */         data.setSize(1.0F);
/* 54 */         data.setMotion(motionX, motionY, motionZ);
/* 55 */         data.setColor(0.0F, 0.0F, 1.0F, 0.8F);
/* 56 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.25D + WyHelper.randomDouble() * 2.0D, posY + y, posZ + z * 1.25D + WyHelper.randomDouble() * 2.0D);
/*    */         
/* 58 */         i++;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\fishkarate\KarakusagawaraSeikenChargeParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */