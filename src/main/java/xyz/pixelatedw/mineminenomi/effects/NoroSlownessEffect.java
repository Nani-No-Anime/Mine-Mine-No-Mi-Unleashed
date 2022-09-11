package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class NoroSlownessEffect
  extends Effect
{
  public NoroSlownessEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
  }
}


