/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.Quaternion;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.LivingRenderer;
/*     */ import net.minecraft.client.renderer.entity.PlayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IMorphAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.mixins.client.ILivingRendererMixin;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MorphHelper
/*     */ {
/*     */   public static <T extends LivingEntity, M extends net.minecraft.client.renderer.entity.model.EntityModel<T>> boolean hasLayerFor(LivingRenderer renderer, Class<? extends LayerRenderer> clz) {
/*  47 */     return ((ILivingRendererMixin)renderer).getLayers().stream().anyMatch(layer -> clz.equals(layer.getClass()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addLayerFor(LivingRenderer renderer, LayerRenderer layer) {
/*  52 */     if (!hasLayerFor(renderer, (Class)layer.getClass())) {
/*  53 */       ((ILivingRendererMixin)renderer).getLayers().add(layer);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void removeLayerFor(LivingRenderer renderer, Class<? extends LayerRenderer> clz) {
/*  58 */     ((ILivingRendererMixin)renderer).getLayers().removeIf(layer -> clz.equals(layer.getClass()));
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public static void renderLimbFirstPerson(MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, float equippedProgress, float swingProgress, HandSide side, @Nullable AbilityOverlay overlay, @Nullable ZoanInfo info) {
/*  64 */     Minecraft mc = Minecraft.getInstance();
/*  65 */     ClientPlayerEntity clientPlayerEntity = mc.player;
/*  66 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)mc.player);
/*     */     
/*  68 */     if (props.isBlackLeg()) {
/*     */       
/*  70 */       renderLeg((AbstractClientPlayerEntity)clientPlayerEntity, matrixStack, buffer, combinedLight, equippedProgress, swingProgress, side, null, info);
/*  71 */       renderLeg((AbstractClientPlayerEntity)clientPlayerEntity, matrixStack, buffer, combinedLight, equippedProgress, swingProgress, side, overlay, info);
/*     */     }
/*  73 */     else if (!props.isBlackLeg()) {
/*     */       
/*  75 */       renderArm((AbstractClientPlayerEntity)clientPlayerEntity, matrixStack, buffer, combinedLight, equippedProgress, swingProgress, side, null, info);
/*  76 */       renderArm((AbstractClientPlayerEntity)clientPlayerEntity, matrixStack, buffer, combinedLight, equippedProgress, swingProgress, side, overlay, info);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   private static void renderLeg(AbstractClientPlayerEntity clientPlayer, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, float equippedProgress, float swingProgress, HandSide side, @Nullable AbilityOverlay overlay, @Nullable ZoanInfo info) {
/*  83 */     Minecraft mc = Minecraft.getInstance();
/*  84 */     EntityRendererManager renderManager = mc.getRenderManager();
/*  85 */     PlayerRenderer renderer = (PlayerRenderer)renderManager.getRenderer(clientPlayer);
/*  86 */     IDevilFruit dfProps = DevilFruitCapability.get((LivingEntity)mc.player);
/*     */     
/*  88 */     matrixStack.push();
/*     */     
/*  90 */     boolean flag = (side != HandSide.LEFT);
/*  91 */     float f = flag ? 1.0F : -1.0F;
/*     */     
/*  93 */     float f1 = MathHelper.sqrt(swingProgress) / 1.55F;
/*  94 */     float f2 = -0.3F * MathHelper.sin(f1 * 3.1415927F);
/*  95 */     float f3 = 0.4F * MathHelper.sin(f1 * 6.2831855F);
/*  96 */     float f4 = -0.4F * MathHelper.sin(swingProgress * 3.1415927F);
/*  97 */     matrixStack.translate((f * (f2 + 0.64000005F)), (f3 + -0.6F + equippedProgress * -0.6F), (f4 + -0.71999997F));
/*  98 */     matrixStack.rotate(new Quaternion(Vector3f.ZN, f * 85.0F, true));
/*  99 */     float f6 = MathHelper.sin(f1 * 3.1415927F);
/* 100 */     matrixStack.rotate(new Quaternion(Vector3f.YP, f * f6 * 120.0F, true));
/* 101 */     matrixStack.translate((f * -1.0F), 4.0D, 3.5999999046325684D);
/* 102 */     matrixStack.rotate(new Quaternion(Vector3f.ZP, f * 120.0F, true));
/* 103 */     matrixStack.rotate(new Quaternion(Vector3f.XP, 200.0F, true));
/* 104 */     matrixStack.rotate(new Quaternion(Vector3f.YP, f * -135.0F, true));
/* 105 */     matrixStack.translate((f * 5.6F), 0.0D, 0.0D);
/*     */     
/* 107 */     IVertexBuilder vertex = null;
/* 108 */     float red = (overlay != null) ? (overlay.getColor().getRed() / 255.0F) : 1.0F;
/* 109 */     float green = (overlay != null) ? (overlay.getColor().getGreen() / 255.0F) : 1.0F;
/* 110 */     float blue = (overlay != null) ? (overlay.getColor().getBlue() / 255.0F) : 1.0F;
/* 111 */     float alpha = (overlay != null) ? (overlay.getColor().getAlpha() / 255.0F) : 1.0F;
/*     */     
/* 113 */     if (overlay != null) {
/*     */       
/* 115 */       if (overlay.getTexture() != null) {
/* 116 */         vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(overlay.getTexture()));
/*     */       } else {
/* 118 */         vertex = buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
/*     */       } 
/*     */     } else {
/*     */       
/* 122 */       vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(clientPlayer.getLocationSkin()));
/*     */     } 
/*     */     
/* 125 */     ZoanMorphModel morphModel = null;
/* 126 */     boolean renderLimb = false;
/* 127 */     boolean isPartial = false;
/*     */     
/* 129 */     if (info != null) {
/*     */       
/* 131 */       morphModel = info.getModel();
/* 132 */       isPartial = info.isPartial();
/* 133 */       renderLimb = info.shouldRenderFirstPersonLeg();
/*     */     } 
/*     */     
/* 136 */     if (WyHelper.isNullOrEmpty(dfProps.getZoanPoint()) || (isPartial && !renderLimb)) {
/*     */       
/* 138 */       PlayerModel model = (PlayerModel)renderer.getEntityModel();
/*     */       
/* 140 */       if (flag)
/*     */       {
/* 142 */         model.bipedRightLeg.rotateAngleX = 0.0F;
/* 143 */         model.bipedRightLeg.rotateAngleY = 0.0F;
/* 144 */         model.bipedRightLeg.rotateAngleZ = 0.0F;
/*     */         
/* 146 */         model.bipedRightLeg.rotationPointX = -2.0F;
/* 147 */         model.bipedRightLeg.rotationPointY = 0.0F;
/* 148 */         model.bipedRightLeg.rotationPointZ = 0.0F;
/*     */         
/* 150 */         model.bipedRightLegwear.copyModelAngles(model.bipedRightLeg);
/*     */         
/* 152 */         model.bipedRightLeg.render(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
/* 153 */         model.bipedRightLegwear.render(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
/*     */       }
/*     */       else
/*     */       {
/* 157 */         model.bipedLeftLeg.rotateAngleX = 0.0F;
/* 158 */         model.bipedLeftLeg.rotateAngleY = 0.0F;
/* 159 */         model.bipedLeftLeg.rotateAngleZ = 0.0F;
/*     */         
/* 161 */         model.bipedLeftLeg.rotationPointX = 2.0F;
/* 162 */         model.bipedLeftLeg.rotationPointY = 0.0F;
/* 163 */         model.bipedLeftLeg.rotationPointZ = 0.0F;
/*     */         
/* 165 */         model.bipedLeftLegwear.copyModelAngles(model.bipedLeftLeg);
/*     */         
/* 167 */         model.bipedLeftLeg.render(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
/* 168 */         model.bipedLeftLegwear.render(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 173 */     else if (morphModel != null && renderLimb) {
/*     */       
/* 175 */       ResourceLocation texture = info.getTexture(clientPlayer);
/*     */       
/* 177 */       if (overlay == null || overlay.getTexture() == null)
/*     */       {
/* 179 */         if (texture != null) {
/* 180 */           vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(texture));
/*     */         } else {
/* 182 */           vertex = buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
/*     */         } 
/*     */       }
/* 185 */       morphModel.renderFirstPersonLeg(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha, side);
/*     */     } 
/*     */ 
/*     */     
/* 189 */     matrixStack.pop();
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   private static void renderArm(AbstractClientPlayerEntity clientPlayer, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, float equippedProgress, float swingProgress, HandSide side, @Nullable AbilityOverlay overlay, @Nullable ZoanInfo info) {
/* 195 */     Minecraft mc = Minecraft.getInstance();
/* 196 */     EntityRendererManager renderManager = mc.getRenderManager();
/* 197 */     PlayerRenderer renderer = (PlayerRenderer)renderManager.getRenderer(clientPlayer);
/* 198 */     IDevilFruit dfProps = DevilFruitCapability.get((LivingEntity)mc.player);
/*     */     
/* 200 */     matrixStack.push();
/*     */     
/* 202 */     boolean flag = (side != HandSide.LEFT);
/* 203 */     float f = flag ? 1.0F : -1.0F;
/*     */     
/* 205 */     float f1 = MathHelper.sqrt(swingProgress);
/* 206 */     float f2 = -0.3F * MathHelper.sin(f1 * 3.1415927F);
/* 207 */     float f3 = 0.4F * MathHelper.sin(f1 * 6.2831855F);
/* 208 */     float f4 = -0.4F * MathHelper.sin(swingProgress * 3.1415927F);
/* 209 */     matrixStack.translate((f * (f2 + 0.64000005F)), (f3 + -0.6F + equippedProgress * -0.6F), (f4 + -0.71999997F));
/* 210 */     matrixStack.rotate(new Quaternion(Vector3f.YP, f * 45.0F, true));
/* 211 */     float f5 = MathHelper.sin(swingProgress * swingProgress * 3.1415927F);
/* 212 */     float f6 = MathHelper.sin(f1 * 3.1415927F);
/* 213 */     matrixStack.rotate(new Quaternion(Vector3f.YP, f * f6 * 70.0F, true));
/* 214 */     matrixStack.rotate(new Quaternion(Vector3f.ZP, f * f5 * -20.0F, true));
/*     */     
/* 216 */     matrixStack.translate((f * -1.0F), 3.5999999046325684D, 3.5D);
/* 217 */     matrixStack.rotate(new Quaternion(Vector3f.ZP, f * 120.0F, true));
/* 218 */     matrixStack.rotate(new Quaternion(Vector3f.XP, 200.0F, true));
/* 219 */     matrixStack.rotate(new Quaternion(Vector3f.YP, f * -135.0F, true));
/* 220 */     matrixStack.translate((f * 5.6F), 0.0D, 0.0D);
/*     */     
/* 222 */     IVertexBuilder vertex = null;
/* 223 */     float red = (overlay != null) ? (overlay.getColor().getRed() / 255.0F) : 1.0F;
/* 224 */     float green = (overlay != null) ? (overlay.getColor().getGreen() / 255.0F) : 1.0F;
/* 225 */     float blue = (overlay != null) ? (overlay.getColor().getBlue() / 255.0F) : 1.0F;
/* 226 */     float alpha = (overlay != null) ? (overlay.getColor().getAlpha() / 255.0F) : 1.0F;
/*     */     
/* 228 */     ZoanMorphModel morphModel = null;
/* 229 */     boolean renderLimb = false;
/* 230 */     boolean isPartial = false;
/*     */     
/* 232 */     if (info != null) {
/*     */       
/* 234 */       morphModel = info.getModel();
/* 235 */       isPartial = info.isPartial();
/* 236 */       renderLimb = info.shouldRenderFirstPersonHand();
/*     */     } 
/*     */     
/* 239 */     if (overlay != null) {
/*     */       
/* 241 */       if (overlay.getTexture() != null) {
/* 242 */         vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(overlay.getTexture()));
/*     */       } else {
/* 244 */         vertex = buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
/*     */       } 
/*     */     } else {
/*     */       
/* 248 */       vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(clientPlayer.getLocationSkin()));
/*     */     } 
/*     */     
/* 251 */     if (WyHelper.isNullOrEmpty(dfProps.getZoanPoint()) || (isPartial && !renderLimb)) {
/*     */       
/* 253 */       PlayerModel model = (PlayerModel)renderer.getEntityModel();
/*     */       
/* 255 */       if (flag)
/*     */       {
/* 257 */         model.bipedRightArm.rotateAngleX = 0.0F;
/* 258 */         model.bipedRightArm.rotateAngleY = 0.02F;
/* 259 */         model.bipedRightArm.rotateAngleZ = 0.1F;
/*     */         
/* 261 */         model.bipedRightArm.rotationPointX = -5.0F;
/* 262 */         model.bipedRightArm.rotationPointY = 2.0F;
/* 263 */         model.bipedRightArm.rotationPointZ = 0.0F;
/*     */         
/* 265 */         model.bipedRightArmwear.copyModelAngles(model.bipedRightArm);
/*     */         
/* 267 */         model.bipedRightArm.render(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
/* 268 */         model.bipedRightArmwear.render(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
/*     */       }
/*     */       else
/*     */       {
/* 272 */         model.bipedLeftArm.rotateAngleX = 0.0F;
/* 273 */         model.bipedLeftArm.rotateAngleY = 0.04F;
/* 274 */         model.bipedLeftArm.rotateAngleZ = -0.1F;
/*     */         
/* 276 */         model.bipedLeftArm.rotationPointX = 5.0F;
/* 277 */         model.bipedLeftArm.rotationPointY = 2.0F;
/* 278 */         model.bipedLeftArm.rotationPointZ = 0.0F;
/*     */         
/* 280 */         model.bipedLeftArmwear.copyModelAngles(model.bipedLeftArm);
/*     */         
/* 282 */         model.bipedLeftArm.render(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
/* 283 */         model.bipedLeftArmwear.render(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 288 */     else if (morphModel != null && renderLimb) {
/*     */       
/* 290 */       ResourceLocation texture = info.getTexture(clientPlayer);
/*     */       
/* 292 */       if (overlay == null || overlay.getTexture() == null)
/*     */       {
/* 294 */         if (texture != null) {
/* 295 */           vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(texture));
/*     */         } else {
/* 297 */           vertex = buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
/*     */         } 
/*     */       }
/* 300 */       morphModel.renderFirstPersonArm(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha, side);
/*     */     } 
/*     */ 
/*     */     
/* 304 */     matrixStack.pop();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static ZoanInfo getZoanInfo(LivingEntity entity) {
/* 310 */     IDevilFruit devilFruitProps = DevilFruitCapability.get(entity);
/* 311 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*     */     
/* 313 */     if (devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && YomiZoanInfo.INSTANCE.isActive(entity)) {
/* 314 */       return (ZoanInfo)YomiZoanInfo.INSTANCE;
/*     */     }
/*     */ 
/*     */     
/* 318 */     for (Ability ability : abilityProps.getEquippedAbilities()) {
/*     */       
/* 320 */       if (ability != null && ability instanceof IMorphAbility) {
/*     */ 
/*     */         
/* 323 */         IMorphAbility morphAbility = (IMorphAbility)ability;
/*     */         
/* 325 */         if (morphAbility.isTransformationActive(entity))
/*     */         {
/*     */           
/* 328 */           return morphAbility.getTransformation(); } 
/*     */       } 
/*     */     } 
/* 331 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\MorphHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */