package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.EffectType;
import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class BindEffect
  extends ModEffect
{
  public BindEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public boolean isBlockingSwings() {
    return true;
  }


  
  public boolean isRemoveable() {
    return false;
  }
}


