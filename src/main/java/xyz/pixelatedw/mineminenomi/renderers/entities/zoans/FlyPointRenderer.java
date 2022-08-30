/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities.zoans;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ 
/*    */ 
/*    */ public class FlyPointRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel>
/*    */   extends ZoanMorphRenderer<T, M>
/*    */ {
/*    */   public FlyPointRenderer(EntityRendererManager rendererManager, ZoanInfo info) {
/* 20 */     super(rendererManager, info);
/* 21 */     setCullingState(true);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void renderModel(AbstractClientPlayerEntity entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 27 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch)));
/* 28 */     super.renderModel(entity, matrixStack, packedLight, buffer, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private ZoanInfo info;
/*    */     private boolean hasCulling;
/*    */     
/*    */     public Factory(ZoanInfo info) {
/* 38 */       this.info = info;
/*    */     }
/*    */ 
/*    */     
/*    */     public Factory(ZoanInfo info, boolean hasCulling) {
/* 43 */       this.info = info;
/* 44 */       this.hasCulling = hasCulling;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 50 */       FlyPointRenderer<AbstractClientPlayerEntity, ZoanMorphModel> renderer = new FlyPointRenderer<>(manager, this.info);
/* 51 */       renderer.setCullingState(this.hasCulling);
/* 52 */       return (EntityRenderer)renderer;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\zoans\FlyPointRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */