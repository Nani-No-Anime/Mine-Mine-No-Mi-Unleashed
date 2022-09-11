package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WeatherWizardModel
  extends BipedModel
{
  public ModelRenderer rightArm;
  public ModelRenderer rightLeg;
  public ModelRenderer head;
  public ModelRenderer body;
  public ModelRenderer leftArm;
  public ModelRenderer leftLeg;
  public ModelRenderer hat1;
  public ModelRenderer beard1;
  public ModelRenderer hat2;
  public ModelRenderer hat3;
  public ModelRenderer hat4;
  public ModelRenderer hat5;
  public ModelRenderer beard2;
  public ModelRenderer rightFancyBeard1;
  public ModelRenderer leftFancyBeard1;
  
  public WeatherWizardModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 64;
    this.hat5 = new ModelRenderer((Model)this, 0, 39);
    this.hat5.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.hat5.addBox(-2.5F, -9.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.0F);
    this.leftFancyBeard1 = new ModelRenderer((Model)this, 35, 0);
    this.leftFancyBeard1.setRotationPoint(0.9F, 1.0F, 0.0F);
    this.leftFancyBeard1.addBox(0.0F, -2.0F, 0.0F, 4.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftFancyBeard1, 0.0F, 0.0F, 1.0471976F);
    this.beard2 = new ModelRenderer((Model)this, 35, 0);
    this.beard2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.beard2.addBox(-2.0F, 7.0F, 0.0F, 4.0F, 2.0F, 1.0F, 0.0F);
    this.head = new ModelRenderer((Model)this, 0, 0);
    this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.head.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F);
    this.beard1 = new ModelRenderer((Model)this, 35, 0);
    this.beard1.setRotationPoint(0.0F, -2.0F, -5.0F);
    this.beard1.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 7.0F, 1.0F, 0.0F);
    this.rightArm = new ModelRenderer((Model)this, 40, 16);
    this.rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
    this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
    this.rightFancyBeard1 = new ModelRenderer((Model)this, 35, 0);
    this.rightFancyBeard1.setRotationPoint(-0.9F, 1.0F, 0.0F);
    this.rightFancyBeard1.addBox(-4.0F, -2.0F, 0.0F, 4.0F, 2.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightFancyBeard1, 0.0F, 0.0F, -1.0471976F);
    this.body = new ModelRenderer((Model)this, 16, 16);
    this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.body.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F);
    this.hat1 = new ModelRenderer((Model)this, 0, 35);
    this.hat1.setRotationPoint(0.0F, -8.5F, 0.0F);
    this.hat1.addBox(-4.5F, 0.0F, -4.5F, 9.0F, 2.0F, 9.0F, 0.0F);
    this.hat2 = new ModelRenderer((Model)this, 0, 36);
    this.hat2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.hat2.addBox(-4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F);
    this.hat4 = new ModelRenderer((Model)this, 0, 38);
    this.hat4.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.hat4.addBox(-3.0F, -7.0F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F);
    this.leftLeg = new ModelRenderer((Model)this, 16, 48);
    this.leftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
    this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
    this.rightLeg = new ModelRenderer((Model)this, 0, 16);
    this.rightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
    this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
    this.leftArm = new ModelRenderer((Model)this, 32, 48);
    this.leftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
    this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
    this.hat3 = new ModelRenderer((Model)this, 0, 36);
    this.hat3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.hat3.addBox(-3.5F, -5.0F, -3.5F, 7.0F, 3.0F, 7.0F, 0.0F);
    this.hat4.addChild(this.hat5);
    this.beard2.addChild(this.leftFancyBeard1);
    this.beard1.addChild(this.beard2);
    this.head.addChild(this.beard1);
    this.beard2.addChild(this.rightFancyBeard1);
    this.head.addChild(this.hat1);
    this.hat1.addChild(this.hat2);
    this.hat3.addChild(this.hat4);
    this.hat2.addChild(this.hat3);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.bipedBody = this.body;
    this.bipedHead = this.head;
    this.bipedLeftArm = this.leftArm;
    this.bipedRightArm = this.rightArm;
    this.bipedLeftLeg = this.leftLeg;
    this.bipedRightLeg = this.rightLeg;
    
    this.bipedHeadwear.showModel = false;
    
    this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }

  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


