package xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.partial.AllosaurusHeavyPartialModel;

public class AllosaurusHeavyLayer<T extends LivingEntity, M extends EntityModel<T>>
  extends LayerRenderer<T, M>
{
  private AllosaurusHeavyPartialModel model = new AllosaurusHeavyPartialModel();
  private ResourceLocation texture = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/ryu_ryu_allosaurus_heavy.png");

  
  public AllosaurusHeavyLayer(IEntityRenderer renderer) {
    super(renderer);
  }


  
  public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    BipedModel ogModel = (BipedModel)getEntityModel();
    
    this.model.bodyScales.copyModelAngles(ogModel.bipedBody);
    this.model.headScales.copyModelAngles(ogModel.bipedHead);
    this.model.rightArmScales.copyModelAngles(ogModel.bipedRightArm);
    this.model.leftArmScales.copyModelAngles(ogModel.bipedLeftArm);
    
    RenderType renderType = ModRenderTypes.getZoanWithCullingRenderType(this.texture);
    this.model.isSneak = entity.isCrouching();
    this.model.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, packedLight, packedLight, ((LivingEntity)entity).rotationYaw, partialTicks, packedLight);
  }

  
  public AllosaurusHeavyPartialModel getPartialModel() {
    return this.model;
  }
}


