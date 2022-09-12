package xyz.pixelatedw.mineminenomi.init;

import xyz.pixelatedw.mineminenomi.packets.client.*;
import xyz.pixelatedw.mineminenomi.packets.client.ability.*;
import xyz.pixelatedw.mineminenomi.packets.client.challenge.CResetChallengesPacket;
import xyz.pixelatedw.mineminenomi.packets.client.challenge.CStartChallengePacket;
import xyz.pixelatedw.mineminenomi.packets.client.crew.CCreateCrewPacket;
import xyz.pixelatedw.mineminenomi.packets.client.crew.CKickFromCrewPacket;
import xyz.pixelatedw.mineminenomi.packets.client.crew.CLeaveCrewPacket;
import xyz.pixelatedw.mineminenomi.packets.client.crew.CUpdateJollyRogerPacket;
import xyz.pixelatedw.mineminenomi.packets.client.quest.CAbandonQuestPacket;
import xyz.pixelatedw.mineminenomi.packets.client.quest.CStartObjectiveEventPacket;
import xyz.pixelatedw.mineminenomi.packets.client.trade.CBuyFromTraderPacket;
import xyz.pixelatedw.mineminenomi.packets.client.trade.CSellToTraderPacket;
import xyz.pixelatedw.mineminenomi.packets.client.ui.COpenChallengesScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.client.ui.COpenPlayerScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.server.*;
import xyz.pixelatedw.mineminenomi.packets.server.ability.*;
import xyz.pixelatedw.mineminenomi.packets.server.entities.SUpdateEntityOwnerPacket;
import xyz.pixelatedw.mineminenomi.packets.server.quest.SDespawnQuestObjectivePacket;
import xyz.pixelatedw.mineminenomi.packets.server.randfruit.SSetFruitSeedsPacket;
import xyz.pixelatedw.mineminenomi.packets.server.randfruit.SSetRandomizedFruitsPacket;
import xyz.pixelatedw.mineminenomi.packets.server.trade.SUpdateTraderOffersPacket;
import xyz.pixelatedw.mineminenomi.packets.server.ui.*;
import xyz.pixelatedw.mineminenomi.wypi.APIDefaults;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;


