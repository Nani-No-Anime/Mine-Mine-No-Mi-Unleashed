package xyz.pixelatedw.mineminenomi.particles.effects.doctor;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class FirstAidParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 45; i++) {
      GenericParticleData data;
      motionX = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
      motionY = WyHelper.randomWithRange(1, 2);
      motionZ = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
      
      double middlePoint = 0.1D;
      middlePoint *= (world.rand.nextFloat() * 2.0F + 0.3F);
      
      motionX *= middlePoint / 50.0D;
      motionY *= middlePoint / 2.0D;
      motionZ *= middlePoint / 50.0D;
      
      double offsetX = WyHelper.randomDouble() / 1.2D;
      double offsetZ = WyHelper.randomDouble() / 1.2D;


      
      if (i % 2 == 0) {
        data = new GenericParticleData(ModParticleTypes.CHIYU);
      } else {
        data = new GenericParticleData(ModParticleTypes.PIKA);
      } 
      data.setLife(10);
      data.setSize(1.5F);
      data.setColor(0.0F, 0.8F, 0.0F);
      data.setMotion(motionX, motionY, motionZ);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 0.5D, posZ + offsetZ);
    } 
  }
}


