/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.rokushiki;
/*    */ 
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class GeppoParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 15 */     double offsetX = WyHelper.randomDouble() / 2.0D;
/* 16 */     double offsetY = WyHelper.randomDouble() / 2.0D;
/* 17 */     double offsetZ = WyHelper.randomDouble() / 2.0D;
/* 18 */     ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.CLOUD, (int)posX, (int)posY, (int)posZ, 9, offsetX, offsetY, offsetZ, 0.01D);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\rokushiki\GeppoParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */