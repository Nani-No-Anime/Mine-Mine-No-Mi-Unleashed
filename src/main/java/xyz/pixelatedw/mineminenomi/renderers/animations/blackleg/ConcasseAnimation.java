package xyz.pixelatedw.mineminenomi.renderers.animations.blackleg;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;

public class ConcasseAnimation
  implements IAnimation<BipedModel> {
  public static final ConcasseAnimation INSTANCE = new ConcasseAnimation();


  
  public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    model.bipedLeftLeg.rotateAngleX = (float)Math.toRadians(-90.0D);
    model.bipedRightLeg.rotateAngleX = (float)Math.toRadians(180.0D);
  }


  
  public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
    matrixStack.rotate(Vector3f.XP.rotationDegrees((player.ticksExisted + partialTicks) * 40.0F));
  }
}


