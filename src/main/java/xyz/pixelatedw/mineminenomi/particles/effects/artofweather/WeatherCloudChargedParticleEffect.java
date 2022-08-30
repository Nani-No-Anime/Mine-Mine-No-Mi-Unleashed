/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.artofweather;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class WeatherCloudChargedParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     int i = 0;
/* 18 */     double phi = 0.0D;
/* 19 */     double radius = 7.0D;
/*    */     
/* 21 */     while (phi < Math.PI) {
/*    */       
/* 23 */       phi += 2.0943951023931953D;
/*    */       double theta;
/* 25 */       for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.7853981633974483D) {
/*    */         ParticleType<GenericParticleData> particle;
/* 27 */         double x = radius * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble() * radius;
/* 28 */         double y = radius * Math.cos(phi);
/* 29 */         double z = radius * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble() * radius;
/*    */ 
/*    */         
/* 32 */         if (i % 4 == 0) {
/* 33 */           particle = ModParticleTypes.GORO_YELLOW;
/*    */         } else {
/* 35 */           particle = ModParticleTypes.GORO2_YELLOW;
/*    */         } 
/* 37 */         GenericParticleData data = new GenericParticleData(particle);
/* 38 */         data.setLife(10);
/* 39 */         data.setSize(6.0F);
/* 40 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY - 2.0D + y, posZ + z);
/* 41 */         i++;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\artofweather\WeatherCloudChargedParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */