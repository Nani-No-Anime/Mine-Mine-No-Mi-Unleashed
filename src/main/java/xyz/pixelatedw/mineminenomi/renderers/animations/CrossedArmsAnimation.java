package xyz.pixelatedw.mineminenomi.renderers.animations;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;

public class CrossedArmsAnimation
  implements IAnimation<BipedModel> {
  public static final CrossedArmsAnimation INSTANCE = new CrossedArmsAnimation();


  
  public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    model.bipedRightArm.rotateAngleX = model.bipedRightArm.rotateAngleX * 0.5F - 3.1415927F + 1.8F;
    model.bipedRightArm.rotateAngleY = -1.2F;
    model.bipedRightArm.rotateAngleZ = -0.8F;
    
    model.bipedLeftArm.rotateAngleX = model.bipedLeftArm.rotateAngleX * 0.5F - 3.1415927F + 1.8F;
    model.bipedLeftArm.rotateAngleY = 1.2F;
    model.bipedLeftArm.rotateAngleZ = 0.8F;
  }
  
  public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
}


