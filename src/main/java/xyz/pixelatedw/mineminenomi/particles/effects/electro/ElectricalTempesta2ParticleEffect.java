package xyz.pixelatedw.mineminenomi.particles.effects.electro;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.Random;


public class ElectricalTempesta2ParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double t = 0.0D;
    
    Random rand = world.rand;
    double size = 0.1D;
    
    while (t < 5.0D) {
      
      t += size * Math.PI;
      double theta;
      for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
        GenericParticleData data;
        double x = t * Math.cos(theta);
        double z = t * Math.sin(theta);
        
        motionX = x / 5.0D;
        motionY = 0.05D + rand.nextDouble() / 15.0D;
        motionZ = z / 5.0D;


        
        if (rand.nextInt(10) % 2 == 0) {
          data = new GenericParticleData(ModParticleTypes.GORO);
        } else {
          data = new GenericParticleData(ModParticleTypes.GORO2);
        }  data.setLife(0);
        data.setSize((float)WyHelper.randomWithRange(3, 5));
        data.setColor(1.0F, 1.0F, 1.0F, 0.8F);
        data.setMotion(motionX, motionY, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x + WyHelper.randomDouble() / 2.0D, posY + 0.25D, posZ + z + WyHelper.randomDouble() / 2.0D);
      } 
      size += 0.2D;
    } 
  }
}


