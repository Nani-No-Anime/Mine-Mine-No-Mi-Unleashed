/*    */ package xyz.pixelatedw.mineminenomi.renderers.animations.ope;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.ItemCameraTransforms;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.HandSide;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IHeldItemAnimation;
/*    */ 
/*    */ public class InjectionShotAnimation
/*    */   implements IHeldItemAnimation<BipedModel> {
/* 17 */   public static final InjectionShotAnimation INSTANCE = new InjectionShotAnimation();
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 22 */     model.bipedRightArm.rotationPointZ += 3.0F;
/* 23 */     model.bipedRightArm.rotateAngleX = (float)(model.bipedRightArm.rotateAngleX + Math.toRadians(-70.0D));
/* 24 */     model.bipedRightArm.rotateAngleY = 0.0F;
/*    */     
/* 26 */     model.bipedLeftArm.rotateAngleX = (float)(model.bipedLeftArm.rotateAngleX + Math.toRadians(-90.0D));
/* 27 */     model.bipedLeftArm.rotateAngleY = (float)(model.bipedLeftArm.rotateAngleY + Math.toRadians(60.0D));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ 
/*    */   
/*    */   public void setupHeldItem(LivingEntity entity, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int packedLight) {
/* 36 */     matrixStack.translate(0.0D, -0.2D, -0.05D);
/* 37 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(-75.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\animations\ope\InjectionShotAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */