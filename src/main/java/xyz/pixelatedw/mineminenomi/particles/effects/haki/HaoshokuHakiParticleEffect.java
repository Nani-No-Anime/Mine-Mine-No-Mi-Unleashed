package xyz.pixelatedw.mineminenomi.particles.effects.haki;

import java.util.Random;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class HaoshokuHakiParticleEffect
  extends ParticleEffect
{
  private int level;
  
  public HaoshokuHakiParticleEffect(int level) {
    this.level = level;
  }


  
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    double phi = 0.0D;
    
    double radius = 1.0D;
    Random rand = world.rand;
    
    int i = 0;
    while (phi < 1.0D) {
      
      phi += 1.5707963267948966D;
      double theta;
      for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.04908738521234052D) {
        GenericParticleData data;
        double x = phi * Math.cos(theta);
        double y = WyHelper.randomDouble() / 2.0D;
        double z = phi * Math.sin(theta);
        
        motionX = x * 1.2D;
        motionY = 0.2D + rand.nextDouble() / 8.0D;
        motionZ = z * 1.2D;

        
        if (i % 3 == 0) {
          data = new GenericParticleData(ModParticleTypes.GASU2);
        } else {
          data = new GenericParticleData(ModParticleTypes.MOKU);
        }  data.setLife(20);
        data.setSize(8.0F);
        data.setMotion(motionX, motionY / 2.0D, motionZ);
        data.setColor(0.7F, 0.0F, 0.7F, 0.5F);
        WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY - 0.2D + y, posZ + z);
        
        if (this.level >= 2) {
          
          if (i % 3 == 0) {
            data = new GenericParticleData(ModParticleTypes.GASU2);
          } else {
            data = new GenericParticleData(ModParticleTypes.MOKU);
          }  data.setLife(20);
          data.setSize(7.0F);
          data.setMotion(motionX, motionY / 1.5D, motionZ);
          data.setColor(0.7F, 0.0F, 0.7F, 0.5F);
          WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.35D, posY + 0.2D + y, posZ + z * 1.35D);
          
          if (i % 3 == 0) {
            data = new GenericParticleData(ModParticleTypes.GASU2);
          } else {
            data = new GenericParticleData(ModParticleTypes.MOKU);
          }  data.setLife(20);
          data.setSize(7.0F);
          data.setMotion(motionX, motionY, motionZ);
          data.setColor(0.7F, 0.0F, 0.7F, 0.5F);
          WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.25D, posY + 1.2D + y, posZ + z * 1.25D);
          
          if (i % 3 == 0) {
            data = new GenericParticleData(ModParticleTypes.GASU2);
          } else {
            data = new GenericParticleData(ModParticleTypes.MOKU);
          }  data.setLife(20);
          data.setSize(8.0F);
          data.setMotion(motionX, motionY, motionZ);
          data.setColor(0.7F, 0.0F, 0.7F, 0.5F);
          WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 0.85D, posY + 2.2D + y, posZ + z * 0.85D);
        } 
        
        if (this.level >= 3)
        {
          for (int n = 0; n < 12; n++) {
            
            x = radius * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble();
            y = radius * Math.cos(phi) + WyHelper.randomDouble() * 2.0D;
            z = radius * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble();
            
            if (i % 3 == 0) {
              data = new GenericParticleData(ModParticleTypes.GASU);
            } else {
              data = new GenericParticleData(ModParticleTypes.YUKI);
            }  data.setLife(18);
            data.setSize(5.0F);
            data.setMotion(motionX / 1.1D, 0.3D, motionZ / 1.1D);
            data.setColor(0.7F, 0.0F, 0.7F);
            WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY - 0.5D + y, posZ + z);
            
            motionY = Math.abs(y * 1.01D);
            
            if (i % 3 == 0) {
              data = new GenericParticleData(ModParticleTypes.GASU);
            } else {
              data = new GenericParticleData(ModParticleTypes.YUKI);
            }  data.setLife(18);
            data.setSize(5.0F);
            data.setMotion(motionX / 1.1D, motionY / 1.5D, motionZ / 1.1D);
            data.setColor(0.7F, 0.0F, 0.7F);
            WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY - 0.5D + y, posZ + z);
          } 
        }
        i++;
      } 
    } 
  }
}


