package xyz.pixelatedw.mineminenomi.api.quests.objectives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IUseItemObjective {
  boolean checkItem(PlayerEntity paramPlayerEntity, ItemStack paramItemStack, int paramInt);
}


