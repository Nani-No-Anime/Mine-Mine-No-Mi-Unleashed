package xyz.pixelatedw.mineminenomi.abilities.mogu;


import java.util.UUID;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.MoguHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class MoguHeavyPointAbility extends ZoanAbility {
  public static final MoguHeavyPointAbility INSTANCE = new MoguHeavyPointAbility();
  
  private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Mogu Heavy Point Modifier", 5.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Mogu Heavy Point Modifier", 3.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ATTACK_SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), (Ability)INSTANCE, "Mogu Heavy Point Modifier", 0.15000000596046448D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Mogu Heavy Reach Modifier", -0.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public MoguHeavyPointAbility() {
    super("Mogu Heavy Point", AbilityHelper.getDevilFruitCategory());
    setDescription("Transforms the user into a mole, which focuses on strength and digging speed");
    addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)ARMOR_MODIFIER);
    addZoanModifier(ModAttributes.PUNCH_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
    addZoanModifier(SharedMonsterAttributes.ATTACK_SPEED, (AttributeModifier)ATTACK_SPEED_MODIFIER);
    addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
    addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
    
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }


  
  public void duringContinuityEvent(PlayerEntity player, int time) {
    super.duringContinuityEvent(player, time);
    player.addPotionEffect(new EffectInstance(Effects.HASTE, 5, 2, false, false));
    if (!player.isPotionActive(Effects.NIGHT_VISION) || player.getActivePotionEffect(Effects.NIGHT_VISION).getDuration() < 500) {
      player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 500, 0, false, false));
    }
  }

  
  protected boolean onEndContinuityEvent(PlayerEntity player) {
    player.removePotionEffect(Effects.HASTE);
    player.removePotionEffect(Effects.NIGHT_VISION);
    return super.onEndContinuityEvent(player);
  }


  
  public ZoanInfo getTransformation() {
    return (ZoanInfo)MoguHeavyZoanInfo.INSTANCE;
  }
}


