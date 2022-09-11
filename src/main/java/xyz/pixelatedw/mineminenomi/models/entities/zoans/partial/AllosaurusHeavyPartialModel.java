package xyz.pixelatedw.mineminenomi.models.entities.zoans.partial;

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
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;

public class AllosaurusHeavyPartialModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer tail;
  private final ModelRenderer tail2;
  private final ModelRenderer tail3;
  private final ModelRenderer leftLeg;
  private final ModelRenderer leftLeg2;
  private final ModelRenderer leftFeet;
  private final ModelRenderer leftFeet2;
  private final ModelRenderer leftToe1;
  private final ModelRenderer leftToe2;
  private final ModelRenderer leftToe3;
  private final ModelRenderer leftToe4;
  private final ModelRenderer rightLeg;
  private final ModelRenderer rightLeg2;
  private final ModelRenderer rightFeet;
  private final ModelRenderer rightFeet2;
  private final ModelRenderer rightToe;
  private final ModelRenderer rightToe2;
  private final ModelRenderer rightToe3;
  private final ModelRenderer rightToe4;
  public final ModelRenderer bodyScales;
  public final ModelRenderer headScales;
  public final ModelRenderer leftArmScales;
  public final ModelRenderer rightArmScales;
  
  public AllosaurusHeavyPartialModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 128;
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(0.0F, 8.75F, 1.5F);
    setRotationAngle(this.tail, -0.1309F, 0.0F, 0.0F);
    this.tail.setTextureOffset(25, 28).addBox(-3.0F, -5.7243F, -0.3916F, 6.0F, 6.0F, 10.0F, 0.0F, false);
    
    this.tail2 = new ModelRenderer((Model)this);
    this.tail2.setRotationPoint(0.0F, 0.0F, 10.0F);
    this.tail.addChild(this.tail2);
    setRotationAngle(this.tail2, 0.0873F, 0.0F, 0.0F);
    this.tail2.setTextureOffset(26, 45).addBox(-2.5F, -5.2471F, -0.6308F, 5.0F, 5.0F, 10.0F, 0.0F, false);
    
    this.tail3 = new ModelRenderer((Model)this);
    this.tail3.setRotationPoint(0.0F, 0.0F, 8.0F);
    this.tail2.addChild(this.tail3);
    setRotationAngle(this.tail3, 0.0436F, 0.0F, 0.0F);
    this.tail3.setTextureOffset(22, 61).addBox(-2.0F, -4.75F, 0.5F, 4.0F, 4.0F, 15.0F, 0.0F, false);
    
    this.leftLeg = new ModelRenderer((Model)this);
    this.leftLeg.setRotationPoint(2.5F, 11.396F, 0.0604F);
    setRotationAngle(this.leftLeg, 0.2182F, 0.0F, 0.0F);
    this.leftLeg.setTextureOffset(0, 34).addBox(-0.5F, -3.3918F, -2.0119F, 3.0F, 8.0F, 5.0F, 0.01F, false);
    
    this.leftLeg2 = new ModelRenderer((Model)this);
    this.leftLeg2.setRotationPoint(0.0F, 3.8013F, -0.0043F);
    this.leftLeg.addChild(this.leftLeg2);
    setRotationAngle(this.leftLeg2, -0.6981F, 0.0F, 0.0F);
    this.leftLeg2.setTextureOffset(1, 49).addBox(-0.5F, -1.4808F, -1.3065F, 3.0F, 9.0F, 4.0F, 0.0F, false);
    
    this.leftFeet = new ModelRenderer((Model)this);
    this.leftFeet.setRotationPoint(-14.0F, 5.7326F, 2.6722F);
    this.leftLeg2.addChild(this.leftFeet);
    setRotationAngle(this.leftFeet, 0.48F, 0.0F, 0.0F);
    this.leftFeet.setTextureOffset(0, 63).addBox(13.0F, -0.5F, -4.5F, 4.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.leftFeet2 = new ModelRenderer((Model)this);
    this.leftFeet2.setRotationPoint(0.0F, 0.5F, -3.0F);
    this.leftFeet.addChild(this.leftFeet2);
    this.leftFeet2.setTextureOffset(0, 72).addBox(12.5F, 0.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.01F, false);
    
    this.leftToe1 = new ModelRenderer((Model)this);
    this.leftToe1.setRotationPoint(16.8372F, 1.3799F, -3.6384F);
    this.leftFeet2.addChild(this.leftToe1);
    setRotationAngle(this.leftToe1, 0.0873F, -0.1745F, -0.0036F);
    this.leftToe1.setTextureOffset(0, 80).addBox(-0.1632F, -0.957F, -1.5095F, 1.0F, 2.0F, 3.0F, 0.0F, false);
    
    this.leftToe2 = new ModelRenderer((Model)this);
    this.leftToe2.setRotationPoint(15.0F, 1.5361F, -3.8781F);
    this.leftFeet2.addChild(this.leftToe2);
    setRotationAngle(this.leftToe2, 0.0873F, 0.0F, 0.0F);
    this.leftToe2.setTextureOffset(0, 80).addBox(-0.5F, -1.2064F, -1.5019F, 1.0F, 2.0F, 3.0F, 0.0F, false);
    
    this.leftToe3 = new ModelRenderer((Model)this);
    this.leftToe3.setRotationPoint(12.3246F, 1.2317F, -3.7439F);
    this.leftFeet2.addChild(this.leftToe3);
    setRotationAngle(this.leftToe3, 0.0873F, 0.1745F, 0.0F);
    this.leftToe3.setTextureOffset(0, 80).addBox(-0.0868F, -0.957F, -1.5095F, 1.0F, 2.0F, 3.0F, 0.0F, false);
    
    this.leftToe4 = new ModelRenderer((Model)this);
    this.leftToe4.setRotationPoint(14.5F, -1.038F, 3.3855F);
    this.leftFeet2.addChild(this.leftToe4);
    setRotationAngle(this.leftToe4, -0.5672F, 0.0F, 0.0F);
    this.leftToe4.setTextureOffset(9, 81).addBox(0.0F, -0.2686F, -1.0783F, 1.0F, 1.0F, 3.0F, 0.0F, false);
    
    this.rightLeg = new ModelRenderer((Model)this);
    this.rightLeg.setRotationPoint(-2.5F, 11.396F, 0.0604F);
    setRotationAngle(this.rightLeg, 0.2182F, 0.0F, 0.0F);
    this.rightLeg.setTextureOffset(0, 34).addBox(-2.5F, -3.3918F, -2.0119F, 3.0F, 8.0F, 5.0F, 0.01F, false);
    
    this.rightLeg2 = new ModelRenderer((Model)this);
    this.rightLeg2.setRotationPoint(-2.0F, 3.8013F, -0.0043F);
    this.rightLeg.addChild(this.rightLeg2);
    setRotationAngle(this.rightLeg2, -0.6981F, 0.0F, 0.0F);
    this.rightLeg2.setTextureOffset(1, 49).addBox(-0.5F, -1.4808F, -1.3065F, 3.0F, 9.0F, 4.0F, 0.0F, false);
    
    this.rightFeet = new ModelRenderer((Model)this);
    this.rightFeet.setRotationPoint(-14.0F, 5.7326F, 2.6722F);
    this.rightLeg2.addChild(this.rightFeet);
    setRotationAngle(this.rightFeet, 0.48F, 0.0F, 0.0F);
    this.rightFeet.setTextureOffset(0, 63).addBox(13.0F, -0.5F, -4.5F, 4.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.rightFeet2 = new ModelRenderer((Model)this);
    this.rightFeet2.setRotationPoint(0.0F, 0.5F, -3.0F);
    this.rightFeet.addChild(this.rightFeet2);
    this.rightFeet2.setTextureOffset(0, 72).addBox(12.5F, 0.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.01F, false);
    
    this.rightToe = new ModelRenderer((Model)this);
    this.rightToe.setRotationPoint(16.8372F, 1.3799F, -3.6384F);
    this.rightFeet2.addChild(this.rightToe);
    setRotationAngle(this.rightToe, 0.0873F, -0.1745F, -0.0036F);
    this.rightToe.setTextureOffset(0, 80).addBox(-0.1632F, -0.957F, -1.5095F, 1.0F, 2.0F, 3.0F, 0.0F, false);
    
    this.rightToe2 = new ModelRenderer((Model)this);
    this.rightToe2.setRotationPoint(15.0F, 1.5361F, -3.8781F);
    this.rightFeet2.addChild(this.rightToe2);
    setRotationAngle(this.rightToe2, 0.0873F, 0.0F, 0.0F);
    this.rightToe2.setTextureOffset(0, 80).addBox(-0.5F, -1.2064F, -1.5019F, 1.0F, 2.0F, 3.0F, 0.0F, false);
    
    this.rightToe3 = new ModelRenderer((Model)this);
    this.rightToe3.setRotationPoint(12.3246F, 1.2317F, -3.7439F);
    this.rightFeet2.addChild(this.rightToe3);
    setRotationAngle(this.rightToe3, 0.0873F, 0.1745F, 0.0F);
    this.rightToe3.setTextureOffset(0, 80).addBox(-0.0868F, -0.957F, -1.5095F, 1.0F, 2.0F, 3.0F, 0.0F, false);
    
    this.rightToe4 = new ModelRenderer((Model)this);
    this.rightToe4.setRotationPoint(14.5F, -1.038F, 3.3855F);
    this.rightFeet2.addChild(this.rightToe4);
    setRotationAngle(this.rightToe4, -0.5672F, 0.0F, 0.0F);
    this.rightToe4.setTextureOffset(9, 81).addBox(0.0F, -0.2686F, -1.0783F, 1.0F, 1.0F, 3.0F, 0.0F, false);
    
    this.bodyScales = new ModelRenderer((Model)this);
    this.bodyScales.setRotationPoint(0.0F, -3.0F, 0.0F);
    this.bodyScales.setTextureOffset(0, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.01F, false);
    
    this.headScales = new ModelRenderer((Model)this);
    this.headScales.setRotationPoint(0.0F, -3.0F, 0.0F);
    this.headScales.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.01F, false);
    
    this.leftArmScales = new ModelRenderer((Model)this);
    this.leftArmScales.setRotationPoint(5.0F, -1.0F, 0.0F);
    this.leftArmScales.setTextureOffset(34, 4).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.01F, false);
    
    this.rightArmScales = new ModelRenderer((Model)this);
    this.rightArmScales.setRotationPoint(-5.0F, -1.0F, 0.0F);
    this.rightArmScales.setTextureOffset(34, 4).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.01F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.tail.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    matrixStack.push();
    float scale = 1.1F;
    matrixStack.scale(scale, scale, scale);
    matrixStack.translate(0.0D, -0.18D, 0.0D);
    this.bodyScales.render(matrixStack, buffer, packedLight, packedOverlay);
    this.headScales.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftArmScales.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightArmScales.render(matrixStack, buffer, packedLight, packedOverlay);
    matrixStack.pop();
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    float limbSpeed = 0.5F;
    if (entity.isSprinting())
      limbSpeed = 0.7F; 
    this.rightLeg.rotateAngleX = 0.2F + MathHelper.cos(limbSwing * limbSpeed) * 0.5F * limbSwingAmount;
    this.rightLeg2.rotateAngleX = -0.7F - MathHelper.sin(limbSwing * limbSpeed) * 0.5F * limbSwingAmount;
    this.leftLeg.rotateAngleX = 0.2F + MathHelper.cos(limbSwing * limbSpeed + 3.1415927F) * 0.5F * limbSwingAmount;
    this.leftLeg2.rotateAngleX = -0.7F - MathHelper.sin(limbSwing * limbSpeed + 3.1415927F) * 0.5F * limbSwingAmount;
    
    this.tail.rotateAngleY = (float)(this.tail.rotateAngleY + Math.sin(ageInTicks * 0.01D) / 20.0D);
    this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + Math.sin(ageInTicks * 0.005D) / 10.0D);
    this.tail2.rotateAngleY = (float)(this.tail2.rotateAngleY + Math.sin(ageInTicks * 0.09D) / 20.0D);
    this.tail2.rotateAngleX = (float)(this.tail2.rotateAngleX + Math.sin(ageInTicks * 0.01D) / 10.0D);
    this.tail3.rotateAngleY = (float)(this.tail3.rotateAngleY + Math.sin(ageInTicks * 0.1D) / 20.0D);
    this.tail3.rotateAngleX = (float)(this.tail3.rotateAngleX + Math.sin(ageInTicks * 0.05D) / 10.0D);

    
    if (entity.isSneaking()) {
      
      this.rightLeg.rotationPointZ = 2.0F;
      this.rightLeg.rotationPointY = 9.5F;
      this.leftLeg.rotationPointZ = 2.0F;
      this.leftLeg.rotationPointY = 9.5F;
      this.tail.rotationPointZ = 3.9F;
      this.tail.rotationPointY = 11.0F;
    } 

    
    this.swingProgress = ((LivingEntity)entity).swingProgress;
    boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
    if (this.swingProgress > 0.0F)
    {
      if (isBlackLeg) {
        
        this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
        this.rightLeg.rotateAngleY += this.bipedBody.rotateAngleY;
        this.leftLeg.rotateAngleY += this.bipedBody.rotateAngleY;
        float f1 = 1.0F - this.swingProgress;
        f1 *= f1;
        f1 *= f1;
        f1 = 1.0F - f1;
        float f2 = MathHelper.sin(f1 * 3.1415927F);
        float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
        this.rightLeg.rotateAngleX = (float)(this.bipedRightArm.rotateAngleX - f2 * 1.5D + f3);
        this.rightLeg.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
      } 
    }
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    super.translateHand(side, matrixStack);
    matrixStack.translate(0.0D, 0.4D, 0.0D);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees((side == HandSide.RIGHT) ? -20.0F : 20.0F));
  }


  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}

  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(0.0D, -0.8D, 0.3D);
      matrixStack.scale(1.5F, 1.5F, 1.5F);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-60.0F));
      this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    }
    else {
      
      matrixStack.translate(0.0D, -0.8D, 0.3D);
      matrixStack.scale(1.5F, 1.5F, 1.5F);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(60.0F));
      this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    } 
  }
}


