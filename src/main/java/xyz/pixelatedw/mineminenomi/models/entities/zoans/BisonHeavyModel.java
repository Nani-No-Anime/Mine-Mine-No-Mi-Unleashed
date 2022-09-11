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
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;

public class BisonHeavyModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer rightArm;
  private final ModelRenderer rightArm2;
  private final ModelRenderer rightHand;
  private final ModelRenderer leftArm;
  private final ModelRenderer leftArm2;
  private final ModelRenderer leftHand;
  private final ModelRenderer rightLeg;
  private final ModelRenderer rightLeg2;
  private final ModelRenderer rightLeg3;
  private final ModelRenderer rightFeet;
  private final ModelRenderer leftLeg;
  private final ModelRenderer leftLeg2;
  private final ModelRenderer leftLeg3;
  private final ModelRenderer leftFeet;
  private final ModelRenderer upperBody;
  private final ModelRenderer lowerBody;
  private final ModelRenderer hunch;
  private final ModelRenderer rightShoulder;
  private final ModelRenderer leftShoulder;
  private final ModelRenderer neck;
  private final ModelRenderer head;
  private final ModelRenderer leftHorn1;
  private final ModelRenderer leftHorn2;
  private final ModelRenderer rightHorn1;
  private final ModelRenderer rightHorn2;
  
  public BisonHeavyModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 64;
    
    this.rightArm = new ModelRenderer((Model)this);
    this.rightArm.setRotationPoint(-5.0F, -2.6F, 0.0F);
    setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.2094F);
    this.rightArm.setTextureOffset(23, 30).addBox(-4.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, -0.01F, false);
    
    this.rightArm2 = new ModelRenderer((Model)this);
    this.rightArm2.setRotationPoint(-0.2445F, 0.052F, 0.0F);
    this.rightArm.addChild(this.rightArm2);
    setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.2793F);
    this.rightArm2.setTextureOffset(23, 42).addBox(-4.5F, 5.8F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);
    
    this.rightHand = new ModelRenderer((Model)this);
    this.rightHand.setRotationPoint(-2.5201F, 12.2906F, 0.25F);
    this.rightArm2.addChild(this.rightHand);
    setRotationAngle(this.rightHand, 0.1745F, 1.5708F, 0.0175F);
    this.rightHand.setTextureOffset(0, 25).addBox(-1.2353F, 0.3094F, -1.0712F, 3.0F, 3.0F, 1.0F, 0.2F, false);
    
    this.leftArm = new ModelRenderer((Model)this);
    this.leftArm.setRotationPoint(5.0F, -2.6F, 0.0F);
    setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.2094F);
    this.leftArm.setTextureOffset(23, 30).addBox(0.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, -0.01F, false);
    
    this.leftArm2 = new ModelRenderer((Model)this);
    this.leftArm2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftArm.addChild(this.leftArm2);
    setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.2793F);
    this.leftArm2.setTextureOffset(23, 42).addBox(2.0F, 5.8F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);
    
    this.leftHand = new ModelRenderer((Model)this);
    this.leftHand.setRotationPoint(3.9781F, 1.9722F, 0.0F);
    this.leftArm2.addChild(this.leftHand);
    setRotationAngle(this.leftHand, 0.1745F, -1.5708F, 0.0F);
    this.leftHand.setTextureOffset(0, 25).addBox(-1.4781F, 10.6278F, -1.8489F, 3.0F, 3.0F, 1.0F, 0.2F, false);
    
    this.rightLeg = new ModelRenderer((Model)this);
    this.rightLeg.setRotationPoint(-3.0F, 11.6F, 0.5F);
    setRotationAngle(this.rightLeg, -0.3491F, 0.0F, 0.0F);
    this.rightLeg.setTextureOffset(9, 30).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);
    
    this.rightLeg2 = new ModelRenderer((Model)this);
    this.rightLeg2.setRotationPoint(0.0F, 5.9802F, 1.1124F);
    this.rightLeg.addChild(this.rightLeg2);
    setRotationAngle(this.rightLeg2, 1.8151F, 0.0F, 0.0F);
    this.rightLeg2.setTextureOffset(9, 41).addBox(-1.0F, -0.5326F, -0.7283F, 2.0F, 4.0F, 2.0F, 0.0F, false);
    
    this.rightLeg3 = new ModelRenderer((Model)this);
    this.rightLeg3.setRotationPoint(0.0F, 0.2827F, 6.0278F);
    this.rightLeg2.addChild(this.rightLeg3);
    setRotationAngle(this.rightLeg3, -2.0071F, 0.0F, 0.0F);
    this.rightLeg3.setTextureOffset(0, 30).addBox(-1.0F, 3.1F, 4.8F, 2.0F, 6.0F, 2.0F, -0.01F, false);
    
    this.rightFeet = new ModelRenderer((Model)this);
    this.rightFeet.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.rightLeg3.addChild(this.rightFeet);
    setRotationAngle(this.rightFeet, 0.5236F, 0.0F, 0.0F);
    this.rightFeet.setTextureOffset(0, 39).addBox(-1.0F, 10.25F, -0.5044F, 2.0F, 2.0F, 2.0F, 0.01F, false);
    
    this.leftLeg = new ModelRenderer((Model)this);
    this.leftLeg.setRotationPoint(3.5F, 11.6F, 1.0F);
    setRotationAngle(this.leftLeg, -0.3491F, 0.0F, 0.0F);
    this.leftLeg.setTextureOffset(9, 30).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 7.0F, 3.0F, 0.0F, false);
    
    this.leftLeg2 = new ModelRenderer((Model)this);
    this.leftLeg2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftLeg.addChild(this.leftLeg2);
    setRotationAngle(this.leftLeg2, 1.8151F, 0.0F, 0.0F);
    this.leftLeg2.setTextureOffset(9, 41).addBox(-1.5F, -1.5F, -6.8F, 2.0F, 4.0F, 2.0F, 0.0F, false);
    
    this.leftLeg3 = new ModelRenderer((Model)this);
    this.leftLeg3.setRotationPoint(0.0F, -0.163F, 0.2021F);
    this.leftLeg2.addChild(this.leftLeg3);
    setRotationAngle(this.leftLeg3, -2.0071F, 0.0F, 0.0F);
    this.leftLeg3.setTextureOffset(0, 30).addBox(-1.5F, 3.5143F, 4.5288F, 2.0F, 6.0F, 2.0F, -0.01F, false);
    
    this.leftFeet = new ModelRenderer((Model)this);
    this.leftFeet.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftLeg3.addChild(this.leftFeet);
    setRotationAngle(this.leftFeet, 0.5236F, 0.0F, 0.0F);
    this.leftFeet.setTextureOffset(0, 39).addBox(-1.5F, 10.5F, -1.0F, 2.0F, 2.0F, 2.0F, 0.01F, false);
    
    this.upperBody = new ModelRenderer((Model)this);
    this.upperBody.setRotationPoint(0.0F, -5.0F, 0.0F);
    this.upperBody.setTextureOffset(48, 0).addBox(-5.5F, 0.0F, -2.5F, 11.0F, 10.0F, 6.0F, 0.0F, false);
    
    this.lowerBody = new ModelRenderer((Model)this);
    this.lowerBody.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.upperBody.addChild(this.lowerBody);
    this.lowerBody.setTextureOffset(17, 0).addBox(-5.0F, 9.9F, -2.0F, 10.0F, 8.0F, 5.0F, 0.0F, false);
    
    this.hunch = new ModelRenderer((Model)this);
    this.hunch.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.upperBody.addChild(this.hunch);
    setRotationAngle(this.hunch, 0.4189F, 0.0F, 0.0F);
    this.hunch.setTextureOffset(83, 24).addBox(-4.5F, 0.0F, -0.5F, 9.0F, 10.0F, 6.0F, 0.0F, false);
    
    this.rightShoulder = new ModelRenderer((Model)this);
    this.rightShoulder.setRotationPoint(-3.5F, 4.0F, 0.0F);
    this.upperBody.addChild(this.rightShoulder);
    setRotationAngle(this.rightShoulder, 0.0F, 0.0F, -0.2618F);
    this.rightShoulder.setTextureOffset(83, 0).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 7.0F, 4.0F, 0.0F, false);
    
    this.leftShoulder = new ModelRenderer((Model)this);
    this.leftShoulder.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.upperBody.addChild(this.leftShoulder);
    setRotationAngle(this.leftShoulder, 0.0F, 0.0F, 0.2618F);
    this.leftShoulder.setTextureOffset(83, 0).addBox(1.5F, 0.0F, -2.0F, 6.0F, 7.0F, 4.0F, 0.0F, false);
    
    this.neck = new ModelRenderer((Model)this);
    this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.upperBody.addChild(this.neck);
    setRotationAngle(this.neck, 0.4887F, 0.0F, 0.0F);
    this.neck.setTextureOffset(83, 12).addBox(-3.5F, 0.0F, -5.0F, 7.0F, 7.0F, 4.0F, 0.0F, false);
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(0.0F, -4.0F, 0.0F);
    this.head.setTextureOffset(0, 0).addBox(-2.0F, -1.0F, -7.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
    
    this.leftHorn1 = new ModelRenderer((Model)this);
    this.leftHorn1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.head.addChild(this.leftHorn1);
    setRotationAngle(this.leftHorn1, 0.0F, 0.0F, -0.733F);
    this.leftHorn1.setTextureOffset(115, 0).addBox(1.0F, 1.0F, -6.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftHorn2 = new ModelRenderer((Model)this);
    this.leftHorn2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftHorn1.addChild(this.leftHorn2);
    setRotationAngle(this.leftHorn2, 0.0F, 0.0F, -1.2217F);
    this.leftHorn2.setTextureOffset(122, 0).addBox(-0.7F, 2.5F, -6.0F, 2.0F, 1.0F, 1.0F, 0.01F, false);
    
    this.rightHorn1 = new ModelRenderer((Model)this);
    this.rightHorn1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.head.addChild(this.rightHorn1);
    setRotationAngle(this.rightHorn1, 0.0F, 0.0F, 0.733F);
    this.rightHorn1.setTextureOffset(115, 0).addBox(-3.0F, 1.0F, -6.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightHorn2 = new ModelRenderer((Model)this);
    this.rightHorn2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.rightHorn1.addChild(this.rightHorn2);
    setRotationAngle(this.rightHorn2, 0.0F, 0.0F, 1.2217F);
    this.rightHorn2.setTextureOffset(122, 0).addBox(-1.3F, 2.5F, -6.0F, 2.0F, 1.0F, 1.0F, 0.01F, false);
    
    this.bipedBody = this.upperBody;
    this.bipedHead = this.head;
    this.bipedRightArm = this.rightArm;
    this.bipedLeftArm = this.leftArm;
    this.bipedRightLeg = this.rightLeg;
    this.bipedLeftLeg = this.leftLeg;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.upperBody.render(matrixStack, buffer, packedLight, packedOverlay);
    this.head.render(matrixStack, buffer, packedLight, packedOverlay);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    boolean flag = (entity.getTicksElytraFlying() > 4);
    boolean flag1 = entity.isActualySwimming();
    this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;
    if (flag) {
      this.bipedHead.rotateAngleX = -0.7853982F;
    } else if (this.swimAnimation > 0.0F) {
      
      if (flag1) {
        this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, -0.7853982F, this.swimAnimation);
      } else {
        this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
      } 
    } else {
      
      this.bipedHead.rotateAngleX = headPitch * 0.017453292F;
      if (this.bipedHead.rotateAngleX > 0.4D) {
        this.bipedHead.rotateAngleX = 0.4F;
      } else if (this.bipedHead.rotateAngleX < -0.4D) {
        this.bipedHead.rotateAngleX = -0.4F;
      } 
    } 
    
    float f = 1.0F;
    this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
    this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
    this.rightLeg.rotateAngleX = -0.34F + MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
    this.leftLeg.rotateAngleX = -0.34F + MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
    if (!entity.getHeldItemMainhand().isEmpty()) {
      this.rightArm.rotateAngleX += -0.15F;
    }
    
    this.swingProgress = ((LivingEntity)entity).swingProgress;
    boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
    if (this.swingProgress > 0.0F)
    {
      if (isBlackLeg) {
        
        this.upperBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
        this.rightLeg.rotateAngleY += this.upperBody.rotateAngleY;
        this.leftLeg.rotateAngleY += this.upperBody.rotateAngleY;
        float f1 = 1.0F - this.swingProgress;
        f1 *= f1;
        f1 *= f1;
        f1 = 1.0F - f1;
        float f2 = MathHelper.sin(f1 * 3.1415927F);
        float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
        this.rightLeg.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
        this.rightLeg.rotateAngleY += this.lowerBody.rotateAngleY * 2.0F;
      }
      else {
        
        this.upperBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
        this.rightArm.rotationPointZ = MathHelper.sin(this.upperBody.rotateAngleY) * 5.0F;
        this.rightArm.rotationPointX = -MathHelper.cos(this.upperBody.rotateAngleY) * 5.0F;
        this.leftArm.rotationPointZ = -MathHelper.sin(this.upperBody.rotateAngleY) * 5.0F;
        this.leftArm.rotationPointX = MathHelper.cos(this.upperBody.rotateAngleY) * 5.0F;
        this.rightArm.rotateAngleY += this.upperBody.rotateAngleY;
        this.leftArm.rotateAngleY += this.upperBody.rotateAngleY;
        this.leftArm.rotateAngleX += this.upperBody.rotateAngleY;
        float f1 = 1.0F - this.swingProgress;
        f1 *= f1;
        f1 *= f1;
        f1 = 1.0F - f1;
        float f2 = MathHelper.sin(f1 * 3.1415927F);
        float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
        this.rightArm.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
        this.rightArm.rotateAngleY += this.lowerBody.rotateAngleY * 2.0F;
        this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.9F;
      } 
    }

    
    if (entity.isSneaking()) {
      
      this.upperBody.rotateAngleX = 0.5F;
      this.upperBody.rotationPointZ -= 4.0F;
      this.rightArm.rotateAngleX += 0.4F;
      this.rightArm.rotationPointZ -= 2.5F;
      this.leftArm.rotateAngleX += 0.4F;
      this.leftArm.rotationPointZ -= 2.5F;
      this.rightLeg.rotationPointZ = 4.0F;
      this.leftLeg.rotationPointZ = 4.5F;
      this.rightLeg.rotationPointY = 10.5F;
      this.leftLeg.rotationPointY = 10.5F;
      this.head.rotationPointY = -4.0F;
      this.head.rotationPointZ = -2.0F;
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


  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(-0.5D, -0.2D, -0.2D);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
      matrixStack.rotate(Vector3f.ZP.rotationDegrees(10.0F));
      this.rightArm2.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    }
    else {
      
      matrixStack.translate(0.5D, -0.2D, -0.2D);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
      matrixStack.rotate(Vector3f.ZP.rotationDegrees(-10.0F));
      this.leftArm2.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    } 
  }


  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(0.0D, -1.2D, 0.3D);
      matrixStack.scale(1.5F, 1.5F, 1.5F);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-60.0F));
      this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    }
    else {
      
      matrixStack.translate(0.0D, -1.2D, 0.3D);
      matrixStack.scale(1.5F, 1.5F, 1.5F);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(60.0F));
      this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    } 
  }
}


