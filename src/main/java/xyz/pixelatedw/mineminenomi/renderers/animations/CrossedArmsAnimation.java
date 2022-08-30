/*    */ package xyz.pixelatedw.mineminenomi.renderers.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ 
/*    */ public class CrossedArmsAnimation
/*    */   implements IAnimation<BipedModel> {
/* 12 */   public static final CrossedArmsAnimation INSTANCE = new CrossedArmsAnimation();
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 17 */     model.bipedRightArm.rotateAngleX = model.bipedRightArm.rotateAngleX * 0.5F - 3.1415927F + 1.8F;
/* 18 */     model.bipedRightArm.rotateAngleY = -1.2F;
/* 19 */     model.bipedRightArm.rotateAngleZ = -0.8F;
/*    */     
/* 21 */     model.bipedLeftArm.rotateAngleX = model.bipedLeftArm.rotateAngleX * 0.5F - 3.1415927F + 1.8F;
/* 22 */     model.bipedLeftArm.rotateAngleY = 1.2F;
/* 23 */     model.bipedLeftArm.rotateAngleZ = 0.8F;
/*    */   }
/*    */   
/*    */   public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\animations\CrossedArmsAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */