package xyz.pixelatedw.mineminenomi.api.effects;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;

public abstract class OverlayEffect
  extends ModEffect
{
  protected OverlayEffect(EffectType typeIn, int liquidColorIn) {
    super(typeIn, liquidColorIn);
  }

  
  public abstract float[] getOverlayColor();
  
  public abstract boolean hasBodyOverlayColor();
  
  public abstract Block getBlockOverlay();
  
  public abstract ResourceLocation getResourceLocation(int paramInt);
  
  @OnlyIn(Dist.CLIENT)
  public RenderType getRenderType() {
    return ModRenderTypes.TRANSPARENT_COLOR;
  }
}


