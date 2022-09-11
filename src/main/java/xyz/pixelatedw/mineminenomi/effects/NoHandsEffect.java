package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class NoHandsEffect
  extends ModEffect
{
  public NoHandsEffect() {
    super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public boolean isReady(int duration, int amplifier) {
    return true;
  }


  
  public boolean shouldRender(EffectInstance effect) {
    return false;
  }


  
  public boolean shouldRenderHUD(EffectInstance effect) {
    return false;
  }


  
  public boolean isBlockingSwings() {
    return true;
  }
}


