/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.yami;
/*    */ 
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class KurouzuChargeParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 16 */     GenericParticleData data = new GenericParticleData(ModParticleTypes.KUROUZU);
/* 17 */     data.setColor(1.0F, 1.0F, 1.0F, 0.4F);
/* 18 */     data.setLife(1);
/* 19 */     data.setSize(10.0F);
/* 20 */     data.setHasScaleDecay(false);
/* 21 */     data.setRotation(Vector3f.ZP);
/* 22 */     WyHelper.spawnParticles(data, (ServerWorld)world, posX, posY, posZ);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\yami\KurouzuChargeParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */