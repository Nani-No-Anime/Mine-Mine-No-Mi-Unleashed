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

public class GiraffeHeavyModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer neck;
  private final ModelRenderer neck2;
  private final ModelRenderer head;
  private final ModelRenderer rightEar;
  private final ModelRenderer head2;
  private final ModelRenderer leftHorn;
  private final ModelRenderer rightHorn;
  private final ModelRenderer leftEar;
  private final ModelRenderer mane;
  private final ModelRenderer mane2;
  private final ModelRenderer body;
  private final ModelRenderer leftShoulder;
  private final ModelRenderer rightShoulder;
  private final ModelRenderer rightArm;
  private final ModelRenderer rightArm2;
  private final ModelRenderer rightHand2;
  private final ModelRenderer rightHand1;
  private final ModelRenderer leftArm;
  private final ModelRenderer leftArm2;
  private final ModelRenderer leftHand2;
  private final ModelRenderer leftHand1;
  private final ModelRenderer rightLeg;
  private final ModelRenderer rightLeg3;
  private final ModelRenderer rightLeg2;
  private final ModelRenderer rightHoof;
  private final ModelRenderer rightHoof2;
  private final ModelRenderer rightHoof3;
  private final ModelRenderer tail;
  private final ModelRenderer tail2;
  private final ModelRenderer tail3;
  private final ModelRenderer leftLeg;
  private final ModelRenderer leftLeg2;
  private final ModelRenderer leftLeg3;
  private final ModelRenderer leftHoof;
  private final ModelRenderer leftHoof2;
  private final ModelRenderer leftHoof3;
  
  public GiraffeHeavyModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 64;
    
    this.neck = new ModelRenderer((Model)this);
    this.neck.setRotationPoint(-2.0F, -11.0F, -3.5F);
    setRotationAngle(this.neck, 0.1745F, 0.0F, 0.0F);
    this.neck.setTextureOffset(79, 0).addBox(0.0F, 0.0F, 0.0F, 5.0F, 8.0F, 5.0F, 0.0F, false);
    
    this.neck2 = new ModelRenderer((Model)this);
    this.neck2.setRotationPoint(0.5F, -7.0F, -0.5F);
    this.neck.addChild(this.neck2);
    setRotationAngle(this.neck2, 0.1396F, 0.0F, 0.0F);
    this.neck2.setTextureOffset(79, 14).addBox(0.0F, 0.0F, 0.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(2.0F, 0.5F, 1.5F);
    this.neck2.addChild(this.head);
    setRotationAngle(this.head, -0.3142F, 0.0F, 0.0F);
    this.head.setTextureOffset(32, 18).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 3.0F, 8.0F, 0.0F, false);
    
    this.rightEar = new ModelRenderer((Model)this);
    this.rightEar.setRotationPoint(-2.0F, -3.5F, 0.0F);
    this.head.addChild(this.rightEar);
    setRotationAngle(this.rightEar, 0.0F, 0.0F, -0.2618F);
    this.rightEar.setTextureOffset(32, 0).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, false);
    
    this.head2 = new ModelRenderer((Model)this);
    this.head2.setRotationPoint(-2.0F, -1.0F, 0.5F);
    this.head.addChild(this.head2);
    this.head2.setTextureOffset(31, 29).addBox(0.01F, 0.0F, -4.0F, 4.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.leftHorn = new ModelRenderer((Model)this);
    this.leftHorn.setRotationPoint(0.5F, -6.0F, -0.5F);
    this.head.addChild(this.leftHorn);
    this.leftHorn.setTextureOffset(60, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightHorn = new ModelRenderer((Model)this);
    this.rightHorn.setRotationPoint(-1.5F, -6.0F, -0.5F);
    this.head.addChild(this.rightHorn);
    this.rightHorn.setTextureOffset(60, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftEar = new ModelRenderer((Model)this);
    this.leftEar.setRotationPoint(2.0F, -3.5F, 0.0F);
    this.head.addChild(this.leftEar);
    setRotationAngle(this.leftEar, 0.0F, 0.0F, 0.2618F);
    this.leftEar.setTextureOffset(32, 0).addBox(0.0F, 0.0F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, true);
    
    this.mane = new ModelRenderer((Model)this);
    this.mane.setRotationPoint(2.0F, -2.0F, 3.5F);
    this.neck2.addChild(this.mane);
    setRotationAngle(this.mane, 0.0349F, 0.0F, 0.0F);
    this.mane.setTextureOffset(76, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 10.0F, 1.0F, 0.0F, false);
    
    this.mane2 = new ModelRenderer((Model)this);
    this.mane2.setRotationPoint(2.5F, 0.0F, 4.6F);
    this.neck.addChild(this.mane2);
    setRotationAngle(this.mane2, 0.0175F, 0.0F, 0.0F);
    this.mane2.setTextureOffset(76, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 8.0F, 1.0F, 0.0F, false);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(-0.5F, -4.0F, 0.0F);
    this.body.setTextureOffset(0, 0).addBox(-4.0F, 0.0F, -2.0F, 10.0F, 16.0F, 5.0F, 0.0F, false);
    
    this.leftShoulder = new ModelRenderer((Model)this);
    this.leftShoulder.setRotationPoint(-0.5F, 7.5F, -1.9F);
    this.body.addChild(this.leftShoulder);
    setRotationAngle(this.leftShoulder, 0.0F, 0.0F, -0.9599F);
    this.leftShoulder.setTextureOffset(47, 0).addBox(0.0F, 0.0F, -0.1F, 9.0F, 7.0F, 5.0F, -0.01F, true);
    
    this.rightShoulder = new ModelRenderer((Model)this);
    this.rightShoulder.setRotationPoint(2.5F, 7.4F, -1.91F);
    this.body.addChild(this.rightShoulder);
    setRotationAngle(this.rightShoulder, 0.0F, 0.0F, -2.2689F);
    this.rightShoulder.setTextureOffset(47, 0).addBox(0.0F, -7.5F, -0.09F, 9.0F, 7.0F, 5.0F, -0.01F, false);
    
    this.rightArm = new ModelRenderer((Model)this);
    this.rightArm.setRotationPoint(-8.0F, 1.0F, -1.0F);
    setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.2793F);
    this.rightArm.setTextureOffset(23, 30).addBox(0.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
    
    this.rightArm2 = new ModelRenderer((Model)this);
    this.rightArm2.setRotationPoint(0.0F, 6.0F, 0.0F);
    this.rightArm.addChild(this.rightArm2);
    setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.4189F);
    this.rightArm2.setTextureOffset(23, 39).addBox(0.0F, 0.0F, 0.1F, 2.0F, 6.0F, 2.0F, 0.0F, false);
    
    this.rightHand2 = new ModelRenderer((Model)this);
    this.rightHand2.setRotationPoint(1.1392F, 5.1097F, 0.8F);
    this.rightArm2.addChild(this.rightHand2);
    setRotationAngle(this.rightHand2, 0.1745F, 0.0F, -0.3491F);
    this.rightHand2.setTextureOffset(65, 20).addBox(0.0F, -0.0868F, -0.4924F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightHand1 = new ModelRenderer((Model)this);
    this.rightHand1.setRotationPoint(0.0F, 5.5F, 2.0F);
    this.rightArm2.addChild(this.rightHand1);
    setRotationAngle(this.rightHand1, 0.1745F, 1.5708F, 0.1396F);
    this.rightHand1.setTextureOffset(65, 24).addBox(-0.1F, 0.0F, 0.1F, 2.0F, 2.0F, 1.0F, -0.01F, false);
    
    this.leftArm = new ModelRenderer((Model)this);
    this.leftArm.setRotationPoint(9.0F, 1.0F, -1.0F);
    setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.2793F);
    this.leftArm.setTextureOffset(23, 30).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
    
    this.leftArm2 = new ModelRenderer((Model)this);
    this.leftArm2.setRotationPoint(0.0F, 6.0F, 0.0F);
    this.leftArm.addChild(this.leftArm2);
    setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.4189F);
    this.leftArm2.setTextureOffset(23, 39).addBox(-2.0F, 0.0F, 0.1F, 2.0F, 6.0F, 2.0F, 0.0F, false);
    
    this.leftHand2 = new ModelRenderer((Model)this);
    this.leftHand2.setRotationPoint(-7.4075F, 2.7019F, 1.8F);
    this.leftArm2.addChild(this.leftHand2);
    setRotationAngle(this.leftHand2, 0.1745F, 0.0F, 0.3491F);
    this.leftHand2.setTextureOffset(65, 20).addBox(5.9F, -0.07F, -1.4F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftHand1 = new ModelRenderer((Model)this);
    this.leftHand1.setRotationPoint(1.8929F, 2.9691F, 1.5F);
    this.leftArm2.addChild(this.leftHand1);
    setRotationAngle(this.leftHand1, 0.1745F, -1.5708F, -0.1396F);
    this.leftHand1.setTextureOffset(65, 24).addBox(-1.4F, 2.6065F, 1.9F, 2.0F, 2.0F, 1.0F, -0.01F, false);
    
    this.rightLeg = new ModelRenderer((Model)this);
    this.rightLeg.setRotationPoint(-2.0F, 11.6F, 1.0F);
    setRotationAngle(this.rightLeg, -0.3491F, 0.0F, 0.0F);
    this.rightLeg.setTextureOffset(10, 30).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 7.0F, 3.0F, 0.0F, false);
    
    this.rightLeg3 = new ModelRenderer((Model)this);
    this.rightLeg3.setRotationPoint(0.5F, 5.0F, 0.0F);
    this.rightLeg.addChild(this.rightLeg3);
    setRotationAngle(this.rightLeg3, 1.7453F, 0.0F, 0.0F);
    this.rightLeg3.setTextureOffset(10, 41).addBox(-2.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
    
    this.rightLeg2 = new ModelRenderer((Model)this);
    this.rightLeg2.setRotationPoint(0.0F, 5.2065F, -0.5747F);
    this.rightLeg3.addChild(this.rightLeg2);
    setRotationAngle(this.rightLeg2, -1.9199F, 0.0F, 0.0F);
    this.rightLeg2.setTextureOffset(0, 30).addBox(-2.0F, 0.0F, -2.0F, 2.0F, 6.0F, 2.0F, 0.01F, false);
    
    this.rightHoof = new ModelRenderer((Model)this);
    this.rightHoof.setRotationPoint(0.0F, 5.0F, -0.5F);
    this.rightLeg2.addChild(this.rightHoof);
    setRotationAngle(this.rightHoof, 0.5236F, 0.0F, 0.0F);
    this.rightHoof.setTextureOffset(0, 41).addBox(-2.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.rightHoof2 = new ModelRenderer((Model)this);
    this.rightHoof2.setRotationPoint(-1.0F, 0.0F, -2.5F);
    this.rightHoof.addChild(this.rightHoof2);
    setRotationAngle(this.rightHoof2, -0.1211F, -0.4883F, -0.0394F);
    this.rightHoof2.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightHoof3 = new ModelRenderer((Model)this);
    this.rightHoof3.setRotationPoint(-2.0F, 0.0F, -2.0F);
    this.rightHoof.addChild(this.rightHoof3);
    setRotationAngle(this.rightHoof3, -0.1211F, 0.4883F, 0.0394F);
    this.rightHoof3.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(0.0F, 10.0F, 3.0F);
    setRotationAngle(this.tail, -0.733F, 0.0F, 0.0F);
    this.tail.setTextureOffset(31, 3).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
    
    this.tail2 = new ModelRenderer((Model)this);
    this.tail2.setRotationPoint(0.0F, 0.0F, 4.5F);
    this.tail.addChild(this.tail2);
    setRotationAngle(this.tail2, 0.4712F, 0.0F, 0.0F);
    this.tail2.setTextureOffset(31, 10).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
    
    this.tail3 = new ModelRenderer((Model)this);
    this.tail3.setRotationPoint(-0.5F, -0.3578F, 3.2176F);
    this.tail2.addChild(this.tail3);
    setRotationAngle(this.tail3, 0.2094F, 0.0F, 0.0F);
    this.tail3.setTextureOffset(60, 13).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
    
    this.leftLeg = new ModelRenderer((Model)this);
    this.leftLeg.setRotationPoint(4.0F, 11.6F, 1.0F);
    setRotationAngle(this.leftLeg, -0.3491F, 0.0F, 0.0F);
    this.leftLeg.setTextureOffset(10, 30).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 7.0F, 3.0F, 0.0F, false);
    
    this.leftLeg2 = new ModelRenderer((Model)this);
    this.leftLeg2.setRotationPoint(0.5F, 5.0F, 0.0F);
    this.leftLeg.addChild(this.leftLeg2);
    setRotationAngle(this.leftLeg2, 1.7453F, 0.0F, 0.0F);
    this.leftLeg2.setTextureOffset(10, 41).addBox(-2.0F, 0.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
    
    this.leftLeg3 = new ModelRenderer((Model)this);
    this.leftLeg3.setRotationPoint(0.0F, 5.2065F, -0.5747F);
    this.leftLeg2.addChild(this.leftLeg3);
    setRotationAngle(this.leftLeg3, -1.9199F, 0.0F, 0.0F);
    this.leftLeg3.setTextureOffset(0, 30).addBox(-2.0F, 0.0F, -2.0F, 2.0F, 6.0F, 2.0F, 0.01F, false);
    
    this.leftHoof = new ModelRenderer((Model)this);
    this.leftHoof.setRotationPoint(0.0F, 5.0F, -0.5F);
    this.leftLeg3.addChild(this.leftHoof);
    setRotationAngle(this.leftHoof, 0.5236F, 0.0F, 0.0F);
    this.leftHoof.setTextureOffset(0, 41).addBox(-2.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.leftHoof2 = new ModelRenderer((Model)this);
    this.leftHoof2.setRotationPoint(-1.0F, 0.0F, -2.5F);
    this.leftHoof.addChild(this.leftHoof2);
    setRotationAngle(this.leftHoof2, -0.1211F, -0.4883F, -0.0394F);
    this.leftHoof2.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftHoof3 = new ModelRenderer((Model)this);
    this.leftHoof3.setRotationPoint(-2.0F, 0.0F, -2.0F);
    this.leftHoof.addChild(this.leftHoof3);
    setRotationAngle(this.leftHoof3, -0.1211F, 0.4883F, 0.0394F);
    this.leftHoof3.setTextureOffset(65, 20).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.bipedBody = this.body;
    this.bipedHead = this.head;
    this.bipedRightArm = this.rightArm;
    this.bipedLeftArm = this.leftArm;
    this.bipedRightLeg = this.rightLeg;
    this.bipedLeftLeg = this.leftLeg;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.neck.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
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
    this.rightLeg.rotateAngleX = -0.34F + MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
    this.leftLeg.rotateAngleX = -0.34F + MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
    if (!entity.getHeldItemMainhand().isEmpty())
      this.rightArm.rotateAngleX += -0.15F; 
    if (entity.isSprinting()) {
      
      this.tail.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
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
        this.rightArm.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 12.0F;
        this.rightArm.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 9.0F;
        this.rightArm.rotateAngleY += this.body.rotateAngleY;
        this.leftArm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
        this.leftArm.rotateAngleY -= this.body.rotateAngleY;
        this.leftArm.rotateAngleX -= this.body.rotateAngleY;
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
      this.rightArm.rotationPointZ -= 0.5F;
      this.leftArm.rotateAngleX += 0.4F;
      this.leftArm.rotationPointZ -= 0.5F;
      this.rightLeg.rotationPointZ = 4.0F;
      this.leftLeg.rotationPointZ = 4.5F;
      this.rightLeg.rotationPointY = 10.5F;
      this.leftLeg.rotationPointY = 10.5F;
      this.head.rotationPointY = 0.0F;
      this.head.rotationPointZ = 2.0F;
      this.neck.rotateAngleX += 0.25F;
      this.neck.rotationPointZ = -9.0F;
      this.neck.rotationPointY++;
    } 
  }


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    super.translateHand(side, matrixStack);
    matrixStack.translate((side == HandSide.RIGHT) ? 0.12D : -0.12D, 0.2D, 0.1D);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees((side == HandSide.RIGHT) ? -20.0F : 20.0F));
  }


  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(-0.5D, 0.0D, -0.1D);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
      matrixStack.rotate(Vector3f.ZP.rotationDegrees(10.0F));
      this.rightArm2.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
    else {
      
      matrixStack.translate(0.5D, 0.0D, -0.1D);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
      matrixStack.rotate(Vector3f.ZP.rotationDegrees(-10.0F));
      this.leftArm2.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
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

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


