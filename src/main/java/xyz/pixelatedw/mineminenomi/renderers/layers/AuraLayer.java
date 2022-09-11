package xyz.pixelatedw.mineminenomi.renderers.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.OutlineLayerBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;

import net.minecraft.entity.LivingEntity;
import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;




public class AuraLayer<T extends LivingEntity, M extends EntityModel<T>>
  extends LayerRenderer<T, M>
{
  public AuraLayer(IEntityRenderer renderer) {
    super(renderer);
  }


  
  public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
    
    if (entity == clientPlayerEntity) {
      return;
    }
    IEntityStats entityData = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
    IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
    IHakiData hakiProps = HakiDataCapability.get((LivingEntity)clientPlayerEntity);
    
    Ability ability = abilityData.getEquippedAbility((Ability)KenbunshokuHakiAuraAbility.INSTANCE);
    boolean isActive = (ability != null && ability.isContinuous());
    
    if (isActive) {
      
      float dorikiPower = entityData.getDoriki() / 1000.0F;
      float hakiPower = hakiProps.getKenbunshokuHakiExp() / 2.0F;
      float finalPower = (dorikiPower + hakiPower) * 1.06F * (DevilFruitCapability.get((LivingEntity)clientPlayerEntity).hasDevilFruit(ModAbilities.GORO_GORO_NO_MI) ? 4 : 1);
      
      if (entity.getDistance(clientPlayerEntity) > finalPower) {
        return;
      }
      matrixStack.push();
      
      String color = "#5555FF";
      
      if (entity instanceof net.minecraft.entity.passive.AnimalEntity) {
        color = "#55FF55";
      } else if (entity instanceof net.minecraft.entity.monster.MonsterEntity) {
        color = "#FF0000";
      } else if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
        color = "#00FFFF";
      } 
      OutlineLayerBuffer outline = Minecraft.getInstance().getRenderTypeBuffers().getOutlineBufferSource();
      Color rgbColor = WyHelper.hexToRGB(color);
      float red = rgbColor.getRed() / 255.0F;
      float green = rgbColor.getGreen() / 255.0F;
      float blue = rgbColor.getBlue() / 255.0F;
      outline.setColor((int)(red * 255.0F), (int)(green * 255.0F), (int)(blue * 255.0F), 200);
      IVertexBuilder vertex = outline.getBuffer(ModRenderTypes.getAuraRenderType(getEntityTexture(entity)));
      
      getEntityModel().render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, 0.6F);
      
      matrixStack.pop();
    } 
  }
}


