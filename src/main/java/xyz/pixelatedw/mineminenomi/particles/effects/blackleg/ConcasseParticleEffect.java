package xyz.pixelatedw.mineminenomi.particles.effects.blackleg;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class ConcasseParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    posY += 1.5D;
    
    double radius = 0.5D;
    double phi = 0.0D;
    
    while (phi < Math.PI) {
      
      phi += 1.5707963267948966D;
      double theta;
      for (theta = 0.0D; theta <= 12.566370614359172D; theta += 1.5707963267948966D) {
        GenericParticleData data;
        double x = radius * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble() / 2.0D;
        double y = radius * Math.cos(phi) + WyHelper.randomDouble() / 2.0D;
        double z = radius * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble() / 2.0D;


        
        if (world.rand.nextInt(10) % 2 == 0) {
          data = new GenericParticleData(ModParticleTypes.MERA);
        } else {
          data = new GenericParticleData(ModParticleTypes.MERA2);
        }  data.setLife((int)WyHelper.randomWithRange(5, 10));
        data.setSize((float)WyHelper.randomWithRange(1, 3));
        data.setColor(1.0F, 1.0F, 1.0F, 0.5F);
        data.setMotion(motionX, motionY, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + y, posZ + z);
      } 
    } 
  }
}


