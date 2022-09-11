package xyz.pixelatedw.mineminenomi.particles.effects.hie;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class IceBlockAvalancheParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double phi = 0.0D;
    
    while (phi < Math.PI) {
      
      phi += 0.7853981633974483D;
      double theta;
      for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.5235987755982988D) {
        
        double x = 8.0D * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble();
        double y = posY - 3.0D - 1.0D;
        double z = 8.0D * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble();
        motionX = x / 40.0D;
        motionY = -0.2D;
        motionZ = z / 40.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.HIE);
        data.setLife(8);
        data.setSize(10.0F);
        
        data.setMotion(-motionX, motionY, -motionZ);
        if (Math.random() > 0.7D)
          WyHelper.spawnParticles(data, (ServerWorld)world, posX + x + WyHelper.randomDouble(), y, posZ + z + WyHelper.randomDouble()); 
      } 
    } 
  }
}


