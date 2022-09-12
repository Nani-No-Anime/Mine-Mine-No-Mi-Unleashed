package xyz.pixelatedw.mineminenomi.models.armors;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class MH5GasMaskModel<T extends LivingEntity>
  extends BipedModel<T> {
  public ModelRenderer base_0;
  public ModelRenderer base_1;
  public ModelRenderer eyes;
  public ModelRenderer right_ear;
  public ModelRenderer left_ear;
  public ModelRenderer eye_ear_connector_0;
  public ModelRenderer eye_ear_connector_1;
  public ModelRenderer eye_ear_connector_2;
  public ModelRenderer eye_ear_connector_3;
  
  public MH5GasMaskModel() {
    super(1.0F);
    this.textureWidth = 32;
    this.textureHeight = 16;
    this.left_ear = new ModelRenderer((Model)this, 17, 10);
    this.left_ear.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.left_ear.addBox(2.8F, -5.6F, -1.8F, 2.0F, 2.0F, 4.0F, 0.0F);
    this.eye_ear_connector_2 = new ModelRenderer((Model)this, 8, 10);
    this.eye_ear_connector_2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.eye_ear_connector_2.addBox(3.5F, -5.4F, -4.4F, 1.0F, 1.0F, 3.0F, 0.0F);
    this.base_0 = new ModelRenderer((Model)this, 0, 12);
    this.base_0.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.base_0.addBox(-1.4F, -3.0F, -4.5F, 3.0F, 3.0F, 1.0F, 0.0F);
    this.eyes = new ModelRenderer((Model)this, 0, 0);
    this.eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.eyes.addBox(-3.0F, -5.9F, -5.0F, 6.0F, 3.0F, 2.0F, 0.0F);
    this.eye_ear_connector_3 = new ModelRenderer((Model)this, 9, 14);
    this.eye_ear_connector_3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.eye_ear_connector_3.addBox(1.5F, -5.4F, -4.4F, 2.0F, 1.0F, 1.0F, 0.0F);
    this.base_1 = new ModelRenderer((Model)this, 0, 8);
    this.base_1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.base_1.addBox(-1.0F, -2.5F, -5.4F, 2.0F, 2.0F, 2.0F, 0.0F);
    this.eye_ear_connector_1 = new ModelRenderer((Model)this, 9, 14);
    this.eye_ear_connector_1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.eye_ear_connector_1.addBox(-4.5F, -5.4F, -4.4F, 2.0F, 1.0F, 1.0F, 0.0F);
    this.right_ear = new ModelRenderer((Model)this, 17, 10);
    this.right_ear.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.right_ear.addBox(-5.2F, -5.6F, -1.8F, 2.0F, 2.0F, 4.0F, 0.0F);
    this.eye_ear_connector_0 = new ModelRenderer((Model)this, 8, 10);
    this.eye_ear_connector_0.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.eye_ear_connector_0.addBox(-4.5F, -5.4F, -4.4F, 1.0F, 1.0F, 3.0F, 0.0F);
    this.base_0.addChild(this.left_ear);
    this.left_ear.addChild(this.eye_ear_connector_2);
    this.base_0.addChild(this.eyes);
    this.left_ear.addChild(this.eye_ear_connector_3);
    this.base_0.addChild(this.base_1);
    this.right_ear.addChild(this.eye_ear_connector_1);
    this.base_0.addChild(this.right_ear);
    this.right_ear.addChild(this.eye_ear_connector_0);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.base_0.copyModelAngles(this.bipedHead);
    this.base_0.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
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


