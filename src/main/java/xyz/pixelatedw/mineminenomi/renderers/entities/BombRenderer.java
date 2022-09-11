package xyz.pixelatedw.mineminenomi.renderers.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.SphereModel;

public class BombRenderer<T extends Entity>
  extends EntityRenderer<T>
{
  private SphereModel sphere = new SphereModel();

  
  protected BombRenderer(EntityRendererManager renderManager) {
    super(renderManager);
    this.shadowSize = 0.4F;
  }


  
  public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    matrixStack.push();
    
    if (((Entity)entity).ticksExisted < 4) {
      matrixStack.translate(0.0D, 1.5D - (((Entity)entity).ticksExisted / 3.0F), 0.0D);
    } else {
      matrixStack.translate(0.0D, 0.4D, 0.0D);
    } 
    float scale = 3.0F;
    matrixStack.scale(scale, scale, scale);
    
    RenderType type = ModRenderTypes.TRANSPARENT_COLOR;
    IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
    this.sphere.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 0.1F, 0.1F, 0.1F, 1.0F);
    
    matrixStack.pop();
  }


  
  public ResourceLocation getEntityTexture(T entity) {
    return null;
  }

  
  public static class Factory
    implements IRenderFactory
  {
    public EntityRenderer createRenderFor(EntityRendererManager manager) {
      return new BombRenderer<>(manager);
    }
  }
}


