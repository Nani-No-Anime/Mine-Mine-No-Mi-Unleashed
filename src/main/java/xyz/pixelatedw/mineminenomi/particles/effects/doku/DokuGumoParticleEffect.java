package xyz.pixelatedw.mineminenomi.particles.effects.doku;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class DokuGumoParticleEffect
  extends ParticleEffect
{
  public boolean venomDemon = false;
  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    int width = this.venomDemon ? 5 : 3;
    int height = this.venomDemon ? 6 : 3;
    for (int i = 0; i < (this.venomDemon ? 50 : 30); i++) {
      
      double offsetX = WyHelper.randomWithRange(-width, width) + WyHelper.randomDouble();
      double offsetY = WyHelper.randomWithRange(-height, height) + WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-width, width) + WyHelper.randomDouble();
      
      int age = (int)(1.0D + WyHelper.randomWithRange(0, 2));
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.DOKU);
      if (this.venomDemon) {
        data.setColor(1.0F, 0.0F, 0.0F);
      }
      data.setLife(age);
      data.setSize(1.0F);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
    } 
  }
}


