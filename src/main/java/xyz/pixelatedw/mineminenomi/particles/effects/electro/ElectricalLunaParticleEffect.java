/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.electro;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ElectricalLunaParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 15 */     for (int i = 0; i < 20; i++) {
/*    */       GenericParticleData data;
/* 17 */       double offsetX = WyHelper.randomDouble();
/* 18 */       double offsetY = WyHelper.randomDouble();
/* 19 */       double offsetZ = WyHelper.randomDouble();
/*    */ 
/*    */ 
/*    */       
/* 23 */       if (i % 2 == 0) {
/* 24 */         data = new GenericParticleData(ModParticleTypes.GORO2);
/*    */       } else {
/* 26 */         data = new GenericParticleData(ModParticleTypes.GORO);
/* 27 */       }  data.setLife(5);
/* 28 */       data.setSize(7.0F);
/* 29 */       data.setColor(1.0F, 1.0F, 1.0F, 0.5F);
/* 30 */       data.setMotion(0.0D, WyHelper.randomDouble() / 3.0D, 0.0D);
/* 31 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\electro\ElectricalLunaParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */