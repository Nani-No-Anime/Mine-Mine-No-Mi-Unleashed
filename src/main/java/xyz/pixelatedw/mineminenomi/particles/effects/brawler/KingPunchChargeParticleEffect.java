package xyz.pixelatedw.mineminenomi.particles.effects.brawler;

import java.util.Random;
import net.minecraft.block.BlockState;
import net.minecraft.particles.BlockParticleData;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;



public class KingPunchChargeParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    int i = 0;
    double t = 0.0D;
    
    Random rand = world.rand;
    
    while (t < 1.0D) {
      
      t += 1.5707963267948966D;
      double theta;
      for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
        
        double x = t * Math.cos(theta);
        double y = rand.nextInt(1);
        double z = t * Math.sin(theta);
        
        motionX = -x / 20.0D;
        motionY = 0.05D + rand.nextDouble() / 10.0D;
        motionZ = -z / 20.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
        
        if (i % 2 == 0) {
          data = new GenericParticleData(ModParticleTypes.MOKU2);
        }
        double offsetX = world.rand.nextDouble() * WyHelper.randomWithRange(7, 9);
        double offsetY = 1.0D;
        double offsetZ = world.rand.nextDouble() * WyHelper.randomWithRange(7, 9);
        
        BlockState blockState = world.getBlockState((new BlockPos(posX + offsetX, posY, posZ + offsetZ)).down());
        
        ((ServerWorld)world).spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, blockState), posX - 4.0D + offsetX, posY - 1.0D + offsetY, posZ - 4.0D + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);




        
        data.setLife(1);
        data.setSize(1.0F);
        data.setMotion(motionX, motionY, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.25D + WyHelper.randomDouble() * 2.0D, posY + y, posZ + z * 1.25D + WyHelper.randomDouble() * 2.0D);
        
        data.setLife(3);
        data.setSize(1.0F);
        data.setMotion(motionX, motionY + 0.001D, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.5D + WyHelper.randomDouble(), posY + y, posZ + z * 1.5D + WyHelper.randomDouble());
        
        data.setLife(5);
        data.setSize(1.0F);
        data.setMotion(motionX, motionY + 0.005D, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.75D + WyHelper.randomDouble() * 2.0D, posY + y, posZ + z * 1.75D + WyHelper.randomDouble() * 2.0D);
        
        i++;
      } 
    } 
  }
}


