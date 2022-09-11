package xyz.pixelatedw.mineminenomi.abilities.ryubrachiosaurus;

import java.util.UUID;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.BrachiosaurusGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class BrachiosaurusGuardPointAbility
  extends ZoanAbility {
  public static final BrachiosaurusGuardPointAbility INSTANCE = new BrachiosaurusGuardPointAbility();
  
  private static final AbilityAttributeModifier HEALTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("00c01b89-bfd0-486b-9e21-5bb993eb8eb2"), (Ability)INSTANCE, "Brachiosaurus Guard Point Health Modifier", 30.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d141ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Brachiosaurus Guard Point Speed Modifier", -0.1D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
  private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Brachiosaurus Guard Point Armor Modifier", 15.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Brachiosaurus Guard Point Strength Modifier", 4.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Brachiosaurus Guard Point Attack Speed Modifier", -0.4000000059604645D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Brachiosaurus Guard Point Reach Modifier", 5.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STEP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("f5212f9a-5d23-41da-b355-13ed81e4336c"), (Ability)INSTANCE, "Brachiosaurus Guard Point Step Height Modifier", 1.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Brachiosaurus Guard Point Knockback Resistance Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("8521a343-3d02-4d64-bbd3-a7e272fd8b74"), (Ability)INSTANCE, "Brachiosaurus Guard Fall Resistance Modifier", 10.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public BrachiosaurusGuardPointAbility() {
    super("Brachiosaurus Guard Point", AbilityHelper.getDevilFruitCategory());
    setDescription("Transforms the user into an ancient brachiosaurus, which focuses on defense.");
    addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.MAX_HEALTH, (AttributeModifier)HEALTH_MODIFIER);
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
    return (ZoanInfo)BrachiosaurusGuardZoanInfo.INSTANCE;
  }
}


