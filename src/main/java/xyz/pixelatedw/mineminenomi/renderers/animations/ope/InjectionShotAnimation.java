package xyz.pixelatedw.mineminenomi.renderers.animations.ope;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import xyz.pixelatedw.mineminenomi.api.animations.IHeldItemAnimation;

public class InjectionShotAnimation
  implements IHeldItemAnimation<BipedModel> {
  public static final InjectionShotAnimation INSTANCE = new InjectionShotAnimation();


  
  public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    model.bipedRightArm.rotationPointZ += 3.0F;
    model.bipedRightArm.rotateAngleX = (float)(model.bipedRightArm.rotateAngleX + Math.toRadians(-70.0D));
    model.bipedRightArm.rotateAngleY = 0.0F;
    
    model.bipedLeftArm.rotateAngleX = (float)(model.bipedLeftArm.rotateAngleX + Math.toRadians(-90.0D));
    model.bipedLeftArm.rotateAngleY = (float)(model.bipedLeftArm.rotateAngleY + Math.toRadians(60.0D));
  }


  
  public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}

  
  public void setupHeldItem(LivingEntity entity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int packedLight) {
    matrixStack.translate(0.0D, -0.2D, -0.05D);
    matrixStack.rotate(Vector3f.XP.rotationDegrees(-75.0F));
  }
}


