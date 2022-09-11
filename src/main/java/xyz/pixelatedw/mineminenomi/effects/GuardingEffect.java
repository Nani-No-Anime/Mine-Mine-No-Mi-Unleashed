package xyz.pixelatedw.mineminenomi.effects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import net.minecraft.block.Block;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class GuardingEffect
  extends OverlayEffect
{
  public boolean useOnlySources = false;
  public boolean reduceSpeedAfterHit = false;
  public boolean blockSwings = false;
  public ArrayList<String> acceptableSources = new ArrayList<>(Collections.emptyList());

  
  public GuardingEffect(boolean blockSwings) {
    super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
    this.blockSwings = blockSwings;
  }

  
  public GuardingEffect(boolean useOnlySources, boolean makeUserSlow, boolean blockSwings, String... s) {
    super(EffectType.NEUTRAL, WyHelper.hexToRGB("#000000").getRGB());
    
    this.useOnlySources = useOnlySources;
    this.reduceSpeedAfterHit = makeUserSlow;
    this.blockSwings = blockSwings;
    this.acceptableSources.addAll(Arrays.asList(s));
  }


  
  public boolean shouldRender(EffectInstance effect) {
    return false;
  }


  
  public boolean shouldRenderHUD(EffectInstance effect) {
    return false;
  }


  
  public float[] getOverlayColor() {
    return null;
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


  
  public boolean isRemoveable() {
    return false;
  }


  
  public boolean isBlockingSwings() {
    return this.blockSwings;
  }
}


