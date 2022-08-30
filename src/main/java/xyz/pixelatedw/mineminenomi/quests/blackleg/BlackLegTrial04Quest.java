/*    */ package xyz.pixelatedw.mineminenomi.quests.blackleg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.SkywalkAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.TimedSurvivalObjective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class BlackLegTrial04Quest extends Quest {
/*    */   static {
/* 20 */     BREATH_DIAL_CHECK = (itemStack -> (itemStack.getItem() == ModBlocks.BREATH_DIAL.asItem()));
/*    */   }
/*    */ 
/*    */   
/*    */   private static final Predicate<ItemStack> BREATH_DIAL_CHECK;
/* 25 */   private Objective objective01 = (Objective)new ObtainItemObjective("Collect %s breath dials", 5, BREATH_DIAL_CHECK);
/* 26 */   private Objective objective02 = (Objective)new ObtainItemObjective("Collect %s rabbit feet", 5, Items.RABBIT_FOOT);
/* 27 */   private Objective objective03 = (Objective)new TimedSurvivalObjective("Survive for %s seconds without getting hit", 1800);
/*    */ 
/*    */   
/*    */   public BlackLegTrial04Quest() {
/* 31 */     super("black_leg_trial_04", "Trial: Skywalk");
/* 32 */     addRequirement(ModQuests.BLACK_LEG_TRIAL_03);
/*    */     
/* 34 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*    */     
/* 36 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 41 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 43 */     props.addUnlockedAbility((Ability)SkywalkAbility.INSTANCE);
/*    */     
/* 45 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\blackleg\BlackLegTrial04Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */