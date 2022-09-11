package xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.BaraCarPartialModel;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.BaraCarWheelsPartialModel;

public class BaraCarLayer<T extends LivingEntity, M extends EntityModel<T>>
  extends LayerRenderer<T, M>
{
  private final BaraCarPartialModel bodyModel = new BaraCarPartialModel();
  private final BaraCarWheelsPartialModel wheelsModel = new BaraCarWheelsPartialModel();
  private ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/bara_car_wheels.png");

  
  public BaraCarLayer(IEntityRenderer renderer) {
    super(renderer);
  }


  
  public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    ResourceLocation skin = DefaultPlayerSkin.getDefaultSkin(entity.getUniqueID());
    if (entity instanceof PlayerEntity) {
      
      PlayerEntity player = ((LivingEntity)entity).world.getPlayerByUuid(entity.getUniqueID());
      if (player != null) {
        skin = ((AbstractClientPlayerEntity)player).getLocationSkin();
      }
    } 
    RenderType renderType = ModRenderTypes.getZoanRenderType(skin);
    this.bodyModel.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    this.bodyModel.render(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    
    renderType = ModRenderTypes.getZoanRenderType(this.texture);
    this.wheelsModel.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    this.wheelsModel.render(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
  }
}


