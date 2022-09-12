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

public class BisonWalkModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer head;
  private final ModelRenderer snout;
  private final ModelRenderer rightHorn;
  private final ModelRenderer rightHorn2;
  private final ModelRenderer leftHorn;
  private final ModelRenderer leftHorn2;
  private final ModelRenderer body;
  private final ModelRenderer hunch;
  private final ModelRenderer lowerBack;
  private final ModelRenderer tail;
  private final ModelRenderer leftFrontLeg;
  private final ModelRenderer leftFrontLeg2;
  private final ModelRenderer leftFrontNail1;
  private final ModelRenderer leftFrontNail2;
  private final ModelRenderer rightFrontLeg;
  private final ModelRenderer rightFrontLeg2;
  private final ModelRenderer rightFrontNail1;
  private final ModelRenderer rightFrontNail2;
  private final ModelRenderer leftRearLeg;
  private final ModelRenderer leftRearNail1;
  private final ModelRenderer leftRearNail2;
  private final ModelRenderer rightRearLeg;
  private final ModelRenderer rightRearNail1;
  private final ModelRenderer rightRearNail2;
  
  public BisonWalkModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 64;
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(-2.5F, 8.0F, -8.5F);
    setRotationAngle(this.head, 0.0873F, 0.0F, 0.0F);
    this.head.setTextureOffset(13, 29).addBox(0.0F, 0.3861F, -7.0606F, 5.0F, 5.0F, 5.0F, 0.0F, false);
    
    this.snout = new ModelRenderer((Model)this);
    this.snout.setRotationPoint(0.5F, 2.0F, -2.5F);
    this.head.addChild(this.snout);
    setRotationAngle(this.snout, 0.0873F, 0.0F, 0.0F);
    this.snout.setTextureOffset(13, 40).addBox(0.0F, -0.2307F, -7.0674F, 4.0F, 3.0F, 3.0F, 0.0F, false);
    
    this.rightHorn = new ModelRenderer((Model)this);
    this.rightHorn.setRotationPoint(-0.5F, -1.0F, 3.5F);
    this.head.addChild(this.rightHorn);
    setRotationAngle(this.rightHorn, 0.0F, 0.0F, 0.733F);
    this.rightHorn.setTextureOffset(115, 0).addBox(0.2584F, 0.2869F, -7.0606F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightHorn2 = new ModelRenderer((Model)this);
    this.rightHorn2.setRotationPoint(0.3809F, -1.1029F, 0.0436F);
    this.rightHorn.addChild(this.rightHorn2);
    setRotationAngle(this.rightHorn2, 0.0F, 0.0F, 1.3352F);
    this.rightHorn2.setTextureOffset(122, 0).addBox(0.2584F, -0.3313F, -7.1041F, 2.0F, 1.0F, 1.0F, 0.01F, false);
    
    this.leftHorn = new ModelRenderer((Model)this);
    this.leftHorn.setRotationPoint(5.5F, -0.751F, 3.4782F);
    this.head.addChild(this.leftHorn);
    setRotationAngle(this.leftHorn, 0.0F, 0.0F, -0.733F);
    this.leftHorn.setTextureOffset(115, 0).addBox(-2.0911F, 0.1011F, -7.0606F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftHorn2 = new ModelRenderer((Model)this);
    this.leftHorn2.setRotationPoint(0.3457F, -4.1516F, 0.0F);
    this.leftHorn.addChild(this.leftHorn2);
    setRotationAngle(this.leftHorn2, 0.0F, 0.0F, -1.3352F);
    this.leftHorn2.setTextureOffset(122, 0).addBox(-5.1393F, -0.1842F, -7.0606F, 2.0F, 1.0F, 1.0F, 0.01F, false);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(-4.5F, 7.0F, -3.5F);
    this.body.setTextureOffset(0, 0).addBox(0.0F, 1.0F, -7.0F, 9.0F, 7.0F, 11.0F, 0.0F, false);
    
    this.hunch = new ModelRenderer((Model)this);
    this.hunch.setRotationPoint(0.5F, -5.0F, 3.5F);
    this.body.addChild(this.hunch);
    setRotationAngle(this.hunch, -0.5934F, 0.0F, 0.0F);
    this.hunch.setTextureOffset(41, 0).addBox(0.0F, 4.7434F, -5.2441F, 8.0F, 6.0F, 9.0F, 0.0F, false);
    
    this.lowerBack = new ModelRenderer((Model)this);
    this.lowerBack.setRotationPoint(0.5F, 0.0F, 10.5F);
    this.body.addChild(this.lowerBack);
    this.lowerBack.setTextureOffset(76, 0).addBox(0.0F, 1.0F, -7.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(4.0F, 4.0F, 0.5F);
    this.lowerBack.addChild(this.tail);
    setRotationAngle(this.tail, 0.3491F, 0.0F, 0.0F);
    this.tail.setTextureOffset(110, 0).addBox(-0.5F, 3.0E-4F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    
    this.leftFrontLeg = new ModelRenderer((Model)this);
    this.leftFrontLeg.setRotationPoint(2.75F, 14.0F, -7.0F);
    this.leftFrontLeg.setTextureOffset(0, 29).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.leftFrontLeg2 = new ModelRenderer((Model)this);
    this.leftFrontLeg2.setRotationPoint(0.0F, 6.0F, 0.0F);
    this.leftFrontLeg.addChild(this.leftFrontLeg2);
    this.leftFrontLeg2.setTextureOffset(0, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
    
    this.leftFrontNail1 = new ModelRenderer((Model)this);
    this.leftFrontNail1.setRotationPoint(0.0F, 3.1F, 6.5F);
    this.leftFrontLeg2.addChild(this.leftFrontNail1);
    setRotationAngle(this.leftFrontNail1, -0.1211F, -0.4883F, -0.0394F);
    this.leftFrontNail1.setTextureOffset(0, 55).addBox(-3.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftFrontNail2 = new ModelRenderer((Model)this);
    this.leftFrontNail2.setRotationPoint(0.0F, 3.1F, 6.5F);
    this.leftFrontLeg2.addChild(this.leftFrontNail2);
    setRotationAngle(this.leftFrontNail2, -0.1211F, 0.4883F, 0.0394F);
    this.leftFrontNail2.setTextureOffset(0, 55).addBox(2.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightFrontLeg = new ModelRenderer((Model)this);
    this.rightFrontLeg.setRotationPoint(-2.75F, 14.0F, -7.0F);
    this.rightFrontLeg.setTextureOffset(0, 29).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    
    this.rightFrontLeg2 = new ModelRenderer((Model)this);
    this.rightFrontLeg2.setRotationPoint(0.0F, 6.0F, 0.0F);
    this.rightFrontLeg.addChild(this.rightFrontLeg2);
    this.rightFrontLeg2.setTextureOffset(0, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
    
    this.rightFrontNail1 = new ModelRenderer((Model)this);
    this.rightFrontNail1.setRotationPoint(0.0F, 3.1F, 6.5F);
    this.rightFrontLeg2.addChild(this.rightFrontNail1);
    setRotationAngle(this.rightFrontNail1, -0.1211F, -0.4883F, -0.0394F);
    this.rightFrontNail1.setTextureOffset(0, 55).addBox(-3.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightFrontNail2 = new ModelRenderer((Model)this);
    this.rightFrontNail2.setRotationPoint(0.0F, 3.1F, 6.5F);
    this.rightFrontLeg2.addChild(this.rightFrontNail2);
    setRotationAngle(this.rightFrontNail2, -0.1211F, 0.4883F, 0.0394F);
    this.rightFrontNail2.setTextureOffset(0, 55).addBox(2.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftRearLeg = new ModelRenderer((Model)this);
    this.leftRearLeg.setRotationPoint(3.0F, 14.0F, 7.0F);
    this.leftRearLeg.setTextureOffset(0, 39).addBox(-1.25F, 0.0F, -1.25F, 2.0F, 10.0F, 2.0F, 0.0F, false);
    
    this.leftRearNail1 = new ModelRenderer((Model)this);
    this.leftRearNail1.setRotationPoint(0.0F, 9.1F, 6.5F);
    this.leftRearLeg.addChild(this.leftRearNail1);
    setRotationAngle(this.leftRearNail1, -0.1211F, 0.4883F, 0.0394F);
    this.leftRearNail1.setTextureOffset(0, 52).addBox(2.6808F, 0.7975F, -7.4708F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftRearNail2 = new ModelRenderer((Model)this);
    this.leftRearNail2.setRotationPoint(0.0F, 9.1F, 6.5F);
    this.leftRearLeg.addChild(this.leftRearNail2);
    setRotationAngle(this.leftRearNail2, -0.1211F, -0.4883F, -0.0394F);
    this.leftRearNail2.setTextureOffset(0, 52).addBox(-4.122F, 0.7496F, -7.2405F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightRearLeg = new ModelRenderer((Model)this);
    this.rightRearLeg.setRotationPoint(-2.75F, 14.0F, 6.75F);
    this.rightRearLeg.setTextureOffset(0, 39).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
    
    this.rightRearNail1 = new ModelRenderer((Model)this);
    this.rightRearNail1.setRotationPoint(0.0F, 9.1F, 6.5F);
    this.rightRearLeg.addChild(this.rightRearNail1);
    setRotationAngle(this.rightRearNail1, -0.1211F, 0.4883F, 0.0394F);
    this.rightRearNail1.setTextureOffset(0, 52).addBox(2.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.rightRearNail2 = new ModelRenderer((Model)this);
    this.rightRearNail2.setRotationPoint(0.0F, 9.1F, 6.5F);
    this.rightRearLeg.addChild(this.rightRearNail2);
    setRotationAngle(this.rightRearNail2, -0.1211F, -0.4883F, -0.0394F);
    this.rightRearNail2.setTextureOffset(0, 52).addBox(-3.7841F, 0.7469F, -7.1365F, 1.0F, 1.0F, 1.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftRearLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightRearLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.rightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.9F) * 0.5F * limbSwingAmount;
    this.leftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.9F) * 0.5F * limbSwingAmount;
    this.rightRearLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.9F + 3.1415927F) * 0.5F * limbSwingAmount;
    this.leftRearLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.9F + 3.1415927F) * 0.5F * limbSwingAmount;
    if (entity.isSprinting()) {
      this.tail.rotateAngleX = 1.2F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
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


