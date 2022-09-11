package xyz.pixelatedw.mineminenomi.particles.effects.fishkarate;

import java.util.Random;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class KarakusagawaraSeikenChargeParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    int i = 0;
    double t = 0.0D;
    
    Random rand = world.rand;
    
    while (t < 1.0D) {
      
      t += 1.5707963267948966D;
      double theta;
      for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
        
        double x = t * Math.cos(theta);
        double y = rand.nextInt(1);
        double z = t * Math.sin(theta);
        
        motionX = -x / 20.0D;
        motionY = 0.05D + rand.nextDouble() / 10.0D;
        motionZ = -z / 20.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.AWA);
        
        if (i % 2 == 0) {
          data = new GenericParticleData(ModParticleTypes.AWA);
        }
        double offsetX = world.rand.nextDouble() * WyHelper.randomWithRange(7, 9);
        double offsetY = 1.0D;
        double offsetZ = world.rand.nextDouble() * WyHelper.randomWithRange(7, 9);
        
        ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.DRIPPING_WATER, posX - 4.0D + offsetX, posY - 1.0D + offsetY, posZ - 4.0D + offsetZ, 1, 0.0D, 1.2D, 0.0D, 1.2D);




        
        data.setLife(1);
        data.setSize(1.0F);
        data.setMotion(motionX, motionY, motionZ);
        data.setColor(0.0F, 0.0F, 1.0F, 0.8F);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.25D + WyHelper.randomDouble() * 2.0D, posY + y, posZ + z * 1.25D + WyHelper.randomDouble() * 2.0D);
        
        i++;
      } 
    } 
  }
}


