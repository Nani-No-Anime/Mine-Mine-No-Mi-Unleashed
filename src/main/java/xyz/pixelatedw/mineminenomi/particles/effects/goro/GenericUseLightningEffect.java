package xyz.pixelatedw.mineminenomi.particles.effects.goro;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class GenericUseLightningEffect
  extends ParticleEffect {
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 25; i++) {
      
      double offsetX = WyHelper.randomDouble();
      double offsetY = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble() / 2.0D;
      double offsetZ = WyHelper.randomDouble();
      
      int age = (int)WyHelper.randomWithRange(2, 8);
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO);
      data.setLife(age);
      data.setSize(0.4F);
      data.setMotion(motionX, 0.01D, motionZ);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
    } 
  }
}


