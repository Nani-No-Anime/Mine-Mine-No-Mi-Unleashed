package xyz.pixelatedw.mineminenomi.mixins.client;

import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.FluidBlockRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({BlockRendererDispatcher.class})
public interface BlockRendererDispatcherMixin {
  @Accessor("fluidRenderer")
  void setfluidRenderer(FluidBlockRenderer paramFluidBlockRenderer);
  
  @Accessor("fluidRenderer")
  FluidBlockRenderer getfluidRenderer();
}


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\client\BlockRendererDispatcherMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */