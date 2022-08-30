/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ZoanMorphRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial.AllosaurusHeavyLayer;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class AllosaurusHeavyPartialMorphRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel> extends ZoanMorphRenderer<T, M> {
/*    */   public AllosaurusHeavyPartialMorphRenderer(EntityRendererManager renderManager, ZoanInfo info, boolean hasSmallHands) {
/* 25 */     super(renderManager, info, hasSmallHands);
/* 26 */     removeLayer(HeldItemLayer.class);
/* 27 */     AllosaurusHeavyLayer partialRenderer = new AllosaurusHeavyLayer((IEntityRenderer)this);
/* 28 */     addLayer((LayerRenderer)partialRenderer);
/* 29 */     addLayer((LayerRenderer)new HeldItemLayer((IEntityRenderer)this));
/* 30 */     addLayer((LayerRenderer)new AuraLayer((IEntityRenderer)this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 36 */     ((PlayerModel)getEntityModel()).bipedRightLeg.showModel = false;
/* 37 */     ((PlayerModel)getEntityModel()).bipedLeftLeg.showModel = false;
/*    */     
/* 39 */     ((PlayerModel)getEntityModel()).bipedLeftLegwear.showModel = false;
/* 40 */     ((PlayerModel)getEntityModel()).bipedRightLegwear.showModel = false;
/*    */     
/* 42 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void renderModel(AbstractClientPlayerEntity entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 48 */     matrixStack.push();
/* 49 */     float scale = 1.1F;
/* 50 */     matrixStack.scale(scale, scale, scale);
/* 51 */     matrixStack.translate(0.0D, -0.18D, 0.0D);
/* 52 */     super.renderModel(entity, matrixStack, packedLight, buffer, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
/* 53 */     matrixStack.pop();
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private ZoanInfo info;
/*    */     private boolean hasSmallHands;
/*    */     
/*    */     public Factory(ZoanInfo info, boolean hasSmallHands) {
/* 63 */       this.info = info;
/* 64 */       this.hasSmallHands = hasSmallHands;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 70 */       return (EntityRenderer)new AllosaurusHeavyPartialMorphRenderer<>(manager, this.info, this.hasSmallHands);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\zoans\partial\AllosaurusHeavyPartialMorphRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */