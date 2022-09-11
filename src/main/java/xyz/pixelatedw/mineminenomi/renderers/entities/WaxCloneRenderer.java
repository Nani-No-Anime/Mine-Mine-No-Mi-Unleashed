package xyz.pixelatedw.mineminenomi.renderers.entities;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.ability.WaxCloneEntity;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;

public class WaxCloneRenderer extends HumanoidRenderer<WaxCloneEntity, BipedModel<WaxCloneEntity>> {
  public static final ResourceLocation WAX_LOCATION = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/candle_lock.png");

  
  public WaxCloneRenderer(EntityRendererManager manager) {
    super(manager, new HumanoidModel());
  }


  
  public ResourceLocation getEntityTexture(WaxCloneEntity entity) {
    if (entity.isTextured()) {
      
      PlayerEntity player = entity.world.getPlayerByUuid(entity.getOwnerUUID());
      if (player != null) {
        return ((AbstractClientPlayerEntity)player).getLocationSkin();
      }
      return DefaultPlayerSkin.getDefaultSkin(entity.getOwnerUUID());
    } 
    
    return WAX_LOCATION;
  }





  
  public static class Factory
    implements IRenderFactory<WaxCloneEntity>
  {
    public EntityRenderer<? super WaxCloneEntity> createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer<? super WaxCloneEntity>)new WaxCloneRenderer(manager);
    }
  }
}


