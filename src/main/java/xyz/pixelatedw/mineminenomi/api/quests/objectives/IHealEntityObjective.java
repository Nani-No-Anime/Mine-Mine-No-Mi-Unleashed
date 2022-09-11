package xyz.pixelatedw.mineminenomi.api.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface IHealEntityObjective {
  boolean checkHeal(PlayerEntity paramPlayerEntity, LivingEntity paramLivingEntity, float paramFloat);
}


