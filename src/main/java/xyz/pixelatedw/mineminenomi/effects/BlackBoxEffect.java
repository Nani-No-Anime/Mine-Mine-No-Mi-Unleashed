package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.block.Block;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class BlackBoxEffect
  extends OverlayEffect
{
  public BlackBoxEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public float[] getOverlayColor() {
    return new float[] { 0.0F, 0.0F, 0.0F, 0.8F };
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


  
  public boolean shouldRender(EffectInstance effect) {
    return false;
  }


  
  public boolean shouldRenderHUD(EffectInstance effect) {
    return false;
  }


  
  public Block getBlockOverlay() {
    return ModBlocks.DARKNESS;
  }


  
  public boolean isRemoveable() {
    return false;
  }
}


