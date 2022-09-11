package xyz.pixelatedw.mineminenomi.renderers.layers.zoan;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;

@OnlyIn(Dist.CLIENT)
public class PartialZoanHeldItemLayer<T extends LivingEntity, M extends EntityModel<T> & IHasArm>
  extends LayerRenderer<T, M>
{
  private ZoanMorphModel partialModel;
  
  public PartialZoanHeldItemLayer(IEntityRenderer<T, M> entityRenderer, ZoanMorphModel partialModel) {
    super(entityRenderer);
    this.partialModel = partialModel;
  }


  
  public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    boolean flag = (entity.getPrimaryHand() == HandSide.RIGHT);
    ItemStack itemstack = flag ? entity.getHeldItemOffhand() : entity.getHeldItemMainhand();
    ItemStack itemstack1 = flag ? entity.getHeldItemMainhand() : entity.getHeldItemOffhand();
    if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
      
      matrixStack.push();
      renderItem((LivingEntity)entity, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT, matrixStack, buffer, packedLight);
      renderItem((LivingEntity)entity, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT, matrixStack, buffer, packedLight);
      matrixStack.pop();
    } 
  }

  
  private void renderItem(LivingEntity entity, ItemStack itemStack, ItemCameraTransforms.TransformType cameraTransform, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    if (!itemStack.isEmpty() && this.partialModel instanceof IHasArm) {
      
      matrixStack.push();
      if (!this.partialModel.renderItemInHand(entity, handSide, matrixStack)) {
        
        matrixStack.pop();
        return;
      } 
      matrixStack.rotate(Vector3f.XP.rotationDegrees(-90.0F));
      matrixStack.rotate(Vector3f.YP.rotationDegrees(180.0F));
      boolean flag = (handSide == HandSide.LEFT);
      matrixStack.translate(((flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);
      Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(entity, itemStack, cameraTransform, flag, matrixStack, buffer, packedLight);
      matrixStack.pop();
    } 
  }
}


