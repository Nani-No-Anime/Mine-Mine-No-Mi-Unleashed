package xyz.pixelatedw.mineminenomi.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HandcuffModel
  extends EntityModel<Entity>
{
  private final ModelRenderer handcuffs;
  private final ModelRenderer rightCuff;
  private final ModelRenderer leftCuff;
  private final ModelRenderer chain;
  
  public HandcuffModel() {
    this.textureWidth = 32;
    this.textureHeight = 32;
    
    this.handcuffs = new ModelRenderer((Model)this);
    this.handcuffs.setRotationPoint(1.0F, 12.0F, -10.0F);
    setRotationAngle(this.handcuffs, 0.7854F, 0.0F, 0.0F);
    
    this.rightCuff = new ModelRenderer((Model)this);
    this.rightCuff.setRotationPoint(-7.0F, 0.75F, 7.0F);
    this.handcuffs.addChild(this.rightCuff);
    this.rightCuff.setTextureOffset(0, 12).addBox(2.0F, -2.0F, -2.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
    this.rightCuff.setTextureOffset(0, 0).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
    this.rightCuff.setTextureOffset(0, 0).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
    this.rightCuff.setTextureOffset(0, 12).addBox(-3.0F, -2.0F, -2.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
    
    this.leftCuff = new ModelRenderer((Model)this);
    this.leftCuff.setRotationPoint(5.0F, 0.75F, 7.0F);
    this.handcuffs.addChild(this.leftCuff);
    this.leftCuff.setTextureOffset(0, 12).addBox(2.0F, -2.0F, -2.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
    this.leftCuff.setTextureOffset(0, 0).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
    this.leftCuff.setTextureOffset(0, 0).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
    this.leftCuff.setTextureOffset(0, 12).addBox(-3.0F, -2.0F, -2.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
    
    this.chain = new ModelRenderer((Model)this);
    this.chain.setRotationPoint(-1.0F, 0.25F, 6.0F);
    this.handcuffs.addChild(this.chain);
    this.chain.setTextureOffset(0, 8).addBox(-2.0F, 1.0F, 0.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
    this.chain.setTextureOffset(0, 8).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
    this.chain.setTextureOffset(0, 5).addBox(-3.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    this.chain.setTextureOffset(0, 5).addBox(1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
  }



  
  public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.handcuffs.render(matrixStack, buffer, packedLight, packedOverlay);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


