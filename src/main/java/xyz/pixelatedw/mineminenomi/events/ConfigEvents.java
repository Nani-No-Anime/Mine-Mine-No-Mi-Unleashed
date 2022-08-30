/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.SpecialFlyAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.BountyEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.DorikiEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.HakiExpEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.LoyaltyEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class ConfigEvents {
/*     */   @SubscribeEvent
/*     */   public static void onClonePlayer(PlayerEvent.Clone event) {
/*  47 */     if (event.isWasDeath()) {
/*     */       
/*  49 */       IDevilFruit oldDevilFruitProps = DevilFruitCapability.get((LivingEntity)event.getOriginal());
/*  50 */       IDevilFruit newDevilFruitProps = DevilFruitCapability.get((LivingEntity)event.getPlayer());
/*  51 */       IAbilityData newAbilityData = AbilityDataCapability.get((LivingEntity)event.getPlayer());
/*     */       
/*  53 */       CompoundNBT compoundNBT = new CompoundNBT();
/*     */       
/*  55 */       if (oldDevilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && !YomiZoanInfo.INSTANCE.isActive((LivingEntity)event.getOriginal())) {
/*     */         
/*  57 */         restoreFullData(event.getOriginal(), event.getPlayer());
/*  58 */         DevilFruitCapability.get((LivingEntity)event.getPlayer()).setDevilFruit(ModAbilities.YOMI_YOMI_NO_MI);
/*  59 */         DevilFruitCapability.get((LivingEntity)event.getPlayer()).setZoanPoint(YomiZoanInfo.INSTANCE.getForm());
/*     */         
/*     */         return;
/*     */       } 
/*  63 */       if (CommonConfig.INSTANCE.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.FULL) {
/*     */         
/*  65 */         restoreFullData(event.getOriginal(), event.getPlayer());
/*     */       }
/*  67 */       else if (CommonConfig.INSTANCE.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.AUTO) {
/*     */         
/*  69 */         IEntityStats oldEntityStats = EntityStatsCapability.get((LivingEntity)event.getOriginal());
/*  70 */         String faction = oldEntityStats.getFaction();
/*  71 */         String race = oldEntityStats.getRace();
/*  72 */         String subRace = oldEntityStats.getSubRace();
/*  73 */         String fightStyle = oldEntityStats.getFightingStyle();
/*  74 */         int ultraCola = oldEntityStats.getUltraCola() / 2;
/*  75 */         int maxCola = Math.max(100 + ultraCola * 20, 100);
/*  76 */         int doriki = oldEntityStats.getDoriki() / 3;
/*  77 */         long bounty = oldEntityStats.getBounty() / 3L;
/*  78 */         long belly = oldEntityStats.getBelly() / 3L;
/*  79 */         long extol = oldEntityStats.getExtol() / 3L;
/*  80 */         int loyalty = oldEntityStats.getLoyalty() / 2;
/*     */         
/*  82 */         IEntityStats newEntityStats = EntityStatsCapability.get((LivingEntity)event.getPlayer());
/*  83 */         newEntityStats.setFaction(faction);
/*  84 */         newEntityStats.setRace(race);
/*  85 */         newEntityStats.setSubRace(subRace);
/*  86 */         newEntityStats.setFightingStyle(fightStyle);
/*  87 */         newEntityStats.setMaxCola(maxCola);
/*  88 */         newEntityStats.setUltraCola(ultraCola);
/*  89 */         newEntityStats.setCola(100);
/*  90 */         newEntityStats.setDoriki(doriki);
/*  91 */         newEntityStats.setBounty(bounty);
/*  92 */         newEntityStats.setBelly(belly);
/*  93 */         newEntityStats.setExtol(extol);
/*  94 */         newEntityStats.setLoyalty(loyalty);
/*     */         
/*  96 */         DorikiEvent e = new DorikiEvent(event.getPlayer(), newEntityStats.getDoriki());
/*  97 */         MinecraftForge.EVENT_BUS.post((Event)e);
/*     */         
/*  99 */         LoyaltyEvent e5 = new LoyaltyEvent(event.getPlayer(), newEntityStats.getLoyalty());
/* 100 */         MinecraftForge.EVENT_BUS.post((Event)e5);
/*     */         
/* 102 */         IHakiData oldHakiProps = HakiDataCapability.get((LivingEntity)event.getOriginal());
/* 103 */         float hardeningBusoExp = oldHakiProps.getBusoshokuHardeningHakiExp() / 2.0F;
/* 104 */         float imbuingBusoExp = oldHakiProps.getBusoshokuImbuingHakiExp() / 2.0F;
/* 105 */         float observationExp = oldHakiProps.getKenbunshokuHakiExp() / 2.0F;
/*     */         
/* 107 */         IHakiData newHakiProps = HakiDataCapability.get((LivingEntity)event.getPlayer());
/* 108 */         newHakiProps.setBusoshokuHardeningHakiExp(hardeningBusoExp);
/* 109 */         newHakiProps.setBusoshokuImbuingHakiExp(imbuingBusoExp);
/* 110 */         newHakiProps.setKenbunshokuHakiExp(observationExp);
/*     */         
/* 112 */         HakiExpEvent e2 = new HakiExpEvent(event.getPlayer(), hardeningBusoExp, HakiType.HARDENING);
/* 113 */         MinecraftForge.EVENT_BUS.post((Event)e2);
/*     */         
/* 115 */         HakiExpEvent e3 = new HakiExpEvent(event.getPlayer(), imbuingBusoExp, HakiType.IMBUING);
/* 116 */         MinecraftForge.EVENT_BUS.post((Event)e3);
/*     */         
/* 118 */         HakiExpEvent e4 = new HakiExpEvent(event.getPlayer(), observationExp, HakiType.KENBUNSHOKU);
/* 119 */         MinecraftForge.EVENT_BUS.post((Event)e4);
/*     */       }
/* 121 */       else if (CommonConfig.INSTANCE.getAfterDeathLogic() == CommonConfig.KeepStatsLogic.CUSTOM) {
/*     */         
/* 123 */         IEntityStats oldEntityStats = EntityStatsCapability.get((LivingEntity)event.getOriginal());
/* 124 */         IEntityStats newEntityStats = EntityStatsCapability.get((LivingEntity)event.getPlayer());
/*     */         
/* 126 */         for (String stat : CommonConfig.INSTANCE.getStatsToKeep()) {
/*     */           INBT iNBT; DorikiEvent dorikiEvent; BountyEvent bountyEvent; IHakiData oldHakiProps; float hardeningBusoExp; float imbuingBusoExp; float observationExp; IHakiData newHakiProps; LoyaltyEvent loyaltyEvent;
/* 128 */           switch (WyHelper.getResourceName(stat)) {
/*     */             
/*     */             case "doriki":
/* 131 */               newEntityStats.setDoriki((int)WyHelper.percentage(CommonConfig.INSTANCE.getDorikiKeepPercentage(), oldEntityStats.getDoriki()));
/* 132 */               dorikiEvent = new DorikiEvent(event.getPlayer(), newEntityStats.getDoriki());
/* 133 */               MinecraftForge.EVENT_BUS.post((Event)dorikiEvent);
/*     */               break;
/*     */             case "bounty":
/* 136 */               newEntityStats.setBounty((int)WyHelper.percentage(CommonConfig.INSTANCE.getBountyKeepPercentage(), oldEntityStats.getBounty()));
/* 137 */               bountyEvent = new BountyEvent(event.getPlayer(), newEntityStats.getBounty());
/* 138 */               MinecraftForge.EVENT_BUS.post((Event)bountyEvent);
/*     */               break;
/*     */             case "belly":
/* 141 */               newEntityStats.setBelly((int)WyHelper.percentage(CommonConfig.INSTANCE.getBellyKeepPercentage(), oldEntityStats.getBelly()));
/*     */               break;
/*     */             case "race":
/* 144 */               newEntityStats.setRace(oldEntityStats.getRace());
/* 145 */               newEntityStats.setSubRace(oldEntityStats.getSubRace());
/*     */               break;
/*     */             case "faction":
/* 148 */               newEntityStats.setFaction(oldEntityStats.getFaction());
/*     */               break;
/*     */             case "fighting_style":
/* 151 */               newEntityStats.setFightingStyle(oldEntityStats.getFightingStyle());
/*     */               break;
/*     */             case "devil_fruit":
/* 154 */               iNBT = DevilFruitCapability.INSTANCE.writeNBT(oldDevilFruitProps, null);
/* 155 */               DevilFruitCapability.INSTANCE.readNBT(newDevilFruitProps, null, iNBT);
/*     */               break;
/*     */             case "haki_exp":
/* 158 */               oldHakiProps = HakiDataCapability.get((LivingEntity)event.getOriginal());
/* 159 */               hardeningBusoExp = oldHakiProps.getBusoshokuHardeningHakiExp() * CommonConfig.INSTANCE.getHakiExpKeepPercentage() / 100.0F;
/* 160 */               imbuingBusoExp = oldHakiProps.getBusoshokuImbuingHakiExp() * CommonConfig.INSTANCE.getHakiExpKeepPercentage() / 100.0F;
/* 161 */               observationExp = oldHakiProps.getKenbunshokuHakiExp() * CommonConfig.INSTANCE.getHakiExpKeepPercentage() / 100.0F;
/*     */               
/* 163 */               newHakiProps = HakiDataCapability.get((LivingEntity)event.getPlayer());
/* 164 */               newHakiProps.setBusoshokuHardeningHakiExp(hardeningBusoExp);
/* 165 */               newHakiProps.setBusoshokuImbuingHakiExp(imbuingBusoExp);
/* 166 */               newHakiProps.setKenbunshokuHakiExp(observationExp);
/*     */               break;
/*     */             case "loyalty":
/* 169 */               newEntityStats.setLoyalty((int)WyHelper.percentage(((Integer)CommonConfig.INSTANCE.loyaltyKeepPercentage.get()).intValue(), oldEntityStats.getLoyalty()));
/* 170 */               loyaltyEvent = new LoyaltyEvent(event.getPlayer(), newEntityStats.getLoyalty());
/* 171 */               MinecraftForge.EVENT_BUS.post((Event)loyaltyEvent);
/*     */               break;
/*     */           } 
/*     */         
/*     */         } 
/*     */       } 
/* 177 */       restorePermaData(event.getOriginal(), event.getPlayer());
/*     */ 
/*     */       
/* 180 */       AbilityHelper.enableAbilities(event.getPlayer(), ability -> true);
/*     */       
/* 182 */       AbilityHelper.validateDevilFruitMoves(event.getPlayer());
/* 183 */       AbilityHelper.validateRacialMoves(event.getPlayer());
/* 184 */       AbilityHelper.validateStyleMoves(event.getPlayer());
/* 185 */       AbilityHelper.validateFactionMoves(event.getPlayer());
/*     */       
/* 187 */       WyNetwork.sendTo(new SSyncDevilFruitPacket(event.getPlayer().getEntityId(), newDevilFruitProps), event.getPlayer());
/* 188 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(event.getPlayer().getEntityId(), newAbilityData), event.getPlayer());
/*     */       
/* 190 */       if (CommonConfig.INSTANCE.isSpecialFlyingEnabled() && AbilityDataCapability.get((LivingEntity)event.getPlayer()).hasUnlockedAbility((Ability)SpecialFlyAbility.INSTANCE) && !event.getPlayer().isCreative() && !event.getPlayer().isSpectator())
/*     */       {
/* 192 */         (event.getPlayer()).abilities.allowFlying = false;
/* 193 */         (event.getPlayer()).abilities.isFlying = false;
/* 194 */         ((ServerPlayerEntity)event.getPlayer()).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket((event.getPlayer()).abilities));
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 199 */       restoreFullData(event.getOriginal(), event.getPlayer());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void restoreFullData(PlayerEntity original, PlayerEntity player) {
/* 205 */     IDevilFruit oldDevilFruitProps = DevilFruitCapability.get((LivingEntity)original);
/* 206 */     IDevilFruit newDevilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/* 207 */     IAbilityData newAbilityData = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 209 */     CompoundNBT compoundNBT = new CompoundNBT();
/*     */ 
/*     */     
/* 212 */     IEntityStats oldEntityStats = EntityStatsCapability.get((LivingEntity)original);
/* 213 */     INBT iNBT = EntityStatsCapability.INSTANCE.writeNBT(oldEntityStats, null);
/* 214 */     IEntityStats newEntityStats = EntityStatsCapability.get((LivingEntity)player);
/* 215 */     EntityStatsCapability.INSTANCE.readNBT(newEntityStats, null, iNBT);
/* 216 */     newEntityStats.setMaxCola(100);
/* 217 */     newEntityStats.setHeart(true);
/* 218 */     newEntityStats.setShadow(true);
/* 219 */     newEntityStats.setStrawDoll(true);
/* 220 */     newEntityStats.setCola(Math.max(Math.min(oldEntityStats.getCola(), newEntityStats.getMaxCola()), 0));
/* 221 */     DorikiEvent e = new DorikiEvent(player, newEntityStats.getDoriki());
/* 222 */     MinecraftForge.EVENT_BUS.post((Event)e);
/*     */ 
/*     */     
/* 225 */     iNBT = DevilFruitCapability.INSTANCE.writeNBT(oldDevilFruitProps, null);
/* 226 */     DevilFruitCapability.INSTANCE.readNBT(newDevilFruitProps, null, iNBT);
/* 227 */     newDevilFruitProps.setZoanPoint("");
/*     */ 
/*     */     
/* 230 */     IAbilityData oldAbilityData = AbilityDataCapability.get((LivingEntity)original);
/* 231 */     iNBT = AbilityDataCapability.INSTANCE.writeNBT(oldAbilityData, null);
/* 232 */     AbilityDataCapability.INSTANCE.readNBT(newAbilityData, null, iNBT);
/*     */ 
/*     */     
/* 235 */     IHakiData oldHakiData = HakiDataCapability.get((LivingEntity)original);
/* 236 */     iNBT = HakiDataCapability.INSTANCE.writeNBT(oldHakiData, null);
/* 237 */     IHakiData newHakiData = HakiDataCapability.get((LivingEntity)player);
/* 238 */     HakiDataCapability.INSTANCE.readNBT(newHakiData, null, iNBT);
/*     */     
/* 240 */     restorePermaData(original, player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void restorePermaData(PlayerEntity original, PlayerEntity player) {
/* 246 */     CompoundNBT compoundNBT = new CompoundNBT();
/*     */ 
/*     */     
/* 249 */     IQuestData oldQuestData = QuestDataCapability.get(original);
/* 250 */     INBT iNBT = QuestDataCapability.INSTANCE.writeNBT(oldQuestData, null);
/* 251 */     IQuestData newQuestData = QuestDataCapability.get(player);
/* 252 */     QuestDataCapability.INSTANCE.readNBT(newQuestData, null, iNBT);
/*     */ 
/*     */     
/* 255 */     IChallengesData oldChallengeData = ChallengesDataCapability.get(original);
/* 256 */     iNBT = ChallengesDataCapability.INSTANCE.writeNBT(oldChallengeData, null);
/* 257 */     IChallengesData newChallengeData = ChallengesDataCapability.get(player);
/* 258 */     ChallengesDataCapability.INSTANCE.readNBT(newChallengeData, null, iNBT);
/*     */ 
/*     */     
/* 261 */     IAbilityData oldAbilityData = AbilityDataCapability.get((LivingEntity)original);
/*     */     
/* 263 */     oldAbilityData.clearEquippedAbilities(AbilityHelper.getAbilityFromCategoryPredicate(APIConfig.AbilityCategory.ALL));
/*     */     
/* 265 */     AbilityHelper.validateDevilFruitMoves(original);
/* 266 */     AbilityHelper.validateRacialMoves(original);
/* 267 */     AbilityHelper.validateStyleMoves(original);
/* 268 */     AbilityHelper.validateFactionMoves(original);
/*     */     
/* 270 */     iNBT = AbilityDataCapability.INSTANCE.writeNBT(oldAbilityData, null);
/* 271 */     IAbilityData newAbilityData = AbilityDataCapability.get((LivingEntity)player);
/* 272 */     AbilityDataCapability.INSTANCE.readNBT(newAbilityData, null, iNBT);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\ConfigEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */