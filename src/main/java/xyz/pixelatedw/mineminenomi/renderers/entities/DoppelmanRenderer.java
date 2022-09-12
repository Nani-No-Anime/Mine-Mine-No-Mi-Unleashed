package xyz.pixelatedw.mineminenomi.renderers.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.ability.DoppelmanEntity;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class DoppelmanRenderer
  extends HumanoidRenderer<DoppelmanEntity, BipedModel<DoppelmanEntity>> {
  public DoppelmanRenderer(EntityRendererManager renderManager) {
    super(renderManager, new HumanoidModel(), "doppelman");
  }


  
  public void preRenderCallback(DoppelmanEntity entity, MatrixStack matrixStack, float partialTickTime) {
    float scale = 1.0F + entity.getShadows() / 7.0F;
    
    if (scale < 1.0F) {
      scale = 1.0F;
    }
    matrixStack.scale(scale, scale, scale);
  }





  
  public static class Factory
    implements IRenderFactory<DoppelmanEntity>
  {
    public EntityRenderer<? super DoppelmanEntity> createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer<? super DoppelmanEntity>)new DoppelmanRenderer(manager);
    }
  }
}


