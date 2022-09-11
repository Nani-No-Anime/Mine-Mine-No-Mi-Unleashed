package xyz.pixelatedw.mineminenomi.api.animations;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.entity.player.PlayerEntity;

public interface IAnimation<M extends net.minecraft.client.renderer.entity.model.EntityModel> {
  void setupAnimation(PlayerEntity paramPlayerEntity, MatrixStack paramMatrixStack, IRenderTypeBuffer paramIRenderTypeBuffer, int paramInt, float paramFloat1, float paramFloat2);
  
  void setAnimationAngles(PlayerEntity paramPlayerEntity, M paramM, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5);
}


