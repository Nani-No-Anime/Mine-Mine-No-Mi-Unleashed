package xyz.pixelatedw.mineminenomi.particles.effects.common;

import net.minecraft.block.BlockState;
import net.minecraft.particles.BlockParticleData;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class GroundParticlesEffect
  extends ParticleEffect {
  private int offset;
  private int amount;
  
  public GroundParticlesEffect(int offset, int amount) {
    this.offset = offset;
    this.amount = amount;
  }


  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < this.amount; i++) {
      
      double offsetX = WyHelper.randomWithRange(-this.offset, this.offset) + WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-this.offset, this.offset) + WyHelper.randomDouble();
      
      for (int j = 0; j < 2; j++) {
        
        BlockState blockState = world.getBlockState((new BlockPos(posX + offsetX, posY - j, posZ + offsetZ)).down());
        ((ServerWorld)world).spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, blockState), posX + offsetX, posY, posZ + offsetZ, 1, 0.0D, 0.5D, 0.0D, 0.0D);
      } 
    } 
  }
}


