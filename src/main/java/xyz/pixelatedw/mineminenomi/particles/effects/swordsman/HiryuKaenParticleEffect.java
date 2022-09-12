package xyz.pixelatedw.mineminenomi.particles.effects.swordsman;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class HiryuKaenParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double t = 0.0D;
    
    Random rand = world.rand;
    
    while (t < 1.0D) {
      
      t += 1.5707963267948966D;
      double theta;
      for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.09817477042468103D) {
        GenericParticleData data;
        double x = t * Math.cos(theta);
        double z = t * Math.sin(theta);
        
        motionX = x / 5.0D;
        motionY = 0.01D + rand.nextDouble() / 4.0D;
        motionZ = z / 5.0D;


        
        if (rand.nextInt(10) % 2 == 0) {
          data = new GenericParticleData(ModParticleTypes.MERA);
        } else {
          data = new GenericParticleData(ModParticleTypes.MERA2);
        }  data.setLife(5);
        data.setSize(2.0F);
        data.setColor(1.0F, 1.0F, 1.0F, 0.5F);
        data.setMotion(motionX, motionY, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 0.85D, posY + 1.2D, posZ + z * 0.85D);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 0.45D, posY + 1.8D, posZ + z * 0.45D);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 0.85D, posY + 2.2D, posZ + z * 0.85D);
      } 
    } 
  }
}


