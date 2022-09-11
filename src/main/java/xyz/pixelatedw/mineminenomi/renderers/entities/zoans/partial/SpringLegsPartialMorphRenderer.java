package xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.renderers.entities.zoans.ZoanMorphRenderer;
import xyz.pixelatedw.mineminenomi.renderers.layers.AuraLayer;
import xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial.SpringLegsLayer;

@OnlyIn(Dist.CLIENT)
public class SpringLegsPartialMorphRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel> extends ZoanMorphRenderer<T, M> {
  public SpringLegsPartialMorphRenderer(EntityRendererManager renderManager, ZoanInfo info) {
    super(renderManager, info);
    SpringLegsLayer partialRenderer = new SpringLegsLayer((IEntityRenderer)this);
    addLayer((LayerRenderer)partialRenderer);
    addLayer((LayerRenderer)new AuraLayer((IEntityRenderer)this));
  }


  
  public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    ((PlayerModel)getEntityModel()).bipedRightLeg.showModel = false;
    ((PlayerModel)getEntityModel()).bipedLeftLeg.showModel = false;
    
    super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
  }
  
  public static class Factory<T extends PlayerEntity>
    implements IRenderFactory<T>
  {
    private ZoanInfo info;
    
    public Factory(ZoanInfo info) {
      this.info = info;
    }


    
    public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
      return (EntityRenderer)new SpringLegsPartialMorphRenderer<>(manager, this.info);
    }
  }
}


