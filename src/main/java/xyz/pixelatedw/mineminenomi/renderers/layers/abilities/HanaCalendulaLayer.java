package xyz.pixelatedw.mineminenomi.renderers.layers.abilities;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.abilities.hana.VeinteFleurCalendulaAbility;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.models.abilities.HanaCalendulaModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class HanaCalendulaLayer<T extends LivingEntity, M extends EntityModel<T>>
  extends LayerRenderer<T, M> {
  private HanaCalendulaModel model = new HanaCalendulaModel();

  
  public HanaCalendulaLayer(IEntityRenderer renderer) {
    super(renderer);
  }


  
  public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)entity);
    Ability ability = abilityData.getEquippedAbility((Ability)VeinteFleurCalendulaAbility.INSTANCE);
    boolean hasAbility = (ability != null && ability.isContinuous());
    
    if (hasAbility) {
      
      ResourceLocation skin = ((AbstractClientPlayerEntity)entity).getLocationSkin();
      RenderType renderType = ModRenderTypes.getZoanRenderType(skin);
      this.model.isSneak = entity.isCrouching();
      this.model.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
      this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    } 
  }
}


