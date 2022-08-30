/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities.zoans;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ 
/*    */ public class ModifiedPlayerRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel> extends ZoanMorphRenderer<T, M> {
/* 23 */   private double size = 1.0D;
/*    */ 
/*    */   
/*    */   public ModifiedPlayerRenderer(EntityRendererManager rendererManager, ZoanInfo info, boolean hasSmallHands, double size) {
/* 27 */     super(rendererManager, info, hasSmallHands);
/* 28 */     this.size = size;
/* 29 */     if (this.size == 0.0D)
/* 30 */       this.size = 1.0D; 
/* 31 */     addLayer(new BipedArmorLayer((IEntityRenderer)this, new BipedModel((float)size), new BipedModel((float)size)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 37 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void renderModel(AbstractClientPlayerEntity entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 43 */     ((PlayerModel)this.entityModel).setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
/* 44 */     ((PlayerModel)this.entityModel).setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */     
/* 46 */     boolean shouldSit = (entity.isPassenger() && entity.getRidingEntity() != null && entity.getRidingEntity().shouldRiderSit());
/* 47 */     if (shouldSit) {
/* 48 */       matrixStack.translate(0.0D, -2.5D, 0.0D);
/*    */     }
/* 50 */     boolean flag = isVisible(entity);
/* 51 */     boolean flag1 = (!flag && !entity.isInvisibleToPlayer((PlayerEntity)(Minecraft.getInstance()).player));
/* 52 */     RenderType renderType = ModRenderTypes.getZoanRenderType(getEntityTexture(entity));
/* 53 */     if (renderType != null && flag) {
/*    */       
/* 55 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(renderType);
/* 56 */       int i = getPackedOverlay(entity, getOverlayProgress(entity, partialTicks));
/* 57 */       ((PlayerModel)this.entityModel).render(matrixStack, ivertexbuilder, packedLight, i, 1.0F, 1.0F, 1.0F, flag1 ? 0.15F : 1.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(AbstractClientPlayerEntity entitylivingbase, MatrixStack matrixStack, float partialTickTime) {
/* 64 */     matrixStack.scale((float)this.size, (float)this.size, (float)this.size);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(AbstractClientPlayerEntity entity) {
/* 70 */     return entity.getLocationSkin();
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private ZoanInfo info;
/*    */     private boolean hasSmallHands;
/*    */     private double size;
/*    */     
/*    */     public Factory(ZoanInfo info, boolean hasSmallHands, double size) {
/* 82 */       this.size = size;
/* 83 */       this.info = info;
/* 84 */       this.hasSmallHands = hasSmallHands;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 90 */       ModifiedPlayerRenderer<AbstractClientPlayerEntity, ZoanMorphModel> renderer = new ModifiedPlayerRenderer<>(manager, this.info, this.hasSmallHands, this.size);
/* 91 */       return (EntityRenderer)renderer;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\zoans\ModifiedPlayerRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */