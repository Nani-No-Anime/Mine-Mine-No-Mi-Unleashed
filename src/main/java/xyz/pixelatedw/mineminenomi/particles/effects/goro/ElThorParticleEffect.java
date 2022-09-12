package xyz.pixelatedw.mineminenomi.particles.effects.goro;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class ElThorParticleEffect extends ParticleEffect {
  public boolean aiming = false;
  public int ownerID = 0;

  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    int i;
    for (i = 0; i < 30; i++) {
      
      double offsetX = WyHelper.randomWithRange(-32, 32) + WyHelper.randomDouble();
      double offsetY = 72.0D + WyHelper.randomWithRange(-3, 3) + WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-32, 32) + WyHelper.randomDouble();
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO3);
      data.setLife(140);
      data.setSize(50.0F);
      
      if (i % 2 == 0) {
        data.setColor(0.4F, 0.4F, 0.4F);
      } else {
        data.setColor(0.3F, 0.3F, 0.3F);
      } 
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + 0.5D + offsetX, posY + offsetY, posZ + offsetZ);
    } 
    
    for (i = 0; i < 16; i++) {
      
      double offsetX = WyHelper.randomWithRange(-4, 4) + WyHelper.randomDouble();
      double offsetY = 72.0D + WyHelper.randomDouble();
      double offsetZ = WyHelper.randomWithRange(-4, 4) + WyHelper.randomDouble();
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO2);
      data.setLife(140);
      data.setSize(15.0F);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + 0.5D + offsetX, posY + offsetY, posZ + offsetZ);
    } 
    
    if (this.aiming)
    {
      for (i = 0; i < 3; i++) {
        
        double offsetX = WyHelper.randomDouble();
        double offsetZ = WyHelper.randomDouble();
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.GORO);
        data.setLife(1);
        data.setEntityID(this.ownerID);
        data.hideFromOthers();
        data.setSize(10.0F);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + 0.5D + offsetX, posY, posZ + offsetZ);
      } 
    }
  }
}


