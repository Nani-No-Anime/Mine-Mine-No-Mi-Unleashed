package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.AttackDamageEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.mineminenomi.api.effects.IBindHandsEffect;
import xyz.pixelatedw.mineminenomi.api.effects.IIgnoreMilkEffect;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class HandcuffedEffect extends AttackDamageEffect implements IIgnoreMilkEffect, IBindHandsEffect {
  private boolean isKairosekiCuffs;
  
  public HandcuffedEffect(boolean isKairosekiCuffs) {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB(), -20.0D);
    this.isKairosekiCuffs = isKairosekiCuffs;
  }


  
  public boolean isReady(int duration, int amplifier) {
    return true;
  }


  
  public void performEffect(LivingEntity entity, int amplifier) {
    EffectInstance instance = entity.getActivePotionEffect((Effect)this);
    
    if (instance.getDuration() <= 1) {
      
      entity.removePotionEffect((Effect)this);
      if (entity instanceof PlayerEntity) {
        
        PlayerEntity player = (PlayerEntity)entity;
        AbilityHelper.enableAbilities(player, ability -> (ability != null && ability.getCategory() == AbilityHelper.getDevilFruitCategory()));
      } 
    } 
    
    if (this.isKairosekiCuffs && entity instanceof PlayerEntity) {
      
      PlayerEntity player = (PlayerEntity)entity;
      AbilityHelper.disableAbilities(player, instance.getDuration(), ability -> (ability != null && ability.getCategory() == AbilityHelper.getDevilFruitCategory()));
    } 
  }


  
  public boolean isRemoveable() {
    return false;
  }


  
  public boolean isBlockingSwings() {
    return true;
  }
}


