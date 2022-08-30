/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.kachi;
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
/*    */ public class EvaporateParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 3; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomDouble();
/* 19 */       double offsetY = WyHelper.randomDouble();
/* 20 */       double offsetZ = WyHelper.randomDouble();
/*    */       
/* 22 */       motionX = WyHelper.randomWithRange(0, 1) + WyHelper.randomDouble();
/* 23 */       motionY = WyHelper.randomWithRange(0, 1) + WyHelper.randomDouble();
/* 24 */       motionZ = WyHelper.randomWithRange(0, 1) + WyHelper.randomDouble();
/*    */       
/* 26 */       double middlePoint = 0.05D;
/* 27 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 29 */       motionX *= middlePoint / 2.0D;
/* 30 */       motionY *= middlePoint / 2.0D;
/* 31 */       motionZ *= middlePoint / 2.0D;
/*    */       
/* 33 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
/* 34 */       data.setLife(10);
/* 35 */       data.setSize(2.5F);
/* 36 */       data.setMotion(motionX, motionY + 0.05D, motionZ);
/* 37 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + 1.5D + offsetX, posY + offsetY, posZ + offsetZ);
/*    */       
/* 39 */       offsetX = WyHelper.randomDouble();
/* 40 */       offsetY = WyHelper.randomDouble();
/* 41 */       offsetZ = WyHelper.randomDouble();
/*    */       
/* 43 */       data = new GenericParticleData(ModParticleTypes.MERA);
/* 44 */       data.setLife(10);
/* 45 */       data.setSize(2.5F);
/* 46 */       data.setMotion(motionX, motionY + 0.05D, motionZ);
/* 47 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + 1.5D + offsetX, posY + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\kachi\EvaporateParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */