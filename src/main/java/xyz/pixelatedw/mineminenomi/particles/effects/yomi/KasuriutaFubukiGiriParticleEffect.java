/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.yomi;
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
/*    */ public class KasuriutaFubukiGiriParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     for (int i = 0; i < 2; i++) {
/*    */       
/* 18 */       double offsetX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/* 19 */       double offsetY = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/* 20 */       double offsetZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
/*    */       
/* 22 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.HIE);
/* 23 */       data.setLife(20);
/* 24 */       data.setSize(1.3F);
/* 25 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.5D + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yomi\KasuriutaFubukiGiriParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */