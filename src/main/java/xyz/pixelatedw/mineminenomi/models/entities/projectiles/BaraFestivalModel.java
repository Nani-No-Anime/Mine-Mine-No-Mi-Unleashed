package xyz.pixelatedw.mineminenomi.models.entities.projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bara.BaraFestivalEntity;

public class BaraFestivalModel<T extends BaraFestivalEntity>
  extends EntityModel<T> {
  private final ModelRenderer limb1;
  private final ModelRenderer limb2;
  private final ModelRenderer limb3;
  private final ModelRenderer limb4;
  
  public BaraFestivalModel() {
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.limb1 = new ModelRenderer((Model)this);
    this.limb1.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.limb1.setTextureOffset(0, 16).addBox(-18.0F, -12.0F, -6.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    
    this.limb2 = new ModelRenderer((Model)this);
    this.limb2.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.limb2.setTextureOffset(0, 16).addBox(4.0F, -12.0F, 12.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    
    this.limb3 = new ModelRenderer((Model)this);
    this.limb3.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.limb3.setTextureOffset(40, 16).addBox(-4.0F, -12.0F, -20.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    
    this.limb4 = new ModelRenderer((Model)this);
    this.limb4.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.limb4.setTextureOffset(40, 16).addBox(16.0F, -12.0F, 2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.limb1.rotateAngleY = (float)(this.limb1.rotateAngleY + Math.sin(ageInTicks * 0.02D) / 10.0D);
    this.limb1.rotateAngleZ = (float)(this.limb1.rotateAngleZ + Math.sin(ageInTicks * 0.1D) / 3.0D);
    
    this.limb2.rotateAngleY = (float)(this.limb2.rotateAngleY + Math.cos(ageInTicks * 0.2D) / 10.0D);
    this.limb2.rotateAngleX = (float)(this.limb2.rotateAngleX + Math.sin(ageInTicks * 0.1D) / 3.0D);
    
    this.limb3.rotateAngleY = (float)(this.limb3.rotateAngleY - Math.cos(ageInTicks * 0.02D) / 10.0D);
    this.limb3.rotateAngleZ = (float)(this.limb3.rotateAngleZ + Math.sin(ageInTicks * 0.1D) / 3.0D);
    
    this.limb4.rotateAngleY = (float)(this.limb4.rotateAngleY - Math.sin(ageInTicks * 0.5D) / 10.0D);
    this.limb4.rotateAngleX = (float)(this.limb4.rotateAngleX - Math.sin(ageInTicks * 0.1D) / 3.0D);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.limb1.render(matrixStack, buffer, packedLight, packedOverlay);
    this.limb2.render(matrixStack, buffer, packedLight, packedOverlay);
    this.limb3.render(matrixStack, buffer, packedLight, packedOverlay);
    this.limb4.render(matrixStack, buffer, packedLight, packedOverlay);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


