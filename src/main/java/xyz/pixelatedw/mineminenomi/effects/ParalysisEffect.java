package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class ParalysisEffect
  extends ModEffect
{
  public ParalysisEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public boolean shouldRender(EffectInstance effect) {
    return false;
  }


  
  public boolean isReady(int duration, int amplifier) {
    return true;
  }


  
  public void performEffect(LivingEntity entity, int amplifier) {
    if (amplifier > 0) {
      entity.setMotion(0.0D, 0.0D, 0.0D);
    }
  }

  
  public boolean shouldRenderHUD(EffectInstance effect) {
    return false;
  }


  
  public boolean isRemoveable() {
    return false;
  }


  
  public boolean isBlockingSwings() {
    return true;
  }
}


