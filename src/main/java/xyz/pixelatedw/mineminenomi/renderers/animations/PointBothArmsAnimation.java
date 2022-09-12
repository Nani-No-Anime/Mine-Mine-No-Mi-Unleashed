package xyz.pixelatedw.mineminenomi.renderers.animations;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;

public class PointBothArmsAnimation
  implements IAnimation<BipedModel> {
  public static final PointBothArmsAnimation INSTANCE = new PointBothArmsAnimation();


  
  public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    model.bipedRightArm.rotateAngleX = model.bipedRightArm.rotateAngleX * 0.5F - 3.1415927F + 1.8F;
    model.bipedRightArm.rotateAngleY = -0.2F;
    
    model.bipedLeftArm.rotateAngleX = model.bipedLeftArm.rotateAngleX * 0.5F - 3.1415927F + 1.8F;
    model.bipedLeftArm.rotateAngleY = -0.2F;
  }
  
  public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
}


