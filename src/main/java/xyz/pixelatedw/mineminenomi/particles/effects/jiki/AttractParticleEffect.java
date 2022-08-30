/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.jiki;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class AttractParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 15 */     for (int i = 0; i < WyHelper.randomWithRange(6, 9); i++) {
/*    */       
/* 17 */       double offsetX = WyHelper.randomDouble() / 1.7D;
/* 18 */       double offsetY = WyHelper.randomDouble() * 1.55D;
/* 19 */       double offsetZ = WyHelper.randomDouble() / 1.7D;
/*    */       
/* 21 */       int age = (int)(3.0D + WyHelper.randomWithRange(0, 4));
/* 22 */       motionY = WyHelper.randomDouble() / 50.0D;
/* 23 */       if (motionY < 0.0D) {
/* 24 */         motionY = 0.02D;
/*    */       }
/* 26 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.MAGNET);
/* 27 */       data.setLife(age);
/* 28 */       data.setSize(4.0F);
/* 29 */       data.setMotion(0.0D, motionY, 0.0D);
/* 30 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.25D + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\jiki\AttractParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */