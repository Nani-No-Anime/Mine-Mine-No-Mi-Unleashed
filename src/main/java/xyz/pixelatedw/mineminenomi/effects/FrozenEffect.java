package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class FrozenEffect
  extends OverlayEffect {
  private static final DamageSource DAMAGE_SOURCE = (new DamageSource("frost")).setDamageBypassesArmor().setMagicDamage().setDamageIsAbsolute();

  
  public FrozenEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public boolean isReady(int duration, int amplifier) {
    return (duration % 30 == 0);
  }


  
  public void performEffect(LivingEntity entity, int amplifier) {
    float damage = Math.max(2, 2 * amplifier);
    entity.attackEntityFrom(DAMAGE_SOURCE, damage);
  }


  
  public float[] getOverlayColor() {
    return new float[] { 0.3F, 0.92F, 0.87F, 0.8F };
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
    return Blocks.BLUE_ICE;
  }


  
  public boolean isRemoveable() {
    return false;
  }


  
  public boolean isBlockingSwings() {
    return true;
  }
}


