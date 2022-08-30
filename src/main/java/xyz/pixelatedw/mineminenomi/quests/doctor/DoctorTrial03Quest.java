/*    */ package xyz.pixelatedw.mineminenomi.quests.doctor;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doctor.DopingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doctor.MedicBagExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.BrewPotionObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.EquippedItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.quests.objectives.KillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class DoctorTrial03Quest extends Quest {
/*    */   static {
/* 25 */     MULTIPLE_DEBUFFS_CHECK = ((player, target, source) -> {
/*    */         int debuffs = 0;
/*    */         for (EffectInstance inst : target.getActivePotionEffects()) {
/*    */           if (!inst.getPotion().isBeneficial()) {
/*    */             debuffs++;
/*    */           }
/*    */         } 
/*    */         return (debuffs >= 3);
/*    */       });
/*    */   }
/*    */ 
/*    */   
/*    */   public static final KillEntityObjective.ICheckKill MULTIPLE_DEBUFFS_CHECK;
/* 38 */   private Objective objective01 = (Objective)new EquippedItemObjective("Equip a %s", 1, ModArmors.MEDIC_BAG, EquipmentSlotType.CHEST);
/* 39 */   private Objective objective02 = (new BrewPotionObjective("Brew %s healing potions", 12, new Item[] { Items.SPLASH_POTION }, new Effect[] { Effects.INSTANT_HEALTH })).addRequirements(new Objective[] { this.objective01 });
/* 40 */   private Objective objective03 = (new KillEntityObjective("Kill an enemy afflicted with 3+ debuffs", 1, MULTIPLE_DEBUFFS_CHECK)).addRequirements(new Objective[] { this.objective01 });
/*    */ 
/*    */   
/*    */   public DoctorTrial03Quest() {
/* 44 */     super("doctor_trial_03", "Trial: Medic Bag Explosion");
/* 45 */     addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
/* 46 */     addRequirement(ModQuests.DOCTOR_TRIAL_02);
/*    */     
/* 48 */     this.onCompleteEvent = this::giveReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean giveReward(PlayerEntity player) {
/* 53 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 55 */     props.addUnlockedAbility((Ability)MedicBagExplosionAbility.INSTANCE);
/* 56 */     props.addUnlockedAbility((Ability)DopingAbility.INSTANCE);
/*    */     
/* 58 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\doctor\DoctorTrial03Quest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */