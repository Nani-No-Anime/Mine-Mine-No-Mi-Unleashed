package xyz.pixelatedw.mineminenomi.mixins;

import net.minecraft.potion.EffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({EffectInstance.class})
public interface EffectInstanceMixin {
  @Accessor("duration")
  void setDuration(int paramInt);
}


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\EffectInstanceMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */