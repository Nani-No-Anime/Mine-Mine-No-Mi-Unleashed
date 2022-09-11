package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.block.Block;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class LovestruckEffect
  extends OverlayEffect
{
  public LovestruckEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public float[] getOverlayColor() {
    return new float[] { 0.9F, 0.8F, 0.9F, 0.8F };
  }


  
  public boolean hasBodyOverlayColor() {
    return true;
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


