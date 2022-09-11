package xyz.pixelatedw.mineminenomi.abilities.ushigiraffe;

import java.util.UUID;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class GiraffeHeavyPointAbility
  extends ZoanAbility {
  public static final GiraffeHeavyPointAbility INSTANCE = new GiraffeHeavyPointAbility();
  
  private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d171ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Giraffe Heavy Point Speed Modifier", 0.30000001192092896D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
  private static final AbilityAttributeModifier JUMP_BOOST = (new AbilityAttributeModifier(UUID.fromString("13b3d607-ed90-4459-832c-01e616a5ef16"), (Ability)INSTANCE, "Giraffe Heavy Point Jump Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Giraffe Heavy Point Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Giraffe Heavy Point Modifier", 0.15000000596046448D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Giraffe Heavy PointReach Modifier", 0.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Giraffe Heavy Point Modifier", 7.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public GiraffeHeavyPointAbility() {
    super("Giraffe Heavy Point", AbilityHelper.getDevilFruitCategory());
    setDescription("Transforms the user into a half-giraffe hybrid, which focuses on strength, Allows the user to use 'Bigan'");
    addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
    addZoanModifier(ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_BOOST);
    addZoanModifier(ModAttributes.FALL_RESISTANCE, (AttributeModifier)JUMP_BOOST);
    addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)GiraffeHeavyZoanInfo.INSTANCE;
  }
}


