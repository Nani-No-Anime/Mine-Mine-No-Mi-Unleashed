package xyz.pixelatedw.mineminenomi.abilities;


import java.util.UUID;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;

public class NoFallDamageAbility extends PassiveAbility {
  public static final NoFallDamageAbility INSTANCE = new NoFallDamageAbility();
  
  private static final AbilityAttributeModifier FALL_RESISTANCE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("12ee5c43-a900-4545-883c-709d84ef1f9c"), (Ability)INSTANCE, "Jump Resistance Modifier", 10000.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);

  
  public NoFallDamageAbility() {
    this("No Fall Damage", AbilityHelper.getDevilFruitCategory());
    this.duringPassiveEvent = this::duringPassiveEvent;
  }

  
  public NoFallDamageAbility(String name, APIConfig.AbilityCategory category) {
    super(name, category);
    this.duringPassiveEvent = this::duringPassiveEvent;
  }

  
  protected void duringPassiveEvent(PlayerEntity player) {
    if (!player.getAttribute(ModAttributes.FALL_RESISTANCE).hasModifier((AttributeModifier)FALL_RESISTANCE_MODIFIER))
      player.getAttribute(ModAttributes.FALL_RESISTANCE).applyModifier((AttributeModifier)FALL_RESISTANCE_MODIFIER); 
  }
}


