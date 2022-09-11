package xyz.pixelatedw.mineminenomi.models.blocks;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WantedPosterPackageModel
  extends EntityModel
{
  public ModelRenderer poster1;
  public ModelRenderer poster2;
  public ModelRenderer poster3;
  public ModelRenderer poster4;
  public ModelRenderer poster5;
  public ModelRenderer poster6;
  public ModelRenderer poster7;
  public ModelRenderer package1;
  public ModelRenderer package2;
  public ModelRenderer package3;
  public ModelRenderer package4;
  public ModelRenderer package5;
  public ModelRenderer package6;
  public ModelRenderer package7;
  public ModelRenderer package8;
  public ModelRenderer package9;
  public ModelRenderer package10;
  public ModelRenderer package11;
  public ModelRenderer package12;
  public ModelRenderer parachute;
  public ModelRenderer parachute_cord1;
  public ModelRenderer parachute_cord2;
  public ModelRenderer parachute_cord3;
  public ModelRenderer parachute_cord4;
  public ModelRenderer parachute_cloth1;
  public ModelRenderer parachute_cloth2;
  public ModelRenderer parachute_cloth3;
  public ModelRenderer parachute_cloth4;
  public ModelRenderer parachute_cloth5;
  public ModelRenderer parachute_cloth6;
  public ModelRenderer parachute_cloth7;
  public ModelRenderer parachute_cloth8;
  
  public WantedPosterPackageModel() {
    this.textureWidth = 64;
    this.textureHeight = 64;
    this.package9 = new ModelRenderer((Model)this, 7, 0);
    this.package9.setRotationPoint(0.0F, 21.0F, 0.0F);
    this.package9.addBox(-0.5F, -1.0F, -9.0F, 1.0F, 1.0F, 18.0F, 0.0F);
    this.parachute_cloth8 = new ModelRenderer((Model)this, 5, 30);
    this.parachute_cloth8.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachute_cloth8.addBox(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
    setRotateAngle(this.parachute_cloth8, 0.2617994F, 5.497787F, 0.0F);
    this.parachute_cloth2 = new ModelRenderer((Model)this, 5, 30);
    this.parachute_cloth2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachute_cloth2.addBox(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
    setRotateAngle(this.parachute_cloth2, 0.2617994F, 0.7853982F, 0.0F);
    this.package6 = new ModelRenderer((Model)this, 30, 0);
    this.package6.setRotationPoint(0.0F, 21.0F, 0.0F);
    this.package6.addBox(-7.0F, -1.0F, 4.0F, 14.0F, 1.0F, 1.0F, 0.0F);
    this.parachute = new ModelRenderer((Model)this, 0, 30);
    this.parachute.setRotationPoint(0.0F, 19.0F, 0.0F);
    this.parachute.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F);
    this.poster1 = new ModelRenderer((Model)this, -16, 0);
    this.poster1.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.poster1.addBox(-6.0F, 0.0F, -8.0F, 12.0F, 0.0F, 16.0F, 0.0F);
    this.parachute_cloth7 = new ModelRenderer((Model)this, 5, 30);
    this.parachute_cloth7.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachute_cloth7.addBox(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
    setRotateAngle(this.parachute_cloth7, 0.2617994F, 4.712389F, 0.0F);
    this.package1 = new ModelRenderer((Model)this, 0, 20);
    this.package1.setRotationPoint(0.0F, 21.0F, 0.0F);
    this.package1.addBox(-7.0F, 0.0F, 4.0F, 1.0F, 3.0F, 1.0F, 0.0F);
    this.package10 = new ModelRenderer((Model)this, 7, 0);
    this.package10.setRotationPoint(0.0F, 21.0F, 0.0F);
    this.package10.addBox(-0.5F, 3.0F, -9.0F, 1.0F, 1.0F, 18.0F, 0.0F);
    this.poster5 = new ModelRenderer((Model)this, -16, 0);
    this.poster5.setRotationPoint(0.0F, 22.0F, 0.0F);
    this.poster5.addBox(-6.0F, 0.0F, -8.0F, 12.0F, 0.0F, 16.0F, 0.0F);
    this.parachute_cord1 = new ModelRenderer((Model)this, 0, 30);
    this.parachute_cord1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachute_cord1.addBox(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
    setRotateAngle(this.parachute_cord1, 0.43633232F, 0.0F, 0.0F);
    this.parachute_cloth3 = new ModelRenderer((Model)this, 5, 30);
    this.parachute_cloth3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachute_cloth3.addBox(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
    setRotateAngle(this.parachute_cloth3, 0.2617994F, 1.5707964F, 0.0F);
    this.parachute_cloth6 = new ModelRenderer((Model)this, 5, 30);
    this.parachute_cloth6.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachute_cloth6.addBox(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
    setRotateAngle(this.parachute_cloth6, 0.2617994F, 3.9269907F, 0.0F);
    this.parachute_cord2 = new ModelRenderer((Model)this, 0, 30);
    this.parachute_cord2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachute_cord2.addBox(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
    setRotateAngle(this.parachute_cord2, 0.43633232F, 3.1415927F, 0.0F);
    this.package11 = new ModelRenderer((Model)this, 0, 20);
    this.package11.setRotationPoint(0.0F, 21.0F, 0.0F);
    this.package11.addBox(-0.5F, 0.0F, -9.0F, 1.0F, 3.0F, 1.0F, 0.0F);
    this.package5 = new ModelRenderer((Model)this, 30, 0);
    this.package5.setRotationPoint(0.0F, 21.0F, 0.0F);
    this.package5.addBox(-7.0F, -1.0F, -5.0F, 14.0F, 1.0F, 1.0F, 0.0F);
    this.parachute_cord3 = new ModelRenderer((Model)this, 0, 30);
    this.parachute_cord3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachute_cord3.addBox(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
    setRotateAngle(this.parachute_cord3, 0.43633232F, 1.5707964F, 0.0F);
    this.package4 = new ModelRenderer((Model)this, 0, 20);
    this.package4.setRotationPoint(0.0F, 21.0F, 0.0F);
    this.package4.addBox(6.0F, 0.0F, -5.0F, 1.0F, 3.0F, 1.0F, 0.0F);
    this.poster4 = new ModelRenderer((Model)this, -16, 0);
    this.poster4.setRotationPoint(0.0F, 22.5F, 0.0F);
    this.poster4.addBox(-6.0F, 0.0F, -8.0F, 12.0F, 0.0F, 16.0F, 0.0F);
    this.parachute_cloth1 = new ModelRenderer((Model)this, 5, 30);
    this.parachute_cloth1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachute_cloth1.addBox(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
    setRotateAngle(this.parachute_cloth1, 0.2617994F, 0.0F, 0.0F);
    this.package2 = new ModelRenderer((Model)this, 0, 20);
    this.package2.setRotationPoint(0.0F, 21.0F, 0.0F);
    this.package2.addBox(-7.0F, 0.0F, -5.0F, 1.0F, 3.0F, 1.0F, 0.0F);
    this.poster6 = new ModelRenderer((Model)this, -16, 0);
    this.poster6.setRotationPoint(0.0F, 21.5F, 0.0F);
    this.poster6.addBox(-6.0F, 0.0F, -8.0F, 12.0F, 0.0F, 16.0F, 0.0F);
    this.package8 = new ModelRenderer((Model)this, 30, 0);
    this.package8.setRotationPoint(0.0F, 21.0F, 0.0F);
    this.package8.addBox(-7.0F, 3.0F, 4.0F, 14.0F, 1.0F, 1.0F, 0.0F);
    this.parachute_cloth4 = new ModelRenderer((Model)this, 5, 30);
    this.parachute_cloth4.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachute_cloth4.addBox(-4.0F, -11.5F, -8.0F, 8.0F, 1.0F, 10.0F, 0.0F);
    setRotateAngle(this.parachute_cloth4, 0.2617994F, 2.3561945F, 0.0F);
    this.poster3 = new ModelRenderer((Model)this, -16, 0);
    this.poster3.setRotationPoint(0.0F, 23.0F, 0.0F);
    this.poster3.addBox(-6.0F, 0.0F, -8.0F, 12.0F, 0.0F, 16.0F, 0.0F);
    this.parachute_cord4 = new ModelRenderer((Model)this, 0, 30);
    this.parachute_cord4.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachute_cord4.addBox(0.0F, -11.0F, -1.0F, 1.0F, 12.0F, 1.0F, 0.0F);
    setRotateAngle(this.parachute_cord4, 0.43633232F, -1.5707964F, 0.0F);
    this.poster7 = new ModelRenderer((Model)this, -16, 0);
    this.poster7.setRotationPoint(0.0F, 21.0F, 0.0F);
    this.poster7.addBox(-6.0F, 0.0F, -8.0F, 12.0F, 0.0F, 16.0F, 0.0F);
    this.parachute_cloth5 = new ModelRenderer((Model)this, 5, 30);
    this.parachute_cloth5.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.parachute_cloth5.addBox(-4.0F, -11.5F, -9.0F, 8.0F, 1.0F, 11.0F, 0.0F);
    setRotateAngle(this.parachute_cloth5, 0.2617994F, 3.1415927F, 0.0F);
    this.package7 = new ModelRenderer((Model)this, 30, 0);
    this.package7.setRotationPoint(0.0F, 21.0F, 0.0F);
    this.package7.addBox(-7.0F, 3.0F, -5.0F, 14.0F, 1.0F, 1.0F, 0.0F);
    this.poster2 = new ModelRenderer((Model)this, -16, 0);
    this.poster2.setRotationPoint(0.0F, 23.5F, 0.0F);
    this.poster2.addBox(-6.0F, 0.0F, -8.0F, 12.0F, 0.0F, 16.0F, 0.0F);
    this.package3 = new ModelRenderer((Model)this, 0, 20);
    this.package3.setRotationPoint(0.0F, 21.0F, 0.0F);
    this.package3.addBox(6.0F, 0.0F, 4.0F, 1.0F, 3.0F, 1.0F, 0.0F);
    this.package12 = new ModelRenderer((Model)this, 0, 20);
    this.package12.setRotationPoint(0.0F, 21.0F, 0.0F);
    this.package12.addBox(-0.5F, 0.0F, 8.0F, 1.0F, 3.0F, 1.0F, 0.0F);
    this.parachute.addChild(this.parachute_cloth8);
    this.parachute.addChild(this.parachute_cloth2);
    this.parachute.addChild(this.parachute_cloth7);
    this.parachute.addChild(this.parachute_cord1);
    this.parachute.addChild(this.parachute_cloth3);
    this.parachute.addChild(this.parachute_cloth6);
    this.parachute.addChild(this.parachute_cord2);
    this.parachute.addChild(this.parachute_cord3);
    this.parachute.addChild(this.parachute_cloth1);
    this.parachute.addChild(this.parachute_cloth4);
    this.parachute.addChild(this.parachute_cord4);
    this.parachute.addChild(this.parachute_cloth5);
  }


  
  public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    this.package9.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.package6.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.parachute.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.poster1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.package1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.package10.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.poster5.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.package11.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.package5.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.package4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.poster4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.package2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.poster6.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.package8.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.poster3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.poster7.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.package7.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.poster2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.package3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.package12.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


