/*     */ package xyz.pixelatedw.mineminenomi.renderers.layers;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GearFourthAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiFullBodyHardeningAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ 
/*     */ public class BodyCoatingLayer<T extends LivingEntity, M extends EntityModel<T>>
/*     */   extends LayerRenderer<T, M>
/*     */ {
/*     */   public BodyCoatingLayer(IEntityRenderer renderer) {
/*  36 */     super(renderer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/*  42 */     IVertexBuilder vertex = null;
/*     */ 
/*     */ 
/*     */     
/*  46 */     if (entity instanceof PlayerEntity) {
/*     */       
/*  48 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)entity);
/*  49 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)entity);
/*     */       
/*  51 */       GearFourthAbility g4Ability = (GearFourthAbility)abilityProps.getEquippedAbility((Ability)GearFourthAbility.INSTANCE);
/*  52 */       boolean hasG4 = (g4Ability != null && g4Ability.isContinuous());
/*     */       
/*  54 */       for (Ability ability : abilityProps.getEquippedAbilities()) {
/*     */         
/*  56 */         if (ability != null && ability.isContinuous() && !ability.isStateForced())
/*     */         {
/*     */           
/*  59 */           if (ability instanceof IPunchOverlayAbility && getEntityModel() instanceof net.minecraft.client.renderer.entity.model.IHasArm) {
/*     */             
/*  61 */             AbilityOverlay overlay = ((IPunchOverlayAbility)ability).getPunchOverlay((LivingEntity)entity);
/*  62 */             float red = overlay.getColor().getRed() / 255.0F;
/*  63 */             float green = overlay.getColor().getGreen() / 255.0F;
/*  64 */             float blue = overlay.getColor().getBlue() / 255.0F;
/*  65 */             float alpha = overlay.getColor().getAlpha() / 255.0F;
/*     */             
/*  67 */             if (overlay.getTexture() != null) {
/*  68 */               vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(overlay.getTexture()));
/*     */             } else {
/*  70 */               vertex = overlay.getRenderType().equals(AbilityOverlay.RenderType.ENERGY) ? buffer.getBuffer(ModRenderTypes.ENERGY) : buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
/*     */             } 
/*  72 */             if (props.isBlackLeg()) {
/*  73 */               ((PlayerModel)getEntityModel()).bipedRightLegwear.render(matrixStack, vertex, packedLight, packedLight, red, green, blue, alpha);
/*     */             } else {
/*     */               
/*  76 */               boolean isLeftHanded = false;
/*     */               
/*  78 */               if (entity instanceof PlayerEntity) {
/*  79 */                 isLeftHanded = (((PlayerEntity)entity).getPrimaryHand() == HandSide.LEFT);
/*  80 */               } else if (entity instanceof MobEntity) {
/*  81 */                 isLeftHanded = ((MobEntity)entity).isLeftHanded();
/*     */               } 
/*  83 */               if (isLeftHanded) {
/*  84 */                 ((PlayerModel)getEntityModel()).bipedLeftArmwear.render(matrixStack, vertex, packedLight, packedLight, red, green, blue, alpha);
/*     */               } else {
/*  86 */                 ((PlayerModel)getEntityModel()).bipedRightArmwear.render(matrixStack, vertex, packedLight, packedLight, red, green, blue, alpha);
/*     */               } 
/*     */             } 
/*  89 */           } else if (ability instanceof IBodyOverlayAbility) {
/*     */             
/*  91 */             AbilityOverlay overlay = ((IBodyOverlayAbility)ability).getBodyOverlay();
/*  92 */             float red = overlay.getColor().getRed() / 255.0F;
/*  93 */             float green = overlay.getColor().getGreen() / 255.0F;
/*  94 */             float blue = overlay.getColor().getBlue() / 255.0F;
/*  95 */             float alpha = overlay.getColor().getAlpha() / 255.0F;
/*     */             
/*  97 */             if (hasG4 && ability instanceof BusoshokuHakiFullBodyHardeningAbility) {
/*  98 */               alpha = 0.0F;
/*     */             }
/* 100 */             if (overlay.getTexture() != null) {
/* 101 */               vertex = buffer.getBuffer(ModRenderTypes.getAbilityBody(overlay.getTexture()));
/*     */             } else {
/* 103 */               vertex = overlay.getRenderType().equals(AbilityOverlay.RenderType.ENERGY) ? buffer.getBuffer(ModRenderTypes.ENERGY) : buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
/*     */             } 
/* 105 */             getEntityModel().render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
/*     */           } 
/*     */         }
/*     */       } 
/* 109 */     } else if (entity instanceof OPEntity) {
/*     */       
/* 111 */       OPEntity opEntity = (OPEntity)entity;
/* 112 */       if (opEntity.hasBusoHaki()) {
/*     */         
/* 114 */         AbilityOverlay overlay = BusoshokuHakiHardeningAbility.INSTANCE.getPunchOverlay((LivingEntity)entity);
/* 115 */         float red = overlay.getColor().getRed() / 255.0F;
/* 116 */         float green = overlay.getColor().getGreen() / 255.0F;
/* 117 */         float blue = overlay.getColor().getBlue() / 255.0F;
/* 118 */         float alpha = overlay.getColor().getAlpha() / 255.0F;
/* 119 */         vertex = buffer.getBuffer(ModRenderTypes.getAbilityBody(overlay.getTexture()));
/*     */         
/* 121 */         ((BipedModel)getEntityModel()).bipedRightArm.render(matrixStack, vertex, packedLight, packedLight, red, green, blue, alpha + 0.2F);
/*     */       }
/* 123 */       else if (opEntity.hasFullbodyHaki()) {
/*     */         
/* 125 */         AbilityOverlay overlay = BusoshokuHakiFullBodyHardeningAbility.INSTANCE.getBodyOverlay();
/* 126 */         float red = overlay.getColor().getRed() / 255.0F;
/* 127 */         float green = overlay.getColor().getGreen() / 255.0F;
/* 128 */         float blue = overlay.getColor().getBlue() / 255.0F;
/* 129 */         float alpha = overlay.getColor().getAlpha() / 255.0F;
/* 130 */         vertex = buffer.getBuffer(ModRenderTypes.getAbilityBody(overlay.getTexture()));
/*     */         
/* 132 */         getEntityModel().render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha + 0.2F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\BodyCoatingLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */