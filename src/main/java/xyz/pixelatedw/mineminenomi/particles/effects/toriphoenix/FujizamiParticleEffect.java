/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class FujizamiParticleEffect extends ParticleEffect {
/* 12 */   public float multiplier = 1.0F;
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     double radius = 0.4D * this.multiplier;
/*    */     
/* 19 */     for (int i = 0; i < 6; i++) {
/*    */       
/* 21 */       double phi = 0.0D;
/*    */       
/* 23 */       while (phi < Math.PI) {
/*    */         
/* 25 */         phi += 0.39269908169872414D;
/*    */         double theta;
/* 27 */         for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.39269908169872414D) {
/*    */           
/* 29 */           double x = radius * Math.cos(theta) * Math.sin(phi);
/* 30 */           double y = radius * Math.cos(phi);
/* 31 */           double z = radius * Math.sin(theta) * Math.sin(phi);
/*    */           
/* 33 */           GenericParticleData data = new GenericParticleData(ModParticleTypes.BLUE_FLAME);
/* 34 */           data.setLife(20);
/* 35 */           data.setColor(1.0F, 1.0F, 1.0F, 0.3F);
/*    */           
/* 37 */           motionX = (WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble()) / 20.0D;
/* 38 */           motionZ = (WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble()) / 20.0D;
/*    */           
/* 40 */           data.setMotion(motionX, 0.009999999776482582D, motionZ);
/* 41 */           data.setSize(1.0F);
/* 42 */           WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + y, posZ + z);
/*    */         } 
/*    */       } 
/*    */       
/* 46 */       radius += 0.2D * this.multiplier;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\toriphoenix\FujizamiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */