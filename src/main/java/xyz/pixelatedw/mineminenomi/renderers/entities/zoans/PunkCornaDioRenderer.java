/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities.zoans;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PunkCornaDioBullZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.BodyCoatingLayer;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class PunkCornaDioRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel<T>>
/*    */   extends ZoanMorphRenderer<T, M> {
/*    */   public PunkCornaDioRenderer(EntityRendererManager renderManager, ZoanInfo info) {
/* 26 */     super(renderManager, info);
/* 27 */     addLayer(new BodyCoatingLayer(this));
/* 28 */     this.cornaDioRenderer = (new ZoanMorphRenderer.Factory<>((ZoanInfo)PunkCornaDioBullZoanInfo.INSTANCE)).createRenderFor(renderManager);
/*    */   }
/*    */   
/*    */   private EntityRenderer<? super PlayerEntity> cornaDioRenderer;
/*    */   
/*    */   public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 34 */     matrixStack.push();
/* 35 */     matrixStack.translate(0.0D, 0.7D, 0.0D);
/* 36 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/* 37 */     matrixStack.pop();
/*    */     
/* 39 */     this.cornaDioRenderer.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getEntityTexture(AbstractClientPlayerEntity entity) {
/* 45 */     return entity.getLocationSkin();
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private ZoanInfo info;
/*    */     
/*    */     public Factory(ZoanInfo info) {
/* 54 */       this.info = info;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 60 */       return (EntityRenderer)new PunkCornaDioRenderer<>(manager, this.info);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\zoans\PunkCornaDioRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */