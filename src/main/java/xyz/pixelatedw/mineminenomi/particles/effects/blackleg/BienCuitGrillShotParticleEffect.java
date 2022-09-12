package xyz.pixelatedw.mineminenomi.particles.effects.blackleg;


import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class BienCuitGrillShotParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    for (int k = -2; k <= 2; k++) {
      
      for (int i = -10; i <= 10; i++) {
        
        for (int j = 0; j < 3; j++) {
          
          double offsetX = WyHelper.randomDouble() / 2.0D;
          double offsetY = WyHelper.randomDouble() / 2.0D;
          double offsetZ = WyHelper.randomDouble() / 2.0D;
          
          GenericParticleData genericParticleData = new GenericParticleData(ModParticleTypes.MERA);
          genericParticleData.setLife(10);
          genericParticleData.setSize(1.0F);
          genericParticleData.setMotion(motionX / 1.7D, motionY / 1.7D, motionZ / 1.7D);
          WyHelper.spawnParticles(genericParticleData, (ServerWorld)world, posX - (i / 5) + offsetX, posY + 1.5D + k / 1.2D + offsetY, posZ - (i / 5) + offsetZ);
        } 
      } 
    } 
    
    GenericParticleData data = new GenericParticleData(ModParticleTypes.GRILL);
    data.setLife(10);
    data.setSize(40.0F);
    data.setMotion(motionX / 1.4D, motionY / 1.4D, motionZ / 1.4D);
    WyHelper.spawnParticles(data, (ServerWorld)world, posX, posY + 1.5D, posZ);
  }
}


