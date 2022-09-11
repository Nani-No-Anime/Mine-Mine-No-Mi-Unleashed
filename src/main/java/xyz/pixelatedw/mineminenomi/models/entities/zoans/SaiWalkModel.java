package xyz.pixelatedw.mineminenomi.models.entities.zoans;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;

public class SaiWalkModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer body;
  private final ModelRenderer lowerBody_r1;
  private final ModelRenderer upperBody_r1;
  private final ModelRenderer head;
  private final ModelRenderer neck_r1;
  private final ModelRenderer head2_r1;
  private final ModelRenderer head1_r1;
  private final ModelRenderer horns;
  private final ModelRenderer horn4_r1;
  private final ModelRenderer horn3_r1;
  private final ModelRenderer horn2_r1;
  private final ModelRenderer horn1_r1;
  private final ModelRenderer rightEars;
  private final ModelRenderer rightEar_r1;
  private final ModelRenderer leftEar;
  private final ModelRenderer leftEar_r1;
  private final ModelRenderer leftFrontLeg;
  private final ModelRenderer leftFrontLeg2_r1;
  private final ModelRenderer leftFrontLeg1_r1;
  private final ModelRenderer rightFrontLeg;
  private final ModelRenderer rightFrontLeg2_r1;
  private final ModelRenderer rightFrontLeg1_r1;
  private final ModelRenderer rightBackLeg;
  private final ModelRenderer rightBackLeg2_r1;
  private final ModelRenderer rightBackLeg1_r1;
  private final ModelRenderer leftBackLeg;
  private final ModelRenderer leftBackLeg2_r1;
  private final ModelRenderer leftBackLeg1_r1;
  private final ModelRenderer tail;
  private final ModelRenderer tail_r1;
  
  public SaiWalkModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 128;
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(-1.3508F, 11.8354F, 0.0F);
    
    this.lowerBody_r1 = new ModelRenderer((Model)this);
    this.lowerBody_r1.setRotationPoint(-4.5067F, 0.2204F, 0.0F);
    this.body.addChild(this.lowerBody_r1);
    setRotationAngle(this.lowerBody_r1, 0.0F, 0.0F, 0.0436F);
    this.lowerBody_r1.setTextureOffset(0, 23).addBox(-5.5F, -4.5F, -5.5F, 11.0F, 9.0F, 11.0F, 0.0F, false);
    
    this.upperBody_r1 = new ModelRenderer((Model)this);
    this.upperBody_r1.setRotationPoint(4.5067F, -0.2204F, 0.0F);
    this.body.addChild(this.upperBody_r1);
    setRotationAngle(this.upperBody_r1, 0.0F, 0.0F, -0.0873F);
    this.upperBody_r1.setTextureOffset(0, 0).addBox(-5.5F, -5.0F, -6.5F, 11.0F, 10.0F, 13.0F, 0.0F, false);
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(8.0271F, 9.6204F, -1.5F);
    
    this.neck_r1 = new ModelRenderer((Model)this);
    this.neck_r1.setRotationPoint(-5.0271F, -1.6204F, 1.0F);
    this.head.addChild(this.neck_r1);
    setRotationAngle(this.neck_r1, 0.0F, 0.0F, -0.4363F);
    this.neck_r1.setTextureOffset(37, 36).addBox(2.0F, 0.0F, -3.0F, 8.0F, 8.0F, 7.0F, 0.01F, false);
    
    this.head2_r1 = new ModelRenderer((Model)this);
    this.head2_r1.setRotationPoint(7.8872F, 0.9067F, 1.5F);
    this.head.addChild(this.head2_r1);
    setRotationAngle(this.head2_r1, 0.0F, 0.0F, 0.48F);
    this.head2_r1.setTextureOffset(35, 0).addBox(-5.0F, -3.0F, -3.5F, 10.0F, 6.0F, 7.0F, 0.02F, false);
    
    this.head1_r1 = new ModelRenderer((Model)this);
    this.head1_r1.setRotationPoint(5.0116F, -3.3758F, 1.5F);
    this.head.addChild(this.head1_r1);
    setRotationAngle(this.head1_r1, 0.0F, 0.0F, 0.5236F);
    this.head1_r1.setTextureOffset(39, 24).addBox(-2.5F, -1.0F, -3.5F, 5.0F, 2.0F, 7.0F, 0.0F, false);
    
    this.horns = new ModelRenderer((Model)this);
    this.horns.setRotationPoint(-8.0271F, 1.3796F, 1.5F);
    this.head.addChild(this.horns);
    
    this.horn4_r1 = new ModelRenderer((Model)this);
    this.horn4_r1.setRotationPoint(19.4789F, -3.4751F, 0.0F);
    this.horns.addChild(this.horn4_r1);
    setRotationAngle(this.horn4_r1, 0.0F, 0.0F, 0.3491F);
    this.horn4_r1.setTextureOffset(0, 31).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.01F, false);
    
    this.horn3_r1 = new ModelRenderer((Model)this);
    this.horn3_r1.setRotationPoint(18.8548F, -2.4177F, 0.0F);
    this.horns.addChild(this.horn3_r1);
    setRotationAngle(this.horn3_r1, 0.0F, 0.0F, 0.6109F);
    this.horn3_r1.setTextureOffset(0, 23).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.horn2_r1 = new ModelRenderer((Model)this);
    this.horn2_r1.setRotationPoint(22.3124F, -4.0707F, 0.0F);
    this.horns.addChild(this.horn2_r1);
    setRotationAngle(this.horn2_r1, 0.0F, 0.0F, 0.3491F);
    this.horn2_r1.setTextureOffset(4, 8).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, 0.01F, false);
    
    this.horn1_r1 = new ModelRenderer((Model)this);
    this.horn1_r1.setRotationPoint(21.3463F, -2.0735F, 0.0F);
    this.horns.addChild(this.horn1_r1);
    setRotationAngle(this.horn1_r1, 0.0F, 0.0F, 0.6109F);
    this.horn1_r1.setTextureOffset(0, 27).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.rightEars = new ModelRenderer((Model)this);
    this.rightEars.setRotationPoint(-8.0271F, 1.3796F, 1.5F);
    this.head.addChild(this.rightEars);

    
    this.rightEar_r1 = new ModelRenderer((Model)this);
    this.rightEar_r1.setRotationPoint(12.3332F, -7.2594F, -2.8669F);
    this.rightEars.addChild(this.rightEar_r1);
    setRotationAngle(this.rightEar_r1, 0.1309F, 0.0F, 0.4363F);
    this.rightEar_r1.setTextureOffset(6, 0).addBox(-0.5F, -1.5F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
    
    this.leftEar = new ModelRenderer((Model)this);
    this.leftEar.setRotationPoint(-8.0271F, 1.3796F, 1.5F);
    this.head.addChild(this.leftEar);
    
    this.leftEar_r1 = new ModelRenderer((Model)this);
    this.leftEar_r1.setRotationPoint(12.3446F, -7.2837F, 2.9562F);
    this.leftEar.addChild(this.leftEar_r1);
    setRotationAngle(this.leftEar_r1, -0.1745F, 0.0F, 0.4363F);
    this.leftEar_r1.setTextureOffset(6, 0).addBox(-0.5F, -1.5F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, true);
    
    this.leftFrontLeg = new ModelRenderer((Model)this);
    this.leftFrontLeg.setRotationPoint(5.0F, 11.0F, 5.5F);
    this.leftFrontLeg.setTextureOffset(12, 66).addBox(-2.1F, 11.0F, -2.5F, 5.0F, 2.0F, 4.0F, 0.01F, true);
    
    this.leftFrontLeg2_r1 = new ModelRenderer((Model)this);
    this.leftFrontLeg2_r1.setRotationPoint(-0.3916F, 9.0257F, -0.5F);
    this.leftFrontLeg.addChild(this.leftFrontLeg2_r1);
    setRotationAngle(this.leftFrontLeg2_r1, 0.0F, 0.0F, -0.1309F);
    this.leftFrontLeg2_r1.setTextureOffset(0, 58).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, true);
    
    this.leftFrontLeg1_r1 = new ModelRenderer((Model)this);
    this.leftFrontLeg1_r1.setRotationPoint(1.0F, 5.0F, -1.0F);
    this.leftFrontLeg.addChild(this.leftFrontLeg1_r1);
    setRotationAngle(this.leftFrontLeg1_r1, 0.0F, 0.0F, 0.0873F);
    this.leftFrontLeg1_r1.setTextureOffset(0, 43).addBox(-4.5F, -7.0F, -2.0F, 6.0F, 10.0F, 5.0F, 0.0F, true);
    
    this.rightFrontLeg = new ModelRenderer((Model)this);
    this.rightFrontLeg.setRotationPoint(5.0F, 11.0F, -5.5F);
    this.rightFrontLeg.setTextureOffset(12, 66).addBox(-2.1F, 11.0F, -1.5F, 5.0F, 2.0F, 4.0F, 0.01F, true);
    
    this.rightFrontLeg2_r1 = new ModelRenderer((Model)this);
    this.rightFrontLeg2_r1.setRotationPoint(-0.3916F, 9.0257F, 0.5F);
    this.rightFrontLeg.addChild(this.rightFrontLeg2_r1);
    setRotationAngle(this.rightFrontLeg2_r1, 0.0F, 0.0F, -0.1309F);
    this.rightFrontLeg2_r1.setTextureOffset(0, 58).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, true);
    
    this.rightFrontLeg1_r1 = new ModelRenderer((Model)this);
    this.rightFrontLeg1_r1.setRotationPoint(1.0F, 5.0F, 2.0F);
    this.rightFrontLeg.addChild(this.rightFrontLeg1_r1);
    setRotationAngle(this.rightFrontLeg1_r1, 0.0F, 0.0F, 0.0873F);
    this.rightFrontLeg1_r1.setTextureOffset(0, 43).addBox(-4.5F, -7.0F, -4.0F, 6.0F, 10.0F, 5.0F, 0.0F, true);
    
    this.rightBackLeg = new ModelRenderer((Model)this);
    this.rightBackLeg.setRotationPoint(-7.0F, 11.0F, -5.0F);
    this.rightBackLeg.setTextureOffset(12, 66).addBox(-2.0F, 11.0F, -1.0F, 5.0F, 2.0F, 4.0F, 0.01F, true);
    
    this.rightBackLeg2_r1 = new ModelRenderer((Model)this);
    this.rightBackLeg2_r1.setRotationPoint(-0.3349F, 9.3909F, 1.0F);
    this.rightBackLeg.addChild(this.rightBackLeg2_r1);
    setRotationAngle(this.rightBackLeg2_r1, 0.0F, 0.0F, -0.1309F);
    this.rightBackLeg2_r1.setTextureOffset(0, 68).addBox(-2.0F, -2.5F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, true);
    
    this.rightBackLeg1_r1 = new ModelRenderer((Model)this);
    this.rightBackLeg1_r1.setRotationPoint(0.0F, 4.0F, 2.5F);
    this.rightBackLeg.addChild(this.rightBackLeg1_r1);
    setRotationAngle(this.rightBackLeg1_r1, 0.0F, 0.0F, 0.0873F);
    this.rightBackLeg1_r1.setTextureOffset(0, 43).addBox(-3.5F, -6.0F, -4.0F, 6.0F, 10.0F, 5.0F, 0.0F, true);
    
    this.leftBackLeg = new ModelRenderer((Model)this);
    this.leftBackLeg.setRotationPoint(-7.0F, 11.0F, 5.0F);
    this.leftBackLeg.setTextureOffset(12, 66).addBox(-2.0F, 11.0F, -3.0F, 5.0F, 2.0F, 4.0F, 0.01F, true);
    
    this.leftBackLeg2_r1 = new ModelRenderer((Model)this);
    this.leftBackLeg2_r1.setRotationPoint(-0.3349F, 9.3909F, -1.0F);
    this.leftBackLeg.addChild(this.leftBackLeg2_r1);
    setRotationAngle(this.leftBackLeg2_r1, 0.0F, 0.0F, -0.1309F);
    this.leftBackLeg2_r1.setTextureOffset(0, 68).addBox(-2.0F, -2.5F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, true);
    
    this.leftBackLeg1_r1 = new ModelRenderer((Model)this);
    this.leftBackLeg1_r1.setRotationPoint(0.0F, 4.0F, -1.5F);
    this.leftBackLeg.addChild(this.leftBackLeg1_r1);
    setRotationAngle(this.leftBackLeg1_r1, 0.0F, 0.0F, 0.0873F);
    this.leftBackLeg1_r1.setTextureOffset(0, 43).addBox(-3.5F, -6.0F, -2.0F, 6.0F, 10.0F, 5.0F, 0.0F, true);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(-10.8276F, 8.5712F, 0.0F);
    setRotationAngle(this.tail, 0.0F, 0.0F, 0.1745F);
    
    this.tail_r1 = new ModelRenderer((Model)this);
    this.tail_r1.setRotationPoint(-0.2589F, -1.165F, -1.0F);
    this.tail.addChild(this.tail_r1);
    setRotationAngle(this.tail_r1, 0.0F, 0.0F, 0.1309F);
    this.tail_r1.setTextureOffset(0, 0).addBox(-0.1428F, 0.2831F, 0.0F, 1.0F, 8.0F, 2.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
    this.head.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightBackLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftBackLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.tail.render(matrixStack, buffer, packedLight, packedOverlay);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.rightFrontLeg.rotateAngleZ = MathHelper.cos(limbSwing * 0.77F) * 0.5F * limbSwingAmount;
    this.leftFrontLeg.rotateAngleZ = MathHelper.cos(limbSwing * 0.75F) * 0.5F * limbSwingAmount;
    this.rightBackLeg.rotateAngleZ = MathHelper.cos(limbSwing * 0.78F) * 0.5F * limbSwingAmount;
    this.leftBackLeg.rotateAngleZ = MathHelper.cos(limbSwing * 0.74F) * 0.5F * limbSwingAmount;
    if (entity.isSprinting()) {
      this.tail.rotateAngleZ = 1.2F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
    }
    
    this.swingProgress = ((LivingEntity)entity).swingProgress;
    if (this.swingProgress > 0.0F) {
      
      this.head.rotateAngleY += this.body.rotateAngleY;
      float f1 = 1.0F - this.swingProgress;
      f1 *= f1;
      f1 *= f1;
      f1 = 1.0F - f1;
      float f2 = MathHelper.sin(f1 * 3.1415927F);
      float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.1F) * 0.15F;
      this.head.rotateAngleX = (float)(this.head.rotateAngleX - f2 * 0.5D + f3);
      this.head.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.8F;
    } 
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    this.head.translateRotate(matrixStack);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees(115.0F));
    matrixStack.rotate(Vector3f.XP.rotationDegrees(170.0F));
    matrixStack.translate(-0.01D, 0.1D, -0.1D);
  }
  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
}


