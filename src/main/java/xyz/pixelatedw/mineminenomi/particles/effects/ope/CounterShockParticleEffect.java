/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.ope;
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
/*    */ public class CounterShockParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 20; i++) {
/*    */       
/* 18 */       double x = WyHelper.randomDouble();
/* 19 */       double y = WyHelper.randomDouble();
/* 20 */       double z = WyHelper.randomDouble();
/*    */       
/* 22 */       motionY = 0.005D + Math.abs(WyHelper.randomDouble() / 8.0D);
/*    */       
/* 24 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO2);
/* 25 */       data.setLife(5);
/* 26 */       data.setSize(3.5F);
/* 27 */       data.setMotion(0.0D, motionY, 0.0D);
/* 28 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + 1.5D + y, posZ + z);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\ope\CounterShockParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */