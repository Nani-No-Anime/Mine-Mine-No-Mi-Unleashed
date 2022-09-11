package xyz.pixelatedw.mineminenomi.particles.effects.suna;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class SablesPesadoChargingParticleEffect
  extends ParticleEffect {
  public float multiplier = 1.0F;


  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double phi = 0.0D;

    
    GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA2);
    data.setLife(20);
    data.setSize(1.3F);
    
    while (phi < 6.283185307179586D) {
      
      phi += 0.19634954084936207D; double t;
      for (t = 0.0D; t <= 6.283185307179586D; t += 0.19634954084936207D) {
        
        double x = 0.45D * this.multiplier * (6.283185307179586D + t) * Math.cos(t * phi * Math.PI) + WyHelper.randomDouble();
        double y = 1.5D * this.multiplier * t;
        double z = 0.45D * this.multiplier * (6.283185307179586D + t) * Math.sin(t * phi * Math.PI) + WyHelper.randomDouble();
        
        data.setMotion(motionX, motionY + 0.01D + Math.abs(WyHelper.randomDouble()) / 5.0D, motionZ);
        
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + y, posZ + z);
      } 
    } 
  }
}


