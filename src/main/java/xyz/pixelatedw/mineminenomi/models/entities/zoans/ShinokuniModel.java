package xyz.pixelatedw.mineminenomi.models.entities.zoans;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;

public class ShinokuniModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer body;
  private final ModelRenderer neck;
  private final ModelRenderer leftArm;
  private final ModelRenderer leftArm2;
  private final ModelRenderer rightArm;
  private final ModelRenderer rightArm2;
  
  public ShinokuniModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 128;
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(1.5F, 17.0F, -2.0F);
    this.body.setTextureOffset(46, 0).addBox(-7.0F, -6.0F, -1.0F, 11.0F, 3.0F, 6.0F, 0.0F, false);
    this.body.setTextureOffset(42, 27).addBox(-8.0F, -11.0F, -2.0F, 13.0F, 5.0F, 8.0F, 0.0F, false);
    this.body.setTextureOffset(0, 16).addBox(-9.0F, -19.0F, -3.0F, 15.0F, 9.0F, 10.0F, 0.0F, false);
    this.body.setTextureOffset(40, 16).addBox(-8.0F, -22.0F, -0.5F, 13.0F, 3.0F, 7.0F, 0.0F, false);
    this.body.setTextureOffset(52, 51).addBox(-7.5F, -21.0F, -2.5F, 12.0F, 2.0F, 2.0F, 0.0F, false);
    this.body.setTextureOffset(42, 40).addBox(-8.0F, -3.0F, -2.0F, 13.0F, 3.0F, 8.0F, 0.0F, false);
    this.body.setTextureOffset(0, 35).addBox(-9.0F, 0.0F, -3.0F, 15.0F, 3.0F, 10.0F, 0.0F, false);
    this.body.setTextureOffset(0, 0).addBox(-10.0F, 3.0F, -4.0F, 17.0F, 4.0F, 12.0F, 0.0F, false);
    
    this.neck = new ModelRenderer((Model)this);
    this.neck.setRotationPoint(-1.0F, -19.0F, 3.0F);
    this.body.addChild(this.neck);
    setRotationAngle(this.neck, 0.48F, 0.0F, 0.0F);
    this.neck.setTextureOffset(36, 74).addBox(-2.5F, -5.0F, -5.5F, 4.0F, 5.0F, 4.0F, 0.0F, false);
    
    this.leftArm = new ModelRenderer((Model)this);
    this.leftArm.setRotationPoint(7.0F, -1.0F, 1.0F);
    setRotationAngle(this.leftArm, 0.0F, 0.0F, 1.2217F);
    this.leftArm.setTextureOffset(0, 48).addBox(-0.4739F, -4.8191F, -5.0F, 7.0F, 7.0F, 8.0F, 0.0F, true);
    
    this.leftArm2 = new ModelRenderer((Model)this);
    this.leftArm2.setRotationPoint(12.3118F, -1.3905F, 0.0F);
    this.leftArm.addChild(this.leftArm2);
    this.leftArm2.setTextureOffset(0, 69).addBox(-5.7857F, -2.4286F, -4.0F, 9.0F, 5.0F, 1.0F, 0.0F, true);
    this.leftArm2.setTextureOffset(0, 80).addBox(-5.7857F, -2.4286F, 1.0F, 11.0F, 4.0F, 1.0F, 0.0F, true);
    this.leftArm2.setTextureOffset(0, 86).addBox(-5.7857F, 1.5714F, -3.0F, 9.0F, 1.0F, 2.0F, 0.0F, true);
    this.leftArm2.setTextureOffset(0, 66).addBox(-5.7857F, 1.5714F, -1.0F, 10.0F, 1.0F, 2.0F, 0.0F, true);
    this.leftArm2.setTextureOffset(0, 63).addBox(-5.7857F, -2.4286F, -3.0F, 10.0F, 1.0F, 2.0F, 0.0F, true);
    this.leftArm2.setTextureOffset(0, 76).addBox(-5.7857F, -2.4286F, -1.0F, 11.0F, 1.0F, 2.0F, 0.0F, true);
    this.leftArm2.setTextureOffset(0, 90).addBox(0.2143F, -1.4286F, -2.5F, 7.0F, 3.0F, 3.0F, 0.0F, true);
    
    this.rightArm = new ModelRenderer((Model)this);
    this.rightArm.setRotationPoint(-7.0F, -1.0F, 1.0F);
    setRotationAngle(this.rightArm, 0.0F, 0.0F, -1.2217F);
    this.rightArm.setTextureOffset(0, 48).addBox(-6.5261F, -4.8191F, -5.0F, 7.0F, 7.0F, 8.0F, 0.0F, false);
    
    this.rightArm2 = new ModelRenderer((Model)this);
    this.rightArm2.setRotationPoint(-12.3118F, -1.3905F, 0.0F);
    this.rightArm.addChild(this.rightArm2);
    this.rightArm2.setTextureOffset(0, 69).addBox(-3.2143F, -2.4286F, -4.0F, 9.0F, 5.0F, 1.0F, 0.0F, false);
    this.rightArm2.setTextureOffset(0, 80).addBox(-5.2143F, -2.4286F, 1.0F, 11.0F, 4.0F, 1.0F, 0.0F, false);
    this.rightArm2.setTextureOffset(0, 86).addBox(-3.2143F, 1.5714F, -3.0F, 9.0F, 1.0F, 2.0F, 0.0F, false);
    this.rightArm2.setTextureOffset(0, 66).addBox(-4.2143F, 1.5714F, -1.0F, 10.0F, 1.0F, 2.0F, 0.0F, false);
    this.rightArm2.setTextureOffset(0, 63).addBox(-4.2143F, -2.4286F, -3.0F, 10.0F, 1.0F, 2.0F, 0.0F, false);
    this.rightArm2.setTextureOffset(0, 76).addBox(-5.2143F, -2.4286F, -1.0F, 11.0F, 1.0F, 2.0F, 0.0F, false);
    this.rightArm2.setTextureOffset(0, 90).addBox(-7.2143F, -1.4286F, -2.5F, 7.0F, 3.0F, 3.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.body.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
    this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    float f = 1.0F;
    this.rightArm.rotateAngleY = MathHelper.cos(limbSwing * 0.6F + 3.1415927F) * 0.5F * limbSwingAmount * 0.5F / f;
    this.leftArm.rotateAngleY = -MathHelper.cos(limbSwing * 0.6F) * 0.5F * limbSwingAmount * 0.5F / f;
    if (!entity.getHeldItemMainhand().isEmpty()) {
      this.rightArm.rotateAngleX += -0.15F;
    }
    
    this.swingProgress = ((LivingEntity)entity).swingProgress;
    if (this.swingProgress > 0.0F) {
      
      this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
      this.rightArm.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 5.0F;
      this.rightArm.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 8.0F;
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
      float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * 0.75F;
      this.rightArm.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
      this.rightArm.rotateAngleY += this.body.rotateAngleY * 5.0F;
      this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.9F;
    } 
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }


  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(0.2D, 0.3D, 0.0D);
      this.rightArm.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
    else {
      
      matrixStack.translate(-0.2D, 0.3D, 0.0D);
      this.leftArm.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    } 
  }



  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    super.translateHand(side, matrixStack);
    matrixStack.translate((side == HandSide.RIGHT) ? -0.6D : 0.6D, -0.5D, -0.2D);
  }
}


