package xyz.pixelatedw.mineminenomi.particles.effects.horu;


import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class WinkExplosionEffect
  extends ParticleEffect
{
  private int explosionSize;
  
  public WinkExplosionEffect() {
    this(2);
  }

  
  public WinkExplosionEffect(int explosionSize) {
    this.explosionSize = explosionSize;
  }


  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < this.explosionSize * 2; i++) {
      
      double x = posX + WyHelper.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + WyHelper.randomDouble();
      double y = posY + WyHelper.randomDouble();
      double z = posZ + WyHelper.randomWithRange(-this.explosionSize / 2, this.explosionSize / 2) + WyHelper.randomDouble();
      
      motionX = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
      motionY = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
      motionZ = WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
      
      double middlePoint = 0.5D / (5.0D / this.explosionSize + 0.1D);
      middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
      
      motionX *= middlePoint / 2.0D;
      motionY *= middlePoint / 2.0D;
      motionZ *= middlePoint / 2.0D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.HORU);
      data.setLife(20);
      data.setSize(6.0F);
      data.setMotion(motionX, motionY, motionZ);
      WyHelper.spawnParticles(data, (ServerWorld)world, x, y + 1.0D, z);
      WyHelper.spawnParticles(ParticleTypes.EXPLOSION, (ServerWorld)world, x, y, z);
      WyHelper.spawnParticles(ParticleTypes.POOF, (ServerWorld)world, posX, posY + 1.0D, posZ);
    } 
  }
}


