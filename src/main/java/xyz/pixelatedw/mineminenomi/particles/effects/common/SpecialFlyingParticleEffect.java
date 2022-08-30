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
/*    */ public class SpecialFlyingParticleEffect
/*    */   extends ParticleEffect {
/*    */   private ParticleType<GenericParticleData> particle;
/*    */   
/*    */   public SpecialFlyingParticleEffect(ParticleType<GenericParticleData> particle) {
/* 16 */     this.particle = particle;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 22 */     for (int i = 0; i < WyHelper.randomWithRange(5, 10); i++) {
/*    */       
/* 24 */       double offsetX = WyHelper.randomDouble() / 1.7D;
/* 25 */       double offsetY = -0.55D + WyHelper.randomDouble() / 3.0D;
/* 26 */       double offsetZ = WyHelper.randomDouble() / 1.7D;
/*    */       
/* 28 */       int age = (int)(1.0D + WyHelper.randomWithRange(0, 20));
/* 29 */       motionY = WyHelper.randomDouble() / 10.0D * -1.0D;
/* 30 */       if (motionY > 0.0D) {
/* 31 */         motionY = -0.005D;
/*    */       }
/* 33 */       GenericParticleData data = new GenericParticleData(this.particle);
/* 34 */       data.setLife(age);
/* 35 */       data.setSize(2.5F);
/* 36 */       data.setMotion(0.0D, motionY, 0.0D);
/* 37 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\common\SpecialFlyingParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */