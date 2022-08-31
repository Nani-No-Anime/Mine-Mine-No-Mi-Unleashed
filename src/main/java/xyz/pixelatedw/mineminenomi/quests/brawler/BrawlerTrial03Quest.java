/*    */ package xyz.pixelatedw.mineminenomi.quests.brawler;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.GenkotsuMeteorAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.SpinningBrawlAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.HitEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedHitChecks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class BrawlerTrial03Quest extends Quest {
/* 20 */   private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s cannon balls", 30, ModItems.CANNON_BALL);
/* 21 */   private Objective objective02 = (new HitEntityObjective("Hit at least %s enemies using Spinning Brawl", 5, SharedHitChecks.checkAbilitySource((Ability)SpinningBrawlAbility.INSTANCE))).addRequirement(this.objective01);
/* 22 */   private Objective objective03 = (new KillEntityObjective("Kill %s enemies using critical hits", 10, SharedKillChecks.CRITICAL_KILL_CHECK.and(SharedKillChecks.HAS_EMPTY_HAND))).addRequirement(this.objective01);
/*    */ 
/*    */   
/*    */   public BrawlerTrial03Quest() {
/* 26 */     super("brawler_trial_03", "Trial: Genkotsu Meteor");
/* 27 */     addRequirement(ModQuests.BRAWLER_TRIAL_02);
/*    */     
/* 29 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*    */     
/* 31 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 36 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 38 */     props.addUnlockedAbility((Ability)GenkotsuMeteorAbility.INSTANCE);
/*    */     
/* 40 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\brawler\BrawlerTrial03Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */