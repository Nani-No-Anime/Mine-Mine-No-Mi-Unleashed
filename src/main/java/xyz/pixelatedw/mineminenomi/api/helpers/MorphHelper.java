package xyz.pixelatedw.mineminenomi.api.helpers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IMorphAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.mixins.client.ILivingRendererMixin;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import javax.annotation.Nullable;



public class MorphHelper
{
  public static <T extends LivingEntity, M extends net.minecraft.client.renderer.entity.model.EntityModel<T>> boolean hasLayerFor(LivingRenderer renderer, Class<? extends LayerRenderer> clz) {
    return ((ILivingRendererMixin)renderer).getLayers().stream().anyMatch(layer -> clz.equals(layer.getClass()));
  }

  
  public static void addLayerFor(LivingRenderer renderer, LayerRenderer layer) {
    if (!hasLayerFor(renderer, (Class)layer.getClass())) {
      ((ILivingRendererMixin)renderer).getLayers().add(layer);
    }
  }
  
  public static void removeLayerFor(LivingRenderer renderer, Class<? extends LayerRenderer> clz) {
    ((ILivingRendererMixin)renderer).getLayers().removeIf(layer -> clz.equals(layer.getClass()));
  }

  
  @OnlyIn(Dist.CLIENT)
  public static void renderLimbFirstPerson(MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, float equippedProgress, float swingProgress, HandSide side, @Nullable AbilityOverlay overlay, @Nullable ZoanInfo info) {
    Minecraft mc = Minecraft.getInstance();
    ClientPlayerEntity clientPlayerEntity = mc.player;
    IEntityStats props = EntityStatsCapability.get((LivingEntity)mc.player);
    
    if (props.isBlackLeg()) {
      
      renderLeg((AbstractClientPlayerEntity)clientPlayerEntity, matrixStack, buffer, combinedLight, equippedProgress, swingProgress, side, null, info);
      renderLeg((AbstractClientPlayerEntity)clientPlayerEntity, matrixStack, buffer, combinedLight, equippedProgress, swingProgress, side, overlay, info);
    }
    else if (!props.isBlackLeg()) {
      
      renderArm((AbstractClientPlayerEntity)clientPlayerEntity, matrixStack, buffer, combinedLight, equippedProgress, swingProgress, side, null, info);
      renderArm((AbstractClientPlayerEntity)clientPlayerEntity, matrixStack, buffer, combinedLight, equippedProgress, swingProgress, side, overlay, info);
    } 
  }

  
  @OnlyIn(Dist.CLIENT)
  private static void renderLeg(AbstractClientPlayerEntity clientPlayer, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, float equippedProgress, float swingProgress, HandSide side, @Nullable AbilityOverlay overlay, @Nullable ZoanInfo info) {
    Minecraft mc = Minecraft.getInstance();
    EntityRendererManager renderManager = mc.getRenderManager();
    PlayerRenderer renderer = (PlayerRenderer)renderManager.getRenderer(clientPlayer);
    IDevilFruit dfProps = DevilFruitCapability.get((LivingEntity)mc.player);
    
    matrixStack.push();
    
    boolean flag = (side != HandSide.LEFT);
    float f = flag ? 1.0F : -1.0F;
    
    float f1 = MathHelper.sqrt(swingProgress) / 1.55F;
    float f2 = -0.3F * MathHelper.sin(f1 * 3.1415927F);
    float f3 = 0.4F * MathHelper.sin(f1 * 6.2831855F);
    float f4 = -0.4F * MathHelper.sin(swingProgress * 3.1415927F);
    matrixStack.translate((f * (f2 + 0.64000005F)), (f3 + -0.6F + equippedProgress * -0.6F), (f4 + -0.71999997F));
    matrixStack.rotate(new Quaternion(Vector3f.ZN, f * 85.0F, true));
    float f6 = MathHelper.sin(f1 * 3.1415927F);
    matrixStack.rotate(new Quaternion(Vector3f.YP, f * f6 * 120.0F, true));
    matrixStack.translate((f * -1.0F), 4.0D, 3.5999999046325684D);
    matrixStack.rotate(new Quaternion(Vector3f.ZP, f * 120.0F, true));
    matrixStack.rotate(new Quaternion(Vector3f.XP, 200.0F, true));
    matrixStack.rotate(new Quaternion(Vector3f.YP, f * -135.0F, true));
    matrixStack.translate((f * 5.6F), 0.0D, 0.0D);
    
    IVertexBuilder vertex = null;
    float red = (overlay != null) ? (overlay.getColor().getRed() / 255.0F) : 1.0F;
    float green = (overlay != null) ? (overlay.getColor().getGreen() / 255.0F) : 1.0F;
    float blue = (overlay != null) ? (overlay.getColor().getBlue() / 255.0F) : 1.0F;
    float alpha = (overlay != null) ? (overlay.getColor().getAlpha() / 255.0F) : 1.0F;
    
    if (overlay != null) {
      
      if (overlay.getTexture() != null) {
        vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(overlay.getTexture()));
      } else {
        vertex = buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
      } 
    } else {
      
      vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(clientPlayer.getLocationSkin()));
    } 
    
    ZoanMorphModel morphModel = null;
    boolean renderLimb = false;
    boolean isPartial = false;
    
    if (info != null) {
      
      morphModel = info.getModel();
      isPartial = info.isPartial();
      renderLimb = info.shouldRenderFirstPersonLeg();
    } 
    
    if (WyHelper.isNullOrEmpty(dfProps.getZoanPoint()) || (isPartial && !renderLimb)) {
      
      PlayerModel model = (PlayerModel)renderer.getEntityModel();
      
      if (flag)
      {
        model.bipedRightLeg.rotateAngleX = 0.0F;
        model.bipedRightLeg.rotateAngleY = 0.0F;
        model.bipedRightLeg.rotateAngleZ = 0.0F;
        
        model.bipedRightLeg.rotationPointX = -2.0F;
        model.bipedRightLeg.rotationPointY = 0.0F;
        model.bipedRightLeg.rotationPointZ = 0.0F;
        
        model.bipedRightLegwear.copyModelAngles(model.bipedRightLeg);
        
        model.bipedRightLeg.render(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
        model.bipedRightLegwear.render(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
      }
      else
      {
        model.bipedLeftLeg.rotateAngleX = 0.0F;
        model.bipedLeftLeg.rotateAngleY = 0.0F;
        model.bipedLeftLeg.rotateAngleZ = 0.0F;
        
        model.bipedLeftLeg.rotationPointX = 2.0F;
        model.bipedLeftLeg.rotationPointY = 0.0F;
        model.bipedLeftLeg.rotationPointZ = 0.0F;
        
        model.bipedLeftLegwear.copyModelAngles(model.bipedLeftLeg);
        
        model.bipedLeftLeg.render(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
        model.bipedLeftLegwear.render(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
      
      }
    
    }
    else if (morphModel != null && renderLimb) {
      
      ResourceLocation texture = info.getTexture(clientPlayer);
      
      if (overlay == null || overlay.getTexture() == null)
      {
        if (texture != null) {
          vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(texture));
        } else {
          vertex = buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
        } 
      }
      morphModel.renderFirstPersonLeg(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha, side);
    } 

    
    matrixStack.pop();
  }

  
  @OnlyIn(Dist.CLIENT)
  private static void renderArm(AbstractClientPlayerEntity clientPlayer, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, float equippedProgress, float swingProgress, HandSide side, @Nullable AbilityOverlay overlay, @Nullable ZoanInfo info) {
    Minecraft mc = Minecraft.getInstance();
    EntityRendererManager renderManager = mc.getRenderManager();
    PlayerRenderer renderer = (PlayerRenderer)renderManager.getRenderer(clientPlayer);
    IDevilFruit dfProps = DevilFruitCapability.get((LivingEntity)mc.player);
    
    matrixStack.push();
    
    boolean flag = (side != HandSide.LEFT);
    float f = flag ? 1.0F : -1.0F;
    
    float f1 = MathHelper.sqrt(swingProgress);
    float f2 = -0.3F * MathHelper.sin(f1 * 3.1415927F);
    float f3 = 0.4F * MathHelper.sin(f1 * 6.2831855F);
    float f4 = -0.4F * MathHelper.sin(swingProgress * 3.1415927F);
    matrixStack.translate((f * (f2 + 0.64000005F)), (f3 + -0.6F + equippedProgress * -0.6F), (f4 + -0.71999997F));
    matrixStack.rotate(new Quaternion(Vector3f.YP, f * 45.0F, true));
    float f5 = MathHelper.sin(swingProgress * swingProgress * 3.1415927F);
    float f6 = MathHelper.sin(f1 * 3.1415927F);
    matrixStack.rotate(new Quaternion(Vector3f.YP, f * f6 * 70.0F, true));
    matrixStack.rotate(new Quaternion(Vector3f.ZP, f * f5 * -20.0F, true));
    
    matrixStack.translate((f * -1.0F), 3.5999999046325684D, 3.5D);
    matrixStack.rotate(new Quaternion(Vector3f.ZP, f * 120.0F, true));
    matrixStack.rotate(new Quaternion(Vector3f.XP, 200.0F, true));
    matrixStack.rotate(new Quaternion(Vector3f.YP, f * -135.0F, true));
    matrixStack.translate((f * 5.6F), 0.0D, 0.0D);
    
    IVertexBuilder vertex = null;
    float red = (overlay != null) ? (overlay.getColor().getRed() / 255.0F) : 1.0F;
    float green = (overlay != null) ? (overlay.getColor().getGreen() / 255.0F) : 1.0F;
    float blue = (overlay != null) ? (overlay.getColor().getBlue() / 255.0F) : 1.0F;
    float alpha = (overlay != null) ? (overlay.getColor().getAlpha() / 255.0F) : 1.0F;
    
    ZoanMorphModel morphModel = null;
    boolean renderLimb = false;
    boolean isPartial = false;
    
    if (info != null) {
      
      morphModel = info.getModel();
      isPartial = info.isPartial();
      renderLimb = info.shouldRenderFirstPersonHand();
    } 
    
    if (overlay != null) {
      
      if (overlay.getTexture() != null) {
        vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(overlay.getTexture()));
      } else {
        vertex = buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
      } 
    } else {
      
      vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(clientPlayer.getLocationSkin()));
    } 
    
    if (WyHelper.isNullOrEmpty(dfProps.getZoanPoint()) || (isPartial && !renderLimb)) {
      
      PlayerModel model = (PlayerModel)renderer.getEntityModel();
      
      if (flag)
      {
        model.bipedRightArm.rotateAngleX = 0.0F;
        model.bipedRightArm.rotateAngleY = 0.02F;
        model.bipedRightArm.rotateAngleZ = 0.1F;
        
        model.bipedRightArm.rotationPointX = -5.0F;
        model.bipedRightArm.rotationPointY = 2.0F;
        model.bipedRightArm.rotationPointZ = 0.0F;
        
        model.bipedRightArmwear.copyModelAngles(model.bipedRightArm);
        
        model.bipedRightArm.render(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
        model.bipedRightArmwear.render(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
      }
      else
      {
        model.bipedLeftArm.rotateAngleX = 0.0F;
        model.bipedLeftArm.rotateAngleY = 0.04F;
        model.bipedLeftArm.rotateAngleZ = -0.1F;
        
        model.bipedLeftArm.rotationPointX = 5.0F;
        model.bipedLeftArm.rotationPointY = 2.0F;
        model.bipedLeftArm.rotationPointZ = 0.0F;
        
        model.bipedLeftArmwear.copyModelAngles(model.bipedLeftArm);
        
        model.bipedLeftArm.render(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
        model.bipedLeftArmwear.render(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
      
      }
    
    }
    else if (morphModel != null && renderLimb) {
      
      ResourceLocation texture = info.getTexture(clientPlayer);
      
      if (overlay == null || overlay.getTexture() == null)
      {
        if (texture != null) {
          vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(texture));
        } else {
          vertex = buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
        } 
      }
      morphModel.renderFirstPersonArm(matrixStack, vertex, combinedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha, side);
    } 

    
    matrixStack.pop();
  }

  
  @Nullable
  public static ZoanInfo getZoanInfo(LivingEntity entity) {
    IDevilFruit devilFruitProps = DevilFruitCapability.get(entity);
    IAbilityData abilityProps = AbilityDataCapability.get(entity);
    
    if (devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && YomiZoanInfo.INSTANCE.isActive(entity)) {
      return (ZoanInfo)YomiZoanInfo.INSTANCE;
    }

    
    for (Ability ability : abilityProps.getEquippedAbilities()) {
      
      if (ability != null && ability instanceof IMorphAbility) {

        
        IMorphAbility morphAbility = (IMorphAbility)ability;
        
        if (morphAbility.isTransformationActive(entity))
        {
          
          return morphAbility.getTransformation(); } 
      } 
    } 
    return null;
  }
}


