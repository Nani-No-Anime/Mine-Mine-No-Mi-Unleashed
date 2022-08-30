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


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\client\WorldRendererMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */