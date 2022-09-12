package xyz.pixelatedw.mineminenomi.particles.effects.hitodaibutsu;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class ImpactWaveParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double radius = 1.0D;
    
    for (int i = 0; i < 10; i++) {
      
      double phi = 0.0D;
      
      while (phi < Math.PI) {
        
        phi += 0.19634954084936207D;
        double theta;
        for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.19634954084936207D) {
          
          double x = radius * Math.cos(theta) * Math.sin(phi);
          double y = radius * Math.cos(phi);
          double z = radius * Math.sin(theta) * Math.sin(phi);
          
          GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA2);
          data.setLife(50);
          
          motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
          motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
          
          double middlePoint = 1.2D;
          
          motionX *= middlePoint / 25.0D;
          motionZ *= middlePoint / 25.0D;
          data.setMotion(motionX, 0.10000000149011612D, motionZ);
          data.setSize(10.0F);
          data.setColor(1.0F, 1.0F, 0.2F, 0.5F);
          WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + y, posZ + z);
        } 
      } 
      
      radius += 0.8D;
    } 
  }
}


