package xyz.pixelatedw.mineminenomi.renderers.animations;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;

public class BodyRotateAnimation
  extends TimeAnimation {
  public static final BodyRotateAnimation INSTANCE = new BodyRotateAnimation(30.0F);
  
  protected float speed = 30.0F;

  
  public BodyRotateAnimation(float speed) {
    this.speed = speed;
  }



  
  public void setAnimationAngles(PlayerEntity player, BipedModel entityModel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
    matrixStack.rotate(Vector3f.YP.rotationDegrees((float)getTime() * this.speed));
  }
}


