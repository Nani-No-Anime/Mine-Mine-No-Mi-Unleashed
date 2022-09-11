package xyz.pixelatedw.mineminenomi.mixins;

import net.minecraft.command.impl.FillCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;








@Mixin({FillCommand.class})
public class FillCommandMixin
{
  @ModifyVariable(method = {"doFill"}, at = @At("STORE"), ordinal = 0)
  private static int areaSize(int x) {
    return 0;
  }
}


