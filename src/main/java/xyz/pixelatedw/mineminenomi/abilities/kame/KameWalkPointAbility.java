package xyz.pixelatedw.mineminenomi.abilities.kame;

import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.KameWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class KameWalkPointAbility
  extends ZoanAbility {
  public static final KameWalkPointAbility INSTANCE = new KameWalkPointAbility();
  
  private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d141ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Kame Walk Point Modifier", -0.4000000059604645D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
  private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Kame Walk Point Modifier", 15.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Kame Walk Point Knockback Resistance Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier JUMP_BOOST = (new AbilityAttributeModifier(UUID.fromString("13b3d607-ed90-4459-832c-01e616a5ef16"), (Ability)INSTANCE, "Kame Walk Point Jump Modifier", -0.25D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier WATER_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("b8d182b5-95e8-4efb-a4a9-6711ffbcf0e6"), (Ability)INSTANCE, "Kame Walk Point Water Speed Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public KameWalkPointAbility() {
    super("Kame Walk Point", AbilityHelper.getDevilFruitCategory());
    setDescription("Transforms the user into a turtle hybrid, which focuses on defense");
    addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, (AttributeModifier)KNOCKBACK_RESISTANCE);
    addZoanModifier(ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_BOOST);
    addZoanModifier(LivingEntity.SWIM_SPEED, (AttributeModifier)WATER_SPEED_MODIFIER);
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)KameWalkZoanInfo.INSTANCE;
  }
}


