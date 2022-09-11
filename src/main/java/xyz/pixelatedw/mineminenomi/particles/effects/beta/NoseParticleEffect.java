package xyz.pixelatedw.mineminenomi.particles.effects.beta;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class NoseParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 10; i++) {
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.BETA);
      data.setLife(20);
      data.setSize(0.6F);
      data.setMotion(motionX, motionY - 0.03D, motionZ);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX - 0.35D, posY + 1.45D, posZ);
    } 
  }
}


