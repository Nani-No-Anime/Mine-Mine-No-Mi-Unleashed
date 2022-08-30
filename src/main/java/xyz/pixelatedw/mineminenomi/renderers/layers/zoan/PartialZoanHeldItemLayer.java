/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.zoan;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.HandSide;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class PartialZoanHeldItemLayer<T extends LivingEntity, M extends EntityModel<T> & IHasArm>
/*    */   extends LayerRenderer<T, M>
/*    */ {
/*    */   private ZoanMorphModel partialModel;
/*    */   
/*    */   public PartialZoanHeldItemLayer(IEntityRenderer<T, M> entityRenderer, ZoanMorphModel partialModel) {
/* 27 */     super(entityRenderer);
/* 28 */     this.partialModel = partialModel;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 34 */     boolean flag = (entity.getPrimaryHand() == HandSide.RIGHT);
/* 35 */     ItemStack itemstack = flag ? entity.getHeldItemOffhand() : entity.getHeldItemMainhand();
/* 36 */     ItemStack itemstack1 = flag ? entity.getHeldItemMainhand() : entity.getHeldItemOffhand();
/* 37 */     if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
/*    */       
/* 39 */       matrixStack.push();
/* 40 */       renderItem((LivingEntity)entity, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT, matrixStack, buffer, packedLight);
/* 41 */       renderItem((LivingEntity)entity, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT, matrixStack, buffer, packedLight);
/* 42 */       matrixStack.pop();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void renderItem(LivingEntity entity, ItemStack itemStack, ItemCameraTransforms.TransformType cameraTransform, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 48 */     if (!itemStack.isEmpty() && this.partialModel instanceof IHasArm) {
/*    */       
/* 50 */       matrixStack.push();
/* 51 */       if (!this.partialModel.renderItemInHand(entity, handSide, matrixStack)) {
/*    */         
/* 53 */         matrixStack.pop();
/*    */         return;
/*    */       } 
/* 56 */       matrixStack.rotate(Vector3f.XP.rotationDegrees(-90.0F));
/* 57 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(180.0F));
/* 58 */       boolean flag = (handSide == HandSide.LEFT);
/* 59 */       matrixStack.translate(((flag ? -1 : 1) / 16.0F), 0.125D, -0.625D);
/* 60 */       Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(entity, itemStack, cameraTransform, flag, matrixStack, buffer, packedLight);
/* 61 */       matrixStack.pop();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\zoan\PartialZoanHeldItemLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */