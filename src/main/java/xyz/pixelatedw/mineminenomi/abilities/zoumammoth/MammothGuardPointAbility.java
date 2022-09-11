package xyz.pixelatedw.mineminenomi.abilities.zoumammoth;

import java.util.UUID;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.MammothGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class MammothGuardPointAbility
  extends ZoanAbility {
  public static final MammothGuardPointAbility INSTANCE = new MammothGuardPointAbility();
  
  private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d141ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Mammoth Guard Point Modifier", -0.30000001192092896D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
  private static final AbilityAttributeModifier HEALTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d141ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Mammoth Guard Point Modifier", 25.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Mammoth Guard Point Modifier", 18.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Mammoth Guard Point Modifier", 6.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Mammoth Guard Point Modifier", -0.15000000596046448D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Mammoth Guard Point Reach Modifier", 1.8D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Mammoth Guard Point Knockback Resistance Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STEP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Mammoth Guard Point Step Height Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("8521a343-3d02-4d64-bbd3-a7e272fd8b74"), (Ability)INSTANCE, "Mammoth Guard Fall Resistance Modifier", 10.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public MammothGuardPointAbility() {
    super("Mammoth Guard Point", AbilityHelper.getDevilFruitCategory());
    setDescription("Transforms the user into an ancient mammoth, which focuses on defense");
    addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.MAX_HEALTH, (AttributeModifier)HEALTH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
    addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, (AttributeModifier)KNOCKBACK_RESISTANCE);
    addZoanModifier(ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT);
    addZoanModifier(ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_RESISTANCE_MODIFIER);
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)MammothGuardZoanInfo.INSTANCE;
  }
}


