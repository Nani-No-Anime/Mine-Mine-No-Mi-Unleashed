/*    */ package xyz.pixelatedw.mineminenomi.renderers.layers.abilities;
import java.util.Optional;

/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.entity.model.IHasHead;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hana.HanaHandsEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*    */ import xyz.pixelatedw.mineminenomi.models.abilities.EntityArmModel;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HanaHandsLayer<T extends LivingEntity, M extends EntityModel<T>>
/*    */   extends LayerRenderer<T, M> {
/* 26 */   private EntityArmModel model = new EntityArmModel();
/*    */ 
/*    */   
/*    */   public HanaHandsLayer(IEntityRenderer renderer) {
/* 30 */     super(renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 36 */     Optional<HanaHandsEntity> OptionalHands = WyHelper.<HanaHandsEntity>getEntitiesNear(entity.getPosition(), ((LivingEntity)entity).world, 1.0D,new Class[]{HanaHandsEntity.class}).stream().findFirst();
HanaHandsEntity hands= OptionalHands.orElse((HanaHandsEntity)null);
/* 37 */     boolean inClutch = (hands != null);
/* 38 */     boolean hasEffect = (entity.isPotionActive(ModEffects.HANA_HANDS) && entity.getActivePotionEffect(ModEffects.HANA_HANDS).getDuration() > 0);
/*    */     
/* 40 */     if (!inClutch || hands == null || hands.getCaster() == null || !hasEffect) {
/*    */       return;
/*    */     }
/* 43 */     int type = hands.getHandsType();
/* 44 */     float progress = hands.getAnimationProgress(partialTicks);
/*    */     
/* 46 */     float animation = 1.0F;
/* 47 */     if (progress > 0.5F && progress < 0.6F) {
/* 48 */       animation += progress / 2.0F;
/* 49 */     } else if (progress > 0.6F) {
/* 50 */       animation = (float)(animation * (1.0D - progress) / 0.4000000059604645D);
/*    */     } 
/* 52 */     matrixStack.push();
/* 53 */     ModelRenderer pivotModel = null;
/* 54 */     float armsPos = -1.0F;
/* 55 */     if (getEntityModel() instanceof BipedModel) {
/* 56 */       pivotModel = ((BipedModel)getEntityModel()).bipedBody;
/* 57 */     } else if (getEntityModel() instanceof IHasHead) {
/* 58 */       pivotModel = ((IHasHead)getEntityModel()).getModelHead();
/*    */     } else {
/* 60 */       armsPos = entity.getHeight() / 8.0F;
/*    */     } 
/* 62 */     if (pivotModel != null) {
/* 63 */       pivotModel.translateRotate(matrixStack);
/*    */     } else {
/* 65 */       matrixStack.translate(0.0D, -armsPos, 0.0D);
/*    */     } 
/* 67 */     ResourceLocation skin = ((AbstractClientPlayerEntity)hands.getCaster()).getLocationSkin();
/* 68 */     RenderType renderType = ModRenderTypes.getZoanRenderType(skin);
/* 69 */     if (type == 0) {
/*    */       
/* 71 */       matrixStack.scale(animation, animation, animation);
/* 72 */       matrixStack.rotate(Vector3f.ZN.rotationDegrees(90.0F));
/* 73 */       matrixStack.translate(0.3D, -0.35D, -0.1D);
/* 74 */       this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/* 75 */       matrixStack.translate(-0.5D, 0.0D, 0.0D);
/* 76 */       this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     }
/* 78 */     else if (type == 1) {
/*    */       
/* 80 */       matrixStack.rotate(Vector3f.ZN.rotationDegrees(120.0F));
/* 81 */       matrixStack.rotate(Vector3f.ZN.rotationDegrees(90.0F * animation));
/* 82 */       matrixStack.translate(0.35D, -0.35D, -0.1D);
/* 83 */       matrixStack.translate(-0.0D, 0.0D, -0.1D);
/* 84 */       this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/* 86 */     matrixStack.pop();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\abilities\HanaHandsLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */