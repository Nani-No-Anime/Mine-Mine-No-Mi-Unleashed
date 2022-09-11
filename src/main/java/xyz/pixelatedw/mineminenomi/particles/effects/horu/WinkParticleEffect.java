package xyz.pixelatedw.mineminenomi.particles.effects.horu;

import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class WinkParticleEffect
  extends ParticleEffect {
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    motionX = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
    motionY = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
    motionZ = WyHelper.randomWithRange(-2, 2) + WyHelper.randomDouble();
    
    double middlePoint = 0.1D;
    middlePoint *= WyHelper.randomDouble() * 2.0D + 0.25D;
    
    motionX *= middlePoint / 5.0D;
    motionY *= middlePoint / 5.0D;
    motionZ *= middlePoint / 5.0D;
    
    GenericParticleData data = new GenericParticleData(ModParticleTypes.HORU);
    data.setLife(5);
    data.setSize(5.0F);
    data.setMotion(motionX, motionY, motionZ);
    WyHelper.spawnParticles(data, (ServerWorld)world, posX, posY + 1.5D, posZ);
  }
}


