package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.block.Block;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class BubblyCoralEffect
  extends OverlayEffect
{
  public BubblyCoralEffect() {
    super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public float[] getOverlayColor() {
    return new float[] { 0.0F, 0.41F, 0.58F, 0.3F };
  }


  
  public boolean hasBodyOverlayColor() {
    return true;
  }


  
  public boolean isBlockingRotations() {
    return false;
  }

  
  public ResourceLocation getResourceLocation(int duration) {
    return null;
  }


  
  public Block getBlockOverlay() {
    return null;
  }


  
  public boolean isRemoveable() {
    return false;
  }
}


