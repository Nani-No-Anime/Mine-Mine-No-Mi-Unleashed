package xyz.pixelatedw.mineminenomi.particles.effects.moku;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class WhiteLauncherParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 20; i++) {
      
      double offsetX = WyHelper.randomDouble() / 5.0D;
      double offsetY = WyHelper.randomDouble() / 5.0D;
      double offsetZ = WyHelper.randomDouble() / 5.0D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
      data.setLife(20);
      data.setSize(1.3F);
      data.setMotion(0.0D, 0.05D, 0.0D);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.5D + offsetY, posZ + offsetZ);
    } 
  }
}


