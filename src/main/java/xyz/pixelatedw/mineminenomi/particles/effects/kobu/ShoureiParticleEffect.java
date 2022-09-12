package xyz.pixelatedw.mineminenomi.particles.effects.kobu;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class ShoureiParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 20; i++) {
      
      double offsetX = WyHelper.randomDouble() / 2.0D;
      double offsetY = WyHelper.randomDouble() / 2.0D;
      double offsetZ = WyHelper.randomDouble() / 2.0D;
      
      GenericParticleData particleData = new GenericParticleData(ModParticleTypes.CHIYU);
      particleData.setLife(7);
      particleData.setSize(1.0F);
      double y = world.rand.nextDouble() / 15.0D;
      particleData.setMotion(0.0D, y, 0.0D);
      particleData.setColor(0.5F, 1.0F, 0.5F, 0.7F);
      WyHelper.spawnParticles(particleData, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
    } 
  }
}


