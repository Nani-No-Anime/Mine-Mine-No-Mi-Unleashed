package xyz.pixelatedw.mineminenomi.abilities.kira;

import java.awt.Color;
import java.lang.invoke.SerializedLambda;
import java.util.UUID;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

public class DiamondBodyAbility extends ContinuousAbility implements IParallelContinuousAbility, IBodyOverlayAbility {
  public static final DiamondBodyAbility INSTANCE = new DiamondBodyAbility();
  
  private static final AbilityAttributeModifier ARMOR_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), (Ability)INSTANCE, "Diamond Body Armor Modifier", 24.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ARMOR_THOUGHNESS_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("a647e093-70f1-4369-86c4-7736718ef26c"), (Ability)INSTANCE, "Diamond Body Armor Thoughness Modifier", 12.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityAttributeModifier ATTACK_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("d6ee4b3f-92dd-4b55-9bb5-3c8b1100e438"), (Ability)INSTANCE, "Diamond Body Attack Modifier", 6.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.DIAMOND_BODY).setColor(new Color(255, 255, 255, 165));

  
  public DiamondBodyAbility() {
    super("Diamond Body", AbilityHelper.getDevilFruitCategory());
    setThreshold(120.0D);
    setDescription("Allows the user's body to become diamond, gaining high strength and defence");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuity;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    return true;
  }

  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    player.addPotionEffect(new EffectInstance(ModEffects.PHYSICAL_MOVING_GUARD, 2, 3, false, false));
    
    IAttributeInstance attri = player.getAttribute(SharedMonsterAttributes.ARMOR);
    if (!attri.hasModifier((AttributeModifier)ARMOR_MODIFIER))
      attri.applyModifier((AttributeModifier)ARMOR_MODIFIER); 
    IAttributeInstance attrib = player.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
    if (!attrib.hasModifier((AttributeModifier)ARMOR_THOUGHNESS_MODIFIER))
      attrib.applyModifier((AttributeModifier)ARMOR_THOUGHNESS_MODIFIER); 
    IAttributeInstance attribu = player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    if (!attribu.hasModifier((AttributeModifier)ATTACK_MODIFIER)) {
      attribu.applyModifier((AttributeModifier)ATTACK_MODIFIER);
    }
  }
  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    IAttributeInstance attri = player.getAttribute(SharedMonsterAttributes.ARMOR);
    if (attri.hasModifier((AttributeModifier)ARMOR_MODIFIER))
      attri.removeModifier((AttributeModifier)ARMOR_MODIFIER); 
    IAttributeInstance attrib = player.getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
    if (attrib.hasModifier((AttributeModifier)ARMOR_THOUGHNESS_MODIFIER))
      attrib.removeModifier((AttributeModifier)ARMOR_THOUGHNESS_MODIFIER); 
    IAttributeInstance attribu = player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    if (attribu.hasModifier((AttributeModifier)ATTACK_MODIFIER)) {
      attribu.removeModifier((AttributeModifier)ATTACK_MODIFIER);
    }
    int cooldown = (int)Math.round(this.continueTime / 60.0D);
    setMaxCooldown(cooldown);
    return true;
  }


  
  public AbilityOverlay getBodyOverlay() {
    return OVERLAY;
  }
}


