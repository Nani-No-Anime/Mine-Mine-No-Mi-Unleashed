package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class SilentEffect
  extends Effect
{
  public SilentEffect() {
    super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
  }
}


