package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class BlueBirdEffect
  extends ParticleEffect {
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    posY += 1.5D;
    
    double radius = 1.0D;
    double phi = 0.0D;
    
    while (phi < Math.PI) {
      
      phi += 0.7853981633974483D;
      double theta;
      for (theta = 0.0D; theta <= 6.283185307179586D; theta += 1.5707963267948966D) {
        
        double x = radius * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble();
        double y = radius * Math.cos(phi) + WyHelper.randomDouble();
        double z = radius * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble();
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.BLUE_FLAME);
        data.setLife(20);
        data.setSize(2.0F);
        data.setMotion(0.0D, 0.02D, 0.0D);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY - 1.0D + y, posZ + z);
      } 
    } 
  }
}


