package xyz.pixelatedw.mineminenomi.renderers.animations;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;

public class RaiseBothArmAnimation
  implements IAnimation<BipedModel> {
  public static final RaiseBothArmAnimation INSTANCE = new RaiseBothArmAnimation();


  
  public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    model.bipedRightArm.rotateAngleX = (float)Math.toRadians(180.0D);
    model.bipedRightArm.rotateAngleY = 0.0F;
    
    model.bipedLeftArm.rotateAngleX = (float)Math.toRadians(180.0D);
    model.bipedLeftArm.rotateAngleY = 0.0F;
  }
  
  public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
}


