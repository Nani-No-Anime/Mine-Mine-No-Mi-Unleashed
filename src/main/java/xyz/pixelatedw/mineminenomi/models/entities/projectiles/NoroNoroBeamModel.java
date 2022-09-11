package xyz.pixelatedw.mineminenomi.models.entities.projectiles;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NoroNoroBeamModel
  extends EntityModel
{
  public ModelRenderer circle1;
  public ModelRenderer circle2;
  public ModelRenderer circle3;
  public ModelRenderer circle4;
  public ModelRenderer circle5;
  public ModelRenderer circle6;
  public ModelRenderer circle7;
  public ModelRenderer circle8;
  public ModelRenderer circle9;
  public ModelRenderer circle10;
  public ModelRenderer circle11;
  public ModelRenderer circle12;
  public ModelRenderer pellicle;
  
  public NoroNoroBeamModel() {
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.circle3 = new ModelRenderer((Model)this, 0, 0);
    this.circle3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.circle3.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.circle3, 0.0F, -0.0F, 0.7853982F);
    this.circle1 = new ModelRenderer((Model)this, 0, 0);
    this.circle1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.circle1.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.circle1, 0.0F, -0.0F, -0.2617994F);
    this.circle11 = new ModelRenderer((Model)this, 0, 0);
    this.circle11.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.circle11.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.circle11, 0.0F, -0.0F, -1.3089969F);
    this.circle12 = new ModelRenderer((Model)this, 0, 0);
    this.circle12.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.circle12.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.circle12, 0.0F, -0.0F, -0.7853982F);
    this.pellicle = new ModelRenderer((Model)this, 0, 3);
    this.pellicle.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.pellicle.addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 0.0F, 0.0F);
    this.circle9 = new ModelRenderer((Model)this, 0, 0);
    this.circle9.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.circle9.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.circle9, 0.0F, -0.0F, -2.3561945F);
    this.circle2 = new ModelRenderer((Model)this, 0, 0);
    this.circle2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.circle2.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.circle2, 0.0F, -0.0F, 0.2617994F);
    this.circle10 = new ModelRenderer((Model)this, 0, 0);
    this.circle10.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.circle10.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.circle10, 0.0F, -0.0F, -1.8325957F);
    this.circle7 = new ModelRenderer((Model)this, 0, 0);
    this.circle7.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.circle7.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.circle7, 0.0F, -0.0F, 2.8972466F);
    this.circle5 = new ModelRenderer((Model)this, 0, 0);
    this.circle5.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.circle5.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.circle5, 0.0F, -0.0F, 1.8325957F);
    this.circle8 = new ModelRenderer((Model)this, 0, 0);
    this.circle8.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.circle8.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.circle8, 0.0F, -0.0F, -2.8972466F);
    this.circle4 = new ModelRenderer((Model)this, 0, 0);
    this.circle4.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.circle4.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.circle4, 0.0F, -0.0F, 1.3089969F);
    this.circle6 = new ModelRenderer((Model)this, 0, 0);
    this.circle6.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.circle6.addBox(-1.5F, -5.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.circle6, 0.0F, -0.0F, 2.3561945F);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    matrixStack.push();
    matrixStack.translate(0.0D, -0.15D, 0.0D);
    ImmutableList.of(this.circle3, this.circle1, this.circle11, this.circle12, this.pellicle, this.circle9, this.circle2, this.circle10, this.circle7, this.circle5, this.circle8, this.circle4, new ModelRenderer[] { this.circle6 }).forEach(model -> model.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));

    
    matrixStack.pop();
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


