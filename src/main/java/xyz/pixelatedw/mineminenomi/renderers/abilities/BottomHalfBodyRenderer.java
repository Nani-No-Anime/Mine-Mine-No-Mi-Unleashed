/*     */ package xyz.pixelatedw.mineminenomi.renderers.abilities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.Quaternion;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.entities.BottomHalfBodyEntity;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class BottomHalfBodyRenderer
/*     */   extends EntityRenderer<BottomHalfBodyEntity> {
/*  30 */   private PlayerModel model = new PlayerModel(1.0F, false);
/*     */ 
/*     */   
/*     */   public BottomHalfBodyRenderer(EntityRendererManager renderManager) {
/*  34 */     super(renderManager);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(BottomHalfBodyEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  40 */     matrixStack.push();
/*     */     
/*  42 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  43 */     matrixStack.translate(0.0D, 1.5D, 0.0D);
/*  44 */     matrixStack.rotate(new Quaternion(Vector3f.ZP, 180.0F, true));
/*  45 */     matrixStack.rotate(new Quaternion(Vector3f.YP, entity.rotationYaw + 180.0F, true));
/*     */     
/*  47 */     RenderSystem.enableBlend();
/*  48 */     RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/*     */     
/*  50 */     if (entity.hurtTime > 0) {
/*     */       
/*  52 */       matrixStack.push();
/*  53 */       RenderSystem.color3f(1.0F, 0.0F, 0.0F);
/*  54 */       matrixStack.pop();
/*     */     } 
/*     */     
/*  57 */     float f = MathHelper.interpolateAngle(partialTicks, entity.prevRenderYawOffset, entity.renderYawOffset);
/*  58 */     float f1 = MathHelper.interpolateAngle(partialTicks, entity.prevRotationYawHead, entity.rotationYawHead);
/*  59 */     float f2 = f1 - f;
/*     */     
/*  61 */     float f6 = MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch);
/*  62 */     float f7 = entity.ticksExisted + partialTicks;
/*     */     
/*  64 */     float f8 = 0.0F;
/*  65 */     float f5 = 0.0F;
/*  66 */     if (entity.isAlive()) {
/*     */       
/*  68 */       f8 = MathHelper.lerp(partialTicks, entity.prevLimbSwingAmount, entity.limbSwingAmount);
/*  69 */       f5 = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);
/*  70 */       if (entity.isChild())
/*     */       {
/*  72 */         f5 *= 3.0F;
/*     */       }
/*     */       
/*  75 */       if (f8 > 1.0F)
/*     */       {
/*  77 */         f8 = 1.0F;
/*     */       }
/*     */     } 
/*     */     
/*  81 */     ResourceLocation res = getEntityTexture(entity);
/*  82 */     Minecraft.getInstance().getTextureManager().bindTexture(res);
/*  83 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(this.model.getRenderType(res));
/*  84 */     this.model.isChild = false;
/*  85 */     this.model.setLivingAnimations((LivingEntity)entity, f5, f8, partialTicks);
/*  86 */     this.model.setRotationAngles(entity, f5, f8, f7, f2, f6);
/*  87 */     this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*  88 */     this.model.setVisible(false);
/*  89 */     this.model.bipedLeftLeg.showModel = true;
/*  90 */     this.model.bipedRightLeg.showModel = true;
/*     */     
/*  92 */     matrixStack.pop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getEntityTexture(BottomHalfBodyEntity entity) {
/*  98 */     PlayerEntity player = entity.world.getPlayerByUuid(entity.getOwnerUUID());
/*  99 */     if (player != null) {
/* 100 */       return ((AbstractClientPlayerEntity)player).getLocationSkin();
/*     */     }
/* 102 */     return DefaultPlayerSkin.getDefaultSkin(entity.getOwnerUUID());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Factory
/*     */     implements IRenderFactory<BottomHalfBodyEntity>
/*     */   {
/*     */     public EntityRenderer<? super BottomHalfBodyEntity> createRenderFor(EntityRendererManager manager) {
/* 114 */       return new BottomHalfBodyRenderer(manager);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\BottomHalfBodyRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */