/*    */ package xyz.pixelatedw.mineminenomi.renderers.animations.blackleg;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ 
/*    */ public class ConcasseAnimation
/*    */   implements IAnimation<BipedModel> {
/* 13 */   public static final ConcasseAnimation INSTANCE = new ConcasseAnimation();
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 18 */     model.bipedLeftLeg.rotateAngleX = (float)Math.toRadians(-90.0D);
/* 19 */     model.bipedRightLeg.rotateAngleX = (float)Math.toRadians(180.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 25 */     matrixStack.rotate(Vector3f.XP.rotationDegrees((player.ticksExisted + partialTicks) * 40.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\animations\blackleg\ConcasseAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */