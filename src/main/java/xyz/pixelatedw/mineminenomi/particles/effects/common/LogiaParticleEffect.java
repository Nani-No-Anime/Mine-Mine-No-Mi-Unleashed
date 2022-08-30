/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.common;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class LogiaParticleEffect
/*    */   extends ParticleEffect {
/*    */   private ParticleType<GenericParticleData> particle;
/*    */   public boolean hideTooClose = false;
/* 15 */   public int ownerID = 0;
/*    */ 
/*    */   
/*    */   public LogiaParticleEffect(ParticleType<GenericParticleData> particle) {
/* 19 */     this.particle = particle;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 25 */     for (int i = 0; i < WyHelper.randomWithRange(3, 6); i++) {
/*    */       
/* 27 */       double offsetX = WyHelper.randomDouble() / 1.7D;
/* 28 */       double offsetY = 0.25D + WyHelper.randomDouble() / 3.0D;
/* 29 */       double offsetZ = WyHelper.randomDouble() / 1.7D;
/*    */       
/* 31 */       int age = (int)(1.0D + WyHelper.randomWithRange(0, 4));
/* 32 */       motionY = WyHelper.randomDouble() / 50.0D;
/* 33 */       if (motionY < 0.0D) {
/* 34 */         motionY = 0.005D;
/*    */       }
/* 36 */       GenericParticleData data = new GenericParticleData(this.particle);
/* 37 */       data.setLife(age);
/* 38 */       data.setSize(1.5F);
/* 39 */       if (this.hideTooClose)
/* 40 */         data.hideTooClose(); 
/* 41 */       data.setEntityID(this.ownerID);
/* 42 */       data.setMotion(0.0D, motionY, 0.0D);
/* 43 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\common\LogiaParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */