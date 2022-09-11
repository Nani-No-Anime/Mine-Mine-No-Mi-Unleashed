package xyz.pixelatedw.mineminenomi.api.animations;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;

public interface IHeldItemAnimation<M extends net.minecraft.client.renderer.entity.model.EntityModel> extends IAnimation<M> {
  void setupHeldItem(LivingEntity paramLivingEntity, ItemStack paramItemStack, ItemCameraTransforms.TransformType paramTransformType, HandSide paramHandSide, MatrixStack paramMatrixStack, IRenderTypeBuffer paramIRenderTypeBuffer, int paramInt);
}


