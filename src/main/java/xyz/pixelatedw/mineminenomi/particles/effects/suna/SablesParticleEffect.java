package xyz.pixelatedw.mineminenomi.particles.effects.suna;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class SablesParticleEffect extends ParticleEffect {
  public float mult = 10.0F;
  int angle = 0;


  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    int maxHeight = 15 + (int)(10.0F * this.mult);
    double minRadius = 0.4D + 1.2D * this.mult;
    double maxRadius = (1.0F + 3.0F * this.mult);
    int lines = 12;
    double heightIncrease = 0.15D;
    double radiusIncrement = maxRadius / maxHeight * (1.0F - this.mult / 2.0F);
    for (int l = 0; l < lines; l++) {
      double y;
      for (y = 0.0D; y < maxHeight; y += heightIncrease) {
        
        double radius = y * radiusIncrement;
        double v = (360.0F / lines * l) + y * 25.0D;
        double x = Math.cos(Math.toRadians(v - this.angle)) * Math.max(radius, minRadius);
        double z = Math.sin(Math.toRadians(v - this.angle)) * Math.max(radius, minRadius);
        GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA2);
        data.setLife(10);
        data.setSize(0.25F + 1.5F * this.mult);
        data.setMotion(motionX, motionY + 0.1D - Math.abs(WyHelper.randomDouble() / 5.0D), motionZ);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + y, posZ + z);
      } 
    } 
    this.angle += 2;
  }
}


