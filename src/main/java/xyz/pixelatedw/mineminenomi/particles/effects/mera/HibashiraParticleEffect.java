package xyz.pixelatedw.mineminenomi.particles.effects.mera;

import java.util.Random;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class HibashiraParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double t = 0.0D;
    
    Random rand = world.rand;
    double size = 2.5D;
    
    while (t < 1.0D) {
      
      t += 1.5707963267948966D;
      double theta;
      for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
        GenericParticleData data;
        double x = t * Math.cos(theta);
        double z = t * Math.sin(theta);
        
        motionX = Math.sin(theta) / 10.0D;
        motionY = 0.3D + rand.nextDouble() / 10.0D;
        motionZ = -Math.cos(theta) / 10.0D;


        
        if (rand.nextInt(10) % 2 == 0) {
          data = new GenericParticleData(ModParticleTypes.MERA);
        } else {
          data = new GenericParticleData(ModParticleTypes.MERA2);
        }  data.setLife((int)WyHelper.randomWithRange(5, 20));
        data.setSize((float)WyHelper.randomWithRange(3, 5));
        data.setColor(1.0F, 1.0F, 1.0F, 0.5F);
        data.setMotion(motionX, motionY, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * size + WyHelper.randomDouble() / 2.0D, posY - 10.0D, posZ + z * size + WyHelper.randomDouble() / 2.0D);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * size + WyHelper.randomDouble() / 2.0D, posY - 5.0D, posZ + z * size + WyHelper.randomDouble() / 2.0D);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * size + WyHelper.randomDouble() / 2.0D, posY, posZ + z * size + WyHelper.randomDouble() / 2.0D);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * size + WyHelper.randomDouble() / 2.0D, posY + 5.0D + WyHelper.randomWithRange(-2, 0), posZ + z * size + WyHelper.randomDouble() / 2.0D);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * size + WyHelper.randomDouble() / 2.0D, posY + 10.0D + WyHelper.randomWithRange(-2, 0), posZ + z * size + WyHelper.randomDouble() / 2.0D);
      } 
    } 
  }
}


