package xyz.pixelatedw.mineminenomi.particles.effects.goro;


import net.minecraft.particles.ParticleType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class DeathpiaCloudParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    int range = 128;
    for (int i = 0; i < range; i++) {
      ParticleType<GenericParticleData> particle;
      double offsetX = WyHelper.randomWithRange(-range, range) + WyHelper.randomDouble();
      double offsetY = world.getWorldInfo().getGenerator().getCloudHeight() + WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-range, range) + WyHelper.randomDouble();
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO3);
      data.setLife(40);
      data.setSize(80.0F);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);

      
      if (i % 4 == 0) {
        particle = ModParticleTypes.GORO;
      } else {
        particle = ModParticleTypes.GORO2;
      } 
      GenericParticleData d = new GenericParticleData(particle);
      d.setLife(10);
      d.setSize(6.0F);
      WyHelper.spawnParticles(d, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
    } 
  }
}


