package xyz.pixelatedw.mineminenomi.particles.effects.yami;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class BlackWorldParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 500; i++) {
      
      double offsetX = WyHelper.randomWithRange(-10, 10) + WyHelper.randomDouble();
      double offsetY = WyHelper.randomWithRange(0, 2) + WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-10, 10) + WyHelper.randomDouble();
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.DARKNESS);
      data.setLife(30 + world.rand.nextInt(10));
      data.setSize(1.2F);
      data.setMotion(0.0D, 0.02D, 0.0D);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
      
      data.setLife(30 + world.rand.nextInt(10));
      data.setSize(1.2F);
      data.setMotion(0.0D, 0.02D, 0.0D);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 0.5D + offsetY, posZ + offsetZ);
      
      data.setLife(30 + world.rand.nextInt(10));
      data.setSize(1.2F);
      data.setMotion(0.0D, 0.02D, 0.0D);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.5D + offsetY, posZ + offsetZ);
    } 
  }
}


