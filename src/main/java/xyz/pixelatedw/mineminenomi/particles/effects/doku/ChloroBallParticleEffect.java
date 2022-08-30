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
/*    */ 
/*    */ public class ChloroBallParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public boolean venomDemon = false;
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 18 */     for (int i = 0; i < 12; i++) {
/*    */       
/* 20 */       motionX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 21 */       motionY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/* 22 */       motionZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
/*    */       
/* 24 */       double middlePoint = 0.25D;
/* 25 */       middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
/*    */       
/* 27 */       motionX *= middlePoint / 2.0D;
/* 28 */       motionY *= middlePoint / 2.0D;
/* 29 */       motionZ *= middlePoint / 2.0D;
/*    */       
/* 31 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.DOKU);
/*    */       
/* 33 */       if (this.venomDemon) {
/* 34 */         data.setColor(1.0F, 0.0F, 0.0F);
/*    */       }
/* 36 */       data.setLife(8);
/* 37 */       data.setSize(0.8F);
/* 38 */       data.setMotion(motionX, motionY, motionZ);
/* 39 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX, posY, posZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\doku\ChloroBallParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */