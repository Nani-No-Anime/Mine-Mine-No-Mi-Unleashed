package xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles;

import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;

public interface IFlexibleSizeProjectile
{
  public static final DataParameter<Float> SIZE = EntityDataManager.createKey(AbilityProjectileEntity.class, DataSerializers.FLOAT);
  
  void setSize(float paramFloat);
  
  float getSize();
}


