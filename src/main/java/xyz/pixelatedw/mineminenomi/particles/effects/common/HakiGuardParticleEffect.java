/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.common;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HakiGuardParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 14 */     for (int i = 0; i < 25; i++) {
/*    */       
/* 16 */       double x = posX + WyHelper.randomDouble() / 1.2D;
/* 17 */       double y = posY + WyHelper.randomDouble() / 1.2D;
/* 18 */       double z = posZ + WyHelper.randomDouble() / 1.2D;
/*    */       
/* 20 */       if (i % 2 == 0)
/* 21 */         WyHelper.spawnParticles(ParticleTypes.CRIT, (ServerWorld)world, x, y + 1.0D, z); 
/* 22 */       WyHelper.spawnParticles(ParticleTypes.WITCH, (ServerWorld)world, x, y + 1.0D, z);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\common\HakiGuardParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */