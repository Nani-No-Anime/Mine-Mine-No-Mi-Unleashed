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

public class ZouHeavyModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer body;
  private final ModelRenderer body2;
  private final ModelRenderer tail;
  private final ModelRenderer tail2;
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
  private final ModelRenderer leftTusk;
  private final ModelRenderer leftTusk2;
  private final ModelRenderer rightTusk;
  private final ModelRenderer rightTusk2;
  
  public ZouHeavyModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 64;
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.0F, -2.6F, 2.7F);
    setRotationAngle(this.body, 0.0873F, 0.0F, 0.0F);
    this.body.setTextureOffset(35, 0).addBox(-9.0F, -10.0F, -7.0F, 18.0F, 14.0F, 10.0F, 0.0F, false);
    
    this.body2 = new ModelRenderer((Model)this);
    this.body2.setRotationPoint(0.0F, 13.8F, 0.3F);
    this.body.addChild(this.body2);
    this.body2.setTextureOffset(35, 25).addBox(-8.5F, -10.0F, -7.0F, 17.0F, 10.0F, 10.0F, 0.0F, false);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(0.0F, -2.5F, 2.0F);
    this.body2.addChild(this.tail);
    setRotationAngle(this.tail, 0.4363F, 0.0F, 0.0F);
    this.tail.setTextureOffset(114, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, 0.0F, false);
    
    this.tail2 = new ModelRenderer((Model)this);
    this.tail2.setRotationPoint(0.0F, 5.2282F, -0.0718F);
    this.tail.addChild(this.tail2);
    this.tail2.setTextureOffset(119, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
    
    this.rightLeg = new ModelRenderer((Model)this);
    this.rightLeg.setRotationPoint(-5.0F, 11.1F, 1.0F);
    setRotationAngle(this.rightLeg, -0.0524F, 0.0F, 0.0F);
    this.rightLeg.setTextureOffset(42, 46).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F, 0.0F, false);
    
    this.rightLeg2 = new ModelRenderer((Model)this);
    this.rightLeg2.setRotationPoint(0.0F, 6.8F, 0.0F);
    this.rightLeg.addChild(this.rightLeg2);
    setRotationAngle(this.rightLeg2, 0.0524F, 0.0F, 0.0F);
    this.rightLeg2.setTextureOffset(63, 46).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);
    
    this.leftLeg = new ModelRenderer((Model)this);
    this.leftLeg.setRotationPoint(5.0F, 11.1F, 1.0F);
    setRotationAngle(this.leftLeg, -0.0524F, 0.0F, 0.0F);
    this.leftLeg.setTextureOffset(42, 46).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F, 0.0F, false);
    
    this.leftLeg2 = new ModelRenderer((Model)this);
    this.leftLeg2.setRotationPoint(0.0F, 6.8F, 0.0F);
    this.leftLeg.addChild(this.leftLeg2);
    setRotationAngle(this.leftLeg2, 0.0524F, 0.0F, 0.0F);
    this.leftLeg2.setTextureOffset(63, 46).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);
    
    this.rightArm = new ModelRenderer((Model)this);
    this.rightArm.setRotationPoint(-9.0F, -9.6F, 1.0F);
    setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.0349F);
    this.rightArm.setTextureOffset(0, 46).addBox(-5.0F, -2.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
    
    this.rightArm2 = new ModelRenderer((Model)this);
    this.rightArm2.setRotationPoint(-2.5F, 7.8F, 0.0F);
    this.rightArm.addChild(this.rightArm2);
    setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.0349F);
    this.rightArm2.setTextureOffset(21, 46).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
    
    this.leftArm = new ModelRenderer((Model)this);
    this.leftArm.setRotationPoint(9.0F, -9.6F, 1.0F);
    setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.0349F);
    this.leftArm.setTextureOffset(0, 46).addBox(0.0F, -2.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
    
    this.leftArm2 = new ModelRenderer((Model)this);
    this.leftArm2.setRotationPoint(2.5F, 7.8F, 0.0F);
    this.leftArm.addChild(this.leftArm2);
    setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.0349F);
    this.leftArm2.setTextureOffset(21, 46).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(0.0F, -12.2F, -3.0F);
    setRotationAngle(this.head, 0.0524F, 0.0F, 0.0F);
    this.head.setTextureOffset(0, 0).addBox(-4.0F, -9.0F, -4.5F, 8.0F, 9.0F, 9.0F, 0.0F, false);
    
    this.snout = new ModelRenderer((Model)this);
    this.snout.setRotationPoint(-2.0F, -1.5F, -4.5F);
    this.head.addChild(this.snout);
    setRotationAngle(this.snout, -0.2094F, 0.0F, 0.0F);
    this.snout.setTextureOffset(112, 35).addBox(0.0F, 0.0F, 0.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
    
    this.snout2 = new ModelRenderer((Model)this);
    this.snout2.setRotationPoint(0.5F, 7.0F, 0.5F);
    this.snout.addChild(this.snout2);
    setRotationAngle(this.snout2, 0.2094F, 0.0F, 0.0F);
    this.snout2.setTextureOffset(116, 47).addBox(0.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.snout3 = new ModelRenderer((Model)this);
    this.snout3.setRotationPoint(0.5F, 5.5F, 0.5F);
    this.snout2.addChild(this.snout3);
    setRotationAngle(this.snout3, 0.2094F, 0.0F, 0.0F);
    this.snout3.setTextureOffset(120, 57).addBox(0.0F, 0.0F, 0.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
    
    this.leftEar = new ModelRenderer((Model)this);
    this.leftEar.setRotationPoint(3.5F, -3.8F, 0.0F);
    this.head.addChild(this.leftEar);
    setRotationAngle(this.leftEar, -0.2365F, -1.0306F, 0.274F);
    this.leftEar.setTextureOffset(0, 19).addBox(0.0F, -6.0F, 0.0F, 6.0F, 8.0F, 1.0F, 0.0F, true);
    
    this.rightEar = new ModelRenderer((Model)this);
    this.rightEar.setRotationPoint(-3.5F, -3.8F, -1.0F);
    this.head.addChild(this.rightEar);
    setRotationAngle(this.rightEar, -0.2365F, 1.0306F, -0.274F);
    this.rightEar.setTextureOffset(0, 19).addBox(-6.0F, -6.0F, 0.0F, 6.0F, 8.0F, 1.0F, 0.0F, false);
    
    this.leftTusk = new ModelRenderer((Model)this);
    this.leftTusk.setRotationPoint(2.3F, -1.0F, -3.5F);
    this.head.addChild(this.leftTusk);
    setRotationAngle(this.leftTusk, -0.3491F, -0.1745F, 0.0F);
    this.leftTusk.setTextureOffset(15, 19).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    
    this.leftTusk2 = new ModelRenderer((Model)this);
    this.leftTusk2.setRotationPoint(0.0F, 3.8F, 0.0F);
    this.leftTusk.addChild(this.leftTusk2);
    setRotationAngle(this.leftTusk2, -0.1745F, -0.0175F, 0.0F);
    this.leftTusk2.setTextureOffset(15, 25).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    
    this.rightTusk = new ModelRenderer((Model)this);
    this.rightTusk.setRotationPoint(-2.3F, -1.0F, -3.5F);
    this.head.addChild(this.rightTusk);
    setRotationAngle(this.rightTusk, -0.3491F, 0.1745F, 0.0F);
    this.rightTusk.setTextureOffset(15, 19).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    
    this.rightTusk2 = new ModelRenderer((Model)this);
    this.rightTusk2.setRotationPoint(0.0F, 3.9F, 0.0F);
    this.rightTusk.addChild(this.rightTusk2);
    setRotationAngle(this.rightTusk2, -0.1745F, 0.0175F, 0.0F);
    this.rightTusk2.setTextureOffset(15, 25).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    
    this.bipedBody = this.body;
    this.bipedHead = this.head;
    this.bipedRightArm = this.rightArm;
    this.bipedLeftArm = this.leftArm;
    this.bipedRightLeg = this.rightLeg;
    this.bipedLeftLeg = this.leftLeg;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
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
      
      this.bipedHead.rotateAngleX = headPitch * 0.015707964F;
      if (this.bipedHead.rotateAngleX > 0.6D) {
        this.bipedHead.rotateAngleX = 0.6F;
      }
    } 
    
    float f = 1.0F;
    this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
    this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
    this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
    this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
    if (!entity.getHeldItemMainhand().isEmpty())
      this.rightArm.rotateAngleX += -0.15F; 
    if (entity.isSprinting()) {
      
      this.tail.rotateAngleX = 1.6F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
      this.leftEar.rotateAngleY = -0.3F - MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
      this.rightEar.rotateAngleY = 0.3F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
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


  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(-0.3D, -0.15D, 0.0D);
      this.rightArm2.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
    else {
      
      matrixStack.translate(0.6D, -0.15D, 0.0D);
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
    matrixStack.translate((side == HandSide.RIGHT) ? -0.06D : 0.06D, 0.5D, 0.0D);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees(0.0F));
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


