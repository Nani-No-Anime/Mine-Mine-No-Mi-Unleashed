package xyz.pixelatedw.mineminenomi.particles.effects.suna;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class DesertGirasole2ParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double t = 0.0D;

    
    while (t < 1.0D) {
      
      t += 0.3141592653589793D;
      double theta;
      for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
        
        double x = t * Math.cos(theta);
        double y = WyHelper.randomDouble();
        double z = t * Math.sin(theta);
        
        motionX = WyHelper.randomDouble() / 2.0D;
        motionY = 0.0D;
        motionZ = WyHelper.randomDouble() / 2.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA);
        data.setLife(35);
        data.setSize(3.0F);
        data.setMotion(motionX, motionY, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 2.25D, posY + 0.5D + y, posZ + z * 2.25D);
      } 
    } 
  }
}


