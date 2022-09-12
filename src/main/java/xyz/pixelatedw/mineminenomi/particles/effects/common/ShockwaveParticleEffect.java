package xyz.pixelatedw.mineminenomi.particles.effects.common;

import net.minecraft.block.BlockState;
import net.minecraft.particles.BlockParticleData;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class ShockwaveParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 120; i++) {
      
      double y = WyHelper.randomDouble() / 2.0D;
      
      BlockState blockState = world.getBlockState((new BlockPos(posX, posY, posZ)).down());
      
      ((ServerWorld)world).spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, blockState), posX + 
          WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble() / 20.0D, posY + y + 0.5D, posZ + 
          
          WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble() / 20.0D, 1, 0.0D, 0.0D, 0.0D, 0.0D);
    } 
  }
}


