package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.EffectType;
import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class CandleLockEffect
  extends ModEffect
{
  public CandleLockEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public boolean isRemoveable() {
    return false;
  }
}


