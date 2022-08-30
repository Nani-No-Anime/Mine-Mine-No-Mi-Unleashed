/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.supa;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.particles.BlockParticleData;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class AtomicSpurtParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     for (int i = 0; i < 2; i++) {
/*    */       
/* 19 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 20 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */       
/* 22 */       BlockState BlockState = world.getBlockState((new BlockPos(posX, posY, posZ)).down());
/*    */       
/* 24 */       world.addParticle((IParticleData)new BlockParticleData(ParticleTypes.BLOCK, BlockState), posX + offsetX, posY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\supa\AtomicSpurtParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */