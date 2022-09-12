package xyz.pixelatedw.mineminenomi.models.entities.zoans.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;

public class SpringLegsPartialModel<T extends LivingEntity>
  extends ZoanMorphModel<T> {
  private final ModelRenderer rightSpring;
  private final ModelRenderer leftSpring;
  private final ModelRenderer rightSpringLayer;
  private final ModelRenderer leftSpringLayer;
  
  public SpringLegsPartialModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.rightSpring = new ModelRenderer((Model)this);
    this.rightSpring.setRotationPoint(-2.0F, 12.0F, 0.0F);
    this.rightSpring.setTextureOffset(0, 0).addBox(-1.0F, -0.25F, -2.0F, 2.0F, 1.0F, 1.0F, -0.01F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, 1.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 1.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(1.0F, 2.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 2.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 3.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 3.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(1.0F, 4.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 4.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 5.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 5.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(1.0F, 6.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 6.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 7.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 7.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(1.0F, 8.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 8.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 9.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 9.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(1.0F, 10.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 10.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-2.0F, 11.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
    this.rightSpring.setTextureOffset(0, 0).addBox(-1.0F, 11.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.leftSpring = new ModelRenderer((Model)this);
    this.leftSpring.setRotationPoint(2.0F, 12.0F, 0.0F);
    this.leftSpring.setTextureOffset(0, 0).addBox(-1.0F, -0.25F, -2.0F, 2.0F, 1.0F, 1.0F, -0.01F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, 1.0F, 4.0F, 1.0F, 1.0F, 0.0F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(1.0F, 1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 1.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 2.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 2.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(1.0F, 3.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 3.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 4.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 4.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(1.0F, 5.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 5.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 6.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 6.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(1.0F, 7.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 7.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 8.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 8.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(1.0F, 9.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 9.5F, -2.0F, 4.0F, 1.0F, 1.0F, 0.01F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 10.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-2.0F, 10.5F, 1.0F, 4.0F, 1.0F, 1.0F, 0.0F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(1.0F, 11.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
    this.leftSpring.setTextureOffset(0, 0).addBox(-1.0F, 11.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, true);
    
    this.rightSpringLayer = new ModelRenderer((Model)this);
    this.rightSpringLayer.setRotationPoint(-2.0F, 12.0F, 0.0F);
    this.rightSpringLayer.setTextureOffset(0, 48).addBox(-2.0F, -0.25F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);
    
    this.leftSpringLayer = new ModelRenderer((Model)this);
    this.leftSpringLayer.setRotationPoint(2.0F, 12.0F, 0.0F);
    this.leftSpringLayer.setTextureOffset(16, 48).addBox(-2.0F, -0.25F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, true);
    
    this.bipedLeftLeg = this.leftSpring;
    this.bipedRightLeg = this.rightSpring;
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.rightSpring.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftSpring.render(matrixStack, buffer, packedLight, packedOverlay);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
}


