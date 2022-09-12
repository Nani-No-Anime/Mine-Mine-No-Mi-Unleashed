package xyz.pixelatedw.mineminenomi.particles.effects.extra;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class MH5GasParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 1024; i++) {
      
      double offsetX = WyHelper.randomWithRange(-100, 100) + WyHelper.randomDouble();
      double offsetY = WyHelper.randomWithRange(-10, 10) + WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-100, 100) + WyHelper.randomDouble();
      
      int age = (int)(3.0D + WyHelper.randomWithRange(0, 4));
      motionY = WyHelper.randomDouble() / 50.0D;
      if (motionY < 0.0D) {
        motionY = 0.02D;
      }
      GenericParticleData data = new GenericParticleData(ModParticleTypes.DOKU);
      if (i % 2 == 0) {
        
        data = new GenericParticleData(ModParticleTypes.AWA_FOAM);
        data.setLife(age);
        data.setSize(1.5F);
        data.setMotion(0.0D, motionY / 2.0D, 0.0D);
        data.setColor(1.0F, 0.0F, 1.0F, 0.5F);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX / 2.0D, posY + 1.0D + offsetY / 2.0D, posZ + offsetZ / 2.0D);
      }
      else {
        
        data.setLife(age);
        data.setSize(1.5F);
        data.setMotion(0.0D, motionY / 2.0D, 0.0D);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX / 2.0D, posY + 1.0D + offsetY / 2.0D, posZ + offsetZ / 2.0D);
      } 
    } 
  }
}


