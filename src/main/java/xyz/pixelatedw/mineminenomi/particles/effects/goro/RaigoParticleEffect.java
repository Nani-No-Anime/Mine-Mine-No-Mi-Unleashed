/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.goro;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class RaigoParticleEffect
/*    */   extends ParticleEffect {
/* 13 */   public int range = 128;
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/*    */     int i;
/* 17 */     for (i = 0; i < this.range; i++) {
/*    */       
/* 19 */       double offsetX = WyHelper.randomWithRange(-this.range, this.range) + WyHelper.randomDouble();
/* 20 */       double offsetY = 40.0D + WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
/* 21 */       double offsetZ = WyHelper.randomWithRange(-this.range, this.range) + WyHelper.randomDouble();
/*    */       
/* 23 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO3);
/* 24 */       data.setLife(100);
/* 25 */       data.setSize(100.0F);
/* 26 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
/*    */       
/* 28 */       if (i % 2 == 0) {
/* 29 */         data.setColor(0.4F, 0.4F, 0.4F);
/*    */       } else {
/* 31 */         data.setColor(0.3F, 0.3F, 0.3F);
/*    */       } 
/*    */     } 
/* 34 */     for (i = 0; i < this.range / 2; i++) {
/*    */       
/* 36 */       double offsetX = WyHelper.randomWithRange(-this.range, this.range) + WyHelper.randomDouble();
/* 37 */       double offsetY = 30.0D + WyHelper.randomWithRange(-5, 0) + WyHelper.randomDouble();
/* 38 */       double offsetZ = WyHelper.randomWithRange(-this.range, this.range) + WyHelper.randomDouble();
/*    */       
/* 40 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO2);
/* 41 */       data.setLife(10);
/* 42 */       data.setSize(40.0F);
/* 43 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\goro\RaigoParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */