package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class AbilityOffEffect
  extends ModEffect
{
  public AbilityOffEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public boolean isReady(int duration, int amplifier) {
    return true;
  }


  
  public void performEffect(LivingEntity entity, int amplifier) {
    if (entity instanceof PlayerEntity && amplifier > 0)
    {
      AbilityHelper.disableAbilities((PlayerEntity)entity, 3, ability -> 
          (ability != null && ability.getCategory() == AbilityHelper.getDevilFruitCategory()));
    }
  }


  
  public boolean shouldRender(EffectInstance effect) {
    return false;
  }


  
  public boolean shouldRenderHUD(EffectInstance effect) {
    return false;
  }


  
  public boolean isRemoveable() {
    return false;
  }
}


