package xyz.pixelatedw.mineminenomi.particles.effects.suna;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class DesertSpadaParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 70; i++) {
      
      double offsetX = WyHelper.randomDouble() * 0.5D;
      double offsetZ = WyHelper.randomDouble() * 0.5D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA2);
      data.setLife(20);
      data.setSize(1.5F);
      data.setMotion(motionX, motionY + 0.25D + Math.abs(WyHelper.randomDouble()) / 3.0D, motionZ);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY, posZ + offsetZ);
      data.setLife(60);
      data.setMotion(0.0D, 0.25D + Math.abs(WyHelper.randomDouble()) / 4.0D, 0.0D);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY, posZ + offsetZ);
    } 
  }
}


