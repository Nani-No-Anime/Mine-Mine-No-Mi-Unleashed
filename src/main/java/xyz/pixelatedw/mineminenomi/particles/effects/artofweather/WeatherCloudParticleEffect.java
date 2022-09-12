package xyz.pixelatedw.mineminenomi.particles.effects.artofweather;


import net.minecraft.particles.ParticleType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class WeatherCloudParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    int i = 0;
    double phi = 0.0D;
    double radius = 7.0D;

    
    while (phi < Math.PI) {
      
      phi += 2.0943951023931953D;
      double theta;
      for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.7853981633974483D) {
        float red, green, blue; ParticleType<GenericParticleData> particle;
        double x = radius * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble() * radius;
        double y = radius * Math.cos(phi);
        double z = radius * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble() * radius;

        
        if (i % 2 == 0) {
          
          red = 0.5F;
          green = 0.5F;
          blue = 0.5F;
          particle = ModParticleTypes.MOKU2;
        }
        else {
          
          red = 0.3F;
          green = 0.3F;
          blue = 0.3F;
          particle = ModParticleTypes.MOKU;
        } 
        
        GenericParticleData data = new GenericParticleData(particle);
        data.setLife((int)(50.0D + WyHelper.randomDouble()));
        data.setSize(25.0F);
        data.setColor(red, green, blue);
        
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + y, posZ + z);
        i++;
      } 
    } 
  }
}


