package xyz.pixelatedw.mineminenomi.particles.effects.common;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class GuardParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 25; i++) {
      
      double x = posX + WyHelper.randomDouble() / 1.2D;
      double y = posY + WyHelper.randomDouble() / 1.2D;
      double z = posZ + WyHelper.randomDouble() / 1.2D;
      
      WyHelper.spawnParticles(ParticleTypes.CRIT, (ServerWorld)world, x, y + 1.0D, z);
      WyHelper.spawnParticles(ParticleTypes.ENCHANTED_HIT, (ServerWorld)world, x, y + 1.0D, z);
    } 
  }
}


