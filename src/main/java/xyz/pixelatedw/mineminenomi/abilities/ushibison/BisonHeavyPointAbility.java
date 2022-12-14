package xyz.pixelatedw.mineminenomi.abilities.ushibison;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.util.UUID;

public class BisonHeavyPointAbility extends ZoanAbility {
  public static final BisonHeavyPointAbility INSTANCE = new BisonHeavyPointAbility();
  
  private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d141ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Bison Heavy Point Modifier", 0.3D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
  private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Bison Heavy Point Modifier", 6.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Bison Heavy Point Modifier", 4.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Bison Heavy Point Modifier", -0.800000011920929D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier JUMP_BOOST = (new AbilityAttributeModifier(UUID.fromString("72ee5c43-a900-4545-883c-709d84ef1f9c"), (Ability)INSTANCE, "Bison Heavy Point Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Bison Heavy Point Reach Modifier", 0.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public BisonHeavyPointAbility() {
    super("Bison Heavy Point", AbilityHelper.getDevilFruitCategory());
    setDescription("Transforms the user into a half-bison hybrid, which focuses on strength");
    addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
    addZoanModifier(ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_BOOST);
    addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
  }


  
  protected boolean onEndContinuityEvent(PlayerEntity player) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    KokuteiCrossAbility kokuteiCrossAbility = (KokuteiCrossAbility)props.getEquippedAbility((Ability)KokuteiCrossAbility.INSTANCE);
    if (kokuteiCrossAbility != null && kokuteiCrossAbility.isContinuous()) {
      kokuteiCrossAbility.endContinuity(player);
    }
    return super.onEndContinuityEvent(player);
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)BisonHeavyZoanInfo.INSTANCE;
  }
}


