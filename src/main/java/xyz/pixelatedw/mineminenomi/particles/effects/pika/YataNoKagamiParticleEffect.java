/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.pika;
/*    */ 
/*    */ import java.util.Random;
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
/*    */ 
/*    */ public class YataNoKagamiParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 19 */     for (int i = 0; i < 20; i++) {
/*    */       
/* 21 */       double offsetX = ((new Random()).nextInt(40) + 1.0D - 20.0D) / 20.0D;
/* 22 */       double offsetY = ((new Random()).nextInt(40) + 1.0D) / 20.0D;
/* 23 */       double offsetZ = ((new Random()).nextInt(40) + 1.0D - 20.0D) / 20.0D;
/*    */       
/* 25 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.PIKA);
/* 26 */       data.setLife(20);
/* 27 */       data.setSize(4.0F);
/* 28 */       data.setRotation(Vector3f.YP);
/* 29 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 0.5D + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\pika\YataNoKagamiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */