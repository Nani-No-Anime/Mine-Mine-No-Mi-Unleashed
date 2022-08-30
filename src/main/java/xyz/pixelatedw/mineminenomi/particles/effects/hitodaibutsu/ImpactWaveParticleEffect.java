/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.hitodaibutsu;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ImpactWaveParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 15 */     double radius = 1.0D;
/*    */     
/* 17 */     for (int i = 0; i < 10; i++) {
/*    */       
/* 19 */       double phi = 0.0D;
/*    */       
/* 21 */       while (phi < Math.PI) {
/*    */         
/* 23 */         phi += 0.19634954084936207D;
/*    */         double theta;
/* 25 */         for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.19634954084936207D) {
/*    */           
/* 27 */           double x = radius * Math.cos(theta) * Math.sin(phi);
/* 28 */           double y = radius * Math.cos(phi);
/* 29 */           double z = radius * Math.sin(theta) * Math.sin(phi);
/*    */           
/* 31 */           GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA2);
/* 32 */           data.setLife(50);
/*    */           
/* 34 */           motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/* 35 */           motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/*    */           
/* 37 */           double middlePoint = 1.2D;
/*    */           
/* 39 */           motionX *= middlePoint / 25.0D;
/* 40 */           motionZ *= middlePoint / 25.0D;
/* 41 */           data.setMotion(motionX, 0.10000000149011612D, motionZ);
/* 42 */           data.setSize(10.0F);
/* 43 */           data.setColor(1.0F, 1.0F, 0.2F, 0.5F);
/* 44 */           WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + y, posZ + z);
/*    */         } 
/*    */       } 
/*    */       
/* 48 */       radius += 0.8D;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\hitodaibutsu\ImpactWaveParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */