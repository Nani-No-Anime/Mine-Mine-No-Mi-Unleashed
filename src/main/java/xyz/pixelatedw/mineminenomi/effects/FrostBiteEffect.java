package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class FrostBiteEffect
  extends OverlayEffect
{
  public FrostBiteEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public boolean isReady(int duration, int amplifier) {
    return (duration > 200);
  }


  
  public void performEffect(LivingEntity entity, int amplifier) {
    entity.addPotionEffect(new EffectInstance(ModEffects.FROZEN, 80, 0, true, true));
    entity.removePotionEffect(ModEffects.FROSTBITE);
  }


  
  public Block getBlockOverlay() {
    return null;
  }


  
  public float[] getOverlayColor() {
    return new float[] { 1.0F, 1.0F, 1.0F, 1.0F };
  }


  
  public boolean hasBodyOverlayColor() {
    return true;
  }


  
  public boolean isBlockingRotations() {
    return false;
  }


  
  public ResourceLocation getResourceLocation(int value) {
    if (value > 0 && value < 40)
      return ModResources.FROSTBITE_1; 
    if (value > 40 && value < 80)
      return ModResources.FROSTBITE_2; 
    if (value > 80 && value < 120)
      return ModResources.FROSTBITE_3; 
    if (value > 120 && value < 160) {
      return ModResources.FROSTBITE_4;
    }
    return ModResources.FROSTBITE_5;
  }
}


