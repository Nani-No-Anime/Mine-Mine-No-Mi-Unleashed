/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.beta;
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
/*    */ public class NoseParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 10; i++) {
/*    */       
/* 18 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.BETA);
/* 19 */       data.setLife(20);
/* 20 */       data.setSize(0.6F);
/* 21 */       data.setMotion(motionX, motionY - 0.03D, motionZ);
/* 22 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX - 0.35D, posY + 1.45D, posZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\beta\NoseParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */