package xyz.pixelatedw.mineminenomi.particles.effects.zou;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class GreatStompParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double phi = 0.0D;

    
    while (phi < 10.0D) {
      
      phi += 0.3141592653589793D;
      double theta;
      for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
        
        double x = phi * Math.cos(theta);
        double y = WyHelper.randomDouble();
        double z = phi * Math.sin(theta);
        
        motionX = WyHelper.randomDouble() / 2.0D;
        motionY = 0.0D;
        motionZ = WyHelper.randomDouble() / 2.0D;
        
        BlockState blockState = world.getBlockState((new BlockPos(posX, posY, posZ)).down());
        
        if (blockState.getMaterial() == Material.AIR) {
          blockState = Blocks.DIRT.getDefaultState();
        }
        ((ServerWorld)world).spawnParticle((IParticleData)new BlockParticleData(ParticleTypes.BLOCK, blockState), posX + 
            WyHelper.randomWithRange(-3, 3) + x, posY + y, posZ + 
            
            WyHelper.randomWithRange(-3, 3) + z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
      } 
    } 
  }
}


