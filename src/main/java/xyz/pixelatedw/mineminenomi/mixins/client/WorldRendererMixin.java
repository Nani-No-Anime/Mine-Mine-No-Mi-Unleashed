package xyz.pixelatedw.mineminenomi.mixins.client;

import net.minecraft.client.renderer.RenderTypeBuffers;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({WorldRenderer.class})
public interface WorldRendererMixin {
  @Accessor("ViewFrustum")
  void setViewFrustum(ViewFrustum paramViewFrustum);
  
  @Accessor("ViewFrustum")
  ViewFrustum getViewFrustum();
  
  @Accessor("renderTypeTextures")
  RenderTypeBuffers getRenderTypeTextures();
}


