package xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
import xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial.DamnedPunkLayer;
import xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial.PunkGibsonLayer;

@OnlyIn(Dist.CLIENT)
public class PunkGibsonPartialMorphRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel> extends ZoanMorphRenderer<T, M> {
  public PunkGibsonPartialMorphRenderer(EntityRendererManager renderManager, ZoanInfo info, boolean isDamnedPunk) {
    super(renderManager, info);
    removeLayer(HeldItemLayer.class);
    addLayer((LayerRenderer)new AuraLayer((IEntityRenderer)this));
    addLayer((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
    addLayer((LayerRenderer)new CapeLayer((IEntityRenderer)this));
    addLayer((LayerRenderer)new ElytraLayer((IEntityRenderer)this));
    if (isDamnedPunk) {
      addLayer((LayerRenderer)new DamnedPunkLayer((IEntityRenderer)this));
    } else {
      addLayer((LayerRenderer)new PunkGibsonLayer((IEntityRenderer)this));
    } 
  }

  
  public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    ((PlayerModel)getEntityModel()).bipedRightArm.showModel = false;
    ((PlayerModel)getEntityModel()).bipedRightArmwear.showModel = false;
    
    super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
  }
  
  public static class Factory<T extends PlayerEntity>
    implements IRenderFactory<T>
  {
    private ZoanInfo info;
    private boolean isDamnedPunk;
    
    public Factory(ZoanInfo info, boolean isDamnedPunk) {
      this.info = info;
      this.isDamnedPunk = isDamnedPunk;
    }


    
    public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer)new PunkGibsonPartialMorphRenderer<>(manager, this.info, this.isDamnedPunk);
    }
  }
}


