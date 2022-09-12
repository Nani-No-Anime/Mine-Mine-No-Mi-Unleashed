package xyz.pixelatedw.mineminenomi.particles.effects.mera;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class DaiEnkaiParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 10; i++) {
      
      double offsetX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
      double offsetY = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
      
      motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
      motionY = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
      motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
      
      double middlePoint = 0.05D;
      middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
      
      motionX *= middlePoint / 2.0D;
      motionY *= middlePoint / 2.0D;
      motionZ *= middlePoint / 2.0D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
      data.setLife(20);
      data.setSize(1.3F);
      data.setMotion(motionX, motionY + 0.05D, motionZ);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.5D + offsetY, posZ + offsetZ);
    } 
  }
}


