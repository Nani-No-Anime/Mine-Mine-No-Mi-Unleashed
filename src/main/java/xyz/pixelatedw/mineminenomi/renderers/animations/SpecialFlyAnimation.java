/*    */ package xyz.pixelatedw.mineminenomi.renderers.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ 
/*    */ public class SpecialFlyAnimation
/*    */   implements IAnimation<BipedModel> {
/* 12 */   public static final SpecialFlyAnimation INSTANCE = new SpecialFlyAnimation();
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 17 */     double posXDiff = Math.abs(player.prevPosX - player.getPosX());
/* 18 */     double posZDiff = Math.abs(player.prevPosZ - player.getPosZ());
/*    */     
/* 20 */     float angles = 0.0F;
/*    */ 
/*    */     
/* 23 */     if (posXDiff >= 0.2D || posZDiff >= 0.2D)
/* 24 */       angles = (float)Math.toRadians(45.0D); 
/* 25 */     if (posXDiff >= 0.5D || posZDiff >= 0.5D) {
/* 26 */       angles = (float)Math.toRadians(60.0D);
/*    */     }
/* 28 */     model.bipedBody.rotateAngleX = angles;
/*    */     
/* 30 */     if (player.swingProgress <= 0.0F) {
/*    */       
/* 32 */       model.bipedRightArm.rotateAngleX = angles;
/* 33 */       model.bipedLeftArm.rotateAngleX = angles;
/*    */     } 
/*    */     
/* 36 */     model.bipedRightLeg.showModel = false;
/* 37 */     model.bipedLeftLeg.showModel = false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 43 */     double posXDiff = Math.abs(player.prevPosX - player.getPosX());
/* 44 */     double posZDiff = Math.abs(player.prevPosZ - player.getPosZ());
/*    */     
/* 46 */     double drop = 0.0D;
/*    */     
/* 48 */     if (posXDiff >= 0.2D || posZDiff >= 0.2D)
/* 49 */       drop = 0.3D; 
/* 50 */     if (posXDiff >= 0.5D || posZDiff >= 0.5D) {
/* 51 */       drop = 0.4D;
/*    */     }
/* 53 */     matrixStack.translate(0.0D, drop, 0.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\animations\SpecialFlyAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */