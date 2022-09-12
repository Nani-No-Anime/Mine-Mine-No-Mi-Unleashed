package xyz.pixelatedw.mineminenomi.models.entities.zoans.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;

public class KameWalkPartialModel<T extends LivingEntity>
  extends ZoanMorphModel<T> {
  private final ModelRenderer shell;
  
  public KameWalkPartialModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.shell = new ModelRenderer((Model)this);
    this.shell.setRotationPoint(0.0F, 9.5F, 1.0F);
    this.shell.setTextureOffset(0, 18).addBox(-5.5F, -2.5F, 0.0F, 11.0F, 3.0F, 2.0F, 0.0F, false);
    this.shell.setTextureOffset(0, 13).addBox(-5.5F, 8.5F, 0.0F, 11.0F, 3.0F, 2.0F, 0.0F, false);
    this.shell.setTextureOffset(10, 23).addBox(-5.5F, 0.5F, 0.0F, 3.0F, 8.0F, 2.0F, 0.0F, false);
    this.shell.setTextureOffset(0, 23).addBox(2.5F, 0.5F, 0.0F, 3.0F, 8.0F, 2.0F, 0.0F, false);
    this.shell.setTextureOffset(0, 0).addBox(-4.5F, -1.5F, 2.0F, 9.0F, 12.0F, 1.0F, 0.0F, false);
    this.shell.setTextureOffset(20, 0).addBox(-3.5F, -0.5F, 3.0F, 7.0F, 10.0F, 1.0F, 0.0F, false);
    
    this.bipedBody = this.shell;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    matrixStack.push();
    float scale = 1.25F;
    matrixStack.scale(scale, scale, scale);
    this.shell.render(matrixStack, buffer, packedLight, packedOverlay);
    matrixStack.pop();
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
}


