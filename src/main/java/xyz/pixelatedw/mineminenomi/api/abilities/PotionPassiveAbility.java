package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;

public abstract class PotionPassiveAbility
  extends PassiveAbility
{
  protected ICheckPotionEvent checkPotionEvent = (player, effect) -> true;
  
  public PotionPassiveAbility(String name, APIConfig.AbilityCategory category) {
    super(name, category);
  }


  
  public boolean check(PlayerEntity user, EffectInstance effect) {
    return this.checkPotionEvent.checkPotion(user, effect);
  }
  
  public static interface ICheckPotionEvent {
    boolean checkPotion(PlayerEntity param1PlayerEntity, EffectInstance param1EffectInstance);
  }
}


