package xyz.pixelatedw.mineminenomi.mixins;

import net.minecraft.potion.EffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({EffectInstance.class})
public interface EffectInstanceMixin {
  @Accessor("duration")
  void setDuration(int paramInt);
}


