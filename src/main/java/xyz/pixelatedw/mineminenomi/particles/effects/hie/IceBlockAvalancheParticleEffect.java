/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.hie;
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
/*    */ public class IceBlockAvalancheParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     double phi = 0.0D;
/*    */     
/* 18 */     while (phi < Math.PI) {
/*    */       
/* 20 */       phi += 0.7853981633974483D;
/*    */       double theta;
/* 22 */       for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.5235987755982988D) {
/*    */         
/* 24 */         double x = 8.0D * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble();
/* 25 */         double y = posY - 3.0D - 1.0D;
/* 26 */         double z = 8.0D * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble();
/* 27 */         motionX = x / 40.0D;
/* 28 */         motionY = -0.2D;
/* 29 */         motionZ = z / 40.0D;
/*    */         
/* 31 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.HIE);
/* 32 */         data.setLife(8);
/* 33 */         data.setSize(10.0F);
/*    */         
/* 35 */         data.setMotion(-motionX, motionY, -motionZ);
/* 36 */         if (Math.random() > 0.7D)
/* 37 */           WyHelper.spawnParticles(data, (ServerWorld)world, posX + x + WyHelper.randomDouble(), y, posZ + z + WyHelper.randomDouble()); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\hie\IceBlockAvalancheParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */