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

public class PteranodonFlyModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer head;
  private final ModelRenderer head7_r1;
  private final ModelRenderer head6_r1;
  private final ModelRenderer head5_r1;
  private final ModelRenderer head4_r1;
  private final ModelRenderer head3_r1;
  private final ModelRenderer head2_r1;
  private final ModelRenderer neck;
  private final ModelRenderer neck_r1;
  private final ModelRenderer beck;
  private final ModelRenderer upperBeck;
  public final ModelRenderer lowerBeck;
  private final ModelRenderer body;
  private final ModelRenderer body3_r1;
  private final ModelRenderer rightWing;
  private final ModelRenderer leftWing;
  private final ModelRenderer rightLeg;
  private final ModelRenderer rightLeg5_r1;
  private final ModelRenderer rightLeg1_r1;
  private final ModelRenderer leftLeg;
  private final ModelRenderer leftLeg5_r1;
  private final ModelRenderer leftLeg1_r1;
  
  public PteranodonFlyModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(-0.5F, 17.0F, -2.0F);
    this.head.setTextureOffset(17, 37).addBox(-1.5F, -4.0F, -17.0F, 3.0F, 4.0F, 7.0F, 0.0F, false);
    
    this.head7_r1 = new ModelRenderer((Model)this);
    this.head7_r1.setRotationPoint(0.0F, -3.6703F, -11.8864F);
    this.head.addChild(this.head7_r1);
    setRotationAngle(this.head7_r1, 1.0036F, 0.0F, 0.0F);
    this.head7_r1.setTextureOffset(8, 10).addBox(-0.5F, 1.1F, 7.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.head6_r1 = new ModelRenderer((Model)this);
    this.head6_r1.setRotationPoint(0.0F, -3.9663F, -13.3072F);
    this.head.addChild(this.head6_r1);
    setRotationAngle(this.head6_r1, 0.829F, 0.0F, 0.0F);
    this.head6_r1.setTextureOffset(8, 14).addBox(-0.5F, 1.1F, 6.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.head5_r1 = new ModelRenderer((Model)this);
    this.head5_r1.setRotationPoint(0.0F, -3.1147F, -15.0223F);
    this.head.addChild(this.head5_r1);
    setRotationAngle(this.head5_r1, 0.7418F, 0.0F, 0.0F);
    this.head5_r1.setTextureOffset(15, 10).addBox(-0.5F, 1.1F, 6.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.head4_r1 = new ModelRenderer((Model)this);
    this.head4_r1.setRotationPoint(0.0F, -3.372F, -15.126F);
    this.head.addChild(this.head4_r1);
    setRotationAngle(this.head4_r1, 0.6981F, 0.0F, 0.0F);
    this.head4_r1.setTextureOffset(15, 14).addBox(-0.5F, 1.1F, 4.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.head3_r1 = new ModelRenderer((Model)this);
    this.head3_r1.setRotationPoint(0.0F, -3.9118F, -11.6183F);
    this.head.addChild(this.head3_r1);
    setRotationAngle(this.head3_r1, 0.4363F, 0.0F, 0.0F);
    this.head3_r1.setTextureOffset(22, 10).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.head2_r1 = new ModelRenderer((Model)this);
    this.head2_r1.setRotationPoint(0.0F, -3.7133F, -13.62F);
    this.head.addChild(this.head2_r1);
    setRotationAngle(this.head2_r1, 0.2618F, 0.0F, 0.0F);
    this.head2_r1.setTextureOffset(22, 15).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.neck = new ModelRenderer((Model)this);
    this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.head.addChild(this.neck);

    
    this.neck_r1 = new ModelRenderer((Model)this);
    this.neck_r1.setRotationPoint(0.0F, -0.9169F, -9.6038F);
    this.neck.addChild(this.neck_r1);
    setRotationAngle(this.neck_r1, -0.1309F, 0.0F, 0.0F);
    this.neck_r1.setTextureOffset(31, 30).addBox(-0.5F, -1.5F, -4.5F, 1.0F, 3.0F, 9.0F, 0.0F, false);
    
    this.beck = new ModelRenderer((Model)this);
    this.beck.setRotationPoint(0.0F, -1.0F, -10.0F);
    this.head.addChild(this.beck);
    this.beck.setTextureOffset(0, 0).addBox(-1.0F, -2.5F, -9.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
    this.beck.setTextureOffset(35, 10).addBox(-1.0F, 0.0F, -9.0F, 2.0F, 1.0F, 2.0F, 0.01F, false);
    
    this.upperBeck = new ModelRenderer((Model)this);
    this.upperBeck.setRotationPoint(0.0F, 0.0F, -2.0F);
    this.beck.addChild(this.upperBeck);
    this.upperBeck.setTextureOffset(0, 18).addBox(-0.5F, -2.0F, -18.0F, 1.0F, 2.0F, 12.0F, 0.01F, false);
    
    this.lowerBeck = new ModelRenderer((Model)this);
    this.lowerBeck.setRotationPoint(0.0F, 0.0F, -9.0F);
    this.beck.addChild(this.lowerBeck);
    this.lowerBeck.setTextureOffset(14, 20).addBox(-0.5F, 0.0F, -11.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(-0.5F, 16.0F, 4.0F);
    this.body.setTextureOffset(28, 18).addBox(-3.0F, -1.5F, -12.0F, 6.0F, 5.0F, 7.0F, 0.0F, false);
    
    this.body3_r1 = new ModelRenderer((Model)this);
    this.body3_r1.setRotationPoint(0.5F, 40.0F, -20.0F);
    this.body.addChild(this.body3_r1);
    setRotationAngle(this.body3_r1, -0.0873F, 0.0F, 0.0F);
    this.body3_r1.setTextureOffset(46, 44).addBox(-2.0F, -42.0F, 18.1F, 3.0F, 2.0F, 1.0F, 0.0F, false);
    this.body3_r1.setTextureOffset(0, 33).addBox(-3.0F, -42.4F, 11.1F, 5.0F, 4.0F, 7.0F, 0.0F, false);
    
    this.rightWing = new ModelRenderer((Model)this);
    this.rightWing.setRotationPoint(-3.0F, 17.0F, 0.0F);
    this.rightWing.setTextureOffset(17, 33).addBox(-8.5F, -0.5F, -6.0F, 8.0F, 1.0F, 1.0F, 0.0F, true);
    this.rightWing.setTextureOffset(14, 24).addBox(-5.5F, -0.5F, -7.0F, 5.0F, 1.0F, 1.0F, 0.0F, true);
    this.rightWing.setTextureOffset(17, 33).addBox(-13.5F, -0.5F, -5.0F, 8.0F, 1.0F, 1.0F, 0.0F, true);
    this.rightWing.setTextureOffset(0, 27).addBox(-18.5F, -0.5F, -3.0F, 4.0F, 1.0F, 1.0F, 0.0F, true);
    this.rightWing.setTextureOffset(0, 44).addBox(-17.5F, -0.5F, -4.0F, 7.0F, 1.0F, 1.0F, 0.0F, true);
    this.rightWing.setTextureOffset(7, 20).addBox(-18.5F, -0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
    this.rightWing.setTextureOffset(0, 0).addBox(-18.5F, 0.0F, -5.0F, 19.0F, 0.0F, 9.0F, 0.0F, true);
    
    this.leftWing = new ModelRenderer((Model)this);
    this.leftWing.setRotationPoint(2.0F, 17.0F, 0.0F);
    this.leftWing.setTextureOffset(17, 33).addBox(0.5F, -0.5F, -6.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
    this.leftWing.setTextureOffset(14, 24).addBox(0.5F, -0.5F, -7.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
    this.leftWing.setTextureOffset(17, 33).addBox(5.5F, -0.5F, -5.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
    this.leftWing.setTextureOffset(0, 27).addBox(14.5F, -0.5F, -3.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
    this.leftWing.setTextureOffset(0, 44).addBox(10.5F, -0.5F, -4.0F, 7.0F, 1.0F, 1.0F, 0.0F, false);
    this.leftWing.setTextureOffset(7, 20).addBox(17.5F, -0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.leftWing.setTextureOffset(0, 0).addBox(-0.5F, 0.0F, -5.0F, 19.0F, 0.0F, 9.0F, 0.0F, false);
    
    this.rightLeg = new ModelRenderer((Model)this);
    this.rightLeg.setRotationPoint(-3.0F, 18.0F, 11.0F);
    setRotationAngle(this.rightLeg, 1.3526F, 0.0F, 0.0F);

    
    this.rightLeg5_r1 = new ModelRenderer((Model)this);
    this.rightLeg5_r1.setRotationPoint(5.0F, 12.1847F, 7.6754F);
    this.rightLeg.addChild(this.rightLeg5_r1);
    setRotationAngle(this.rightLeg5_r1, -1.0472F, 0.0F, 0.0F);
    this.rightLeg5_r1.setTextureOffset(14, 20).addBox(-7.5F, 4.6958F, -15.7354F, 5.0F, 4.0F, 0.0F, 0.0F, false);
    this.rightLeg5_r1.setTextureOffset(0, 5).addBox(-6.5F, 3.93F, -15.7223F, 3.0F, 3.0F, 1.0F, 0.0F, false);
    this.rightLeg5_r1.setTextureOffset(0, 13).addBox(-5.5F, 0.6958F, -15.7223F, 1.0F, 4.0F, 1.0F, -0.01F, false);
    this.rightLeg5_r1.setTextureOffset(0, 13).addBox(-5.5F, 0.6958F, -20.7223F, 1.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.rightLeg1_r1 = new ModelRenderer((Model)this);
    this.rightLeg1_r1.setRotationPoint(5.0F, 13.8689F, 7.5424F);
    this.rightLeg.addChild(this.rightLeg1_r1);
    setRotationAngle(this.rightLeg1_r1, -2.2253F, 0.0F, 0.0F);
    this.rightLeg1_r1.setTextureOffset(42, 30).addBox(-6.0F, 18.0963F, -11.8567F, 2.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.leftLeg = new ModelRenderer((Model)this);
    this.leftLeg.setRotationPoint(2.0F, 18.0F, 11.0F);
    setRotationAngle(this.leftLeg, 1.3526F, 0.0F, 0.0F);

    
    this.leftLeg5_r1 = new ModelRenderer((Model)this);
    this.leftLeg5_r1.setRotationPoint(5.0F, 12.1847F, 7.6754F);
    this.leftLeg.addChild(this.leftLeg5_r1);
    setRotationAngle(this.leftLeg5_r1, -1.0472F, 0.0F, 0.0F);
    this.leftLeg5_r1.setTextureOffset(14, 20).addBox(-7.5F, 4.6958F, -15.7354F, 5.0F, 4.0F, 0.0F, 0.0F, false);
    this.leftLeg5_r1.setTextureOffset(0, 5).addBox(-6.5F, 3.93F, -15.7223F, 3.0F, 3.0F, 1.0F, 0.0F, false);
    this.leftLeg5_r1.setTextureOffset(0, 13).addBox(-5.5F, 0.6958F, -15.7223F, 1.0F, 4.0F, 1.0F, -0.01F, false);
    this.leftLeg5_r1.setTextureOffset(0, 13).addBox(-5.5F, 0.6958F, -20.7223F, 1.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.leftLeg1_r1 = new ModelRenderer((Model)this);
    this.leftLeg1_r1.setRotationPoint(5.0F, 13.8689F, 7.5424F);
    this.leftLeg.addChild(this.leftLeg1_r1);
    setRotationAngle(this.leftLeg1_r1, -2.2253F, 0.0F, 0.0F);
    this.leftLeg1_r1.setTextureOffset(42, 30).addBox(-6.0F, 18.0963F, -11.8567F, 2.0F, 3.0F, 5.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.head.render(matrixStack, buffer, packedLight, packedOverlay);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
    matrixStack.push();
    matrixStack.scale(1.75F, 1.0F, 1.0F);
    matrixStack.translate(0.10000000149011612D, 0.0D, 0.0D);
    this.rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
    matrixStack.translate(-0.20000000298023224D, 0.0D, 0.0D);
    this.leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
    matrixStack.pop();
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    if ((entity.getMotion()).y < -1.7D) {
      
      this.leftWing.rotateAngleX = (float)(this.leftWing.rotateAngleX + Math.toRadians(-90.0D));
      this.leftWing.rotateAngleY = (float)(this.leftWing.rotateAngleY + Math.toRadians(-85.0D));
      this.leftWing.rotateAngleY += MathHelper.cos(((LivingEntity)entity).ticksExisted * 0.9F) / 50.0F;
      this.leftWing.rotateAngleZ += MathHelper.cos(((LivingEntity)entity).ticksExisted * 2.9F) / 50.0F;
      
      this.rightWing.rotateAngleX = (float)(this.rightWing.rotateAngleX + Math.toRadians(-90.0D));
      this.rightWing.rotateAngleY = (float)(this.rightWing.rotateAngleY + Math.toRadians(85.0D));
      this.rightWing.rotateAngleY += MathHelper.cos(((LivingEntity)entity).ticksExisted * 0.9F) / 50.0F;
      this.rightWing.rotateAngleZ += MathHelper.cos(((LivingEntity)entity).ticksExisted * 2.9F) / 50.0F;
    
    }
    else {
      
      this.leftWing.rotateAngleZ = MathHelper.cos(ageInTicks * 0.4F) * 0.7F;
      this.rightWing.rotateAngleZ = MathHelper.cos(ageInTicks * 0.4F + 3.1415927F) * 0.7F;
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
      this.head.rotateAngleX = (float)(this.head.rotateAngleX - f2 * 0.5D + f3);
      this.head.rotateAngleZ -= MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
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
    matrixStack.scale(0.5F, 0.5F, 0.5F);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
    matrixStack.rotate(Vector3f.XP.rotationDegrees(260.0F));
    matrixStack.translate(0.35D, -0.1D, -0.1D);
  }
}


