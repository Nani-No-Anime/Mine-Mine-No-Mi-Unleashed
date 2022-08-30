/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.blackleg;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ExtraHachiParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 15 */     for (int i = 0; i < 4; i++) {
/*    */       
/* 17 */       double offsetX = WyHelper.randomDouble() / 3.0D;
/* 18 */       double offsetY = WyHelper.randomDouble() / 3.0D;
/* 19 */       double offsetZ = WyHelper.randomDouble() / 3.0D;
/*    */       
/* 21 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
/* 22 */       data.setLife(10);
/* 23 */       data.setSize(1.0F);
/* 24 */       data.setMotion(offsetX / 12.0D, offsetY / 5.0D + 0.05D, offsetZ / 12.0D);
/* 25 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\blackleg\ExtraHachiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */