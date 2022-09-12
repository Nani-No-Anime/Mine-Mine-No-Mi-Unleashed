package xyz.pixelatedw.mineminenomi.models.entities.zoans.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;

public class BaraCarPartialModel<T extends LivingEntity>
  extends ZoanMorphModel<T> {
  private final ModelRenderer Head;
  private final ModelRenderer Body;
  private final ModelRenderer RightArm;
  private final ModelRenderer LeftArm;
  private final ModelRenderer RightLeg;
  private final ModelRenderer LeftLeg;
  
  public BaraCarPartialModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.Head = new ModelRenderer((Model)this);
    this.Head.setRotationPoint(0.0F, 19.0F, 0.0F);
    this.Head.setTextureOffset(0, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
    this.Head.setTextureOffset(32, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);
    
    this.Body = new ModelRenderer((Model)this);
    this.Body.setRotationPoint(0.0F, 21.0F, -1.0F);
    setRotationAngle(this.Body, 1.5708F, 0.0F, 0.0F);
    this.Body.setTextureOffset(16, 16).addBox(-4.0F, -6.0F, -1.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
    this.Body.setTextureOffset(16, 32).addBox(-4.0F, -6.0F, -1.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);
    
    this.RightArm = new ModelRenderer((Model)this);
    this.RightArm.setRotationPoint(-5.0F, 21.0F, -6.0F);
    setRotationAngle(this.RightArm, -1.309F, 0.0F, 0.0F);
    this.RightArm.setTextureOffset(40, 16).addBox(-3.0F, -2.1736F, -2.9848F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    this.RightArm.setTextureOffset(40, 32).addBox(-3.0F, -2.1736F, -2.9848F, 4.0F, 12.0F, 4.0F, 0.25F, false);
    
    this.LeftArm = new ModelRenderer((Model)this);
    this.LeftArm.setRotationPoint(5.0F, 22.0F, -6.0F);
    setRotationAngle(this.LeftArm, -1.309F, 0.0F, 0.0F);
    this.LeftArm.setTextureOffset(32, 48).addBox(-1.0F, -2.1736F, -3.9848F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    this.LeftArm.setTextureOffset(48, 48).addBox(-1.0F, -2.1736F, -3.9848F, 4.0F, 12.0F, 4.0F, 0.25F, false);
    
    this.RightLeg = new ModelRenderer((Model)this);
    this.RightLeg.setRotationPoint(-3.9F, 18.0F, 4.0F);
    setRotationAngle(this.RightLeg, 1.7453F, -0.1745F, 0.1745F);
    this.RightLeg.setTextureOffset(0, 16).addBox(-1.271F, 0.2007F, -4.0354F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    this.RightLeg.setTextureOffset(0, 32).addBox(-1.271F, 0.2007F, -4.0354F, 4.0F, 12.0F, 4.0F, 0.25F, false);
    
    this.LeftLeg = new ModelRenderer((Model)this);
    this.LeftLeg.setRotationPoint(3.9F, 18.0F, 4.0F);
    setRotationAngle(this.LeftLeg, 1.7453F, 0.2618F, -0.1745F);
    this.LeftLeg.setTextureOffset(16, 48).addBox(-2.7323F, 0.2153F, -4.038F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    this.LeftLeg.setTextureOffset(0, 48).addBox(-2.7323F, 0.2153F, -4.038F, 4.0F, 12.0F, 4.0F, 0.25F, false);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}



  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.Head.render(matrixStack, buffer, packedLight, packedOverlay);
    this.Body.render(matrixStack, buffer, packedLight, packedOverlay);
    this.RightArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.LeftArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.RightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
}


