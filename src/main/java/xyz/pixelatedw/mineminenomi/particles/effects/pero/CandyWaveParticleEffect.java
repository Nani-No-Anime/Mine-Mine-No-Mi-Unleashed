/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.pero;
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
/*    */ public class CandyWaveParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 40; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomDouble() * 3.0D;
/* 19 */       double offsetZ = WyHelper.randomDouble() * 3.0D;
/*    */       
/* 21 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA2);
/* 22 */       data.setLife(8);
/* 23 */       data.setSize(1.4F);
/* 24 */       data.setMotion(0.0D, 0.2D + Math.abs(WyHelper.randomDouble()) / 3.0D, 0.0D);
/* 25 */       data.setColor(0.5F, 0.0F, 0.5F);
/* 26 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\pero\CandyWaveParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */