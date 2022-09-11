package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.marineloyalty.MusterAbility;
import xyz.pixelatedw.mineminenomi.abilities.marineloyalty.SmallMusterAbility;
import xyz.pixelatedw.mineminenomi.api.events.LoyaltyEvent;
import xyz.pixelatedw.mineminenomi.api.events.SetPlayerDetailsEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;


@EventBusSubscriber(modid = "mineminenomi")
public class LoyaltyEvents
{
  @SubscribeEvent
  public static void onPlayerUpdate(LivingEvent.LivingUpdateEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity) || (event.getEntityLiving()).world.isRemote) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    
    boolean isMarineOrRevo = (props.isMarine() || props.isRevolutionary());
    
    if (!isMarineOrRevo || props.getLoyalty() >= 100) {
      return;
    }
    if (props.getDoriki() < 4000 && props.getLoyalty() > 15) {
      return;
    }
    if (player.world.getGameTime() % 24000L == 0L) {
      
      props.alterLoyalty(1);
      LoyaltyEvent loyaltyEvent = new LoyaltyEvent(player, 1L);
      if (MinecraftForge.EVENT_BUS.post((Event)loyaltyEvent)) {
        return;
      }
    } 
  }
  
  @SubscribeEvent
  public static void checkForAbilityGain(LoyaltyEvent event) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)event.getPlayer());
    if (props.isMarine()) {
      
      gainMarineAbility(event.getPlayer(), FactionHelper.MarineRank.LIEUTENANT, (Ability)SmallMusterAbility.INSTANCE);
      gainMarineAbility(event.getPlayer(), FactionHelper.MarineRank.CAPTAIN, (Ability)MusterAbility.INSTANCE);
    }
    else if (props.isRevolutionary()) {
    
    } 
  }


  
  @SubscribeEvent
  public static void onStatsChoose(SetPlayerDetailsEvent event) {
    event.getEntityStats().setLoyalty(0);
  }

  
  private static void gainRevolutionaryAbility(PlayerEntity player, FactionHelper.RevolutionaryRank rank, Ability ability) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    if (props.hasRevolutionaryRank(rank) && !abilityProps.hasUnlockedAbility(ability) && !AbilityHelper.verifyIfAbilityIsBanned(ability)) {
      
      abilityProps.addUnlockedAbility(ability);
      player.sendMessage((ITextComponent)new StringTextComponent("Obtained " + ability.getName()));
    } 
    if ((!props.hasRevolutionaryRank(rank) || AbilityHelper.verifyIfAbilityIsBanned(ability)) && abilityProps.hasUnlockedAbility(ability)) {
      abilityProps.removeUnlockedAbility(ability);
    }
    WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps), player);
  }

  
  private static void gainMarineAbility(PlayerEntity player, FactionHelper.MarineRank rank, Ability ability) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    if (props.hasMarineRank(rank) && !abilityProps.hasUnlockedAbility(ability) && !AbilityHelper.verifyIfAbilityIsBanned(ability)) {
      
      abilityProps.addUnlockedAbility(ability);
      player.sendMessage((ITextComponent)new StringTextComponent("Obtained " + ability.getName()));
    } 
    if ((!props.hasMarineRank(rank) || AbilityHelper.verifyIfAbilityIsBanned(ability)) && abilityProps.hasUnlockedAbility(ability)) {
      abilityProps.removeUnlockedAbility(ability);
    }
    WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps), player);
  }
}


