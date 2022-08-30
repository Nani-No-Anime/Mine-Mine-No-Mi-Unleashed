/*    */ package xyz.pixelatedw.mineminenomi.quests.blackleg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.ConcasseAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class BlackLegTrial01Quest extends Quest {
/* 14 */   private Objective objective01 = (Objective)new KillEntityObjective("Kill %s enemies using your kicks", 30, SharedKillChecks.IS_KICKING);
/* 15 */   private Objective objective02 = (Objective)new KillEntityObjective("Kill %s enemies using critical hits", 15, SharedKillChecks.IS_KICKING.and(SharedKillChecks.CRITICAL_KILL_CHECK));
/*    */ 
/*    */   
/*    */   public BlackLegTrial01Quest() {
/* 19 */     super("black_leg_trial_01", "Trial: Concasse");
/*    */     
/* 21 */     addObjectives(new Objective[] { this.objective01, this.objective02 });
/*    */     
/* 23 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 28 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 30 */     props.addUnlockedAbility((Ability)ConcasseAbility.INSTANCE);
/*    */     
/* 32 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\blackleg\BlackLegTrial01Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */