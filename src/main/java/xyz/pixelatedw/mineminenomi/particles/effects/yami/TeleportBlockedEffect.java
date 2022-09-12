package xyz.pixelatedw.mineminenomi.particles.effects.yami;

import net.minecraft.client.renderer.Vector3f;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class TeleportBlockedEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 10; i++) {
      
      double offsetX = WyHelper.randomDouble() / 1.25D;
      double offsetY = WyHelper.randomDouble() * 1.25D;
      double offsetZ = WyHelper.randomDouble() / 1.25D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.DARKNESS);
      data.setLife(5);
      data.setSize(4.0F);
      data.setRotation(Vector3f.YP);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
    } 
  }
}


