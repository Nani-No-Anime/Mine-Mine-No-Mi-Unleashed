package xyz.pixelatedw.mineminenomi.quests.doctor;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Items;
import xyz.pixelatedw.mineminenomi.abilities.doctor.FirstAidAbility;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.quests.objectives.EquippedItemObjective;
import xyz.pixelatedw.mineminenomi.quests.objectives.ObtainItemObjective;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class DoctorTrial01Quest extends Quest {
  private Objective objective01 = (Objective)new EquippedItemObjective("Equip a %s", 1, ModArmors.MEDIC_BAG, EquipmentSlotType.CHEST);
  private Objective objective02 = (new ObtainItemObjective("Collect %s nether warts", 15, Items.NETHER_WART)).addRequirement(this.objective01);
  private Objective objective03 = (new ObtainItemObjective("Collect %s glistering melon slices", 15, Items.GLISTERING_MELON_SLICE)).addRequirement(this.objective01);

  
  public DoctorTrial01Quest() {
    super("doctor_trial_01", "Trial: First Aid");
    addObjectives(new Objective[] { this.objective01, this.objective02, this.objective03 });
    
    this.onCompleteEvent = this::giveReward;
  }

  
  public boolean giveReward(PlayerEntity player) {
    if (!removeQuestItem(player, Items.NETHER_WART, 15)) {
      return false;
    }
    if (!removeQuestItem(player, Items.GLISTERING_MELON_SLICE, 15)) {
      return false;
    }
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    props.addUnlockedAbility((Ability)FirstAidAbility.INSTANCE);
    
    return true;
  }
}


