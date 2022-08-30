/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.common;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class WaterExplosionParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 15 */     for (int i = 0; i < 12; i++) {
/*    */       
/* 17 */       motionX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 18 */       motionY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 19 */       motionZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/*    */       
/* 21 */       double middlePoint = 0.25D;
/* 22 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 24 */       motionX *= middlePoint / 2.0D;
/* 25 */       motionY *= middlePoint / 2.0D;
/* 26 */       motionZ *= middlePoint / 2.0D;
/*    */       
/* 28 */       ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.FISHING, posX, posY, posZ, 1, motionX, motionY, motionZ, 0.1D);
/* 29 */       ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.FISHING, posX, posY, posZ, 1, motionX, motionY, motionZ, 0.1D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\common\WaterExplosionParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */