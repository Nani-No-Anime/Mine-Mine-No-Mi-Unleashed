package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.mogu.MoguHeavyPointAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

@EventBusSubscriber(modid = "mineminenomi")
public class MoguPassiveEvents
{
  @SubscribeEvent
  public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event) {
    PlayerEntity player = event.getPlayer();
    IAbilityData AbilityProps = AbilityDataCapability.get((LivingEntity)player);
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    if (!props.getDevilFruit().equals("mogu_mogu")) {
      return;
    }
    MoguHeavyPointAbility ability = (MoguHeavyPointAbility)AbilityProps.getEquippedAbility((Ability)MoguHeavyPointAbility.INSTANCE);
    if (ability != null && ability.isContinuous())
      event.setNewSpeed(event.getOriginalSpeed() * 5.0F); 
  }
}


