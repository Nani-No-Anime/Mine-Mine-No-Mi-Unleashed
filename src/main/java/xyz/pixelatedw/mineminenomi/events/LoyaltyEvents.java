/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.marineloyalty.MusterAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.marineloyalty.SmallMusterAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.LoyaltyEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.SetPlayerDetailsEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
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
/*     */ public class LoyaltyEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onPlayerUpdate(LivingEvent.LivingUpdateEvent event) {
/*  32 */     if (!(event.getEntityLiving() instanceof PlayerEntity) || (event.getEntityLiving()).world.isRemote) {
/*     */       return;
/*     */     }
/*  35 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*  36 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */     
/*  38 */     boolean isMarineOrRevo = (props.isMarine() || props.isRevolutionary());
/*     */     
/*  40 */     if (!isMarineOrRevo || props.getLoyalty() >= 100) {
/*     */       return;
/*     */     }
/*  43 */     if (props.getDoriki() < 4000 && props.getLoyalty() > 15) {
/*     */       return;
/*     */     }
/*  46 */     if (player.world.getGameTime() % 24000L == 0L) {
/*     */       
/*  48 */       props.alterLoyalty(1);
/*  49 */       LoyaltyEvent loyaltyEvent = new LoyaltyEvent(player, 1L);
/*  50 */       if (MinecraftForge.EVENT_BUS.post((Event)loyaltyEvent)) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void checkForAbilityGain(LoyaltyEvent event) {
/*  58 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)event.getPlayer());
/*  59 */     if (props.isMarine()) {
/*     */       
/*  61 */       gainMarineAbility(event.getPlayer(), FactionHelper.MarineRank.LIEUTENANT, (Ability)SmallMusterAbility.INSTANCE);
/*  62 */       gainMarineAbility(event.getPlayer(), FactionHelper.MarineRank.CAPTAIN, (Ability)MusterAbility.INSTANCE);
/*     */     }
/*  64 */     else if (props.isRevolutionary()) {
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onStatsChoose(SetPlayerDetailsEvent event) {
/*  73 */     event.getEntityStats().setLoyalty(0);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void gainRevolutionaryAbility(PlayerEntity player, FactionHelper.RevolutionaryRank rank, Ability ability) {
/*  78 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*  79 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  81 */     if (props.hasRevolutionaryRank(rank) && !abilityProps.hasUnlockedAbility(ability) && !AbilityHelper.verifyIfAbilityIsBanned(ability)) {
/*     */       
/*  83 */       abilityProps.addUnlockedAbility(ability);
/*  84 */       player.sendMessage((ITextComponent)new StringTextComponent("Obtained " + ability.getName()));
/*     */     } 
/*  86 */     if ((!props.hasRevolutionaryRank(rank) || AbilityHelper.verifyIfAbilityIsBanned(ability)) && abilityProps.hasUnlockedAbility(ability)) {
/*  87 */       abilityProps.removeUnlockedAbility(ability);
/*     */     }
/*  89 */     WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps), player);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void gainMarineAbility(PlayerEntity player, FactionHelper.MarineRank rank, Ability ability) {
/*  94 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*  95 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  97 */     if (props.hasMarineRank(rank) && !abilityProps.hasUnlockedAbility(ability) && !AbilityHelper.verifyIfAbilityIsBanned(ability)) {
/*     */       
/*  99 */       abilityProps.addUnlockedAbility(ability);
/* 100 */       player.sendMessage((ITextComponent)new StringTextComponent("Obtained " + ability.getName()));
/*     */     } 
/* 102 */     if ((!props.hasMarineRank(rank) || AbilityHelper.verifyIfAbilityIsBanned(ability)) && abilityProps.hasUnlockedAbility(ability)) {
/* 103 */       abilityProps.removeUnlockedAbility(ability);
/*     */     }
/* 105 */     WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps), player);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\LoyaltyEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */