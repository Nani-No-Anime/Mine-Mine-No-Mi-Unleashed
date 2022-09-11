package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.LivingEntity;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;

public interface IMorphAbility {
  ZoanInfo getTransformation();
  
  boolean isTransformationActive(LivingEntity paramLivingEntity);
}


