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
public class HeartModel
  extends EntityModel
{
  public ModelRenderer heart1;
  public ModelRenderer heart2;
  public ModelRenderer heart3;
  public ModelRenderer heart4;
  public ModelRenderer heart5;
  public ModelRenderer heart6;
  public ModelRenderer heart7;
  public ModelRenderer heart8;
  public ModelRenderer heart9;
  public ModelRenderer heart10;
  public ModelRenderer heart11;
  public ModelRenderer heart12;
  public ModelRenderer heart13;
  public ModelRenderer heart14;
  public ModelRenderer heart15;
  public ModelRenderer heart16;
  public ModelRenderer heart17;
  public ModelRenderer heart18;
  public ModelRenderer pellicle;
  
  public HeartModel() {
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.heart4 = new ModelRenderer((Model)this, 0, 0);
    this.heart4.setRotationPoint(3.2F, 8.7F, 0.0F);
    this.heart4.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart4, 0.0F, -0.0F, -2.4958208F);
    this.heart15 = new ModelRenderer((Model)this, 10, 0);
    this.heart15.setRotationPoint(-4.4F, -8.0F, 0.0F);
    this.heart15.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart15, 0.0F, -0.0F, -1.012291F);
    this.heart9 = new ModelRenderer((Model)this, 5, 0);
    this.heart9.setRotationPoint(-9.8F, -2.9F, 0.0F);
    this.heart9.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart9, 0.0F, -0.0F, -2.8972466F);
    this.heart11 = new ModelRenderer((Model)this, 10, 0);
    this.heart11.setRotationPoint(-8.9F, -6.5F, 0.0F);
    this.heart11.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart11, 0.0F, -0.0F, -2.3212879F);
    this.heart16 = new ModelRenderer((Model)this, 10, 0);
    this.heart16.setRotationPoint(4.4F, -8.0F, 0.0F);
    this.heart16.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart16, 0.0F, -0.0F, 1.012291F);
    this.heart2 = new ModelRenderer((Model)this, 0, 0);
    this.heart2.setRotationPoint(-0.3F, 12.0F, 0.0F);
    this.heart2.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart2, 0.0F, -0.0F, -2.3387413F);
    this.heart6 = new ModelRenderer((Model)this, 0, 0);
    this.heart6.setRotationPoint(6.2F, 4.9F, 0.0F);
    this.heart6.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart6, 0.0F, -0.0F, -2.6529005F);
    this.heart12 = new ModelRenderer((Model)this, 10, 0);
    this.heart12.setRotationPoint(8.9F, -6.5F, 0.0F);
    this.heart12.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart12, 0.0F, -0.0F, 2.3212879F);
    this.heart7 = new ModelRenderer((Model)this, 0, 0);
    this.heart7.setRotationPoint(-8.6F, 0.7F, 0.0F);
    this.heart7.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart7, 0.0F, -0.0F, 2.86234F);
    this.heart5 = new ModelRenderer((Model)this, 0, 0);
    this.heart5.setRotationPoint(-6.2F, 4.9F, 0.0F);
    this.heart5.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart5, 0.0F, -0.0F, 2.6529005F);
    this.pellicle = new ModelRenderer((Model)this, 15, 0);
    this.pellicle.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.pellicle.addBox(-9.5F, -9.0F, 0.0F, 19.0F, 21.0F, 0.0F, 0.0F);
    this.heart13 = new ModelRenderer((Model)this, 10, 0);
    this.heart13.mirror = true;
    this.heart13.setRotationPoint(-7.1F, -8.4F, 0.0F);
    this.heart13.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart13, 0.0F, -0.0F, -1.4137167F);
    this.heart18 = new ModelRenderer((Model)this, 10, 0);
    this.heart18.setRotationPoint(2.0F, -6.5F, 0.0F);
    this.heart18.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart18, 0.0F, -0.0F, 0.87266463F);
    this.heart8 = new ModelRenderer((Model)this, 0, 0);
    this.heart8.setRotationPoint(8.6F, 0.7F, 0.0F);
    this.heart8.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart8, 0.0F, -0.0F, -2.86234F);
    this.heart14 = new ModelRenderer((Model)this, 10, 0);
    this.heart14.setRotationPoint(7.1F, -8.4F, 0.0F);
    this.heart14.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart14, 0.0F, -0.0F, 1.4137167F);
    this.heart1 = new ModelRenderer((Model)this, 0, 0);
    this.heart1.mirror = true;
    this.heart1.setRotationPoint(0.3F, 12.0F, 0.0F);
    this.heart1.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart1, 0.0F, -0.0F, 2.3387413F);
    this.heart17 = new ModelRenderer((Model)this, 10, 0);
    this.heart17.setRotationPoint(-2.0F, -6.5F, 0.0F);
    this.heart17.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart17, 0.0F, -0.0F, -0.87266463F);
    this.heart3 = new ModelRenderer((Model)this, 0, 0);
    this.heart3.setRotationPoint(-3.2F, 8.7F, 0.0F);
    this.heart3.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart3, 0.0F, -0.0F, 2.4958208F);
    this.heart10 = new ModelRenderer((Model)this, 5, 0);
    this.heart10.setRotationPoint(9.8F, -2.9F, 0.0F);
    this.heart10.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F);
    setRotateAngle(this.heart10, 0.0F, -0.0F, 2.8972466F);
    this.pellicle.addChild(this.heart4);
    this.pellicle.addChild(this.heart15);
    this.pellicle.addChild(this.heart9);
    this.pellicle.addChild(this.heart11);
    this.pellicle.addChild(this.heart16);
    this.pellicle.addChild(this.heart2);
    this.pellicle.addChild(this.heart6);
    this.pellicle.addChild(this.heart12);
    this.pellicle.addChild(this.heart7);
    this.pellicle.addChild(this.heart5);
    this.pellicle.addChild(this.heart13);
    this.pellicle.addChild(this.heart18);
    this.pellicle.addChild(this.heart8);
    this.pellicle.addChild(this.heart14);
    this.pellicle.addChild(this.heart1);
    this.pellicle.addChild(this.heart17);
    this.pellicle.addChild(this.heart3);
    this.pellicle.addChild(this.heart10);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    matrixStack.push();
    matrixStack.scale(1.0F, 1.0F, 1.1F);
    this.pellicle.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    matrixStack.pop();
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


