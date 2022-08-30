/*    */ package xyz.pixelatedw.mineminenomi.quests.swordsman;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.ShiShishiSonsonAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class SwordsmanTrial01Quest extends Quest {
/*    */   static {
/* 20 */     ITEM_OVER_7_DAMAGE = (itemStack -> 
/*    */       
/* 22 */       (ItemsHelper.isSword(itemStack) && ItemsHelper.getItemDamage(itemStack) > 7.0F));
/*    */   }
/*    */   private static final Predicate<ItemStack> ITEM_OVER_7_DAMAGE;
/* 25 */   private Objective objective01 = (Objective)new ObtainItemObjective("Obtain a sword with over 7 damage", 1, ITEM_OVER_7_DAMAGE);
/* 26 */   private Objective objective02 = (new ObtainItemObjective("Collect %s bones", 30, Items.BONE)).addRequirement(this.objective01);
/* 27 */   private Objective objective03 = (new KillEntityObjective("Kill %s enemies while running towards them", 15, SharedKillChecks.PLAYER_RUNNING_CHECK.and(SharedKillChecks.HAS_SWORD))).addRequirement(this.objective01);
/*    */ 
/*    */   
/*    */   public SwordsmanTrial01Quest() {
/* 31 */     super("swordsman_trial_01", "Trial: Shi Shishi Sonson");
/* 32 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*    */     
/* 34 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 39 */     if (!removeQuestItem(player, Items.BONE, 30)) {
/* 40 */       return false;
/*    */     }
/* 42 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 44 */     props.addUnlockedAbility((Ability)ShiShishiSonsonAbility.INSTANCE);
/*    */     
/* 46 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\swordsman\SwordsmanTrial01Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */