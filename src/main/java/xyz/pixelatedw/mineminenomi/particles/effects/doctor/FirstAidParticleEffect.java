/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.doctor;
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
/*    */ public class FirstAidParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 45; i++) {
/*    */       GenericParticleData data;
/* 18 */       motionX = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
/* 19 */       motionY = WyHelper.randomWithRange(1, 2);
/* 20 */       motionZ = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
/*    */       
/* 22 */       double middlePoint = 0.1D;
/* 23 */       middlePoint *= (world.rand.nextFloat() * 2.0F + 0.3F);
/*    */       
/* 25 */       motionX *= middlePoint / 50.0D;
/* 26 */       motionY *= middlePoint / 2.0D;
/* 27 */       motionZ *= middlePoint / 50.0D;
/*    */       
/* 29 */       double offsetX = WyHelper.randomDouble() / 1.2D;
/* 30 */       double offsetZ = WyHelper.randomDouble() / 1.2D;
/*    */ 
/*    */ 
/*    */       
/* 34 */       if (i % 2 == 0) {
/* 35 */         data = new GenericParticleData(ModParticleTypes.CHIYU);
/*    */       } else {
/* 37 */         data = new GenericParticleData(ModParticleTypes.PIKA);
/*    */       } 
/* 39 */       data.setLife(10);
/* 40 */       data.setSize(1.5F);
/* 41 */       data.setColor(0.0F, 0.8F, 0.0F);
/* 42 */       data.setMotion(motionX, motionY, motionZ);
/* 43 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 0.5D, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\doctor\FirstAidParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */