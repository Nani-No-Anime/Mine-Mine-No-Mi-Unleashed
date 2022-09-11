package xyz.pixelatedw.mineminenomi.abilities.ushigiraffe;

import java.lang.invoke.SerializedLambda;
import java.util.UUID;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class GiraffeWalkPointAbility extends ZoanAbility {
  public static final GiraffeWalkPointAbility INSTANCE = new GiraffeWalkPointAbility();
  
  private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d171ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Giraffe Walk Point Speed Modifier", 0.800000011920929D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
  private static final AbilityAttributeModifier JUMP_BOOST = (new AbilityAttributeModifier(UUID.fromString("13b3d607-ed90-4459-832c-01e616a5ef16"), (Ability)INSTANCE, "Giraffe Walk Point Jump Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Giraffe Walk Point Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STEP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("1d68a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Giraffe Walk Point Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Giraffe Walk Point Modifier", 0.15000000596046448D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Giraffe Walk Point Reach Modifier", 1.1D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("8521a343-3d02-4d64-bbd3-a7e272fd8b74"), (Ability)INSTANCE, "Giraffe Walk Point Fall Resistance Modifier", 5.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Giraffe Walk Point Modifier", 3.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public GiraffeWalkPointAbility() {
    super("Giraffe Walk Point", AbilityHelper.getDevilFruitCategory());
    setDescription("Transforms the user into a giraffe, which focuses on speed and high jumps");
    addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
    addZoanModifier(ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
    addZoanModifier(ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_BOOST);
    addZoanModifier(ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT);
    addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(ModAttributes.FALL_RESISTANCE, (AttributeModifier)FALL_RESISTANCE_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
    
    this.duringContinuityEvent = this::duringContinuityEvent;
  }


  
  public void duringContinuityEvent(PlayerEntity player, int activeTime) {
    super.duringContinuityEvent(player, activeTime);
    player.fallDistance = (float)(player.fallDistance - 0.05D);
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)GiraffeWalkZoanInfo.INSTANCE;
  }
}


