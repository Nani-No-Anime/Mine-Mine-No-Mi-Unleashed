package xyz.pixelatedw.mineminenomi.particles.effects.awa;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class GoldenHourParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < WyHelper.randomWithRange(6, 9); i++) {
      
      double offsetX = WyHelper.randomDouble() / 1.7D;
      double offsetY = WyHelper.randomDouble() * 1.55D;
      double offsetZ = WyHelper.randomDouble() / 1.7D;
      
      int age = (int)(3.0D + WyHelper.randomWithRange(0, 4));
      motionY = WyHelper.randomDouble() / 50.0D;
      if (motionY < 0.0D) {
        motionY = 0.02D;
      }
      GenericParticleData data = new GenericParticleData(ModParticleTypes.AWA2);
      data.setLife(age);
      data.setSize(1.5F);
      data.setMotion(0.0D, motionY, 0.0D);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.25D + offsetY, posZ + offsetZ);
      
      data = new GenericParticleData(ModParticleTypes.AWA_FOAM);
      data.setLife(age);
      data.setSize(1.5F);
      data.setMotion(0.0D, motionY / 2.0D, 0.0D);
      data.setColor(1.0F, 1.0F, 0.0F, 0.5F);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX / 2.0D, posY + 1.0D + offsetY / 2.0D, posZ + offsetZ / 2.0D);
    } 
  }
}


