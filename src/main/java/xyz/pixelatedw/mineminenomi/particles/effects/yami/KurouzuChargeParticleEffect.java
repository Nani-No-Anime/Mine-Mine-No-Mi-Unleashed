package xyz.pixelatedw.mineminenomi.particles.effects.yami;

import net.minecraft.client.renderer.Vector3f;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class KurouzuChargeParticleEffect
  extends ParticleEffect
{
  public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
    GenericParticleData data = new GenericParticleData(ModParticleTypes.KUROUZU);
    data.setColor(1.0F, 1.0F, 1.0F, 0.4F);
    data.setLife(1);
    data.setSize(10.0F);
    data.setHasScaleDecay(false);
    data.setRotation(Vector3f.ZP);
    WyHelper.spawnParticles(data, (ServerWorld)world, posX, posY, posZ);
  }
}


