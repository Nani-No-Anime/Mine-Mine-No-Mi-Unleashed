/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.models.HandcuffModel;
/*    */ 
/*    */ public class HandcuffsLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/* 19 */   private static final HandcuffModel MODEL = new HandcuffModel();
/*    */ 
/*    */   
/*    */   public HandcuffsLayer(IEntityRenderer renderer) {
/* 23 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 29 */     if (entity.isPotionActive(ModEffects.HANDCUFFED) || entity.isPotionActive(ModEffects.HANDCUFFED_KAIROSEKI)) {
/*    */       
/* 31 */       if (entity.isPotionActive(ModEffects.HANDCUFFED) && entity.getActivePotionEffect(ModEffects.HANDCUFFED).getDuration() <= 0) {
/* 32 */         entity.removePotionEffect(ModEffects.HANDCUFFED);
/*    */       }
/* 34 */       if (entity.isPotionActive(ModEffects.HANDCUFFED_KAIROSEKI) && entity.getActivePotionEffect(ModEffects.HANDCUFFED_KAIROSEKI).getDuration() <= 0) {
/* 35 */         entity.removePotionEffect(ModEffects.HANDCUFFED_KAIROSEKI);
/*    */       }
/* 37 */       ResourceLocation res = ModResources.HANDCUFFS;
/*    */       
/* 39 */       if (entity.isPotionActive(ModEffects.HANDCUFFED)) {
/* 40 */         res = ModResources.HANDCUFFS;
/* 41 */       } else if (entity.isPotionActive(ModEffects.HANDCUFFED_KAIROSEKI)) {
/* 42 */         res = ModResources.KAIROSEKI_HANDCUFFS;
/*    */       } 
/* 44 */       if (getEntityModel() instanceof net.minecraft.client.renderer.entity.model.BipedModel) {
/*    */         
/* 46 */         matrixStack.translate(0.0D, -0.05D, 0.05D);
/* 47 */         MODEL.render(matrixStack, buffer.getBuffer(ModRenderTypes.getAbilityHand(res)), packedLight, 0, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\HandcuffsLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */