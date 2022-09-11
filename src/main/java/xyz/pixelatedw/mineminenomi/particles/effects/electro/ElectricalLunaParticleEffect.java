package xyz.pixelatedw.mineminenomi.particles.effects.electro;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class ElectricalLunaParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 20; i++) {
      GenericParticleData data;
      double offsetX = WyHelper.randomDouble();
      double offsetY = WyHelper.randomDouble();
      double offsetZ = WyHelper.randomDouble();


      
      if (i % 2 == 0) {
        data = new GenericParticleData(ModParticleTypes.GORO2);
      } else {
        data = new GenericParticleData(ModParticleTypes.GORO);
      }  data.setLife(5);
      data.setSize(7.0F);
      data.setColor(1.0F, 1.0F, 1.0F, 0.5F);
      data.setMotion(0.0D, WyHelper.randomDouble() / 3.0D, 0.0D);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
    } 
  }
}


