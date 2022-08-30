/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class TenseiNoSoenParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 15 */     posY += 1.5D;
/*    */     
/* 17 */     double radius = 1.0D;
/* 18 */     double phi = 0.0D;
/*    */     
/* 20 */     while (phi < Math.PI) {
/*    */       
/* 22 */       phi += 0.7853981633974483D;
/*    */       double theta;
/* 24 */       for (theta = 0.0D; theta <= 6.283185307179586D; theta += 1.5707963267948966D) {
/*    */         
/* 26 */         double x = radius * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble();
/* 27 */         double y = radius * Math.cos(phi) + WyHelper.randomDouble();
/* 28 */         double z = radius * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble();
/*    */         
/* 30 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.BLUE_FLAME);
/* 31 */         data.setLife(20);
/* 32 */         data.setSize(2.0F);
/* 33 */         data.setMotion(0.0D, 0.02D, 0.0D);
/* 34 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY - 1.0D + y, posZ + z);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\toriphoenix\TenseiNoSoenParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */