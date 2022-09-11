package xyz.pixelatedw.mineminenomi.renderers.animations.oto;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;

public class DonAnimation
  extends TimeAnimation {
  public static final DonAnimation INSTANCE = new DonAnimation();

  
  private static final float SPEED = 5.0F;

  
  public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    float angle = MathHelper.clamp(-70.0F + (float)getTime() * 5.0F, -70.0F, -10.0F);
    model.bipedLeftArm.rotateAngleZ = (float)Math.toRadians(80.0D);
    model.bipedLeftArm.rotateAngleX = (float)Math.toRadians(angle);
  }
  
  public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
}


