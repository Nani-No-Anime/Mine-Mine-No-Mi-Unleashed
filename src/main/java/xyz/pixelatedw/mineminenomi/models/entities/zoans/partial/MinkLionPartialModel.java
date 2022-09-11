package xyz.pixelatedw.mineminenomi.models.entities.zoans.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;

public class MinkLionPartialModel<T extends LivingEntity>
  extends ZoanMorphModel<T> {
  private final ModelRenderer mane;
  private final ModelRenderer mane2;
  private final ModelRenderer tail;
  private final ModelRenderer tail2;
  private final ModelRenderer tailTip;
  
  public MinkLionPartialModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.mane = new ModelRenderer((Model)this);
    this.mane.setRotationPoint(0.0F, 6.5F, 1.0F);
    this.mane.setTextureOffset(0, 15).addBox(-6.5F, -10.5F, -2.0F, 13.0F, 13.0F, 4.0F, 0.0F, false);
    
    this.mane2 = new ModelRenderer((Model)this);
    this.mane2.setRotationPoint(4.5F, -0.5F, -1.0F);
    this.mane.addChild(this.mane2);
    this.mane2.setTextureOffset(0, 15).addBox(-10.0F, -9.0F, -2.0F, 11.0F, 11.0F, 6.0F, 0.0F, false);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(0.0F, 11.0F, 1.0F);
    setRotationAngle(this.tail, 0.3054F, 0.0F, 0.0F);
    this.tail.setTextureOffset(0, 0).addBox(-0.5F, -0.2456F, 0.653F, 1.0F, 1.0F, 3.0F, 0.0F, false);
    
    this.tail2 = new ModelRenderer((Model)this);
    this.tail2.setRotationPoint(0.0F, 1.3592F, 3.0808F);
    this.tail.addChild(this.tail2);
    setRotationAngle(this.tail2, -0.7417F, 0.0F, 0.0F);
    this.tail2.setTextureOffset(0, 6).addBox(-0.5F, -1.5592F, -0.5808F, 1.0F, 1.0F, 4.0F, -0.01F, false);
    
    this.tailTip = new ModelRenderer((Model)this);
    this.tailTip.setRotationPoint(0.0F, -0.135F, 3.2472F);
    this.tail2.addChild(this.tailTip);
    setRotationAngle(this.tailTip, -0.1309F, 0.0F, 0.0F);
    this.tailTip.setTextureOffset(11, 0).addBox(-1.0F, -1.8743F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.mane.copyModelAngles(this.bipedHead);
    this.mane.render(matrixStack, buffer, packedLight, packedOverlay);
    this.tail.render(matrixStack, buffer, packedLight, packedOverlay);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    
    if (entity.isCrouching()) {
      
      this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + 0.4D);
      this.tail.rotationPointY++;
      this.tail.rotationPointZ += 4.0F;
    } 
    
    this.tail.rotateAngleY = (float)(this.tail.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 10.0D);
    this.tail.rotateAngleX = (float)(this.tail.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 5.0D);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
}


