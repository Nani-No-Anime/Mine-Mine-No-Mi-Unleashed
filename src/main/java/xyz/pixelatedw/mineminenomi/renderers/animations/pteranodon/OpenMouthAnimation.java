package xyz.pixelatedw.mineminenomi.renderers.animations.pteranodon;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.PteranodonFlyModel;

public class OpenMouthAnimation
  implements IAnimation
{
  public static final OpenMouthAnimation INSTANCE = new OpenMouthAnimation();


  
  public void setAnimationAngles(PlayerEntity player, EntityModel entityModel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    if (entityModel instanceof PteranodonFlyModel) {
      
      PteranodonFlyModel model = (PteranodonFlyModel)entityModel;
      
      model.lowerBeck.rotateAngleX = (float)Math.toRadians(40.0D);
    } 
  }
  
  public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
}


