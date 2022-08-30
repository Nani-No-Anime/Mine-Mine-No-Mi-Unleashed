/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.suna;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SablesPesadoChargingParticleEffect
/*    */   extends ParticleEffect {
/* 13 */   public float multiplier = 1.0F;
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 18 */     double phi = 0.0D;
/*    */ 
/*    */     
/* 21 */     GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA2);
/* 22 */     data.setLife(20);
/* 23 */     data.setSize(1.3F);
/*    */     
/* 25 */     while (phi < 6.283185307179586D) {
/*    */       
/* 27 */       phi += 0.19634954084936207D; double t;
/* 28 */       for (t = 0.0D; t <= 6.283185307179586D; t += 0.19634954084936207D) {
/*    */         
/* 30 */         double x = 0.45D * this.multiplier * (6.283185307179586D + t) * Math.cos(t * phi * Math.PI) + WyHelper.randomDouble();
/* 31 */         double y = 1.5D * this.multiplier * t;
/* 32 */         double z = 0.45D * this.multiplier * (6.283185307179586D + t) * Math.sin(t * phi * Math.PI) + WyHelper.randomDouble();
/*    */         
/* 34 */         data.setMotion(motionX, motionY + 0.01D + Math.abs(WyHelper.randomDouble()) / 5.0D, motionZ);
/*    */         
/* 36 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + y, posZ + z);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\suna\SablesPesadoChargingParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */