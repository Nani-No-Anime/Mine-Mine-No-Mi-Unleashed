/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.CapeLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.ElytraLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ZoanMorphRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial.KameWalkLayer;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class KameWalkPartialMorphRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel> extends ZoanMorphRenderer<T, M> {
/*    */   public KameWalkPartialMorphRenderer(EntityRendererManager renderManager, ZoanInfo info, boolean hasSmallHands) {
/* 28 */     super(renderManager, info, hasSmallHands);
/* 29 */     removeLayer(HeldItemLayer.class);
/* 30 */     KameWalkLayer partialRenderer = new KameWalkLayer((IEntityRenderer)this);
/* 31 */     addLayer((LayerRenderer)partialRenderer);
/* 32 */     addLayer((LayerRenderer)new HeldItemLayer((IEntityRenderer)this));
/* 33 */     addLayer((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
/* 34 */     addLayer((LayerRenderer)new CapeLayer((IEntityRenderer)this));
/* 35 */     addLayer((LayerRenderer)new ElytraLayer((IEntityRenderer)this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 41 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private ZoanInfo info;
/*    */     private boolean hasSmallHands;
/*    */     
/*    */     public Factory(ZoanInfo info, boolean hasSmallHands) {
/* 51 */       this.info = info;
/* 52 */       this.hasSmallHands = hasSmallHands;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 58 */       return (EntityRenderer)new KameWalkPartialMorphRenderer<>(manager, this.info, this.hasSmallHands);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\zoans\partial\KameWalkPartialMorphRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */