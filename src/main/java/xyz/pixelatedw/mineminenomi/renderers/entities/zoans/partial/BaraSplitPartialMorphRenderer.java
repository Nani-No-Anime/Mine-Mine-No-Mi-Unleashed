package xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ZoanMorphRenderer;

public class BaraSplitPartialMorphRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel> extends ZoanMorphRenderer<T, M> {
  public BaraSplitPartialMorphRenderer(EntityRendererManager renderManager, ZoanInfo info, boolean hasSmallHands) {
    super(renderManager, info, hasSmallHands);
    addLayer((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
  }


  
  public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    PlayerModel<AbstractClientPlayerEntity> playermodel = (PlayerModel<AbstractClientPlayerEntity>)getEntityModel();
    playermodel.bipedRightLeg.showModel = false;
    playermodel.bipedLeftLeg.showModel = false;
    super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
  }
  
  public static class Factory<T extends PlayerEntity>
    implements IRenderFactory<T>
  {
    private ZoanInfo info;
    private boolean hasSmallHands;
    
    public Factory(ZoanInfo info, boolean hasSmallHands) {
      this.info = info;
      this.hasSmallHands = hasSmallHands;
    }


    
    public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer)new BaraSplitPartialMorphRenderer<>(manager, this.info, this.hasSmallHands);
    }
  }
}


