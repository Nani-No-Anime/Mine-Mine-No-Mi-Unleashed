package xyz.pixelatedw.mineminenomi.particles.effects.common;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class LogiaParticleEffect
  extends ParticleEffect {
  private ParticleType<GenericParticleData> particle;
  public boolean hideTooClose = false;
  public int ownerID = 0;

  
  public LogiaParticleEffect(ParticleType<GenericParticleData> particle) {
    this.particle = particle;
  }


  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < WyHelper.randomWithRange(3, 6); i++) {
      
      double offsetX = WyHelper.randomDouble() / 1.7D;
      double offsetY = 0.25D + WyHelper.randomDouble() / 3.0D;
      double offsetZ = WyHelper.randomDouble() / 1.7D;
      
      int age = (int)(1.0D + WyHelper.randomWithRange(0, 4));
      motionY = WyHelper.randomDouble() / 50.0D;
      if (motionY < 0.0D) {
        motionY = 0.005D;
      }
      GenericParticleData data = new GenericParticleData(this.particle);
      data.setLife(age);
      data.setSize(1.5F);
      if (this.hideTooClose)
        data.hideTooClose(); 
      data.setEntityID(this.ownerID);
      data.setMotion(0.0D, motionY, 0.0D);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
    } 
  }
}


