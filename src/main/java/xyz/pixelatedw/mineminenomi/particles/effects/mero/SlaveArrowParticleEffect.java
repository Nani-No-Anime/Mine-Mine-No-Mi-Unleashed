package xyz.pixelatedw.mineminenomi.particles.effects.mero;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class SlaveArrowParticleEffect extends ParticleEffect {
  private float size = 1.0F;

  
  public void setSize(float size) {
    this.size = size;
  }

  
  public float getSize() {
    return this.size;
  }


  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    GenericParticleData data = new GenericParticleData(ModParticleTypes.MERO);
    data.setLife(-5);
    data.setSize(this.size);
    data.setColor(1.0F, 1.0F, 1.0F, 0.7F);
    data.setMotion(motionX, motionY, motionZ);
    WyHelper.spawnParticles(data, (ServerWorld)world, posX, posY + 1.5D, posZ);
  }
}


