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
public class PawModel
  extends EntityModel
{
  public ModelRenderer pawA1;
  public ModelRenderer pawA2;
  public ModelRenderer pawA3;
  public ModelRenderer pawA4;
  public ModelRenderer pawA5;
  public ModelRenderer pawB1;
  public ModelRenderer pawB2;
  public ModelRenderer pawB3;
  public ModelRenderer pawB4;
  public ModelRenderer pawC1;
  public ModelRenderer pawC2;
  public ModelRenderer pawC3;
  public ModelRenderer pawC4;
  public ModelRenderer pawD1;
  public ModelRenderer pawD2;
  public ModelRenderer pawD3;
  public ModelRenderer pawD4;
  public ModelRenderer pawE1;
  public ModelRenderer pawE2;
  public ModelRenderer pawE3;
  public ModelRenderer pawE4;
  
  public PawModel() {
    this.textureWidth = 160;
    this.textureHeight = 80;
    this.pawE2 = new ModelRenderer((Model)this, 94, 34);
    this.pawE2.setRotationPoint(9.5F, -10.0F, 0.0F);
    this.pawE2.addBox(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F);
    setRotateAngle(this.pawE2, 0.0F, -0.0F, 0.7853982F);
    this.pawA4 = new ModelRenderer((Model)this, 0, 25);
    this.pawA4.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.pawA4.addBox(-8.0F, -7.0F, -4.0F, 16.0F, 14.0F, 8.0F, 0.0F);
    this.pawC3 = new ModelRenderer((Model)this, 111, 25);
    this.pawC3.setRotationPoint(-3.0F, -12.0F, 0.0F);
    this.pawC3.addBox(-2.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F);
    setRotateAngle(this.pawC3, 0.0F, -0.0F, -0.08726646F);
    this.pawD1 = new ModelRenderer((Model)this, 94, 25);
    this.pawD1.setRotationPoint(3.0F, -12.0F, 0.0F);
    this.pawD1.addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F);
    setRotateAngle(this.pawD1, 0.0F, -0.0F, 0.08726646F);
    this.pawB1 = new ModelRenderer((Model)this, 94, 25);
    this.pawB1.setRotationPoint(-9.5F, -10.0F, 0.0F);
    this.pawB1.addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F);
    setRotateAngle(this.pawB1, 0.0F, -0.0F, -0.7853982F);
    this.pawA5 = new ModelRenderer((Model)this, 49, 25);
    this.pawA5.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.pawA5.addBox(-7.0F, -8.0F, -4.0F, 14.0F, 16.0F, 8.0F, 0.0F);
    this.pawD2 = new ModelRenderer((Model)this, 94, 34);
    this.pawD2.setRotationPoint(3.0F, -12.0F, 0.0F);
    this.pawD2.addBox(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F);
    setRotateAngle(this.pawD2, 0.0F, -0.0F, 0.08726646F);
    this.pawD4 = new ModelRenderer((Model)this, 111, 34);
    this.pawD4.setRotationPoint(3.0F, -12.0F, 0.0F);
    this.pawD4.addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
    setRotateAngle(this.pawD4, 0.0F, -0.0F, 0.08726646F);
    this.pawC1 = new ModelRenderer((Model)this, 94, 25);
    this.pawC1.setRotationPoint(-3.0F, -12.0F, 0.0F);
    this.pawC1.addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F);
    setRotateAngle(this.pawC1, 0.0F, -0.0F, -0.08726646F);
    this.pawA1 = new ModelRenderer((Model)this, 0, 0);
    this.pawA1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.pawA1.addBox(-7.5F, -7.5F, -4.5F, 15.0F, 15.0F, 9.0F, 0.0F);
    this.pawA2 = new ModelRenderer((Model)this, 49, 0);
    this.pawA2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.pawA2.addBox(-7.0F, -7.0F, -5.0F, 14.0F, 14.0F, 10.0F, 0.0F);
    this.pawE3 = new ModelRenderer((Model)this, 111, 25);
    this.pawE3.setRotationPoint(9.5F, -10.0F, 0.0F);
    this.pawE3.addBox(-2.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F);
    setRotateAngle(this.pawE3, 0.0F, -0.0F, 0.7853982F);
    this.pawC2 = new ModelRenderer((Model)this, 94, 34);
    this.pawC2.setRotationPoint(-3.0F, -12.0F, 0.0F);
    this.pawC2.addBox(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F);
    setRotateAngle(this.pawC2, 0.0F, -0.0F, -0.08726646F);
    this.pawB4 = new ModelRenderer((Model)this, 111, 34);
    this.pawB4.setRotationPoint(-9.5F, -10.0F, 0.0F);
    this.pawB4.addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
    setRotateAngle(this.pawB4, 0.0F, -0.0F, -0.7853982F);
    this.pawD3 = new ModelRenderer((Model)this, 111, 25);
    this.pawD3.setRotationPoint(3.0F, -12.0F, 0.0F);
    this.pawD3.addBox(-2.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F);
    setRotateAngle(this.pawD3, 0.0F, -0.0F, 0.08726646F);
    this.pawC4 = new ModelRenderer((Model)this, 111, 34);
    this.pawC4.setRotationPoint(-3.0F, -12.0F, 0.0F);
    this.pawC4.addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
    setRotateAngle(this.pawC4, 0.0F, -0.0F, -0.08726646F);
    this.pawB3 = new ModelRenderer((Model)this, 111, 25);
    this.pawB3.setRotationPoint(-9.5F, -10.0F, 0.0F);
    this.pawB3.addBox(-2.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F);
    setRotateAngle(this.pawB3, 0.0F, -0.0F, -0.7853982F);
    this.pawB2 = new ModelRenderer((Model)this, 94, 34);
    this.pawB2.setRotationPoint(-9.5F, -10.0F, 0.0F);
    this.pawB2.addBox(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F);
    setRotateAngle(this.pawB2, 0.0F, -0.0F, -0.7853982F);
    this.pawE1 = new ModelRenderer((Model)this, 94, 25);
    this.pawE1.setRotationPoint(9.5F, -10.0F, 0.0F);
    this.pawE1.addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F);
    setRotateAngle(this.pawE1, 0.0F, -0.0F, 0.7853982F);
    this.pawA3 = new ModelRenderer((Model)this, 98, 0);
    this.pawA3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.pawA3.addBox(-6.5F, -6.5F, -5.5F, 13.0F, 13.0F, 11.0F, 0.0F);
    this.pawE4 = new ModelRenderer((Model)this, 111, 34);
    this.pawE4.setRotationPoint(9.5F, -10.0F, 0.0F);
    this.pawE4.addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F);
    setRotateAngle(this.pawE4, 0.0F, -0.0F, 0.7853982F);
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    ImmutableList.of(this.pawE2, this.pawA4, this.pawC3, this.pawD1, this.pawB1, this.pawA5, this.pawD2, this.pawD4, this.pawC1, this.pawA1, this.pawA2, this.pawE3, new ModelRenderer[] { this.pawC2, this.pawB4, this.pawD3, this.pawC4, this.pawB3, this.pawB2, this.pawE1, this.pawA3, this.pawE4 }).forEach(model -> model.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha));
  }




  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


