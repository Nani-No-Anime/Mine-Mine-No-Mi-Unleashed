/*    */ package xyz.pixelatedw.mineminenomi.renderers.animations;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
/*    */ 
/*    */ public class BodyRotateAnimation
/*    */   extends TimeAnimation {
/* 13 */   public static final BodyRotateAnimation INSTANCE = new BodyRotateAnimation(30.0F);
/*    */   
/* 15 */   protected float speed = 30.0F;
/*    */ 
/*    */   
/*    */   public BodyRotateAnimation(float speed) {
/* 19 */     this.speed = speed;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(PlayerEntity player, BipedModel entityModel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {
/* 30 */     matrixStack.rotate(Vector3f.YP.rotationDegrees((float)getTime() * this.speed));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\animations\BodyRotateAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */