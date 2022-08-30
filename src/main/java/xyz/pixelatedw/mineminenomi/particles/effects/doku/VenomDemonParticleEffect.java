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
/*    */ public class VenomDemonParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 3; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/* 19 */       double offsetY = WyHelper.randomWithRange(-2, 0) + WyHelper.randomDouble();
/* 20 */       double offsetZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/*    */       
/* 22 */       int age = (int)(1.0D + WyHelper.randomWithRange(0, 4));
/* 23 */       motionY = 0.015D + Math.abs(WyHelper.randomDouble() / 8.0D);
/*    */       
/* 25 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.DOKU);
/* 26 */       data.setLife(age);
/* 27 */       data.setSize(0.8F);
/* 28 */       data.setColor(1.0F, 0.0F, 0.0F);
/* 29 */       data.setMotion(0.0D, motionY, 0.0D);
/* 30 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\doku\VenomDemonParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */