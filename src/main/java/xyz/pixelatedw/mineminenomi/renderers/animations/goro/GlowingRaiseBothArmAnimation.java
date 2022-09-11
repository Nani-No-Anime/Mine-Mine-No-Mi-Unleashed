package xyz.pixelatedw.mineminenomi.renderers.animations.goro;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;

public class GlowingRaiseBothArmAnimation
  implements IAnimation<BipedModel> {
  public static final GlowingRaiseBothArmAnimation INSTANCE = new GlowingRaiseBothArmAnimation();


  
  public void setAnimationAngles(PlayerEntity player, BipedModel entityModel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    if (entityModel instanceof BipedModel) {
      
      BipedModel model = entityModel;
      
      model.bipedRightArm.rotateAngleX = model.bipedRightArm.rotateAngleX * 0.5F - 3.1415927F;
      model.bipedRightArm.rotateAngleY = 0.0F;
      
      model.bipedLeftArm.rotateAngleX = model.bipedLeftArm.rotateAngleX * 0.5F - 3.1415927F;
      model.bipedLeftArm.rotateAngleY = 0.0F;
    } 
  }
  
  public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
}


