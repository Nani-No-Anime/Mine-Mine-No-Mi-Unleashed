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
import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
import xyz.pixelatedw.mineminenomi.models.blocks.WantedPosterPackageModel;

public class WantedPosterPackageRenderer<T extends WantedPosterPackageEntity>
  extends EntityRenderer<T>
{
  private ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/models/wantedposterspackage.png");
  private WantedPosterPackageModel model = new WantedPosterPackageModel();

  
  protected WantedPosterPackageRenderer(EntityRendererManager manager) {
    super(manager);
  }


  
  public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    matrixStack.push();
    
    matrixStack.translate(0.0D, 1.5D, 0.0D);
    matrixStack.rotate(new Quaternion(Vector3f.XP, 180.0F, true));
    matrixStack.rotate(new Quaternion(Vector3f.YP, 180.0F, true));
    matrixStack.rotate(new Quaternion(Vector3f.YP, ((WantedPosterPackageEntity)entity).prevRotationYaw + (((WantedPosterPackageEntity)entity).rotationYaw - ((WantedPosterPackageEntity)entity).prevRotationYaw) * partialTicks - 180.0F, true));
    
    matrixStack.scale(1.5F, 1.0F, 1.5F);
    
    RenderType type = RenderType.getEntityCutoutNoCull(getEntityTexture((WantedPosterPackageEntity)entity));
    IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
    this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    
    matrixStack.pop();
  }


  
  public ResourceLocation getEntityTexture(WantedPosterPackageEntity entity) {
    return this.texture;
  }



  
  public static class Factory
    implements IRenderFactory<WantedPosterPackageEntity>
  {
    public EntityRenderer<? super WantedPosterPackageEntity> createRenderFor(EntityRendererManager manager) {
      return new WantedPosterPackageRenderer<>(manager);
    }
  }
}


