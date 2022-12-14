package xyz.pixelatedw.mineminenomi.abilities.zou;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.util.UUID;

public class ZouHeavyPointAbility extends ZoanAbility {
  public static final ZouHeavyPointAbility INSTANCE = new ZouHeavyPointAbility();
  
  private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d141ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Zou Heavy Point Modifier", -0.10000000149011612D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
  private static final AbilityAttributeModifier HEALTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d141ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Zou Heavy Point Modifier", 10.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Zou Heavy Point Modifier", 12.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Zou Heavy Point Modifier", 7.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Zou Heavy Point Modifier", -0.10000000149011612D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Zou Heavy Point Reach Modifier", 1.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Zou Heavy Point Knockback Resistance Modifier", 0.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public ZouHeavyPointAbility() {
    super("Zou Heavy Point", AbilityHelper.getDevilFruitCategory());
    setDescription("Transforms the user into an elephant hybrid, which focuses on strength");
    addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
    addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.MAX_HEALTH, (AttributeModifier)HEALTH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, (AttributeModifier)KNOCKBACK_RESISTANCE);
  }


  
  protected boolean onEndContinuityEvent(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    IvoryStompAbility ivoryStompAbility = (IvoryStompAbility)props.getEquippedAbility((Ability)IvoryStompAbility.INSTANCE);
    if (ivoryStompAbility != null && ivoryStompAbility.isContinuous()) {
      ivoryStompAbility.endContinuity(player);
    }
    return super.onEndContinuityEvent(player);
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)ZouHeavyZoanInfo.INSTANCE;
  }
}


