/*    */ package xyz.pixelatedw.mineminenomi.renderers.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ 
/*    */ public class HandcuffedAnimation
/*    */   implements IAnimation<BipedModel> {
/* 12 */   public static final HandcuffedAnimation INSTANCE = new HandcuffedAnimation();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 23 */     model.bipedRightArm.rotateAngleX = (float)Math.toRadians(-40.0D);
/* 24 */     model.bipedLeftArm.rotateAngleX = (float)Math.toRadians(-40.0D);
/*    */   }
/*    */   
/*    */   public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\animations\HandcuffedAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */