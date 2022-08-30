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
/*    */ 
/*    */ public class DesertGirasoleParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 64; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomWithRange(-15, 15) + WyHelper.randomDouble();
/* 19 */       double offsetZ = WyHelper.randomWithRange(-15, 15) + WyHelper.randomDouble();
/*    */       
/* 21 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA2);
/* 22 */       data.setLife((int)(80.0D + 0.2D * i));
/* 23 */       data.setSize(4.0F);
/* 24 */       data.setMotion(0.0D, 0.3D, 0.0D);
/* 25 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 0.5D - 0.15D * i, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\suna\DesertGirasoleParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */