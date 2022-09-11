package xyz.pixelatedw.mineminenomi.abilities.doku;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.abilities.PotionPassiveAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;

public class PoisonToRegenAbility
  extends PotionPassiveAbility {
  public static final PoisonToRegenAbility INSTANCE = new PoisonToRegenAbility();

  
  public PoisonToRegenAbility() {
    super("Poison to Regen", AbilityHelper.getDevilFruitCategory());
    setDescription("Converts powerful poison applied into regeneration");
    hideInGUI(false);
    
    this.checkPotionEvent = this::checkPotionEvent;
  }

  
  private boolean checkPotionEvent(PlayerEntity player, EffectInstance effect) {
    if (effect.getPotion().equals(Effects.POISON)) {
      
      if (!player.isPotionActive(Effects.REGENERATION) && effect.getAmplifier() > 0)
        player.addPotionEffect(new EffectInstance(Effects.REGENERATION, effect.getDuration(), 0)); 
      return false;
    } 
    
    return true;
  }
}


