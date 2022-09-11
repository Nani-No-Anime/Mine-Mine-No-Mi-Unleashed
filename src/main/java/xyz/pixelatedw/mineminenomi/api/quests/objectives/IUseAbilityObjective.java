package xyz.pixelatedw.mineminenomi.api.quests.objectives;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public interface IUseAbilityObjective {
  boolean checkAbility(PlayerEntity paramPlayerEntity, Ability paramAbility);
}


