package xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class BlueFlamesParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 20; i++) {
      
      double offsetX = WyHelper.randomDouble() / 1.25D;
      double offsetY = WyHelper.randomDouble() / 1.25D;
      double offsetZ = WyHelper.randomDouble() / 1.25D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.BLUE_FLAME);
      data.setLife(1);
      data.setSize(2.5F);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
    } 
  }
}


