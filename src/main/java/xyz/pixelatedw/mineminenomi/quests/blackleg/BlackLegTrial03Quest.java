/*    */ package xyz.pixelatedw.mineminenomi.quests.blackleg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.AntiMannerKickCourseAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.ConcasseAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.PartyTableKickCourseAbility;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.HitEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.TimedHitEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class BlackLegTrial03Quest extends Quest {
/*    */   static {
/* 20 */     EXTRA_HACHIS_HIT_CHECK = ((player, target, source, amount) -> source.getImmediateSource() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.blackleg.ExtraHachisProjectile);
/*    */   }
/*    */ 
/*    */   
/*    */   private static final HitEntityObjective.ICheckHit EXTRA_HACHIS_HIT_CHECK;
/* 25 */   private Objective objective01 = (Objective)new TimedHitEntityObjective("Hit %s enemies using Extra Hachis at the same time", 3, 3, EXTRA_HACHIS_HIT_CHECK);
/* 26 */   private Objective objective02 = (Objective)new KillEntityObjective("Kill %s enemies using Concasse", 10, SharedKillChecks.IS_KICKING.and(SharedKillChecks.checkAbilitySource((Ability)ConcasseAbility.INSTANCE)));
/*    */ 
/*    */   
/*    */   public BlackLegTrial03Quest() {
/* 30 */     super("black_leg_trial_03", "Trial: Courses");
/* 31 */     addRequirement(ModQuests.BLACK_LEG_TRIAL_02);
/*    */     
/* 33 */     addObjectives(new Objective[] { this.objective01, this.objective02 });
/*    */     
/* 35 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 40 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 42 */     props.addUnlockedAbility((Ability)AntiMannerKickCourseAbility.INSTANCE);
/* 43 */     props.addUnlockedAbility((Ability)PartyTableKickCourseAbility.INSTANCE);
/*    */     
/* 45 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\blackleg\BlackLegTrial03Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */