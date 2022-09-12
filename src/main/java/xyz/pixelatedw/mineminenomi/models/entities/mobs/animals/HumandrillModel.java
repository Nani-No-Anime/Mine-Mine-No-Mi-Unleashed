package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.HumandrillEntity;

public class HumandrillModel<T extends HumandrillEntity> extends BipedModel<T> {
  private final ModelRenderer head;
  private final ModelRenderer head6_r1;
  private final ModelRenderer head5_r1;
  private final ModelRenderer body;
  private final ModelRenderer leftLeg;
  private final ModelRenderer leftLeg2;
  private final ModelRenderer leftFeet;
  private final ModelRenderer leftArm;
  private final ModelRenderer leftArm2;
  private final ModelRenderer leftHand;
  private final ModelRenderer rightArm;
  private final ModelRenderer rightArm2;
  private final ModelRenderer righHand;
  private final ModelRenderer rightLeg;
  private final ModelRenderer rightLeg2;
  private final ModelRenderer rightFeet;
  
  public HumandrillModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 128;
    
    this.bipedBody.showModel = false;
    this.bipedHead.showModel = false;
    this.bipedHeadwear.showModel = false;
    this.bipedLeftArm.showModel = false;
    this.bipedLeftLeg.showModel = false;
    this.bipedRightArm.showModel = false;
    this.bipedRightLeg.showModel = false;
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(0.0F, -11.0F, -1.0F);
    this.head.setTextureOffset(51, 0).addBox(-4.0F, -3.0F, -6.0F, 9.0F, 2.0F, 7.0F, 0.0F, false);
    this.head.setTextureOffset(84, 12).addBox(-2.0F, -6.0F, -6.0F, 5.0F, 3.0F, 6.0F, 0.0F, false);
    this.head.setTextureOffset(90, 76).addBox(-1.0F, -8.0F, -5.0F, 3.0F, 2.0F, 4.0F, 0.0F, false);
    this.head.setTextureOffset(71, 76).addBox(-2.0F, -1.0F, -7.0F, 5.0F, 2.0F, 8.0F, 0.0F, false);
    this.head.setTextureOffset(76, 2).addBox(-1.0F, 1.0F, -7.0F, 3.0F, 1.0F, 8.0F, 0.0F, false);
    this.head.setTextureOffset(82, 67).addBox(-1.0F, -2.0F, -7.0F, 3.0F, 1.0F, 7.0F, 0.0F, false);
    
    this.head6_r1 = new ModelRenderer((Model)this);
    this.head6_r1.setRotationPoint(3.082F, -2.1495F, -4.0F);
    this.head.addChild(this.head6_r1);
    setRotationAngle(this.head6_r1, 0.0F, 0.0F, -0.5672F);
    this.head6_r1.setTextureOffset(90, 39).addBox(-2.0F, 0.0F, -1.5F, 3.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.head5_r1 = new ModelRenderer((Model)this);
    this.head5_r1.setRotationPoint(-2.082F, -2.1495F, -4.0F);
    this.head.addChild(this.head5_r1);
    setRotationAngle(this.head5_r1, 0.0F, 0.0F, 0.5672F);
    this.head5_r1.setTextureOffset(25, 89).addBox(-1.0F, 0.0F, -1.5F, 3.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.body.setTextureOffset(46, 39).addBox(-8.0F, -36.0F, -4.0F, 17.0F, 1.0F, 9.0F, 0.0F, false);
    this.body.setTextureOffset(0, 23).addBox(-9.0F, -35.0F, -4.0F, 19.0F, 4.0F, 11.0F, 0.0F, false);
    this.body.setTextureOffset(0, 0).addBox(-9.0F, -31.0F, -4.0F, 19.0F, 10.0F, 12.0F, 0.0F, false);
    this.body.setTextureOffset(0, 83).addBox(-5.0F, -31.0F, -5.0F, 11.0F, 10.0F, 1.0F, 0.0F, false);
    this.body.setTextureOffset(50, 74).addBox(-6.0F, -30.0F, -4.75F, 13.0F, 8.0F, 1.0F, 0.0F, false);
    this.body.setTextureOffset(0, 39).addBox(-8.0F, -21.0F, -4.0F, 17.0F, 1.0F, 11.0F, 0.0F, false);
    this.body.setTextureOffset(47, 50).addBox(-7.0F, -20.0F, -4.0F, 15.0F, 1.0F, 10.0F, 0.0F, false);
    this.body.setTextureOffset(50, 84).addBox(-4.0F, -32.0F, -4.75F, 9.0F, 12.0F, 1.0F, 0.0F, false);
    
    this.leftLeg = new ModelRenderer((Model)this);
    this.leftLeg.setRotationPoint(4.0F, 5.0F, 1.0F);
    this.leftLeg.setTextureOffset(25, 74).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);
    
    this.leftLeg2 = new ModelRenderer((Model)this);
    this.leftLeg2.setRotationPoint(3.0F, 19.0F, -1.0F);
    this.leftLeg.addChild(this.leftLeg2);
    this.leftLeg2.setTextureOffset(88, 88).addBox(-5.0F, -11.0F, -1.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
    
    this.leftFeet = new ModelRenderer((Model)this);
    this.leftFeet.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftLeg2.addChild(this.leftFeet);
    this.leftFeet.setTextureOffset(61, 31).addBox(-5.0F, -2.0F, -3.0F, 4.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.leftArm = new ModelRenderer((Model)this);
    this.leftArm.setRotationPoint(9.0F, -5.75F, 3.0F);
    setRotationAngle(this.leftArm, 0.0F, 0.0F, 1.5708F);
    this.leftArm.setTextureOffset(55, 15).addBox(-3.0608F, -7.3054F, -5.0F, 10.0F, 7.0F, 8.0F, 0.0F, false);
    
    this.leftArm2 = new ModelRenderer((Model)this);
    this.leftArm2.setRotationPoint(-13.0608F, 26.6946F, -3.0F);
    this.leftArm.addChild(this.leftArm2);
    this.leftArm2.setTextureOffset(60, 62).addBox(20.0F, -33.0F, -1.0F, 8.0F, 5.0F, 6.0F, 0.0F, false);
    
    this.leftHand = new ModelRenderer((Model)this);
    this.leftHand.setRotationPoint(1.0608F, 0.0554F, 0.0F);
    this.leftArm2.addChild(this.leftHand);
    this.leftHand.setTextureOffset(88, 50).addBox(27.0F, -32.0F, 0.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
    
    this.rightArm = new ModelRenderer((Model)this);
    this.rightArm.setRotationPoint(-7.0F, -5.75F, 3.0F);
    setRotationAngle(this.rightArm, 0.0F, 0.0F, 1.5708F);
    this.rightArm.setTextureOffset(0, 52).addBox(-3.0608F, 1.6946F, -5.0F, 10.0F, 7.0F, 8.0F, 0.0F, false);
    
    this.rightArm2 = new ModelRenderer((Model)this);
    this.rightArm2.setRotationPoint(-13.0608F, 35.6946F, -3.0F);
    this.rightArm.addChild(this.rightArm2);
    this.rightArm2.setTextureOffset(31, 62).addBox(20.0F, -33.0F, -1.0F, 8.0F, 5.0F, 6.0F, 0.0F, false);
    
    this.righHand = new ModelRenderer((Model)this);
    this.righHand.setRotationPoint(1.0608F, 0.0554F, 0.0F);
    this.rightArm2.addChild(this.righHand);
    this.righHand.setTextureOffset(80, 31).addBox(27.0F, -32.0F, 0.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
    
    this.rightLeg = new ModelRenderer((Model)this);
    this.rightLeg.setRotationPoint(-3.0F, 5.0F, 1.0F);
    this.rightLeg.setTextureOffset(0, 68).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);
    
    this.rightLeg2 = new ModelRenderer((Model)this);
    this.rightLeg2.setRotationPoint(3.0F, 19.0F, -1.0F);
    this.rightLeg.addChild(this.rightLeg2);
    this.rightLeg2.setTextureOffset(71, 87).addBox(-5.0F, -11.0F, -1.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
    
    this.rightFeet = new ModelRenderer((Model)this);
    this.rightFeet.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.rightLeg2.addChild(this.rightFeet);
    this.rightFeet.setTextureOffset(29, 52).addBox(-5.0F, -2.0F, -3.0F, 4.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.bipedBody = this.body;
    this.bipedHead = this.head;
    this.bipedRightArm = this.rightArm;
    this.bipedLeftArm = this.leftArm;
    this.bipedRightLeg = this.rightLeg;
    this.bipedLeftLeg = this.leftLeg;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.head.render(matrixStack, buffer, packedLight, packedOverlay);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
  }





  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.rightArm.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / 1.0F;
    this.leftArm.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / 1.0F;
    this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / 1.0F;
    this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / 1.0F;
    if (!entity.getHeldItemMainhand().isEmpty()) {
      this.rightArm.rotateAngleY -= -0.15F;
    }
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    super.translateHand(side, matrixStack);
    matrixStack.rotate(Vector3f.ZN.rotationDegrees(90.0F));
    matrixStack.translate(-0.25D, 0.55D, -0.05D);
  }
}


