package xyz.pixelatedw.mineminenomi.particles.effects.kage;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class ChargeableKageParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 2; i++) {
      
      double offsetX = WyHelper.randomDouble() * 0.55D;
      double offsetY = WyHelper.randomDouble();
      double offsetZ = WyHelper.randomDouble() * 0.55D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.DARKNESS);
      data.setLife(5);
      data.setSize(2.0F);
      data.setMotion(0.0D, 0.1D, 0.0D);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 0.5D + offsetY, posZ + offsetZ);
    } 
  }
}


