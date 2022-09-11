package xyz.pixelatedw.mineminenomi.models.abilities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class HanaCalendulaModel<T extends LivingEntity>
  extends PlayerModel<T> {
  private final ModelRenderer calendula;
  private final ModelRenderer calendula1;
  private final ModelRenderer calendula2;
  private final ModelRenderer calendula3;
  private final ModelRenderer calendula4;
  private final ModelRenderer calendula5;
  private final ModelRenderer calendula6;
  private final ModelRenderer calendula7;
  private final ModelRenderer calendula8;
  
  public HanaCalendulaModel() {
    super(1.0F, false);
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.calendula = new ModelRenderer((Model)this);
    this.calendula.setRotationPoint(-9.0F, 7.0F, 0.0F);
    
    this.calendula1 = new ModelRenderer((Model)this);
    this.calendula1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.calendula.addChild(this.calendula1);
    this.calendula1.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    
    this.calendula2 = new ModelRenderer((Model)this);
    this.calendula2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.calendula.addChild(this.calendula2);
    setRotationAngle(this.calendula2, -0.7854F, 0.0F, 0.0F);
    this.calendula2.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.01F, false);
    
    this.calendula3 = new ModelRenderer((Model)this);
    this.calendula3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.calendula.addChild(this.calendula3);
    setRotationAngle(this.calendula3, -1.5708F, 0.0F, 0.0F);
    this.calendula3.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.011F, false);
    
    this.calendula4 = new ModelRenderer((Model)this);
    this.calendula4.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.calendula.addChild(this.calendula4);
    setRotationAngle(this.calendula4, -2.3562F, 0.0F, 0.0F);
    this.calendula4.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.012F, false);
    
    this.calendula5 = new ModelRenderer((Model)this);
    this.calendula5.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.calendula.addChild(this.calendula5);
    setRotationAngle(this.calendula5, 3.1416F, 0.0F, 0.0F);
    this.calendula5.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.013F, false);
    
    this.calendula6 = new ModelRenderer((Model)this);
    this.calendula6.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.calendula.addChild(this.calendula6);
    setRotationAngle(this.calendula6, 2.3562F, 0.0F, 0.0F);
    this.calendula6.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.014F, false);
    
    this.calendula7 = new ModelRenderer((Model)this);
    this.calendula7.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.calendula.addChild(this.calendula7);
    setRotationAngle(this.calendula7, 1.5708F, 0.0F, 0.0F);
    this.calendula7.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.015F, false);
    
    this.calendula8 = new ModelRenderer((Model)this);
    this.calendula8.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.calendula.addChild(this.calendula8);
    setRotationAngle(this.calendula8, 0.7854F, 0.0F, 0.0F);
    this.calendula8.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.016F, false);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.calendula.rotateAngleX = -ageInTicks * 0.2F;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    matrixStack.push();
    matrixStack.translate(0.0D, -0.4D, 0.0D);
    matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
    this.calendula.render(matrixStack, buffer, packedLight, packedOverlay);
    matrixStack.pop();
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


