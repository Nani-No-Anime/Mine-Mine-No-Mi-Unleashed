/*    */ package xyz.pixelatedw.mineminenomi.renderers.animations.swordsman;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.animations.BodyRotateAnimation;
/*    */ 
/*    */ public class BodyRotateWideArmsAnimation extends BodyRotateAnimation {
/*  9 */   public static final BodyRotateWideArmsAnimation INSTANCE = new BodyRotateWideArmsAnimation(-15.0F);
/*    */ 
/*    */   
/*    */   public BodyRotateWideArmsAnimation(float speed) {
/* 13 */     super(speed);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(PlayerEntity player, BipedModel model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 19 */     model.bipedRightArm.rotateAngleX = (float)Math.toRadians(-90.0D);
/* 20 */     model.bipedRightArm.rotateAngleZ = (float)Math.toRadians(-90.0D);
/*    */     
/* 22 */     model.bipedLeftArm.rotateAngleX = (float)Math.toRadians(90.0D);
/* 23 */     model.bipedLeftArm.rotateAngleZ = (float)Math.toRadians(-90.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\animations\swordsman\BodyRotateWideArmsAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */