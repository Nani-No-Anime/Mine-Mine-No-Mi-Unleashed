package xyz.pixelatedw.mineminenomi.renderers.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.models.abilities.ChainsModel;

public class BindLayer<T extends LivingEntity, M extends EntityModel<T>>
  extends LayerRenderer<T, M>
{
  private static final ChainsModel MODEL = new ChainsModel();

  
  public BindLayer(IEntityRenderer renderer) {
    super(renderer);
  }


  
  public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    if (entity.isPotionActive(ModEffects.BIND)) {
      
      if (entity.isPotionActive(ModEffects.BIND) && entity.getActivePotionEffect(ModEffects.BIND).getDuration() <= 0) {
        entity.removePotionEffect(ModEffects.BIND);
      }
      if (getEntityModel() instanceof net.minecraft.client.renderer.entity.model.BipedModel) {
        
        matrixStack.translate(0.0D, -0.05D, 0.05D);
        MODEL.render(matrixStack, buffer.getBuffer(ModRenderTypes.getAbilityHand(ModResources.CANDLE_LOCK)), packedLight, 0, 0.4F, 0.4F, 0.5F, 1.0F);
      } 
    } 
  }
}


