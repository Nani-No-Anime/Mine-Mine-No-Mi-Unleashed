package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.UUID;








public class AbilityAttributeModifier
  extends AttributeModifier
{
  private Ability ability;
  
  public AbilityAttributeModifier(UUID uuid, Ability ability, String name, double amount, AttributeModifier.Operation operation) {
    super(uuid, name, amount, operation);
    this.ability = ability;
  }

  
  public Ability getAbility() {
    return this.ability;
  }



  
  public AbilityAttributeModifier setSaved(boolean saved) {
    return (AbilityAttributeModifier)super.setSaved(saved);
  }
}


