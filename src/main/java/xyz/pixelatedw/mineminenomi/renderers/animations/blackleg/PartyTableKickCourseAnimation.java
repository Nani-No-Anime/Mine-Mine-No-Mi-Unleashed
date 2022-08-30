/*    */ package xyz.pixelatedw.mineminenomi.renderers.animations.blackleg;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.BodyRotateAnimation;
/*    */ 
/*    */ public class PartyTableKickCourseAnimation
/*    */   extends BodyRotateAnimation {
/* 13 */   public static final PartyTableKickCourseAnimation INSTANCE = new PartyTableKickCourseAnimation();
/*    */ 
/*    */   
/*    */   public PartyTableKickCourseAnimation() {
/* 17 */     super(13.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 23 */     model.bipedLeftArm.rotateAngleX = (float)Math.toRadians(180.0D);
/* 24 */     model.bipedLeftArm.rotateAngleZ = (float)Math.toRadians(10.0D);
/* 25 */     model.bipedRightArm.rotateAngleX = (float)Math.toRadians(180.0D);
/* 26 */     model.bipedRightArm.rotateAngleZ = (float)Math.toRadians(-10.0D);
/* 27 */     model.bipedRightLeg.rotateAngleZ = (float)Math.toRadians(90.0D);
/* 28 */     model.bipedLeftLeg.rotateAngleZ = (float)Math.toRadians(-90.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 34 */     matrixStack.translate(0.0D, 1.0D, 0.0D);
/* 35 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(180.0F));
/* 36 */     super.setupAnimation(player, matrixStack, buffer, packedLight, rotationYaw, partialTicks);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\animations\blackleg\PartyTableKickCourseAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */