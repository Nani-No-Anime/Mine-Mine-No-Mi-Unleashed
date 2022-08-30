/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.electro;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class ElectricalTempesta2ParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     double t = 0.0D;
/*    */     
/* 19 */     Random rand = world.rand;
/* 20 */     double size = 0.1D;
/*    */     
/* 22 */     while (t < 5.0D) {
/*    */       
/* 24 */       t += size * Math.PI;
/*    */       double theta;
/* 26 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/*    */         GenericParticleData data;
/* 28 */         double x = t * Math.cos(theta);
/* 29 */         double z = t * Math.sin(theta);
/*    */         
/* 31 */         motionX = x / 5.0D;
/* 32 */         motionY = 0.05D + rand.nextDouble() / 15.0D;
/* 33 */         motionZ = z / 5.0D;
/*    */ 
/*    */ 
/*    */         
/* 37 */         if (rand.nextInt(10) % 2 == 0) {
/* 38 */           data = new GenericParticleData(ModParticleTypes.GORO);
/*    */         } else {
/* 40 */           data = new GenericParticleData(ModParticleTypes.GORO2);
/* 41 */         }  data.setLife(0);
/* 42 */         data.setSize((float)WyHelper.randomWithRange(3, 5));
/* 43 */         data.setColor(1.0F, 1.0F, 1.0F, 0.8F);
/* 44 */         data.setMotion(motionX, motionY, motionZ);
/* 45 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x + WyHelper.randomDouble() / 2.0D, posY + 0.25D, posZ + z + WyHelper.randomDouble() / 2.0D);
/*    */       } 
/* 47 */       size += 0.2D;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\electro\ElectricalTempesta2ParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */