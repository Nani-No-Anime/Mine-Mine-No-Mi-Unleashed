package xyz.pixelatedw.mineminenomi.renderers.layers.armor;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;


public class LowerHalfArmorLayer<T extends LivingEntity, M extends BipedModel<T>, A extends BipedModel<T>>
  extends ArmorLayer<T, M, A>
{
  public LowerHalfArmorLayer(IEntityRenderer<T, M> entityRenderer) {
    super(entityRenderer, (A)new BipedModel<T>(0.5F), (A)new BipedModel<T>(0.5F));
  }


  
  public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    ItemStack itemstack = entity.getItemStackFromSlot(EquipmentSlotType.HEAD);
    if (itemstack.getItem() instanceof xyz.pixelatedw.mineminenomi.api.IExtendedLowerHalfArmor)
    {
      super.render(matrixStack, buffer, packedLight, entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
    }
  }


  
  protected void setModelSlotVisible(A model, EquipmentSlotType slot) {
    setModelVisible(model);
    if (slot == EquipmentSlotType.LEGS || slot == EquipmentSlotType.FEET) {
      
      ((BipedModel)model).bipedBody.showModel = true;
      ((BipedModel)model).bipedRightLeg.showModel = true;
      ((BipedModel)model).bipedLeftLeg.showModel = true;
    } 
  }



  
  protected void setModelVisible(A model) {
    model.setVisible(false);
  }


  
  protected A getArmorModelHook(T entity, ItemStack itemStack, EquipmentSlotType slot, A model) {
    return model;
  }
}


