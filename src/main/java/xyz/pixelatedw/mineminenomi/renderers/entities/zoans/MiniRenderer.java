package xyz.pixelatedw.mineminenomi.renderers.entities.zoans;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.mini.MiniMiniAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.NoMorphModel;
import xyz.pixelatedw.mineminenomi.renderers.layers.abilities.PaperFloatLayer;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class MiniRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel> extends ZoanMorphRenderer<T, M> {
  public MiniRenderer(EntityRendererManager rendererManager, ZoanInfo info, boolean hasSmallHands) {
    super(rendererManager, info, hasSmallHands);
    this.entityModel = new NoMorphModel(hasSmallHands);
    addLayer((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
    addLayer((LayerRenderer)new PaperFloatLayer((IEntityRenderer)this));
  }


  
  public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
  }


  
  protected void renderModel(AbstractClientPlayerEntity entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    ((PlayerModel)this.entityModel).setLivingAnimations((LivingEntity)entity, limbSwing, limbSwingAmount, partialTicks);
    ((PlayerModel)this.entityModel).setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    
    IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)entity);
    Ability ability = abilityData.getEquippedAbility((Ability)MiniMiniAbility.INSTANCE);
    boolean hasAbility = (ability != null && ability.isContinuous());
    boolean hasPaper = (entity.getHeldItemMainhand().getItem() == Items.PAPER || entity.getHeldItemOffhand().getItem() == Items.PAPER);
    boolean inAir = (!entity.onGround && (entity.getMotion()).y < 0.0D);
    
    boolean shouldSit = (entity.isPassenger() && entity.getRidingEntity() != null && entity.getRidingEntity().shouldRiderSit());
    if (shouldSit) {
      matrixStack.translate(0.0D, -2.5D, 0.0D);
    }
    if (entity.isCrouching()) {
      matrixStack.translate(0.0D, -0.5D, 0.0D);
    }
    if (hasAbility && hasPaper && inAir) {
      removeLayer((Class)HeldItemLayer.class);
    }
    boolean flag = isVisible(entity);
    boolean flag1 = (!flag && !entity.isInvisibleToPlayer((PlayerEntity)(Minecraft.getInstance()).player));
    RenderType renderType = ModRenderTypes.getZoanRenderType(getEntityTexture(entity));
    if (renderType != null && flag) {
      
      IVertexBuilder ivertexbuilder = buffer.getBuffer(renderType);
      int i = getPackedOverlay((LivingEntity)entity, getOverlayProgress(entity, partialTicks));
      ((PlayerModel)this.entityModel).render(matrixStack, ivertexbuilder, packedLight, i, 1.0F, 1.0F, 1.0F, flag1 ? 0.15F : 1.0F);
    } 
  }


  
  protected void preRenderCallback(AbstractClientPlayerEntity entitylivingbase, MatrixStack matrixStack, float partialTickTime) {
    matrixStack.scale(0.2F, 0.2F, 0.2F);
  }


  
  public ResourceLocation getEntityTexture(AbstractClientPlayerEntity entity) {
    return entity.getLocationSkin();
  }
  
  public static class Factory<T extends PlayerEntity>
    implements IRenderFactory<T>
  {
    private ZoanInfo info;
    private boolean hasSmallHands;
    
    public Factory(ZoanInfo info, boolean hasSmallHands) {
      this.info = info;
      this.hasSmallHands = hasSmallHands;
    }


    
    public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
      MiniRenderer<AbstractClientPlayerEntity, ZoanMorphModel> renderer = new MiniRenderer<>(manager, this.info, this.hasSmallHands);
      return (EntityRenderer)renderer;
    }
  }
}


