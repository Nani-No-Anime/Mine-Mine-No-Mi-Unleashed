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

public class LeopardHeavyModel<T extends LivingEntity>
  extends ZoanMorphModel<T>
  implements IHasArm {
  private final ModelRenderer head;
  private final ModelRenderer snout;
  private final ModelRenderer snout2;
  private final ModelRenderer rightEar;
  private final ModelRenderer leftEar;
  private final ModelRenderer body;
  private final ModelRenderer body2;
  private final ModelRenderer body3;
  private final ModelRenderer waist;
  private final ModelRenderer neck;
  private final ModelRenderer tail;
  private final ModelRenderer tail2;
  private final ModelRenderer tail3;
  private final ModelRenderer tail4;
  private final ModelRenderer leftArm;
  private final ModelRenderer leftArm2;
  private final ModelRenderer leftArm3;
  private final ModelRenderer leftHand;
  private final ModelRenderer rightArm;
  private final ModelRenderer rightArm2;
  private final ModelRenderer rightArm3;
  private final ModelRenderer rightHand;
  private final ModelRenderer leftLeg;
  private final ModelRenderer leftLeg2;
  private final ModelRenderer leftLeg3;
  private final ModelRenderer leftFoot;
  private final ModelRenderer rightLeg;
  private final ModelRenderer rightLeg2;
  private final ModelRenderer rightLeg3;
  private final ModelRenderer rightLeg4;
  
  public LeopardHeavyModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 128;
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(0.0F, -14.25F, -1.6F);
    this.head.setTextureOffset(0, 71).addBox(-3.5F, -6.25F, -6.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);
    
    this.snout = new ModelRenderer((Model)this);
    this.snout.setRotationPoint(0.0F, -0.95F, -6.7F);
    this.head.addChild(this.snout);
    this.snout.setTextureOffset(10, 86).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.snout2 = new ModelRenderer((Model)this);
    this.snout2.setRotationPoint(0.0F, -8.15F, 5.9F);
    this.snout.addChild(this.snout2);
    setRotationAngle(this.snout2, 0.1955F, 0.0F, 0.0F);
    this.snout2.setTextureOffset(10, 91).addBox(-1.5F, 5.6073F, -8.0548F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.rightEar = new ModelRenderer((Model)this);
    this.rightEar.setRotationPoint(-2.85F, -13.5F, 1.15F);
    this.head.addChild(this.rightEar);
    setRotationAngle(this.rightEar, 0.0873F, -0.2618F, -0.4363F);
    this.rightEar.setTextureOffset(1, 86).addBox(-5.0148F, 4.4168F, -3.0083F, 3.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.leftEar = new ModelRenderer((Model)this);
    this.leftEar.setRotationPoint(1.9F, -14.0F, 1.4F);
    this.head.addChild(this.leftEar);
    setRotationAngle(this.leftEar, 0.0873F, 0.2618F, 0.4363F);
    this.leftEar.setTextureOffset(1, 86).addBox(3.0148F, 4.4168F, -3.0083F, 3.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.5F, -9.9F, 4.9F);
    this.body.setTextureOffset(0, 27).addBox(-6.9F, 5.25F, -5.5F, 12.0F, 6.0F, 7.0F, -0.01F, false);
    
    this.body2 = new ModelRenderer((Model)this);
    this.body2.setRotationPoint(1.1F, -7.7F, -0.6F);
    this.body.addChild(this.body2);
    this.body2.setTextureOffset(0, 0).addBox(-10.2F, 4.65F, -5.3F, 16.0F, 7.0F, 7.0F, 0.0F, false);
    
    this.body3 = new ModelRenderer((Model)this);
    this.body3.setRotationPoint(0.8F, -2.1F, 0.0F);
    this.body.addChild(this.body3);
    this.body3.setTextureOffset(0, 14).addBox(-9.5F, 5.25F, -5.5F, 15.0F, 6.0F, 7.0F, 0.0F, false);
    
    this.waist = new ModelRenderer((Model)this);
    this.waist.setRotationPoint(-0.5F, 4.5F, 0.3F);
    this.body.addChild(this.waist);
    this.waist.setTextureOffset(0, 41).addBox(-6.4F, 5.85F, -5.3F, 12.0F, 5.0F, 6.0F, 0.0F, false);
    
    this.neck = new ModelRenderer((Model)this);
    this.neck.setRotationPoint(-0.5F, -11.9F, -3.7F);
    this.body.addChild(this.neck);
    setRotationAngle(this.neck, 0.8601F, 0.0F, 0.0F);
    this.neck.setTextureOffset(0, 53).addBox(-2.5F, 0.2372F, -10.3836F, 5.0F, 10.0F, 7.0F, 0.0F, false);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(-0.75F, 14.2F, -0.45F);
    this.body.addChild(this.tail);
    setRotationAngle(this.tail, 0.6632F, 0.0F, 0.0F);
    this.tail.setTextureOffset(39, 49).addBox(-0.75F, -0.0952F, -0.5599F, 2.0F, 5.0F, 2.0F, 0.0F, false);
    
    this.tail2 = new ModelRenderer((Model)this);
    this.tail2.setRotationPoint(-1.0F, 0.1429F, 7.4393F);
    this.tail.addChild(this.tail2);
    setRotationAngle(this.tail2, 0.2793F, 0.0F, 0.0F);
    this.tail2.setTextureOffset(39, 57).addBox(0.25F, 2.2267F, -8.9439F, 2.0F, 5.0F, 2.0F, 0.01F, false);
    
    this.tail3 = new ModelRenderer((Model)this);
    this.tail3.setRotationPoint(0.0F, 4.4F, 0.15F);
    this.tail2.addChild(this.tail3);
    setRotationAngle(this.tail3, 0.6283F, 0.0F, 0.0F);
    this.tail3.setTextureOffset(39, 65).addBox(0.25F, -3.1F, -9.05F, 2.0F, 6.0F, 2.0F, 0.0F, false);
    
    this.tail4 = new ModelRenderer((Model)this);
    this.tail4.setRotationPoint(0.0F, 5.65F, 0.15F);
    this.tail3.addChild(this.tail4);
    setRotationAngle(this.tail4, 0.4014F, 0.0F, 0.0F);
    this.tail4.setTextureOffset(39, 74).addBox(0.25F, -6.1248F, -7.4173F, 2.0F, 6.0F, 2.0F, 0.01F, false);
    
    this.leftArm = new ModelRenderer((Model)this);
    this.leftArm.setRotationPoint(6.0F, -10.75F, 3.0F);
    setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.1745F);
    this.leftArm.setTextureOffset(46, 0).addBox(0.6299F, -2.2111F, -3.0126F, 6.0F, 8.0F, 6.0F, 0.0F, false);
    
    this.leftArm2 = new ModelRenderer((Model)this);
    this.leftArm2.setRotationPoint(4.9625F, -2.3633F, 3.35F);
    this.leftArm.addChild(this.leftArm2);
    setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.0436F);
    this.leftArm2.setTextureOffset(46, 15).addBox(-3.4768F, 6.7794F, -5.75F, 5.0F, 8.0F, 5.0F, 0.0F, false);
    
    this.leftArm3 = new ModelRenderer((Model)this);
    this.leftArm3.setRotationPoint(-0.6F, 6.8F, -0.35F);
    this.leftArm2.addChild(this.leftArm3);
    setRotationAngle(this.leftArm3, 0.0F, 0.0F, 0.0873F);
    this.leftArm3.setTextureOffset(46, 29).addBox(-1.8599F, 7.8421F, -4.75F, 4.0F, 5.0F, 4.0F, 0.0F, false);
    
    this.leftHand = new ModelRenderer((Model)this);
    this.leftHand.setRotationPoint(0.2901F, 14.4405F, -2.7616F);
    this.leftArm3.addChild(this.leftHand);
    setRotationAngle(this.leftHand, 0.0F, 0.0F, 0.0873F);
    this.leftHand.setTextureOffset(46, 39).addBox(-1.1526F, -2.1272F, -1.9884F, 2.0F, 4.0F, 4.0F, -0.01F, false);
    
    this.rightArm = new ModelRenderer((Model)this);
    this.rightArm.setRotationPoint(-7.75F, -11.0F, 3.0F);
    setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.1745F);
    this.rightArm.setTextureOffset(46, 0).addBox(-5.7124F, -2.154F, -3.0126F, 6.0F, 8.0F, 6.0F, 0.0F, true);
    
    this.rightArm2 = new ModelRenderer((Model)this);
    this.rightArm2.setRotationPoint(-4.245F, -2.3062F, 3.35F);
    this.rightArm.addChild(this.rightArm2);
    setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.0436F);
    this.rightArm2.setTextureOffset(46, 15).addBox(-1.3232F, 6.7794F, -5.75F, 5.0F, 8.0F, 5.0F, 0.0F, true);
    
    this.rightArm3 = new ModelRenderer((Model)this);
    this.rightArm3.setRotationPoint(-0.6F, 6.8F, -0.35F);
    this.rightArm2.addChild(this.rightArm3);
    setRotationAngle(this.rightArm3, 0.0F, 0.0F, -0.0873F);
    this.rightArm3.setTextureOffset(46, 29).addBox(-1.1401F, 7.8421F, -4.75F, 4.0F, 5.0F, 4.0F, 0.0F, true);
    
    this.rightHand = new ModelRenderer((Model)this);
    this.rightHand.setRotationPoint(1.2099F, 14.5921F, -2.75F);
    this.rightArm3.addChild(this.rightHand);
    setRotationAngle(this.rightHand, 0.0F, 0.0F, -0.0873F);
    this.rightHand.setTextureOffset(46, 39).addBox(-1.1149F, -2.0859F, -2.0F, 2.0F, 4.0F, 4.0F, -0.01F, true);
    
    this.leftLeg = new ModelRenderer((Model)this);
    this.leftLeg.setRotationPoint(3.5F, 5.6F, 2.75F);
    setRotationAngle(this.leftLeg, -0.2618F, 0.0F, 0.0F);
    this.leftLeg.setTextureOffset(72, 1).addBox(-2.0F, -1.1703F, -2.5342F, 4.0F, 9.0F, 4.0F, 0.0F, false);
    
    this.leftLeg2 = new ModelRenderer((Model)this);
    this.leftLeg2.setRotationPoint(-0.05F, -0.9862F, -0.6546F);
    this.leftLeg.addChild(this.leftLeg2);
    setRotationAngle(this.leftLeg2, 0.9446F, 0.0F, 0.0F);
    this.leftLeg2.setTextureOffset(71, 16).addBox(-1.45F, 3.5732F, -8.1449F, 3.0F, 7.0F, 3.0F, -0.01F, false);
    
    this.leftLeg3 = new ModelRenderer((Model)this);
    this.leftLeg3.setRotationPoint(-0.9F, 4.7F, 0.5F);
    this.leftLeg2.addChild(this.leftLeg3);
    setRotationAngle(this.leftLeg3, -1.1446F, 0.0F, 0.0F);
    this.leftLeg3.setTextureOffset(71, 28).addBox(-0.05F, 8.4997F, 0.6879F, 2.0F, 7.0F, 2.0F, 0.0F, false);
    
    this.leftFoot = new ModelRenderer((Model)this);
    this.leftFoot.setRotationPoint(0.75F, 5.95F, 0.55F);
    this.leftLeg3.addChild(this.leftFoot);
    setRotationAngle(this.leftFoot, 0.48F, 0.0F, 0.0F);
    this.leftFoot.setTextureOffset(66, 38).addBox(-1.3F, 8.3533F, -8.0493F, 3.0F, 2.0F, 6.0F, 0.0F, false);
    
    this.rightLeg = new ModelRenderer((Model)this);
    this.rightLeg.setRotationPoint(-3.5F, 5.6F, 2.75F);
    setRotationAngle(this.rightLeg, -0.2618F, 0.0F, 0.0F);
    this.rightLeg.setTextureOffset(72, 1).addBox(-2.0F, -1.1703F, -2.5342F, 4.0F, 9.0F, 4.0F, 0.0F, true);
    
    this.rightLeg2 = new ModelRenderer((Model)this);
    this.rightLeg2.setRotationPoint(0.7F, -0.9862F, -0.6546F);
    this.rightLeg.addChild(this.rightLeg2);
    setRotationAngle(this.rightLeg2, 0.9446F, 0.0F, 0.0F);
    this.rightLeg2.setTextureOffset(71, 16).addBox(-2.2F, 3.5732F, -8.1449F, 3.0F, 7.0F, 3.0F, -0.01F, true);
    
    this.rightLeg3 = new ModelRenderer((Model)this);
    this.rightLeg3.setRotationPoint(-0.4F, 4.7F, 0.5F);
    this.rightLeg2.addChild(this.rightLeg3);
    setRotationAngle(this.rightLeg3, -1.1446F, 0.0F, 0.0F);
    this.rightLeg3.setTextureOffset(71, 28).addBox(-1.3F, 8.4997F, 0.6879F, 2.0F, 7.0F, 2.0F, 0.0F, true);
    
    this.rightLeg4 = new ModelRenderer((Model)this);
    this.rightLeg4.setRotationPoint(0.25F, 5.95F, 0.55F);
    this.rightLeg3.addChild(this.rightLeg4);
    setRotationAngle(this.rightLeg4, 0.48F, 0.0F, 0.0F);
    this.rightLeg4.setTextureOffset(66, 38).addBox(-2.05F, 8.3533F, -8.0493F, 3.0F, 2.0F, 6.0F, 0.0F, true);
    
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
      
      this.head.rotateAngleX = headPitch * 0.010471975F;
      if (this.head.rotateAngleX > 0.6D) {
        this.head.rotateAngleX = 0.6F;
      }
    } 
    
    float f = 1.0F;
    this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
    this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6F) * 0.8F * limbSwingAmount * 0.5F / f;
    this.rightLeg.rotateAngleX = -0.3F + MathHelper.cos(limbSwing * 0.6F) * 0.7F * limbSwingAmount / f;
    this.leftLeg.rotateAngleX = -0.3F + MathHelper.cos(limbSwing * 0.6F + 3.1415927F) * 0.7F * limbSwingAmount / f;
    if (!entity.getHeldItemMainhand().isEmpty()) {
      this.rightArm.rotateAngleX += -0.15F;
    }
    if (!entity.isSprinting())
    {



      
      if (!(entity instanceof xyz.pixelatedw.mineminenomi.entities.DummyEntity)) {
        
        this.tail.rotateAngleY = (float)(this.tail.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 5.0D);
        this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + Math.sin(ageInTicks * 0.05D) / 5.0D);
      } 
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
        this.rightLeg.rotateAngleY += this.body2.rotateAngleY * 2.0F;
      }
      else {
        
        this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
        this.rightArm.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 5.0F;
        this.rightArm.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 5.0F;
        this.leftArm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
        this.leftArm.rotationPointX = MathHelper.cos(this.body.rotateAngleY) * 5.0F;
        this.rightArm.rotateAngleY += this.body.rotateAngleY;
        this.leftArm.rotateAngleY += this.body.rotateAngleY;
        this.leftArm.rotateAngleX += this.body.rotateAngleY;
        float f1 = 1.0F - this.swingProgress;
        f1 *= f1;
        f1 *= f1;
        f1 = 1.0F - f1;
        float f2 = MathHelper.sin(f1 * 3.1415927F);
        float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
        this.rightArm.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
        this.rightArm.rotateAngleY += this.body2.rotateAngleY * 2.0F;
        this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.9F;
      } 
    }

    
    if (entity.isSneaking()) {
      
      this.body.rotateAngleX = 0.5F;
      this.body.rotationPointZ -= 4.0F;
      this.rightArm.rotateAngleX += 0.4F;
      this.rightArm.rotationPointZ -= 2.5F;
      this.leftArm.rotateAngleX += 0.4F;
      this.leftArm.rotationPointZ -= 2.5F;
      this.rightLeg.rotationPointZ = 4.0F;
      this.leftLeg.rotationPointZ = 4.5F;
      this.rightLeg.rotationPointY = 3.5F;
      this.leftLeg.rotationPointY = 3.5F;
      this.head.rotationPointY = -10.0F;
      this.head.rotationPointZ = -8.0F;
    } 
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.head.render(matrixStack, buffer, packedLight, packedOverlay);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
  }


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    super.translateHand(side, matrixStack);
    matrixStack.translate(0.0D, 0.6D, 0.0D);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees((side == HandSide.RIGHT) ? -5.0F : 5.0F));
  }


  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(-0.2D, -0.6D, 0.2D);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
      matrixStack.rotate(Vector3f.ZP.rotationDegrees(10.0F));
      this.rightArm2.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    }
    else {
      
      matrixStack.translate(0.2D, -0.6D, 0.2D);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
      matrixStack.rotate(Vector3f.ZP.rotationDegrees(-10.0F));
      this.leftArm2.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    } 
  }


  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(0.1D, -0.4D, 0.2D);
      matrixStack.scale(1.5F, 1.5F, 1.5F);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-80.0F));
      this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    }
    else {
      
      matrixStack.translate(-0.1D, -0.4D, 0.2D);
      matrixStack.scale(1.5F, 1.5F, 1.5F);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(80.0F));
      this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    } 
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


