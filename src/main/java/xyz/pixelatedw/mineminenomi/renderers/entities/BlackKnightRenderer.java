package xyz.pixelatedw.mineminenomi.renderers.entities;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;

@OnlyIn(Dist.CLIENT)
public class BlackKnightRenderer
  extends HumanoidRenderer<BlackKnightEntity, BipedModel<BlackKnightEntity>> {
  public BlackKnightRenderer(EntityRendererManager manager) {
    super(manager, new HumanoidModel());
  }


  
  public ResourceLocation getEntityTexture(BlackKnightEntity entity) {
    AbstractClientPlayerEntity player = (AbstractClientPlayerEntity)entity.getOwner();
    if (player != null) {
      return player.getLocationSkin();
    }
    return DefaultPlayerSkin.getDefaultSkin(entity.getOwnerUUID());
  }



  
  public static class Factory
    implements IRenderFactory<BlackKnightEntity>
  {
    public EntityRenderer<? super BlackKnightEntity> createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer<? super BlackKnightEntity>)new BlackKnightRenderer(manager);
    }
  }
}


