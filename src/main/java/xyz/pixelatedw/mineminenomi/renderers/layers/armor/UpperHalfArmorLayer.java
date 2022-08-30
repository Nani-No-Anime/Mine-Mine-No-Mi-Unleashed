/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.armor;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.ItemRenderer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.ArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ public class UpperHalfArmorLayer<T extends LivingEntity, M extends BipedModel<T>, A extends BipedModel<T>>
/*    */   extends ArmorLayer<T, M, A>
/*    */ {
/*    */   public UpperHalfArmorLayer(IEntityRenderer<T, M> entityRenderer) {
/* 23 */     super(entityRenderer, (A)new BipedModel<T>(0.5F), (A)new BipedModel<T>(0.5F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 29 */     ItemStack itemstack = entity.getItemStackFromSlot(EquipmentSlotType.HEAD);
/* 30 */     if (itemstack.getItem() instanceof xyz.pixelatedw.mineminenomi.api.IExtendedUpperHalfArmor) {
/*    */ 
/*    */       
/* 33 */       BipedModel bipedModel = getModelFromSlot(EquipmentSlotType.HEAD);
/* 34 */       bipedModel = (BipedModel)getArmorModelHook(entity, itemstack, EquipmentSlotType.HEAD, (A)bipedModel);
/* 35 */       ((BipedModel)getEntityModel()).setModelAttributes(bipedModel);
/* 36 */       bipedModel.setLivingAnimations((LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks);
/* 37 */       setModelSlotVisible((A)bipedModel, EquipmentSlotType.HEAD);
/* 38 */       bipedModel.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 39 */       ResourceLocation armorResource = getArmorResource((Entity)entity, itemstack, EquipmentSlotType.HEAD, null);
/* 40 */       boolean hasGlint = itemstack.hasEffect();
/* 41 */       IVertexBuilder ivertexbuilder = ItemRenderer.getBuffer(buffer, RenderType.getEntityCutoutNoCull(armorResource), false, hasGlint);
/* 42 */       bipedModel.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setModelSlotVisible(A model, EquipmentSlotType slot) {
/* 49 */     setModelVisible(model);
/* 50 */     if (slot == EquipmentSlotType.HEAD || slot == EquipmentSlotType.CHEST) {
/*    */       
/* 52 */       ((BipedModel)model).bipedHead.showModel = true;
/* 53 */       ((BipedModel)model).bipedHeadwear.showModel = true;
/* 54 */       ((BipedModel)model).bipedBody.showModel = true;
/* 55 */       ((BipedModel)model).bipedRightArm.showModel = true;
/* 56 */       ((BipedModel)model).bipedLeftArm.showModel = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setModelVisible(A model) {
/* 64 */     model.setVisible(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected A getArmorModelHook(T entity, ItemStack itemStack, EquipmentSlotType slot, A model) {
/* 70 */     return model;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\armor\UpperHalfArmorLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */