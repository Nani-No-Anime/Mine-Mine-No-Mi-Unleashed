package xyz.pixelatedw.mineminenomi.wypi.abilities.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SphereModel
  extends EntityModel
{
  public ModelRenderer shape1;
  public ModelRenderer shape2;
  public ModelRenderer shape3;
  public ModelRenderer shape4;
  public ModelRenderer shape5;
  public ModelRenderer shape6;
  public ModelRenderer shape7;
  
  public SphereModel() {
    this.textureWidth = 64;
    this.textureHeight = 64;
    this.shape4 = new ModelRenderer((Model)this, 0, 0);
    this.shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.shape4.addBox(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 1.0F, 0.0F);
    this.shape5 = new ModelRenderer((Model)this, 0, 0);
    this.shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.shape5.addBox(-1.5F, -1.5F, 1.5F, 3.0F, 3.0F, 1.0F, 0.0F);
    this.shape7 = new ModelRenderer((Model)this, 0, 0);
    this.shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.shape7.addBox(1.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F);
    this.shape2 = new ModelRenderer((Model)this, 0, 0);
    this.shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.shape2.addBox(-1.5F, -2.5F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F);
    this.shape3 = new ModelRenderer((Model)this, 0, 0);
    this.shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.shape3.addBox(-1.5F, 1.5F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F);
    this.shape6 = new ModelRenderer((Model)this, 0, 0);
    this.shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.shape6.addBox(-2.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F);
    this.shape1 = new ModelRenderer((Model)this, 0, 0);
    this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.shape1.addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F);
    this.shape1.addChild(this.shape4);
    this.shape1.addChild(this.shape5);
    this.shape1.addChild(this.shape7);
    this.shape1.addChild(this.shape2);
    this.shape1.addChild(this.shape3);
    this.shape1.addChild(this.shape6);
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}



  
  public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    this.shape1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
  }

  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


