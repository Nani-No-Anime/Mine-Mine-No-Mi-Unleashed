/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.mero;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SlaveArrowParticleEffect extends ParticleEffect {
/* 12 */   private float size = 1.0F;
/*    */ 
/*    */   
/*    */   public void setSize(float size) {
/* 16 */     this.size = size;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getSize() {
/* 21 */     return this.size;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 27 */     GenericParticleData data = new GenericParticleData(ModParticleTypes.MERO);
/* 28 */     data.setLife(-5);
/* 29 */     data.setSize(this.size);
/* 30 */     data.setColor(1.0F, 1.0F, 1.0F, 0.7F);
/* 31 */     data.setMotion(motionX, motionY, motionZ);
/* 32 */     WyHelper.spawnParticles(data, (ServerWorld)world, posX, posY + 1.5D, posZ);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\mero\SlaveArrowParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */