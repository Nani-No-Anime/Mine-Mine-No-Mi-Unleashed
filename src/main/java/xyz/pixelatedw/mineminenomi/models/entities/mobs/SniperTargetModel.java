package xyz.pixelatedw.mineminenomi.models.entities.mobs;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class SniperTargetModel
  extends BipedModel<LivingEntity>
{
  public ModelRenderer parachute;
  public ModelRenderer target;
  public ModelRenderer parachuteChild;
  public ModelRenderer parachuteChild_1;
  public ModelRenderer parachuteChild_2;
  public ModelRenderer parachuteChild_3;
  public ModelRenderer parachuteChild_4;
  public ModelRenderer parachuteChild_5;
  public ModelRenderer parachuteChild_6;
  public ModelRenderer parachuteChild_7;
  public ModelRenderer parachuteChild_8;
  public ModelRenderer parachuteChild_9;
  public ModelRenderer parachuteChild_10;
  public ModelRenderer parachuteChild_11;
  
  public SniperTargetModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 64;
    this.parachuteChild_4 = new ModelRenderer((Model)this, 5, 30);
    this.parachuteChild_4.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachuteChild_4.addBox(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
    setRotateAngle(this.parachuteChild_4, 0.2617994F, 1.5707964F, 0.0F);
    this.parachuteChild_11 = new ModelRenderer((Model)this, 5, 30);
    this.parachuteChild_11.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachuteChild_11.addBox(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
    setRotateAngle(this.parachuteChild_11, 0.2617994F, 3.1415927F, 0.0F);
    this.parachuteChild_3 = new ModelRenderer((Model)this, 0, 30);
    this.parachuteChild_3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachuteChild_3.addBox(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
    setRotateAngle(this.parachuteChild_3, 0.43633232F, 0.0F, 0.0F);
    this.parachuteChild_9 = new ModelRenderer((Model)this, 5, 30);
    this.parachuteChild_9.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachuteChild_9.addBox(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
    setRotateAngle(this.parachuteChild_9, 0.2617994F, 2.3561945F, 0.0F);
    this.parachuteChild_7 = new ModelRenderer((Model)this, 0, 30);
    this.parachuteChild_7.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachuteChild_7.addBox(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
    setRotateAngle(this.parachuteChild_7, 0.43633232F, 1.5707964F, 0.0F);
    this.parachuteChild_5 = new ModelRenderer((Model)this, 5, 30);
    this.parachuteChild_5.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachuteChild_5.addBox(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
    setRotateAngle(this.parachuteChild_5, 0.2617994F, 3.9269907F, 0.0F);
    this.parachute = new ModelRenderer((Model)this, 0, 30);
    this.parachute.setRotationPoint(0.0F, 19.0F, 0.0F);
    this.parachute.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F);
    this.target = new ModelRenderer((Model)this, 0, 0);
    this.target.setRotationPoint(0.0F, 20.0F, 0.0F);
    this.target.addBox(-4.0F, 0.0F, -0.5F, 8.0F, 8.0F, 1.0F, 0.0F);
    this.parachuteChild_1 = new ModelRenderer((Model)this, 5, 30);
    this.parachuteChild_1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachuteChild_1.addBox(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
    setRotateAngle(this.parachuteChild_1, 0.2617994F, 0.7853982F, 0.0F);
    this.parachuteChild_10 = new ModelRenderer((Model)this, 0, 30);
    this.parachuteChild_10.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachuteChild_10.addBox(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
    setRotateAngle(this.parachuteChild_10, 0.43633232F, -1.5707964F, 0.0F);
    this.parachuteChild_8 = new ModelRenderer((Model)this, 5, 30);
    this.parachuteChild_8.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachuteChild_8.addBox(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
    setRotateAngle(this.parachuteChild_8, 0.2617994F, 0.0F, 0.0F);
    this.parachuteChild = new ModelRenderer((Model)this, 5, 30);
    this.parachuteChild.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachuteChild.addBox(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
    setRotateAngle(this.parachuteChild, 0.2617994F, 5.497787F, 0.0F);
    this.parachuteChild_2 = new ModelRenderer((Model)this, 5, 30);
    this.parachuteChild_2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachuteChild_2.addBox(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
    setRotateAngle(this.parachuteChild_2, 0.2617994F, 4.712389F, 0.0F);
    this.parachuteChild_6 = new ModelRenderer((Model)this, 0, 30);
    this.parachuteChild_6.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachuteChild_6.addBox(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
    setRotateAngle(this.parachuteChild_6, 0.43633232F, 3.1415927F, 0.0F);
    this.parachute.addChild(this.parachuteChild_4);
    this.parachute.addChild(this.parachuteChild_11);
    this.parachute.addChild(this.parachuteChild_3);
    this.parachute.addChild(this.parachuteChild_9);
    this.parachute.addChild(this.parachuteChild_7);
    this.parachute.addChild(this.parachuteChild_5);
    this.parachute.addChild(this.parachuteChild_1);
    this.parachute.addChild(this.parachuteChild_10);
    this.parachute.addChild(this.parachuteChild_8);
    this.parachute.addChild(this.parachuteChild);
    this.parachute.addChild(this.parachuteChild_2);
    this.parachute.addChild(this.parachuteChild_6);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.parachute.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.target.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }

  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


