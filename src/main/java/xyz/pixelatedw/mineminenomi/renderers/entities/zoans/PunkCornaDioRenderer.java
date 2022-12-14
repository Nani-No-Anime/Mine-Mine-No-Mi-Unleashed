package xyz.pixelatedw.mineminenomi.renderers.entities.zoans;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.entities.zoan.PunkCornaDioBullZoanInfo;
import xyz.pixelatedw.mineminenomi.renderers.layers.BodyCoatingLayer;

@OnlyIn(Dist.CLIENT)
public class PunkCornaDioRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel<T>>
  extends ZoanMorphRenderer<T, M> {
  public PunkCornaDioRenderer(EntityRendererManager renderManager, ZoanInfo info) {
    super(renderManager, info);
    addLayer(new BodyCoatingLayer(this));
    this.cornaDioRenderer = (new ZoanMorphRenderer.Factory<>((ZoanInfo)PunkCornaDioBullZoanInfo.INSTANCE)).createRenderFor(renderManager);
  }
  
  private EntityRenderer<? super PlayerEntity> cornaDioRenderer;
  
  public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    matrixStack.push();
    matrixStack.translate(0.0D, 0.7D, 0.0D);
    super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    matrixStack.pop();
    
    this.cornaDioRenderer.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
  }


  
  public ResourceLocation getEntityTexture(AbstractClientPlayerEntity entity) {
    return entity.getLocationSkin();
  }
  
  public static class Factory<T extends PlayerEntity>
    implements IRenderFactory<T>
  {
    private ZoanInfo info;
    
    public Factory(ZoanInfo info) {
      this.info = info;
    }


    
    public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer)new PunkCornaDioRenderer<>(manager, this.info);
    }
  }
}


