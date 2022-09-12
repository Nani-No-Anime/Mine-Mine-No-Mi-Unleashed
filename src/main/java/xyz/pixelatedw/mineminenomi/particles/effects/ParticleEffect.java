package xyz.pixelatedw.mineminenomi.particles.effects;

import net.minecraft.world.World;

import java.io.Serializable;

public abstract class ParticleEffect implements Serializable {
  public abstract void spawn(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6);
}


