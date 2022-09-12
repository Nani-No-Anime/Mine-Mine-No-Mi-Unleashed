package xyz.pixelatedw.mineminenomi.models.armors;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class StrawHatModel<T extends LivingEntity>
  extends BipedModel<T> {
  private final ModelRenderer base;
  private final ModelRenderer middle;
  private final ModelRenderer top;
  
  public StrawHatModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.base = new ModelRenderer((Model)this);
    this.base.setRotationPoint(0.0F, 30.0F, 0.0F);
    this.base.setTextureOffset(0, 0).addBox(-7.0F, -7.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
    
    this.middle = new ModelRenderer((Model)this);
    this.middle.setRotationPoint(0.0F, -7.0F, 0.0F);
    this.base.addChild(this.middle);
    this.middle.setTextureOffset(0, 17).addBox(-5.0F, -2.25F, -5.0F, 10.0F, 3.0F, 10.0F, 0.0F, false);
    
    this.top = new ModelRenderer((Model)this);
    this.top.setRotationPoint(0.0F, -3.0F, 0.0F);
    this.middle.addChild(this.top);
    this.top.setTextureOffset(0, 32).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.base.copyModelAngles(this.bipedHead);
    this.base.render(matrixStack, buffer, packedLight, packedOverlay);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
  }

  
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


