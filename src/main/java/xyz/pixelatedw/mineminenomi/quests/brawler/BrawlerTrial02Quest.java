/*    */ package xyz.pixelatedw.mineminenomi.quests.brawler;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.SpinningBrawlAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.SuplexAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class BrawlerTrial02Quest extends Quest {
/* 16 */   private Objective objective01 = (Objective)new KillEntityObjective("Kill %s enemies using Suplex", 20, SharedKillChecks.HAS_EMPTY_HAND.and(SharedKillChecks.checkAbilitySource((Ability)SuplexAbility.INSTANCE)));
/*    */ 
/*    */   
/*    */   public BrawlerTrial02Quest() {
/* 20 */     super("brawler_trial_02", "Trial: Spinning Brawl");
/* 21 */     addRequirement(ModQuests.BRAWLER_TRIAL_01);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 26 */     addObjectives(new Objective[] { this.objective01 });
/*    */     
/* 28 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean startArena(PlayerEntity player) {
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 38 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 40 */     props.addUnlockedAbility((Ability)SpinningBrawlAbility.INSTANCE);
/*    */     
/* 42 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\brawler\BrawlerTrial02Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */