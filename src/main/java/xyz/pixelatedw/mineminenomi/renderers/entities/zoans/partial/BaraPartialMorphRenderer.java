/*    */ package xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial;
/*    */ 
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
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class BaraPartialMorphRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel>
/*    */   extends ZoanMorphRenderer<T, M> {
/*    */   public BaraPartialMorphRenderer(EntityRendererManager renderManager, ZoanInfo info, BaraMode mode) {
/* 31 */     super(renderManager, info);
/* 32 */     removeLayer(HeldItemLayer.class);
/* 33 */     addLayer((LayerRenderer)new AuraLayer((IEntityRenderer)this));
/* 34 */     this.layer = new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F));
/* 35 */     addLayer((LayerRenderer)this.layer);
/* 36 */     addLayer((LayerRenderer)new CapeLayer((IEntityRenderer)this));
/* 37 */     addLayer((LayerRenderer)new ElytraLayer((IEntityRenderer)this));
/* 38 */     this.mode = mode;
/*    */   }
/*    */   private BaraMode mode;
/*    */   private BipedArmorLayer layer;
/*    */   
/*    */   public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 44 */     if (this.mode == BaraMode.FESTIVAL) {
/*    */       
/* 46 */       ((PlayerModel)getEntityModel()).bipedLeftArm.showModel = false;
/* 47 */       ((PlayerModel)getEntityModel()).bipedLeftArmwear.showModel = false;
/* 48 */       ((PlayerModel)getEntityModel()).bipedRightArm.showModel = false;
/* 49 */       ((PlayerModel)getEntityModel()).bipedRightArmwear.showModel = false;
/* 50 */       ((PlayerModel)getEntityModel()).bipedLeftLeg.showModel = false;
/* 51 */       ((PlayerModel)getEntityModel()).bipedLeftLegwear.showModel = false;
/* 52 */       ((PlayerModel)getEntityModel()).bipedRightLeg.showModel = false;
/* 53 */       ((PlayerModel)getEntityModel()).bipedRightLegwear.showModel = false;
/*    */     }
/* 55 */     else if (this.mode == BaraMode.CIRCUS) {
/*    */       
/* 57 */       ((PlayerModel)getEntityModel()).bipedLeftArm.showModel = false;
/* 58 */       ((PlayerModel)getEntityModel()).bipedLeftArmwear.showModel = false;
/* 59 */       ((PlayerModel)getEntityModel()).bipedRightArm.showModel = false;
/* 60 */       ((PlayerModel)getEntityModel()).bipedRightArmwear.showModel = false;
/*    */     }
/* 62 */     else if (this.mode == BaraMode.HO) {
/*    */       
/* 64 */       ((PlayerModel)getEntityModel()).bipedRightArm.showModel = false;
/* 65 */       ((PlayerModel)getEntityModel()).bipedRightArmwear.showModel = false;
/*    */     } 
/* 67 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T>
/*    */   {
/*    */     private ZoanInfo info;
/*    */     private BaraPartialMorphRenderer.BaraMode mode;
/*    */     
/*    */     public Factory(ZoanInfo info, BaraPartialMorphRenderer.BaraMode mode) {
/* 77 */       this.info = info;
/* 78 */       this.mode = mode;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 84 */       return (EntityRenderer)new BaraPartialMorphRenderer<>(manager, this.info, this.mode);
/*    */     }
/*    */   }
/*    */   
/*    */   public enum BaraMode
/*    */   {
/* 90 */     FESTIVAL,
/* 91 */     CIRCUS,
/* 92 */     HO;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\zoans\partial\BaraPartialMorphRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */