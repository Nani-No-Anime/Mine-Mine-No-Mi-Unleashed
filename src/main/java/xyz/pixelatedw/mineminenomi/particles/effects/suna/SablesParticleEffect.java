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
/*    */ public class SablesParticleEffect extends ParticleEffect {
/* 12 */   public float mult = 10.0F;
/* 13 */   int angle = 0;
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 18 */     int maxHeight = 15 + (int)(10.0F * this.mult);
/* 19 */     double minRadius = 0.4D + 1.2D * this.mult;
/* 20 */     double maxRadius = (1.0F + 3.0F * this.mult);
/* 21 */     int lines = 12;
/* 22 */     double heightIncrease = 0.15D;
/* 23 */     double radiusIncrement = maxRadius / maxHeight * (1.0F - this.mult / 2.0F);
/* 24 */     for (int l = 0; l < lines; l++) {
/*    */       double y;
/* 26 */       for (y = 0.0D; y < maxHeight; y += heightIncrease) {
/*    */         
/* 28 */         double radius = y * radiusIncrement;
/* 29 */         double v = (360.0F / lines * l) + y * 25.0D;
/* 30 */         double x = Math.cos(Math.toRadians(v - this.angle)) * Math.max(radius, minRadius);
/* 31 */         double z = Math.sin(Math.toRadians(v - this.angle)) * Math.max(radius, minRadius);
/* 32 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA2);
/* 33 */         data.setLife(10);
/* 34 */         data.setSize(0.25F + 1.5F * this.mult);
/* 35 */         data.setMotion(motionX, motionY + 0.1D - Math.abs(WyHelper.randomDouble() / 5.0D), motionZ);
/* 36 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + y, posZ + z);
/*    */       } 
/*    */     } 
/* 39 */     this.angle += 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\suna\SablesParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */