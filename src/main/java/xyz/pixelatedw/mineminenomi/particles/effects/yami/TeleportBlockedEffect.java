/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.yami;
/*    */ 
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class TeleportBlockedEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     for (int i = 0; i < 10; i++) {
/*    */       
/* 19 */       double offsetX = WyHelper.randomDouble() / 1.25D;
/* 20 */       double offsetY = WyHelper.randomDouble() * 1.25D;
/* 21 */       double offsetZ = WyHelper.randomDouble() / 1.25D;
/*    */       
/* 23 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.DARKNESS);
/* 24 */       data.setLife(5);
/* 25 */       data.setSize(4.0F);
/* 26 */       data.setRotation(Vector3f.YP);
/* 27 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yami\TeleportBlockedEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */