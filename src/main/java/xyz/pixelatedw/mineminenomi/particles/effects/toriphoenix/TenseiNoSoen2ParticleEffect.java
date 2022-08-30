/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;
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
/*    */ public class TenseiNoSoen2ParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 18 */     double t = 0.0D;
/*    */     
/* 20 */     Random rand = world.rand;
/*    */     
/* 22 */     while (t < 3.0D) {
/*    */       
/* 24 */       t += 0.3141592653589793D;
/*    */       double theta;
/* 26 */       for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
/*    */         
/* 28 */         double x = t * Math.cos(theta);
/* 29 */         double y = rand.nextInt(1);
/* 30 */         double z = t * Math.sin(theta);
/*    */         
/* 32 */         motionX = x / 4.0D;
/* 33 */         motionY = 0.05D + MathHelper.abs((float)WyHelper.randomDouble() / 12.0F);
/* 34 */         motionZ = z / 4.0D;
/*    */         
/* 36 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.BLUE_FLAME);
/* 37 */         data.setLife(20);
/* 38 */         data.setSize(2.0F);
/* 39 */         data.setMotion(motionX, motionY, motionZ);
/* 40 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.25D + WyHelper.randomDouble(), posY + 0.5D + y, posZ + z * 1.25D + WyHelper.randomDouble());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\toriphoenix\TenseiNoSoen2ParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */