package xyz.pixelatedw.mineminenomi.particles;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;


public class CustomParticleRenderType
  implements IParticleRenderType
{
  private ResourceLocation texture;
  
  public CustomParticleRenderType(ResourceLocation texture) {
    this.texture = texture;
  }


  
  public void beginRender(BufferBuilder buffer, TextureManager textureManager) {
    RenderSystem.enableBlend();
    RenderSystem.defaultBlendFunc();
    RenderSystem.depthMask(false);
    textureManager.bindTexture(this.texture);
    buffer.begin(7, ModRenderTypes.PARTICLE_POSITION_TEX_COLOR_LMAP);
  }


  
  public void finishRender(Tessellator tess) {
    tess.draw();
  }
}


