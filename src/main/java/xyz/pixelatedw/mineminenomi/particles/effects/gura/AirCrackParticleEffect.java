package xyz.pixelatedw.mineminenomi.particles.effects.gura;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class AirCrackParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    GenericParticleData data = new GenericParticleData(ModParticleTypes.GURA);
    data.setLife(20);
    data.setSize(16.0F);
    data.setColor(1.0F, 1.0F, 1.0F, 0.7F);
    WyHelper.spawnParticles(data, (ServerWorld)world, posX + Math.random() / 2.0D, posY + Math.random() / 2.0D, posZ + Math.random() / 2.0D);
  }
}


