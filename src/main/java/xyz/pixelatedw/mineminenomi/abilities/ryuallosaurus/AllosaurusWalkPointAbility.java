package xyz.pixelatedw.mineminenomi.abilities.ryuallosaurus;

import java.util.UUID;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.AllosaurusWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class AllosaurusWalkPointAbility
  extends ZoanAbility {
  public static final AllosaurusWalkPointAbility INSTANCE = new AllosaurusWalkPointAbility();
  
  private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d141ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Allosaurus Walk Point Speed Modifier", 0.18D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
  private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Allosaurus Walk Point Armor Modifier", 15.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Allosaurus Walk Point Strength Modifier", 9.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Allosaurus Walk Point Attack Speed Modifier", -0.20000000298023224D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Allosaurus Walk Point Reach Modifier", 0.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STEP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("e857dc49-c6b0-4c05-b9c7-32d9115fc969"), (Ability)INSTANCE, "Allosaurus Walk Point Step Height Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Allosaurus Walk Point Knockback Resistance Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("8521a343-3d02-4d64-bbd3-a7e272fd8b74"), (Ability)INSTANCE, "Allosaurus Walk Fall Resistance Modifier", 5.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public AllosaurusWalkPointAbility() {
    super("Allosaurus Walk Point", AbilityHelper.getDevilFruitCategory());
    setDescription("Transforms the user into an ancient allosaurus.");
    addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
    addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT);
    addZoanModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, (AttributeModifier)KNOCKBACK_RESISTANCE);
    addZoanModifier(ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_RESISTANCE_MODIFIER);
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)AllosaurusWalkZoanInfo.INSTANCE;
  }
}


