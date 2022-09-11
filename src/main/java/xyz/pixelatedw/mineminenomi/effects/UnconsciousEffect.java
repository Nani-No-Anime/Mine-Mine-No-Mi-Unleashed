package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.block.Block;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class UnconsciousEffect
  extends OverlayEffect
{
  public UnconsciousEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public float[] getOverlayColor() {
    return new float[] { 0.0F, 0.0F, 0.0F, 1.0F };
  }


  
  public boolean hasBodyOverlayColor() {
    return false;
  }


  
  public boolean isBlockingRotations() {
    return true;
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


  
  public boolean isBlockingSwings() {
    return true;
  }
}


