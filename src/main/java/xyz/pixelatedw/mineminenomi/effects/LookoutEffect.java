package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.block.Block;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class LookoutEffect
  extends OverlayEffect
{
  public LookoutEffect() {
    super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public float[] getOverlayColor() {
    return new float[] { 0.0F, 0.0F, 0.0F, 0.0F };
  }


  
  public boolean hasBodyOverlayColor() {
    return false;
  }


  
  public Block getBlockOverlay() {
    return null;
  }


  
  public boolean isBlockingRotations() {
    return false;
  }


  
  public ResourceLocation getResourceLocation(int duration) {
    return null;
  }
}


