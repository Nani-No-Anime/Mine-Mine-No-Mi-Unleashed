package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncChallengeDataPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;





@EventBusSubscriber(modid = "mineminenomi")
public class AbilityValidationEvents
{
  @SubscribeEvent
  public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
    if (event.getEntity() instanceof PlayerEntity && CommonConfig.INSTANCE.isAbilityFraudChecksEnabled()) {
      
      PlayerEntity player = (PlayerEntity)event.getEntity();
      IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
      IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
      IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
      IChallengesData challengesProps = ChallengesDataCapability.get(player);
      
      if (!player.world.isRemote)
      {
        ItemStack dfStack = DevilFruitHelper.getDevilFruitItem(devilFruitProps.getDevilFruit());
        
        if (dfStack.getItem() == Blocks.AIR.asItem()) {
          devilFruitProps.setDevilFruit("");
        }
        AbilityHelper.validateDevilFruitMoves(player);
        AbilityHelper.validateRacialMoves(player);
        AbilityHelper.validateStyleMoves(player);
        AbilityHelper.validateFactionMoves(player);
        
        for (Ability abl : abilityProps.getUnlockedAbilities(AbilityHelper.getDevilFruitCategory())) {
          
          if (abl instanceof xyz.pixelatedw.mineminenomi.abilities.yomi.KasuriutaFubukiGiriAbility || abl instanceof xyz.pixelatedw.mineminenomi.abilities.yomi.SoulParadeAbility || abl instanceof xyz.pixelatedw.mineminenomi.abilities.yomi.YomiNoReikiAbility)
          {
            if (!YomiZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
              abilityProps.removeUnlockedAbility(abl);
            }
          }
        } 
        for (int i = 0; i < (abilityProps.getEquippedAbilities()).length; i++) {
          
          if (abilityProps.getEquippedAbility(i) != null)
          {
            if (AbilityHelper.verifyIfAbilityIsBanned(abilityProps.getEquippedAbility(i))) {
              abilityProps.setEquippedAbility(i, null);
            }
          }
        } 
        WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), entityStatsProps), player);
        WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), player);
        WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps), player);
        WyNetwork.sendTo(new SSyncChallengeDataPacket(player.getEntityId(), challengesProps), player);
      }
    
    } else if (event.getEntity() instanceof PlayerEntity && !CommonConfig.INSTANCE.isAbilityFraudChecksEnabled()) {
      
      PlayerEntity player = (PlayerEntity)event.getEntity();
      IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
      IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
      IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
      IChallengesData challengesProps = ChallengesDataCapability.get(player);
      
      WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), entityStatsProps), player);
      WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), player);
      WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps), player);
      WyNetwork.sendTo(new SSyncChallengeDataPacket(player.getEntityId(), challengesProps), player);
    } 
  }
}


