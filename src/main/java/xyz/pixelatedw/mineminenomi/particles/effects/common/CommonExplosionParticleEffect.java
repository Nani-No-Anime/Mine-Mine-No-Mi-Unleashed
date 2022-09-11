package xyz.pixelatedw.mineminenomi.particles.effects.common;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class CommonExplosionParticleEffect
  extends ParticleEffect {
  private int explosionSize;
  
  public CommonExplosionParticleEffect() {
    this(2);
  }

  
  public CommonExplosionParticleEffect(int explosionSize) {
    this.explosionSize = explosionSize;
  }


  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < this.explosionSize * 2; i++) {
      
      double x = posX + WyHelper.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + WyHelper.randomDouble();
      double y = posY + WyHelper.randomDouble();
      double z = posZ + WyHelper.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + WyHelper.randomDouble();
      
      motionX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
      motionY = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
      motionZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
      
      double middlePoint = 0.5D / ((5 / this.explosionSize) + 0.1D);
      middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
      
      motionX *= middlePoint / 2.0D;
      motionY *= middlePoint / 2.0D;
      motionZ *= middlePoint / 2.0D;
      
      WyHelper.spawnParticles(ParticleTypes.EXPLOSION, (ServerWorld)world, x, y + 1.0D, z);
      WyHelper.spawnParticles(ParticleTypes.POOF, (ServerWorld)world, posX, posY + 1.0D, posZ);
    } 
  }
}


