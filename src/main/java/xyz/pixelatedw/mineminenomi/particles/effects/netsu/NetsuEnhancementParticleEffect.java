/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.netsu;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class NetsuEnhancementParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     for (int i = 0; i < 2; i++) {
/*    */       
/* 19 */       double offsetX = WyHelper.randomDouble() / 1.25D;
/* 20 */       double offsetY = WyHelper.randomDouble() / 1.25D;
/* 21 */       double offsetZ = WyHelper.randomDouble() / 1.25D;
/*    */       
/* 23 */       ParticleType<GenericParticleData> particle = ModParticleTypes.NETSU;
/* 24 */       if (i % 3 == 0)
/* 25 */         particle = ModParticleTypes.NETSU2; 
/* 26 */       if (i % 7 == 0) {
/* 27 */         particle = ModParticleTypes.MERA;
/*    */       }
/* 29 */       GenericParticleData data = new GenericParticleData(particle);
/* 30 */       data.setLife(10);
/* 31 */       data.setSize(1.3F);
/* 32 */       WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\netsu\NetsuEnhancementParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */