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
public class SharkModel
  extends EntityModel
{
  public ModelRenderer head1;
  public ModelRenderer head2;
  public ModelRenderer head3;
  public ModelRenderer head4;
  public ModelRenderer teeth1;
  public ModelRenderer teeth2;
  public ModelRenderer body1;
  public ModelRenderer body2;
  public ModelRenderer body3;
  public ModelRenderer body4;
  public ModelRenderer body5;
  public ModelRenderer tail1;
  public ModelRenderer tail2;
  public ModelRenderer tail3;
  public ModelRenderer tail4;
  public ModelRenderer fin1;
  public ModelRenderer rightFin;
  public ModelRenderer leftFin;
  
  public SharkModel() {
    this.textureWidth = 128;
    this.textureHeight = 64;
    this.fin1 = new ModelRenderer((Model)this, 70, 0);
    this.fin1.setRotationPoint(0.0F, -2.5F, -2.0F);
    this.fin1.addBox(-0.5F, -1.5F, 0.0F, 1.0F, 3.0F, 5.0F, 0.0F);
    setRotateAngle(this.fin1, 0.55850536F, -0.0F, 0.0F);
    this.tail3 = new ModelRenderer((Model)this, 0, 58);
    this.tail3.setRotationPoint(0.0F, 1.5F, 13.0F);
    this.tail3.addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F);
    setRotateAngle(this.tail3, -2.0594885F, -0.0F, 0.0F);
    this.body4 = new ModelRenderer((Model)this, 0, 38);
    this.body4.setRotationPoint(0.0F, 0.0F, 10.5F);
    this.body4.addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 3.0F, 0.0F);
    this.teeth1 = new ModelRenderer((Model)this, 37, 29);
    this.teeth1.setRotationPoint(0.0F, 0.6F, -5.9F);
    this.teeth1.addBox(-2.5F, -0.5F, -6.0F, 5.0F, 1.0F, 6.0F, 0.0F);
    this.tail1 = new ModelRenderer((Model)this, 0, 58);
    this.tail1.setRotationPoint(0.0F, -1.5F, 13.0F);
    this.tail1.addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F);
    setRotateAngle(this.tail1, -0.9773844F, -0.0F, 0.0F);
    this.tail4 = new ModelRenderer((Model)this, 7, 58);
    this.tail4.setRotationPoint(0.0F, 3.8F, 14.8F);
    this.tail4.addBox(-0.5F, -3.0F, -1.5F, 1.0F, 3.0F, 1.0F, 0.0F);
    setRotateAngle(this.tail4, -0.7853982F, -0.0F, 0.0F);
    this.body2 = new ModelRenderer((Model)this, 0, 16);
    this.body2.setRotationPoint(0.0F, 0.0F, 3.5F);
    this.body2.addBox(-3.5F, -3.5F, 0.0F, 7.0F, 7.0F, 4.0F, 0.0F);
    this.leftFin = new ModelRenderer((Model)this, 83, 5);
    this.leftFin.setRotationPoint(3.5F, 2.0F, -2.0F);
    this.leftFin.addBox(0.0F, 0.0F, 0.0F, 5.0F, 1.0F, 3.0F, 0.0F);
    setRotateAngle(this.leftFin, 0.074610986F, -0.51484954F, 0.20085935F);
    this.rightFin = new ModelRenderer((Model)this, 83, 0);
    this.rightFin.setRotationPoint(-3.5F, 2.0F, -2.0F);
    this.rightFin.addBox(-5.0F, 0.0F, 0.0F, 5.0F, 1.0F, 3.0F, 0.0F);
    setRotateAngle(this.rightFin, -0.27445486F, 0.51484954F, -0.20085935F);
    this.head3 = new ModelRenderer((Model)this, 37, 16);
    this.head3.setRotationPoint(0.0F, 3.2F, -6.4F);
    this.head3.addBox(-2.5F, -1.0F, -5.0F, 5.0F, 1.0F, 5.0F, 0.0F);
    setRotateAngle(this.head3, 0.08726646F, -0.0F, 0.0F);
    this.head4 = new ModelRenderer((Model)this, 37, 23);
    this.head4.setRotationPoint(0.0F, -0.9F, -6.0F);
    this.head4.addBox(-2.5F, -1.0F, -3.0F, 5.0F, 2.0F, 3.0F, 0.0F);
    this.head2 = new ModelRenderer((Model)this, 37, 8);
    this.head2.setRotationPoint(0.0F, 0.1F, -6.0F);
    this.head2.addBox(-2.5F, -1.0F, -6.0F, 5.0F, 1.0F, 6.0F, 0.0F);
    this.tail2 = new ModelRenderer((Model)this, 7, 58);
    this.tail2.setRotationPoint(0.0F, -3.6F, 14.0F);
    this.tail2.addBox(-0.5F, -3.1333334F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
    setRotateAngle(this.tail2, -2.3212879F, -0.0F, 0.0F);
    this.head1 = new ModelRenderer((Model)this, 37, 0);
    this.head1.setRotationPoint(0.0F, -2.0F, -6.0F);
    this.head1.addBox(-2.5F, -1.0F, -6.0F, 5.0F, 1.0F, 6.0F, 0.0F);
    setRotateAngle(this.head1, 0.34906584F, -0.0F, 0.0F);
    this.teeth2 = new ModelRenderer((Model)this, 37, 37);
    this.teeth2.setRotationPoint(0.0F, 3.2F, -6.2F);
    this.teeth2.addBox(-2.5F, -2.0F, -5.0F, 5.0F, 1.0F, 5.0F, 0.0F);
    setRotateAngle(this.teeth2, 0.15707964F, -0.0F, 0.0F);
    this.body5 = new ModelRenderer((Model)this, 0, 47);
    this.body5.setRotationPoint(0.0F, 0.0F, -6.5F);
    this.body5.addBox(-3.5F, -3.5F, 0.0F, 7.0F, 7.0F, 3.0F, 0.0F);
    this.body3 = new ModelRenderer((Model)this, 0, 28);
    this.body3.setRotationPoint(0.0F, 0.0F, 7.5F);
    this.body3.addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 3.0F, 0.0F);
    this.body1 = new ModelRenderer((Model)this, 0, 0);
    this.body1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.body1.addBox(-4.0F, -4.0F, -3.5F, 8.0F, 8.0F, 7.0F, 0.0F);
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }


  
  public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    this.fin1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.tail3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.body4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.teeth1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.tail1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.tail4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.body2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.leftFin.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.rightFin.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.head3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.head4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.head2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.tail2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.head1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.teeth2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.body5.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.body3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.body1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
  }
}


