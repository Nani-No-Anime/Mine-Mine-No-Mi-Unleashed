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
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.zoan.PartialZoanHeldItemLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial.PhoenixAssaultLayer;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class PhoenixAssaultPartialMorphRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel> extends ZoanMorphRenderer<T, M> {
/*    */   public PhoenixAssaultPartialMorphRenderer(EntityRendererManager renderManager, ZoanInfo info) {
/* 26 */     super(renderManager, info);
/* 27 */     removeLayer(HeldItemLayer.class);
/* 28 */     PhoenixAssaultLayer partialRenderer = new PhoenixAssaultLayer((IEntityRenderer)this);
/* 29 */     addLayer((LayerRenderer)partialRenderer);
/* 30 */     addLayer((LayerRenderer)new AuraLayer((IEntityRenderer)this));
/* 31 */     addLayer((LayerRenderer)new PartialZoanHeldItemLayer((IEntityRenderer)this, (ZoanMorphModel)partialRenderer.getPartialModel()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 37 */     ((PlayerModel)getEntityModel()).bipedLeftArm.showModel = false;
/* 38 */     ((PlayerModel)getEntityModel()).bipedRightArm.showModel = false;
/* 39 */     ((PlayerModel)getEntityModel()).bipedRightLeg.showModel = false;
/* 40 */     ((PlayerModel)getEntityModel()).bipedLeftLeg.showModel = false;
/*    */     
/* 42 */     ((PlayerModel)getEntityModel()).bipedLeftArmwear.showModel = false;
/* 43 */     ((PlayerModel)getEntityModel()).bipedRightArmwear.showModel = false;
/* 44 */     ((PlayerModel)getEntityModel()).bipedLeftLegwear.showModel = false;
/* 45 */     ((PlayerModel)getEntityModel()).bipedRightLegwear.showModel = false;
/*    */     
/* 47 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private ZoanInfo info;
/*    */     
/*    */     public Factory(ZoanInfo info) {
/* 56 */       this.info = info;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 62 */       return (EntityRenderer)new PhoenixAssaultPartialMorphRenderer<>(manager, this.info);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\zoans\partial\PhoenixAssaultPartialMorphRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */