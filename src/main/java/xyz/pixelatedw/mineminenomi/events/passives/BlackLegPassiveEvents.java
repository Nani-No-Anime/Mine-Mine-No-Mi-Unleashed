package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.blackleg.DiableJambeAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;


public class BlackLegPassiveEvents
{
  @EventBusSubscriber(modid = "mineminenomi")
  public static class Common
  {
    @SubscribeEvent
    public static void onHit(AttackEntityEvent event) {
      IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)event.getPlayer());
      
      Ability abl = abilityProps.getEquippedAbility((Ability)DiableJambeAbility.INSTANCE);
      
      if (event.getPlayer().getHeldItemMainhand().isEmpty() && abl != null && abl.isContinuous())
        event.getTarget().setFire(3); 
    }
  }
}


