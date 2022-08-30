/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.armor;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.ArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class LowerHalfArmorLayer<T extends LivingEntity, M extends BipedModel<T>, A extends BipedModel<T>>
/*    */   extends ArmorLayer<T, M, A>
/*    */ {
/*    */   public LowerHalfArmorLayer(IEntityRenderer<T, M> entityRenderer) {
/* 18 */     super(entityRenderer, (A)new BipedModel<T>(0.5F), (A)new BipedModel<T>(0.5F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 24 */     ItemStack itemstack = entity.getItemStackFromSlot(EquipmentSlotType.HEAD);
/* 25 */     if (itemstack.getItem() instanceof xyz.pixelatedw.mineminenomi.api.IExtendedLowerHalfArmor)
/*    */     {
/* 27 */       super.render(matrixStack, buffer, packedLight, entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setModelSlotVisible(A model, EquipmentSlotType slot) {
/* 34 */     setModelVisible(model);
/* 35 */     if (slot == EquipmentSlotType.LEGS || slot == EquipmentSlotType.FEET) {
/*    */       
/* 37 */       ((BipedModel)model).bipedBody.showModel = true;
/* 38 */       ((BipedModel)model).bipedRightLeg.showModel = true;
/* 39 */       ((BipedModel)model).bipedLeftLeg.showModel = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setModelVisible(A model) {
/* 47 */     model.setVisible(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected A getArmorModelHook(T entity, ItemStack itemStack, EquipmentSlotType slot, A model) {
/* 53 */     return model;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\armor\LowerHalfArmorLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */