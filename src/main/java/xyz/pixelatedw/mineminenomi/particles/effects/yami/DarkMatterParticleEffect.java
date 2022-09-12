package xyz.pixelatedw.mineminenomi.particles.effects.yami;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.Random;



public class DarkMatterParticleEffect
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
        
        motionX = -x / 3.0D;
        motionY = -0.1D + rand.nextDouble() / 10.0D;
        motionZ = -z / 3.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.DARKNESS);
        data.setLife(10);
        data.setSize(3.3F);
        data.setMotion(motionX, motionY, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 5.25D, posY + 1.2D, posZ + z * 5.25D);
        
        data.setLife(10);
        data.setSize(3.3F);
        data.setMotion(motionX, motionY, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 5.25D, posY + 3.2D, posZ + z * 5.25D);
      } 
    } 
  }
}


