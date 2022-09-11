package xyz.pixelatedw.mineminenomi.particles.effects.netsu;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class NetsuEnhancementParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 2; i++) {
      
      double offsetX = WyHelper.randomDouble() / 1.25D;
      double offsetY = WyHelper.randomDouble() / 1.25D;
      double offsetZ = WyHelper.randomDouble() / 1.25D;
      
      ParticleType<GenericParticleData> particle = ModParticleTypes.NETSU;
      if (i % 3 == 0)
        particle = ModParticleTypes.NETSU2; 
      if (i % 7 == 0) {
        particle = ModParticleTypes.MERA;
      }
      GenericParticleData data = new GenericParticleData(particle);
      data.setLife(10);
      data.setSize(1.3F);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
    } 
  }
}


