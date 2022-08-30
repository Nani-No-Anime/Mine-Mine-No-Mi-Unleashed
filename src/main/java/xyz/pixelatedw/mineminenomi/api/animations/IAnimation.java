package xyz.pixelatedw.mineminenomi.api.animations;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.entity.player.PlayerEntity;

public interface IAnimation<M extends net.minecraft.client.renderer.entity.model.EntityModel> {
  void setupAnimation(PlayerEntity paramPlayerEntity, MatrixStack paramMatrixStack, IRenderTypeBuffer paramIRenderTypeBuffer, int paramInt, float paramFloat1, float paramFloat2);
  
  void setAnimationAngles(PlayerEntity paramPlayerEntity, M paramM, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5);
}


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\animations\IAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */