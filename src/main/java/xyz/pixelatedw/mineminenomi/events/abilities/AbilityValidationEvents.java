/*    */ package xyz.pixelatedw.mineminenomi.events.abilities;
/*    */ 
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncChallengeDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class AbilityValidationEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
/* 38 */     if (event.getEntity() instanceof PlayerEntity && CommonConfig.INSTANCE.isAbilityFraudChecksEnabled()) {
/*    */       
/* 40 */       PlayerEntity player = (PlayerEntity)event.getEntity();
/* 41 */       IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/* 42 */       IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/* 43 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/* 44 */       IChallengesData challengesProps = ChallengesDataCapability.get(player);
/*    */       
/* 46 */       if (!player.world.isRemote)
/*    */       {
/* 48 */         ItemStack dfStack = DevilFruitHelper.getDevilFruitItem(devilFruitProps.getDevilFruit());
/*    */         
/* 50 */         if (dfStack.getItem() == Blocks.AIR.asItem()) {
/* 51 */           devilFruitProps.setDevilFruit("");
/*    */         }
/* 53 */         AbilityHelper.validateDevilFruitMoves(player);
/* 54 */         AbilityHelper.validateRacialMoves(player);
/* 55 */         AbilityHelper.validateStyleMoves(player);
/* 56 */         AbilityHelper.validateFactionMoves(player);
/*    */         
/* 58 */         for (Ability abl : abilityProps.getUnlockedAbilities(AbilityHelper.getDevilFruitCategory())) {
/*    */           
/* 60 */           if (abl instanceof xyz.pixelatedw.mineminenomi.abilities.yomi.KasuriutaFubukiGiriAbility || abl instanceof xyz.pixelatedw.mineminenomi.abilities.yomi.SoulParadeAbility || abl instanceof xyz.pixelatedw.mineminenomi.abilities.yomi.YomiNoReikiAbility)
/*    */           {
/* 62 */             if (!YomiZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
/* 63 */               abilityProps.removeUnlockedAbility(abl);
/*    */             }
/*    */           }
/*    */         } 
/* 67 */         for (int i = 0; i < (abilityProps.getEquippedAbilities()).length; i++) {
/*    */           
/* 69 */           if (abilityProps.getEquippedAbility(i) != null)
/*    */           {
/* 71 */             if (AbilityHelper.verifyIfAbilityIsBanned(abilityProps.getEquippedAbility(i))) {
/* 72 */               abilityProps.setEquippedAbility(i, null);
/*    */             }
/*    */           }
/*    */         } 
/* 76 */         WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), entityStatsProps), player);
/* 77 */         WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), player);
/* 78 */         WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps), player);
/* 79 */         WyNetwork.sendTo(new SSyncChallengeDataPacket(player.getEntityId(), challengesProps), player);
/*    */       }
/*    */     
/* 82 */     } else if (event.getEntity() instanceof PlayerEntity && !CommonConfig.INSTANCE.isAbilityFraudChecksEnabled()) {
/*    */       
/* 84 */       PlayerEntity player = (PlayerEntity)event.getEntity();
/* 85 */       IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/* 86 */       IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/* 87 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/* 88 */       IChallengesData challengesProps = ChallengesDataCapability.get(player);
/*    */       
/* 90 */       WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), entityStatsProps), player);
/* 91 */       WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), player);
/* 92 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps), player);
/* 93 */       WyNetwork.sendTo(new SSyncChallengeDataPacket(player.getEntityId(), challengesProps), player);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\abilities\AbilityValidationEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */