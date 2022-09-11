package xyz.pixelatedw.mineminenomi.particles.effects.gasu;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class GastanetParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 228; i++) {
      GenericParticleData data;
      double offsetX = WyHelper.randomDouble();
      double offsetY = WyHelper.randomDouble();
      double offsetZ = WyHelper.randomDouble();
      
      motionX = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
      motionY = WyHelper.randomWithRange(0, 5) + WyHelper.randomDouble();
      motionZ = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
      
      double middlePoint = 2.8D;
      
      motionX *= middlePoint / 15.0D;
      motionY *= middlePoint / 25.0D;
      motionZ *= middlePoint / 15.0D;
      
      motionY = Math.abs(motionY);

      
      if (i % 4 == 0) {
        data = new GenericParticleData(ModParticleTypes.GASU);
      } else {
        data = new GenericParticleData(ModParticleTypes.GASU2);
      } 
      data.setLife(7);
      data.setSize(1.5F);
      data.setMotion(motionX, motionY, motionZ);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 0.25D + offsetY, posZ + offsetZ);
    } 
  }
}


