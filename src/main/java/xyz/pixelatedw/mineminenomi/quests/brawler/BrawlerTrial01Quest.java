/*    */ package xyz.pixelatedw.mineminenomi.quests.brawler;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.brawler.SuplexAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class BrawlerTrial01Quest extends Quest {
/* 14 */   private Objective objective01 = (Objective)new KillEntityObjective("Kill %s enemies using your fists", 30, SharedKillChecks.HAS_EMPTY_HAND);
/*    */ 
/*    */   
/*    */   public BrawlerTrial01Quest() {
/* 18 */     super("brawler_trial_01", "Trial: Suplex");
/*    */     
/* 20 */     addObjectives(new Objective[] { this.objective01 });
/*    */     
/* 22 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 27 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 29 */     props.addUnlockedAbility((Ability)SuplexAbility.INSTANCE);
/*    */     
/* 31 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\brawler\BrawlerTrial01Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */