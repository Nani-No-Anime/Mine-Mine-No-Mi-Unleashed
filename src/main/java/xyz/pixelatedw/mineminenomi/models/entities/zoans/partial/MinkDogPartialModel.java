package xyz.pixelatedw.mineminenomi.models.entities.zoans.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;

public class MinkDogPartialModel<T extends LivingEntity>
  extends ZoanMorphModel<T> {
  private final ModelRenderer ears;
  private final ModelRenderer rightEar1;
  private final ModelRenderer rightEar2;
  private final ModelRenderer leftEar1;
  private final ModelRenderer leftEar2;
  private final ModelRenderer tail;
  private final ModelRenderer tail2;
  private final ModelRenderer mouth;
  
  public MinkDogPartialModel() {
    super(1.0F);
    this.textureWidth = 32;
    this.textureHeight = 32;
    
    this.ears = new ModelRenderer((Model)this);
    this.ears.setRotationPoint(0.0F, 8.0F, 0.0F);

    
    this.rightEar1 = new ModelRenderer((Model)this);
    this.rightEar1.setRotationPoint(-2.5F, -7.5F, 0.0F);
    this.ears.addChild(this.rightEar1);
    setRotationAngle(this.rightEar1, 0.0F, -0.1745F, -0.2618F);
    this.rightEar1.setTextureOffset(0, 25).addBox(-1.1471F, -2.5F, -0.1504F, 3.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.rightEar2 = new ModelRenderer((Model)this);
    this.rightEar2.setRotationPoint(0.3214F, -2.2239F, -0.0325F);
    this.rightEar1.addChild(this.rightEar2);
    setRotationAngle(this.rightEar2, 0.0F, 0.0F, 0.7854F);
    this.rightEar2.setTextureOffset(0, 16).addBox(-1.1814F, -1.189F, -0.1179F, 2.0F, 2.0F, 1.0F, -0.001F, false);
    
    this.leftEar1 = new ModelRenderer((Model)this);
    this.leftEar1.setRotationPoint(2.5F, -7.0F, 0.0F);
    this.ears.addChild(this.leftEar1);
    setRotationAngle(this.leftEar1, 0.0F, 0.1745F, 0.2618F);
    this.leftEar1.setTextureOffset(0, 25).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.leftEar2 = new ModelRenderer((Model)this);
    this.leftEar2.setRotationPoint(-0.5315F, -2.7239F, 0.1179F);
    this.leftEar1.addChild(this.leftEar2);
    setRotationAngle(this.leftEar2, 0.0F, 0.0F, 0.7854F);
    this.leftEar2.setTextureOffset(0, 16).addBox(-1.1814F, -1.189F, -0.1179F, 2.0F, 2.0F, 1.0F, -0.001F, false);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(0.0F, 11.0F, 1.0F);
    setRotationAngle(this.tail, 0.3054F, 0.0F, 0.0F);
    this.tail.setTextureOffset(0, 0).addBox(-1.0F, -0.2456F, 0.653F, 2.0F, 2.0F, 3.0F, 0.0F, false);
    
    this.tail2 = new ModelRenderer((Model)this);
    this.tail2.setRotationPoint(0.5F, 1.3463F, 3.8713F);
    this.tail.addChild(this.tail2);
    setRotationAngle(this.tail2, -0.3054F, 0.0F, 0.0F);
    this.tail2.setTextureOffset(0, 6).addBox(-1.5F, -1.4919F, -0.6388F, 2.0F, 2.0F, 4.0F, -0.01F, false);
    
    this.mouth = new ModelRenderer((Model)this);
    this.mouth.setRotationPoint(0.0F, 6.0F, 1.0F);
    this.mouth.setTextureOffset(16, 13).addBox(-1.5F, -3.0F, -8.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.ears.copyModelAngles(this.bipedHead);
    this.mouth.copyModelAngles(this.bipedHead);
    this.ears.render(matrixStack, buffer, packedLight, packedOverlay);
    this.tail.render(matrixStack, buffer, packedLight, packedOverlay);
    this.mouth.render(matrixStack, buffer, packedLight, packedOverlay);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    
    this.tail.rotationPointY--;
    
    if (entity.isCrouching()) {
      
      this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + 0.4D);
      this.tail.rotationPointY++;
      this.tail.rotationPointZ += 4.0F;
    } 
    
    this.tail.rotateAngleY = (float)(this.tail.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 6.0D);
    this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
}


