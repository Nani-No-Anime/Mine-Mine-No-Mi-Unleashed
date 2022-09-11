package xyz.pixelatedw.mineminenomi.particles.effects.doku;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class ChloroBallCloudParticleEffect
  extends ParticleEffect
{
  public boolean venomDemon = false;
  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 64; i++) {
      
      double offsetX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
      double offsetY = WyHelper.randomWithRange(-1, 2) + WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
      
      motionX = WyHelper.randomDouble() / 8.0D;
      motionZ = WyHelper.randomDouble() / 8.0D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.DOKU);
      
      if (this.venomDemon) {
        data.setColor(1.0F, 0.0F, 0.0F);
      }
      data.setLife(5);
      data.setSize(1.5F);
      data.setMotion(motionX, 0.05D, motionZ);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
    } 
  }
}


