package xyz.pixelatedw.mineminenomi.api.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class AbilityDamageSource
  extends ModEntityDamageSource
{
  private Ability ability;
  
  public AbilityDamageSource(String damageType, Entity source, Ability ability) {
    super(damageType, source);
    this.ability = ability;
  }

  
  public Ability getAbilitySource() {
    return this.ability;
  }


  
  public ITextComponent getDeathMessage(LivingEntity entityLivingBaseIn) {
    String s = "death.attack." + this.damageType;
    return (ITextComponent)new TranslationTextComponent(s, new Object[] { entityLivingBaseIn.getDisplayName(), this.damageSourceEntity.getDisplayName(), this.ability.getDisplayName() });
  }
}


