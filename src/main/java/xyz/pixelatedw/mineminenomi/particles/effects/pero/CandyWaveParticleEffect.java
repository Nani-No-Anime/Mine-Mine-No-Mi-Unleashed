package xyz.pixelatedw.mineminenomi.particles.effects.pero;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class CandyWaveParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 40; i++) {
      
      double offsetX = WyHelper.randomDouble() * 3.0D;
      double offsetZ = WyHelper.randomDouble() * 3.0D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA2);
      data.setLife(8);
      data.setSize(1.4F);
      data.setMotion(0.0D, 0.2D + Math.abs(WyHelper.randomDouble()) / 3.0D, 0.0D);
      data.setColor(0.5F, 0.0F, 0.5F);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY, posZ + offsetZ);
    } 
  }
}


