/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.moku;
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
/*    */ 
/*    */ public class WhiteStrikeParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 18 */     double t = 0.0D;
/*    */     
/* 20 */     Random rand = world.rand;
/*    */     
/* 22 */     while (t < 1.0D) {
/*    */       
/* 24 */       t += 1.5707963267948966D;
/*    */       double theta;
/* 26 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/*    */         
/* 28 */         double x = t * Math.cos(theta);
/* 29 */         double y = rand.nextInt(1);
/* 30 */         double z = t * Math.sin(theta);
/*    */         
/* 32 */         motionX = x / 7.0D;
/* 33 */         motionY = 0.02D + rand.nextDouble() / 10.0D;
/* 34 */         motionZ = z / 7.0D;
/*    */         
/* 36 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
/* 37 */         data.setLife(3);
/* 38 */         data.setSize(1.3F);
/* 39 */         data.setMotion(motionX, motionY, motionZ);
/* 40 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.65D + WyHelper.randomDouble(), posY + y, posZ + z * 1.65D + WyHelper.randomDouble());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\moku\WhiteStrikeParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */