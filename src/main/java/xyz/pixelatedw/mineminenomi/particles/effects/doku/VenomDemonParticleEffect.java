package xyz.pixelatedw.mineminenomi.particles.effects.doku;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class VenomDemonParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 3; i++) {
      
      double offsetX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
      double offsetY = WyHelper.randomWithRange(-2, 0) + WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
      
      int age = (int)(1.0D + WyHelper.randomWithRange(0, 4));
      motionY = 0.015D + Math.abs(WyHelper.randomDouble() / 8.0D);
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.DOKU);
      data.setLife(age);
      data.setSize(0.8F);
      data.setColor(1.0F, 0.0F, 0.0F);
      data.setMotion(0.0D, motionY, 0.0D);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
    } 
  }
}


