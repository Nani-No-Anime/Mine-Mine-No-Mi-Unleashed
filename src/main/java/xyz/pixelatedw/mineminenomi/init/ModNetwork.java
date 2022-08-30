/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.CFinishCCPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncQuestDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncWorldDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.CSyncZoanPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CChangeCombatBarPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CEquipAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CRemoveAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CToggleCombatModePacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CTogglePassiveAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CUseAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.challenge.CResetChallengesPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.challenge.CStartChallengePacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.crew.CCreateCrewPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.crew.CKickFromCrewPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.crew.CLeaveCrewPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.crew.CUpdateJollyRogerPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.quest.CAbandonQuestPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.quest.CStartObjectiveEventPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.trade.CBuyFromTraderPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.trade.CSellToTraderPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ui.COpenChallengesScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ui.COpenPlayerScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SFlightValuePacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SFlySpeedPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSetEffectDetailsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SStepHeightValuePacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncChallengeDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SAnimeScreamPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SChangeCombatBarPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SRecalculateEyeHeightPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateAbilityTimeProgressionPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateCustomTexturePacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateExtraDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SViewProtectionPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUpdateEntityOwnerPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.quest.SDespawnQuestObjectivePacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.randfruit.SSetFruitSeedsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.randfruit.SSetRandomizedFruitsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.trade.SUpdateTraderOffersPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SCloseScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenAbilitySelectionScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenArenaSetupScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenChallengesScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenCharacterCreatorScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenEncyclopediaScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenJollyRogerCreatorScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenNewCrewScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenPlayerScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenQuestChooseScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenQuestTrackerScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenTraderScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenWantedPosterScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIDefaults;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ public class ModNetwork
/*     */ {
/*     */   public static void init() {
/*  68 */     APIDefaults.initPackets();
/*     */ 
/*     */     
/*  71 */     WyNetwork.registerPacket(CToggleCombatModePacket.class, CToggleCombatModePacket::encode, CToggleCombatModePacket::decode, CToggleCombatModePacket::handle);
/*  72 */     WyNetwork.registerPacket(CUseAbilityPacket.class, CUseAbilityPacket::encode, CUseAbilityPacket::decode, CUseAbilityPacket::handle);
/*  73 */     WyNetwork.registerPacket(CFinishCCPacket.class, CFinishCCPacket::encode, CFinishCCPacket::decode, CFinishCCPacket::handle);
/*  74 */     WyNetwork.registerPacket(CRequestSyncQuestDataPacket.class, CRequestSyncQuestDataPacket::encode, CRequestSyncQuestDataPacket::decode, CRequestSyncQuestDataPacket::handle);
/*  75 */     WyNetwork.registerPacket(CSyncZoanPacket.class, CSyncZoanPacket::encode, CSyncZoanPacket::decode, CSyncZoanPacket::handle);
/*  76 */     WyNetwork.registerPacket(CRemoveAbilityPacket.class, CRemoveAbilityPacket::encode, CRemoveAbilityPacket::decode, CRemoveAbilityPacket::handle);
/*  77 */     WyNetwork.registerPacket(CCreateCrewPacket.class, CCreateCrewPacket::encode, CCreateCrewPacket::decode, CCreateCrewPacket::handle);
/*  78 */     WyNetwork.registerPacket(CRequestSyncWorldDataPacket.class, CRequestSyncWorldDataPacket::encode, CRequestSyncWorldDataPacket::decode, CRequestSyncWorldDataPacket::handle);
/*  79 */     WyNetwork.registerPacket(CLeaveCrewPacket.class, CLeaveCrewPacket::encode, CLeaveCrewPacket::decode, CLeaveCrewPacket::handle);
/*  80 */     WyNetwork.registerPacket(CUpdateJollyRogerPacket.class, CUpdateJollyRogerPacket::encode, CUpdateJollyRogerPacket::decode, CUpdateJollyRogerPacket::handle);
/*  81 */     WyNetwork.registerPacket(CChangeCombatBarPacket.class, CChangeCombatBarPacket::encode, CChangeCombatBarPacket::decode, CChangeCombatBarPacket::handle);
/*  82 */     WyNetwork.registerPacket(CStartChallengePacket.class, CStartChallengePacket::encode, CStartChallengePacket::decode, CStartChallengePacket::handle);
/*  83 */     WyNetwork.registerPacket(CResetChallengesPacket.class, CResetChallengesPacket::encode, CResetChallengesPacket::decode, CResetChallengesPacket::handle);
/*  84 */     WyNetwork.registerPacket(CKickFromCrewPacket.class, CKickFromCrewPacket::encode, CKickFromCrewPacket::decode, CKickFromCrewPacket::handle);
/*  85 */     WyNetwork.registerPacket(CStartObjectiveEventPacket.class, CStartObjectiveEventPacket::encode, CStartObjectiveEventPacket::decode, CStartObjectiveEventPacket::handle);
/*  86 */     WyNetwork.registerPacket(CAbandonQuestPacket.class, CAbandonQuestPacket::encode, CAbandonQuestPacket::decode, CAbandonQuestPacket::handle);
/*  87 */     WyNetwork.registerPacket(CSellToTraderPacket.class, CSellToTraderPacket::encode, CSellToTraderPacket::decode, CSellToTraderPacket::handle);
/*  88 */     WyNetwork.registerPacket(CBuyFromTraderPacket.class, CBuyFromTraderPacket::encode, CBuyFromTraderPacket::decode, CBuyFromTraderPacket::handle);
/*  89 */     WyNetwork.registerPacket(CEquipAbilityPacket.class, CEquipAbilityPacket::encode, CEquipAbilityPacket::decode, CEquipAbilityPacket::handle);
/*  90 */     WyNetwork.registerPacket(CTogglePassiveAbilityPacket.class, CTogglePassiveAbilityPacket::encode, CTogglePassiveAbilityPacket::decode, CTogglePassiveAbilityPacket::handle);
/*  91 */     WyNetwork.registerPacket(COpenPlayerScreenPacket.class, COpenPlayerScreenPacket::encode, COpenPlayerScreenPacket::decode, COpenPlayerScreenPacket::handle);
/*  92 */     WyNetwork.registerPacket(COpenChallengesScreenPacket.class, COpenChallengesScreenPacket::encode, COpenChallengesScreenPacket::decode, COpenChallengesScreenPacket::handle);
/*  93 */     WyNetwork.registerPacket(CRequestSyncAbilityDataPacket.class, CRequestSyncAbilityDataPacket::encode, CRequestSyncAbilityDataPacket::decode, CRequestSyncAbilityDataPacket::handle);
/*     */ 
/*     */     
/*  96 */     WyNetwork.registerPacket(SSyncDevilFruitPacket.class, SSyncDevilFruitPacket::encode, SSyncDevilFruitPacket::decode, SSyncDevilFruitPacket::handle);
/*  97 */     WyNetwork.registerPacket(SSyncEntityStatsPacket.class, SSyncEntityStatsPacket::encode, SSyncEntityStatsPacket::decode, SSyncEntityStatsPacket::handle);
/*  98 */     WyNetwork.registerPacket(SFlySpeedPacket.class, SFlySpeedPacket::encode, SFlySpeedPacket::decode, SFlySpeedPacket::handle);
/*  99 */     WyNetwork.registerPacket(SRecalculateEyeHeightPacket.class, SRecalculateEyeHeightPacket::encode, SRecalculateEyeHeightPacket::decode, SRecalculateEyeHeightPacket::handle);
/* 100 */     WyNetwork.registerPacket(SViewProtectionPacket.class, SViewProtectionPacket::encode, SViewProtectionPacket::decode, SViewProtectionPacket::handle);
/* 101 */     WyNetwork.registerPacket(SOpenCharacterCreatorScreenPacket.class, SOpenCharacterCreatorScreenPacket::encode, SOpenCharacterCreatorScreenPacket::decode, SOpenCharacterCreatorScreenPacket::handle);
/* 102 */     WyNetwork.registerPacket(SOpenWantedPosterScreenPacket.class, SOpenWantedPosterScreenPacket::encode, SOpenWantedPosterScreenPacket::decode, SOpenWantedPosterScreenPacket::handle);
/* 103 */     WyNetwork.registerPacket(SDespawnQuestObjectivePacket.class, SDespawnQuestObjectivePacket::encode, SDespawnQuestObjectivePacket::decode, SDespawnQuestObjectivePacket::handle);
/* 104 */     WyNetwork.registerPacket(SUpdateTraderOffersPacket.class, SUpdateTraderOffersPacket::encode, SUpdateTraderOffersPacket::decode, SUpdateTraderOffersPacket::handle);
/* 105 */     WyNetwork.registerPacket(SOpenTraderScreenPacket.class, SOpenTraderScreenPacket::encode, SOpenTraderScreenPacket::decode, SOpenTraderScreenPacket::handle);
/* 106 */     WyNetwork.registerPacket(SOpenJollyRogerCreatorScreenPacket.class, SOpenJollyRogerCreatorScreenPacket::encode, SOpenJollyRogerCreatorScreenPacket::decode, SOpenJollyRogerCreatorScreenPacket::handle);
/* 107 */     WyNetwork.registerPacket(SOpenQuestChooseScreenPacket.class, SOpenQuestChooseScreenPacket::encode, SOpenQuestChooseScreenPacket::decode, SOpenQuestChooseScreenPacket::handle);
/* 108 */     WyNetwork.registerPacket(SOpenNewCrewScreenPacket.class, SOpenNewCrewScreenPacket::encode, SOpenNewCrewScreenPacket::decode, SOpenNewCrewScreenPacket::handle);
/* 109 */     WyNetwork.registerPacket(SSyncWorldDataPacket.class, SSyncWorldDataPacket::encode, SSyncWorldDataPacket::decode, SSyncWorldDataPacket::handle);
/* 110 */     WyNetwork.registerPacket(SSyncHakiDataPacket.class, SSyncHakiDataPacket::encode, SSyncHakiDataPacket::decode, SSyncHakiDataPacket::handle);
/* 111 */     WyNetwork.registerPacket(SFlightValuePacket.class, SFlightValuePacket::encode, SFlightValuePacket::decode, SFlightValuePacket::handle);
/* 112 */     WyNetwork.registerPacket(SSyncChallengeDataPacket.class, SSyncChallengeDataPacket::encode, SSyncChallengeDataPacket::decode, SSyncChallengeDataPacket::handle);
/* 113 */     WyNetwork.registerPacket(SAnimeScreamPacket.class, SAnimeScreamPacket::encode, SAnimeScreamPacket::decode, SAnimeScreamPacket::handle);
/* 114 */     WyNetwork.registerPacket(SUpdateEquippedAbilityPacket.class, SUpdateEquippedAbilityPacket::encode, SUpdateEquippedAbilityPacket::decode, SUpdateEquippedAbilityPacket::handle);
/* 115 */     WyNetwork.registerPacket(SOpenArenaSetupScreenPacket.class, SOpenArenaSetupScreenPacket::encode, SOpenArenaSetupScreenPacket::decode, SOpenArenaSetupScreenPacket::handle);
/* 116 */     WyNetwork.registerPacket(SCloseScreenPacket.class, SCloseScreenPacket::encode, SCloseScreenPacket::decode, SCloseScreenPacket::handle);
/* 117 */     WyNetwork.registerPacket(SSetEffectDetailsPacket.class, SSetEffectDetailsPacket::encode, SSetEffectDetailsPacket::decode, SSetEffectDetailsPacket::handle);
/* 118 */     WyNetwork.registerPacket(SUpdateEntityOwnerPacket.class, SUpdateEntityOwnerPacket::encode, SUpdateEntityOwnerPacket::decode, SUpdateEntityOwnerPacket::handle);
/* 119 */     WyNetwork.registerPacket(SOpenPlayerScreenPacket.class, SOpenPlayerScreenPacket::encode, SOpenPlayerScreenPacket::decode, SOpenPlayerScreenPacket::handle);
/* 120 */     WyNetwork.registerPacket(SChangeCombatBarPacket.class, SChangeCombatBarPacket::encode, SChangeCombatBarPacket::decode, SChangeCombatBarPacket::handle);
/* 121 */     WyNetwork.registerPacket(SOpenChallengesScreenPacket.class, SOpenChallengesScreenPacket::encode, SOpenChallengesScreenPacket::decode, SOpenChallengesScreenPacket::handle);
/* 122 */     WyNetwork.registerPacket(SSetRandomizedFruitsPacket.class, SSetRandomizedFruitsPacket::encode, SSetRandomizedFruitsPacket::decode, SSetRandomizedFruitsPacket::handle);
/* 123 */     WyNetwork.registerPacket(SSetFruitSeedsPacket.class, SSetFruitSeedsPacket::encode, SSetFruitSeedsPacket::decode, SSetFruitSeedsPacket::handle);
/* 124 */     WyNetwork.registerPacket(SOpenEncyclopediaScreenPacket.class, SOpenEncyclopediaScreenPacket::encode, SOpenEncyclopediaScreenPacket::decode, SOpenEncyclopediaScreenPacket::handle);
/* 125 */     WyNetwork.registerPacket(SUpdateAbilityTimeProgressionPacket.class, SUpdateAbilityTimeProgressionPacket::encode, SUpdateAbilityTimeProgressionPacket::decode, SUpdateAbilityTimeProgressionPacket::handle);
/* 126 */     WyNetwork.registerPacket(SStepHeightValuePacket.class, SStepHeightValuePacket::encode, SStepHeightValuePacket::decode, SStepHeightValuePacket::handle);
/* 127 */     WyNetwork.registerPacket(SOpenQuestTrackerScreenPacket.class, SOpenQuestTrackerScreenPacket::encode, SOpenQuestTrackerScreenPacket::decode, SOpenQuestTrackerScreenPacket::handle);
/* 128 */     WyNetwork.registerPacket(SUpdateExtraDataPacket.class, SUpdateExtraDataPacket::encode, SUpdateExtraDataPacket::decode, SUpdateExtraDataPacket::handle);
/* 129 */     WyNetwork.registerPacket(SUpdateCustomTexturePacket.class, SUpdateCustomTexturePacket::encode, SUpdateCustomTexturePacket::decode, SUpdateCustomTexturePacket::handle);
/* 130 */     WyNetwork.registerPacket(SOpenAbilitySelectionScreenPacket.class, SOpenAbilitySelectionScreenPacket::encode, SOpenAbilitySelectionScreenPacket::decode, SOpenAbilitySelectionScreenPacket::handle);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\init\ModNetwork.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */