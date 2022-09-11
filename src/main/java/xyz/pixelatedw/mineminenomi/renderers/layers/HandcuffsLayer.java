package xyz.pixelatedw.mineminenomi.renderers.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.models.HandcuffModel;

public class HandcuffsLayer<T extends LivingEntity, M extends EntityModel<T>>
  extends LayerRenderer<T, M>
{
  private static final HandcuffModel MODEL = new HandcuffModel();

  
  public HandcuffsLayer(IEntityRenderer renderer) {
    super(renderer);
  }


  
  public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    if (entity.isPotionActive(ModEffects.HANDCUFFED) || entity.isPotionActive(ModEffects.HANDCUFFED_KAIROSEKI)) {
      
      if (entity.isPotionActive(ModEffects.HANDCUFFED) && entity.getActivePotionEffect(ModEffects.HANDCUFFED).getDuration() <= 0) {
        entity.removePotionEffect(ModEffects.HANDCUFFED);
      }
      if (entity.isPotionActive(ModEffects.HANDCUFFED_KAIROSEKI) && entity.getActivePotionEffect(ModEffects.HANDCUFFED_KAIROSEKI).getDuration() <= 0) {
        entity.removePotionEffect(ModEffects.HANDCUFFED_KAIROSEKI);
      }
      ResourceLocation res = ModResources.HANDCUFFS;
      
      if (entity.isPotionActive(ModEffects.HANDCUFFED)) {
        res = ModResources.HANDCUFFS;
      } else if (entity.isPotionActive(ModEffects.HANDCUFFED_KAIROSEKI)) {
        res = ModResources.KAIROSEKI_HANDCUFFS;
      } 
      if (getEntityModel() instanceof net.minecraft.client.renderer.entity.model.BipedModel) {
        
        matrixStack.translate(0.0D, -0.05D, 0.05D);
        MODEL.render(matrixStack, buffer.getBuffer(ModRenderTypes.getAbilityHand(res)), packedLight, 0, 1.0F, 1.0F, 1.0F, 1.0F);
      } 
    } 
  }
}


