package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class FujizamiParticleEffect extends ParticleEffect {
  public float multiplier = 1.0F;


  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double radius = 0.4D * this.multiplier;
    
    for (int i = 0; i < 6; i++) {
      
      double phi = 0.0D;
      
      while (phi < Math.PI) {
        
        phi += 0.39269908169872414D;
        double theta;
        for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.39269908169872414D) {
          
          double x = radius * Math.cos(theta) * Math.sin(phi);
          double y = radius * Math.cos(phi);
          double z = radius * Math.sin(theta) * Math.sin(phi);
          
          GenericParticleData data = new GenericParticleData(ModParticleTypes.BLUE_FLAME);
          data.setLife(20);
          data.setColor(1.0F, 1.0F, 1.0F, 0.3F);
          
          motionX = (WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble()) / 20.0D;
          motionZ = (WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble()) / 20.0D;
          
          data.setMotion(motionX, 0.009999999776482582D, motionZ);
          data.setSize(1.0F);
          WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + y, posZ + z);
        } 
      } 
      
      radius += 0.2D * this.multiplier;
    } 
  }
}


