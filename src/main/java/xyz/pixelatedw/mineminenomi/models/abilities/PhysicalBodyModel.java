package xyz.pixelatedw.mineminenomi.models.abilities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class PhysicalBodyModel
  extends EntityModel
{
  public ModelRenderer bipedRightLeg;
  public ModelRenderer bipedBody;
  public ModelRenderer bipedLeftLeg;
  public ModelRenderer bipedHead;
  public ModelRenderer bipedRightArm;
  public ModelRenderer bipedLeftArm;
  
  public PhysicalBodyModel() {
    this.textureWidth = 64;
    this.textureHeight = 64;
    this.bipedRightArm = new ModelRenderer((Model)this, 40, 16);
    this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
    this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
    this.bipedLeftArm = new ModelRenderer((Model)this, 32, 48);
    this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
    this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
    this.bipedBody = new ModelRenderer((Model)this, 16, 16);
    this.bipedBody.setRotationPoint(0.0F, 12.0F, 0.0F);
    this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F);
    this.bipedHead = new ModelRenderer((Model)this, 0, 0);
    this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F);
    setRotateAngle(this.bipedHead, 0.7853982F, 0.0F, 0.0F);
    this.bipedRightLeg = new ModelRenderer((Model)this, 0, 16);
    this.bipedRightLeg.setRotationPoint(-1.9F, 22.0F, 0.0F);
    this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
    setRotateAngle(this.bipedRightLeg, -1.5707964F, 0.0F, 0.0F);
    this.bipedLeftLeg = new ModelRenderer((Model)this, 16, 48);
    this.bipedLeftLeg.setRotationPoint(1.9F, 22.0F, 0.0F);
    this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
    setRotateAngle(this.bipedLeftLeg, -1.5707964F, 0.0F, 0.0F);
    this.bipedBody.addChild(this.bipedRightArm);
    this.bipedBody.addChild(this.bipedLeftArm);
    this.bipedBody.addChild(this.bipedHead);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.bipedBody.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.bipedRightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.bipedLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }


  
  public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


