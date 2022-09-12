package xyz.pixelatedw.mineminenomi.particles.effects.swordsman;

import java.util.Random;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class OTatsumakiParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double t = 0.0D;
    
    Random rand = world.rand;
    
    while (t < 1.0D) {
      
      t += 1.5707963267948966D;
      double theta;
      for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
        
        double x = t * Math.cos(theta);
        double z = t * Math.sin(theta);
        
        motionX = x / 6.0D;
        motionY = -0.1D + rand.nextDouble() / 10.0D;
        motionZ = z / 6.0D;
        
        WyHelper.spawnParticles(ParticleTypes.SWEEP_ATTACK, (ServerWorld)world, posX + x * 1.85D, posY + 1.2D + WyHelper.randomDouble(), posZ + z * 1.85D, (float)motionX, (float)motionY, (float)motionZ);
      } 
    } 
  }
}


