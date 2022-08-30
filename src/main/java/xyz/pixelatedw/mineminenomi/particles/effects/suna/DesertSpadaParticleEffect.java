/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.suna;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DesertSpadaParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 15 */     for (int i = 0; i < 70; i++) {
/*    */       
/* 17 */       double offsetX = WyHelper.randomDouble() * 0.5D;
/* 18 */       double offsetZ = WyHelper.randomDouble() * 0.5D;
/*    */       
/* 20 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA2);
/* 21 */       data.setLife(20);
/* 22 */       data.setSize(1.5F);
/* 23 */       data.setMotion(motionX, motionY + 0.25D + Math.abs(WyHelper.randomDouble()) / 3.0D, motionZ);
/* 24 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY, posZ + offsetZ);
/* 25 */       data.setLife(60);
/* 26 */       data.setMotion(0.0D, 0.25D + Math.abs(WyHelper.randomDouble()) / 4.0D, 0.0D);
/* 27 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\suna\DesertSpadaParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */