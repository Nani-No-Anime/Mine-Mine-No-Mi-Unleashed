package xyz.pixelatedw.mineminenomi.particles.effects.fishkarate;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;


public class SamehadaParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    posY += 1.5D;
    
    double radius = 1.0D;
    double phi = 0.0D;
    
    while (phi < Math.PI) {
      
      phi += 0.7853981633974483D;
      double theta;
      for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.5235987755982988D) {
        
        double x = radius * Math.cos(theta) * Math.sin(phi);
        double y = radius * Math.cos(phi);
        double z = radius * Math.sin(theta) * Math.sin(phi);
        
        ((ServerWorld)world).spawnParticle(ParticleTypes.SPLASH, posX + x, posY + y, posZ + z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
      } 
    } 
  }
}


