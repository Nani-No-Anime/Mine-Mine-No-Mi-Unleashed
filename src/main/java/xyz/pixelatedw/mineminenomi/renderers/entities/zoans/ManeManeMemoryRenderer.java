package xyz.pixelatedw.mineminenomi.renderers.entities.zoans;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.abilities.mane.ManeManeMemoryAbility;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.NoMorphModel;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

import java.util.UUID;

public class ManeManeMemoryRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel> extends ZoanMorphRenderer<T, M> {
  public ManeManeMemoryRenderer(EntityRendererManager rendererManager, ZoanInfo info, boolean hasSmallHands) {
    super(rendererManager, info, hasSmallHands);
    this.entityModel = new NoMorphModel(hasSmallHands);
    addLayer((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
  }


  
  public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
  }


  
  public ResourceLocation getEntityTexture(AbstractClientPlayerEntity entity) {
    ManeManeMemoryAbility ability = (ManeManeMemoryAbility)AbilityDataCapability.get((LivingEntity)entity).getEquippedAbility((Ability)ManeManeMemoryAbility.INSTANCE);
    if (ability != null && ability.isTransformationActive((LivingEntity)entity)) {
      
      UUID uuid = ability.getMemory().getUUID();
      PlayerEntity player = entity.world.getPlayerByUuid(uuid);
      if (player != null) {
        return ((AbstractClientPlayerEntity)player).getLocationSkin();
      }
      return DefaultPlayerSkin.getDefaultSkin(uuid);
    } 
    
    return entity.getLocationSkin();
  }
  
  public static class Factory<T extends PlayerEntity>
    implements IRenderFactory<T>
  {
    private ZoanInfo info;
    private boolean hasSmallHands;
    
    public Factory(ZoanInfo info, boolean hasSmallHands) {
      this.info = info;
      this.hasSmallHands = hasSmallHands;
    }


    
    public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
      ManeManeMemoryRenderer<AbstractClientPlayerEntity, ZoanMorphModel> renderer = new ManeManeMemoryRenderer<>(manager, this.info, this.hasSmallHands);
      return (EntityRenderer)renderer;
    }
  }
}


