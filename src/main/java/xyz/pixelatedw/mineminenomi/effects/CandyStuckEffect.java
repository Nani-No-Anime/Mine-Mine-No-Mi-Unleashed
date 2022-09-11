package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class CandyStuckEffect
  extends OverlayEffect
{
  public CandyStuckEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#BA55D3").getRGB());
  }


  
  public float[] getOverlayColor() {
    return new float[] { 0.9F, 0.4F, 0.85F, 0.5F };
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


  
  public boolean isReady(int duration, int amplifier) {
    return true;
  }


  
  public void performEffect(LivingEntity entity, int amplifier) {
    entity.attackEntityFrom(DamageSource.DROWN, amplifier);
  }


  
  public boolean shouldRender(EffectInstance effect) {
    return false;
  }


  
  public boolean shouldRenderHUD(EffectInstance effect) {
    return false;
  }


  
  public Block getBlockOverlay() {
    return ModBlocks.CANDY;
  }


  
  public boolean isRemoveable() {
    return false;
  }


  
  public boolean isBlockingSwings() {
    return true;
  }
}


