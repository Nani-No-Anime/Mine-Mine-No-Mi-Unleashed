/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.goro;
/*    */ 
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class KariParticleEffect
/*    */   extends ParticleEffect {
/*    */   private int range;
/*    */   private float size;
/*    */   
/*    */   public KariParticleEffect(int range) {
/* 18 */     this.range = range;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 24 */     for (int i = 0; i < 16 * this.range; i++) {
/*    */       
/* 26 */       double offsetX = WyHelper.randomWithRange(-this.range, this.range) + WyHelper.randomDouble();
/* 27 */       double offsetY = WyHelper.randomWithRange(-this.range, 2 + this.range) + WyHelper.randomDouble();
/* 28 */       double offsetZ = WyHelper.randomWithRange(-this.range, this.range) + WyHelper.randomDouble();
/*    */       
/* 30 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO2);
/* 31 */       data.setLife(5);
/* 32 */       data.setSize(this.size);
/* 33 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
/*    */       
/* 35 */       GenericParticleData data2 = new GenericParticleData(ModParticleTypes.GORO);
/* 36 */       data2.setLife(5);
/* 37 */       data2.setSize(this.size);
/* 38 */       data.setRotation(Vector3f.YP);
/* 39 */       WyHelper.spawnParticles(data2, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRange(int range) {
/* 45 */     this.range = range;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSize(float range) {
/* 50 */     this.size = range;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\goro\KariParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */