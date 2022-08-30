/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities.zoans;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.NoMorphModel;
/*    */ 
/*    */ public class MegaRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel> extends ZoanMorphRenderer<T, M> {
/*    */   public MegaRenderer(EntityRendererManager rendererManager, ZoanInfo info, boolean hasSmallHands) {
/* 27 */     super(rendererManager, info, hasSmallHands);
/* 28 */     this.entityModel = new NoMorphModel(hasSmallHands);
/* 29 */     addLayer(new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 35 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void renderModel(AbstractClientPlayerEntity entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 41 */     ((PlayerModel)this.entityModel).setLivingAnimations((LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks);
/* 42 */     ((PlayerModel)this.entityModel).setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */     
/* 44 */     boolean shouldSit = (entity.isPassenger() && entity.getRidingEntity() != null && entity.getRidingEntity().shouldRiderSit());
/* 45 */     if (shouldSit) {
/* 46 */       matrixStack.translate(0.0D, -2.5D, 0.0D);
/*    */     }
/* 48 */     boolean flag = isVisible(entity);
/* 49 */     boolean flag1 = (!flag && !entity.isInvisibleToPlayer((PlayerEntity)(Minecraft.getInstance()).player));
/* 50 */     RenderType renderType = ModRenderTypes.getZoanRenderType(getEntityTexture(entity));
/* 51 */     if (renderType != null && flag) {
/*    */       
/* 53 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(renderType);
/* 54 */       int i = getPackedOverlay((LivingEntity)entity, getOverlayProgress(entity, partialTicks));
/* 55 */       ((PlayerModel)this.entityModel).render(matrixStack, ivertexbuilder, packedLight, i, 1.0F, 1.0F, 1.0F, flag1 ? 0.15F : 1.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(AbstractClientPlayerEntity entitylivingbase, MatrixStack matrixStack, float partialTickTime) {
/* 62 */     matrixStack.scale(4.5F, 4.5F, 4.5F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(AbstractClientPlayerEntity entity) {
/* 68 */     return entity.getLocationSkin();
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private ZoanInfo info;
/*    */     private boolean hasSmallHands;
/*    */     
/*    */     public Factory(ZoanInfo info, boolean hasSmallHands) {
/* 78 */       this.info = info;
/* 79 */       this.hasSmallHands = hasSmallHands;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 85 */       MegaRenderer<AbstractClientPlayerEntity, ZoanMorphModel> renderer = new MegaRenderer<>(manager, this.info, this.hasSmallHands);
/* 86 */       return (EntityRenderer)renderer;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\zoans\MegaRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */