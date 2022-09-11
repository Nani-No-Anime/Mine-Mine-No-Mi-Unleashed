package xyz.pixelatedw.mineminenomi.renderers.animations;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;

public class SpecialFlyAnimation
  implements IAnimation<BipedModel> {
  public static final SpecialFlyAnimation INSTANCE = new SpecialFlyAnimation();


  
  public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    double posXDiff = Math.abs(player.prevPosX - player.getPosX());
    double posZDiff = Math.abs(player.prevPosZ - player.getPosZ());
    
    float angles = 0.0F;

    
    if (posXDiff >= 0.2D || posZDiff >= 0.2D)
      angles = (float)Math.toRadians(45.0D); 
    if (posXDiff >= 0.5D || posZDiff >= 0.5D) {
      angles = (float)Math.toRadians(60.0D);
    }
    model.bipedBody.rotateAngleX = angles;
    
    if (player.swingProgress <= 0.0F) {
      
      model.bipedRightArm.rotateAngleX = angles;
      model.bipedLeftArm.rotateAngleX = angles;
    } 
    
    model.bipedRightLeg.showModel = false;
    model.bipedLeftLeg.showModel = false;
  }


  
  public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
    double posXDiff = Math.abs(player.prevPosX - player.getPosX());
    double posZDiff = Math.abs(player.prevPosZ - player.getPosZ());
    
    double drop = 0.0D;
    
    if (posXDiff >= 0.2D || posZDiff >= 0.2D)
      drop = 0.3D; 
    if (posXDiff >= 0.5D || posZDiff >= 0.5D) {
      drop = 0.4D;
    }
    matrixStack.translate(0.0D, drop, 0.0D);
  }
}


