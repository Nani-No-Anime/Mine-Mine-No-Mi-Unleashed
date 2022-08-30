/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.gasu;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GastanetParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 15 */     for (int i = 0; i < 228; i++) {
/*    */       GenericParticleData data;
/* 17 */       double offsetX = WyHelper.randomDouble();
/* 18 */       double offsetY = WyHelper.randomDouble();
/* 19 */       double offsetZ = WyHelper.randomDouble();
/*    */       
/* 21 */       motionX = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
/* 22 */       motionY = WyHelper.randomWithRange(0, 5) + WyHelper.randomDouble();
/* 23 */       motionZ = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
/*    */       
/* 25 */       double middlePoint = 2.8D;
/*    */       
/* 27 */       motionX *= middlePoint / 15.0D;
/* 28 */       motionY *= middlePoint / 25.0D;
/* 29 */       motionZ *= middlePoint / 15.0D;
/*    */       
/* 31 */       motionY = Math.abs(motionY);
/*    */ 
/*    */       
/* 34 */       if (i % 4 == 0) {
/* 35 */         data = new GenericParticleData(ModParticleTypes.GASU);
/*    */       } else {
/* 37 */         data = new GenericParticleData(ModParticleTypes.GASU2);
/*    */       } 
/* 39 */       data.setLife(7);
/* 40 */       data.setSize(1.5F);
/* 41 */       data.setMotion(motionX, motionY, motionZ);
/* 42 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 0.25D + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\gasu\GastanetParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */