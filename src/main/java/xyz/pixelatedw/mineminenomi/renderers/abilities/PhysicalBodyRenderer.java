/*     */ package xyz.pixelatedw.mineminenomi.renderers.abilities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.platform.GlStateManager;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.Quaternion;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.models.abilities.PhysicalBodyModel;
/*     */ 
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class PhysicalBodyRenderer
/*     */   extends EntityRenderer<PhysicalBodyEntity>
/*     */ {
/*  36 */   private PhysicalBodyModel model = new PhysicalBodyModel();
/*     */ 
/*     */   
/*     */   public PhysicalBodyRenderer(EntityRendererManager renderManager) {
/*  40 */     super(renderManager);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(PhysicalBodyEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  46 */     boolean isSkeleton = false;
/*  47 */     if (entity.getOwner() != null) {
/*     */       
/*  49 */       IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)entity.getOwner());
/*  50 */       isSkeleton = (devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && YomiZoanInfo.INSTANCE.isActive((LivingEntity)entity.getOwner()));
/*     */     } 
/*     */     
/*  53 */     matrixStack.push();
/*     */     
/*  55 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  56 */     matrixStack.translate(0.0D, 1.5D, 0.0D);
/*  57 */     matrixStack.rotate(new Quaternion(Vector3f.ZP, 180.0F, true));
/*  58 */     matrixStack.rotate(new Quaternion(Vector3f.YP, entity.rotationYaw + 180.0F, true));
/*     */     
/*  60 */     RenderSystem.enableBlend();
/*  61 */     RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
/*     */     
/*  63 */     if (entity.hurtTime > 0) {
/*     */       
/*  65 */       matrixStack.push();
/*  66 */       RenderSystem.color3f(1.0F, 0.0F, 0.0F);
/*  67 */       matrixStack.pop();
/*     */     } 
/*     */     
/*  70 */     ResourceLocation res = getEntityTexture(entity);
/*  71 */     if (isSkeleton) {
/*     */       
/*  73 */       res = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/yomi_yomi_skeleton.png");
/*  74 */       Minecraft.getInstance().getTextureManager().bindTexture(res);
/*     */     }
/*     */     else {
/*     */       
/*  78 */       res = getEntityTexture(entity);
/*  79 */       Minecraft.getInstance().getTextureManager().bindTexture(res);
/*     */     } 
/*  81 */     IVertexBuilder ivertexbuilder = buffer.getBuffer(this.model.getRenderType(res));
/*  82 */     this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  84 */     matrixStack.pop();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getEntityTexture(PhysicalBodyEntity entity) {
/*  90 */     PlayerEntity player = entity.getOwner();
/*  91 */     if (player != null) {
/*  92 */       return ((AbstractClientPlayerEntity)player).getLocationSkin();
/*     */     }
/*     */     
/*  95 */     UUID uuid = entity.getOwnerUUID();
/*  96 */     if (uuid != null) {
/*  97 */       return DefaultPlayerSkin.getDefaultSkin(uuid);
/*     */     }
/*  99 */     return DefaultPlayerSkin.getDefaultSkinLegacy();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Factory
/*     */     implements IRenderFactory<PhysicalBodyEntity>
/*     */   {
/*     */     public EntityRenderer<? super PhysicalBodyEntity> createRenderFor(EntityRendererManager manager) {
/* 112 */       return new PhysicalBodyRenderer(manager);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\PhysicalBodyRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */