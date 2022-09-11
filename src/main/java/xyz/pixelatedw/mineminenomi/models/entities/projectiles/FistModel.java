package xyz.pixelatedw.mineminenomi.models.entities.projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FistModel
  extends EntityModel
{
  public ModelRenderer arm;
  public ModelRenderer righthand1;
  public ModelRenderer righthand2;
  public ModelRenderer rightfinger10;
  public ModelRenderer rightfinger20;
  public ModelRenderer rightfinger30;
  public ModelRenderer rightfinger50;
  public ModelRenderer rightfinger40;
  public ModelRenderer elbow;
  public ModelRenderer rightfinger11;
  public ModelRenderer rightfinger21;
  public ModelRenderer rightfinger31;
  public ModelRenderer rightfinger41;
  
  public FistModel() {
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.arm = new ModelRenderer((Model)this, 0, 0);
    this.arm.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.arm.addBox(-2.0F, -2.0F, -5.0F, 0.0F, 0.0F, 1.0F, 0.0F);
    this.rightfinger50 = new ModelRenderer((Model)this, 17, 14);
    this.rightfinger50.setRotationPoint(2.3F, 0.5F, -8.1F);
    this.rightfinger50.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightfinger50, 0.06405358F, 0.9545206F, 1.6947147F);
    this.rightfinger11 = new ModelRenderer((Model)this, 17, 10);
    this.rightfinger11.setRotationPoint(0.0F, 1.8F, -0.3F);
    this.rightfinger11.addBox(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightfinger11, 1.8675023F, 0.0F, 0.0F);
    this.rightfinger30 = new ModelRenderer((Model)this, 17, 6);
    this.rightfinger30.setRotationPoint(0.0F, -0.5F, -9.5F);
    this.rightfinger30.addBox(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightfinger30, 0.0F, -0.05235988F, 0.0F);
    this.rightfinger21 = new ModelRenderer((Model)this, 17, 10);
    this.rightfinger21.setRotationPoint(0.0F, 1.8F, -0.3F);
    this.rightfinger21.addBox(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightfinger21, 1.8675023F, 0.0F, 0.0F);
    this.rightfinger41 = new ModelRenderer((Model)this, 17, 10);
    this.rightfinger41.setRotationPoint(0.0F, 1.8F, -0.3F);
    this.rightfinger41.addBox(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightfinger41, 1.8849556F, 0.0F, 0.0F);
    this.righthand2 = new ModelRenderer((Model)this, 28, 0);
    this.righthand2.setRotationPoint(0.9F, -0.5F, -5.9F);
    this.righthand2.addBox(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 2.0F, 0.0F);
    setRotateAngle(this.righthand2, -1.5707964F, -0.43633232F, 0.0F);
    this.rightfinger40 = new ModelRenderer((Model)this, 17, 6);
    this.rightfinger40.setRotationPoint(1.0F, -0.5F, -9.5F);
    this.rightfinger40.addBox(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightfinger40, 0.0F, -0.08726646F, 0.0F);
    this.rightfinger10 = new ModelRenderer((Model)this, 17, 6);
    this.rightfinger10.setRotationPoint(-1.0F, -0.5F, -9.5F);
    this.rightfinger10.addBox(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightfinger10, 0.0F, 0.08726646F, 0.0F);
    this.elbow = new ModelRenderer((Model)this, 0, 0);
    this.elbow.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.elbow.addBox(-2.0F, -4.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F);
    setRotateAngle(this.elbow, -1.5707964F, 0.0F, 0.0F);
    this.rightfinger20 = new ModelRenderer((Model)this, 17, 6);
    this.rightfinger20.setRotationPoint(0.0F, -0.5F, -9.5F);
    this.rightfinger20.addBox(-1.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightfinger20, 0.0F, 0.05235988F, 0.0F);
    this.rightfinger31 = new ModelRenderer((Model)this, 17, 10);
    this.rightfinger31.setRotationPoint(0.0F, 1.8F, -0.3F);
    this.rightfinger31.addBox(0.0F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightfinger31, 1.8849556F, 0.0F, 0.0F);
    this.righthand1 = new ModelRenderer((Model)this, 17, 0);
    this.righthand1.setRotationPoint(0.0F, -1.0F, -5.0F);
    this.righthand1.addBox(-2.0F, 0.0F, -0.5F, 4.0F, 5.0F, 1.0F, 0.0F);
    setRotateAngle(this.righthand1, -1.5707964F, -0.0F, 0.0F);
    this.arm.addChild(this.rightfinger50);
    this.rightfinger10.addChild(this.rightfinger11);
    this.arm.addChild(this.rightfinger30);
    this.rightfinger20.addChild(this.rightfinger21);
    this.rightfinger40.addChild(this.rightfinger41);
    this.arm.addChild(this.righthand2);
    this.arm.addChild(this.rightfinger40);
    this.arm.addChild(this.rightfinger10);
    this.arm.addChild(this.elbow);
    this.arm.addChild(this.rightfinger20);
    this.rightfinger30.addChild(this.rightfinger31);
    this.arm.addChild(this.righthand1);
  }


  
  public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    this.arm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


