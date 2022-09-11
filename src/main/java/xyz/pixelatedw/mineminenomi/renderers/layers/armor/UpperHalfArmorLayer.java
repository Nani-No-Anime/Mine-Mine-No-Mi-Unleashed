package xyz.pixelatedw.mineminenomi.renderers.layers.armor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;


public class UpperHalfArmorLayer<T extends LivingEntity, M extends BipedModel<T>, A extends BipedModel<T>>
  extends ArmorLayer<T, M, A>
{
  public UpperHalfArmorLayer(IEntityRenderer<T, M> entityRenderer) {
    super(entityRenderer, (A)new BipedModel<T>(0.5F), (A)new BipedModel<T>(0.5F));
  }


  
  public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    ItemStack itemstack = entity.getItemStackFromSlot(EquipmentSlotType.HEAD);
    if (itemstack.getItem() instanceof xyz.pixelatedw.mineminenomi.api.IExtendedUpperHalfArmor) {

      
      BipedModel bipedModel = getModelFromSlot(EquipmentSlotType.HEAD);
      bipedModel = (BipedModel)getArmorModelHook(entity, itemstack, EquipmentSlotType.HEAD, (A)bipedModel);
      ((BipedModel)getEntityModel()).setModelAttributes(bipedModel);
      bipedModel.setLivingAnimations((LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks);
      setModelSlotVisible((A)bipedModel, EquipmentSlotType.HEAD);
      bipedModel.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
      ResourceLocation armorResource = getArmorResource((Entity)entity, itemstack, EquipmentSlotType.HEAD, null);
      boolean hasGlint = itemstack.hasEffect();
      IVertexBuilder ivertexbuilder = ItemRenderer.getBuffer(buffer, RenderType.getEntityCutoutNoCull(armorResource), false, hasGlint);
      bipedModel.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    } 
  }


  
  protected void setModelSlotVisible(A model, EquipmentSlotType slot) {
    setModelVisible(model);
    if (slot == EquipmentSlotType.HEAD || slot == EquipmentSlotType.CHEST) {
      
      ((BipedModel)model).bipedHead.showModel = true;
      ((BipedModel)model).bipedHeadwear.showModel = true;
      ((BipedModel)model).bipedBody.showModel = true;
      ((BipedModel)model).bipedRightArm.showModel = true;
      ((BipedModel)model).bipedLeftArm.showModel = true;
    } 
  }



  
  protected void setModelVisible(A model) {
    model.setVisible(false);
  }


  
  protected A getArmorModelHook(T entity, ItemStack itemStack, EquipmentSlotType slot, A model) {
    return model;
  }
}


