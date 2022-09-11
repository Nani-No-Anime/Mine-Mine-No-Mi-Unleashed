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

public class AxolotlWalkModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer head;
  private final ModelRenderer top_gills;
  private final ModelRenderer left_gills;
  private final ModelRenderer right_gills;
  private final ModelRenderer body;
  private final ModelRenderer leg4;
  private final ModelRenderer leg2;
  private final ModelRenderer leg3;
  private final ModelRenderer leg1;
  private final ModelRenderer tail;
  
  public AxolotlWalkModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(0.0F, 18.0F, -5.0F);
    this.head.setTextureOffset(0, 1).addBox(-4.0F, -3.0F, -5.0F, 8.0F, 5.0F, 5.0F, 0.0F, false);
    
    this.top_gills = new ModelRenderer((Model)this);
    this.top_gills.setRotationPoint(0.0F, -3.0F, -1.0F);
    this.head.addChild(this.top_gills);
    this.top_gills.setTextureOffset(3, 37).addBox(-4.0F, -3.0F, 0.0F, 8.0F, 3.0F, 0.0F, 0.0F, false);
    
    this.left_gills = new ModelRenderer((Model)this);
    this.left_gills.setRotationPoint(4.0F, 0.0F, -1.0F);
    this.head.addChild(this.left_gills);
    this.left_gills.setTextureOffset(0, 40).addBox(-11.0F, -5.0F, 0.0F, 3.0F, 7.0F, 0.0F, 0.0F, false);
    
    this.right_gills = new ModelRenderer((Model)this);
    this.right_gills.setRotationPoint(-4.0F, 0.0F, -1.0F);
    this.head.addChild(this.right_gills);
    this.right_gills.setTextureOffset(11, 40).addBox(8.0F, -5.0F, 0.0F, 3.0F, 7.0F, 0.0F, 0.0F, false);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.0F, 18.0F, 4.0F);
    this.body.setTextureOffset(0, 11).addBox(-4.0F, -2.0F, -9.0F, 8.0F, 4.0F, 10.0F, 0.0F, false);
    this.body.setTextureOffset(2, 17).addBox(0.0F, -3.0F, -8.0F, 0.0F, 5.0F, 9.0F, 0.0F, false);
    
    this.leg4 = new ModelRenderer((Model)this);
    this.leg4.setRotationPoint(-3.5F, 19.0F, -4.0F);
    this.leg4.setTextureOffset(2, 13).addBox(6.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);
    
    this.leg2 = new ModelRenderer((Model)this);
    this.leg2.setRotationPoint(-3.5F, 19.0F, 3.0F);
    this.leg2.setTextureOffset(2, 13).addBox(6.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);
    
    this.leg3 = new ModelRenderer((Model)this);
    this.leg3.setRotationPoint(3.5F, 19.0F, -4.0F);
    this.leg3.setTextureOffset(2, 13).addBox(-9.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);
    
    this.leg1 = new ModelRenderer((Model)this);
    this.leg1.setRotationPoint(3.5F, 19.0F, 3.0F);
    this.leg1.setTextureOffset(2, 13).addBox(-9.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(0.0F, 18.0F, 5.0F);
    this.tail.setTextureOffset(2, 19).addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 12.0F, 0.0F, false);
    
    this.bipedBody = this.body;
    this.bipedHead = this.head;
    this.bipedRightArm = this.leg1;
    this.bipedLeftArm = this.leg2;
    this.bipedRightLeg = this.leg3;
    this.bipedLeftLeg = this.leg4;
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
    this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
    this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
    this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
    this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
    
    this.tail.rotateAngleY = (float)(this.tail.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 10.0D);
    this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);

    
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
      this.head.rotateAngleZ = MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
    } 
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.head.render(matrixStack, buffer, packedLight, packedOverlay);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leg4.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leg2.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leg3.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leg1.render(matrixStack, buffer, packedLight, packedOverlay);
    this.tail.render(matrixStack, buffer, packedLight, packedOverlay);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    this.head.translateRotate(matrixStack);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
    matrixStack.rotate(Vector3f.XP.rotationDegrees(260.0F));
    matrixStack.translate(0.1D, -0.2D, -0.03D);
  }
  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
}


