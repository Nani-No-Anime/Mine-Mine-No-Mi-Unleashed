package xyz.pixelatedw.mineminenomi.models.entities.zoans.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;

public class BaraCarWheelsPartialModel<T extends LivingEntity>
  extends ZoanMorphModel<T> {
  private final ModelRenderer frontWheels;
  private final ModelRenderer frontRightWheel;
  private final ModelRenderer frontLeftWheel;
  private final ModelRenderer backWheels;
  private final ModelRenderer backLeftWheel;
  private final ModelRenderer backRightWheel;
  
  public BaraCarWheelsPartialModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 128;
    
    this.frontWheels = new ModelRenderer((Model)this);
    this.frontWheels.setRotationPoint(0.0F, 23.0F, -16.0F);
    this.frontWheels.setTextureOffset(27, 12).addBox(-13.0F, -1.0F, -1.0F, 26.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.frontRightWheel = new ModelRenderer((Model)this);
    this.frontRightWheel.setRotationPoint(-13.0F, 0.0F, 0.0F);
    this.frontWheels.addChild(this.frontRightWheel);
    this.frontRightWheel.setTextureOffset(0, 42).addBox(-3.0F, -4.0F, -4.0F, 3.0F, 8.0F, 8.0F, 0.0F, false);
    
    this.frontLeftWheel = new ModelRenderer((Model)this);
    this.frontLeftWheel.setRotationPoint(13.0F, 0.0F, 0.0F);
    this.frontWheels.addChild(this.frontLeftWheel);
    this.frontLeftWheel.setTextureOffset(0, 42).addBox(0.0F, -4.0F, -4.0F, 3.0F, 8.0F, 8.0F, 0.0F, false);
    
    this.backWheels = new ModelRenderer((Model)this);
    this.backWheels.setRotationPoint(8.0F, 20.75F, 14.25F);
    this.backWheels.setTextureOffset(0, 0).addBox(-21.0F, -2.75F, -3.25F, 26.0F, 6.0F, 6.0F, 0.0F, false);
    
    this.backLeftWheel = new ModelRenderer((Model)this);
    this.backLeftWheel.setRotationPoint(5.0F, 0.0F, 0.0F);
    this.backWheels.addChild(this.backLeftWheel);
    this.backLeftWheel.setTextureOffset(0, 12).addBox(0.0F, -7.75F, -7.25F, 6.0F, 15.0F, 15.0F, 0.0F, false);
    
    this.backRightWheel = new ModelRenderer((Model)this);
    this.backRightWheel.setRotationPoint(-21.0F, 0.0F, 0.0F);
    this.backWheels.addChild(this.backRightWheel);
    this.backRightWheel.setTextureOffset(0, 12).addBox(-6.0F, -7.75F, -7.25F, 6.0F, 15.0F, 15.0F, 0.0F, false);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.frontRightWheel.rotateAngleX = limbSwing * 0.8F;
    this.frontLeftWheel.rotateAngleX = limbSwing * 0.8F;
    this.backRightWheel.rotateAngleX = limbSwing * 0.8F;
    this.backLeftWheel.rotateAngleX = limbSwing * 0.8F;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.frontWheels.render(matrixStack, buffer, packedLight, packedOverlay);
    this.backWheels.render(matrixStack, buffer, packedLight, packedOverlay);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
}


