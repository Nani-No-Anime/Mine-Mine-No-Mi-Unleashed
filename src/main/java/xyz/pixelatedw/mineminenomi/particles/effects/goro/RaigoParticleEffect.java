package xyz.pixelatedw.mineminenomi.particles.effects.goro;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class RaigoParticleEffect
  extends ParticleEffect {
  public int range = 128;
  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    int i;
    for (i = 0; i < this.range; i++) {
      
      double offsetX = WyHelper.randomWithRange(-this.range, this.range) + WyHelper.randomDouble();
      double offsetY = 40.0D + WyHelper.randomWithRange(-5, 5) + WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-this.range, this.range) + WyHelper.randomDouble();
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO3);
      data.setLife(100);
      data.setSize(100.0F);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
      
      if (i % 2 == 0) {
        data.setColor(0.4F, 0.4F, 0.4F);
      } else {
        data.setColor(0.3F, 0.3F, 0.3F);
      } 
    } 
    for (i = 0; i < this.range / 2; i++) {
      
      double offsetX = WyHelper.randomWithRange(-this.range, this.range) + WyHelper.randomDouble();
      double offsetY = 30.0D + WyHelper.randomWithRange(-5, 0) + WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-this.range, this.range) + WyHelper.randomDouble();
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO2);
      data.setLife(10);
      data.setSize(40.0F);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
    } 
  }
}


