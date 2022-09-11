package xyz.pixelatedw.mineminenomi.particles.effects.beta;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class HanamizuShinkenShirahadoriEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 64; i++) {
      
      double offsetX = WyHelper.randomDouble() / 1.2D;
      double offsetY = WyHelper.randomDouble();
      double offsetZ = WyHelper.randomDouble() / 1.2D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.BETA);
      data.setLife(2);
      data.setSize(1.0F);
      data.setColor(1.0F, 1.0F, 1.0F, 0.5F);
      data.setMotion(0.0D, 0.1D, 0.0D);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
    } 
  }
}


