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


