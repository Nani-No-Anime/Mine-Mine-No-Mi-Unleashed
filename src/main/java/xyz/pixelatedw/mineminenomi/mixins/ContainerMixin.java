package xyz.pixelatedw.mineminenomi.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.IWorldPosCallable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.pixelatedw.mineminenomi.abilities.baku.BakuFactoryAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;


@Mixin({Container.class})
public class ContainerMixin
{
  @Inject(method = {"isWithinUsableDistance"}, at = {@At("HEAD")}, cancellable = true)
  private static void isWithinUsableDistance(IWorldPosCallable worldPos, PlayerEntity player, Block targetBlock, CallbackInfoReturnable<Boolean> callback) {
    if (targetBlock == Blocks.CRAFTING_TABLE.getBlock()) {
      
      IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
      Ability ability = abilityData.getEquippedAbility((Ability)BakuFactoryAbility.INSTANCE);
      boolean hasAbility = (ability != null && ability.isContinuous());
      if (hasAbility)
        callback.setReturnValue(Boolean.valueOf(true)); 
    } 
  }
}


