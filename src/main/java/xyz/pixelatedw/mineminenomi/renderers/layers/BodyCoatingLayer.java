package xyz.pixelatedw.mineminenomi.renderers.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.HandSide;
import xyz.pixelatedw.mineminenomi.abilities.gomu.GearFourthAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiFullBodyHardeningAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;


public class BodyCoatingLayer<T extends LivingEntity, M extends EntityModel<T>>
  extends LayerRenderer<T, M>
{
  public BodyCoatingLayer(IEntityRenderer renderer) {
    super(renderer);
  }


  
  public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    IVertexBuilder vertex = null;


    
    if (entity instanceof PlayerEntity) {
      
      IEntityStats props = EntityStatsCapability.get((LivingEntity)entity);
      IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)entity);
      
      GearFourthAbility g4Ability = (GearFourthAbility)abilityProps.getEquippedAbility((Ability)GearFourthAbility.INSTANCE);
      boolean hasG4 = (g4Ability != null && g4Ability.isContinuous());
      
      for (Ability ability : abilityProps.getEquippedAbilities()) {
        
        if (ability != null && ability.isContinuous() && !ability.isStateForced())
        {
          
          if (ability instanceof IPunchOverlayAbility && getEntityModel() instanceof net.minecraft.client.renderer.entity.model.IHasArm) {
            
            AbilityOverlay overlay = ((IPunchOverlayAbility)ability).getPunchOverlay((LivingEntity)entity);
            float red = overlay.getColor().getRed() / 255.0F;
            float green = overlay.getColor().getGreen() / 255.0F;
            float blue = overlay.getColor().getBlue() / 255.0F;
            float alpha = overlay.getColor().getAlpha() / 255.0F;
            
            if (overlay.getTexture() != null) {
              vertex = buffer.getBuffer(ModRenderTypes.getAbilityHand(overlay.getTexture()));
            } else {
              vertex = overlay.getRenderType().equals(AbilityOverlay.RenderType.ENERGY) ? buffer.getBuffer(ModRenderTypes.ENERGY) : buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
            } 
            if (props.isBlackLeg()) {
              ((PlayerModel)getEntityModel()).bipedRightLegwear.render(matrixStack, vertex, packedLight, packedLight, red, green, blue, alpha);
            } else {
              
              boolean isLeftHanded = false;
              
              if (entity instanceof PlayerEntity) {
                isLeftHanded = (((PlayerEntity)entity).getPrimaryHand() == HandSide.LEFT);
              } else if (entity instanceof MobEntity) {
                isLeftHanded = ((MobEntity)entity).isLeftHanded();
              } 
              if (isLeftHanded) {
                ((PlayerModel)getEntityModel()).bipedLeftArmwear.render(matrixStack, vertex, packedLight, packedLight, red, green, blue, alpha);
              } else {
                ((PlayerModel)getEntityModel()).bipedRightArmwear.render(matrixStack, vertex, packedLight, packedLight, red, green, blue, alpha);
              } 
            } 
          } else if (ability instanceof IBodyOverlayAbility) {
            
            AbilityOverlay overlay = ((IBodyOverlayAbility)ability).getBodyOverlay();
            float red = overlay.getColor().getRed() / 255.0F;
            float green = overlay.getColor().getGreen() / 255.0F;
            float blue = overlay.getColor().getBlue() / 255.0F;
            float alpha = overlay.getColor().getAlpha() / 255.0F;
            
            if (hasG4 && ability instanceof BusoshokuHakiFullBodyHardeningAbility) {
              alpha = 0.0F;
            }
            if (overlay.getTexture() != null) {
              vertex = buffer.getBuffer(ModRenderTypes.getAbilityBody(overlay.getTexture()));
            } else {
              vertex = overlay.getRenderType().equals(AbilityOverlay.RenderType.ENERGY) ? buffer.getBuffer(ModRenderTypes.ENERGY) : buffer.getBuffer(ModRenderTypes.TRANSPARENT_COLOR);
            } 
            getEntityModel().render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
          } 
        }
      } 
    } else if (entity instanceof OPEntity) {
      
      OPEntity opEntity = (OPEntity)entity;
      if (opEntity.hasBusoHaki()) {
        
        AbilityOverlay overlay = BusoshokuHakiHardeningAbility.INSTANCE.getPunchOverlay((LivingEntity)entity);
        float red = overlay.getColor().getRed() / 255.0F;
        float green = overlay.getColor().getGreen() / 255.0F;
        float blue = overlay.getColor().getBlue() / 255.0F;
        float alpha = overlay.getColor().getAlpha() / 255.0F;
        vertex = buffer.getBuffer(ModRenderTypes.getAbilityBody(overlay.getTexture()));
        
        ((BipedModel)getEntityModel()).bipedRightArm.render(matrixStack, vertex, packedLight, packedLight, red, green, blue, alpha + 0.2F);
      }
      else if (opEntity.hasFullbodyHaki()) {
        
        AbilityOverlay overlay = BusoshokuHakiFullBodyHardeningAbility.INSTANCE.getBodyOverlay();
        float red = overlay.getColor().getRed() / 255.0F;
        float green = overlay.getColor().getGreen() / 255.0F;
        float blue = overlay.getColor().getBlue() / 255.0F;
        float alpha = overlay.getColor().getAlpha() / 255.0F;
        vertex = buffer.getBuffer(ModRenderTypes.getAbilityBody(overlay.getTexture()));
        
        getEntityModel().render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha + 0.2F);
      } 
    } 
  }
}


