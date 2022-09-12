package xyz.pixelatedw.mineminenomi.renderers.animations;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;

public class HandcuffedAnimation
  implements IAnimation<BipedModel> {
  public static final HandcuffedAnimation INSTANCE = new HandcuffedAnimation();








  
  public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    model.bipedRightArm.rotateAngleX = (float)Math.toRadians(-40.0D);
    model.bipedLeftArm.rotateAngleX = (float)Math.toRadians(-40.0D);
  }
  
  public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
}


