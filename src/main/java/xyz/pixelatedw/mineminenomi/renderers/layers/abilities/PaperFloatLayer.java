package xyz.pixelatedw.mineminenomi.renderers.layers.abilities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;
import xyz.pixelatedw.mineminenomi.abilities.mini.MiniMiniAbility;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class PaperFloatLayer<T extends LivingEntity, M extends EntityModel<T>>
  extends LayerRenderer<T, M> {
  private CubeModel model = new CubeModel();

  
  public PaperFloatLayer(IEntityRenderer renderer) {
    super(renderer);
  }


  
  public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)entity);
    Ability ability = abilityData.getEquippedAbility((Ability)MiniMiniAbility.INSTANCE);
    boolean hasAbility = (ability != null && ability.isContinuous());
    boolean hasPaper = (entity.getHeldItemMainhand().getItem() == Items.PAPER || entity.getHeldItemOffhand().getItem() == Items.PAPER);
    boolean inAir = (!((LivingEntity)entity).onGround && (entity.getMotion()).y < 0.0D);
    
    if (hasAbility && hasPaper && inAir) {
      
      matrixStack.translate(0.0D, -0.7D, 0.0D);
      matrixStack.rotate(new Quaternion(Vector3f.YP, -((LivingEntity)entity).rotationYaw, true));
      
      matrixStack.scale(2.4F, 0.5F, 2.5F);
      
      RenderType type = ModRenderTypes.TRANSPARENT_COLOR;
      IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
      this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    } 
  }
}


