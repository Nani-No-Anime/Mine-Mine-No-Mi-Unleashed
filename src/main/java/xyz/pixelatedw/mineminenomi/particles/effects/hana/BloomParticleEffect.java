package xyz.pixelatedw.mineminenomi.particles.effects.hana;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class BloomParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 15; i++) {
      
      double offsetX = WyHelper.randomDouble() / 2.0D;
      double offsetY = WyHelper.randomDouble() / 2.0D;
      double offsetZ = WyHelper.randomDouble() / 2.0D;
      
      motionX = WyHelper.randomDouble();
      motionY = WyHelper.randomDouble();
      motionZ = WyHelper.randomDouble();
      
      double middlePoint = 0.15D;
      middlePoint *= WyHelper.randomDouble() * 2.0D + 0.30000001192092896D;
      
      motionX *= middlePoint / 5.0D;
      motionY *= middlePoint / 8.0D;
      motionZ *= middlePoint / 5.0D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.HANA);
      data.setLife(20);
      data.setSize(3.0F);
      data.setMotion(Math.sin(motionX), motionY - 0.015D, Math.sin(motionZ));
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY + 1.25D, posZ + offsetZ);
    } 
  }
}


