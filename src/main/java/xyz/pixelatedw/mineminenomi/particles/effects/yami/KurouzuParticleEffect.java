package xyz.pixelatedw.mineminenomi.particles.effects.yami;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class KurouzuParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 15; i++) {
      
      double offsetX = WyHelper.randomDouble();
      double offsetY = WyHelper.randomDouble();
      double offsetZ = WyHelper.randomDouble();
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.DARKNESS);
      data.setLife(1);
      data.setSize(15.0F);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + 0.5D + offsetX, posY + offsetY, posZ + offsetZ);
    } 
  }
}


