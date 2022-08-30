/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.doku;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ChloroBallCloudParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public boolean venomDemon = false;
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     for (int i = 0; i < 64; i++) {
/*    */       
/* 19 */       double offsetX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/* 20 */       double offsetY = WyHelper.randomWithRange(-1, 2) + WyHelper.randomDouble();
/* 21 */       double offsetZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
/*    */       
/* 23 */       motionX = WyHelper.randomDouble() / 8.0D;
/* 24 */       motionZ = WyHelper.randomDouble() / 8.0D;
/*    */       
/* 26 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.DOKU);
/*    */       
/* 28 */       if (this.venomDemon) {
/* 29 */         data.setColor(1.0F, 0.0F, 0.0F);
/*    */       }
/* 31 */       data.setLife(5);
/* 32 */       data.setSize(1.5F);
/* 33 */       data.setMotion(motionX, 0.05D, motionZ);
/* 34 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\doku\ChloroBallCloudParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */