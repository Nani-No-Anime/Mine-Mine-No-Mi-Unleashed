package xyz.pixelatedw.mineminenomi.api.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public interface IHitEntityObjective {
  boolean checkHit(PlayerEntity paramPlayerEntity, LivingEntity paramLivingEntity, DamageSource paramDamageSource, float paramFloat);
}


