package xyz.pixelatedw.mineminenomi.renderers.animations.swordsman;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.renderers.animations.BodyRotateAnimation;

public class BodyRotateWideArmsAnimation extends BodyRotateAnimation {
  public static final BodyRotateWideArmsAnimation INSTANCE = new BodyRotateWideArmsAnimation(-15.0F);

  
  public BodyRotateWideArmsAnimation(float speed) {
    super(speed);
  }


  
  public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    model.bipedRightArm.rotateAngleX = (float)Math.toRadians(-90.0D);
    model.bipedRightArm.rotateAngleZ = (float)Math.toRadians(-90.0D);
    
    model.bipedLeftArm.rotateAngleX = (float)Math.toRadians(90.0D);
    model.bipedLeftArm.rotateAngleZ = (float)Math.toRadians(-90.0D);
  }
}


