package xyz.pixelatedw.mineminenomi.particles.effects.suna;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class DesertEncierroParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double t = 0.0D;
    
    Random rand = world.rand;
    
    while (t < 1.0D) {
      
      t += 1.5707963267948966D;
      double theta;
      for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.09817477042468103D) {
        
        double x = t * Math.cos(theta);
        double z = t * Math.sin(theta);
        
        motionX = -x / 6.0D;
        motionY = -0.1D + rand.nextDouble() / 10.0D;
        motionZ = -z / 6.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA);
        data.setLife(-1);
        data.setSize(3.3F);
        data.setMotion(motionX, motionY, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.25D, posY + 1.2D, posZ + z * 1.25D);
        
        data = new GenericParticleData(ModParticleTypes.SUNA);
        data.setLife(-1);
        data.setSize(3.3F);
        data.setMotion(motionX, motionY, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.25D, posY + 2.2D, posZ + z * 1.25D);
      } 
    } 
  }
}


