package xyz.pixelatedw.mineminenomi.models.abilities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SoapDefenseModel
  extends EntityModel
{
  public ModelRenderer shape1;
  public ModelRenderer shape2;
  public ModelRenderer shape3;
  
  public SoapDefenseModel() {
    this.textureWidth = 128;
    this.textureHeight = 128;
    this.shape1 = new ModelRenderer((Model)this, 0, 0);
    this.shape1.setRotationPoint(0.0F, 9.0F, 0.0F);
    this.shape1.addBox(-10.0F, -15.0F, -7.0F, 20.0F, 30.0F, 15.0F, 0.0F);
    this.shape2 = new ModelRenderer((Model)this, 0, 83);
    this.shape2.setRotationPoint(0.0F, -1.0F, 0.0F);
    this.shape2.addBox(-9.0F, -15.0F, -6.0F, 18.0F, 32.0F, 13.0F, 0.0F);
    this.shape3 = new ModelRenderer((Model)this, 56, 47);
    this.shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.shape3.addBox(-11.0F, -14.0F, -6.0F, 22.0F, 28.0F, 13.0F, 0.0F);
    this.shape1.addChild(this.shape2);
    this.shape1.addChild(this.shape3);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    matrixStack.push();
    matrixStack.scale(1.2F, 1.2F, 1.2F);
    this.shape1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    matrixStack.pop();
  }



  
  public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


