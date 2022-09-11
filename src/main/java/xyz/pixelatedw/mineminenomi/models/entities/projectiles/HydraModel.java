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
public class HydraModel
  extends EntityModel
{
  public ModelRenderer neck;
  public ModelRenderer spine;
  public ModelRenderer thorn1;
  public ModelRenderer thorn2;
  public ModelRenderer thorn3;
  public ModelRenderer thorn4;
  public ModelRenderer thorn5;
  public ModelRenderer thorn6;
  public ModelRenderer head1;
  public ModelRenderer head2;
  public ModelRenderer head3;
  public ModelRenderer head4;
  public ModelRenderer rightHorn1;
  public ModelRenderer rightHorn2;
  public ModelRenderer leftHorn1;
  public ModelRenderer leftHorn2;
  public ModelRenderer jaw1;
  public ModelRenderer jaw2;
  public ModelRenderer teeth1;
  public ModelRenderer teeth2;
  
  public HydraModel() {
    this.textureWidth = 128;
    this.textureHeight = 64;
    this.thorn2 = new ModelRenderer((Model)this, 7, 36);
    this.thorn2.setRotationPoint(0.0F, -5.0F, 9.0F);
    this.thorn2.addBox(-1.0F, 0.0F, -0.5F, 2.0F, 4.0F, 2.0F, 0.0F);
    setRotateAngle(this.thorn2, 2.268928F, -0.0F, 0.0F);
    this.teeth2 = new ModelRenderer((Model)this, 63, 40);
    this.teeth2.setRotationPoint(0.0F, -1.0F, -10.0F);
    this.teeth2.addBox(-4.0F, 0.0F, -10.3F, 8.0F, 2.0F, 10.0F, 0.0F);
    setRotateAngle(this.teeth2, 0.2268928F, -0.0F, 0.0F);
    this.thorn5 = new ModelRenderer((Model)this, 7, 43);
    this.thorn5.setRotationPoint(0.0F, -5.0F, 22.0F);
    this.thorn5.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
    setRotateAngle(this.thorn5, 2.268928F, -0.0F, 0.0F);
    this.head3 = new ModelRenderer((Model)this, 0, 21);
    this.head3.setRotationPoint(0.0F, -6.3F, -11.7F);
    this.head3.addBox(0.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.head3, -0.044734888F, -0.086933546F, -0.08759811F);
    this.thorn1 = new ModelRenderer((Model)this, 7, 27);
    this.thorn1.setRotationPoint(0.0F, -5.0F, 3.0F);
    this.thorn1.addBox(-1.0F, 0.0F, -0.5F, 2.0F, 6.0F, 2.0F, 0.0F);
    setRotateAngle(this.thorn1, 2.268928F, -0.0F, 0.0F);
    this.head2 = new ModelRenderer((Model)this, 0, 21);
    this.head2.setRotationPoint(0.0F, -6.3F, -11.7F);
    this.head2.addBox(-4.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.head2, -0.044734888F, 0.086933546F, 0.08759811F);
    this.thorn3 = new ModelRenderer((Model)this, 7, 43);
    this.thorn3.setRotationPoint(0.0F, -5.0F, 13.0F);
    this.thorn3.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
    setRotateAngle(this.thorn3, 2.268928F, -0.0F, 0.0F);
    this.leftHorn1 = new ModelRenderer((Model)this, 93, 0);
    this.leftHorn1.setRotationPoint(2.6F, -5.6F, -3.0F);
    this.leftHorn1.addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 6.0F, 0.0F);
    setRotateAngle(this.leftHorn1, 0.70455766F, 0.12200478F, 0.052752364F);
    this.rightHorn1 = new ModelRenderer((Model)this, 74, 0);
    this.rightHorn1.mirror = true;
    this.rightHorn1.setRotationPoint(-2.6F, -5.6F, -3.0F);
    this.rightHorn1.addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 6.0F, 0.0F);
    setRotateAngle(this.rightHorn1, 0.70455766F, -0.12200478F, -0.052752364F);
    this.rightHorn2 = new ModelRenderer((Model)this, 74, 10);
    this.rightHorn2.setRotationPoint(-3.5F, -9.0F, 0.8F);
    this.rightHorn2.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F);
    setRotateAngle(this.rightHorn2, 0.99234694F, -0.12125788F, -0.12308134F);
    this.leftHorn2 = new ModelRenderer((Model)this, 93, 10);
    this.leftHorn2.setRotationPoint(3.5F, -9.0F, 0.8F);
    this.leftHorn2.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F);
    setRotateAngle(this.leftHorn2, 0.99234694F, 0.12125788F, 0.12308134F);
    this.teeth1 = new ModelRenderer((Model)this, 63, 27);
    this.teeth1.setRotationPoint(0.0F, -2.0F, -9.9F);
    this.teeth1.addBox(-4.0F, 0.0F, -10.0F, 8.0F, 2.0F, 10.0F, 0.0F);
    this.jaw1 = new ModelRenderer((Model)this, 37, 0);
    this.jaw1.setRotationPoint(0.0F, -4.0F, -10.0F);
    this.jaw1.addBox(-4.0F, 0.0F, -10.0F, 8.0F, 2.0F, 10.0F, 0.0F);
    this.neck = new ModelRenderer((Model)this, 0, 27);
    this.neck.setRotationPoint(0.0F, -1.5F, 29.0F);
    this.neck.addBox(-3.5F, -4.0F, -29.0F, 7.0F, 8.0F, 29.0F, 0.0F);
    this.head1 = new ModelRenderer((Model)this, 0, 0);
    this.head1.setRotationPoint(0.0F, -2.0F, 0.0F);
    this.head1.addBox(-4.0F, -5.0F, -10.0F, 8.0F, 10.0F, 10.0F, 0.0F);
    this.head4 = new ModelRenderer((Model)this, 11, 21);
    this.head4.setRotationPoint(0.0F, -5.5F, -9.8F);
    this.head4.addBox(-4.0F, -1.5F, -2.0F, 8.0F, 3.0F, 2.0F, 0.0F);
    this.thorn4 = new ModelRenderer((Model)this, 7, 36);
    this.thorn4.setRotationPoint(0.0F, -5.0F, 18.0F);
    this.thorn4.addBox(-1.0F, 0.0F, -0.5F, 2.0F, 4.0F, 2.0F, 0.0F);
    setRotateAngle(this.thorn4, 2.268928F, -0.0F, 0.0F);
    this.jaw2 = new ModelRenderer((Model)this, 37, 14);
    this.jaw2.setRotationPoint(0.0F, 1.0F, -10.0F);
    this.jaw2.addBox(-4.0F, 0.0F, -10.0F, 8.0F, 2.0F, 10.0F, 0.0F);
    setRotateAngle(this.jaw2, 0.2268928F, -0.0F, 0.0F);
    this.spine = new ModelRenderer((Model)this, 0, 24);
    this.spine.setRotationPoint(0.0F, -6.0F, 0.0F);
    this.spine.addBox(-1.0F, 0.0F, -0.5F, 2.0F, 29.0F, 1.0F, 0.0F);
    setRotateAngle(this.spine, 1.5707964F, -0.0F, 0.0F);
    this.thorn6 = new ModelRenderer((Model)this, 7, 43);
    this.thorn6.setRotationPoint(0.0F, -5.0F, 26.0F);
    this.thorn6.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
    setRotateAngle(this.thorn6, 2.268928F, -0.0F, 0.0F);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    this.thorn2.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.teeth2.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.thorn5.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.head3.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.thorn1.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.head2.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.thorn3.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.leftHorn1.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.rightHorn1.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.rightHorn2.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.leftHorn2.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.teeth1.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.jaw1.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.neck.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.head1.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.head4.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.thorn4.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.jaw2.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.spine.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    this.thorn6.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
  }


  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


