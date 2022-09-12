package xyz.pixelatedw.mineminenomi.renderers.animations.gura;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;

public class KaishinAnimation
  extends TimeAnimation {
  public static final KaishinAnimation INSTANCE = new KaishinAnimation();

  
  private static final double SPEED = 20.0D;

  
  public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    double angle = MathHelper.clamp((80L - getTime()) * 20.0D, -50.0D, 80.0D);
    model.bipedLeftArm.rotateAngleZ = (float)Math.toRadians(angle);
    model.bipedLeftArm.rotateAngleX = (float)Math.toRadians(-10.0D);
    
    model.bipedRightArm.rotateAngleZ = (float)Math.toRadians(-angle);
    model.bipedRightArm.rotateAngleX = (float)Math.toRadians(-10.0D);
  }
  
  public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
}


