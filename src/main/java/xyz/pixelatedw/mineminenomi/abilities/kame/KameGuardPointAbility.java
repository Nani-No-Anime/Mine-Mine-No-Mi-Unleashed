package xyz.pixelatedw.mineminenomi.abilities.kame;

import java.lang.invoke.SerializedLambda;
import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.KameGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class KameGuardPointAbility extends ZoanAbility {
  public static final KameGuardPointAbility INSTANCE = new KameGuardPointAbility();
  
  private static final AbilityAttributeModifier HEALTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d141ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Kame Guard Point Modifier", 10.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Kame Guard Point Modifier", 25.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Kame Guard Point Modifier", -0.15000000596046448D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Kame Guard Point Knockback Resistance Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier JUMP_BOOST = (new AbilityAttributeModifier(UUID.fromString("13b3d607-ed90-4459-832c-01e616a5ef16"), (Ability)INSTANCE, "Kame Guard Point Jump Modifier", -10.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d141ef28-e77a-418d-927b-ca9a09417189"), (Ability)INSTANCE, "Kame Guard Point Modifier", -0.85D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
  private static final AbilityAttributeModifier WATER_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("b8d182b5-95e8-4efb-a4a9-6711ffbcf0e6"), (Ability)INSTANCE, "Kame Guard Point Water Speed Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public KameGuardPointAbility() {
    super("Kame Guard Point", AbilityHelper.getDevilFruitCategory());
    setDescription("Transforms the user into a turtle, which focuses on defense \n\nSneaking allows you to retract into your shell");
    addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.MAX_HEALTH, (AttributeModifier)HEALTH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, (AttributeModifier)KNOCKBACK_RESISTANCE);
    addZoanModifier(ModAttributes.JUMP_HEIGHT, (AttributeModifier)JUMP_BOOST);
    addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
    addZoanModifier(LivingEntity.SWIM_SPEED, (AttributeModifier)WATER_SPEED_MODIFIER);
    
    this.duringContinuityEvent = this::duringContinuityEvent;
  }

  
  public void duringContinuityEvent(PlayerEntity player, int i) {
    super.duringContinuityEvent(player, i);
    if (player.isSneaking()) {
      player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, 3, false, false));
    }
  }

  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)KameGuardZoanInfo.INSTANCE;
  }
}


