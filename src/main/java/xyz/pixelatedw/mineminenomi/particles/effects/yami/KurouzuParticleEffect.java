/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.yami;
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
/*    */ public class KurouzuParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 15; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomDouble();
/* 19 */       double offsetY = WyHelper.randomDouble();
/* 20 */       double offsetZ = WyHelper.randomDouble();
/*    */       
/* 22 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.DARKNESS);
/* 23 */       data.setLife(1);
/* 24 */       data.setSize(15.0F);
/* 25 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + 0.5D + offsetX, posY + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yami\KurouzuParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */