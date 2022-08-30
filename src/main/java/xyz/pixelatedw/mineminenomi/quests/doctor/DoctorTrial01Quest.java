/*    */ package xyz.pixelatedw.mineminenomi.quests.doctor;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Items;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doctor.FirstAidAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.EquippedItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class DoctorTrial01Quest extends Quest {
/* 17 */   private Objective objective01 = (Objective)new EquippedItemObjective("Equip a %s", 1, ModArmors.MEDIC_BAG, EquipmentSlotType.CHEST);
/* 18 */   private Objective objective02 = (new ObtainItemObjective("Collect %s nether warts", 15, Items.NETHER_WART)).addRequirement(this.objective01);
/* 19 */   private Objective objective03 = (new ObtainItemObjective("Collect %s glistering melon slices", 15, Items.GLISTERING_MELON_SLICE)).addRequirement(this.objective01);
/*    */ 
/*    */   
/*    */   public DoctorTrial01Quest() {
/* 23 */     super("doctor_trial_01", "Trial: First Aid");
/* 24 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/*    */     
/* 26 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 31 */     if (!removeQuestItem(player, Items.NETHER_WART, 15)) {
/* 32 */       return false;
/*    */     }
/* 34 */     if (!removeQuestItem(player, Items.GLISTERING_MELON_SLICE, 15)) {
/* 35 */       return false;
/*    */     }
/* 37 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 39 */     props.addUnlockedAbility((Ability)FirstAidAbility.INSTANCE);
/*    */     
/* 41 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\doctor\DoctorTrial01Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */