package xyz.pixelatedw.mineminenomi.particles.effects.supa;

import net.minecraft.block.BlockState;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class AtomicSpurtParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 2; i++) {
      
      double offsetX = WyHelper.randomDouble() / 2.0D;
      double offsetZ = WyHelper.randomDouble() / 2.0D;
      
      BlockState BlockState = world.getBlockState((new BlockPos(posX, posY, posZ)).down());
      
      world.addParticle((IParticleData)new BlockParticleData(ParticleTypes.BLOCK, BlockState), posX + offsetX, posY, posZ + offsetZ, 0.0D, 0.0D, 0.0D);
    } 
  }
}


