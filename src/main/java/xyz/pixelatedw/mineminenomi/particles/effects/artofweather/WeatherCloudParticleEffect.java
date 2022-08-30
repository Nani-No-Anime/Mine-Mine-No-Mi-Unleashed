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
/*    */ public class WeatherCloudParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     int i = 0;
/* 18 */     double phi = 0.0D;
/* 19 */     double radius = 7.0D;
/*    */ 
/*    */     
/* 22 */     while (phi < Math.PI) {
/*    */       
/* 24 */       phi += 2.0943951023931953D;
/*    */       double theta;
/* 26 */       for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.7853981633974483D) {
/*    */         float red, green, blue; ParticleType<GenericParticleData> particle;
/* 28 */         double x = radius * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble() * radius;
/* 29 */         double y = radius * Math.cos(phi);
/* 30 */         double z = radius * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble() * radius;
/*    */ 
/*    */         
/* 33 */         if (i % 2 == 0) {
/*    */           
/* 35 */           red = 0.5F;
/* 36 */           green = 0.5F;
/* 37 */           blue = 0.5F;
/* 38 */           particle = ModParticleTypes.MOKU2;
/*    */         }
/*    */         else {
/*    */           
/* 42 */           red = 0.3F;
/* 43 */           green = 0.3F;
/* 44 */           blue = 0.3F;
/* 45 */           particle = ModParticleTypes.MOKU;
/*    */         } 
/*    */         
/* 48 */         GenericParticleData data = new GenericParticleData(particle);
/* 49 */         data.setLife((int)(50.0D + WyHelper.randomDouble()));
/* 50 */         data.setSize(25.0F);
/* 51 */         data.setColor(red, green, blue);
/*    */         
/* 53 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + y, posZ + z);
/* 54 */         i++;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\artofweather\WeatherCloudParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */