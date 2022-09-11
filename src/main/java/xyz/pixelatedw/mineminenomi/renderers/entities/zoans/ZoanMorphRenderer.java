package xyz.pixelatedw.mineminenomi.renderers.entities.zoans;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import it.unimi.dsi.fastutil.objects.ObjectList;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.client.renderer.entity.layers.Deadmau5HeadLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderNameplateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
import xyz.pixelatedw.mineminenomi.api.animations.TimeAnimation;
import xyz.pixelatedw.mineminenomi.api.events.RenderMorphEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;

@OnlyIn(Dist.CLIENT)
public class ZoanMorphRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel> extends PlayerRenderer {
  protected ZoanInfo info;
  
  public ZoanMorphRenderer(EntityRendererManager rendererManager, ZoanInfo info) {
    this(rendererManager, info, false);
  }
  private boolean hasCulling = false;
  
  public ZoanMorphRenderer(EntityRendererManager rendererManager, ZoanInfo info, boolean smallHands) {
    super(rendererManager, smallHands);
    if (info.getModel() != null && !info.isPartial())
      this.entityModel = (PlayerModel<AbstractClientPlayerEntity>)info.getModel(); 
    this.shadowSize = info.getShadowSize();
    this.info = info;
    addLayer((LayerRenderer)new AuraLayer((IEntityRenderer)this));
    removeLayer((Class)BipedArmorLayer.class);
    removeLayer((Class)CapeLayer.class);
    removeLayer((Class)ElytraLayer.class);
    removeLayer((Class)Deadmau5HeadLayer.class);
  }


  
  public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    setModelVisibilities(entity);
    
    if (MinecraftForge.EVENT_BUS.post((Event)new RenderMorphEvent.Pre((PlayerEntity)entity, this, partialTicks, matrixStack, buffer, packedLight))) {
      return;
    }
    matrixStack.push();
    ((PlayerModel)this.entityModel).swingProgress = getSwingProgress(entity, partialTicks);
    
    boolean shouldSit = (entity.isPassenger() && entity.getRidingEntity() != null && entity.getRidingEntity().shouldRiderSit());
    ((PlayerModel)this.entityModel).isSitting = shouldSit;
    ((PlayerModel)this.entityModel).isChild = entity.isChild();
    float headYawOffset = MathHelper.interpolateAngle(partialTicks, entity.prevRenderYawOffset, entity.renderYawOffset);
    float headYawRotation = MathHelper.interpolateAngle(partialTicks, entity.prevRotationYawHead, entity.rotationYawHead);
    float netHeadYaw = headYawRotation - headYawOffset;
    if (shouldSit && entity.getRidingEntity() instanceof LivingEntity) {
      
      LivingEntity livingentity = (LivingEntity)entity.getRidingEntity();
      headYawOffset = MathHelper.interpolateAngle(partialTicks, livingentity.prevRenderYawOffset, livingentity.renderYawOffset);
      netHeadYaw = headYawRotation - headYawOffset;
      float f3 = MathHelper.wrapDegrees(netHeadYaw);
      if (f3 < -85.0F)
      {
        f3 = -85.0F;
      }
      
      if (f3 >= 85.0F)
      {
        f3 = 85.0F;
      }
      
      headYawOffset = headYawRotation - f3;
      if (f3 * f3 > 2500.0F)
      {
        headYawOffset += f3 * 0.2F;
      }
      
      netHeadYaw = headYawRotation - headYawOffset;
    } 
    
    float headPitch = MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch);
    if (entity.getPose() == Pose.SLEEPING) {
      
      Direction direction = entity.getBedDirection();
      if (direction != null) {
        
        float eyeHeight = entity.getEyeHeight(Pose.STANDING) - 0.1F;
        matrixStack.translate((-direction.getXOffset() * eyeHeight), 0.0D, (-direction.getZOffset() * eyeHeight));
      } 
    } 
    
    float ageInTicks = handleRotationFloat((AbstractClientPlayerEntity)entity, partialTicks);
    applyRotations(entity, matrixStack, ageInTicks, headYawOffset, partialTicks);
    matrixStack.scale(-1.0F, -1.0F, 1.0F);
    this.info.preRenderCallback(entity, matrixStack, partialTicks);
    preRenderCallback(entity, matrixStack, partialTicks);
    matrixStack.translate(0.0D, -1.5010000467300415D, 0.0D);
    float limbSwingAmount = 0.0F;
    float limbSwing = 0.0F;
    if (!shouldSit && entity.isAlive()) {
      
      limbSwingAmount = MathHelper.lerp(partialTicks, entity.prevLimbSwingAmount, entity.limbSwingAmount);
      limbSwing = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);
      if (entity.isChild())
      {
        limbSwing *= 3.0F;
      }
      
      if (limbSwingAmount > 1.0F)
      {
        limbSwingAmount = 1.0F;
      }
    } 
    
    if (isVisible(entity)) {
      
      IAnimatedAbility ability = AbilityHelper.getActiveAnimationAbility(entity);
      if (ability != null) {
        
        if (ability.getAnimation() instanceof TimeAnimation)
          ((TimeAnimation)ability.getAnimation()).tick(); 
        ability.getAnimation().setupAnimation((PlayerEntity)entity, matrixStack, buffer, packedLight, netHeadYaw, partialTicks);
        ability.getAnimation().setAnimationAngles((PlayerEntity)entity, this.entityModel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
      } 
    } 
    
    renderModel(entity, matrixStack, packedLight, buffer, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
    
    if (!entity.isSpectator())
    {
      for (LayerRenderer layerrenderer : this.layerRenderers)
      {
        layerrenderer.render(matrixStack, buffer, packedLight, (Entity)entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
      }
    }





    
    if (WyDebug.isDebug()) {
      
      ModelRenderer modelrenderer = ((PlayerModel)getEntityModel()).getRandomModelRenderer(entity.getRNG());
      Field f = ObfuscationReflectionHelper.findField(ModelRenderer.class, "field_78804_l");
      
      try {
        if (((ObjectList)f.get(modelrenderer)).size() <= 0) {
          WyDebug.debug("Crash potential for " + this.info.getDisplayName());
        }
      } catch (Exception e) {
        
        e.printStackTrace();
      } 
    } 
    
    matrixStack.pop();
    
    MinecraftForge.EVENT_BUS.post((Event)new RenderMorphEvent.Post((PlayerEntity)entity, this, partialTicks, matrixStack, buffer, packedLight));
    
    RenderNameplateEvent renderNameplateEvent = new RenderNameplateEvent((Entity)entity, entity.getDisplayName().getFormattedText(), this, matrixStack, buffer, packedLight);
    MinecraftForge.EVENT_BUS.post((Event)renderNameplateEvent);
    if (renderNameplateEvent.getResult() != Event.Result.DENY && (renderNameplateEvent.getResult() == Event.Result.ALLOW || canRenderName(entity)))
    {
      renderName(entity, renderNameplateEvent.getContent(), matrixStack, buffer, packedLight);
    }
  }


  
  public void applyRotations(AbstractClientPlayerEntity entity, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
    if (this.info == ZouGuardZoanInfo.INSTANCE) {
      
      Pose pose = entity.getPose();
      if (pose != Pose.SLEEPING) {
        matrixStack.rotate(Vector3f.YP.rotationDegrees(180.0F - rotationYaw));
      }
      if (entity.deathTime > 0)
      {
        float f = (entity.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
        f = MathHelper.sqrt(f);
        if (f > 1.0F)
        {
          f = 1.0F;
        }
        
        matrixStack.rotate(Vector3f.ZP.rotationDegrees(f * getDeathMaxRotation(entity)));
      }
    
    } else {
      
      super.applyRotations(entity, matrixStack, ageInTicks, rotationYaw, partialTicks);
    } 
  }

  
  protected void renderModel(AbstractClientPlayerEntity entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    ((PlayerModel)this.entityModel).setLivingAnimations((LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks);
    ((PlayerModel)this.entityModel).setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    
    boolean flag = isVisible(entity);
    boolean flag1 = (!flag && !entity.isInvisibleToPlayer((PlayerEntity)(Minecraft.getInstance()).player));
    RenderType renderType = ModRenderTypes.getZoanRenderType(getEntityTexture(entity));
    if (this.hasCulling)
      renderType = ModRenderTypes.getZoanWithCullingRenderType(getEntityTexture(entity)); 
    if (renderType != null && flag) {
      
      IVertexBuilder ivertexbuilder = buffer.getBuffer(renderType);
      int i = getPackedOverlay((LivingEntity)entity, getOverlayProgress(entity, partialTicks));
      ((PlayerModel)this.entityModel).render(matrixStack, ivertexbuilder, packedLight, i, 1.0F, 1.0F, 1.0F, flag1 ? 0.15F : 1.0F);
    } 
  }

  
  private void setModelVisibilities(AbstractClientPlayerEntity clientPlayer) {
    PlayerModel<AbstractClientPlayerEntity> playermodel = (PlayerModel<AbstractClientPlayerEntity>)getEntityModel();
    if (clientPlayer.isSpectator()) {
      
      playermodel.setVisible(false);
      playermodel.bipedHead.showModel = true;
      playermodel.bipedHeadwear.showModel = true;
    }
    else {
      
      ItemStack itemstack = clientPlayer.getHeldItemMainhand();
      ItemStack itemstack1 = clientPlayer.getHeldItemOffhand();






      
      playermodel.isSneak = clientPlayer.isCrouching();
      BipedModel.ArmPose bipedmodel$armpose = getArmPose(clientPlayer, itemstack, itemstack1, Hand.MAIN_HAND);
      BipedModel.ArmPose bipedmodel$armpose1 = getArmPose(clientPlayer, itemstack, itemstack1, Hand.OFF_HAND);
      if (clientPlayer.getPrimaryHand() == HandSide.RIGHT) {
        
        playermodel.rightArmPose = bipedmodel$armpose;
        playermodel.leftArmPose = bipedmodel$armpose1;
      }
      else {
        
        playermodel.rightArmPose = bipedmodel$armpose1;
        playermodel.leftArmPose = bipedmodel$armpose;
      } 
    } 
  }

  
  protected void removeLayer(Class<? extends LayerRenderer> clz) {
    this.layerRenderers.removeIf(layer -> clz.equals(layer.getClass()));
  }

  
  private BipedModel.ArmPose getArmPose(AbstractClientPlayerEntity playerIn, ItemStack itemStackMain, ItemStack itemStackOff, Hand handIn) {
    BipedModel.ArmPose bipedmodel$armpose = BipedModel.ArmPose.EMPTY;
    ItemStack itemstack = (handIn == Hand.MAIN_HAND) ? itemStackMain : itemStackOff;
    if (!itemstack.isEmpty()) {
      
      bipedmodel$armpose = BipedModel.ArmPose.ITEM;
      if (playerIn.getItemInUseCount() > 0) {
        
        UseAction useaction = itemstack.getUseAction();
        if (useaction == UseAction.BLOCK)
        {
          bipedmodel$armpose = BipedModel.ArmPose.BLOCK;
        }
        else if (useaction == UseAction.BOW)
        {
          bipedmodel$armpose = BipedModel.ArmPose.BOW_AND_ARROW;
        }
        else if (useaction == UseAction.SPEAR)
        {
          bipedmodel$armpose = BipedModel.ArmPose.THROW_SPEAR;
        }
        else if (useaction == UseAction.CROSSBOW && handIn == playerIn.getActiveHand())
        {
          bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_CHARGE;
        }
      
      } else {
        
        boolean flag3 = (itemStackMain.getItem() == Items.CROSSBOW);
        boolean flag = CrossbowItem.isCharged(itemStackMain);
        boolean flag1 = (itemStackOff.getItem() == Items.CROSSBOW);
        boolean flag2 = CrossbowItem.isCharged(itemStackOff);
        if (flag3 && flag)
        {
          bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_HOLD;
        }
        
        if (flag1 && flag2 && itemStackMain.getItem().getUseAction(itemStackMain) == UseAction.NONE)
        {
          bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_HOLD;
        }
      } 
    } 
    
    return bipedmodel$armpose;
  }


  
  public ResourceLocation getEntityTexture(AbstractClientPlayerEntity entity) {
    if (this.info instanceof ZoanInfo && this.info.getTexture(entity) != null && !this.info.isPartial()) {
      return this.info.getTexture(entity);
    }
    return entity.getLocationSkin();
  }

  
  public void setCullingState(boolean flag) {
    this.hasCulling = flag;
  }
  
  public static class Factory<T extends PlayerEntity>
    implements IRenderFactory<T>
  {
    private ZoanInfo info;
    private boolean hasCulling;
    
    public Factory(ZoanInfo info) {
      this.info = info;
    }

    
    public Factory(ZoanInfo info, boolean hasCulling) {
      this.info = info;
      this.hasCulling = hasCulling;
    }


    
    public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
      ZoanMorphRenderer<AbstractClientPlayerEntity, ZoanMorphModel> renderer = new ZoanMorphRenderer<>(manager, this.info);
      renderer.setCullingState(this.hasCulling);
      return (EntityRenderer)renderer;
    }
  }
}


