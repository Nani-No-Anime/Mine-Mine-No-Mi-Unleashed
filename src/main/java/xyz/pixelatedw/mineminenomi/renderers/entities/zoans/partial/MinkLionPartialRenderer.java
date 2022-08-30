/*     */ package xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.PlayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.MinkLionPartialModel;
/*     */ 
/*     */ public class MinkLionPartialRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel>
/*     */   extends PlayerRenderer {
/*  25 */   private MinkLionPartialModel model = new MinkLionPartialModel();
/*  26 */   private ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/mink_lion.png");
/*     */ 
/*     */   
/*     */   public MinkLionPartialRenderer(EntityRendererManager renderManager) {
/*  30 */     super(renderManager);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  36 */     matrixStack.push();
/*  37 */     ((PlayerModel)this.entityModel).swingProgress = getSwingProgress(entity, partialTicks);
/*     */     
/*  39 */     boolean shouldSit = (entity.isPassenger() && entity.getRidingEntity() != null && entity.getRidingEntity().shouldRiderSit());
/*  40 */     ((PlayerModel)this.entityModel).isSitting = shouldSit;
/*  41 */     ((PlayerModel)this.entityModel).isChild = entity.isChild();
/*  42 */     float headYawOffset = MathHelper.interpolateAngle(partialTicks, entity.prevRenderYawOffset, entity.renderYawOffset);
/*  43 */     float headYaw = MathHelper.interpolateAngle(partialTicks, entity.prevRotationYawHead, entity.rotationYawHead);
/*  44 */     float netHeadYaw = headYaw - headYawOffset;
/*  45 */     if (shouldSit && entity.getRidingEntity() instanceof LivingEntity) {
/*     */       
/*  47 */       LivingEntity livingentity = (LivingEntity)entity.getRidingEntity();
/*  48 */       headYawOffset = MathHelper.interpolateAngle(partialTicks, livingentity.prevRenderYawOffset, livingentity.renderYawOffset);
/*  49 */       netHeadYaw = headYaw - headYawOffset;
/*  50 */       float f3 = MathHelper.wrapDegrees(netHeadYaw);
/*  51 */       if (f3 < -85.0F)
/*     */       {
/*  53 */         f3 = -85.0F;
/*     */       }
/*     */       
/*  56 */       if (f3 >= 85.0F)
/*     */       {
/*  58 */         f3 = 85.0F;
/*     */       }
/*     */       
/*  61 */       headYawOffset = headYaw - f3;
/*  62 */       if (f3 * f3 > 2500.0F)
/*     */       {
/*  64 */         headYawOffset += f3 * 0.2F;
/*     */       }
/*     */       
/*  67 */       netHeadYaw = headYaw - headYawOffset;
/*     */     } 
/*     */     
/*  70 */     float headPitch = MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch);
/*  71 */     if (entity.getPose() == Pose.SLEEPING) {
/*     */       
/*  73 */       Direction direction = entity.getBedDirection();
/*  74 */       if (direction != null) {
/*     */         
/*  76 */         float f4 = entity.getEyeHeight(Pose.STANDING) - 0.1F;
/*  77 */         matrixStack.translate((-direction.getXOffset() * f4), 0.0D, (-direction.getZOffset() * f4));
/*     */       } 
/*     */     } 
/*     */     
/*  81 */     float ageInTicks = handleRotationFloat(entity, partialTicks);
/*  82 */     applyRotations(entity, matrixStack, ageInTicks, headYawOffset, partialTicks);
/*  83 */     matrixStack.scale(-1.0F, -1.0F, 1.0F);
/*  84 */     preRenderCallback(entity, matrixStack, partialTicks);
/*  85 */     matrixStack.translate(0.0D, -1.5010000467300415D, 0.0D);
/*  86 */     float limbSwingAmount = 0.0F;
/*  87 */     float limbSwing = 0.0F;
/*  88 */     if (!shouldSit && entity.isAlive()) {
/*     */       
/*  90 */       limbSwingAmount = MathHelper.lerp(partialTicks, entity.prevLimbSwingAmount, entity.limbSwingAmount);
/*  91 */       limbSwing = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);
/*  92 */       if (entity.isChild())
/*     */       {
/*  94 */         limbSwing *= 3.0F;
/*     */       }
/*     */       
/*  97 */       if (limbSwingAmount > 1.0F)
/*     */       {
/*  99 */         limbSwingAmount = 1.0F;
/*     */       }
/*     */     } 
/*     */     
/* 103 */     RenderType renderType = ModRenderTypes.getZoanRenderType(this.texture);
/* 104 */     this.model.isSneak = entity.isCrouching();
/* 105 */     this.model.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 106 */     this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, packedLight, packedLight, entity.rotationYaw, partialTicks, packedLight);
/*     */     
/* 108 */     matrixStack.pop();
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Factory<T extends PlayerEntity>
/*     */     implements IRenderFactory<T>
/*     */   {
/*     */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 116 */       return (EntityRenderer)new MinkLionPartialRenderer<>(manager);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\zoans\partial\MinkLionPartialRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */