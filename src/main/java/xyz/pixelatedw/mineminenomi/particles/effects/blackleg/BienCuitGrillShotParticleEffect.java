/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.blackleg;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BienCuitGrillShotParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 15 */     for (int k = -2; k <= 2; k++) {
/*    */       
/* 17 */       for (int i = -10; i <= 10; i++) {
/*    */         
/* 19 */         for (int j = 0; j < 3; j++) {
/*    */           
/* 21 */           double offsetX = WyHelper.randomDouble() / 2.0D;
/* 22 */           double offsetY = WyHelper.randomDouble() / 2.0D;
/* 23 */           double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */           
/* 25 */           GenericParticleData genericParticleData = new GenericParticleData(ModParticleTypes.MERA);
/* 26 */           genericParticleData.setLife(10);
/* 27 */           genericParticleData.setSize(1.0F);
/* 28 */           genericParticleData.setMotion(motionX / 1.7D, motionY / 1.7D, motionZ / 1.7D);
/* 29 */           WyHelper.spawnParticles(genericParticleData, (ServerWorld)world, posX - (i / 5) + offsetX, posY + 1.5D + k / 1.2D + offsetY, posZ - (i / 5) + offsetZ);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 34 */     GenericParticleData data = new GenericParticleData(ModParticleTypes.GRILL);
/* 35 */     data.setLife(10);
/* 36 */     data.setSize(40.0F);
/* 37 */     data.setMotion(motionX / 1.4D, motionY / 1.4D, motionZ / 1.4D);
/* 38 */     WyHelper.spawnParticles(data, (ServerWorld)world, posX, posY + 1.5D, posZ);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\blackleg\BienCuitGrillShotParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */