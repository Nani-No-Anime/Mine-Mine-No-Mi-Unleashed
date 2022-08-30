/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.goro;
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
/*    */ public class DeathpiaCloudParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     int range = 128;
/* 18 */     for (int i = 0; i < range; i++) {
/*    */       ParticleType<GenericParticleData> particle;
/* 20 */       double offsetX = WyHelper.randomWithRange(-range, range) + WyHelper.randomDouble();
/* 21 */       double offsetY = world.getWorldInfo().getGenerator().getCloudHeight() + WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
/* 22 */       double offsetZ = WyHelper.randomWithRange(-range, range) + WyHelper.randomDouble();
/*    */       
/* 24 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO3);
/* 25 */       data.setLife(40);
/* 26 */       data.setSize(80.0F);
/* 27 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
/*    */ 
/*    */       
/* 30 */       if (i % 4 == 0) {
/* 31 */         particle = ModParticleTypes.GORO;
/*    */       } else {
/* 33 */         particle = ModParticleTypes.GORO2;
/*    */       } 
/* 35 */       GenericParticleData d = new GenericParticleData(particle);
/* 36 */       d.setLife(10);
/* 37 */       d.setSize(6.0F);
/* 38 */       WyHelper.spawnParticles(d, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\goro\DeathpiaCloudParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */