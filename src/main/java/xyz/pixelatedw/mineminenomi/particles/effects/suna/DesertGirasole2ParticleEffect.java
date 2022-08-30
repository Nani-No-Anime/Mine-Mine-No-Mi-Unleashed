/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.suna;
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
/*    */ public class DesertGirasole2ParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     double t = 0.0D;
/*    */ 
/*    */     
/* 19 */     while (t < 1.0D) {
/*    */       
/* 21 */       t += 0.3141592653589793D;
/*    */       double theta;
/* 23 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/*    */         
/* 25 */         double x = t * Math.cos(theta);
/* 26 */         double y = WyHelper.randomDouble();
/* 27 */         double z = t * Math.sin(theta);
/*    */         
/* 29 */         motionX = WyHelper.randomDouble() / 2.0D;
/* 30 */         motionY = 0.0D;
/* 31 */         motionZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 33 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA);
/* 34 */         data.setLife(35);
/* 35 */         data.setSize(3.0F);
/* 36 */         data.setMotion(motionX, motionY, motionZ);
/* 37 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 2.25D, posY + 0.5D + y, posZ + z * 2.25D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\suna\DesertGirasole2ParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */