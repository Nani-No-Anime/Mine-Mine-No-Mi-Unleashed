/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.hie;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IceAgeParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 19 */     double t = 0.0D;
/*    */     
/* 21 */     Random rand = world.rand;
/*    */     
/* 23 */     while (t < 3.0D) {
/*    */       
/* 25 */       t += 0.3141592653589793D;
/*    */       double theta;
/* 27 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/*    */         
/* 29 */         double x = t * Math.cos(theta);
/* 30 */         double y = rand.nextInt(1);
/* 31 */         double z = t * Math.sin(theta);
/*    */         
/* 33 */         motionX = x / 4.0D;
/* 34 */         motionY = 0.05D + MathHelper.abs((float)WyHelper.randomDouble() / 12.0F);
/* 35 */         motionZ = z / 4.0D;
/*    */         
/* 37 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.HIE);
/* 38 */         data.setLife(20);
/* 39 */         data.setSize(2.0F);
/* 40 */         data.setMotion(motionX, motionY, motionZ);
/* 41 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.25D + WyHelper.randomDouble(), posY + y, posZ + z * 1.25D + WyHelper.randomDouble());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\hie\IceAgeParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */