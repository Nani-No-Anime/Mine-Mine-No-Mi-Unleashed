package xyz.pixelatedw.mineminenomi.renderers.animations.blackleg;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.renderers.animations.BodyRotateAnimation;

public class PartyTableKickCourseAnimation
  extends BodyRotateAnimation {
  public static final PartyTableKickCourseAnimation INSTANCE = new PartyTableKickCourseAnimation();

  
  public PartyTableKickCourseAnimation() {
    super(13.0F);
  }


  
  public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    model.bipedLeftArm.rotateAngleX = (float)Math.toRadians(180.0D);
    model.bipedLeftArm.rotateAngleZ = (float)Math.toRadians(10.0D);
    model.bipedRightArm.rotateAngleX = (float)Math.toRadians(180.0D);
    model.bipedRightArm.rotateAngleZ = (float)Math.toRadians(-10.0D);
    model.bipedRightLeg.rotateAngleZ = (float)Math.toRadians(90.0D);
    model.bipedLeftLeg.rotateAngleZ = (float)Math.toRadians(-90.0D);
  }


  
  public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
    matrixStack.translate(0.0D, 1.0D, 0.0D);
    matrixStack.rotate(Vector3f.XP.rotationDegrees(180.0F));
    super.setupAnimation(player, matrixStack, buffer, packedLight, rotationYaw, partialTicks);
  }
}


