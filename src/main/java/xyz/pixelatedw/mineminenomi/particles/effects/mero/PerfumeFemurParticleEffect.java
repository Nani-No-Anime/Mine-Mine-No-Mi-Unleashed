/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.mero;
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
/*    */ public class PerfumeFemurParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 64; i++) {
/*    */       
/* 18 */       motionX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 19 */       motionY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 20 */       motionZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/*    */       
/* 22 */       double middlePoint = 0.1D;
/* 23 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 25 */       motionX *= middlePoint / 2.0D;
/* 26 */       motionY *= middlePoint / 2.0D;
/* 27 */       motionZ *= middlePoint / 2.0D;
/*    */       
/* 29 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.MERO);
/* 30 */       data.setLife(10);
/* 31 */       data.setSize(1.0F);
/* 32 */       data.setMotion(motionX, motionY, motionZ);
/* 33 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX, posY + 1.0D, posZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\mero\PerfumeFemurParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */