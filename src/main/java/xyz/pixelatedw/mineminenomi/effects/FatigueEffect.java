package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class FatigueEffect
  extends Effect
{
  public FatigueEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
  }
}


