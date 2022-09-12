package xyz.pixelatedw.mineminenomi.particles.effects.yuki;


import net.minecraft.particles.ParticleType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class YukiGakiParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 100; i++) {
      
      double offsetX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
      double offsetY = WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
      
      motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
      motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
      
      double middlePoint = 1.2D;
      
      motionX *= middlePoint / 25.0D;
      motionZ *= middlePoint / 25.0D;
      
      float scale = (float)(1.0D + WyHelper.randomWithRange(0, 3));
      
      ParticleType<GenericParticleData> particle = ModParticleTypes.YUKI;
      if (i % 5 == 0) {
        particle = ModParticleTypes.YUKI3;
      } else if (i % 2 == 0) {
        particle = ModParticleTypes.YUKI2;
      } 
      GenericParticleData data = new GenericParticleData(particle);
      data.setLife(200);
      data.setSize(scale);
      data.setMotion(motionX, -0.05D, motionZ);
      data.setHasMotionDecay(false);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
    } 
  }
}


