package xyz.pixelatedw.mineminenomi.api.quests.objectives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public interface ICureEffectObjective {
  boolean checkEffect(PlayerEntity paramPlayerEntity, EffectInstance paramEffectInstance);
}


