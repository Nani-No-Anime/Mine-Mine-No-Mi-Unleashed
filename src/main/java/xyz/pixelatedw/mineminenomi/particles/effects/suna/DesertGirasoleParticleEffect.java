package xyz.pixelatedw.mineminenomi.particles.effects.suna;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class DesertGirasoleParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 64; i++) {
      
      double offsetX = WyHelper.randomWithRange(-15, 15) + WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-15, 15) + WyHelper.randomDouble();
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA2);
      data.setLife((int)(80.0D + 0.2D * i));
      data.setSize(4.0F);
      data.setMotion(0.0D, 0.3D, 0.0D);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 0.5D - 0.15D * i, posZ + offsetZ);
    } 
  }
}


