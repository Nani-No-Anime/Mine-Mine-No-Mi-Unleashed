package xyz.pixelatedw.mineminenomi.particles.effects.gomu;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class GearSecondParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 2; i++) {
      
      double offsetX = WyHelper.randomDouble() / 1.25D;
      double offsetY = WyHelper.randomDouble() / 1.25D;
      double offsetZ = WyHelper.randomDouble() / 1.25D;
      
      ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.POOF, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ, 1, motionX, motionY, motionZ, 0.0D);
    } 
  }
}


