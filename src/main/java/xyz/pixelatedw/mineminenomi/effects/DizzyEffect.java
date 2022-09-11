package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class DizzyEffect
  extends Effect
{
  public DizzyEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public boolean shouldRender(EffectInstance effect) {
    return false;
  }


  
  public boolean shouldRenderHUD(EffectInstance effect) {
    return false;
  }
}


