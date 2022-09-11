package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.LivingEntity;

public interface IFallDamageBlockingAbility {
  void resetFallDamage(LivingEntity paramLivingEntity);
  
  boolean hasFallDamage();
}


