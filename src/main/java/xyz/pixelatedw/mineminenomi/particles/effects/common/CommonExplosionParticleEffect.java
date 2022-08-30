/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.common;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class CommonExplosionParticleEffect
/*    */   extends ParticleEffect {
/*    */   private int explosionSize;
/*    */   
/*    */   public CommonExplosionParticleEffect() {
/* 15 */     this(2);
/*    */   }
/*    */ 
/*    */   
/*    */   public CommonExplosionParticleEffect(int explosionSize) {
/* 20 */     this.explosionSize = explosionSize;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 26 */     for (int i = 0; i < this.explosionSize * 2; i++) {
/*    */       
/* 28 */       double x = posX + WyHelper.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + WyHelper.randomDouble();
/* 29 */       double y = posY + WyHelper.randomDouble();
/* 30 */       double z = posZ + WyHelper.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + WyHelper.randomDouble();
/*    */       
/* 32 */       motionX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/* 33 */       motionY = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/* 34 */       motionZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/*    */       
/* 36 */       double middlePoint = 0.5D / ((5 / this.explosionSize) + 0.1D);
/* 37 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 39 */       motionX *= middlePoint / 2.0D;
/* 40 */       motionY *= middlePoint / 2.0D;
/* 41 */       motionZ *= middlePoint / 2.0D;
/*    */       
/* 43 */       WyHelper.spawnParticles(ParticleTypes.EXPLOSION, (ServerWorld)world, x, y + 1.0D, z);
/* 44 */       WyHelper.spawnParticles(ParticleTypes.POOF, (ServerWorld)world, posX, posY + 1.0D, posZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\common\CommonExplosionParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */