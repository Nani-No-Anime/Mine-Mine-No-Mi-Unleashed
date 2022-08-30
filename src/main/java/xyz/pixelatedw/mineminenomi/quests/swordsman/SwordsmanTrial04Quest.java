/*    */ package xyz.pixelatedw.mineminenomi.quests.swordsman;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.HitEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedHitChecks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.TimedKillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class SwordsmanTrial04Quest extends Quest {
/* 18 */   private Objective objective01 = (Objective)new HitEntityObjective("Damage %s enemies with sweeping attacks", 20, SharedHitChecks.SWEEP_ATTACK_CHECK);
/* 19 */   private Objective objective02 = (Objective)new TimedKillEntityObjective("Kill %s enemies in less than %s seconds", 3, 5, SharedKillChecks.HAS_SWORD);
/* 20 */   private Objective objective03 = (Objective)new KillEntityObjective("Kill %s airborne enemies with your sword", 5, SharedKillChecks.AIRBORNE_ENEMY_CHECK);
/*    */ 
/*    */   
/*    */   public SwordsmanTrial04Quest() {
/* 24 */     super("swordsman_trial_04", "Trial: O Tatsumaki");
/* 25 */     addRequirements(new Quest[] { ModQuests.SWORDSMAN_TRIAL_03 });
/* 26 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*    */     
/* 28 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 33 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 35 */     props.addUnlockedAbility(OTatsumakiAbility.INSTANCE);
/*    */     
/* 37 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\swordsman\SwordsmanTrial04Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */