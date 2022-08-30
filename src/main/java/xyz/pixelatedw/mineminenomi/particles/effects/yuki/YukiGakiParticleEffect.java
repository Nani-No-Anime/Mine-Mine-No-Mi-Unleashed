/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.yuki;
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
/*    */ public class YukiGakiParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 100; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/* 19 */       double offsetY = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
/* 20 */       double offsetZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/*    */       
/* 22 */       motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/* 23 */       motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/*    */       
/* 25 */       double middlePoint = 1.2D;
/*    */       
/* 27 */       motionX *= middlePoint / 25.0D;
/* 28 */       motionZ *= middlePoint / 25.0D;
/*    */       
/* 30 */       float scale = (float)(1.0D + WyHelper.randomWithRange(0, 3));
/*    */       
/* 32 */       ParticleType<GenericParticleData> particle = ModParticleTypes.YUKI;
/* 33 */       if (i % 5 == 0) {
/* 34 */         particle = ModParticleTypes.YUKI3;
/* 35 */       } else if (i % 2 == 0) {
/* 36 */         particle = ModParticleTypes.YUKI2;
/*    */       } 
/* 38 */       GenericParticleData data = new GenericParticleData(particle);
/* 39 */       data.setLife(200);
/* 40 */       data.setSize(scale);
/* 41 */       data.setMotion(motionX, -0.05D, motionZ);
/* 42 */       data.setHasMotionDecay(false);
/* 43 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yuki\YukiGakiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */