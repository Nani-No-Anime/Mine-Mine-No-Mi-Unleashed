/*    */ package xyz.pixelatedw.mineminenomi.quests.brawler;
/*    */ import java.lang.invoke.SerializedLambda;

import java.util.function.Predicate;

/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.HakaiHoAbility;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class BrawlerTrial04Quest extends Quest {
/*    */   private static final Predicate<ItemStack> IMPACT_DIAL_CHECK;
/*    */   
/*    */   static {
/* 23 */     IMPACT_DIAL_CHECK = (itemStack -> (itemStack.getItem() == ModBlocks.IMPACT_DIAL.asItem()));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 28 */     DISTANCE_CHECK = ((player, target, source) -> {
/*    */         boolean isProj = source.getImmediateSource() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
/*    */         
/*    */         boolean hasDistance = (player.getDistance((Entity)target) >= 30.0F);
/*    */         
/* 33 */         return (isProj && hasDistance);
/*    */       });
/*    */   }
/* 36 */   private static final KillEntityObjective.ICheckKill DISTANCE_CHECK; private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s gunpowder", 30, Items.GUNPOWDER);
/* 37 */   private Objective objective02 = (Objective)new ObtainItemObjective("Collect %s impact dials", 5, IMPACT_DIAL_CHECK);
/* 38 */   private Objective objective03 = (new KillEntityObjective("Kill %s enemies from at least 30 blocks away using Genkotsu Meteor", 5, SharedKillChecks.HAS_BRALWER_HAND_CHECK.and(DISTANCE_CHECK))).addRequirements(new Objective[] { this.objective01, this.objective02 });
/*    */ 
/*    */   
/*    */   public BrawlerTrial04Quest() {
/* 42 */     super("brawler_trial_04", "Trial: Hakai Ho");
/* 43 */     addRequirement(ModQuests.BRAWLER_TRIAL_03);
/*    */     
/* 45 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*    */     
/* 47 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 52 */     if (!removeQuestItem(player, Items.GUNPOWDER, 30)) {
/* 53 */       return false;
/*    */     }
/* 55 */     if (!removeQuestItem(player, ModBlocks.IMPACT_DIAL.asItem(), 5)) {
/* 56 */       return false;
/*    */     }
/* 58 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 60 */     props.addUnlockedAbility((Ability)HakaiHoAbility.INSTANCE);
/*    */     
/* 62 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\brawler\BrawlerTrial04Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */