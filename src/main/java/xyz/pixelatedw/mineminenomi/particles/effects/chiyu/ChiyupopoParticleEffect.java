package xyz.pixelatedw.mineminenomi.particles.effects.chiyu;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.Random;



public class ChiyupopoParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double t = 0.0D;
    
    Random rand = world.rand;
    
    while (t < 1.0D) {
      
      t += 1.5707963267948966D;
      double theta;
      for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.3141592653589793D) {
        
        double x = t * Math.cos(theta);
        double y = 0.2D + rand.nextInt(1);
        double z = t * Math.sin(theta);
        
        motionX = x / 4.0D;
        motionY = 0.05D + rand.nextDouble() / 7.0D;
        motionZ = z / 4.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.CHIYU);
        data.setLife(4);
        data.setSize(2.0F);
        data.setMotion(motionX, motionY, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 0.75D + WyHelper.randomDouble(), posY + y, posZ + z * 0.75D + WyHelper.randomDouble());
        
        data = new GenericParticleData(ModParticleTypes.CHIYU);
        data.setLife(7);
        data.setSize(2.5F);
        data.setMotion(motionX, motionY, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 2.0D + WyHelper.randomDouble(), posY + y, posZ + z * 2.0D + WyHelper.randomDouble());
        
        data = new GenericParticleData(ModParticleTypes.CHIYU);
        data.setLife(10);
        data.setSize(4.5F);
        data.setMotion(motionX, motionY * 2.25D, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 3.25D + WyHelper.randomDouble(), posY + y, posZ + z * 3.25D + WyHelper.randomDouble());
      } 
    } 
  }
}


