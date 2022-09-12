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

public class BrachiosaurusGuardModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer rightBackLeg;
  private final ModelRenderer rightBackLeg_r1;
  private final ModelRenderer rightBackLeg2;
  private final ModelRenderer rightBackLeg2_r1;
  private final ModelRenderer leftBackLeg;
  private final ModelRenderer leftBackLeg_r1;
  private final ModelRenderer leftBackLeg2;
  private final ModelRenderer leftBackLeg2_r1;
  private final ModelRenderer rightFrontLeg;
  private final ModelRenderer rightFrontLeg_r1;
  private final ModelRenderer rightFrontLeg2;
  private final ModelRenderer rightFrontLeg2_r1;
  private final ModelRenderer leftFrontLeg;
  private final ModelRenderer leftFrontLeg_r1;
  private final ModelRenderer leftFrontLeg2;
  private final ModelRenderer leftFrontLeg2_r1;
  private final ModelRenderer body;
  private final ModelRenderer tail;
  private final ModelRenderer tail2;
  private final ModelRenderer tail3;
  private final ModelRenderer head;
  private final ModelRenderer mouth;
  private final ModelRenderer upperMouth;
  private final ModelRenderer upperMouthTeeth;
  private final ModelRenderer lowerMouth;
  private final ModelRenderer lowerMouthTeeth;
  private final ModelRenderer neck;
  private final ModelRenderer neck1_r1;
  private final ModelRenderer neck2;
  private final ModelRenderer neck2_r1;
  private final ModelRenderer neck3;
  private final ModelRenderer neck3_r1;
  private final ModelRenderer neck4;
  private final ModelRenderer neck4_r1;
  private final ModelRenderer neck5;
  private final ModelRenderer neck5_r1;
  private final ModelRenderer neck6;
  private final ModelRenderer neck6_r1;
  private final ModelRenderer neck7;
  private final ModelRenderer neck7_r1;
  
  public BrachiosaurusGuardModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 128;
    
    this.rightBackLeg = new ModelRenderer((Model)this);
    this.rightBackLeg.setRotationPoint(-3.0F, 10.3009F, 6.3044F);

    
    this.rightBackLeg_r1 = new ModelRenderer((Model)this);
    this.rightBackLeg_r1.setRotationPoint(-2.0F, 1.0F, 0.0F);
    this.rightBackLeg.addChild(this.rightBackLeg_r1);
    setRotationAngle(this.rightBackLeg_r1, -0.1745F, 0.0F, 0.0F);
    this.rightBackLeg_r1.setTextureOffset(22, 92).addBox(-2.0F, -3.5F, -3.0F, 4.0F, 7.0F, 6.0F, 0.01F, false);
    
    this.rightBackLeg2 = new ModelRenderer((Model)this);
    this.rightBackLeg2.setRotationPoint(-2.0F, 9.3921F, -0.0439F);
    this.rightBackLeg.addChild(this.rightBackLeg2);
    this.rightBackLeg2.setTextureOffset(21, 119).addBox(-2.0F, 0.4158F, -2.1965F, 4.0F, 4.0F, 5.0F, -0.01F, false);
    
    this.rightBackLeg2_r1 = new ModelRenderer((Model)this);
    this.rightBackLeg2_r1.setRotationPoint(0.0F, -2.4158F, -0.3035F);
    this.rightBackLeg2.addChild(this.rightBackLeg2_r1);
    setRotationAngle(this.rightBackLeg2_r1, 0.1745F, 0.0F, 0.0F);
    this.rightBackLeg2_r1.setTextureOffset(21, 106).addBox(-2.0F, -3.5F, -2.5F, 4.0F, 7.0F, 5.0F, 0.0F, false);
    
    this.leftBackLeg = new ModelRenderer((Model)this);
    this.leftBackLeg.setRotationPoint(3.0F, 10.3009F, 6.3044F);

    
    this.leftBackLeg_r1 = new ModelRenderer((Model)this);
    this.leftBackLeg_r1.setRotationPoint(2.0F, 1.0F, 0.0F);
    this.leftBackLeg.addChild(this.leftBackLeg_r1);
    setRotationAngle(this.leftBackLeg_r1, -0.1745F, 0.0F, 0.0F);
    this.leftBackLeg_r1.setTextureOffset(22, 92).addBox(-2.0F, -3.5F, -3.0F, 4.0F, 7.0F, 6.0F, 0.01F, false);
    
    this.leftBackLeg2 = new ModelRenderer((Model)this);
    this.leftBackLeg2.setRotationPoint(2.0F, 9.3921F, -0.0439F);
    this.leftBackLeg.addChild(this.leftBackLeg2);
    this.leftBackLeg2.setTextureOffset(21, 119).addBox(-2.0F, 0.4158F, -2.1965F, 4.0F, 4.0F, 5.0F, -0.01F, false);
    
    this.leftBackLeg2_r1 = new ModelRenderer((Model)this);
    this.leftBackLeg2_r1.setRotationPoint(0.0F, -2.4158F, -0.3035F);
    this.leftBackLeg2.addChild(this.leftBackLeg2_r1);
    setRotationAngle(this.leftBackLeg2_r1, 0.1745F, 0.0F, 0.0F);
    this.leftBackLeg2_r1.setTextureOffset(21, 106).addBox(-2.0F, -3.5F, -2.5F, 4.0F, 7.0F, 5.0F, 0.0F, false);
    
    this.rightFrontLeg = new ModelRenderer((Model)this);
    this.rightFrontLeg.setRotationPoint(-4.0F, 10.9056F, -7.0765F);

    
    this.rightFrontLeg_r1 = new ModelRenderer((Model)this);
    this.rightFrontLeg_r1.setRotationPoint(-2.5F, 1.0F, 0.0F);
    this.rightFrontLeg.addChild(this.rightFrontLeg_r1);
    setRotationAngle(this.rightFrontLeg_r1, 0.1745F, 0.0F, 0.0F);
    this.rightFrontLeg_r1.setTextureOffset(0, 91).addBox(-1.5F, -3.5F, -3.0F, 4.0F, 7.0F, 6.0F, 0.0F, false);
    
    this.rightFrontLeg2 = new ModelRenderer((Model)this);
    this.rightFrontLeg2.setRotationPoint(-2.0F, 4.4468F, 0.7974F);
    this.rightFrontLeg.addChild(this.rightFrontLeg2);
    this.rightFrontLeg2.setTextureOffset(0, 118).addBox(-2.0F, 5.5064F, -3.7849F, 4.0F, 4.0F, 5.0F, 0.01F, false);
    
    this.rightFrontLeg2_r1 = new ModelRenderer((Model)this);
    this.rightFrontLeg2_r1.setRotationPoint(0.0F, 2.4936F, -0.7151F);
    this.rightFrontLeg2.addChild(this.rightFrontLeg2_r1);
    setRotationAngle(this.rightFrontLeg2_r1, -0.1745F, 0.0F, 0.0F);
    this.rightFrontLeg2_r1.setTextureOffset(0, 105).addBox(-2.0F, -3.5F, -2.5F, 4.0F, 7.0F, 5.0F, 0.01F, false);
    
    this.leftFrontLeg = new ModelRenderer((Model)this);
    this.leftFrontLeg.setRotationPoint(4.0F, 10.9056F, -7.0765F);

    
    this.leftFrontLeg_r1 = new ModelRenderer((Model)this);
    this.leftFrontLeg_r1.setRotationPoint(1.5F, 1.0F, 0.0F);
    this.leftFrontLeg.addChild(this.leftFrontLeg_r1);
    setRotationAngle(this.leftFrontLeg_r1, 0.1745F, 0.0F, 0.0F);
    this.leftFrontLeg_r1.setTextureOffset(0, 91).addBox(-1.5F, -3.5F, -3.0F, 4.0F, 7.0F, 6.0F, 0.0F, false);
    
    this.leftFrontLeg2 = new ModelRenderer((Model)this);
    this.leftFrontLeg2.setRotationPoint(2.0F, 4.4468F, 0.7974F);
    this.leftFrontLeg.addChild(this.leftFrontLeg2);
    this.leftFrontLeg2.setTextureOffset(0, 118).addBox(-2.0F, 5.5064F, -3.7849F, 4.0F, 4.0F, 5.0F, 0.01F, false);
    
    this.leftFrontLeg2_r1 = new ModelRenderer((Model)this);
    this.leftFrontLeg2_r1.setRotationPoint(0.0F, 2.4936F, -0.7151F);
    this.leftFrontLeg2.addChild(this.leftFrontLeg2_r1);
    setRotationAngle(this.leftFrontLeg2_r1, -0.1745F, 0.0F, 0.0F);
    this.leftFrontLeg2_r1.setTextureOffset(0, 105).addBox(-2.0F, -3.5F, -2.5F, 4.0F, 7.0F, 5.0F, 0.01F, false);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.0F, 7.375F, -4.0F);
    this.body.setTextureOffset(0, 0).addBox(-5.0F, -8.375F, -4.0F, 10.0F, 17.0F, 14.0F, 0.0F, false);
    this.body.setTextureOffset(0, 32).addBox(-4.5F, -7.375F, -7.0F, 9.0F, 15.0F, 3.0F, 0.0F, false);
    this.body.setTextureOffset(0, 51).addBox(-4.0F, -6.875F, -9.0F, 8.0F, 13.0F, 2.0F, 0.0F, false);
    this.body.setTextureOffset(50, 0).addBox(-4.5F, -7.375F, 9.0F, 9.0F, 15.0F, 3.0F, 0.0F, false);
    this.body.setTextureOffset(50, 19).addBox(-4.0F, -6.875F, 12.0F, 8.0F, 13.0F, 3.0F, 0.0F, false);
    this.body.setTextureOffset(28, 33).addBox(-3.5F, -6.375F, 15.0F, 7.0F, 12.0F, 3.0F, 0.0F, false);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(0.0F, 0.0F, 18.0F);
    this.body.addChild(this.tail);
    this.tail.setTextureOffset(77, 1).addBox(-2.0F, -3.0F, -1.0F, 4.0F, 6.0F, 10.0F, 0.0F, false);
    
    this.tail2 = new ModelRenderer((Model)this);
    this.tail2.setRotationPoint(-0.5F, -0.75F, 6.0F);
    this.tail.addChild(this.tail2);
    this.tail2.setTextureOffset(74, 18).addBox(-1.0F, -2.0F, 0.0F, 3.0F, 5.0F, 14.0F, 0.0F, false);
    
    this.tail3 = new ModelRenderer((Model)this);
    this.tail3.setRotationPoint(-0.5F, -0.75F, 8.0F);
    this.tail2.addChild(this.tail3);
    this.tail3.setTextureOffset(72, 38).addBox(0.0F, -1.0F, 0.0F, 2.0F, 4.0F, 15.0F, 0.0F, false);
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(0.0F, 4.0F, -11.0F);

    
    this.mouth = new ModelRenderer((Model)this);
    this.mouth.setRotationPoint(-1.0F, -109.0F, -14.0F);
    this.head.addChild(this.mouth);

    
    this.upperMouth = new ModelRenderer((Model)this);
    this.upperMouth.setRotationPoint(1.0F, 86.1616F, 4.9651F);
    this.mouth.addChild(this.upperMouth);
    setRotationAngle(this.upperMouth, -0.1309F, 0.0F, 0.0F);
    this.upperMouth.setTextureOffset(68, 100).addBox(-2.5F, -3.0999F, -2.6049F, 5.0F, 4.0F, 3.0F, 0.0F, false);
    this.upperMouth.setTextureOffset(68, 108).addBox(-2.0F, -2.0999F, -3.6049F, 4.0F, 3.0F, 1.0F, 0.0F, false);
    this.upperMouth.setTextureOffset(68, 113).addBox(-1.5F, -1.0999F, -5.6049F, 3.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.upperMouthTeeth = new ModelRenderer((Model)this);
    this.upperMouthTeeth.setRotationPoint(-1.0F, -79.1968F, -23.5542F);
    this.upperMouth.addChild(this.upperMouthTeeth);
    this.upperMouthTeeth.setTextureOffset(68, 118).addBox(2.25F, 79.8469F, 18.1993F, 0.0F, 1.0F, 5.0F, 0.0F, false);
    this.upperMouthTeeth.setTextureOffset(68, 118).addBox(-0.25F, 79.8469F, 18.1993F, 0.0F, 1.0F, 5.0F, 0.0F, false);
    this.upperMouthTeeth.setTextureOffset(72, 126).addBox(0.0F, 79.8469F, 18.1993F, 2.0F, 1.0F, 0.0F, 0.0F, false);
    
    this.lowerMouth = new ModelRenderer((Model)this);
    this.lowerMouth.setRotationPoint(1.0F, 87.2041F, 4.72F);
    this.mouth.addChild(this.lowerMouth);
    setRotationAngle(this.lowerMouth, 0.2182F, 0.0F, 0.0F);
    this.lowerMouth.setTextureOffset(85, 104).addBox(-2.5F, 0.0434F, -1.3357F, 5.0F, 1.0F, 2.0F, 0.0F, false);
    this.lowerMouth.setTextureOffset(85, 109).addBox(-2.0F, 0.0434F, -2.3357F, 4.0F, 1.0F, 1.0F, 0.0F, false);
    this.lowerMouth.setTextureOffset(84, 112).addBox(-1.5F, 0.0434F, -5.3357F, 3.0F, 1.0F, 3.0F, 0.0F, false);
    
    this.lowerMouthTeeth = new ModelRenderer((Model)this);
    this.lowerMouthTeeth.setRotationPoint(-5.0F, -140.6931F, -19.4114F);
    this.lowerMouth.addChild(this.lowerMouthTeeth);
    this.lowerMouthTeeth.setTextureOffset(84, 118).addBox(6.3F, 139.9865F, 14.3257F, 0.0F, 1.0F, 5.0F, 0.0F, false);
    this.lowerMouthTeeth.setTextureOffset(84, 118).addBox(3.7F, 139.9865F, 14.3257F, 0.0F, 1.0F, 5.0F, 0.0F, false);
    this.lowerMouthTeeth.setTextureOffset(87, 126).addBox(4.0F, 139.9865F, 14.3257F, 2.0F, 1.0F, 0.0F, 0.0F, false);
    
    this.neck = new ModelRenderer((Model)this);
    this.neck.setRotationPoint(-0.5F, -1.7431F, -0.7119F);
    this.head.addChild(this.neck);

    
    this.neck1_r1 = new ModelRenderer((Model)this);
    this.neck1_r1.setRotationPoint(0.0F, -1.5359F, -2.0F);
    this.neck.addChild(this.neck1_r1);
    setRotationAngle(this.neck1_r1, 0.5236F, 0.0F, 0.0F);
    this.neck1_r1.setTextureOffset(45, 112).addBox(-2.0F, -4.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
    
    this.neck2 = new ModelRenderer((Model)this);
    this.neck2.setRotationPoint(0.0F, -4.0762F, -3.6967F);
    this.neck.addChild(this.neck2);

    
    this.neck2_r1 = new ModelRenderer((Model)this);
    this.neck2_r1.setRotationPoint(0.0F, -4.3879F, -2.3033F);
    this.neck2.addChild(this.neck2_r1);
    setRotationAngle(this.neck2_r1, 0.3491F, 0.0F, 0.0F);
    this.neck2_r1.setTextureOffset(45, 100).addBox(-2.0F, -1.25F, -1.75F, 5.0F, 6.0F, 5.0F, -0.01F, false);
    
    this.neck3 = new ModelRenderer((Model)this);
    this.neck3.setRotationPoint(0.0F, -4.6906F, -1.2016F);
    this.neck2.addChild(this.neck3);
    setRotationAngle(this.neck3, -0.1309F, 0.0F, 0.0F);

    
    this.neck3_r1 = new ModelRenderer((Model)this);
    this.neck3_r1.setRotationPoint(0.0F, -2.9848F, -1.1736F);
    this.neck3.addChild(this.neck3_r1);
    setRotationAngle(this.neck3_r1, 0.1745F, 0.0F, 0.0F);
    this.neck3_r1.setTextureOffset(45, 90).addBox(-2.0F, -1.0F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);
    
    this.neck4 = new ModelRenderer((Model)this);
    this.neck4.setRotationPoint(0.0F, -3.841F, -1.054F);
    this.neck3.addChild(this.neck4);

    
    this.neck4_r1 = new ModelRenderer((Model)this);
    this.neck4_r1.setRotationPoint(0.0F, -5.0526F, -1.1616F);
    this.neck4.addChild(this.neck4_r1);
    setRotationAngle(this.neck4_r1, -0.0436F, 0.0F, 0.0F);
    this.neck4_r1.setTextureOffset(45, 76).addBox(-2.0F, -1.5F, -1.25F, 5.0F, 7.0F, 5.0F, 0.01F, false);
    
    this.neck5 = new ModelRenderer((Model)this);
    this.neck5.setRotationPoint(0.0F, -5.9933F, 0.3053F);
    this.neck4.addChild(this.neck5);
    setRotationAngle(this.neck5, 0.3927F, 0.0F, 0.0F);

    
    this.neck5_r1 = new ModelRenderer((Model)this);
    this.neck5_r1.setRotationPoint(0.0F, 1.9407F, -1.4669F);
    this.neck5.addChild(this.neck5_r1);
    setRotationAngle(this.neck5_r1, -0.0436F, 0.0F, 0.0F);
    this.neck5_r1.setTextureOffset(45, 67).addBox(-2.0F, -4.5F, -1.25F, 5.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.neck6 = new ModelRenderer((Model)this);
    this.neck6.setRotationPoint(0.0F, -1.5F, 0.25F);
    this.neck5.addChild(this.neck6);
    setRotationAngle(this.neck6, 0.5672F, 0.0F, 0.0F);

    
    this.neck6_r1 = new ModelRenderer((Model)this);
    this.neck6_r1.setRotationPoint(0.0F, 1.9407F, -1.4669F);
    this.neck6.addChild(this.neck6_r1);
    setRotationAngle(this.neck6_r1, -0.0436F, 0.0F, 0.0F);
    this.neck6_r1.setTextureOffset(45, 58).addBox(-2.0F, -4.5F, -1.25F, 5.0F, 3.0F, 5.0F, 0.015F, false);
    
    this.neck7 = new ModelRenderer((Model)this);
    this.neck7.setRotationPoint(0.0F, -1.5F, 0.25F);
    this.neck6.addChild(this.neck7);
    setRotationAngle(this.neck7, 0.6545F, 0.0F, 0.0F);

    
    this.neck7_r1 = new ModelRenderer((Model)this);
    this.neck7_r1.setRotationPoint(0.0F, 1.9407F, -1.4669F);
    this.neck7.addChild(this.neck7_r1);
    setRotationAngle(this.neck7_r1, -0.0436F, 0.0F, 0.0F);
    this.neck7_r1.setTextureOffset(45, 49).addBox(-2.0F, -4.5F, -1.25F, 5.0F, 3.0F, 5.0F, 0.017F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.rightBackLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftBackLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
    this.head.render(matrixStack, buffer, packedLight, packedOverlay);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    float limbSpeed = 0.3F;
    if (entity.isSprinting())
      limbSpeed = 0.4F; 
    this.rightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * limbSpeed) * 0.3F * limbSwingAmount;
    this.leftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * limbSpeed) * 0.4F * limbSwingAmount;
    this.rightBackLeg.rotateAngleX = MathHelper.cos(limbSwing * limbSpeed + 3.1415927F) * 0.3F * limbSwingAmount;
    this.leftBackLeg.rotateAngleX = MathHelper.cos(limbSwing * limbSpeed + 3.1415927F) * 0.4F * limbSwingAmount;

    
    this.tail.rotateAngleY = (float)(this.tail.rotateAngleY + Math.sin(ageInTicks * 0.01D) / 20.0D);
    this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + Math.sin(ageInTicks * 0.005D) / 10.0D);
    this.tail2.rotateAngleY = (float)(this.tail2.rotateAngleY + Math.sin(ageInTicks * 0.01D) / 10.0D);
    this.tail2.rotateAngleX = (float)(this.tail2.rotateAngleX + Math.sin(ageInTicks * 0.005D) / 5.0D);
    this.tail3.rotateAngleY = (float)(this.tail3.rotateAngleY + Math.sin(ageInTicks * 0.01D) / 10.0D);
    this.tail3.rotateAngleX = (float)(this.tail3.rotateAngleX + Math.sin(ageInTicks * 0.005D) / 5.0D);

    
    this.swingProgress = ((LivingEntity)entity).swingProgress;
    if (this.swingProgress > 0.0F) {
      
      this.head.rotateAngleY += this.body.rotateAngleY;
      float f1 = 1.0F - this.swingProgress;
      f1 *= f1;
      f1 *= f1;
      f1 = 1.0F - f1;
      float f2 = MathHelper.sin(f1 * 3.1415927F);
      float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.1F) * 0.15F;
      this.head.rotateAngleX = (float)(this.head.rotateAngleX + f2 * 1.8D + f3);
      this.head.rotateAngleZ -= MathHelper.sin(this.swingProgress * 3.1415927F) * -0.2F;
    } 
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }



  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}



  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    this.head.translateRotate(matrixStack);
    matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
    matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
    matrixStack.translate(-1.3D, 0.25D, -0.05D);
  }
}


