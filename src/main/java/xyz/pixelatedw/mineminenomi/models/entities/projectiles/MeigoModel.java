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
public class MeigoModel
  extends EntityModel
{
  public ModelRenderer leftarm;
  public ModelRenderer leftarm2;
  public ModelRenderer leftarm3;
  public ModelRenderer leftarm4;
  public ModelRenderer leftarm5;
  public ModelRenderer leftarm6;
  public ModelRenderer leftarm7;
  public ModelRenderer lefthand;
  public ModelRenderer leftfinger10;
  public ModelRenderer leftfinger20;
  public ModelRenderer leftfinger30;
  public ModelRenderer leftfinger40;
  public ModelRenderer leftfinger50;
  public ModelRenderer leftfinger11;
  public ModelRenderer leftfinger21;
  public ModelRenderer leftfinger31;
  public ModelRenderer leftfinger41;
  public ModelRenderer leftfinger51;
  
  public MeigoModel() {
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.leftarm3 = new ModelRenderer((Model)this, 0, 21);
    this.leftarm3.setRotationPoint(0.4F, -7.3F, 0.5F);
    this.leftarm3.addBox(-0.8F, 1.0F, -0.9F, 3.0F, 5.0F, 3.0F, 0.0F);
    this.leftfinger51 = new ModelRenderer((Model)this, 17, 10);
    this.leftfinger51.setRotationPoint(-1.0F, 2.7F, -1.7F);
    this.leftfinger51.addBox(0.0F, 0.2F, 0.2F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftfinger51, 1.0471976F, -0.0F, 0.0F);
    this.leftfinger30 = new ModelRenderer((Model)this, 17, 6);
    this.leftfinger30.setRotationPoint(0.0F, 0.1F, 0.4F);
    this.leftfinger30.addBox(-0.8F, 1.0F, -2.2F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftfinger30, -0.43633232F, 0.0F, 0.0F);
    this.leftfinger20 = new ModelRenderer((Model)this, 17, 6);
    this.leftfinger20.setRotationPoint(0.0F, 0.1F, 0.4F);
    this.leftfinger20.addBox(0.4F, 1.0F, -2.2F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftfinger20, -0.43633232F, 0.0F, 0.0F);
    this.leftfinger41 = new ModelRenderer((Model)this, 17, 10);
    this.leftfinger41.setRotationPoint(-1.7F, 3.0F, -2.2F);
    this.leftfinger41.addBox(0.0F, 0.2F, 0.2F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftfinger41, 1.0471976F, -0.0F, 0.0F);
    this.leftarm6 = new ModelRenderer((Model)this, 26, 21);
    this.leftarm6.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftarm6.addBox(-0.7F, 3.5F, -0.7F, 3.0F, 4.0F, 3.0F, 0.0F);
    this.leftfinger50 = new ModelRenderer((Model)this, 17, 6);
    this.leftfinger50.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftfinger50.addBox(-1.0F, 0.6F, -1.5F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftfinger50, -0.43633232F, 2.048842F, 0.4098033F);
    this.leftarm2 = new ModelRenderer((Model)this, 0, 21);
    this.leftarm2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftarm2.addBox(-2.4F, -7.5F, -2.4F, 3.0F, 5.0F, 3.0F, 0.0F);
    this.leftarm7 = new ModelRenderer((Model)this, 26, 21);
    this.leftarm7.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftarm7.addBox(-2.2F, 4.2F, -2.3F, 3.0F, 4.0F, 3.0F, 0.0F);
    this.leftfinger10 = new ModelRenderer((Model)this, 17, 6);
    this.leftfinger10.setRotationPoint(0.0F, 0.1F, 0.4F);
    this.leftfinger10.addBox(1.5F, 1.0F, -2.2F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftfinger10, -0.43633232F, -0.091106184F, 0.0F);
    this.leftfinger21 = new ModelRenderer((Model)this, 17, 10);
    this.leftfinger21.setRotationPoint(0.45F, 3.0F, -2.2F);
    this.leftfinger21.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftfinger21, 1.0471976F, -0.0F, 0.0F);
    this.leftarm4 = new ModelRenderer((Model)this, 13, 21);
    this.leftarm4.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftarm4.addBox(-2.4F, -3.2F, -1.4F, 3.0F, 5.0F, 3.0F, 0.0F);
    this.leftarm5 = new ModelRenderer((Model)this, 13, 21);
    this.leftarm5.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftarm5.addBox(-0.9F, -2.1F, -2.4F, 3.0F, 5.0F, 3.0F, 0.0F);
    this.leftarm = new ModelRenderer((Model)this, 0, 0);
    this.leftarm.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftarm.addBox(-2.0F, -7.0F, -2.0F, 4.0F, 16.0F, 4.0F, 0.0F);
    setRotateAngle(this.leftarm, -1.5707964F, -0.0F, 0.0F);
    this.leftfinger40 = new ModelRenderer((Model)this, 17, 6);
    this.leftfinger40.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftfinger40.addBox(-1.7F, 0.9F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftfinger40, -0.43633232F, 0.18203785F, 0.0F);
    this.lefthand = new ModelRenderer((Model)this, 17, 0);
    this.lefthand.setRotationPoint(-0.5F, 8.5F, 0.0F);
    this.lefthand.addBox(-1.5F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F);
    setRotateAngle(this.lefthand, 0.17453292F, -0.0F, 0.0F);
    this.leftfinger31 = new ModelRenderer((Model)this, 17, 10);
    this.leftfinger31.setRotationPoint(-0.8F, 3.0F, -2.2F);
    this.leftfinger31.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftfinger31, 1.0471976F, -0.0F, 0.0F);
    this.leftfinger11 = new ModelRenderer((Model)this, 17, 10);
    this.leftfinger11.setRotationPoint(1.5F, 3.0F, -2.2F);
    this.leftfinger11.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftfinger11, 1.0471976F, -0.0F, 0.0F);
    this.leftarm.addChild(this.leftarm3);
    this.leftfinger50.addChild(this.leftfinger51);
    this.lefthand.addChild(this.leftfinger30);
    this.lefthand.addChild(this.leftfinger20);
    this.leftfinger40.addChild(this.leftfinger41);
    this.leftarm.addChild(this.leftarm6);
    this.lefthand.addChild(this.leftfinger50);
    this.leftarm.addChild(this.leftarm2);
    this.leftarm.addChild(this.leftarm7);
    this.lefthand.addChild(this.leftfinger10);
    this.leftfinger20.addChild(this.leftfinger21);
    this.leftarm.addChild(this.leftarm4);
    this.leftarm.addChild(this.leftarm5);
    this.lefthand.addChild(this.leftfinger40);
    this.leftarm.addChild(this.lefthand);
    this.leftfinger30.addChild(this.leftfinger31);
    this.leftfinger10.addChild(this.leftfinger11);
  }


  
  public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    this.leftarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


