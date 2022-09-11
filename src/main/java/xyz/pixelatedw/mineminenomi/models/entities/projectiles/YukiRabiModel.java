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
public class YukiRabiModel
  extends EntityModel
{
  public ModelRenderer body1;
  public ModelRenderer body2;
  public ModelRenderer body3;
  public ModelRenderer body4;
  public ModelRenderer rightEar;
  public ModelRenderer leftEar;
  
  public YukiRabiModel() {
    this.textureWidth = 80;
    this.textureHeight = 40;
    this.rightEar = new ModelRenderer((Model)this, 0, 30);
    this.rightEar.setRotationPoint(-1.0F, -1.8F, 4.0F);
    this.rightEar.addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 7.0F, 0.0F);
    setRotateAngle(this.rightEar, 0.15481742F, -0.08593739F, -0.17518608F);
    this.body4 = new ModelRenderer((Model)this, 29, 15);
    this.body4.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.body4.addBox(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F);
    this.leftEar = new ModelRenderer((Model)this, 19, 30);
    this.leftEar.setRotationPoint(1.0F, -1.8F, 4.0F);
    this.leftEar.addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 7.0F, 0.0F);
    setRotateAngle(this.leftEar, 0.15481742F, 0.08593739F, 0.17518608F);
    this.body3 = new ModelRenderer((Model)this, 29, 0);
    this.body3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.body3.addBox(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 8.0F, 0.0F);
    this.body1 = new ModelRenderer((Model)this, 0, 0);
    this.body1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.body1.addBox(-3.5F, -3.5F, -3.5F, 7.0F, 7.0F, 7.0F, 0.0F);
    this.body2 = new ModelRenderer((Model)this, 0, 15);
    this.body2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.body2.addBox(-4.0F, -3.0F, -3.0F, 8.0F, 6.0F, 6.0F, 0.0F);
  }


  
  public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    this.rightEar.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.body4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.leftEar.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.body3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.body1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.body2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


