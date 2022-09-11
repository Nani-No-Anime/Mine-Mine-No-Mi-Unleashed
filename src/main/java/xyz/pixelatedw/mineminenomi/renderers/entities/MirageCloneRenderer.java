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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.ability.MirageCloneEntity;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class MirageCloneRenderer
  extends HumanoidRenderer<MirageCloneEntity, BipedModel<MirageCloneEntity>> {
  public MirageCloneRenderer(EntityRendererManager manager) {
    super(manager, new HumanoidModel());
  }


  
  public ResourceLocation getEntityTexture(MirageCloneEntity entity) {
    PlayerEntity player = entity.world.getPlayerByUuid(entity.getOwnerUUID());
    if (player != null) {
      return ((AbstractClientPlayerEntity)player).getLocationSkin();
    }
    return DefaultPlayerSkin.getDefaultSkin(entity.getOwnerUUID());
  }



  
  public static class Factory
    implements IRenderFactory<MirageCloneEntity>
  {
    public EntityRenderer<? super MirageCloneEntity> createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer<? super MirageCloneEntity>)new MirageCloneRenderer(manager);
    }
  }
}


