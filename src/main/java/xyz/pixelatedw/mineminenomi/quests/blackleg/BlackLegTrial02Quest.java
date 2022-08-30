/*    */ package xyz.pixelatedw.mineminenomi.quests.blackleg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.ExtraHachisAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.TimedKillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class BlackLegTrial02Quest extends Quest {
/* 15 */   private Objective objective01 = (Objective)new TimedKillEntityObjective("Kill %s enemies in less than %s seconds", 3, 7, SharedKillChecks.IS_KICKING);
/*    */ 
/*    */   
/*    */   public BlackLegTrial02Quest() {
/* 19 */     super("black_leg_trial_02", "Trial: Extra Hachis");
/* 20 */     addRequirement(ModQuests.BLACK_LEG_TRIAL_01);
/*    */     
/* 22 */     addObjectives(new Objective[] { this.objective01 });
/*    */     
/* 24 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 29 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 31 */     props.addUnlockedAbility((Ability)ExtraHachisAbility.INSTANCE);
/*    */     
/* 33 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\blackleg\BlackLegTrial02Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */