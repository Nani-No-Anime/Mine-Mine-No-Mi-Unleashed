package xyz.pixelatedw.mineminenomi.particles.effects.kachi;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class EvaporateParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 3; i++) {
      
      double offsetX = WyHelper.randomDouble();
      double offsetY = WyHelper.randomDouble();
      double offsetZ = WyHelper.randomDouble();
      
      motionX = WyHelper.randomWithRange(0, 1) + WyHelper.randomDouble();
      motionY = WyHelper.randomWithRange(0, 1) + WyHelper.randomDouble();
      motionZ = WyHelper.randomWithRange(0, 1) + WyHelper.randomDouble();
      
      double middlePoint = 0.05D;
      middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
      
      motionX *= middlePoint / 2.0D;
      motionY *= middlePoint / 2.0D;
      motionZ *= middlePoint / 2.0D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
      data.setLife(10);
      data.setSize(2.5F);
      data.setMotion(motionX, motionY + 0.05D, motionZ);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + 1.5D + offsetX, posY + offsetY, posZ + offsetZ);
      
      offsetX = WyHelper.randomDouble();
      offsetY = WyHelper.randomDouble();
      offsetZ = WyHelper.randomDouble();
      
      data = new GenericParticleData(ModParticleTypes.MERA);
      data.setLife(10);
      data.setSize(2.5F);
      data.setMotion(motionX, motionY + 0.05D, motionZ);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + 1.5D + offsetX, posY + offsetY, posZ + offsetZ);
    } 
  }
}


