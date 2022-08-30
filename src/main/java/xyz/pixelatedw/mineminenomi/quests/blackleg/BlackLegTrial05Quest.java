/*    */ package xyz.pixelatedw.mineminenomi.quests.blackleg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.BienCuitGrillShotAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.DiableJambeAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class BlackLegTrial05Quest extends Quest {
/* 16 */   private Objective objective01 = (Objective)new KillEntityObjective("Kill %s on fire enemies using your kicks", 15, SharedKillChecks.IS_KICKING.and(SharedKillChecks.ON_FIRE_ENEMY_CHECK));
/*    */ 
/*    */   
/*    */   public BlackLegTrial05Quest() {
/* 20 */     super("black_leg_trial_05", "Trial: Diable Jambe");
/* 21 */     addRequirement(ModQuests.BLACK_LEG_TRIAL_03);
/*    */     
/* 23 */     addObjectives(new Objective[] { this.objective01 });
/*    */     
/* 25 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 30 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 32 */     props.addUnlockedAbility((Ability)DiableJambeAbility.INSTANCE);
/* 33 */     props.addUnlockedAbility((Ability)BienCuitGrillShotAbility.INSTANCE);
/*    */     
/* 35 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\blackleg\BlackLegTrial05Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */