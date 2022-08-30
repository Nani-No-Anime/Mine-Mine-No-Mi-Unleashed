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
/*    */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ZoanMorphRenderer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial.DamnedPunkLayer;
import xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial.PunkGibsonLayer;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class PunkGibsonPartialMorphRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel> extends ZoanMorphRenderer<T, M> {
/*    */   public PunkGibsonPartialMorphRenderer(EntityRendererManager renderManager, ZoanInfo info, boolean isDamnedPunk) {
/* 30 */     super(renderManager, info);
/* 31 */     removeLayer(HeldItemLayer.class);
/* 32 */     addLayer((LayerRenderer)new AuraLayer((IEntityRenderer)this));
/* 33 */     addLayer((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
/* 34 */     addLayer((LayerRenderer)new CapeLayer((IEntityRenderer)this));
/* 35 */     addLayer((LayerRenderer)new ElytraLayer((IEntityRenderer)this));
/* 36 */     if (isDamnedPunk) {
/* 37 */       addLayer((LayerRenderer)new DamnedPunkLayer((IEntityRenderer)this));
/*    */     } else {
/* 39 */       addLayer((LayerRenderer)new PunkGibsonLayer((IEntityRenderer)this));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 45 */     ((PlayerModel)getEntityModel()).bipedRightArm.showModel = false;
/* 46 */     ((PlayerModel)getEntityModel()).bipedRightArmwear.showModel = false;
/*    */     
/* 48 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private ZoanInfo info;
/*    */     private boolean isDamnedPunk;
/*    */     
/*    */     public Factory(ZoanInfo info, boolean isDamnedPunk) {
/* 58 */       this.info = info;
/* 59 */       this.isDamnedPunk = isDamnedPunk;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 65 */       return (EntityRenderer)new PunkGibsonPartialMorphRenderer<>(manager, this.info, this.isDamnedPunk);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\zoans\partial\PunkGibsonPartialMorphRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */