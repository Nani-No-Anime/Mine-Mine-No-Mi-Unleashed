package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity;

@OnlyIn(Dist.CLIENT)
public class LapahnModel<T extends LapahnEntity> extends BipedModel<T> {
  public ModelRenderer leftLeg1;
  public ModelRenderer leftLeg2;
  public ModelRenderer leftFoot;
  public ModelRenderer head;
  public ModelRenderer body1;
  public ModelRenderer body2;
  public ModelRenderer body3;
  public ModelRenderer body4;
  public ModelRenderer body5;
  public ModelRenderer tail;
  public ModelRenderer rightLeg1;
  public ModelRenderer rightArm1;
  public ModelRenderer leftArm1;
  public ModelRenderer wiskers;
  public ModelRenderer rightEar;
  public ModelRenderer leftEar;
  public ModelRenderer rightLeg2;
  public ModelRenderer rightFoot;
  public ModelRenderer rightArm2;
  public ModelRenderer leftArm2;
  
  public LapahnModel() {
    super(1.0F);
    this.textureWidth = 140;
    this.textureHeight = 70;
    
    this.bipedBody.showModel = false;
    this.bipedHead.showModel = false;
    this.bipedHeadwear.showModel = false;
    this.bipedLeftArm.showModel = false;
    this.bipedLeftLeg.showModel = false;
    this.bipedRightArm.showModel = false;
    this.bipedRightLeg.showModel = false;
    
    this.wiskers = new ModelRenderer((Model)this, 92, 44);
    this.wiskers.setRotationPoint(0.0F, -8.0F, -0.1F);
    this.wiskers.addBox(-4.5F, -6.0F, -3.0F, 9.0F, 9.0F, 0.0F, 0.0F);
    this.leftEar = new ModelRenderer((Model)this, 25, 0);
    this.leftEar.setRotationPoint(1.7F, -4.5F, 0.0F);
    this.leftEar.addBox(-1.0F, -6.0F, -0.5F, 2.0F, 6.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftEar, 0.071907565F, -0.1738348F, 0.08866273F);
    this.body4 = new ModelRenderer((Model)this, 35, 54);
    this.body4.setRotationPoint(0.0F, -5.0F, 0.0F);
    this.body4.addBox(-7.0F, 0.0F, -5.0F, 14.0F, 5.0F, 10.0F, 0.0F);
    this.leftFoot = new ModelRenderer((Model)this, 0, 37);
    this.leftFoot.setRotationPoint(0.0F, 3.0F, 0.7F);
    this.leftFoot.addBox(-2.5F, 0.0F, -8.0F, 5.0F, 2.0F, 10.0F, 0.0F);
    setRotateAngle(this.leftFoot, -0.034906585F, 0.0F, 0.0F);
    this.leftLeg1 = new ModelRenderer((Model)this, 0, 13);
    this.leftLeg1.setRotationPoint(4.8F, 14.9F, -2.2F);
    this.leftLeg1.addBox(-4.0F, -1.0F, -4.0F, 8.0F, 6.0F, 9.0F, 0.0F);
    setRotateAngle(this.leftLeg1, -0.2443461F, -0.0F, 0.0F);
    this.body1 = new ModelRenderer((Model)this, 35, 0);
    this.body1.setRotationPoint(0.0F, 16.0F, 0.0F);
    this.body1.addBox(-8.0F, 0.0F, -5.5F, 16.0F, 1.0F, 11.0F, 0.0F);
    this.rightEar = new ModelRenderer((Model)this, 25, 0);
    this.rightEar.setRotationPoint(-1.7F, -4.5F, 0.0F);
    this.rightEar.addBox(-1.0F, -6.0F, -0.5F, 2.0F, 6.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightEar, 0.071907565F, 0.1738348F, -0.08866273F);
    this.body3 = new ModelRenderer((Model)this, 35, 36);
    this.body3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.body3.addBox(-8.0F, 0.0F, -5.5F, 16.0F, 6.0F, 11.0F, 0.0F);
    this.rightLeg1 = new ModelRenderer((Model)this, 0, 13);
    this.rightLeg1.setRotationPoint(-4.8F, 14.9F, -2.2F);
    this.rightLeg1.addBox(-4.0F, -1.0F, -4.0F, 8.0F, 6.0F, 9.0F, 0.0F);
    setRotateAngle(this.rightLeg1, -0.2443461F, -0.0F, 0.0F);
    this.body2 = new ModelRenderer((Model)this, 35, 13);
    this.body2.setRotationPoint(0.0F, 6.0F, 0.0F);
    this.body2.addBox(-8.5F, 0.0F, -6.0F, 17.0F, 10.0F, 12.0F, 0.0F);
    this.leftLeg2 = new ModelRenderer((Model)this, 0, 29);
    this.leftLeg2.setRotationPoint(0.0F, 3.3F, 3.0F);
    this.leftLeg2.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F);
    setRotateAngle(this.leftLeg2, 0.33161256F, -0.0F, 0.0F);
    this.rightFoot = new ModelRenderer((Model)this, 0, 37);
    this.rightFoot.setRotationPoint(0.0F, 3.0F, 0.7F);
    this.rightFoot.addBox(-2.5F, 0.0F, -8.0F, 5.0F, 2.0F, 10.0F, 0.0F);
    setRotateAngle(this.rightFoot, -0.034906585F, 0.0F, 0.0F);
    this.body5 = new ModelRenderer((Model)this, 90, 0);
    this.body5.setRotationPoint(0.0F, -7.0F, 0.0F);
    this.body5.addBox(-6.5F, 0.0F, -4.5F, 13.0F, 2.0F, 9.0F, 0.0F);
    this.tail = new ModelRenderer((Model)this, 0, 50);
    this.tail.setRotationPoint(0.0F, 13.0F, 5.8F);
    this.tail.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F);
    setRotateAngle(this.tail, -0.10471976F, -0.0F, 0.0F);
    this.rightLeg2 = new ModelRenderer((Model)this, 0, 29);
    this.rightLeg2.setRotationPoint(0.0F, 3.3F, 3.0F);
    this.rightLeg2.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F);
    setRotateAngle(this.rightLeg2, 0.33161256F, -0.0F, 0.0F);
    this.head = new ModelRenderer((Model)this, 0, 0);
    this.head.setRotationPoint(0.0F, -8.0F, 0.0F);
    this.head.addBox(-3.0F, -5.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F);
    this.leftArm2 = new ModelRenderer((Model)this, 94, 27);
    this.leftArm2.setRotationPoint(2.5F, 5.5F, 0.0F);
    this.leftArm2.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F);
    setRotateAngle(this.leftArm2, 0.0F, -0.0F, 0.12217305F);
    this.rightArm1 = new ModelRenderer((Model)this, 94, 13);
    this.rightArm1.setRotationPoint(-6.5F, -3.0F, 0.0F);
    this.rightArm1.addBox(-5.0F, -2.0F, -2.5F, 5.0F, 8.0F, 5.0F, 0.0F);
    setRotateAngle(this.rightArm1, 0.0F, -0.0F, 0.2617994F);
    this.leftArm1 = new ModelRenderer((Model)this, 94, 13);
    this.leftArm1.setRotationPoint(6.5F, -3.0F, 0.0F);
    this.leftArm1.addBox(0.0F, -2.0F, -2.5F, 5.0F, 8.0F, 5.0F, 0.0F);
    setRotateAngle(this.leftArm1, 0.0F, -0.0F, -0.2617994F);
    this.rightArm2 = new ModelRenderer((Model)this, 94, 27);
    this.rightArm2.setRotationPoint(-2.5F, 5.5F, 0.0F);
    this.rightArm2.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F);
    setRotateAngle(this.rightArm2, 0.0F, -0.0F, -0.12217305F);
    this.head.addChild(this.leftEar);
    this.leftLeg2.addChild(this.leftFoot);
    this.head.addChild(this.rightEar);
    this.leftLeg1.addChild(this.leftLeg2);
    this.rightLeg2.addChild(this.rightFoot);
    this.rightLeg1.addChild(this.rightLeg2);
    this.leftArm1.addChild(this.leftArm2);
    this.rightArm1.addChild(this.rightArm2);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.wiskers.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftLeg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightArm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightLeg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftArm1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    if (Math.sqrt(entity.getDistanceSq(((LapahnEntity)entity).prevPosX, ((LapahnEntity)entity).prevPosY, ((LapahnEntity)entity).prevPosZ)) > 0.0D) {
      
      this.rightLeg1.rotateAngleX = 0.9F * (-0.2F + MathHelper.cos(ageInTicks * 0.5F));
      this.leftLeg1.rotateAngleX = 0.9F * (-0.2F + MathHelper.cos(ageInTicks * 0.5F));
      this.rightArm1.rotateAngleX = -0.5F * (-0.2F + MathHelper.sin(ageInTicks * 0.45F));
      this.leftArm1.rotateAngleX = -0.5F * (-0.2F + MathHelper.sin(ageInTicks * 0.45F));
      float formula = (0.0F - 0.9F + MathHelper.cos(ageInTicks * 0.45F)) * 10.0F;
      this.head.rotationPointY = -8.0F + formula;
      this.rightArm1.rotationPointY = -3.0F + formula;
      this.leftArm1.rotationPointY = -3.0F + formula;
      this.head.rotationPointY = -8.0F + formula;
      this.wiskers.rotationPointY = -8.0F + formula;
      this.body1.rotationPointY = 16.0F + formula;
      this.body2.rotationPointY = 6.0F + formula;
      this.body3.rotationPointY = formula;
      this.body4.rotationPointY = -5.0F + formula;
      this.body5.rotationPointY = -7.0F + formula;
      this.leftLeg1.rotationPointY = 14.9F + formula;
      this.rightLeg1.rotationPointY = 14.9F + formula;
      this.tail.rotationPointY = 13.0F + formula;
    }
    else {
      
      this.rightLeg1.rotateAngleX = (float)Math.toRadians(-17.0D);
      this.leftLeg1.rotateAngleX = (float)Math.toRadians(-17.0D);
      this.rightArm1.rotateAngleX = (float)Math.toRadians(0.0D);
      this.leftArm1.rotateAngleX = (float)Math.toRadians(0.0D);
      this.head.rotationPointY = -8.0F;
      this.rightArm1.rotationPointY = -3.0F;
      this.leftArm1.rotationPointY = -3.0F;
      this.head.rotationPointY = -8.0F;
      this.wiskers.rotationPointY = -8.0F;
      this.body1.rotationPointY = 16.0F;
      this.body2.rotationPointY = 6.0F;
      this.body3.rotationPointY = 0.0F;
      this.body4.rotationPointY = -5.0F;
      this.body5.rotationPointY = -7.0F;
      this.leftLeg1.rotationPointY = 14.9F;
      this.rightLeg1.rotationPointY = 14.9F;
      this.tail.rotationPointY = 13.0F;

      
      if (ageInTicks % 300.0F > 0.0F && ageInTicks % 300.0F < 100.0F) {
        this.leftEar.rotateAngleX = 0.1F * (0.3F + MathHelper.cos(ageInTicks * 0.55F));
      } else {
        this.leftEar.rotateAngleX = (float)Math.toRadians(0.0D);
      } 
    } 
  }
  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


