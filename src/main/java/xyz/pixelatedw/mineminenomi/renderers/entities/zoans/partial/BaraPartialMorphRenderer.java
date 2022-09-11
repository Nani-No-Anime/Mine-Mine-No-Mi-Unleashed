package xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;

@OnlyIn(Dist.CLIENT)
public class BaraPartialMorphRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel>
  extends ZoanMorphRenderer<T, M> {
  public BaraPartialMorphRenderer(EntityRendererManager renderManager, ZoanInfo info, BaraMode mode) {
    super(renderManager, info);
    removeLayer(HeldItemLayer.class);
    addLayer((LayerRenderer)new AuraLayer((IEntityRenderer)this));
    this.layer = new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F));
    addLayer((LayerRenderer)this.layer);
    addLayer((LayerRenderer)new CapeLayer((IEntityRenderer)this));
    addLayer((LayerRenderer)new ElytraLayer((IEntityRenderer)this));
    this.mode = mode;
  }
  private BaraMode mode;
  private BipedArmorLayer layer;
  
  public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    if (this.mode == BaraMode.FESTIVAL) {
      
      ((PlayerModel)getEntityModel()).bipedLeftArm.showModel = false;
      ((PlayerModel)getEntityModel()).bipedLeftArmwear.showModel = false;
      ((PlayerModel)getEntityModel()).bipedRightArm.showModel = false;
      ((PlayerModel)getEntityModel()).bipedRightArmwear.showModel = false;
      ((PlayerModel)getEntityModel()).bipedLeftLeg.showModel = false;
      ((PlayerModel)getEntityModel()).bipedLeftLegwear.showModel = false;
      ((PlayerModel)getEntityModel()).bipedRightLeg.showModel = false;
      ((PlayerModel)getEntityModel()).bipedRightLegwear.showModel = false;
    }
    else if (this.mode == BaraMode.CIRCUS) {
      
      ((PlayerModel)getEntityModel()).bipedLeftArm.showModel = false;
      ((PlayerModel)getEntityModel()).bipedLeftArmwear.showModel = false;
      ((PlayerModel)getEntityModel()).bipedRightArm.showModel = false;
      ((PlayerModel)getEntityModel()).bipedRightArmwear.showModel = false;
    }
    else if (this.mode == BaraMode.HO) {
      
      ((PlayerModel)getEntityModel()).bipedRightArm.showModel = false;
      ((PlayerModel)getEntityModel()).bipedRightArmwear.showModel = false;
    } 
    super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
  }
  
  public static class Factory<T extends PlayerEntity>
    implements IRenderFactory<T>
  {
    private ZoanInfo info;
    private BaraPartialMorphRenderer.BaraMode mode;
    
    public Factory(ZoanInfo info, BaraPartialMorphRenderer.BaraMode mode) {
      this.info = info;
      this.mode = mode;
    }


    
    public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer)new BaraPartialMorphRenderer<>(manager, this.info, this.mode);
    }
  }
  
  public enum BaraMode
  {
    FESTIVAL,
    CIRCUS,
    HO;
  }
}


