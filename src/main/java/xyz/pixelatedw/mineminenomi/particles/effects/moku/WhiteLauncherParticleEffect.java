/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.moku;
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
/*    */ public class WhiteLauncherParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 20; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomDouble() / 5.0D;
/* 19 */       double offsetY = WyHelper.randomDouble() / 5.0D;
/* 20 */       double offsetZ = WyHelper.randomDouble() / 5.0D;
/*    */       
/* 22 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
/* 23 */       data.setLife(20);
/* 24 */       data.setSize(1.3F);
/* 25 */       data.setMotion(0.0D, 0.05D, 0.0D);
/* 26 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.5D + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\moku\WhiteLauncherParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */