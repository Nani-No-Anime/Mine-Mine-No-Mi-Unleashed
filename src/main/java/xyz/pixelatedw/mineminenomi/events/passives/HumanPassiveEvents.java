package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.TekkaiAbility;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;


@EventBusSubscriber(modid = "mineminenomi")
public class HumanPassiveEvents
{
  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    IEntityStats statsProps = EntityStatsCapability.get((LivingEntity)player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    if (!statsProps.isHuman()) {
      return;
    }
    Ability tekkaiAbility = abilityProps.getEquippedAbility(TekkaiAbility.INSTANCE);
    boolean isTekkaiActive = (tekkaiAbility != null && tekkaiAbility.isContinuous());
    if (isTekkaiActive)
      player.setMotion(0.0D, -5.0D, 0.0D); 
  }
}


