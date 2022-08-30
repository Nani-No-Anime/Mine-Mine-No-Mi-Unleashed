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
/*    */ 
/*    */ public class FubukiParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     for (int i = 0; i < 1024; i++) {
/*    */       
/* 19 */       double offsetX = WyHelper.randomWithRange(-15, 15) + WyHelper.randomDouble();
/* 20 */       double offsetY = WyHelper.randomWithRange(0, 20) + WyHelper.randomDouble();
/* 21 */       double offsetZ = WyHelper.randomWithRange(-15, 15) + WyHelper.randomDouble();
/*    */       
/* 23 */       motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/* 24 */       motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/*    */       
/* 26 */       double middlePoint = 1.2D;
/*    */       
/* 28 */       motionX *= middlePoint / 25.0D;
/* 29 */       motionZ *= middlePoint / 25.0D;
/*    */       
/* 31 */       float scale = (float)(1.0D + WyHelper.randomWithRange(0, 3));
/*    */       
/* 33 */       ParticleType<GenericParticleData> particle = ModParticleTypes.YUKI;
/* 34 */       if (i % 5 == 0) {
/* 35 */         particle = ModParticleTypes.YUKI3;
/* 36 */       } else if (i % 2 == 0) {
/* 37 */         particle = ModParticleTypes.YUKI2;
/*    */       } else {
/* 39 */         particle = ModParticleTypes.YUKI;
/*    */       } 
/* 41 */       GenericParticleData data = new GenericParticleData(particle);
/* 42 */       data.setLife(300);
/* 43 */       data.setSize(scale);
/* 44 */       data.setMotion(motionX, -0.05D, motionY);
/* 45 */       data.setHasMotionDecay(false);
/* 46 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yuki\FubukiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */