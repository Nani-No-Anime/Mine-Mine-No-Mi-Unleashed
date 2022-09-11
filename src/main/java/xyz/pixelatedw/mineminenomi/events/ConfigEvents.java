package xyz.pixelatedw.mineminenomi.events;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.SpecialFlyAbility;
import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
import xyz.pixelatedw.mineminenomi.api.events.BountyEvent;
import xyz.pixelatedw.mineminenomi.api.events.DorikiEvent;
import xyz.pixelatedw.mineminenomi.api.events.HakiExpEvent;
import xyz.pixelatedw.mineminenomi.api.events.LoyaltyEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

@EventBusSubscriber(modid = "mineminenomi")
public class ConfigEvents {
  @SubscribeEvent
  public static void onClonePlayer(PlayerEvent.Clone event) {
    if (event.isWasDeath()) {
      
      IDevilFruit oldDevilFruitProps = DevilFruitCapability.get((LivingEntity)event.getOriginal());
      IDevilFruit newDevilFruitProps = DevilFruitCapability.get((LivingEntity)event.getPlayer());
      IAbilityData newAbilityData = AbilityDataCapability.get((LivingEntity)event.getPlayer());
      
      CompoundNBT compoundNBT = new CompoundNBT();
      
      if (oldDevilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && !YomiZoanInfo.INSTANCE.isActive((LivingEntity)event.getOriginal())) {
        
        restoreFullData(event.getOriginal(), event.getPlayer());
        DevilFruitCapability.get((LivingEntity)event.getPlayer()).setDevilFruit(ModAbilities.YOMI_YOMI_NO_MI);
        DevilFruitCapability.get((LivingEntity)event.getPlayer()).setZoanPoint(YomiZoanInfo.INSTANCE.getForm());
        
        return;
      } 
      if (CommonConfig.INSTANCE.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.FULL) {
        
        restoreFullData(event.getOriginal(), event.getPlayer());
      }
      else if (CommonConfig.INSTANCE.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.AUTO) {
        
        IEntityStats oldEntityStats = EntityStatsCapability.get((LivingEntity)event.getOriginal());
        String faction = oldEntityStats.getFaction();
        String race = oldEntityStats.getRace();
        String subRace = oldEntityStats.getSubRace();
        String fightStyle = oldEntityStats.getFightingStyle();
        int ultraCola = oldEntityStats.getUltraCola() / 2;
        int maxCola = Math.max(100 + ultraCola * 20, 100);
        int doriki = oldEntityStats.getDoriki() / 3;
        long bounty = oldEntityStats.getBounty() / 3L;
        long belly = oldEntityStats.getBelly() / 3L;
        long extol = oldEntityStats.getExtol() / 3L;
        int loyalty = oldEntityStats.getLoyalty() / 2;
        
        IEntityStats newEntityStats = EntityStatsCapability.get((LivingEntity)event.getPlayer());
        newEntityStats.setFaction(faction);
        newEntityStats.setRace(race);
        newEntityStats.setSubRace(subRace);
        newEntityStats.setFightingStyle(fightStyle);
        newEntityStats.setMaxCola(maxCola);
        newEntityStats.setUltraCola(ultraCola);
        newEntityStats.setCola(100);
        newEntityStats.setDoriki(doriki);
        newEntityStats.setBounty(bounty);
        newEntityStats.setBelly(belly);
        newEntityStats.setExtol(extol);
        newEntityStats.setLoyalty(loyalty);
        
        DorikiEvent e = new DorikiEvent(event.getPlayer(), newEntityStats.getDoriki());
        MinecraftForge.EVENT_BUS.post((Event)e);
        
        LoyaltyEvent e5 = new LoyaltyEvent(event.getPlayer(), newEntityStats.getLoyalty());
        MinecraftForge.EVENT_BUS.post((Event)e5);
        
        IHakiData oldHakiProps = HakiDataCapability.get((LivingEntity)event.getOriginal());
        float hardeningBusoExp = oldHakiProps.getBusoshokuHardeningHakiExp() / 2.0F;
        float imbuingBusoExp = oldHakiProps.getBusoshokuImbuingHakiExp() / 2.0F;
        float observationExp = oldHakiProps.getKenbunshokuHakiExp() / 2.0F;
        
        IHakiData newHakiProps = HakiDataCapability.get((LivingEntity)event.getPlayer());
        newHakiProps.setBusoshokuHardeningHakiExp(hardeningBusoExp);
        newHakiProps.setBusoshokuImbuingHakiExp(imbuingBusoExp);
        newHakiProps.setKenbunshokuHakiExp(observationExp);
        
        HakiExpEvent e2 = new HakiExpEvent(event.getPlayer(), hardeningBusoExp, HakiType.HARDENING);
        MinecraftForge.EVENT_BUS.post((Event)e2);
        
        HakiExpEvent e3 = new HakiExpEvent(event.getPlayer(), imbuingBusoExp, HakiType.IMBUING);
        MinecraftForge.EVENT_BUS.post((Event)e3);
        
        HakiExpEvent e4 = new HakiExpEvent(event.getPlayer(), observationExp, HakiType.KENBUNSHOKU);
        MinecraftForge.EVENT_BUS.post((Event)e4);
      }
      else if (CommonConfig.INSTANCE.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.CUSTOM) {
        
        IEntityStats oldEntityStats = EntityStatsCapability.get((LivingEntity)event.getOriginal());
        IEntityStats newEntityStats = EntityStatsCapability.get((LivingEntity)event.getPlayer());
        
        for (String stat : CommonConfig.INSTANCE.getStatsToKeep()) {
          INBT iNBT; DorikiEvent dorikiEvent; BountyEvent bountyEvent; IHakiData oldHakiProps; float hardeningBusoExp; float imbuingBusoExp; float observationExp; IHakiData newHakiProps; LoyaltyEvent loyaltyEvent;
          switch (WyHelper.getResourceName(stat)) {
            
            case "doriki":
              newEntityStats.setDoriki((int)WyHelper.percentage(CommonConfig.INSTANCE.getDorikiKeepPercentage(), oldEntityStats.getDoriki()));
              dorikiEvent = new DorikiEvent(event.getPlayer(), newEntityStats.getDoriki());
              MinecraftForge.EVENT_BUS.post((Event)dorikiEvent);
              break;
            case "bounty":
              newEntityStats.setBounty((int)WyHelper.percentage(CommonConfig.INSTANCE.getBountyKeepPercentage(), oldEntityStats.getBounty()));
              bountyEvent = new BountyEvent(event.getPlayer(), newEntityStats.getBounty());
              MinecraftForge.EVENT_BUS.post((Event)bountyEvent);
              break;
            case "belly":
              newEntityStats.setBelly((int)WyHelper.percentage(CommonConfig.INSTANCE.getBellyKeepPercentage(), oldEntityStats.getBelly()));
              break;
            case "race":
              newEntityStats.setRace(oldEntityStats.getRace());
              newEntityStats.setSubRace(oldEntityStats.getSubRace());
              break;
            case "faction":
              newEntityStats.setFaction(oldEntityStats.getFaction());
              break;
            case "fighting_style":
              newEntityStats.setFightingStyle(oldEntityStats.getFightingStyle());
              break;
            case "devil_fruit":
              iNBT = DevilFruitCapability.INSTANCE.writeNBT(oldDevilFruitProps, null);
              DevilFruitCapability.INSTANCE.readNBT(newDevilFruitProps, null, iNBT);
              break;
            case "haki_exp":
              oldHakiProps = HakiDataCapability.get((LivingEntity)event.getOriginal());
              hardeningBusoExp = oldHakiProps.getBusoshokuHardeningHakiExp() * CommonConfig.INSTANCE.getHakiExpKeepPercentage() / 100.0F;
              imbuingBusoExp = oldHakiProps.getBusoshokuImbuingHakiExp() * CommonConfig.INSTANCE.getHakiExpKeepPercentage() / 100.0F;
              observationExp = oldHakiProps.getKenbunshokuHakiExp() * CommonConfig.INSTANCE.getHakiExpKeepPercentage() / 100.0F;
              
              newHakiProps = HakiDataCapability.get((LivingEntity)event.getPlayer());
              newHakiProps.setBusoshokuHardeningHakiExp(hardeningBusoExp);
              newHakiProps.setBusoshokuImbuingHakiExp(imbuingBusoExp);
              newHakiProps.setKenbunshokuHakiExp(observationExp);
              break;
            case "loyalty":
              newEntityStats.setLoyalty((int)WyHelper.percentage(((Integer)CommonConfig.INSTANCE.loyaltyKeepPercentage.get()).intValue(), oldEntityStats.getLoyalty()));
              loyaltyEvent = new LoyaltyEvent(event.getPlayer(), newEntityStats.getLoyalty());
              MinecraftForge.EVENT_BUS.post((Event)loyaltyEvent);
              break;
          } 
        
        } 
      } 
      restorePermaData(event.getOriginal(), event.getPlayer());

      
      AbilityHelper.enableAbilities(event.getPlayer(), ability -> true);
      
      AbilityHelper.validateDevilFruitMoves(event.getPlayer());
      AbilityHelper.validateRacialMoves(event.getPlayer());
      AbilityHelper.validateStyleMoves(event.getPlayer());
      AbilityHelper.validateFactionMoves(event.getPlayer());
      
      WyNetwork.sendTo(new SSyncDevilFruitPacket(event.getPlayer().getEntityId(), newDevilFruitProps), event.getPlayer());
      WyNetwork.sendTo(new SSyncAbilityDataPacket(event.getPlayer().getEntityId(), newAbilityData), event.getPlayer());
      
      if (CommonConfig.INSTANCE.isSpecialFlyingEnabled() && AbilityDataCapability.get((LivingEntity)event.getPlayer()).hasUnlockedAbility((Ability)SpecialFlyAbility.INSTANCE) && !event.getPlayer().isCreative() && !event.getPlayer().isSpectator())
      {
        (event.getPlayer()).abilities.allowFlying = false;
        (event.getPlayer()).abilities.isFlying = false;
        ((ServerPlayerEntity)event.getPlayer()).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket((event.getPlayer()).abilities));
      }
    
    } else {
      
      restoreFullData(event.getOriginal(), event.getPlayer());
    } 
  }

  
  private static void restoreFullData(PlayerEntity original, PlayerEntity player) {
    IDevilFruit oldDevilFruitProps = DevilFruitCapability.get((LivingEntity)original);
    IDevilFruit newDevilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    IAbilityData newAbilityData = AbilityDataCapability.get((LivingEntity)player);
    
    CompoundNBT compoundNBT = new CompoundNBT();

    
    IEntityStats oldEntityStats = EntityStatsCapability.get((LivingEntity)original);
    INBT iNBT = EntityStatsCapability.INSTANCE.writeNBT(oldEntityStats, null);
    IEntityStats newEntityStats = EntityStatsCapability.get((LivingEntity)player);
    EntityStatsCapability.INSTANCE.readNBT(newEntityStats, null, iNBT);
    newEntityStats.setMaxCola(100);
    newEntityStats.setHeart(true);
    newEntityStats.setShadow(true);
    newEntityStats.setStrawDoll(true);
    newEntityStats.setCola(Math.max(Math.min(oldEntityStats.getCola(), newEntityStats.getMaxCola()), 0));
    DorikiEvent e = new DorikiEvent(player, newEntityStats.getDoriki());
    MinecraftForge.EVENT_BUS.post((Event)e);

    
    iNBT = DevilFruitCapability.INSTANCE.writeNBT(oldDevilFruitProps, null);
    DevilFruitCapability.INSTANCE.readNBT(newDevilFruitProps, null, iNBT);
    newDevilFruitProps.setZoanPoint("");

    
    IAbilityData oldAbilityData = AbilityDataCapability.get((LivingEntity)original);
    iNBT = AbilityDataCapability.INSTANCE.writeNBT(oldAbilityData, null);
    AbilityDataCapability.INSTANCE.readNBT(newAbilityData, null, iNBT);

    
    IHakiData oldHakiData = HakiDataCapability.get((LivingEntity)original);
    iNBT = HakiDataCapability.INSTANCE.writeNBT(oldHakiData, null);
    IHakiData newHakiData = HakiDataCapability.get((LivingEntity)player);
    HakiDataCapability.INSTANCE.readNBT(newHakiData, null, iNBT);
    
    restorePermaData(original, player);
  }


  
  private static void restorePermaData(PlayerEntity original, PlayerEntity player) {
    CompoundNBT compoundNBT = new CompoundNBT();

    
    IQuestData oldQuestData = QuestDataCapability.get(original);
    INBT iNBT = QuestDataCapability.INSTANCE.writeNBT(oldQuestData, null);
    IQuestData newQuestData = QuestDataCapability.get(player);
    QuestDataCapability.INSTANCE.readNBT(newQuestData, null, iNBT);

    
    IChallengesData oldChallengeData = ChallengesDataCapability.get(original);
    iNBT = ChallengesDataCapability.INSTANCE.writeNBT(oldChallengeData, null);
    IChallengesData newChallengeData = ChallengesDataCapability.get(player);
    ChallengesDataCapability.INSTANCE.readNBT(newChallengeData, null, iNBT);

    
    IAbilityData oldAbilityData = AbilityDataCapability.get((LivingEntity)original);
    
    oldAbilityData.clearEquippedAbilities(AbilityHelper.getAbilityFromCategoryPredicate(APIConfig.AbilityCategory.ALL));
    
    AbilityHelper.validateDevilFruitMoves(original);
    AbilityHelper.validateRacialMoves(original);
    AbilityHelper.validateStyleMoves(original);
    AbilityHelper.validateFactionMoves(original);
    
    iNBT = AbilityDataCapability.INSTANCE.writeNBT(oldAbilityData, null);
    IAbilityData newAbilityData = AbilityDataCapability.get((LivingEntity)player);
    AbilityDataCapability.INSTANCE.readNBT(newAbilityData, null, iNBT);
  }
}


