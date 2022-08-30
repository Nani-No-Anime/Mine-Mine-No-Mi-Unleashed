/*    */ package xyz.pixelatedw.mineminenomi.particles.effects.common;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.particles.BlockParticleData;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ShockwaveParticleEffect
/*    */   extends ParticleEffect
/*    */ {
/*    */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/* 17 */     for (int i = 0; i < 120; i++) {
/*    */       
/* 19 */       double y = WyHelper.randomDouble() / 2.0D;
/*    */       
/* 21 */       BlockState blockState = world.getBlockState((new BlockPos(posX, posY, posZ)).down());
/*    */       
/* 23 */       ((ServerWorld)world).spawnParticle((IParticleData)new BlockParticleData(ParticleTypes.BLOCK, blockState), posX + 
/* 24 */           WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble() / 20.0D, posY + y + 0.5D, posZ + 
/*    */           
/* 26 */           WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble() / 20.0D, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\common\ShockwaveParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */