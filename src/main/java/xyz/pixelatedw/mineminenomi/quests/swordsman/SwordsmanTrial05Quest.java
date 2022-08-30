/*    */ package xyz.pixelatedw.mineminenomi.quests.swordsman;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.enchantment.EnchantmentData;
/*    */ import net.minecraft.enchantment.Enchantments;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.EnchantedBookItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.HiryuKaenAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.SharedKillChecks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class SwordsmanTrial05Quest extends Quest {
/* 20 */   private Objective objective01 = (Objective)new KillEntityObjective("Kill %s enemies while they're on fire", 30, SharedKillChecks.ON_FIRE_ENEMY_CHECK);
/* 21 */   private Objective objective02 = (Objective)new KillEntityObjective("Kill %s enemies while you're on fire", 10, SharedKillChecks.ON_FIRE_PLAYER_CHECK);
/*    */ 
/*    */   
/*    */   public SwordsmanTrial05Quest() {
/* 25 */     super("swordsman_trial_05", "Trial: Hiryu Kaen");
/* 26 */     addRequirements(new Quest[] { ModQuests.SWORDSMAN_TRIAL_04 });
/* 27 */     addObjectives(new Objective[] { this.objective01, this.objective02 });
/*    */     
/* 29 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 34 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 36 */     ItemStack stack = new ItemStack((IItemProvider)Items.ENCHANTED_BOOK);
/* 37 */     EnchantedBookItem.addEnchantment(stack, new EnchantmentData(Enchantments.FIRE_ASPECT, 2));
/* 38 */     player.addItemStackToInventory(stack);
/*    */     
/* 40 */     props.addUnlockedAbility((Ability)HiryuKaenAbility.INSTANCE);
/*    */     
/* 42 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\swordsman\SwordsmanTrial05Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */