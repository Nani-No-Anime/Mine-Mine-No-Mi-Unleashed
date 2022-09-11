package xyz.pixelatedw.mineminenomi.renderers.abilities;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class EmptyRenderer<T extends Entity>
  extends EntityRenderer<T>
{
  public EmptyRenderer(EntityRendererManager renderManager) {
    super(renderManager);
  }


  
  public ResourceLocation getEntityTexture(T entity) {
    return null;
  }



  
  public static class Factory
    implements IRenderFactory
  {
    public EntityRenderer<? super Entity> createRenderFor(EntityRendererManager manager) {
      return new EmptyRenderer<>(manager);
    }
  }
}


