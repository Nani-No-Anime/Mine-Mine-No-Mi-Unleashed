/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.hana;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BloomParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 15 */     for (int i = 0; i < 15; i++) {
/*    */       
/* 17 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 18 */       double offsetY = WyHelper.randomDouble() / 2.0D;
/* 19 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */       
/* 21 */       motionX = WyHelper.randomDouble();
/* 22 */       motionY = WyHelper.randomDouble();
/* 23 */       motionZ = WyHelper.randomDouble();
/*    */       
/* 25 */       double middlePoint = 0.15D;
/* 26 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 28 */       motionX *= middlePoint / 5.0D;
/* 29 */       motionY *= middlePoint / 8.0D;
/* 30 */       motionZ *= middlePoint / 5.0D;
/*    */       
/* 32 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.HANA);
/* 33 */       data.setLife(20);
/* 34 */       data.setSize(3.0F);
/* 35 */       data.setMotion(Math.sin(motionX), motionY - 0.015D, Math.sin(motionZ));
/* 36 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY + 1.25D, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\hana\BloomParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */