package xyz.pixelatedw.mineminenomi.models.entities.zoans;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;

public class GiraffeWalkModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer body;
  private final ModelRenderer tail1;
  private final ModelRenderer hunch;
  private final ModelRenderer neck;
  private final ModelRenderer head;
  private final ModelRenderer head2;
  private final ModelRenderer rightHorn;
  private final ModelRenderer leftHorn;
  private final ModelRenderer rightEar;
  private final ModelRenderer leftEar;
  private final ModelRenderer mane;
  private final ModelRenderer rightFrontLeg;
  private final ModelRenderer rightFrontHoof1;
  private final ModelRenderer rightFrontHoof2;
  private final ModelRenderer leftFrontLeg;
  private final ModelRenderer leftFrontHoof1;
  private final ModelRenderer leftFrontHoof2;
  private final ModelRenderer leftRearLeg;
  private final ModelRenderer leftRearHoof1;
  private final ModelRenderer leftRearHoof2;
  private final ModelRenderer rightRearLeg;
  private final ModelRenderer rightRearHoof1;
  private final ModelRenderer rightRearHoof2;
  
  public GiraffeWalkModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 64;
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.0F, 7.0F, 3.0F);
    setRotationAngle(this.body, 1.4835F, 0.0F, 0.0F);
    this.body.setTextureOffset(18, 18).addBox(-6.0F, -10.4346F, -6.7471F, 10.0F, 16.0F, 9.0F, 0.0F, false);
    
    this.tail1 = new ModelRenderer((Model)this);
    this.tail1.setRotationPoint(-1.0F, 4.5747F, 1.0142F);
    this.body.addChild(this.tail1);
    setRotationAngle(this.tail1, -1.0472F, 0.0F, 0.0F);
    this.tail1.setTextureOffset(60, 28).addBox(-0.5F, 0.4821F, -0.5745F, 1.0F, 5.0F, 1.0F, 0.0F, false);
    
    this.hunch = new ModelRenderer((Model)this);
    this.hunch.setRotationPoint(-6.02F, -10.5F, 3.0F);
    this.body.addChild(this.hunch);
    setRotationAngle(this.hunch, -0.7854F, 0.0F, 0.0F);
    this.hunch.setTextureOffset(40, 0).addBox(0.02F, 0.5745F, -0.4821F, 10.0F, 9.0F, 7.0F, -0.02F, false);
    
    this.neck = new ModelRenderer((Model)this);
    this.neck.setRotationPoint(-1.0F, 2.0F, -2.0F);
    setRotationAngle(this.neck, 0.7854F, 0.0F, 0.0F);
    this.neck.setTextureOffset(0, 0).addBox(-2.0F, -21.9506F, -3.0052F, 4.0F, 21.0F, 4.0F, 0.0F, false);
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(2.0F, -20.481F, -1.9748F);
    this.neck.addChild(this.head);
    setRotationAngle(this.head, -0.7854F, 0.0F, 0.0F);
    this.head.setTextureOffset(17, 0).addBox(-4.0F, -3.25F, -6.0F, 4.0F, 3.0F, 7.0F, -0.01F, false);
    
    this.head2 = new ModelRenderer((Model)this);
    this.head2.setRotationPoint(-4.0F, -6.0F, -3.0F);
    this.head.addChild(this.head2);
    this.head2.setTextureOffset(18, 11).addBox(0.0F, 0.75F, 0.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
    
    this.rightHorn = new ModelRenderer((Model)this);
    this.rightHorn.setRotationPoint(0.75F, 0.0F, 3.0F);
    this.head2.addChild(this.rightHorn);
    this.rightHorn.setTextureOffset(60, 20).addBox(-0.5F, -1.25F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftHorn = new ModelRenderer((Model)this);
    this.leftHorn.setRotationPoint(3.25F, 0.0F, 3.0F);
    this.head2.addChild(this.leftHorn);
    this.leftHorn.setTextureOffset(60, 20).addBox(-0.5F, -1.25F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightEar = new ModelRenderer((Model)this);
    this.rightEar.setRotationPoint(-0.25F, 1.25F, 3.0F);
    this.head2.addChild(this.rightEar);
    setRotationAngle(this.rightEar, 0.0F, 0.0F, 0.2618F);
    this.rightEar.setTextureOffset(60, 24).addBox(-2.099F, -0.0129F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, false);
    
    this.leftEar = new ModelRenderer((Model)this);
    this.leftEar.setRotationPoint(4.0F, 1.7F, 3.0F);
    this.head2.addChild(this.leftEar);
    setRotationAngle(this.leftEar, 0.0F, 0.0F, -0.2618F);
    this.leftEar.setTextureOffset(67, 24).addBox(-0.4183F, -0.3709F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, true);
    
    this.mane = new ModelRenderer((Model)this);
    this.mane.setRotationPoint(0.0F, -22.481F, 1.5252F);
    this.neck.addChild(this.mane);
    this.mane.setTextureOffset(76, 0).addBox(0.0F, 0.0303F, -0.5303F, 0.0F, 21.0F, 1.0F, 0.0F, false);
    
    this.rightFrontLeg = new ModelRenderer((Model)this);
    this.rightFrontLeg.setRotationPoint(-2.75F, 12.0F, -4.75F);
    this.rightFrontLeg.setTextureOffset(0, 28).addBox(-3.0F, 0.0F, -3.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
    
    this.rightFrontHoof1 = new ModelRenderer((Model)this);
    this.rightFrontHoof1.setRotationPoint(-2.0F, 10.0F, -3.5F);
    this.rightFrontLeg.addChild(this.rightFrontHoof1);
    setRotationAngle(this.rightFrontHoof1, -0.1211F, -0.4883F, -0.0394F);
    this.rightFrontHoof1.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightFrontHoof2 = new ModelRenderer((Model)this);
    this.rightFrontHoof2.setRotationPoint(-3.0F, 10.0F, -3.0F);
    this.rightFrontLeg.addChild(this.rightFrontHoof2);
    setRotationAngle(this.rightFrontHoof2, -0.1211F, 0.4883F, 0.0394F);
    this.rightFrontHoof2.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftFrontLeg = new ModelRenderer((Model)this);
    this.leftFrontLeg.setRotationPoint(4.75F, 12.0F, -4.75F);
    this.leftFrontLeg.setTextureOffset(0, 28).addBox(-3.0F, 0.0F, -3.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
    
    this.leftFrontHoof1 = new ModelRenderer((Model)this);
    this.leftFrontHoof1.setRotationPoint(-2.0F, 10.0F, -3.5F);
    this.leftFrontLeg.addChild(this.leftFrontHoof1);
    setRotationAngle(this.leftFrontHoof1, -0.1211F, -0.4883F, -0.0394F);
    this.leftFrontHoof1.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftFrontHoof2 = new ModelRenderer((Model)this);
    this.leftFrontHoof2.setRotationPoint(-3.0F, 10.0F, -3.0F);
    this.leftFrontLeg.addChild(this.leftFrontHoof2);
    setRotationAngle(this.leftFrontHoof2, -0.1211F, 0.4883F, 0.0394F);
    this.leftFrontHoof2.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftRearLeg = new ModelRenderer((Model)this);
    this.leftRearLeg.setRotationPoint(4.75F, 12.0F, 8.25F);
    this.leftRearLeg.setTextureOffset(0, 28).addBox(-3.0F, 0.0F, -3.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
    
    this.leftRearHoof1 = new ModelRenderer((Model)this);
    this.leftRearHoof1.setRotationPoint(-2.0F, 10.0F, -3.5F);
    this.leftRearLeg.addChild(this.leftRearHoof1);
    setRotationAngle(this.leftRearHoof1, -0.1211F, -0.4883F, -0.0394F);
    this.leftRearHoof1.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftRearHoof2 = new ModelRenderer((Model)this);
    this.leftRearHoof2.setRotationPoint(-3.0F, 10.0F, -3.0F);
    this.leftRearLeg.addChild(this.leftRearHoof2);
    setRotationAngle(this.leftRearHoof2, -0.1211F, 0.4883F, 0.0394F);
    this.leftRearHoof2.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightRearLeg = new ModelRenderer((Model)this);
    this.rightRearLeg.setRotationPoint(-2.75F, 12.0F, 8.25F);
    this.rightRearLeg.setTextureOffset(0, 28).addBox(-3.0F, 0.0F, -3.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
    
    this.rightRearHoof1 = new ModelRenderer((Model)this);
    this.rightRearHoof1.setRotationPoint(-2.0F, 10.0F, -3.5F);
    this.rightRearLeg.addChild(this.rightRearHoof1);
    setRotationAngle(this.rightRearHoof1, -0.1211F, -0.4883F, -0.0394F);
    this.rightRearHoof1.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightRearHoof2 = new ModelRenderer((Model)this);
    this.rightRearHoof2.setRotationPoint(-3.0F, 10.0F, -3.0F);
    this.rightRearLeg.addChild(this.rightRearHoof2);
    setRotationAngle(this.rightRearHoof2, -0.1211F, 0.4883F, 0.0394F);
    this.rightRearHoof2.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftRearLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightRearLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.rightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
    this.leftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount;
    this.rightRearLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.3F * limbSwingAmount;
    this.leftRearLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.4F * limbSwingAmount;
    if (entity.isSprinting()) {
      
      this.tail1.rotateAngleX = 0.2F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
      this.leftEar.rotateAngleY = -0.3F - MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
      this.rightEar.rotateAngleY = 0.3F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
    } 

    
    this.swingProgress = ((LivingEntity)entity).swingProgress;
    if (this.swingProgress > 0.0F) {
      
      this.neck.rotateAngleY += this.body.rotateAngleY;
      float f1 = 1.0F - this.swingProgress;
      f1 *= f1;
      f1 *= f1;
      f1 = 1.0F - f1;
      float f2 = MathHelper.sin(f1 * 3.1415927F);
      float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.1F) * 0.15F;
      this.neck.rotateAngleX = 1.0F + (float)(this.neck.rotateAngleX - f2 * 1.5D + f3);
      this.neck.rotateAngleZ -= MathHelper.sin(this.swingProgress * 3.1415927F) * -1.0F;
    } 
  }



  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}



  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    this.neck.translateRotate(matrixStack);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
    matrixStack.rotate(Vector3f.YP.rotationDegrees(50.0F));
    matrixStack.rotate(Vector3f.XP.rotationDegrees(270.0F));
    matrixStack.translate(-0.7D, 0.8D, 0.0D);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


