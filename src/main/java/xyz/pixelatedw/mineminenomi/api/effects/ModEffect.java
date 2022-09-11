package xyz.pixelatedw.mineminenomi.api.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class ModEffect
  extends Effect
  implements IIgnoreMilkEffect, IBindHandsEffect {
  public ModEffect(EffectType typeIn, int liquidColorIn) {
    super(typeIn, liquidColorIn);
  }


  
  public boolean isBlockingRotations() {
    return false;
  }


  
  public boolean isBlockingSwings() {
    return false;
  }


  
  public boolean isRemoveable() {
    return true;
  }
}


