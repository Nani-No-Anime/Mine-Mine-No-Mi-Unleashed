package xyz.pixelatedw.mineminenomi.particles.effects.baku;

import net.minecraft.block.BlockState;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class BakuMunchParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 3; i++) {
      
      double offsetX = world.rand.nextDouble();
      double offsetY = 1.0D;
      double offsetZ = world.rand.nextDouble();
      
      BlockState blockState = world.getBlockState((new BlockPos(posX + offsetX, posY, posZ + offsetZ)).down());
      
      ((ServerWorld)world).spawnParticle((IParticleData)new BlockParticleData(ParticleTypes.BLOCK, blockState), posX + offsetX, posY + offsetY, posZ + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
    } 
  }
}


