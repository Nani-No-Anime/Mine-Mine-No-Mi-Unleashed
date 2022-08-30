/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.yami;
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
/*    */ public class BlackWorldParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 500; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomWithRange(-10, 10) + WyHelper.randomDouble();
/* 19 */       double offsetY = WyHelper.randomWithRange(0, 2) + WyHelper.randomDouble();
/* 20 */       double offsetZ = WyHelper.randomWithRange(-10, 10) + WyHelper.randomDouble();
/*    */       
/* 22 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.DARKNESS);
/* 23 */       data.setLife(30 + world.rand.nextInt(10));
/* 24 */       data.setSize(1.2F);
/* 25 */       data.setMotion(0.0D, 0.02D, 0.0D);
/* 26 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
/*    */       
/* 28 */       data.setLife(30 + world.rand.nextInt(10));
/* 29 */       data.setSize(1.2F);
/* 30 */       data.setMotion(0.0D, 0.02D, 0.0D);
/* 31 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 0.5D + offsetY, posZ + offsetZ);
/*    */       
/* 33 */       data.setLife(30 + world.rand.nextInt(10));
/* 34 */       data.setSize(1.2F);
/* 35 */       data.setMotion(0.0D, 0.02D, 0.0D);
/* 36 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.5D + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yami\BlackWorldParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */