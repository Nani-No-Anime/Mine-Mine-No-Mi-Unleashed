/*    */ package xyz.pixelatedw.mineminenomi.quests.doctor;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
import xyz.pixelatedw.mineminenomi.abilities.doctor.FailedExperimentAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doctor.FirstAidAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.BrewPotionObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.EquippedItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.HealEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class DoctorTrial02Quest extends Quest {
/*    */   static {
/* 23 */     FIRST_AID_VILLAGERS_CHECK = ((player, target, amount) -> {
/*    */         IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */         
/*    */         FirstAidAbility ability = (FirstAidAbility)props.getEquippedAbility((Ability)FirstAidAbility.INSTANCE);
/* 27 */         boolean hasAbility = (ability != null && ability.isContinuous());
/*    */         
/*    */         ItemStack medicBag = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
/* 30 */         boolean hasMedicBag = (medicBag != null && medicBag.getItem() == ModArmors.MEDIC_BAG);
/*    */         
/*    */         boolean hasHealNeed = (target.getHealth() < target.getMaxHealth());
/*    */         
/* 34 */         return (hasHealNeed && hasAbility && hasMedicBag);
/*    */       });
/*    */   }
/* 37 */   private static final HealEntityObjective.ICheckHeal FIRST_AID_VILLAGERS_CHECK; private Objective objective01 = (Objective)new EquippedItemObjective("Equip a %s", 1, ModArmors.MEDIC_BAG, EquipmentSlotType.CHEST);
/* 38 */   private Objective objective02 = (new BrewPotionObjective("Brew %s splash potions", 12, new Item[] { Items.SPLASH_POTION }, null)).addRequirements(new Objective[] { this.objective01 });
/* 39 */   private Objective objective03 = (new HealEntityObjective("Heal %s Villagers using First Aid", 10, FIRST_AID_VILLAGERS_CHECK)).addRequirement(this.objective01);
/*    */ 
/*    */   
/*    */   public DoctorTrial02Quest() {
/* 43 */     super("doctor_trial_02", "Trial: Failed Experiment");
/* 44 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/* 45 */     addRequirement(ModQuests.DOCTOR_TRIAL_01);
/*    */     
/* 47 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 52 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 54 */     props.addUnlockedAbility(FailedExperimentAbility.INSTANCE);
/*    */     
/* 56 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\doctor\DoctorTrial02Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */