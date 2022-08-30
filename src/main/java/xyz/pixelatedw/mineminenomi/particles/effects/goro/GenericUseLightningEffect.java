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
/*    */ public class GenericUseLightningEffect
/*    */   extends ParticleEffect {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 14 */     for (int i = 0; i < 25; i++) {
/*    */       
/* 16 */       double offsetX = WyHelper.randomDouble();
/* 17 */       double offsetY = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble() / 2.0D;
/* 18 */       double offsetZ = WyHelper.randomDouble();
/*    */       
/* 20 */       int age = (int)WyHelper.randomWithRange(2, 8);
/*    */       
/* 22 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO);
/* 23 */       data.setLife(age);
/* 24 */       data.setSize(0.4F);
/* 25 */       data.setMotion(motionX, 0.01D, motionZ);
/* 26 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\goro\GenericUseLightningEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */