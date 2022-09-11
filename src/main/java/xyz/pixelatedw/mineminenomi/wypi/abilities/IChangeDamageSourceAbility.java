package xyz.pixelatedw.mineminenomi.wypi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public interface IChangeDamageSourceAbility {
  float damageToEntityWithSource(PlayerEntity paramPlayerEntity, LivingEntity paramLivingEntity);
  
  DamageSource getSourceToUse(PlayerEntity paramPlayerEntity);
  
  boolean cancelsOriginalDamage();
  
  boolean isSourceChangeEnabled();
}


