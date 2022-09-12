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

public class ZouGuardModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer head;
  private final ModelRenderer snout;
  private final ModelRenderer snout2;
  private final ModelRenderer snout3;
  private final ModelRenderer rightTusk;
  private final ModelRenderer rightTusk2;
  private final ModelRenderer leftTusk;
  private final ModelRenderer leftTusk2;
  private final ModelRenderer leftEar;
  private final ModelRenderer rightEar;
  private final ModelRenderer body;
  private final ModelRenderer tail;
  private final ModelRenderer tail2;
  private final ModelRenderer body2;
  private final ModelRenderer rightRearLeg;
  private final ModelRenderer leftRearLeg;
  private final ModelRenderer rightFrontLeg;
  private final ModelRenderer leftFrontLeg;
  
  public ZouGuardModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 64;
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(0.0F, -1.0F, -14.0F);
    this.head.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 11.0F, 9.0F, 0.0F, false);
    
    this.snout = new ModelRenderer((Model)this);
    this.snout.setRotationPoint(0.0F, 6.0F, -4.5F);
    this.head.addChild(this.snout);
    setRotationAngle(this.snout, -0.1745F, 0.0F, 0.0F);
    this.snout.setTextureOffset(108, 8).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
    
    this.snout2 = new ModelRenderer((Model)this);
    this.snout2.setRotationPoint(0.0F, 6.0F, 0.0F);
    this.snout.addChild(this.snout2);
    setRotationAngle(this.snout2, 0.1745F, 0.0F, 0.0F);
    this.snout2.setTextureOffset(108, 20).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.snout3 = new ModelRenderer((Model)this);
    this.snout3.setRotationPoint(0.0F, 5.5F, 0.5F);
    this.snout2.addChild(this.snout3);
    setRotationAngle(this.snout3, 0.1745F, 0.0F, 0.0F);
    this.snout3.setTextureOffset(108, 30).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
    
    this.rightTusk = new ModelRenderer((Model)this);
    this.rightTusk.setRotationPoint(2.3F, 5.0F, -5.0F);
    this.head.addChild(this.rightTusk);
    setRotationAngle(this.rightTusk, -0.3491F, -0.2094F, 0.0F);
    this.rightTusk.setTextureOffset(15, 21).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    
    this.rightTusk2 = new ModelRenderer((Model)this);
    this.rightTusk2.setRotationPoint(0.0F, 3.8F, 0.0F);
    this.rightTusk.addChild(this.rightTusk2);
    setRotationAngle(this.rightTusk2, -0.1745F, 0.0F, 0.0F);
    this.rightTusk2.setTextureOffset(15, 27).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    
    this.leftTusk = new ModelRenderer((Model)this);
    this.leftTusk.setRotationPoint(-2.3F, 5.0F, -5.0F);
    this.head.addChild(this.leftTusk);
    setRotationAngle(this.leftTusk, -0.3491F, 0.2094F, 0.0F);
    this.leftTusk.setTextureOffset(15, 21).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    
    this.leftTusk2 = new ModelRenderer((Model)this);
    this.leftTusk2.setRotationPoint(-0.039F, 5.5022F, 0.3754F);
    this.leftTusk.addChild(this.leftTusk2);
    setRotationAngle(this.leftTusk2, -0.1745F, 0.0F, 0.0F);
    this.leftTusk2.setTextureOffset(15, 27).addBox(-0.461F, -1.6998F, -1.2926F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    
    this.leftEar = new ModelRenderer((Model)this);
    this.leftEar.setRotationPoint(3.0F, -0.5F, -2.5F);
    this.head.addChild(this.leftEar);
    setRotationAngle(this.leftEar, -0.1368F, -0.4707F, 0.2946F);
    this.leftEar.setTextureOffset(0, 21).addBox(0.0F, -4.5F, -0.5F, 6.0F, 9.0F, 1.0F, 0.0F, true);
    
    this.rightEar = new ModelRenderer((Model)this);
    this.rightEar.setRotationPoint(-3.0F, -0.5F, -2.5F);
    this.head.addChild(this.rightEar);
    setRotationAngle(this.rightEar, -0.1368F, 0.4707F, -0.2946F);
    this.rightEar.setTextureOffset(0, 21).addBox(-6.0F, -4.5F, -0.5F, 6.0F, 9.0F, 1.0F, 0.0F, false);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.0F, 9.0F, -4.0F);
    this.body.setTextureOffset(36, 25).addBox(-7.5F, -12.0F, -8.0F, 15.0F, 15.0F, 24.0F, 0.0F, false);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(0.0F, -7.0F, 15.5F);
    this.body.addChild(this.tail);
    setRotationAngle(this.tail, 0.3491F, 0.0F, 0.0F);
    this.tail.setTextureOffset(108, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, 0.0F, false);
    
    this.tail2 = new ModelRenderer((Model)this);
    this.tail2.setRotationPoint(0.0F, 5.5F, 0.0F);
    this.tail.addChild(this.tail2);
    this.tail2.setTextureOffset(113, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
    
    this.body2 = new ModelRenderer((Model)this);
    this.body2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.body.addChild(this.body2);
    this.body2.setTextureOffset(36, 0).addBox(-6.5F, -13.0F, -7.0F, 13.0F, 2.0F, 22.0F, 0.0F, false);
    
    this.rightRearLeg = new ModelRenderer((Model)this);
    this.rightRearLeg.setRotationPoint(-5.0F, 11.0F, 9.5F);
    this.rightRearLeg.setTextureOffset(0, 46).addBox(-2.25F, 0.0F, -2.75F, 5.0F, 13.0F, 5.0F, 0.0F, false);
    
    this.leftRearLeg = new ModelRenderer((Model)this);
    this.leftRearLeg.setRotationPoint(5.0F, 11.0F, 9.5F);
    this.leftRearLeg.setTextureOffset(0, 46).addBox(-2.75F, 0.0F, -2.75F, 5.0F, 13.0F, 5.0F, 0.0F, false);
    
    this.rightFrontLeg = new ModelRenderer((Model)this);
    this.rightFrontLeg.setRotationPoint(-5.0F, 11.0F, -9.5F);
    this.rightFrontLeg.setTextureOffset(0, 46).addBox(-2.25F, 0.0F, -2.25F, 5.0F, 13.0F, 5.0F, 0.0F, false);
    
    this.leftFrontLeg = new ModelRenderer((Model)this);
    this.leftFrontLeg.setRotationPoint(5.0F, 11.0F, -9.5F);
    this.leftFrontLeg.setTextureOffset(0, 46).addBox(-2.75F, 0.0F, -2.25F, 5.0F, 13.0F, 5.0F, 0.0F, false);
    
    this.bipedHead = this.head;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightRearLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftRearLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    boolean flag1 = entity.isActualySwimming();
    this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;
    if (this.swimAnimation > 0.0F) {
      
      if (flag1) {
        this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, -0.7853982F, this.swimAnimation);
      } else {
        this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
      } 
    } else {
      
      this.bipedHead.rotateAngleX = headPitch * 0.017453292F;
      if (this.bipedHead.rotateAngleX > 0.6D) {
        this.bipedHead.rotateAngleX = 0.6F;
      }
    } 
    
    this.rightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
    this.leftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount;
    this.rightRearLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.3F * limbSwingAmount;
    this.leftRearLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.4F * limbSwingAmount;
    if (entity.isSprinting()) {
      
      this.tail.rotateAngleX = 0.6F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
      this.leftEar.rotateAngleY = -0.3F - MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
      this.rightEar.rotateAngleY = 0.3F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
    } 

    
    this.swingProgress = ((LivingEntity)entity).swingProgress;
    if (this.swingProgress > 0.0F) {
      
      this.head.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
      this.head.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
      this.snout.rotateAngleY += this.body2.rotateAngleY;
      float f1 = 1.0F - this.swingProgress;
      f1 *= f1;
      f1 *= f1;
      f1 = 1.0F - f1;
      float f2 = MathHelper.sin(f1 * 3.1415927F);
      float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
      this.snout.rotateAngleX = (float)(this.snout.rotateAngleX - f2 * 1.2D + f3);
      this.snout.rotateAngleY += this.body.rotateAngleY * 2.0F;
      this.snout.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
    } 

    
    if (entity.isElytraFlying()) {
      
      this.rightEar.rotateAngleX = 1.2F;
      this.rightEar.rotateAngleY = 0.1F;
      this.rightEar.rotateAngleZ = MathHelper.cos(ageInTicks) * 0.6F;
      
      this.leftEar.rotateAngleX = 1.2F;
      this.leftEar.rotateAngleY = 0.1F;
      this.leftEar.rotateAngleZ = -MathHelper.cos(ageInTicks) * 0.6F;
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
    this.snout.translateRotate(matrixStack);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
    matrixStack.rotate(Vector3f.XP.rotationDegrees(260.0F));
    matrixStack.translate(0.8D, -0.6D, 0.0D);
  }
}


