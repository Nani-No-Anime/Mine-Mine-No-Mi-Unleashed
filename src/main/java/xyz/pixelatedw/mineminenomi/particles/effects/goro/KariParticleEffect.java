package xyz.pixelatedw.mineminenomi.particles.effects.goro;

import net.minecraft.client.renderer.Vector3f;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class KariParticleEffect
  extends ParticleEffect {
  private int range;
  private float size;
  
  public KariParticleEffect(int range) {
    this.range = range;
  }


  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 16 * this.range; i++) {
      
      double offsetX = WyHelper.randomWithRange(-this.range, this.range) + WyHelper.randomDouble();
      double offsetY = WyHelper.randomWithRange(-this.range, 2 + this.range) + WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-this.range, this.range) + WyHelper.randomDouble();
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO2);
      data.setLife(5);
      data.setSize(this.size);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
      
      GenericParticleData data2 = new GenericParticleData(ModParticleTypes.GORO);
      data2.setLife(5);
      data2.setSize(this.size);
      data.setRotation(Vector3f.YP);
      WyHelper.spawnParticles(data2, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
    } 
  }

  
  public void setRange(int range) {
    this.range = range;
  }

  
  public void setSize(float range) {
    this.size = range;
  }
}


