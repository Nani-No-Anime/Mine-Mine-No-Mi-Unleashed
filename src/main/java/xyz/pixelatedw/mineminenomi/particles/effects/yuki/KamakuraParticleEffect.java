/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.yuki;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class KamakuraParticleEffect extends ParticleEffect {
/* 12 */   public int size = 4;
/* 13 */   public int life = 100;
/*    */ 
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     double radius = this.size * 1.25D;
/* 18 */     for (int i = 0; i < 10; i++) {
/*    */       
/* 20 */       double phi = 0.0D;
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
/* 31 */           ParticleType<GenericParticleData> particle = ModParticleTypes.YUKI;
/* 32 */           if (i % 5 == 0) {
/* 33 */             particle = ModParticleTypes.YUKI3;
/* 34 */           } else if (i % 2 == 0) {
/* 35 */             particle = ModParticleTypes.YUKI2;
/*    */           } 
/* 37 */           motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/* 38 */           motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/*    */           
/* 40 */           double middlePoint = 1.2D;
/*    */           
/* 42 */           motionX *= middlePoint / 25.0D;
/* 43 */           motionZ *= middlePoint / 25.0D;
/*    */           
/* 45 */           float scale = (float)(1.0D + WyHelper.randomWithRange(0, 3));
/* 46 */           GenericParticleData data = new GenericParticleData(particle);
/* 47 */           data.setLife(this.life);
/* 48 */           data.setMotion(motionX, -0.05D, motionZ);
/* 49 */           data.setSize(scale);
/* 50 */           WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + y, posZ + z);
/*    */         } 
/*    */       } 
/*    */       
/* 54 */       radius += 0.8D;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yuki\KamakuraParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */