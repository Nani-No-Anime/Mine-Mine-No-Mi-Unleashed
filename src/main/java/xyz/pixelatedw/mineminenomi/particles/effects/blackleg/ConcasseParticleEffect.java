/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.blackleg;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class ConcasseParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     posY += 1.5D;
/*    */     
/* 18 */     double radius = 0.5D;
/* 19 */     double phi = 0.0D;
/*    */     
/* 21 */     while (phi < Math.PI) {
/*    */       
/* 23 */       phi += 1.5707963267948966D;
/*    */       double theta;
/* 25 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 1.5707963267948966D) {
/*    */         GenericParticleData data;
/* 27 */         double x = radius * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble() / 2.0D;
/* 28 */         double y = radius * Math.cos(phi) + WyHelper.randomDouble() / 2.0D;
/* 29 */         double z = radius * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble() / 2.0D;
/*    */ 
/*    */ 
/*    */         
/* 33 */         if (world.rand.nextInt(10) % 2 == 0) {
/* 34 */           data = new GenericParticleData(ModParticleTypes.MERA);
/*    */         } else {
/* 36 */           data = new GenericParticleData(ModParticleTypes.MERA2);
/* 37 */         }  data.setLife((int)WyHelper.randomWithRange(5, 10));
/* 38 */         data.setSize((float)WyHelper.randomWithRange(1, 3));
/* 39 */         data.setColor(1.0F, 1.0F, 1.0F, 0.5F);
/* 40 */         data.setMotion(motionX, motionY, motionZ);
/* 41 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + y, posZ + z);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\blackleg\ConcasseParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */