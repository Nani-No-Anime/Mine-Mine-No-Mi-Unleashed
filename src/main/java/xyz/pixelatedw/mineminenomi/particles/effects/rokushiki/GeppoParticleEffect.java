package xyz.pixelatedw.mineminenomi.particles.effects.rokushiki;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class GeppoParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double offsetX = WyHelper.randomDouble() / 2.0D;
    double offsetY = WyHelper.randomDouble() / 2.0D;
    double offsetZ = WyHelper.randomDouble() / 2.0D;
    ((ServerWorld)world).spawnParticle((IParticleData)ParticleTypes.CLOUD, (int)posX, (int)posY, (int)posZ, 9, offsetX, offsetY, offsetZ, 0.01D);
  }
}


