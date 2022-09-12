package xyz.pixelatedw.mineminenomi.particles.effects.mera;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class HeatDashParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 40; i++) {
      
      double offsetX = WyHelper.randomDouble() / 1.3D;
      double offsetY = WyHelper.randomDouble() / 1.3D;
      double offsetZ = WyHelper.randomDouble() / 1.3D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
      data.setLife(10);
      data.setSize(1.3F);
      data.setMotion(offsetX / 5.0D, offsetY / 5.0D + 0.05D, offsetZ / 5.0D);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
    } 
  }
}


