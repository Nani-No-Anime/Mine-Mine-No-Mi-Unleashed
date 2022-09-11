package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;

public interface IDeathAbility {
  boolean onUserDeath(LivingEntity paramLivingEntity, DamageSource paramDamageSource);
}


