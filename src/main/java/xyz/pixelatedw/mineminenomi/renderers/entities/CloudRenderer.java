package xyz.pixelatedw.mineminenomi.renderers.entities;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;

public class CloudRenderer
  extends EntityRenderer<EntityCloud>
{
  protected CloudRenderer(EntityRendererManager manager) {
    super(manager);
  }


  
  public ResourceLocation getEntityTexture(EntityCloud entity) {
    return null;
  }



  
  public static class Factory
    implements IRenderFactory<EntityCloud>
  {
    public EntityRenderer<? super EntityCloud> createRenderFor(EntityRendererManager manager) {
      return new CloudRenderer(manager);
    }
  }
}


