/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.kobu;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ShoureiParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 15 */     for (int i = 0; i < 20; i++) {
/*    */       
/* 17 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 18 */       double offsetY = WyHelper.randomDouble() / 2.0D;
/* 19 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */       
/* 21 */       GenericParticleData particleData = new GenericParticleData(ModParticleTypes.CHIYU);
/* 22 */       particleData.setLife(7);
/* 23 */       particleData.setSize(1.0F);
/* 24 */       double y = world.rand.nextDouble() / 15.0D;
/* 25 */       particleData.setMotion(0.0D, y, 0.0D);
/* 26 */       particleData.setColor(0.5F, 1.0F, 0.5F, 0.7F);
/* 27 */       WyHelper.spawnParticles(particleData, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\kobu\ShoureiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */