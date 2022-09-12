package xyz.pixelatedw.mineminenomi.particles.effects.electro;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class ElectroChargingParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double t = 0.0D;
    
    Random rand = world.rand;
    double size = 0.75D;
    
    while (t < 1.0D) {
      
      t += size * Math.PI;
      double theta;
      for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
        GenericParticleData data;
        double x = t * Math.cos(theta);
        double z = t * Math.sin(theta);
        
        motionX = -x / 20.0D;
        motionY = 0.01D + rand.nextDouble() / 15.0D;
        motionZ = -z / 20.0D;


        
        if (rand.nextInt(10) % 2 == 0) {
          data = new GenericParticleData(ModParticleTypes.GORO);
        } else {
          data = new GenericParticleData(ModParticleTypes.GORO2);
        }  data.setLife((int)WyHelper.randomWithRange(5, 10));
        data.setSize((float)WyHelper.randomWithRange(3, 5));
        data.setColor(1.0F, 1.0F, 1.0F, 0.5F);
        data.setMotion(motionX, motionY, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x + WyHelper.randomDouble() / 2.0D, posY + 0.25D, posZ + z + WyHelper.randomDouble() / 2.0D);
      } 
    } 
  }
}


