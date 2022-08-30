/*    */ package xyz.pixelatedw.mineminenomi.renderers.animations.gura;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
/*    */ 
/*    */ public class KaishinAnimation
/*    */   extends TimeAnimation {
/* 13 */   public static final KaishinAnimation INSTANCE = new KaishinAnimation();
/*    */ 
/*    */   
/*    */   private static final double SPEED = 20.0D;
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 20 */     double angle = MathHelper.clamp((80L - getTime()) * 20.0D, -50.0D, 80.0D);
/* 21 */     model.bipedLeftArm.rotateAngleZ = (float)Math.toRadians(angle);
/* 22 */     model.bipedLeftArm.rotateAngleX = (float)Math.toRadians(-10.0D);
/*    */     
/* 24 */     model.bipedRightArm.rotateAngleZ = (float)Math.toRadians(-angle);
/* 25 */     model.bipedRightArm.rotateAngleX = (float)Math.toRadians(-10.0D);
/*    */   }
/*    */   
/*    */   public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\animations\gura\KaishinAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */