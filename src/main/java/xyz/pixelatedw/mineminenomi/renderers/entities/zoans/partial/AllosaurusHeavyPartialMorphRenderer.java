package xyz.pixelatedw.mineminenomi.renderers.entities.zoans.partial;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
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
import xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial.AllosaurusHeavyLayer;

@OnlyIn(Dist.CLIENT)
public class AllosaurusHeavyPartialMorphRenderer<T extends AbstractClientPlayerEntity, M extends ZoanMorphModel> extends ZoanMorphRenderer<T, M> {
  public AllosaurusHeavyPartialMorphRenderer(EntityRendererManager renderManager, ZoanInfo info, boolean hasSmallHands) {
    super(renderManager, info, hasSmallHands);
    removeLayer(HeldItemLayer.class);
    AllosaurusHeavyLayer partialRenderer = new AllosaurusHeavyLayer((IEntityRenderer)this);
    addLayer((LayerRenderer)partialRenderer);
    addLayer((LayerRenderer)new HeldItemLayer((IEntityRenderer)this));
    addLayer((LayerRenderer)new AuraLayer((IEntityRenderer)this));
  }


  
  public void render(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    ((PlayerModel)getEntityModel()).bipedRightLeg.showModel = false;
    ((PlayerModel)getEntityModel()).bipedLeftLeg.showModel = false;
    
    ((PlayerModel)getEntityModel()).bipedLeftLegwear.showModel = false;
    ((PlayerModel)getEntityModel()).bipedRightLegwear.showModel = false;
    
    super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
  }


  
  protected void renderModel(AbstractClientPlayerEntity entity, MatrixStack matrixStack, int packedLight, IRenderTypeBuffer buffer, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    matrixStack.push();
    float scale = 1.1F;
    matrixStack.scale(scale, scale, scale);
    matrixStack.translate(0.0D, -0.18D, 0.0D);
    super.renderModel(entity, matrixStack, packedLight, buffer, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
    matrixStack.pop();
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
      return (EntityRenderer)new AllosaurusHeavyPartialMorphRenderer<>(manager, this.info, this.hasSmallHands);
    }
  }
}


