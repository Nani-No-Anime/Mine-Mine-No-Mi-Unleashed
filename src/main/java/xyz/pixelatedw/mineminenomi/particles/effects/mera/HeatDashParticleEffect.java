/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.mera;
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
/*    */ public class HeatDashParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 40; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomDouble() / 1.3D;
/* 19 */       double offsetY = WyHelper.randomDouble() / 1.3D;
/* 20 */       double offsetZ = WyHelper.randomDouble() / 1.3D;
/*    */       
/* 22 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
/* 23 */       data.setLife(10);
/* 24 */       data.setSize(1.3F);
/* 25 */       data.setMotion(offsetX / 5.0D, offsetY / 5.0D + 0.05D, offsetZ / 5.0D);
/* 26 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\mera\HeatDashParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */