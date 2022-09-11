package xyz.pixelatedw.mineminenomi.models.entities.zoans;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;

public class MammothHeavyModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer body;
  private final ModelRenderer body2;
  private final ModelRenderer tail;
  private final ModelRenderer tail_r1;
  private final ModelRenderer rightLeg;
  private final ModelRenderer rightLeg2;
  private final ModelRenderer leftLeg;
  private final ModelRenderer leftLeg2;
  private final ModelRenderer rightArm;
  private final ModelRenderer rightArm2;
  private final ModelRenderer leftArm;
  private final ModelRenderer leftArm2;
  private final ModelRenderer head;
  private final ModelRenderer snout;
  private final ModelRenderer snout2;
  private final ModelRenderer snout3;
  private final ModelRenderer leftEar;
  private final ModelRenderer rightEar;
  private final ModelRenderer rightTusk;
  private final ModelRenderer rightTusk2;
  private final ModelRenderer rightTusk3;
  private final ModelRenderer leftTusk;
  private final ModelRenderer leftTusk2;
  private final ModelRenderer leftTusk3;
  
  public MammothHeavyModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 128;
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.0F, -2.6F, 2.7F);
    setRotationAngle(this.body, 0.0873F, 0.0F, 0.0F);
    this.body.setTextureOffset(0, 0).addBox(-9.0F, -10.0F, -7.0F, 18.0F, 14.0F, 10.0F, 0.0F, false);
    
    this.body2 = new ModelRenderer((Model)this);
    this.body2.setRotationPoint(0.0F, 13.8F, 0.3F);
    this.body.addChild(this.body2);
    this.body2.setTextureOffset(0, 24).addBox(-8.5F, -10.0F, -7.0F, 17.0F, 10.0F, 10.0F, 0.0F, false);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(0.25F, -2.5F, 2.0F);
    this.body2.addChild(this.tail);
    setRotationAngle(this.tail, 0.4363F, 0.0F, 0.0F);
    
    this.tail_r1 = new ModelRenderer((Model)this);
    this.tail_r1.setRotationPoint(-1.0F, -2.7F, 0.0F);
    this.tail.addChild(this.tail_r1);
    setRotationAngle(this.tail_r1, 0.48F, 0.0F, 0.0F);
    this.tail_r1.setTextureOffset(56, 0).addBox(-0.75F, 0.587F, -3.0383F, 3.0F, 8.0F, 3.0F, 0.0F, false);
    
    this.rightLeg = new ModelRenderer((Model)this);
    this.rightLeg.setRotationPoint(-4.0F, 11.1F, 1.0F);
    setRotationAngle(this.rightLeg, -0.0524F, 0.0F, 0.0F);
    this.rightLeg.setTextureOffset(28, 60).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 7.0F, 6.0F, 0.0F, false);
    
    this.rightLeg2 = new ModelRenderer((Model)this);
    this.rightLeg2.setRotationPoint(0.0F, 6.8F, 0.0F);
    this.rightLeg.addChild(this.rightLeg2);
    setRotationAngle(this.rightLeg2, 0.0524F, 0.0F, 0.0F);
    this.rightLeg2.setTextureOffset(0, 62).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 6.0F, 6.0F, 0.0F, false);
    
    this.leftLeg = new ModelRenderer((Model)this);
    this.leftLeg.setRotationPoint(5.0F, 11.1F, 1.0F);
    setRotationAngle(this.leftLeg, -0.0524F, 0.0F, 0.0F);
    this.leftLeg.setTextureOffset(28, 60).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 7.0F, 6.0F, 0.0F, false);
    
    this.leftLeg2 = new ModelRenderer((Model)this);
    this.leftLeg2.setRotationPoint(0.0F, 6.8F, 0.0F);
    this.leftLeg.addChild(this.leftLeg2);
    setRotationAngle(this.leftLeg2, 0.0524F, 0.0F, 0.0F);
    this.leftLeg2.setTextureOffset(0, 62).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 6.0F, 6.0F, 0.0F, false);
    
    this.rightArm = new ModelRenderer((Model)this);
    this.rightArm.setRotationPoint(-9.0F, -9.6F, 1.0F);
    setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.0349F);
    this.rightArm.setTextureOffset(50, 18).addBox(-6.0F, -2.0F, -3.5F, 6.0F, 10.0F, 6.0F, 0.0F, false);
    
    this.rightArm2 = new ModelRenderer((Model)this);
    this.rightArm2.setRotationPoint(-2.5F, 7.8F, 0.0F);
    this.rightArm.addChild(this.rightArm2);
    setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.0349F);
    this.rightArm2.setTextureOffset(54, 34).addBox(-3.5F, 0.0F, -3.5F, 6.0F, 10.0F, 6.0F, 0.01F, false);
    
    this.leftArm = new ModelRenderer((Model)this);
    this.leftArm.setRotationPoint(9.0F, -9.6F, 1.0F);
    setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.0349F);
    this.leftArm.setTextureOffset(50, 18).addBox(0.0F, -2.0F, -3.5F, 6.0F, 10.0F, 6.0F, 0.0F, true);
    
    this.leftArm2 = new ModelRenderer((Model)this);
    this.leftArm2.setRotationPoint(2.5F, 7.8F, 0.0F);
    this.leftArm.addChild(this.leftArm2);
    setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.0349F);
    this.leftArm2.setTextureOffset(54, 34).addBox(-2.5F, 0.0F, -3.5F, 6.0F, 10.0F, 6.0F, 0.01F, true);
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(0.0F, -12.2F, -3.0F);
    setRotationAngle(this.head, 0.0524F, 0.0F, 0.0F);
    this.head.setTextureOffset(0, 44).addBox(-4.0F, -9.0F, -4.5F, 8.0F, 9.0F, 9.0F, 0.0F, false);
    
    this.snout = new ModelRenderer((Model)this);
    this.snout.setRotationPoint(-2.5F, -1.5F, -4.5F);
    this.head.addChild(this.snout);
    setRotationAngle(this.snout, -0.3839F, 0.0F, 0.0F);
    this.snout.setTextureOffset(34, 48).addBox(0.0F, 0.0F, 0.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);
    
    this.snout2 = new ModelRenderer((Model)this);
    this.snout2.setRotationPoint(1.5F, 7.0F, 0.5F);
    this.snout.addChild(this.snout2);
    setRotationAngle(this.snout2, 0.2094F, 0.0F, 0.0F);
    this.snout2.setTextureOffset(72, 30).addBox(-1.0F, 0.0F, 0.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
    
    this.snout3 = new ModelRenderer((Model)this);
    this.snout3.setRotationPoint(0.5F, 5.5F, 0.5F);
    this.snout2.addChild(this.snout3);
    setRotationAngle(this.snout3, 0.2094F, 0.0F, 0.0F);
    this.snout3.setTextureOffset(74, 22).addBox(-1.0F, 0.0F, 0.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
    
    this.leftEar = new ModelRenderer((Model)this);
    this.leftEar.setRotationPoint(3.5F, -3.55F, 0.0F);
    this.head.addChild(this.leftEar);
    setRotationAngle(this.leftEar, -0.2365F, -1.0306F, 0.274F);
    this.leftEar.setTextureOffset(68, 13).addBox(-1.0F, -6.0F, -0.5F, 8.0F, 8.0F, 1.0F, 0.0F, true);
    
    this.rightEar = new ModelRenderer((Model)this);
    this.rightEar.setRotationPoint(-3.5F, -3.8F, -1.0F);
    this.head.addChild(this.rightEar);
    setRotationAngle(this.rightEar, -0.2365F, 1.0306F, -0.274F);
    this.rightEar.setTextureOffset(68, 13).addBox(-8.0F, -6.0F, 0.0F, 8.0F, 8.0F, 1.0F, 0.0F, false);
    
    this.rightTusk = new ModelRenderer((Model)this);
    this.rightTusk.setRotationPoint(-3.25F, -1.8F, -1.0F);
    this.head.addChild(this.rightTusk);
    setRotationAngle(this.rightTusk, 2.5307F, 0.2618F, 0.0F);
    this.rightTusk.setTextureOffset(69, 0).addBox(-1.4352F, -6.5246F, -1.629F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.rightTusk2 = new ModelRenderer((Model)this);
    this.rightTusk2.setRotationPoint(0.2445F, -6.1921F, -0.3535F);
    this.rightTusk.addChild(this.rightTusk2);
    setRotationAngle(this.rightTusk2, -0.3054F, 0.0F, 0.0F);
    this.rightTusk2.setTextureOffset(69, 0).addBox(-1.6298F, -5.9104F, -1.4339F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.rightTusk3 = new ModelRenderer((Model)this);
    this.rightTusk3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.rightTusk2.addChild(this.rightTusk3);
    setRotationAngle(this.rightTusk3, -0.3054F, 0.0F, 0.0F);
    this.rightTusk3.setTextureOffset(69, 0).addBox(-1.6298F, -11.1604F, -3.1839F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.leftTusk = new ModelRenderer((Model)this);
    this.leftTusk.setRotationPoint(2.5F, -1.8F, -1.0F);
    this.head.addChild(this.leftTusk);
    setRotationAngle(this.leftTusk, 2.5307F, -0.2618F, 0.0F);
    this.leftTusk.setTextureOffset(69, 0).addBox(-1.0F, -6.5246F, -1.629F, 3.0F, 6.0F, 3.0F, 0.0F, true);
    
    this.leftTusk2 = new ModelRenderer((Model)this);
    this.leftTusk2.setRotationPoint(0.2445F, -6.1921F, -0.3535F);
    this.leftTusk.addChild(this.leftTusk2);
    setRotationAngle(this.leftTusk2, -0.3054F, 0.0F, 0.0F);
    this.leftTusk2.setTextureOffset(69, 0).addBox(-1.2445F, -5.9104F, -1.4339F, 3.0F, 6.0F, 3.0F, 0.0F, true);
    
    this.leftTusk3 = new ModelRenderer((Model)this);
    this.leftTusk3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftTusk2.addChild(this.leftTusk3);
    setRotationAngle(this.leftTusk3, -0.3054F, 0.0F, 0.0F);
    this.leftTusk3.setTextureOffset(69, 0).addBox(-1.2445F, -11.1604F, -3.1839F, 3.0F, 6.0F, 3.0F, 0.0F, true);
    
    this.bipedBody = this.body;
    this.bipedHead = this.head;
    this.bipedRightArm = this.rightArm;
    this.bipedLeftArm = this.leftArm;
    this.bipedRightLeg = this.rightLeg;
    this.bipedLeftLeg = this.leftLeg;
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    boolean flag = (entity.getTicksElytraFlying() > 4);
    boolean flag1 = entity.isActualySwimming();
    this.head.rotateAngleY = netHeadYaw * 0.011635529F;
    if (flag) {
      this.head.rotateAngleX = -0.7853982F;
    } else if (this.swimAnimation > 0.0F) {
      
      if (flag1) {
        this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, -0.7853982F, this.swimAnimation);
      } else {
        this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
      } 
    } else {
      
      this.head.rotateAngleX = headPitch * 0.015707964F;
      if (this.head.rotateAngleX > 0.6D) {
        this.head.rotateAngleX = 0.6F;
      }
    } 
    
    float f = 1.0F;
    this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.3F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
    this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.3F) * 0.8F * limbSwingAmount * 0.5F / f;
    this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.3F) * 0.7F * limbSwingAmount / f;
    this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.3F + 3.1415927F) * 0.7F * limbSwingAmount / f;
    if (!entity.getHeldItemMainhand().isEmpty())
      this.rightArm.rotateAngleX += -0.15F; 
    if (entity.isSprinting()) {
      
      this.tail.rotateAngleX = 1.6F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
      this.leftEar.rotateAngleY = -0.3F - MathHelper.cos(limbSwing * 0.6F) * 0.2F * limbSwingAmount;
      this.rightEar.rotationPointX = (float)(this.rightEar.rotationPointX + 0.8D);
      this.rightEar.rotateAngleY = 0.3F + MathHelper.cos(limbSwing * 0.6F) * 0.2F * limbSwingAmount;
    } 

    
    this.swingProgress = ((LivingEntity)entity).swingProgress;
    boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
    if (this.swingProgress > 0.0F)
    {
      if (isBlackLeg) {
        
        this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
        this.rightLeg.rotateAngleY += this.body.rotateAngleY;
        this.leftLeg.rotateAngleY += this.body.rotateAngleY;
        float f1 = 1.0F - this.swingProgress;
        f1 *= f1;
        f1 *= f1;
        f1 = 1.0F - f1;
        float f2 = MathHelper.sin(f1 * 3.1415927F);
        float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
        this.rightLeg.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
        this.rightLeg.rotateAngleY += this.body.rotateAngleY * 2.0F;
      }
      else {
        
        this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
        this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
        this.rightArm.rotationPointZ = MathHelper.sin(this.body2.rotateAngleY) * 8.0F;
        this.rightArm.rotationPointX = -MathHelper.cos(this.body2.rotateAngleY) * 8.0F;
        this.rightArm.rotateAngleY += this.body2.rotateAngleY;
        this.leftArm.rotateAngleY += this.body2.rotateAngleY;
        this.leftArm.rotateAngleX += this.body2.rotateAngleY;
        float f1 = 1.0F - this.swingProgress;
        f1 *= f1;
        f1 *= f1;
        f1 = 1.0F - f1;
        float f2 = MathHelper.sin(f1 * 3.1415927F);
        float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
        this.rightArm.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.2D + f3);
        this.rightArm.rotateAngleY += this.body.rotateAngleY * 2.0F;
        this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
      } 
    }

    
    if (entity.isSneaking()) {
      
      this.body.rotateAngleX = 0.5F;
      this.body.rotationPointZ -= 4.0F;
      this.rightArm.rotateAngleX += 0.4F;
      this.rightArm.rotationPointZ -= 8.5F;
      this.rightArm.rotationPointY++;
      this.leftArm.rotateAngleX += 0.4F;
      this.leftArm.rotationPointZ -= 8.5F;
      this.leftArm.rotationPointY++;
      this.rightLeg.rotationPointZ = 1.5F;
      this.leftLeg.rotationPointZ = 1.5F;
      this.rightLeg.rotationPointY = 9.0F;
      this.leftLeg.rotationPointY = 9.0F;
      this.head.rotationPointZ -= 7.0F;
      this.head.rotationPointY += 3.0F;
    } 
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.head.render(matrixStack, buffer, packedLight, packedOverlay);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }


  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.rotate(Vector3f.YP.rotationDegrees(90.0F));
      matrixStack.translate(0.15D, -0.3D, -0.5D);
      this.rightArm2.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
    else {
      
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
      matrixStack.translate(0.15D, -0.3D, -0.5D);
      this.rightArm2.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
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


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    super.translateHand(side, matrixStack);
    matrixStack.translate((side == HandSide.RIGHT) ? -0.1D : 0.1D, 0.4D, 0.0D);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees(0.0F));
  }
}


