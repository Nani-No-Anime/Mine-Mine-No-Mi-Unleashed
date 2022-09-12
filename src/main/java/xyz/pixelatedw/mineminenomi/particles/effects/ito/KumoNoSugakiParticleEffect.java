package xyz.pixelatedw.mineminenomi.particles.effects.ito;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class KumoNoSugakiParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    GenericParticleData data = new GenericParticleData(ModParticleTypes.ITO);
    data.setLife(1);
    data.setSize(20.0F);
    data.setColor(1.0F, 1.0F, 1.0F, 0.7F);
    data.setMotion(motionX, motionY, motionZ);
    WyHelper.spawnParticles(data, (ServerWorld)world, posX, posY + 1.2D, posZ);
  }
}


