package xyz.pixelatedw.mineminenomi.particles.effects.brawler;

import java.util.Random;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;



public class TatsumakiParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double t = 0.0D;
    
    Random rand = world.rand;
    double size = 2.0D;
    
    while (t < 1.0D) {
      
      t += 1.5707963267948966D;
      double theta;
      for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
        
        double x = t * Math.cos(theta);
        double z = t * Math.sin(theta);
        
        motionX = Math.sin(theta) / 10.0D;
        motionY = 0.3D + rand.nextDouble() / 10.0D;
        motionZ = -Math.cos(theta) / 10.0D;
        for (int i = -10; i < 10; i++)
          ((ServerWorld)world).spawnParticle(ParticleTypes.SNEEZE, posX + x * size + WyHelper.randomDouble() / 2.0D, posY + i, posZ + z * size + WyHelper.randomDouble() / 2.0D, 1, motionX, motionY, motionZ, 0.03D); 
      } 
    } 
  }
}


