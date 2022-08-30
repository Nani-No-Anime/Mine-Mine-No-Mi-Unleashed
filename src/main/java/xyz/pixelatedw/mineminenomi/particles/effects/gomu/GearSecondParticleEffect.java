/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.gomu;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class GearSecondParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 15 */     for (int i = 0; i < 2; i++) {
/*    */       
/* 17 */       double offsetX = WyHelper.randomDouble() / 1.25D;
/* 18 */       double offsetY = WyHelper.randomDouble() / 1.25D;
/* 19 */       double offsetZ = WyHelper.randomDouble() / 1.25D;
/*    */       
/* 21 */       ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.POOF, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 1, motionX, motionY, motionZ, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\gomu\GearSecondParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */