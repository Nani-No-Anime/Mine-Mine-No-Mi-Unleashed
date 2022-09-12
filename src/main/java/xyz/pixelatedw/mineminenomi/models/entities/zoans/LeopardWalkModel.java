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

public class LeopardWalkModel<T extends LivingEntity>
  extends ZoanMorphModel<T>
  implements IHasArm {
  private final ModelRenderer body;
  private final ModelRenderer body2;
  private final ModelRenderer neck;
  private final ModelRenderer head;
  private final ModelRenderer leftEar;
  private final ModelRenderer rightEar;
  private final ModelRenderer snout;
  private final ModelRenderer snout2;
  private final ModelRenderer tail;
  private final ModelRenderer tail2;
  private final ModelRenderer tail3;
  private final ModelRenderer leftRearLeg;
  private final ModelRenderer leftRearLeg2;
  private final ModelRenderer leftRearLeg3;
  private final ModelRenderer leftRearFoot;
  private final ModelRenderer rightRearLeg;
  private final ModelRenderer rightRearLeg2;
  private final ModelRenderer rightRearLeg3;
  private final ModelRenderer rightRearFoot;
  private final ModelRenderer leftFrontLeg;
  private final ModelRenderer leftFrontLeg2;
  private final ModelRenderer leftFrontLeg3;
  private final ModelRenderer leftFrontFoot;
  private final ModelRenderer rightFrontLeg;
  private final ModelRenderer rightFrontLeg2;
  private final ModelRenderer rightFrontLeg3;
  private final ModelRenderer rightFrontFoot;
  
  public LeopardWalkModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 128;
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.0F, 1.4F, -6.6F);
    setRotationAngle(this.body, -0.0873F, 0.0F, 0.0F);
    this.body.setTextureOffset(0, 0).addBox(-4.5F, 4.2281F, 0.109F, 9.0F, 7.0F, 12.0F, 0.01F, false);
    
    this.body2 = new ModelRenderer((Model)this);
    this.body2.setRotationPoint(0.0F, 9.1F, 10.7F);
    this.body.addChild(this.body2);
    setRotationAngle(this.body2, 0.1047F, 0.0F, 0.0F);
    this.body2.setTextureOffset(0, 19).addBox(-4.5F, -4.6727F, 0.7449F, 9.0F, 7.0F, 9.0F, 0.0F, false);
    
    this.neck = new ModelRenderer((Model)this);
    this.neck.setRotationPoint(0.0F, 6.8F, 1.0F);
    this.body.addChild(this.neck);
    setRotationAngle(this.neck, 0.9819F, 0.0F, 0.0F);
    this.neck.setTextureOffset(45, 15).addBox(-2.5F, -6.3895F, -3.8895F, 5.0F, 9.0F, 7.0F, 0.0F, false);
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(-2.0F, 5.0F, -10.0F);
    this.head.setTextureOffset(43, 0).addBox(-1.5F, -4.5F, -6.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);
    
    this.leftEar = new ModelRenderer((Model)this);
    this.leftEar.setRotationPoint(2.9F, -13.05F, -7.0F);
    this.head.addChild(this.leftEar);
    setRotationAngle(this.leftEar, 0.5864F, -1.3683F, 0.0F);
    this.leftEar.setTextureOffset(0, 0).addBox(4.3F, 4.4849F, -7.1516F, 1.0F, 3.0F, 3.0F, 0.0F, false);
    
    this.rightEar = new ModelRenderer((Model)this);
    this.rightEar.setRotationPoint(-2.1F, -13.15F, -7.0F);
    this.head.addChild(this.rightEar);
    setRotationAngle(this.rightEar, -0.0391F, -1.7984F, -0.3129F);
    this.rightEar.setTextureOffset(0, 0).addBox(4.1016F, 6.7521F, -0.8553F, 1.0F, 3.0F, 3.0F, 0.0F, false);
    
    this.snout = new ModelRenderer((Model)this);
    this.snout.setRotationPoint(2.0F, 1.15F, -5.7F);
    this.head.addChild(this.snout);
    setRotationAngle(this.snout, 0.1955F, 0.0F, 0.0F);
    this.snout.setTextureOffset(50, 32).addBox(-1.5F, -2.2452F, -3.9514F, 3.0F, 2.0F, 5.0F, 0.01F, false);
    
    this.snout2 = new ModelRenderer((Model)this);
    this.snout2.setRotationPoint(0.0F, -9.7355F, -3.011F);
    this.snout.addChild(this.snout2);
    setRotationAngle(this.snout2, -0.1955F, 0.0F, 0.0F);
    this.snout2.setTextureOffset(50, 40).addBox(-1.5F, 7.5F, 1.0F, 3.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(0.1F, 8.6F, 12.0F);
    setRotationAngle(this.tail, 0.3519F, 0.0F, 0.0F);
    this.tail.setTextureOffset(73, 1).addBox(-1.0F, -0.966F, -0.3461F, 2.0F, 5.0F, 2.0F, 0.0F, false);
    
    this.tail2 = new ModelRenderer((Model)this);
    this.tail2.setRotationPoint(-0.5F, 4.05F, 1.1F);
    this.tail.addChild(this.tail2);
    setRotationAngle(this.tail2, -0.0782F, 0.0F, 0.0F);
    this.tail2.setTextureOffset(73, 9).addBox(-0.5F, -0.2402F, -1.45F, 2.0F, 5.0F, 2.0F, -0.01F, false);
    
    this.tail3 = new ModelRenderer((Model)this);
    this.tail3.setRotationPoint(0.0F, 4.0985F, 0.1813F);
    this.tail2.addChild(this.tail3);
    setRotationAngle(this.tail3, 0.2346F, 0.0F, 0.0F);
    this.tail3.setTextureOffset(73, 17).addBox(-0.5F, 0.1622F, -1.6313F, 2.0F, 5.0F, 2.0F, 0.0F, false);
    
    this.leftRearLeg = new ModelRenderer((Model)this);
    this.leftRearLeg.setRotationPoint(4.1F, 11.5F, 9.65F);
    setRotationAngle(this.leftRearLeg, -0.2618F, 0.0F, 0.0F);
    this.leftRearLeg.setTextureOffset(1, 36).addBox(-1.1F, -2.926F, -1.6932F, 4.0F, 8.0F, 4.0F, 0.01F, false);
    
    this.leftRearLeg2 = new ModelRenderer((Model)this);
    this.leftRearLeg2.setRotationPoint(-24.1F, -3.9082F, -7.6316F);
    this.leftRearLeg.addChild(this.leftRearLeg2);
    setRotationAngle(this.leftRearLeg2, 0.7854F, 0.0F, 0.0F);
    this.leftRearLeg2.setTextureOffset(1, 49).addBox(23.0F, 10.4824F, -2.0051F, 4.0F, 6.0F, 4.0F, 0.0F, false);
    
    this.leftRearLeg3 = new ModelRenderer((Model)this);
    this.leftRearLeg3.setRotationPoint(24.75F, 14.4F, 1.4F);
    this.leftRearLeg2.addChild(this.leftRearLeg3);
    setRotationAngle(this.leftRearLeg3, -1.1868F, 0.0F, 0.0F);
    this.leftRearLeg3.setTextureOffset(3, 60).addBox(-1.25F, 1.047F, -1.8626F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.leftRearFoot = new ModelRenderer((Model)this);
    this.leftRearFoot.setRotationPoint(0.35F, 6.0F, -0.75F);
    this.leftRearLeg3.addChild(this.leftRearFoot);
    setRotationAngle(this.leftRearFoot, 0.6632F, 0.0F, 0.0F);
    this.leftRearFoot.setTextureOffset(1, 70).addBox(-1.8F, 0.0507F, -3.7232F, 3.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.rightRearLeg = new ModelRenderer((Model)this);
    this.rightRearLeg.setRotationPoint(-3.9F, 11.5F, 9.65F);
    setRotationAngle(this.rightRearLeg, -0.2618F, 0.0F, 0.0F);
    this.rightRearLeg.setTextureOffset(1, 36).addBox(-3.1F, -2.926F, -1.6932F, 4.0F, 8.0F, 4.0F, 0.01F, false);
    
    this.rightRearLeg2 = new ModelRenderer((Model)this);
    this.rightRearLeg2.setRotationPoint(-26.1F, -3.9082F, -7.6316F);
    this.rightRearLeg.addChild(this.rightRearLeg2);
    setRotationAngle(this.rightRearLeg2, 0.7854F, 0.0F, 0.0F);
    this.rightRearLeg2.setTextureOffset(1, 49).addBox(23.0F, 10.4824F, -2.0051F, 4.0F, 6.0F, 4.0F, 0.0F, false);
    
    this.rightRearLeg3 = new ModelRenderer((Model)this);
    this.rightRearLeg3.setRotationPoint(24.75F, 14.4F, 1.4F);
    this.rightRearLeg2.addChild(this.rightRearLeg3);
    setRotationAngle(this.rightRearLeg3, -1.1868F, 0.0F, 0.0F);
    this.rightRearLeg3.setTextureOffset(3, 60).addBox(-1.25F, 1.047F, -1.8626F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.rightRearFoot = new ModelRenderer((Model)this);
    this.rightRearFoot.setRotationPoint(0.35F, 6.0F, -0.75F);
    this.rightRearLeg3.addChild(this.rightRearFoot);
    setRotationAngle(this.rightRearFoot, 0.6632F, 0.0F, 0.0F);
    this.rightRearFoot.setTextureOffset(1, 70).addBox(-1.8F, 0.0507F, -3.7232F, 3.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.leftFrontLeg = new ModelRenderer((Model)this);
    this.leftFrontLeg.setRotationPoint(2.7F, 8.5F, -3.75F);
    setRotationAngle(this.leftFrontLeg, 0.0873F, 0.0F, 0.0F);
    this.leftFrontLeg.setTextureOffset(35, 45).addBox(-0.1F, -1.818F, -2.0619F, 4.0F, 8.0F, 4.0F, 0.0F, false);
    
    this.leftFrontLeg2 = new ModelRenderer((Model)this);
    this.leftFrontLeg2.setRotationPoint(2.35F, 5.5824F, 0.0351F);
    this.leftFrontLeg.addChild(this.leftFrontLeg2);
    setRotationAngle(this.leftFrontLeg2, -0.0873F, 0.0F, 0.0F);
    this.leftFrontLeg2.setTextureOffset(37, 58).addBox(-2.05F, 0.363F, -1.5637F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.leftFrontLeg3 = new ModelRenderer((Model)this);
    this.leftFrontLeg3.setRotationPoint(-0.15F, 5.663F, 0.3363F);
    this.leftFrontLeg2.addChild(this.leftFrontLeg3);
    this.leftFrontLeg3.setTextureOffset(37, 68).addBox(-1.9F, -2.5F, -1.5F, 3.0F, 6.0F, 3.0F, -0.01F, false);
    
    this.leftFrontFoot = new ModelRenderer((Model)this);
    this.leftFrontFoot.setRotationPoint(0.0F, 2.2F, 0.35F);
    this.leftFrontLeg3.addChild(this.leftFrontFoot);
    setRotationAngle(this.leftFrontFoot, 0.0349F, 0.0F, 0.0F);
    this.leftFrontFoot.setTextureOffset(36, 78).addBox(-1.9F, -0.0741F, -3.1679F, 3.0F, 2.0F, 4.0F, 0.0F, false);
    
    this.rightFrontLeg = new ModelRenderer((Model)this);
    this.rightFrontLeg.setRotationPoint(-3.3F, 8.5F, -3.75F);
    setRotationAngle(this.rightFrontLeg, 0.0873F, 0.0F, 0.0F);
    this.rightFrontLeg.setTextureOffset(35, 45).addBox(-4.1F, -1.818F, -2.0619F, 4.0F, 8.0F, 4.0F, 0.0F, false);
    
    this.rightFrontLeg2 = new ModelRenderer((Model)this);
    this.rightFrontLeg2.setRotationPoint(-1.65F, 5.5824F, 0.0351F);
    this.rightFrontLeg.addChild(this.rightFrontLeg2);
    setRotationAngle(this.rightFrontLeg2, -0.0873F, 0.0F, 0.0F);
    this.rightFrontLeg2.setTextureOffset(37, 58).addBox(-2.05F, 0.363F, -1.5637F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.rightFrontLeg3 = new ModelRenderer((Model)this);
    this.rightFrontLeg3.setRotationPoint(-0.15F, 5.663F, 0.3363F);
    this.rightFrontLeg2.addChild(this.rightFrontLeg3);
    this.rightFrontLeg3.setTextureOffset(37, 68).addBox(-1.9F, -2.5F, -1.5F, 3.0F, 6.0F, 3.0F, -0.01F, false);
    
    this.rightFrontFoot = new ModelRenderer((Model)this);
    this.rightFrontFoot.setRotationPoint(0.0F, 2.2F, 0.35F);
    this.rightFrontLeg3.addChild(this.rightFrontFoot);
    setRotationAngle(this.rightFrontFoot, 0.0349F, 0.0F, 0.0F);
    this.rightFrontFoot.setTextureOffset(36, 78).addBox(-1.9F, -0.0741F, -3.1679F, 3.0F, 2.0F, 4.0F, 0.0F, false);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.rightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6F) * 1.1F * limbSwingAmount;
    this.leftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6F) * 1.1F * limbSwingAmount;
    this.rightRearLeg.rotateAngleX = -0.3F + MathHelper.cos(limbSwing * 0.6F + 3.1415927F) * 1.1F * limbSwingAmount;
    this.leftRearLeg.rotateAngleX = -0.3F + MathHelper.cos(limbSwing * 0.6F + 3.1415927F) * 1.1F * limbSwingAmount;
    if (entity.isSprinting()) {
      this.tail.rotateAngleX = 1.2F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
    
    }
    else if (!(entity instanceof xyz.pixelatedw.mineminenomi.entities.DummyEntity)) {
      
      this.tail.rotateAngleY = (float)(this.tail.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 5.0D);
      this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + Math.sin(ageInTicks * 0.05D) / 5.0D);
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
      this.head.rotateAngleX = (float)(this.head.rotateAngleX - f2 * 1.5D + f3);
      this.head.rotateAngleZ -= MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
    } 
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
    this.head.render(matrixStack, buffer, packedLight, packedOverlay);
    this.tail.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftRearLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightRearLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
  }


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    this.head.translateRotate(matrixStack);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
    matrixStack.rotate(Vector3f.XP.rotationDegrees(260.0F));
    matrixStack.translate(0.3D, 0.0D, -0.03D);
  }



  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}


  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}


  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


