package xyz.pixelatedw.mineminenomi.renderers.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.HumandrillEntity;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.HumandrillModel;

public class HumandrillRenderer<T extends HumandrillEntity, M extends BipedModel<T>>
  extends HumanoidRenderer<T, M> {
  public HumandrillRenderer(EntityRendererManager manager) {
    super(manager, (M)new HumandrillModel(), "humandrill");
  }


  
  public void preRenderCallback(T entity, MatrixStack matrixStack, float partialTickTime) {
    matrixStack.scale(entity.getSize(), entity.getSize(), entity.getSize());
    this.shadowSize = entity.getSize() / 1.2F;
  }

  
  public static class Factory
    implements IRenderFactory
  {
    public EntityRenderer createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer)new HumandrillRenderer<>(manager);
    }
  }
}


