package xyz.pixelatedw.mineminenomi.renderers.entities;

import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.dispenser.IPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.SeaKingEntity;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.SeaKingModel;

public class SeaKingRenderer<T extends SeaKingEntity, M extends SeaKingModel<T>> extends HumanoidRenderer<T, M> {
  public SeaKingRenderer(EntityRendererManager manager) {
    super(manager, (M)new SeaKingModel());
    this.scale = new float[] { 1.0F, 1.0F, 1.0F };
    this.shadowSize = 0.7F;
  }

  
  public ResourceLocation getEntityTexture(T entity) {
    return new ResourceLocation("mineminenomi", "textures/models/lapahn.png");
  }

  
  public boolean shouldRender(T entity, ClippingHelperImpl p_225626_2_, double camX, double camY, double camZ) {
    if (entity.getPosition().withinDistance((IPosition)new Vec3d(camX, camY, camZ), 100.0D))
    {
      return true;
    }
    return super.shouldRender(entity, p_225626_2_, camX, camY, camZ);
  }

  
  public static class Factory
    implements IRenderFactory
  {
    public EntityRenderer createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer)new SeaKingRenderer<>(manager);
    }
  }
}


