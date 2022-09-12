package xyz.pixelatedw.mineminenomi.particles.effects.pika;

import net.minecraft.client.renderer.Vector3f;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class ChargingPikaParticleEvent
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 2; i++) {
      
      double offsetX = WyHelper.randomDouble() * 1.55D;
      double offsetY = WyHelper.randomDouble();
      double offsetZ = WyHelper.randomDouble() * 1.55D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.PIKA);
      data.setLife(5);
      data.setSize(3.0F);
      data.setMotion(0.0D, 0.15D, 0.0D);
      data.setRotation(Vector3f.YP);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 0.5D + offsetY, posZ + offsetZ);
    } 
  }
}


