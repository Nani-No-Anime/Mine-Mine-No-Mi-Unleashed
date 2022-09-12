package xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;

public class ShinokuniFaceLayer<T extends LivingEntity, M extends EntityModel<T>>
  extends LayerRenderer<T, M> {
  private final PlayerModel playerModel = new PlayerModel(1.0F, false);

  
  public ShinokuniFaceLayer(IEntityRenderer renderer) {
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
    matrixStack.push();
    matrixStack.scale(0.85F, 0.85F, 0.85F);
    matrixStack.translate(0.0D, -0.95D, -0.35D);

    
    headPitch = MathHelper.clamp(headPitch, -17.0F, 60.0F);
    netHeadYaw = MathHelper.clamp(netHeadYaw, -27.0F, 27.0F);
    
    RenderType renderType = ModRenderTypes.getZoanRenderType(skin);
    this.playerModel.setVisible(false);
    this.playerModel.bipedHead.showModel = true;
    this.playerModel.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    this.playerModel.render(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    matrixStack.pop();
  }
}