public class ModNetwork
{
  public static void init() {
    APIDefaults.initPackets();

    
    WyNetwork.registerPacket(CToggleCombatModePacket.class, CToggleCombatModePacket::encode, CToggleCombatModePacket::decode, CToggleCombatModePacket::handle);
    WyNetwork.registerPacket(CUseAbilityPacket.class, CUseAbilityPacket::encode, CUseAbilityPacket::decode, CUseAbilityPacket::handle);
    WyNetwork.registerPacket(CFinishCCPacket.class, CFinishCCPacket::encode, CFinishCCPacket::decode, CFinishCCPacket::handle);
    WyNetwork.registerPacket(CRequestSyncQuestDataPacket.class, CRequestSyncQuestDataPacket::encode, CRequestSyncQuestDataPacket::decode, CRequestSyncQuestDataPacket::handle);
    WyNetwork.registerPacket(CSyncZoanPacket.class, CSyncZoanPacket::encode, CSyncZoanPacket::decode, CSyncZoanPacket::handle);
    WyNetwork.registerPacket(CRemoveAbilityPacket.class, CRemoveAbilityPacket::encode, CRemoveAbilityPacket::decode, CRemoveAbilityPacket::handle);
    WyNetwork.registerPacket(CCreateCrewPacket.class, CCreateCrewPacket::encode, CCreateCrewPacket::decode, CCreateCrewPacket::handle);
    WyNetwork.registerPacket(CRequestSyncWorldDataPacket.class, CRequestSyncWorldDataPacket::encode, CRequestSyncWorldDataPacket::decode, CRequestSyncWorldDataPacket::handle);
    WyNetwork.registerPacket(CLeaveCrewPacket.class, CLeaveCrewPacket::encode, CLeaveCrewPacket::decode, CLeaveCrewPacket::handle);
    WyNetwork.registerPacket(CUpdateJollyRogerPacket.class, CUpdateJollyRogerPacket::encode, CUpdateJollyRogerPacket::decode, CUpdateJollyRogerPacket::handle);
    WyNetwork.registerPacket(CChangeCombatBarPacket.class, CChangeCombatBarPacket::encode, CChangeCombatBarPacket::decode, CChangeCombatBarPacket::handle);
    WyNetwork.registerPacket(CStartChallengePacket.class, CStartChallengePacket::encode, CStartChallengePacket::decode, CStartChallengePacket::handle);
    WyNetwork.registerPacket(CResetChallengesPacket.class, CResetChallengesPacket::encode, CResetChallengesPacket::decode, CResetChallengesPacket::handle);
    WyNetwork.registerPacket(CKickFromCrewPacket.class, CKickFromCrewPacket::encode, CKickFromCrewPacket::decode, CKickFromCrewPacket::handle);
    WyNetwork.registerPacket(CStartObjectiveEventPacket.class, CStartObjectiveEventPacket::encode, CStartObjectiveEventPacket::decode, CStartObjectiveEventPacket::handle);
    WyNetwork.registerPacket(CAbandonQuestPacket.class, CAbandonQuestPacket::encode, CAbandonQuestPacket::decode, CAbandonQuestPacket::handle);
    WyNetwork.registerPacket(CSellToTraderPacket.class, CSellToTraderPacket::encode, CSellToTraderPacket::decode, CSellToTraderPacket::handle);
    WyNetwork.registerPacket(CBuyFromTraderPacket.class, CBuyFromTraderPacket::encode, CBuyFromTraderPacket::decode, CBuyFromTraderPacket::handle);
    WyNetwork.registerPacket(CEquipAbilityPacket.class, CEquipAbilityPacket::encode, CEquipAbilityPacket::decode, CEquipAbilityPacket::handle);
    WyNetwork.registerPacket(CTogglePassiveAbilityPacket.class, CTogglePassiveAbilityPacket::encode, CTogglePassiveAbilityPacket::decode, CTogglePassiveAbilityPacket::handle);
    WyNetwork.registerPacket(COpenPlayerScreenPacket.class, COpenPlayerScreenPacket::encode, COpenPlayerScreenPacket::decode, COpenPlayerScreenPacket::handle);
    WyNetwork.registerPacket(COpenChallengesScreenPacket.class, COpenChallengesScreenPacket::encode, COpenChallengesScreenPacket::decode, COpenChallengesScreenPacket::handle);
    WyNetwork.registerPacket(CRequestSyncAbilityDataPacket.class, CRequestSyncAbilityDataPacket::encode, CRequestSyncAbilityDataPacket::decode, CRequestSyncAbilityDataPacket::handle);

    
    WyNetwork.registerPacket(SSyncDevilFruitPacket.class, SSyncDevilFruitPacket::encode, SSyncDevilFruitPacket::decode, SSyncDevilFruitPacket::handle);
    WyNetwork.registerPacket(SSyncEntityStatsPacket.class, SSyncEntityStatsPacket::encode, SSyncEntityStatsPacket::decode, SSyncEntityStatsPacket::handle);
    WyNetwork.registerPacket(SFlySpeedPacket.class, SFlySpeedPacket::encode, SFlySpeedPacket::decode, SFlySpeedPacket::handle);
    WyNetwork.registerPacket(SRecalculateEyeHeightPacket.class, SRecalculateEyeHeightPacket::encode, SRecalculateEyeHeightPacket::decode, SRecalculateEyeHeightPacket::handle);
    WyNetwork.registerPacket(SViewProtectionPacket.class, SViewProtectionPacket::encode, SViewProtectionPacket::decode, SViewProtectionPacket::handle);
    WyNetwork.registerPacket(SOpenCharacterCreatorScreenPacket.class, SOpenCharacterCreatorScreenPacket::encode, SOpenCharacterCreatorScreenPacket::decode, SOpenCharacterCreatorScreenPacket::handle);
    WyNetwork.registerPacket(SOpenWantedPosterScreenPacket.class, SOpenWantedPosterScreenPacket::encode, SOpenWantedPosterScreenPacket::decode, SOpenWantedPosterScreenPacket::handle);
    WyNetwork.registerPacket(SDespawnQuestObjectivePacket.class, SDespawnQuestObjectivePacket::encode, SDespawnQuestObjectivePacket::decode, SDespawnQuestObjectivePacket::handle);
    WyNetwork.registerPacket(SUpdateTraderOffersPacket.class, SUpdateTraderOffersPacket::encode, SUpdateTraderOffersPacket::decode, SUpdateTraderOffersPacket::handle);
    WyNetwork.registerPacket(SOpenTraderScreenPacket.class, SOpenTraderScreenPacket::encode, SOpenTraderScreenPacket::decode, SOpenTraderScreenPacket::handle);
    WyNetwork.registerPacket(SOpenJollyRogerCreatorScreenPacket.class, SOpenJollyRogerCreatorScreenPacket::encode, SOpenJollyRogerCreatorScreenPacket::decode, SOpenJollyRogerCreatorScreenPacket::handle);
    WyNetwork.registerPacket(SOpenQuestChooseScreenPacket.class, SOpenQuestChooseScreenPacket::encode, SOpenQuestChooseScreenPacket::decode, SOpenQuestChooseScreenPacket::handle);
    WyNetwork.registerPacket(SOpenNewCrewScreenPacket.class, SOpenNewCrewScreenPacket::encode, SOpenNewCrewScreenPacket::decode, SOpenNewCrewScreenPacket::handle);
    WyNetwork.registerPacket(SSyncWorldDataPacket.class, SSyncWorldDataPacket::encode, SSyncWorldDataPacket::decode, SSyncWorldDataPacket::handle);
    WyNetwork.registerPacket(SSyncHakiDataPacket.class, SSyncHakiDataPacket::encode, SSyncHakiDataPacket::decode, SSyncHakiDataPacket::handle);
    WyNetwork.registerPacket(SFlightValuePacket.class, SFlightValuePacket::encode, SFlightValuePacket::decode, SFlightValuePacket::handle);
    WyNetwork.registerPacket(SSyncChallengeDataPacket.class, SSyncChallengeDataPacket::encode, SSyncChallengeDataPacket::decode, SSyncChallengeDataPacket::handle);
    WyNetwork.registerPacket(SAnimeScreamPacket.class, SAnimeScreamPacket::encode, SAnimeScreamPacket::decode, SAnimeScreamPacket::handle);
    WyNetwork.registerPacket(SUpdateEquippedAbilityPacket.class, SUpdateEquippedAbilityPacket::encode, SUpdateEquippedAbilityPacket::decode, SUpdateEquippedAbilityPacket::handle);
    WyNetwork.registerPacket(SOpenArenaSetupScreenPacket.class, SOpenArenaSetupScreenPacket::encode, SOpenArenaSetupScreenPacket::decode, SOpenArenaSetupScreenPacket::handle);
    WyNetwork.registerPacket(SCloseScreenPacket.class, SCloseScreenPacket::encode, SCloseScreenPacket::decode, SCloseScreenPacket::handle);
    WyNetwork.registerPacket(SSetEffectDetailsPacket.class, SSetEffectDetailsPacket::encode, SSetEffectDetailsPacket::decode, SSetEffectDetailsPacket::handle);
    WyNetwork.registerPacket(SUpdateEntityOwnerPacket.class, SUpdateEntityOwnerPacket::encode, SUpdateEntityOwnerPacket::decode, SUpdateEntityOwnerPacket::handle);
    WyNetwork.registerPacket(SOpenPlayerScreenPacket.class, SOpenPlayerScreenPacket::encode, SOpenPlayerScreenPacket::decode, SOpenPlayerScreenPacket::handle);
    WyNetwork.registerPacket(SChangeCombatBarPacket.class, SChangeCombatBarPacket::encode, SChangeCombatBarPacket::decode, SChangeCombatBarPacket::handle);
    WyNetwork.registerPacket(SOpenChallengesScreenPacket.class, SOpenChallengesScreenPacket::encode, SOpenChallengesScreenPacket::decode, SOpenChallengesScreenPacket::handle);
    WyNetwork.registerPacket(SSetRandomizedFruitsPacket.class, SSetRandomizedFruitsPacket::encode, SSetRandomizedFruitsPacket::decode, SSetRandomizedFruitsPacket::handle);
    WyNetwork.registerPacket(SSetFruitSeedsPacket.class, SSetFruitSeedsPacket::encode, SSetFruitSeedsPacket::decode, SSetFruitSeedsPacket::handle);
    WyNetwork.registerPacket(SOpenEncyclopediaScreenPacket.class, SOpenEncyclopediaScreenPacket::encode, SOpenEncyclopediaScreenPacket::decode, SOpenEncyclopediaScreenPacket::handle);
    WyNetwork.registerPacket(SUpdateAbilityTimeProgressionPacket.class, SUpdateAbilityTimeProgressionPacket::encode, SUpdateAbilityTimeProgressionPacket::decode, SUpdateAbilityTimeProgressionPacket::handle);
    WyNetwork.registerPacket(SStepHeightValuePacket.class, SStepHeightValuePacket::encode, SStepHeightValuePacket::decode, SStepHeightValuePacket::handle);
    WyNetwork.registerPacket(SOpenQuestTrackerScreenPacket.class, SOpenQuestTrackerScreenPacket::encode, SOpenQuestTrackerScreenPacket::decode, SOpenQuestTrackerScreenPacket::handle);
    WyNetwork.registerPacket(SUpdateExtraDataPacket.class, SUpdateExtraDataPacket::encode, SUpdateExtraDataPacket::decode, SUpdateExtraDataPacket::handle);
    WyNetwork.registerPacket(SUpdateCustomTexturePacket.class, SUpdateCustomTexturePacket::encode, SUpdateCustomTexturePacket::decode, SUpdateCustomTexturePacket::handle);
    WyNetwork.registerPacket(SOpenAbilitySelectionScreenPacket.class, SOpenAbilitySelectionScreenPacket::encode, SOpenAbilitySelectionScreenPacket::decode, SOpenAbilitySelectionScreenPacket::handle);
  }
}


