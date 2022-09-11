package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class BleedingEffect
  extends Effect
{
  public BleedingEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public boolean isReady(int duration, int amplifier) {
    return (duration % 20 == 0);
  }


  
  public void performEffect(LivingEntity entity, int amplifier) {
    float damage = Math.max(2, 2 * amplifier);
    entity.attackEntityFrom(DamageSource.MAGIC, damage);
  }
}


