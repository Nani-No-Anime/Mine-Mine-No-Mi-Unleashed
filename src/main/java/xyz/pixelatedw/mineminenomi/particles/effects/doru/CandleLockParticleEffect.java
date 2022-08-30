/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.doru;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class CandleLockParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     for (int i = 0; i < 40; i++) {
/*    */       
/* 19 */       double offsetX = WyHelper.randomDouble() * 2.0D;
/* 20 */       double offsetZ = WyHelper.randomDouble() * 2.0D;
/*    */       
/* 22 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
/* 23 */       data.setLife(8);
/* 24 */       data.setSize(1.4F);
/* 25 */       data.setMotion(0.0D, 0.2D + Math.abs(WyHelper.randomDouble()) / 3.0D, 0.0D);
/* 26 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY, posZ + offsetZ);
/*    */       
/* 28 */       if (i % 5 == 0)
/* 29 */         ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.END_ROD, posX + offsetX, posY, posZ + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.2D); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\doru\CandleLockParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */