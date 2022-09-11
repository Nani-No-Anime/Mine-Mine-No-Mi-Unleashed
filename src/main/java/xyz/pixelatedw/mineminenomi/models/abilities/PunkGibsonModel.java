package xyz.pixelatedw.mineminenomi.models.abilities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class PunkGibsonModel
  extends EntityModel<Entity>
{
  public final ModelRenderer rightArm;
  private final ModelRenderer smallArm;
  private final ModelRenderer bigArm;
  private final ModelRenderer finger4;
  private final ModelRenderer finger4b;
  private final ModelRenderer finger3;
  private final ModelRenderer finger3b;
  private final ModelRenderer finger2;
  private final ModelRenderer finger2b;
  private final ModelRenderer finger1;
  private final ModelRenderer finger1b;
  
  public PunkGibsonModel() {
    this.textureWidth = 128;
    this.textureHeight = 128;
    
    this.rightArm = new ModelRenderer((Model)this);
    this.rightArm.setRotationPoint(-2.75F, -0.75F, -0.5F);
    setRotationAngle(this.rightArm, -1.5272F, 0.0F, 0.0F);
    
    this.smallArm = new ModelRenderer((Model)this);
    this.smallArm.setRotationPoint(-1.0019F, -0.0872F, 2.5F);
    this.rightArm.addChild(this.smallArm);
    this.smallArm.setTextureOffset(64, 83).addBox(-16.0F, -7.0F, -6.0F, 16.0F, 29.0F, 16.0F, 0.0F, false);
    
    this.bigArm = new ModelRenderer((Model)this);
    this.bigArm.setRotationPoint(-8.0019F, 19.9128F, 5.0F);
    this.rightArm.addChild(this.bigArm);
    setRotationAngle(this.bigArm, 0.0452F, 0.3127F, -0.1608F);
    this.bigArm.setTextureOffset(0, 0).addBox(-12.7334F, 0.2782F, -11.8359F, 24.0F, 58.0F, 24.0F, 0.0F, false);
    
    this.finger4 = new ModelRenderer((Model)this);
    this.finger4.setRotationPoint(11.3785F, 55.124F, -6.8285F);
    this.bigArm.addChild(this.finger4);
    setRotationAngle(this.finger4, 0.7105F, -0.7987F, -0.3516F);
    this.finger4.setTextureOffset(0, 103).addBox(-3.5F, -2.0F, -7.0F, 7.0F, 4.0F, 14.0F, 0.0F, false);
    
    this.finger4b = new ModelRenderer((Model)this);
    this.finger4b.setRotationPoint(0.0F, -1.0418F, -6.3826F);
    this.finger4.addChild(this.finger4b);
    setRotationAngle(this.finger4b, 1.0472F, 0.0F, 0.0F);
    this.finger4b.setTextureOffset(0, 85).addBox(-3.5F, -1.2224F, -14.1691F, 7.0F, 4.0F, 14.0F, 0.01F, false);
    
    this.finger3 = new ModelRenderer((Model)this);
    this.finger3.setRotationPoint(6.6941F, 56.5544F, -11.7482F);
    this.bigArm.addChild(this.finger3);
    setRotationAngle(this.finger3, 0.8733F, -0.028F, -0.0259F);
    this.finger3.setTextureOffset(0, 103).addBox(-3.2897F, -0.6527F, -11.7155F, 7.0F, 4.0F, 14.0F, 0.0F, false);
    
    this.finger3b = new ModelRenderer((Model)this);
    this.finger3b.setRotationPoint(0.0F, -2.6608F, -11.5565F);
    this.finger3.addChild(this.finger3b);
    setRotationAngle(this.finger3b, 1.0036F, 0.0F, 0.0F);
    this.finger3b.setTextureOffset(0, 85).addBox(-3.2897F, 0.9734F, -15.8321F, 7.0F, 4.0F, 14.0F, 0.01F, false);
    
    this.finger2 = new ModelRenderer((Model)this);
    this.finger2.setRotationPoint(-1.3059F, 56.5544F, -11.7482F);
    this.bigArm.addChild(this.finger2);
    setRotationAngle(this.finger2, 0.8728F, 0.0F, 0.0076F);
    this.finger2.setTextureOffset(0, 103).addBox(-3.299F, -0.6527F, -11.7065F, 7.0F, 4.0F, 14.0F, 0.0F, false);
    
    this.finger2b = new ModelRenderer((Model)this);
    this.finger2b.setRotationPoint(0.0F, -2.9108F, -12.3065F);
    this.finger2.addChild(this.finger2b);
    setRotationAngle(this.finger2b, 1.0036F, 0.0F, 0.0F);
    this.finger2b.setTextureOffset(0, 85).addBox(-3.299F, 1.5005F, -15.4963F, 7.0F, 4.0F, 14.0F, 0.01F, false);
    
    this.finger1 = new ModelRenderer((Model)this);
    this.finger1.setRotationPoint(-9.3674F, 56.552F, -11.763F);
    this.bigArm.addChild(this.finger1);
    setRotationAngle(this.finger1, 0.8728F, 0.043F, 0.0076F);
    this.finger1.setTextureOffset(0, 103).addBox(-3.1633F, -0.6465F, -11.6975F, 7.0F, 4.0F, 14.0F, 0.0F, false);
    
    this.finger1b = new ModelRenderer((Model)this);
    this.finger1b.setRotationPoint(0.2148F, -2.91F, -12.3019F);
    this.finger1.addChild(this.finger1b);
    setRotationAngle(this.finger1b, 1.0036F, 0.0F, 0.0F);
    this.finger1b.setTextureOffset(0, 85).addBox(-3.3781F, 1.5072F, -15.4983F, 7.0F, 4.0F, 14.0F, 0.01F, false);
  }



  
  public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}



  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


