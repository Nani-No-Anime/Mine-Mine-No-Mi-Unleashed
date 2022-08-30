/*    */ package xyz.pixelatedw.mineminenomi.renderers.animations.ito;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ 
/*    */ public class ShufukuSagyoAnimation
/*    */   implements IAnimation
/*    */ {
/* 14 */   public static final ShufukuSagyoAnimation INSTANCE = new ShufukuSagyoAnimation();
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(PlayerEntity player, EntityModel entityModel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 19 */     if (entityModel instanceof BipedModel) {
/*    */       
/* 21 */       BipedModel model = (BipedModel)entityModel;
/* 22 */       boolean HPreq = (player.getHealth() > player.getMaxHealth() * 0.7D);
/* 23 */       if (!HPreq) {
/*    */         
/* 25 */         model.bipedRightArm.rotateAngleX = model.bipedRightArm.rotateAngleX * 0.5F - 3.1415927F + 1.8F;
/* 26 */         model.bipedRightArm.rotateAngleY = -1.1F;
/* 27 */         model.bipedRightArm.rotateAngleZ = -0.8F;
/*    */       } 
/*    */       
/* 30 */       model.bipedLeftArm.rotateAngleX = model.bipedLeftArm.rotateAngleX * 0.5F - 3.1415927F + 1.8F;
/* 31 */       model.bipedLeftArm.rotateAngleY = 1.1F;
/* 32 */       model.bipedLeftArm.rotateAngleZ = 0.8F;
/*    */       
/* 34 */       if (model instanceof PlayerModel) {
/*    */         
/* 36 */         if (!HPreq)
/* 37 */           ((PlayerModel)model).bipedRightArmwear.copyModelAngles(model.bipedRightArm); 
/* 38 */         ((PlayerModel)model).bipedLeftArmwear.copyModelAngles(model.bipedLeftArm);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\animations\ito\ShufukuSagyoAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */