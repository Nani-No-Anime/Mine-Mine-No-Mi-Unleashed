package xyz.pixelatedw.mineminenomi.particles.effects.artofweather;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class FailedTempoParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 10; i++) {
      
      double offsetX = WyHelper.randomDouble();
      double offsetY = WyHelper.randomDouble();
      double offsetZ = WyHelper.randomDouble();
      
      if (i % 2 == 0) {
        ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.POOF, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
      } else {
        ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.SMOKE, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
      } 
    } 
  }
}


