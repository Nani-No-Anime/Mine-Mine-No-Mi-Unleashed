/*    */ package xyz.pixelatedw.mineminenomi.renderers.animations.oto;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
/*    */ 
/*    */ public class BonAnimation
/*    */   extends TimeAnimation {
/* 13 */   public static final BonAnimation INSTANCE = new BonAnimation();
/*    */ 
/*    */   
/*    */   private static final float SPEED = 5.0F;
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 20 */     model.bipedLeftArm.rotateAngleZ = (float)Math.toRadians(30.0D);
/* 21 */     model.bipedLeftArm.rotateAngleX = (float)Math.toRadians(-50.0D);
/*    */     
/* 23 */     model.bipedRightArm.rotateAngleZ = (float)Math.toRadians(-60.0D);
/* 24 */     float angle = MathHelper.clamp(0.0F + (float)getTime() * 5.0F, 0.0F, 30.0F);
/* 25 */     model.bipedRightArm.rotateAngleX = (float)Math.toRadians((-60.0F + angle));
/*    */   }
/*    */   
/*    */   public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\animations\oto\BonAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */