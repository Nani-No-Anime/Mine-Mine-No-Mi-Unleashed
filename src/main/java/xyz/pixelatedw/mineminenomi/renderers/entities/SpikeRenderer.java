package xyz.pixelatedw.mineminenomi.renderers.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.SpikeEntity;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;

public class SpikeRenderer<T extends SpikeEntity>
  extends EntityRenderer<T> {
  private CubeModel spike = new CubeModel();

  
  protected SpikeRenderer(EntityRendererManager renderManager) {
    super(renderManager);
    this.shadowSize = 0.05F;
  }


  
  public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    matrixStack.push();
    
    matrixStack.translate(0.0D, 0.02D, 0.0D);
    
    float scale = 0.2F;
    matrixStack.scale(scale, scale, scale);
    
    matrixStack.rotate(new Quaternion(Vector3f.XP, 45.0F, true));
    matrixStack.rotate(new Quaternion(Vector3f.YP, 45.0F, true));
    
    RenderType type = ModRenderTypes.TRANSPARENT_COLOR;
    IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
    this.spike.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 0.1F, 0.1F, 0.1F, 1.0F);
    
    matrixStack.pop();
  }


  
  public ResourceLocation getEntityTexture(T entity) {
    return null;
  }

  
  public static class Factory
    implements IRenderFactory
  {
    public EntityRenderer createRenderFor(EntityRendererManager manager) {
      return new SpikeRenderer<>(manager);
    }
  }
}


