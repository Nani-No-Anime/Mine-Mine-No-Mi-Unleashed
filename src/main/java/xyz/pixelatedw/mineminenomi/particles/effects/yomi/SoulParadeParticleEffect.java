package xyz.pixelatedw.mineminenomi.particles.effects.yomi;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class SoulParadeParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 64; i++) {
      
      motionX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
      motionY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
      motionZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
      
      double middlePoint = 0.1D;
      middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
      
      motionX *= middlePoint / 2.0D;
      motionY *= middlePoint / 2.0D;
      motionZ *= middlePoint / 2.0D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.HIE);
      data.setLife(10);
      data.setSize(1.0F);
      data.setMotion(motionX, motionY, motionZ);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX, posY + 1.0D, posZ);
    } 
  }
}


