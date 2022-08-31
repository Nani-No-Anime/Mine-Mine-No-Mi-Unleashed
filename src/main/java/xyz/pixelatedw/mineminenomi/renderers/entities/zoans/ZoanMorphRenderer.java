/*     */ package xyz.pixelatedw.mineminenomi.renderers.entities.zoans;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectList;
/*     */ import java.lang.reflect.Field;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.PlayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.CapeLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.Deadmau5HeadLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.ElytraLayer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.CrossbowItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.item.UseAction;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.event.RenderNameplateEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.RenderMorphEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.ZouGuardZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class ZoanMorphRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel> extends PlayerRenderer {
/*     */   protected ZoanInfo info;
/*     */   
/*     */   public ZoanMorphRenderer(EntityRendererManager rendererManager, ZoanInfo info) {
/*  61 */     this(rendererManager, info, false);
/*     */   }
/*     */   private boolean hasCulling = false;
/*     */   
/*     */   public ZoanMorphRenderer(EntityRendererManager rendererManager, ZoanInfo info, boolean smallHands) {
/*  66 */     super(rendererManager, smallHands);
/*  67 */     if (info.getModel() != null && !info.isPartial())
/*  68 */       this.entityModel = (PlayerModel<AbstractClientPlayerEntity>)info.getModel(); 
/*  69 */     this.shadowSize = info.getShadowSize();
/*  70 */     this.info = info;
/*  71 */     addLayer((LayerRenderer)new AuraLayer((IEntityRenderer)this));
/*  72 */     removeLayer((Class)BipedArmorLayer.class);
/*  73 */     removeLayer((Class)CapeLayer.class);
/*  74 */     removeLayer((Class)ElytraLayer.class);
/*  75 */     removeLayer((Class)Deadmau5HeadLayer.class);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/*  81 */     setModelVisibilities(entity);
/*     */     
/*  83 */     if (MinecraftForge.EVENT_BUS.post((Event)new RenderMorphEvent.Pre((PlayerEntity)entity, this, partialTicks, matrixStack, buffer, packedLight))) {
/*     */       return;
/*     */     }
/*  86 */     matrixStack.push();
/*  87 */     ((PlayerModel)this.entityModel).swingProgress = getSwingProgress(entity, partialTicks);
/*     */     
/*  89 */     boolean shouldSit = (entity.isPassenger() && entity.getRidingEntity() != null && entity.getRidingEntity().shouldRiderSit());
/*  90 */     ((PlayerModel)this.entityModel).isSitting = shouldSit;
/*  91 */     ((PlayerModel)this.entityModel).isChild = entity.isChild();
/*  92 */     float headYawOffset = MathHelper.interpolateAngle(partialTicks, entity.prevRenderYawOffset, entity.renderYawOffset);
/*  93 */     float headYawRotation = MathHelper.interpolateAngle(partialTicks, entity.prevRotationYawHead, entity.rotationYawHead);
/*  94 */     float netHeadYaw = headYawRotation - headYawOffset;
/*  95 */     if (shouldSit && entity.getRidingEntity() instanceof LivingEntity) {
/*     */       
/*  97 */       LivingEntity livingentity = (LivingEntity)entity.getRidingEntity();
/*  98 */       headYawOffset = MathHelper.interpolateAngle(partialTicks, livingentity.prevRenderYawOffset, livingentity.renderYawOffset);
/*  99 */       netHeadYaw = headYawRotation - headYawOffset;
/* 100 */       float f3 = MathHelper.wrapDegrees(netHeadYaw);
/* 101 */       if (f3 < -85.0F)
/*     */       {
/* 103 */         f3 = -85.0F;
/*     */       }
/*     */       
/* 106 */       if (f3 >= 85.0F)
/*     */       {
/* 108 */         f3 = 85.0F;
/*     */       }
/*     */       
/* 111 */       headYawOffset = headYawRotation - f3;
/* 112 */       if (f3 * f3 > 2500.0F)
/*     */       {
/* 114 */         headYawOffset += f3 * 0.2F;
/*     */       }
/*     */       
/* 117 */       netHeadYaw = headYawRotation - headYawOffset;
/*     */     } 
/*     */     
/* 120 */     float headPitch = MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch);
/* 121 */     if (entity.getPose() == Pose.SLEEPING) {
/*     */       
/* 123 */       Direction direction = entity.getBedDirection();
/* 124 */       if (direction != null) {
/*     */         
/* 126 */         float eyeHeight = entity.getEyeHeight(Pose.STANDING) - 0.1F;
/* 127 */         matrixStack.translate((-direction.getXOffset() * eyeHeight), 0.0D, (-direction.getZOffset() * eyeHeight));
/*     */       } 
/*     */     } 
/*     */     
/* 131 */     float ageInTicks = handleRotationFloat((AbstractClientPlayerEntity)entity, partialTicks);
/* 132 */     applyRotations(entity, matrixStack, ageInTicks, headYawOffset, partialTicks);
/* 133 */     matrixStack.scale(-1.0F, -1.0F, 1.0F);
/* 134 */     this.info.preRenderCallback(entity, matrixStack, partialTicks);
/* 135 */     preRenderCallback(entity, matrixStack, partialTicks);
/* 136 */     matrixStack.translate(0.0D, -1.5010000467300415D, 0.0D);
/* 137 */     float limbSwingAmount = 0.0F;
/* 138 */     float limbSwing = 0.0F;
/* 139 */     if (!shouldSit && entity.isAlive()) {
/*     */       
/* 141 */       limbSwingAmount = MathHelper.lerp(partialTicks, entity.prevLimbSwingAmount, entity.limbSwingAmount);
/* 142 */       limbSwing = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);
/* 143 */       if (entity.isChild())
/*     */       {
/* 145 */         limbSwing *= 3.0F;
/*     */       }
/*     */       
/* 148 */       if (limbSwingAmount > 1.0F)
/*     */       {
/* 150 */         limbSwingAmount = 1.0F;
/*     */       }
/*     */     } 
/*     */     
/* 154 */     if (isVisible(entity)) {
/*     */       
/* 156 */       IAnimatedAbility ability = AbilityHelper.getActiveAnimationAbility(entity);
/* 157 */       if (ability != null) {
/*     */         
/* 159 */         if (ability.getAnimation() instanceof TimeAnimation)
/* 160 */           ((TimeAnimation)ability.getAnimation()).tick(); 
/* 161 */         ability.getAnimation().setupAnimation((PlayerEntity)entity, matrixStack, buffer, packedLight, netHeadYaw, partialTicks);
/* 162 */         ability.getAnimation().setAnimationAngles((PlayerEntity)entity, this.entityModel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */       } 
/*     */     } 
/*     */     
/* 166 */     renderModel(entity, matrixStack, packedLight, buffer, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
/*     */     
/* 168 */     if (!entity.isSpectator())
/*     */     {
/* 170 */       for (LayerRenderer layerrenderer : this.layerRenderers)
/*     */       {
/* 172 */         layerrenderer.render(matrixStack, buffer, packedLight, (Entity)entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     if (WyDebug.isDebug()) {
/*     */       
/* 183 */       ModelRenderer modelrenderer = ((PlayerModel)getEntityModel()).getRandomModelRenderer(entity.getRNG());
/* 184 */       Field f = ObfuscationReflectionHelper.findField(ModelRenderer.class, "field_78804_l");
/*     */       
/*     */       try {
/* 187 */         if (((ObjectList)f.get(modelrenderer)).size() <= 0) {
/* 188 */           WyDebug.debug("Crash potential for " + this.info.getDisplayName());
/*     */         }
/* 190 */       } catch (Exception e) {
/*     */         
/* 192 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/* 196 */     matrixStack.pop();
/*     */     
/* 198 */     MinecraftForge.EVENT_BUS.post((Event)new RenderMorphEvent.Post((PlayerEntity)entity, this, partialTicks, matrixStack, buffer, packedLight));
/*     */     
/* 200 */     RenderNameplateEvent renderNameplateEvent = new RenderNameplateEvent((Entity)entity, entity.getDisplayName().getFormattedText(), this, matrixStack, buffer, packedLight);
/* 201 */     MinecraftForge.EVENT_BUS.post((Event)renderNameplateEvent);
/* 202 */     if (renderNameplateEvent.getResult() != Event.Result.DENY && (renderNameplateEvent.getResult() == Event.Result.ALLOW || canRenderName(entity)))
/*     */     {
/* 204 */       renderName(entity, renderNameplateEvent.getContent(), matrixStack, buffer, packedLight);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyRotations(AbstractClientPlayerEntity entity, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
/* 211 */     if (this.info == ZouGuardZoanInfo.INSTANCE) {
/*     */       
/* 213 */       Pose pose = entity.getPose();
/* 214 */       if (pose != Pose.SLEEPING) {
/* 215 */         matrixStack.rotate(Vector3f.YP.rotationDegrees(180.0F - rotationYaw));
/*     */       }
/* 217 */       if (entity.deathTime > 0)
/*     */       {
/* 219 */         float f = (entity.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
/* 220 */         f = MathHelper.sqrt(f);
/* 221 */         if (f > 1.0F)
/*     */         {
/* 223 */           f = 1.0F;
/*     */         }
/*     */         
/* 226 */         matrixStack.rotate(Vector3f.ZP.rotationDegrees(f * getDeathMaxRotation(entity)));
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 231 */       super.applyRotations(entity, matrixStack, ageInTicks, rotationYaw, partialTicks);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderModel(AbstractClientPlayerEntity entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 237 */     ((PlayerModel)this.entityModel).setLivingAnimations((LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks);
/* 238 */     ((PlayerModel)this.entityModel).setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*     */     
/* 240 */     boolean flag = isVisible(entity);
/* 241 */     boolean flag1 = (!flag && !entity.isInvisibleToPlayer((PlayerEntity)(Minecraft.getInstance()).player));
/* 242 */     RenderType renderType = ModRenderTypes.getZoanRenderType(getEntityTexture(entity));
/* 243 */     if (this.hasCulling)
/* 244 */       renderType = ModRenderTypes.getZoanWithCullingRenderType(getEntityTexture(entity)); 
/* 245 */     if (renderType != null && flag) {
/*     */       
/* 247 */       IVertexBuilder ivertexbuilder = buffer.getBuffer(renderType);
/* 248 */       int i = getPackedOverlay((LivingEntity)entity, getOverlayProgress(entity, partialTicks));
/* 249 */       ((PlayerModel)this.entityModel).render(matrixStack, ivertexbuilder, packedLight, i, 1.0F, 1.0F, 1.0F, flag1 ? 0.15F : 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setModelVisibilities(AbstractClientPlayerEntity clientPlayer) {
/* 255 */     PlayerModel<AbstractClientPlayerEntity> playermodel = (PlayerModel<AbstractClientPlayerEntity>)getEntityModel();
/* 256 */     if (clientPlayer.isSpectator()) {
/*     */       
/* 258 */       playermodel.setVisible(false);
/* 259 */       playermodel.bipedHead.showModel = true;
/* 260 */       playermodel.bipedHeadwear.showModel = true;
/*     */     }
/*     */     else {
/*     */       
/* 264 */       ItemStack itemstack = clientPlayer.getHeldItemMainhand();
/* 265 */       ItemStack itemstack1 = clientPlayer.getHeldItemOffhand();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 273 */       playermodel.isSneak = clientPlayer.isCrouching();
/* 274 */       BipedModel.ArmPose bipedmodel$armpose = getArmPose(clientPlayer, itemstack, itemstack1, Hand.MAIN_HAND);
/* 275 */       BipedModel.ArmPose bipedmodel$armpose1 = getArmPose(clientPlayer, itemstack, itemstack1, Hand.OFF_HAND);
/* 276 */       if (clientPlayer.getPrimaryHand() == HandSide.RIGHT) {
/*     */         
/* 278 */         playermodel.rightArmPose = bipedmodel$armpose;
/* 279 */         playermodel.leftArmPose = bipedmodel$armpose1;
/*     */       }
/*     */       else {
/*     */         
/* 283 */         playermodel.rightArmPose = bipedmodel$armpose1;
/* 284 */         playermodel.leftArmPose = bipedmodel$armpose;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void removeLayer(Class<? extends LayerRenderer> clz) {
/* 291 */     this.layerRenderers.removeIf(layer -> clz.equals(layer.getClass()));
/*     */   }
/*     */ 
/*     */   
/*     */   private BipedModel.ArmPose getArmPose(AbstractClientPlayerEntity playerIn, ItemStack itemStackMain, ItemStack itemStackOff, Hand handIn) {
/* 296 */     BipedModel.ArmPose bipedmodel$armpose = BipedModel.ArmPose.EMPTY;
/* 297 */     ItemStack itemstack = (handIn == Hand.MAIN_HAND) ? itemStackMain : itemStackOff;
/* 298 */     if (!itemstack.isEmpty()) {
/*     */       
/* 300 */       bipedmodel$armpose = BipedModel.ArmPose.ITEM;
/* 301 */       if (playerIn.getItemInUseCount() > 0) {
/*     */         
/* 303 */         UseAction useaction = itemstack.getUseAction();
/* 304 */         if (useaction == UseAction.BLOCK)
/*     */         {
/* 306 */           bipedmodel$armpose = BipedModel.ArmPose.BLOCK;
/*     */         }
/* 308 */         else if (useaction == UseAction.BOW)
/*     */         {
/* 310 */           bipedmodel$armpose = BipedModel.ArmPose.BOW_AND_ARROW;
/*     */         }
/* 312 */         else if (useaction == UseAction.SPEAR)
/*     */         {
/* 314 */           bipedmodel$armpose = BipedModel.ArmPose.THROW_SPEAR;
/*     */         }
/* 316 */         else if (useaction == UseAction.CROSSBOW && handIn == playerIn.getActiveHand())
/*     */         {
/* 318 */           bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_CHARGE;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 323 */         boolean flag3 = (itemStackMain.getItem() == Items.CROSSBOW);
/* 324 */         boolean flag = CrossbowItem.isCharged(itemStackMain);
/* 325 */         boolean flag1 = (itemStackOff.getItem() == Items.CROSSBOW);
/* 326 */         boolean flag2 = CrossbowItem.isCharged(itemStackOff);
/* 327 */         if (flag3 && flag)
/*     */         {
/* 329 */           bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_HOLD;
/*     */         }
/*     */         
/* 332 */         if (flag1 && flag2 && itemStackMain.getItem().getUseAction(itemStackMain) == UseAction.NONE)
/*     */         {
/* 334 */           bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_HOLD;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 339 */     return bipedmodel$armpose;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getEntityTexture(AbstractClientPlayerEntity entity) {
/* 345 */     if (this.info instanceof ZoanInfo && this.info.getTexture(entity) != null && !this.info.isPartial()) {
/* 346 */       return this.info.getTexture(entity);
/*     */     }
/* 348 */     return entity.getLocationSkin();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCullingState(boolean flag) {
/* 353 */     this.hasCulling = flag;
/*     */   }
/*     */   
/*     */   public static class Factory<T extends PlayerEntity>
/*     */     implements IRenderFactory<T>
/*     */   {
/*     */     private ZoanInfo info;
/*     */     private boolean hasCulling;
/*     */     
/*     */     public Factory(ZoanInfo info) {
/* 363 */       this.info = info;
/*     */     }
/*     */ 
/*     */     
/*     */     public Factory(ZoanInfo info, boolean hasCulling) {
/* 368 */       this.info = info;
/* 369 */       this.hasCulling = hasCulling;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 375 */       ZoanMorphRenderer<AbstractClientPlayerEntity, ZoanMorphModel> renderer = new ZoanMorphRenderer<>(manager, this.info);
/* 376 */       renderer.setCullingState(this.hasCulling);
/* 377 */       return (EntityRenderer)renderer;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\renderers\entities\zoans\ZoanMorphRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */