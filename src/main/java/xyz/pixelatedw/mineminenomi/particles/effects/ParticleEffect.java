package xyz.pixelatedw.mineminenomi.particles.effects;

import java.io.Serializable;
import net.minecraft.world.World;

public abstract class ParticleEffect implements Serializable {
  public abstract void spawn(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6);
}


