package xyz.pixelatedw.mineminenomi.particles.effects.ope;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class CounterShockParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 20; i++) {
      
      double x = WyHelper.randomDouble();
      double y = WyHelper.randomDouble();
      double z = WyHelper.randomDouble();
      
      motionY = 0.005D + Math.abs(WyHelper.randomDouble() / 8.0D);
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO2);
      data.setLife(5);
      data.setSize(3.5F);
      data.setMotion(0.0D, motionY, 0.0D);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + 1.5D + y, posZ + z);
    } 
  }
}


