package xyz.pixelatedw.mineminenomi.particles.effects.moku;

import java.util.Random;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;



public class WhiteStrikeParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double t = 0.0D;
    
    Random rand = world.rand;
    
    while (t < 1.0D) {
      
      t += 1.5707963267948966D;
      double theta;
      for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
        
        double x = t * Math.cos(theta);
        double y = rand.nextInt(1);
        double z = t * Math.sin(theta);
        
        motionX = x / 7.0D;
        motionY = 0.02D + rand.nextDouble() / 10.0D;
        motionZ = z / 7.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
        data.setLife(3);
        data.setSize(1.3F);
        data.setMotion(motionX, motionY, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.65D + WyHelper.randomDouble(), posY + y, posZ + z * 1.65D + WyHelper.randomDouble());
      } 
    } 
  }
}


