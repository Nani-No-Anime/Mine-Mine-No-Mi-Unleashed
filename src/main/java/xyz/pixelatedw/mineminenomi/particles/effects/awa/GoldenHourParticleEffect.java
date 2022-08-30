/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.awa;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GoldenHourParticleEffect
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
/* 26 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.AWA2);
/* 27 */       data.setLife(age);
/* 28 */       data.setSize(1.5F);
/* 29 */       data.setMotion(0.0D, motionY, 0.0D);
/* 30 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.25D + offsetY, posZ + offsetZ);
/*    */       
/* 32 */       data = new GenericParticleData(ModParticleTypes.AWA_FOAM);
/* 33 */       data.setLife(age);
/* 34 */       data.setSize(1.5F);
/* 35 */       data.setMotion(0.0D, motionY / 2.0D, 0.0D);
/* 36 */       data.setColor(1.0F, 1.0F, 0.0F, 0.5F);
/* 37 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX / 2.0D, posY + 1.0D + offsetY / 2.0D, posZ + offsetZ / 2.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\awa\GoldenHourParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */