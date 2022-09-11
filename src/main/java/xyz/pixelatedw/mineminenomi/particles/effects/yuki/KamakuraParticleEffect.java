package xyz.pixelatedw.mineminenomi.particles.effects.yuki;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class KamakuraParticleEffect extends ParticleEffect {
  public int size = 4;
  public int life = 100;

  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double radius = this.size * 1.25D;
    for (int i = 0; i < 10; i++) {
      
      double phi = 0.0D;
      while (phi < Math.PI) {
        
        phi += 0.19634954084936207D;
        double theta;
        for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.19634954084936207D) {
          
          double x = radius * Math.cos(theta) * Math.sin(phi);
          double y = radius * Math.cos(phi);
          double z = radius * Math.sin(theta) * Math.sin(phi);
          
          ParticleType<GenericParticleData> particle = ModParticleTypes.YUKI;
          if (i % 5 == 0) {
            particle = ModParticleTypes.YUKI3;
          } else if (i % 2 == 0) {
            particle = ModParticleTypes.YUKI2;
          } 
          motionX = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
          motionZ = WyHelper.randomWithRange(-1, 1) + WyHelper.randomDouble();
          
          double middlePoint = 1.2D;
          
          motionX *= middlePoint / 25.0D;
          motionZ *= middlePoint / 25.0D;
          
          float scale = (float)(1.0D + WyHelper.randomWithRange(0, 3));
          GenericParticleData data = new GenericParticleData(particle);
          data.setLife(this.life);
          data.setMotion(motionX, -0.05D, motionZ);
          data.setSize(scale);
          WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + y, posZ + z);
        } 
      } 
      
      radius += 0.8D;
    } 
  }
}


