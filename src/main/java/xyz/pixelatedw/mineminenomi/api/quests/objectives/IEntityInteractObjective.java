package xyz.pixelatedw.mineminenomi.api.quests.objectives;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public interface IEntityInteractObjective {
  boolean checkInteraction(PlayerEntity paramPlayerEntity, Entity paramEntity);
}


