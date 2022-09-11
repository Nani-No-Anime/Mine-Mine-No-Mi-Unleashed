package xyz.pixelatedw.mineminenomi.models.armors;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class ColaBackpackModel<T extends LivingEntity>
  extends BipedModel<T> {
  public ModelRenderer base;
  public ModelRenderer leftCola;
  public ModelRenderer rightCola;
  public ModelRenderer leftColaCap;
  public ModelRenderer rightColaCap;
  
  public ColaBackpackModel() {
    super(1.0F);
    this.textureWidth = 16;
    this.textureHeight = 16;
    this.leftColaCap = new ModelRenderer((Model)this, 0, 14);
    this.leftColaCap.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftColaCap.addBox(1.5F, 1.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F);
    this.rightCola = new ModelRenderer((Model)this, 0, 0);
    this.rightCola.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.rightCola.addBox(-3.5F, 1.5F, 2.0F, 3.0F, 7.0F, 3.0F, 0.0F);
    this.leftCola = new ModelRenderer((Model)this, 0, 0);
    this.leftCola.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftCola.addBox(0.5F, 1.5F, 2.0F, 3.0F, 7.0F, 3.0F, 0.0F);
    this.rightColaCap = new ModelRenderer((Model)this, 0, 14);
    this.rightColaCap.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.rightColaCap.addBox(-2.5F, 1.0F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F);
    this.base = new ModelRenderer((Model)this, 5, 12);
    this.base.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.base.addBox(-0.5F, 3.0F, 2.0F, 1.0F, 2.0F, 2.0F, 0.0F);
    this.leftCola.addChild(this.leftColaCap);
    this.base.addChild(this.rightCola);
    this.base.addChild(this.leftCola);
    this.rightCola.addChild(this.rightColaCap);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.base.copyModelAngles(this.bipedBody);
    this.base.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
  }

  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


