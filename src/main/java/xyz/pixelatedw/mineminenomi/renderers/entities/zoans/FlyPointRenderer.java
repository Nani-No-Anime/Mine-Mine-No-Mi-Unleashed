package xyz.pixelatedw.mineminenomi.renderers.entities.zoans;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;


public class FlyPointRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel>
  extends ZoanMorphRenderer<T, M>
{
  public FlyPointRenderer(EntityRendererManager rendererManager, ZoanInfo info) {
    super(rendererManager, info);
    setCullingState(true);
  }


  
  protected void renderModel(AbstractClientPlayerEntity entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    matrixStack.rotate(Vector3f.XP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch)));
    super.renderModel(entity, matrixStack, packedLight, buffer, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
  }
  
  public static class Factory<T extends PlayerEntity>
    implements IRenderFactory<T>
  {
    private ZoanInfo info;
    private boolean hasCulling;
    
    public Factory(ZoanInfo info) {
      this.info = info;
    }

    
    public Factory(ZoanInfo info, boolean hasCulling) {
      this.info = info;
      this.hasCulling = hasCulling;
    }


    
    public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
      FlyPointRenderer<AbstractClientPlayerEntity, ZoanMorphModel> renderer = new FlyPointRenderer<>(manager, this.info);
      renderer.setCullingState(this.hasCulling);
      return (EntityRenderer)renderer;
    }
  }
}


