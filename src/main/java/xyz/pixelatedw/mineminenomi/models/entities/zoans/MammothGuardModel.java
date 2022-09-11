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

public class MammothGuardModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer rightFrontLeg;
  private final ModelRenderer leftFrontLeg;
  private final ModelRenderer rightBackLeg;
  private final ModelRenderer leftBackLeg;
  private final ModelRenderer body;
  private final ModelRenderer body2;
  private final ModelRenderer body3;
  private final ModelRenderer tail;
  private final ModelRenderer neck;
  private final ModelRenderer head;
  private final ModelRenderer rightEar;
  private final ModelRenderer leftEar;
  private final ModelRenderer trunk;
  private final ModelRenderer trunk2;
  private final ModelRenderer trunk3;
  private final ModelRenderer trunk4;
  private final ModelRenderer rightTusk;
  private final ModelRenderer rightTusk2;
  private final ModelRenderer rightTusk3;
  private final ModelRenderer rightTusk4;
  private final ModelRenderer rightTusk5;
  private final ModelRenderer leftTusk;
  private final ModelRenderer leftTusk2;
  private final ModelRenderer leftTusk3;
  private final ModelRenderer leftTusk4;
  private final ModelRenderer leftTusk5;
  
  public MammothGuardModel() {
    super(1.0F);
    this.textureWidth = 256;
    this.textureHeight = 256;
    
    this.rightFrontLeg = new ModelRenderer((Model)this);
    this.rightFrontLeg.setRotationPoint(-5.0F, 10.0F, -12.0F);
    this.rightFrontLeg.setTextureOffset(28, 96).addBox(-3.5F, 0.0F, -3.5F, 6.0F, 15.0F, 6.0F, 0.0F, false);
    
    this.leftFrontLeg = new ModelRenderer((Model)this);
    this.leftFrontLeg.setRotationPoint(5.0F, 10.0F, -13.0F);
    this.leftFrontLeg.setTextureOffset(28, 96).addBox(-2.5F, 0.0F, -2.5F, 6.0F, 15.0F, 6.0F, 0.0F, false);
    
    this.rightBackLeg = new ModelRenderer((Model)this);
    this.rightBackLeg.setRotationPoint(-5.0F, 10.0F, 12.0F);
    this.rightBackLeg.setTextureOffset(28, 96).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 15.0F, 6.0F, 0.0F, false);
    
    this.leftBackLeg = new ModelRenderer((Model)this);
    this.leftBackLeg.setRotationPoint(5.5F, 10.0F, 12.0F);
    this.leftBackLeg.setTextureOffset(28, 96).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 15.0F, 6.0F, 0.0F, false);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.0F, -5.6F, -17.0F);
    setRotationAngle(this.body, 0.0782F, 0.0F, 0.0F);
    this.body.setTextureOffset(0, 0).addBox(-10.0F, -11.9126F, -1.199F, 20.0F, 30.0F, 20.0F, 0.0F, false);
    
    this.body2 = new ModelRenderer((Model)this);
    this.body2.setRotationPoint(-0.5F, 4.684F, 18.7307F);
    this.body.addChild(this.body2);
    setRotationAngle(this.body2, -0.1672F, 0.0F, 0.0F);
    this.body2.setTextureOffset(76, 32).addBox(-9.0F, -12.685F, -9.4673F, 19.0F, 27.0F, 18.0F, 0.0F, false);
    
    this.body3 = new ModelRenderer((Model)this);
    this.body3.setRotationPoint(0.4F, 2.9704F, 9.0416F);
    this.body2.addChild(this.body3);
    setRotationAngle(this.body3, -0.1564F, 0.0F, 0.0F);
    this.body3.setTextureOffset(0, 50).addBox(-9.0F, -13.6175F, -10.6404F, 18.0F, 26.0F, 20.0F, 0.0F, false);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(0.0F, -9.2051F, 5.6705F);
    this.body3.addChild(this.tail);
    setRotationAngle(this.tail, -0.9163F, 0.0F, 0.0F);
    this.tail.setTextureOffset(24, 117).addBox(-1.9F, -1.8493F, 2.5571F, 3.0F, 3.0F, 10.0F, 0.0F, false);
    
    this.neck = new ModelRenderer((Model)this);
    this.neck.setRotationPoint(0.0F, -0.516F, -1.8693F);
    this.body.addChild(this.neck);
    setRotationAngle(this.neck, -0.0436F, 0.0F, 0.0F);
    this.neck.setTextureOffset(80, 0).addBox(-5.5F, -8.0751F, -4.7215F, 10.0F, 15.0F, 10.0F, 0.0F, false);
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(0.3F, -5.9F, 1.8F);
    this.head.setTextureOffset(76, 77).addBox(-8.0F, -13.0F, -36.0F, 15.0F, 20.0F, 15.0F, 0.0F, false);
    
    this.rightEar = new ModelRenderer((Model)this);
    this.rightEar.setRotationPoint(-7.95F, -8.1F, -28.7F);
    this.head.addChild(this.rightEar);
    setRotationAngle(this.rightEar, 0.0524F, 0.7679F, -0.0873F);
    this.rightEar.setTextureOffset(55, 115).addBox(-11.2484F, -4.4659F, -0.0486F, 12.0F, 17.0F, 0.0F, 0.0F, false);
    
    this.leftEar = new ModelRenderer((Model)this);
    this.leftEar.setRotationPoint(5.8F, -5.1F, -27.7F);
    this.head.addChild(this.leftEar);
    setRotationAngle(this.leftEar, 0.0524F, -0.7679F, 0.0873F);
    this.leftEar.setTextureOffset(55, 115).addBox(-1.2718F, -7.9778F, -1.4385F, 12.0F, 17.0F, 0.0F, 0.0F, true);
    
    this.trunk = new ModelRenderer((Model)this);
    this.trunk.setRotationPoint(-0.25F, -1.0F, -34.9F);
    this.head.addChild(this.trunk);
    setRotationAngle(this.trunk, 0.5061F, 0.0F, 0.0F);
    this.trunk.setTextureOffset(113, 18).addBox(-3.65F, -2.634F, -5.705F, 7.0F, 7.0F, 7.0F, 0.0F, false);
    
    this.trunk2 = new ModelRenderer((Model)this);
    this.trunk2.setRotationPoint(0.25F, 10.2166F, 16.8113F);
    this.trunk.addChild(this.trunk2);
    setRotationAngle(this.trunk2, -0.48F, 0.0F, 0.0F);
    this.trunk2.setTextureOffset(0, 96).addBox(-3.9F, -1.2635F, -25.8035F, 7.0F, 13.0F, 7.0F, 0.01F, false);
    
    this.trunk3 = new ModelRenderer((Model)this);
    this.trunk3.setRotationPoint(-0.5F, 11.5F, 1.4F);
    this.trunk2.addChild(this.trunk3);
    setRotationAngle(this.trunk3, 0.2346F, 0.0F, 0.0F);
    this.trunk3.setTextureOffset(0, 116).addBox(-2.9F, -6.3296F, -25.9225F, 6.0F, 10.0F, 6.0F, 0.0F, false);
    
    this.trunk4 = new ModelRenderer((Model)this);
    this.trunk4.setRotationPoint(0.1F, 9.6F, -1.45F);
    this.trunk3.addChild(this.trunk4);
    setRotationAngle(this.trunk4, 0.0719F, 0.0F, 0.0F);
    this.trunk4.setTextureOffset(0, 0).addBox(-1.9F, -7.8112F, -23.2052F, 4.0F, 6.0F, 4.0F, 0.0F, false);
    
    this.rightTusk = new ModelRenderer((Model)this);
    this.rightTusk.setRotationPoint(-4.8F, 1.9F, -10.8F);
    this.head.addChild(this.rightTusk);
    setRotationAngle(this.rightTusk, 2.0944F, 0.4363F, 0.0F);
    this.rightTusk.setTextureOffset(110, 0).addBox(8.1202F, -24.2825F, 9.3245F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.rightTusk2 = new ModelRenderer((Model)this);
    this.rightTusk2.setRotationPoint(-0.2F, -5.7F, 0.6F);
    this.rightTusk.addChild(this.rightTusk2);
    setRotationAngle(this.rightTusk2, 2.8274F, 0.0F, 0.0F);
    this.rightTusk2.setTextureOffset(110, 0).addBox(8.3702F, 20.0896F, -5.4339F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.rightTusk3 = new ModelRenderer((Model)this);
    this.rightTusk3.setRotationPoint(0.0F, 5.1F, 0.1F);
    this.rightTusk2.addChild(this.rightTusk3);
    setRotationAngle(this.rightTusk3, -0.384F, 0.0F, 0.0F);
    this.rightTusk3.setTextureOffset(110, 0).addBox(8.3702F, 20.1284F, 2.5197F, 3.0F, 6.0F, 3.0F, -0.01F, false);
    
    this.rightTusk4 = new ModelRenderer((Model)this);
    this.rightTusk4.setRotationPoint(-0.3F, 5.1F, 0.0F);
    this.rightTusk3.addChild(this.rightTusk4);
    setRotationAngle(this.rightTusk4, -0.1571F, 0.0F, 0.0F);
    this.rightTusk4.setTextureOffset(110, 0).addBox(8.7202F, 19.3094F, 5.6865F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.rightTusk5 = new ModelRenderer((Model)this);
    this.rightTusk5.setRotationPoint(0.7F, 5.1F, -0.25F);
    this.rightTusk4.addChild(this.rightTusk5);
    setRotationAngle(this.rightTusk5, -0.2793F, 0.0F, 0.0F);
    this.rightTusk5.setTextureOffset(110, 0).addBox(8.1202F, 16.6753F, 11.1062F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.leftTusk = new ModelRenderer((Model)this);
    this.leftTusk.setRotationPoint(4.45F, 1.9F, -10.8F);
    this.head.addChild(this.leftTusk);
    setRotationAngle(this.leftTusk, 2.0944F, -0.4363F, 0.0F);
    this.leftTusk.setTextureOffset(110, 0).addBox(-11.3202F, -24.2825F, 9.3245F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.leftTusk2 = new ModelRenderer((Model)this);
    this.leftTusk2.setRotationPoint(-0.2F, -5.7F, 0.6F);
    this.leftTusk.addChild(this.leftTusk2);
    setRotationAngle(this.leftTusk2, 2.8274F, 0.0F, 0.0F);
    this.leftTusk2.setTextureOffset(110, 0).addBox(-11.0702F, 20.0896F, -5.4339F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.leftTusk3 = new ModelRenderer((Model)this);
    this.leftTusk3.setRotationPoint(0.0F, 5.1F, 0.1F);
    this.leftTusk2.addChild(this.leftTusk3);
    setRotationAngle(this.leftTusk3, -0.384F, 0.0F, 0.0F);
    this.leftTusk3.setTextureOffset(110, 0).addBox(-11.0702F, 20.1284F, 2.5197F, 3.0F, 6.0F, 3.0F, -0.01F, false);
    
    this.leftTusk4 = new ModelRenderer((Model)this);
    this.leftTusk4.setRotationPoint(-0.3F, 5.1F, 0.0F);
    this.leftTusk3.addChild(this.leftTusk4);
    setRotationAngle(this.leftTusk4, -0.1571F, 0.0F, 0.0F);
    this.leftTusk4.setTextureOffset(110, 0).addBox(-10.7202F, 19.3094F, 5.6865F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.leftTusk5 = new ModelRenderer((Model)this);
    this.leftTusk5.setRotationPoint(0.7F, 5.1F, -0.25F);
    this.leftTusk4.addChild(this.leftTusk5);
    setRotationAngle(this.leftTusk5, -0.2793F, 0.0F, 0.0F);
    this.leftTusk5.setTextureOffset(110, 0).addBox(-11.3202F, 16.6753F, 11.1062F, 3.0F, 6.0F, 3.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.rightFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightBackLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftBackLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
    this.head.render(matrixStack, buffer, packedLight, packedOverlay);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    boolean flag = (entity.getTicksElytraFlying() > 4);
    boolean flag1 = entity.isActualySwimming();
    this.head.rotateAngleY = MathHelper.clamp(netHeadYaw * 0.017453292F, -0.1F, 0.1F);
    if (flag) {
      this.head.rotateAngleX = -0.7853982F;
    } else if (this.swimAnimation > 0.0F) {
      
      if (flag1) {
        this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, -0.7853982F, this.swimAnimation);
      } else {
        this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
      } 
    } else {
      
      this.head.rotateAngleX = headPitch * 0.017453292F;
      if (this.head.rotateAngleX > 0.1D) {
        this.head.rotateAngleX = 0.1F;
      } else if (this.head.rotateAngleX < -0.1D) {
        this.head.rotateAngleX = -0.1F;
      } 
    } 
    
    this.rightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.4F) * 0.3F * limbSwingAmount;
    this.leftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.4F) * 0.4F * limbSwingAmount;
    this.rightBackLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.4F + 3.1415927F) * 0.3F * limbSwingAmount;
    this.leftBackLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.4F + 3.1415927F) * 0.4F * limbSwingAmount;
    if (entity.isSprinting()) {
      
      this.tail.rotateAngleX = 0.1F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
      this.leftEar.rotateAngleY = -0.3F - MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
      this.rightEar.rotateAngleY = 0.3F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
    } 

    
    this.swingProgress = ((LivingEntity)entity).swingProgress;
    if (this.swingProgress > 0.0F) {
      
      this.head.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
      this.head.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
      this.trunk.rotateAngleY += this.body2.rotateAngleY;
      float f1 = 1.0F - this.swingProgress;
      f1 *= f1;
      f1 *= f1;
      f1 = 1.0F - f1;
      float f2 = MathHelper.sin(f1 * 3.1415927F);
      float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
      this.trunk.rotateAngleX = (float)(this.trunk.rotateAngleX - f2 * 1.2D + f3);
      this.trunk.rotateAngleY += this.body.rotateAngleY * 2.0F;
      this.trunk.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
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
    this.trunk.translateRotate(matrixStack);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
    matrixStack.rotate(Vector3f.XP.rotationDegrees(260.0F));
    matrixStack.translate(1.3D, -0.02D, 0.1D);
  }
}


