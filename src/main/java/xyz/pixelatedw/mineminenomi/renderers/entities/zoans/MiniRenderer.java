/*     */ package xyz.pixelatedw.mineminenomi.renderers.entities.zoans;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.mini.MiniMiniAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.zoans.NoMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.PaperFloatLayer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ public class MiniRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel> extends ZoanMorphRenderer<T, M> {
/*     */   public MiniRenderer(EntityRendererManager rendererManager, ZoanInfo info, boolean hasSmallHands) {
/*  33 */     super(rendererManager, info, hasSmallHands);
/*  34 */     this.entityModel = new NoMorphModel(hasSmallHands);
/*  35 */     addLayer((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
/*  36 */     addLayer((LayerRenderer)new PaperFloatLayer((IEntityRenderer)this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  42 */     super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderModel(AbstractClientPlayerEntity entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/*  48 */     ((PlayerModel)this.entityModel).setLivingAnimations((LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks);
/*  49 */     ((PlayerModel)this.entityModel).setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */     
/*  51 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)entity);
/*  52 */     Ability ability = abilityData.getEquippedAbility((Ability)MiniMiniAbility.INSTANCE);
/*  53 */     boolean hasAbility = (ability != null && ability.isContinuous());
/*  54 */     boolean hasPaper = (entity.getHeldItemMainhand().getItem() == Items.PAPER || entity.getHeldItemOffhand().getItem() == Items.PAPER);
/*  55 */     boolean inAir = (!entity.onGround && (entity.getMotion()).y < 0.0D);
/*     */     
/*  57 */     boolean shouldSit = (entity.isPassenger() && entity.getRidingEntity() != null && entity.getRidingEntity().shouldRiderSit());
/*  58 */     if (shouldSit) {
/*  59 */       matrixStack.translate(0.0D, -2.5D, 0.0D);
/*     */     }
/*  61 */     if (entity.isCrouching()) {
/*  62 */       matrixStack.translate(0.0D, -0.5D, 0.0D);
/*     */     }
/*  64 */     if (hasAbility && hasPaper && inAir) {
/*  65 */       removeLayer((Class)HeldItemLayer.class);
/*     */     }
/*  67 */     boolean flag = isVisible(entity);
/*  68 */     boolean flag1 = (!flag && !entity.isInvisibleToPlayer((PlayerEntity)(Minecraft.getInstance()).player));
/*  69 */     RenderType renderType = ModRenderTypes.getZoanRenderType(getEntityTexture(entity));
/*  70 */     if (renderType != null && flag) {
/*     */       
/*  72 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(renderType);
/*  73 */       int i = getPackedOverlay((LivingEntity)entity, getOverlayProgress(entity, partialTicks));
/*  74 */       ((PlayerModel)this.entityModel).render(matrixStack, ivertexbuilder, packedLight, i, 1.0F, 1.0F, 1.0F, flag1 ? 0.15F : 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(AbstractClientPlayerEntity entitylivingbase, MatrixStack matrixStack, float partialTickTime) {
/*  81 */     matrixStack.scale(0.2F, 0.2F, 0.2F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getEntityTexture(AbstractClientPlayerEntity entity) {
/*  87 */     return entity.getLocationSkin();
/*     */   }
/*     */   
/*     */   public static class Factory<T extends PlayerEntity>
/*     */     implements IRenderFactory<T>
/*     */   {
/*     */     private ZoanInfo info;
/*     */     private boolean hasSmallHands;
/*     */     
/*     */     public Factory(ZoanInfo info, boolean hasSmallHands) {
/*  97 */       this.info = info;
/*  98 */       this.hasSmallHands = hasSmallHands;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 104 */       MiniRenderer<AbstractClientPlayerEntity, ZoanMorphModel> renderer = new MiniRenderer<>(manager, this.info, this.hasSmallHands);
/* 105 */       return (EntityRenderer)renderer;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\zoans\MiniRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */