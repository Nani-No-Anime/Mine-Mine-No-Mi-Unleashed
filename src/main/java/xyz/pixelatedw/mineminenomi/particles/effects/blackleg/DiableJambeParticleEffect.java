package xyz.pixelatedw.mineminenomi.particles.effects.blackleg;

import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class DiableJambeParticleEffect
  extends ParticleEffect {
  public void spawn(PlayerEntity player, World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double rotX = 0.0D, rotZ = 0.0D;
    
    float rotation = Math.abs(player.rotationYaw);
    if (rotation >= 280.0F || rotation < 30.0F) {
      
      rotX = -0.1D;
      rotZ = 0.2D;
    }
    else if (rotation >= 30.0F && rotation < 120.0F) {
      
      rotX = 0.2D;
      rotZ = 0.1D;
    }
    else if (rotation >= 120.0F && rotation < 200.0F) {
      
      rotX = 0.1D;
      rotZ = -0.2D;
    }
    else if (rotation >= 200.0F && rotation < 280.0F) {
      
      rotX = -0.2D;
      rotZ = -0.1D;
    } 
    
    for (int i = 0; i < 2; i++) {
      
      double offsetX = rotX + WyHelper.randomDouble() / 5.0D;
      double offsetY = 0.4D + WyHelper.randomDouble() / 2.5D;
      double offsetZ = rotZ + WyHelper.randomDouble() / 5.0D;
      
      int age = (int)(1.0D + WyHelper.randomWithRange(0, 2));
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
      data.setLife(age);
      data.setSize(age / 2.5F);
      data.setMotion(0.0D, 0.02D, 0.0D);
      WyHelper.spawnParticles(data, (ServerWorld)world, posX + offsetX, posY + offsetY, posZ + offsetZ);
    } 
  }
  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {}
}


