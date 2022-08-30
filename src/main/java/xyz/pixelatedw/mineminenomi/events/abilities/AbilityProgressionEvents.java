/*     */ package xyz.pixelatedw.mineminenomi.events.abilities;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.ColaOverdriveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.CoupDeBooAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.CoupDeVentAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.FreshFireAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.RadicalBeamAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.cyborg.StrongRightAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.electro.EleclawAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalLunaAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalMissileAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalShowerAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalTempestaAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.electro.SulongAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KachiageHaisokuAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KarakusagawaraSeikenAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.MurasameAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.SamehadaShoteiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.TwoFishEngineAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.UchimizuAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.YarinamiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.GeppoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.KamieAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.RankyakuAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.RokuoganAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.ShiganAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.SoruAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.TekkaiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.DorikiEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.HakiExpEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class AbilityProgressionEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onHakiExpGained(HakiExpEvent event) {
/*  52 */     HakiHelper.checkForUnlocks(event.getPlayer());
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onDorikiGained(DorikiEvent event) {
/*  58 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)event.getPlayer());
/*  59 */     if (props.isHuman()) {
/*     */       
/*  61 */       gainAbility(event.getPlayer(), 500, SoruAbility.INSTANCE);
/*  62 */       gainAbility(event.getPlayer(), 1000, TekkaiAbility.INSTANCE);
/*  63 */       gainAbility(event.getPlayer(), 1250, (Ability)GeppoAbility.INSTANCE);
/*  64 */       gainAbility(event.getPlayer(), 3000, ShiganAbility.INSTANCE);
/*  65 */       gainAbility(event.getPlayer(), 3600, KamieAbility.INSTANCE);
/*  66 */       gainAbility(event.getPlayer(), 5000, RankyakuAbility.INSTANCE);
/*  67 */       gainAbility(event.getPlayer(), 9000, (Ability)RokuoganAbility.INSTANCE);
/*     */     }
/*  69 */     else if (props.isFishman()) {
/*     */       
/*  71 */       gainAbility(event.getPlayer(), 800, UchimizuAbility.INSTANCE);
/*  72 */       gainAbility(event.getPlayer(), 1500, KachiageHaisokuAbility.INSTANCE);
/*  73 */       gainAbility(event.getPlayer(), 2000, (Ability)TwoFishEngineAbility.INSTANCE);
/*  74 */       gainAbility(event.getPlayer(), 3000, SamehadaShoteiAbility.INSTANCE);
/*  75 */       gainAbility(event.getPlayer(), 5000, MurasameAbility.INSTANCE);
/*  76 */       gainAbility(event.getPlayer(), 5500, (Ability)YarinamiAbility.INSTANCE);
/*  77 */       gainAbility(event.getPlayer(), 7500, KarakusagawaraSeikenAbility.INSTANCE);
/*     */     }
/*  79 */     else if (props.isCyborg()) {
/*     */       
/*  81 */       gainAbility(event.getPlayer(), 0, FreshFireAbility.INSTANCE);
/*  82 */       gainAbility(event.getPlayer(), 0, ColaOverdriveAbility.INSTANCE);
/*  83 */       gainAbility(event.getPlayer(), 0, StrongRightAbility.INSTANCE);
/*  84 */       gainAbility(event.getPlayer(), 0, RadicalBeamAbility.INSTANCE);
/*  85 */       gainAbility(event.getPlayer(), 0, CoupDeVentAbility.INSTANCE);
/*  86 */       gainAbility(event.getPlayer(), 0, CoupDeBooAbility.INSTANCE);
/*     */     }
/*  88 */     else if (props.isMink()) {
/*     */       
/*  90 */       gainAbility(event.getPlayer(), 500, (Ability)EleclawAbility.INSTANCE);
/*  91 */       gainAbility(event.getPlayer(), 800, (Ability)ElectricalMissileAbility.INSTANCE);
/*  92 */       gainAbility(event.getPlayer(), 1200, (Ability)SulongAbility.INSTANCE);
/*  93 */       gainAbility(event.getPlayer(), 3000, (Ability)ElectricalTempestaAbility.INSTANCE);
/*  94 */       gainAbility(event.getPlayer(), 3600, (Ability)ElectricalLunaAbility.INSTANCE);
/*  95 */       gainAbility(event.getPlayer(), 7000, (Ability)ElectricalShowerAbility.INSTANCE);
/*     */     } 
/*     */     
/*  98 */     HakiHelper.checkForUnlocks(event.getPlayer());
/*     */   }
/*     */ 
/*     */   
/*     */   private static void gainAbility(PlayerEntity player, int doriki, Ability ability) {
/* 103 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 104 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 106 */     if (props.getDoriki() >= doriki && !abilityProps.hasUnlockedAbility(ability) && !AbilityHelper.verifyIfAbilityIsBanned(ability))
/* 107 */       abilityProps.addUnlockedAbility(ability); 
/* 108 */     if ((props.getDoriki() < doriki || AbilityHelper.verifyIfAbilityIsBanned(ability)) && abilityProps.hasUnlockedAbility(ability) && abilityProps.getUnlockedAbility(ability).getUnlockType() != AbilityUnlock.COMMAND) {
/* 109 */       abilityProps.removeUnlockedAbility(ability);
/*     */     }
/* 111 */     WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps), player);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\abilities\AbilityProgressionEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */