package xyz.pixelatedw.mineminenomi.api.quests.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public interface IKillEntityObjective {
  boolean checkKill(PlayerEntity paramPlayerEntity, LivingEntity paramLivingEntity, DamageSource paramDamageSource);
}


