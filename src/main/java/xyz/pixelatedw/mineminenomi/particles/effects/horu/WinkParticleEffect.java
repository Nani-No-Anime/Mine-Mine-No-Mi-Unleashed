/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.horu;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class WinkParticleEffect
/*    */   extends ParticleEffect {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 14 */     motionX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/* 15 */     motionY = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/* 16 */     motionZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/*    */     
/* 18 */     double middlePoint = 0.1D;
/* 19 */     middlePoint *= WyHelper.randomDouble() * 2.0D + 0.25D;
/*    */     
/* 21 */     motionX *= middlePoint / 5.0D;
/* 22 */     motionY *= middlePoint / 5.0D;
/* 23 */     motionZ *= middlePoint / 5.0D;
/*    */     
/* 25 */     GenericParticleData data = new GenericParticleData(ModParticleTypes.HORU);
/* 26 */     data.setLife(5);
/* 27 */     data.setSize(5.0F);
/* 28 */     data.setMotion(motionX, motionY, motionZ);
/* 29 */     WyHelper.spawnParticles(data, (ServerWorld)world, posX, posY + 1.5D, posZ);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\horu\WinkParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */