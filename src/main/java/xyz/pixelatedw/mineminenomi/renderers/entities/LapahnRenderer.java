package xyz.pixelatedw.mineminenomi.renderers.entities;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.LapahnModel;

public class LapahnRenderer<T extends LapahnEntity, M extends LapahnModel<T>>
  extends HumanoidRenderer<T, M> {
  public LapahnRenderer(EntityRendererManager manager) {
    super(manager, (M)new LapahnModel());
    this.shadowSize = 0.8F;
  }


  
  public ResourceLocation getEntityTexture(T entity) {
    if (entity.isEnraged()) {
      return new ResourceLocation("mineminenomi", "textures/models/lapahn_angry.png");
    }
    return new ResourceLocation("mineminenomi", "textures/models/lapahn.png");
  }

  
  public static class Factory
    implements IRenderFactory
  {
    public EntityRenderer createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer)new LapahnRenderer<>(manager);
    }
  }
}


