/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.sniper;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class KemuriBoshiParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 15 */     for (int i = 0; i < 512; i++) {
/*    */       
/* 17 */       double offsetX = WyHelper.randomWithRange(-4, 4) + WyHelper.randomDouble();
/* 18 */       double offsetY = WyHelper.randomWithRange(-2, 3) + WyHelper.randomDouble();
/* 19 */       double offsetZ = WyHelper.randomWithRange(-4, 4) + WyHelper.randomDouble();
/*    */       
/* 21 */       if (i % 2 == 0) {
/* 22 */         ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.CLOUD, posX + offsetX + WyHelper.randomWithRange(-7, 7), posY + 0.5D + offsetY + WyHelper.randomWithRange(-1, 3), posZ + offsetZ + WyHelper.randomWithRange(-7, 7), 1, 0.0D, 0.0D, 0.0D, 0.05D);
/*    */       } else {
/* 24 */         ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.POOF, posX + offsetX + WyHelper.randomWithRange(-7, 7), posY + 0.5D + offsetY + WyHelper.randomWithRange(-1, 3), posZ + offsetZ + WyHelper.randomWithRange(-7, 7), 1, 0.0D, 0.0D, 0.0D, 0.05D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\sniper\KemuriBoshiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */