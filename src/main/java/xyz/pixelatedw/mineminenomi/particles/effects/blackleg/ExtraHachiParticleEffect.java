package xyz.pixelatedw.mineminenomi.particles.effects.blackleg;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class ExtraHachiParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 4; i++) {
      
      double offsetX = WyHelper.randomDouble() / 3.0D;
      double offsetY = WyHelper.randomDouble() / 3.0D;
      double offsetZ = WyHelper.randomDouble() / 3.0D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
      data.setLife(10);
      data.setSize(1.0F);
      data.setMotion(offsetX / 12.0D, offsetY / 5.0D + 0.05D, offsetZ / 12.0D);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
    } 
  }
}


