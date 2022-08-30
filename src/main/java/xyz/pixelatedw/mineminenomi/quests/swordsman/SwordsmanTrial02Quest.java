/*    */ package xyz.pixelatedw.mineminenomi.quests.swordsman;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.enchantment.Enchantments;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.YakkodoriAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.TimedHitEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class SwordsmanTrial02Quest extends Quest {
/*    */   static {
/* 22 */     ITEM_WITH_SHARPNESS_2 = (itemStack -> 
/*    */       
/* 24 */       (ItemsHelper.isSword(itemStack) && EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, itemStack) > 1));
/*    */   }
/*    */   private static final Predicate<ItemStack> ITEM_WITH_SHARPNESS_2;
/* 27 */   private Objective objective01 = (Objective)new TimedHitEntityObjective("Hit %s enemies at the same time", 3, 2);
/* 28 */   private Objective objective02 = (Objective)new KillEntityObjective("Kill %s enemies with critical hits", 25, SharedKillChecks.CRITICAL_KILL_CHECK.and(SharedKillChecks.HAS_SWORD));
/* 29 */   private Objective objective03 = (Objective)new ObtainItemObjective("Obtain a sword with Sharpness II", 1, ITEM_WITH_SHARPNESS_2);
/*    */ 
/*    */   
/*    */   public SwordsmanTrial02Quest() {
/* 33 */     super("swordsman_trial_02", "Trial: Yakkodori");
/* 34 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*    */     
/* 36 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 41 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 43 */     props.addUnlockedAbility(YakkodoriAbility.INSTANCE);
/*    */     
/* 45 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\swordsman\SwordsmanTrial02Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */