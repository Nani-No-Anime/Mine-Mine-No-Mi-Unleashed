package xyz.pixelatedw.mineminenomi.particles.effects.artofweather;

import net.minecraft.particles.ParticleType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.awt.*;

public class ChargedWeatherBallParticleEffect extends ParticleEffect {
  private float red;
  private float green;
  private float blue;
  private ParticleType<GenericParticleData> particle;
  
  public ChargedWeatherBallParticleEffect(Color color, ParticleType<GenericParticleData> particle) {
    this.red = color.getRed() / 255.0F;
    this.green = color.getGreen() / 255.0F;
    this.blue = color.getBlue() / 255.0F;
    this.particle = particle;
  }


  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 10; i++) {
      
      double offsetX = WyHelper.randomDouble() / 1.5D;
      double offsetY = WyHelper.randomDouble() / 1.5D;
      double offsetZ = WyHelper.randomDouble() / 1.5D;
      
      GenericParticleData data = new GenericParticleData(this.particle);
      data.setLife(4);
      data.setSize(2.0F);
      data.setMotion(0.0D, 0.02D, 0.0D);
      data.setColor(this.red, this.green, this.blue);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 1.0D + offsetY, posZ + offsetZ);
    } 
  }
}


