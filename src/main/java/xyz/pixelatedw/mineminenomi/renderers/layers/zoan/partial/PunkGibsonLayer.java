package xyz.pixelatedw.mineminenomi.renderers.layers.zoan.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.models.abilities.PunkGibsonModel;

public class PunkGibsonLayer<T extends LivingEntity, M extends EntityModel<T>>
  extends LayerRenderer<T, M>
{
  private PunkGibsonModel model = new PunkGibsonModel();
  public static final ResourceLocation TEXTURE = new ResourceLocation("mineminenomi", "textures/models/zoanmorph/punk_gibson.png");

  
  public PunkGibsonLayer(IEntityRenderer renderer) {
    super(renderer);
  }


  
  public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    BipedModel ogModel = (BipedModel)getEntityModel();
    
    matrixStack.push();

    
    matrixStack.translate(0.0D, 0.0D, -0.1D);
    this.model.rightArm.copyModelAngles(ogModel.bipedRightArm);
    RenderType renderType = ModRenderTypes.getZoanWithCullingRenderType(TEXTURE);
    this.model.rightArm.rotationPointX = (float)(this.model.rightArm.rotationPointX + 2.3D);
    this.model.rightArm.rotationPointY -= 2.0F;
    this.model.rightArm.rotateAngleX = (float)(this.model.rightArm.rotateAngleX + Math.toRadians(-120.0D));
    this.model.rightArm.rotateAngleY = (float)(this.model.rightArm.rotateAngleY + Math.toRadians(20.0D));
    this.model.rightArm.rotateAngleZ = (float)(this.model.rightArm.rotateAngleZ + Math.toRadians(-20.0D));
    this.model.render(matrixStack, buffer.getBuffer(renderType), packedLight, packedLight, packedLight, ((LivingEntity)entity).rotationYaw, partialTicks, packedLight);
    
    matrixStack.pop();
  }

  
  public PunkGibsonModel getPartialModel() {
    return this.model;
  }
}


