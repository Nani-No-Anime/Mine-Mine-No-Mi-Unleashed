/*    */ package xyz.pixelatedw.mineminenomi.quests.brawler;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.HakaiHoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.JishinHoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.KingPunchAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ReachDorikiObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.TimedKillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class BrawlerTrial06Quest extends Quest {
/* 19 */   private Objective objective01 = (Objective)new ReachDorikiObjective("Reach %s doriki", 7000);
/* 20 */   private Objective objective02 = (new KillEntityObjective("Kill %s enemies using Hakai Ho", 10, SharedKillChecks.checkAbilitySource((Ability)HakaiHoAbility.INSTANCE))).addRequirement(this.objective01);
/* 21 */   private Objective objective03 = (new TimedKillEntityObjective("Kill %s enemies using Jishin Ho in %s seconds or less", 5, 5, SharedKillChecks.checkAbilitySource((Ability)JishinHoAbility.INSTANCE))).addRequirement(this.objective01);
/*    */ 
/*    */   
/*    */   public BrawlerTrial06Quest() {
/* 25 */     super("brawler_trial_06", "Trial: King Punch");
/* 26 */     addRequirements(new Quest[] { ModQuests.BRAWLER_TRIAL_04, ModQuests.BRAWLER_TRIAL_05 });
/*    */     
/* 28 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*    */     
/* 30 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 35 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 37 */     props.addUnlockedAbility((Ability)KingPunchAbility.INSTANCE);
/*    */     
/* 39 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\brawler\BrawlerTrial06Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */