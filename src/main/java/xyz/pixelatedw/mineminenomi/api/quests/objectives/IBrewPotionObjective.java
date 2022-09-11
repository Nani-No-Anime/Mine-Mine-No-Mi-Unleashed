package xyz.pixelatedw.mineminenomi.api.quests.objectives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IBrewPotionObjective {
  boolean checkPotion(PlayerEntity paramPlayerEntity, ItemStack paramItemStack);
}


