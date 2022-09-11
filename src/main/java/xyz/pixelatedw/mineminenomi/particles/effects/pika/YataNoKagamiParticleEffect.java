package xyz.pixelatedw.mineminenomi.particles.effects.pika;

import java.util.Random;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;



public class YataNoKagamiParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int i = 0; i < 20; i++) {
      
      double offsetX = ((new Random()).nextInt(40) + 1.0D - 20.0D) / 20.0D;
      double offsetY = ((new Random()).nextInt(40) + 1.0D) / 20.0D;
      double offsetZ = ((new Random()).nextInt(40) + 1.0D - 20.0D) / 20.0D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.PIKA);
      data.setLife(20);
      data.setSize(4.0F);
      data.setRotation(Vector3f.YP);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + 0.5D + offsetY, posZ + offsetZ);
    } 
  }
}


