package xyz.pixelatedw.mineminenomi.renderers.entities.zoans;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bara.BaraFestivalEntity;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.BaraFestivalModel;

@OnlyIn(Dist.CLIENT)
public class BaraFestivalRenderer<T extends BaraFestivalEntity>
  extends EntityRenderer<T> {
  private BaraFestivalModel model = new BaraFestivalModel();

  
  protected BaraFestivalRenderer(EntityRendererManager renderManager) {
    super(renderManager);
    this.shadowSize = 0.0F;
  }


  
  public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    matrixStack.push();
    
    RenderType type = ModRenderTypes.getZoanRenderType(getEntityTexture(entity));
    IVertexBuilder ivertexbuilder = buffer.getBuffer(type);
    this.model.setRotationAngles((BaraFestivalEntity)entity, 0.0F, 0.0F, ((BaraFestivalEntity)entity).ticksExisted, 0.0F, 0.0F);
    this.model.render(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    
    matrixStack.pop();
  }


  
  public ResourceLocation getEntityTexture(T entity) {
    PlayerEntity player = ((BaraFestivalEntity)entity).world.getPlayerByUuid(entity.getOwnerUUID());
    if (player != null) {
      return ((AbstractClientPlayerEntity)player).getLocationSkin();
    }
    return DefaultPlayerSkin.getDefaultSkin(entity.getOwnerUUID());
  }

  
  public static class Factory
    implements IRenderFactory
  {
    public EntityRenderer createRenderFor(EntityRendererManager manager) {
      return new BaraFestivalRenderer<>(manager);
    }
  }
}


