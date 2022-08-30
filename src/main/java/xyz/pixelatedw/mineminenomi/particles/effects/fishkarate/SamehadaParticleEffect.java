/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.fishkarate;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ 
/*    */ public class SamehadaParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 14 */     posY += 1.5D;
/*    */     
/* 16 */     double radius = 1.0D;
/* 17 */     double phi = 0.0D;
/*    */     
/* 19 */     while (phi < Math.PI) {
/*    */       
/* 21 */       phi += 0.7853981633974483D;
/*    */       double theta;
/* 23 */       for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.5235987755982988D) {
/*    */         
/* 25 */         double x = radius * Math.cos(theta) * Math.sin(phi);
/* 26 */         double y = radius * Math.cos(phi);
/* 27 */         double z = radius * Math.sin(theta) * Math.sin(phi);
/*    */         
/* 29 */         ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.SPLASH, posX + x, posY + y, posZ + z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\fishkarate\SamehadaParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */