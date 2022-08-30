/*    */ package xyz.pixelatedw.mineminenomi.renderers.animations.pteranodon;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.PteranodonFlyModel;
/*    */ 
/*    */ public class OpenMouthAnimation
/*    */   implements IAnimation
/*    */ {
/* 13 */   public static final OpenMouthAnimation INSTANCE = new OpenMouthAnimation();
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAnimationAngles(PlayerEntity player, EntityModel entityModel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 18 */     if (entityModel instanceof PteranodonFlyModel) {
/*    */       
/* 20 */       PteranodonFlyModel model = (PteranodonFlyModel)entityModel;
/*    */       
/* 22 */       model.lowerBeck.rotateAngleX = (float)Math.toRadians(40.0D);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setupAnimation(PlayerEntity player, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, float rotationYaw, float partialTicks) {}
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\animations\pteranodon\OpenMouthAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */