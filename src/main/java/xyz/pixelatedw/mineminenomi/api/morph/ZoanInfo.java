/*     */ package xyz.pixelatedw.mineminenomi.api.morph;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ZoanMorphRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ZoanInfo
/*     */ {
/*     */   @Nullable
/*     */   public AkumaNoMiItem getDevilFruit() {
/*  34 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getForm();
/*     */ 
/*     */   
/*     */   public boolean isActive(LivingEntity player) {
/*  43 */     return DevilFruitCapability.get(player).getZoanPoint().equalsIgnoreCase(getForm());
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public abstract Ability getAbility();
/*     */   
/*     */   @Deprecated
/*     */   public String getDisplayName() {
/*  52 */     return getAbility().getDisplayName();
/*     */   }
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public abstract ZoanMorphModel getModel();
/*     */   @Nullable
/*     */   public ResourceLocation getTexture(AbstractClientPlayerEntity entity) {
/*  58 */     return getTexture();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ResourceLocation getTexture() {
/*  64 */     if (getDevilFruit() != null) {
/*  65 */       return new ResourceLocation("mineminenomi", "textures/models/zoanmorph/" + DevilFruitHelper.getDevilFruitKey(getDevilFruit()) + "_" + getForm() + ".png");
/*     */     }
/*  67 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory(AbstractClientPlayerEntity entity) {
/*  73 */     return getRendererFactory();
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public IRenderFactory getRendererFactory() {
/*  79 */     return (IRenderFactory)new ZoanMorphRenderer.Factory(this, hasCulling());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPartial() {
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean shouldRenderFirstPersonHand() {
/*  90 */     return (getModel() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean shouldRenderFirstPersonLeg() {
/*  96 */     return shouldRenderFirstPersonHand();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void preRenderCallback(AbstractClientPlayerEntity player, MatrixStack matrixStack, float partialTickTime) {}
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Map<Pose, EntitySize> getSizes() {
/* 109 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/* 114 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/* 119 */     return -1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMount() {
/* 124 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public boolean hasCulling() {
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraZoom(PlayerEntity player) {
/* 136 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public double getCameraHeight(PlayerEntity player) {
/* 142 */     return 0.0D;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\morph\ZoanInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */