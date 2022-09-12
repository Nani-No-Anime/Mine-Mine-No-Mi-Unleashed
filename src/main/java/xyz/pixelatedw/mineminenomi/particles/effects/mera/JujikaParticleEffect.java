package xyz.pixelatedw.mineminenomi.particles.effects.mera;

import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.Random;


public class JujikaParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double dirId) {
    Direction dir = Direction.values()[(int)dirId];
    Random rand = world.rand; float i;
    for (i = 0.0F; i <= 5.0F; i += 0.5F) {
      GenericParticleData data;

      
      int x = 0;
      int z = 0;
      
      z = (int)(z + i * dir.getXOffset());
      x = (int)(x + i * dir.getZOffset());
      
      if (rand.nextInt(10) % 2 == 0) {
        data = new GenericParticleData(ModParticleTypes.MERA);
      } else {
        data = new GenericParticleData(ModParticleTypes.MERA2);
      }  data.setLife((int)WyHelper.randomWithRange(1, 2));
      data.setSize((float)WyHelper.randomWithRange(0, 2));
      data.setColor(1.0F, 1.0F, 1.0F, 1.0F);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX - x, posY, posZ - z);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY, posZ + z);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX, posY + i, posZ);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX, posY - i, posZ);
    } 
  }
}


