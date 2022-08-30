/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.extra;
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
/*    */ public class MH5GasParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 1024; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomWithRange(-100, 100) + WyHelper.randomDouble();
/* 19 */       double offsetY = WyHelper.randomWithRange(-10, 10) + WyHelper.randomDouble();
/* 20 */       double offsetZ = WyHelper.randomWithRange(-100, 100) + WyHelper.randomDouble();
/*    */       
/* 22 */       int age = (int)(3.0D + WyHelper.randomWithRange(0, 4));
/* 23 */       motionY = WyHelper.randomDouble() / 50.0D;
/* 24 */       if (motionY < 0.0D) {
/* 25 */         motionY = 0.02D;
/*    */       }
/* 27 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.DOKU);
/* 28 */       if (i % 2 == 0) {
/*    */         
/* 30 */         data = new GenericParticleData(ModParticleTypes.AWA_FOAM);
/* 31 */         data.setLife(age);
/* 32 */         data.setSize(1.5F);
/* 33 */         data.setMotion(0.0D, motionY / 2.0D, 0.0D);
/* 34 */         data.setColor(1.0F, 0.0F, 1.0F, 0.5F);
/* 35 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX / 2.0D, posY + 1.0D + offsetY / 2.0D, posZ + offsetZ / 2.0D);
/*    */       }
/*    */       else {
/*    */         
/* 39 */         data.setLife(age);
/* 40 */         data.setSize(1.5F);
/* 41 */         data.setMotion(0.0D, motionY / 2.0D, 0.0D);
/* 42 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX / 2.0D, posY + 1.0D + offsetY / 2.0D, posZ + offsetZ / 2.0D);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\extra\MH5GasParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */