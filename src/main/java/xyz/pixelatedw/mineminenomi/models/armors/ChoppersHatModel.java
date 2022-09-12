package xyz.pixelatedw.mineminenomi.models.armors;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class ChoppersHatModel<T extends LivingEntity>
  extends BipedModel<T> {
  public ModelRenderer base;
  public ModelRenderer base_1;
  
  public ChoppersHatModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 32;
    
    this.base = new ModelRenderer((Model)this);
    this.base.setRotationPoint(0.0F, -1.0F, 0.0F);
    this.base.setTextureOffset(0, 18).addBox(-5.5F, -8.0F, -5.5F, 11.0F, 1.0F, 11.0F, 0.0F, false);
    
    this.base_1 = new ModelRenderer((Model)this);
    this.base_1.setRotationPoint(0.0F, -8.0F, 0.0F);
    this.base.addChild(this.base_1);
    this.base_1.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.base.copyModelAngles(this.bipedHead);
    this.base.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
  }

  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


