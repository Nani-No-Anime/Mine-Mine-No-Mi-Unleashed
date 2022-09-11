package xyz.pixelatedw.mineminenomi.renderers.animations.ito;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;

public class ShufukuSagyoAnimation
  implements IAnimation
{
  public static final ShufukuSagyoAnimation INSTANCE = new ShufukuSagyoAnimation();


  
  public void setAnimationAngles(PlayerEntity player, EntityModel entityModel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    if (entityModel instanceof BipedModel) {
      
      BipedModel model = (BipedModel)entityModel;
      boolean HPreq = (player.getHealth() > player.getMaxHealth() * 0.7D);
      if (!HPreq) {
        
        model.bipedRightArm.rotateAngleX = model.bipedRightArm.rotateAngleX * 0.5F - 3.1415927F + 1.8F;
        model.bipedRightArm.rotateAngleY = -1.1F;
        model.bipedRightArm.rotateAngleZ = -0.8F;
      } 
      
      model.bipedLeftArm.rotateAngleX = model.bipedLeftArm.rotateAngleX * 0.5F - 3.1415927F + 1.8F;
      model.bipedLeftArm.rotateAngleY = 1.1F;
      model.bipedLeftArm.rotateAngleZ = 0.8F;
      
      if (model instanceof PlayerModel) {
        
        if (!HPreq)
          ((PlayerModel)model).bipedRightArmwear.copyModelAngles(model.bipedRightArm); 
        ((PlayerModel)model).bipedLeftArmwear.copyModelAngles(model.bipedLeftArm);
      } 
    } 
  }
  
  public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
}


