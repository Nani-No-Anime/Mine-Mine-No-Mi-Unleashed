package xyz.pixelatedw.mineminenomi.particles.effects.yami;

import java.util.Random;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;



public class BlackHoleParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double t = 0.0D;
    
    Random rand = world.rand;
    
    while (t < 3.0D) {
      
      t += 0.3141592653589793D;
      double theta;
      for (theta = 0.0D; theta <= 12.566370614359172D; theta += 0.19634954084936207D) {
        
        double x = t * Math.cos(theta);
        double y = rand.nextInt(1);
        double z = t * Math.sin(theta);
        
        motionX = x / 4.0D;
        motionY = 0.05D + MathHelper.abs((float)WyHelper.randomDouble() / 12.0F);
        motionZ = z / 4.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.DARKNESS);
        data.setLife(20);
        data.setSize(2.0F);
        data.setMotion(motionX, motionY, motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.25D + WyHelper.randomDouble(), posY + y, posZ + z * 1.25D + WyHelper.randomDouble());
      } 
    } 
  }
}


