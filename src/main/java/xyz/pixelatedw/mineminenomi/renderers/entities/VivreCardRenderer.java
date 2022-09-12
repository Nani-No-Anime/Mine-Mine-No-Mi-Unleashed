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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.VivreCardEntity;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;

@OnlyIn(Dist.CLIENT)
public class VivreCardRenderer<T extends VivreCardEntity>
  extends EntityRenderer<T> {
  private CubeModel model = new CubeModel();

  
  protected VivreCardRenderer(EntityRendererManager manager) {
    super(manager);
  }


  
  public ResourceLocation getEntityTexture(VivreCardEntity entity) {
    return null;
  }


  
  public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    matrixStack.push();
    
    matrixStack.translate(0.0D, 0.05D, 0.0D);
    matrixStack.rotate(new Quaternion(Vector3f.YP, -((VivreCardEntity)entity).rotationYaw, true));
    
    matrixStack.scale(0.4F, 0.1F, 0.5F);
    
    RenderType type = ModRenderTypes.TRANSPARENT_COLOR;
    IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
    this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    
    matrixStack.pop();
    super.render((T)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
  }





  
  public static class Factory
    implements IRenderFactory<VivreCardEntity>
  {
    public EntityRenderer<? super VivreCardEntity> createRenderFor(EntityRendererManager manager) {
      return new VivreCardRenderer<>(manager);
    }
  }
}


