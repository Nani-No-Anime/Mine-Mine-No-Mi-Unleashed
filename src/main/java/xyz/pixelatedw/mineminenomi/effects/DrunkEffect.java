package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class DrunkEffect extends ModEffect {
  private double oldHealth = 0.0D;
  private int storedDamage = 0;

  
  public DrunkEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public boolean isReady(int duration, int amplifier) {
    return true;
  }


  
  public void performEffect(LivingEntity entity, int amplifier) {
    if (this.oldHealth == 0.0D) {
      this.oldHealth = entity.getHealth();
    }
    if (entity.getHealth() < this.oldHealth) {
      
      double damage = this.oldHealth - entity.getHealth();
      this.storedDamage = (int)(this.storedDamage + damage);
      entity.heal((float)damage);
      this.oldHealth = entity.getHealth();
    } 
    
    if (amplifier >= 2 && amplifier < 4) {
      entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 0));
    } else if (amplifier >= 4) {
      entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 2));
    } 
    EffectInstance effect = entity.getActivePotionEffect((Effect)this);
    
    if (effect.getDuration() < 2 || this.storedDamage > 100 + Math.min(amplifier, 5) * 10) {
      
      entity.attackEntityFrom(DamageSource.MAGIC, this.storedDamage);
      this.storedDamage = 0;
      this.oldHealth = 0.0D;
      entity.removePotionEffect((Effect)this);
    } 
  }

  
  public boolean isRemoveable() {
    return false;
  }
}


