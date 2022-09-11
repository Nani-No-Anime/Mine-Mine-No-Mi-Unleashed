package xyz.pixelatedw.mineminenomi.models.entities.projectiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PheasantModel
  extends EntityModel
{
  public ModelRenderer body1;
  public ModelRenderer body2;
  public ModelRenderer body3;
  public ModelRenderer body4;
  public ModelRenderer body5;
  public ModelRenderer head;
  public ModelRenderer tuft1;
  public ModelRenderer tuft2;
  public ModelRenderer tuft3;
  public ModelRenderer beak1;
  public ModelRenderer beak2;
  public ModelRenderer beak3;
  public ModelRenderer beak4;
  public ModelRenderer tail1;
  public ModelRenderer tail2;
  public ModelRenderer tail3;
  public ModelRenderer rightleg1;
  public ModelRenderer rightleg2;
  public ModelRenderer rightfoot1;
  public ModelRenderer leftleg1;
  public ModelRenderer leftleg2;
  public ModelRenderer leftfoot1;
  public ModelRenderer rightWing1;
  public ModelRenderer leftWing1;
  public ModelRenderer rightWing2;
  public ModelRenderer rightWing3;
  public ModelRenderer rightWing4;
  public ModelRenderer rightWing5;
  public ModelRenderer leftWing2;
  public ModelRenderer leftWing3;
  public ModelRenderer leftWing4;
  public ModelRenderer leftWing5;
  
  public PheasantModel() {
    this.textureWidth = 128;
    this.textureHeight = 64;
    this.body3 = new ModelRenderer((Model)this, 0, 27);
    this.body3.setRotationPoint(0.0F, 8.5F, -5.7F);
    this.body3.addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 2.0F, 0.0F);
    setRotateAngle(this.body3, -0.05235988F, -0.0F, 0.0F);
    this.leftWing5 = new ModelRenderer((Model)this, 94, 5);
    this.leftWing5.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftWing5.addBox(0.5F, 0.5F, 1.0F, 6.0F, 0.0F, 6.0F, 0.0F);
    setRotateAngle(this.leftWing5, -0.017453292F, 0.0F, 0.0F);
    this.tuft3 = new ModelRenderer((Model)this, 29, 51);
    this.tuft3.setRotationPoint(0.0F, 7.0F, -12.5F);
    this.tuft3.addBox(0.0F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F);
    setRotateAngle(this.tuft3, 0.6981317F, -0.0F, 0.34906584F);
    this.rightleg2 = new ModelRenderer((Model)this, 48, 0);
    this.rightleg2.setRotationPoint(-2.0F, 10.5F, 2.0F);
    this.rightleg2.addBox(-0.5F, 4.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F);
    setRotateAngle(this.rightleg2, 0.7853982F, -0.17453292F, 0.0F);
    this.body1 = new ModelRenderer((Model)this, 0, 0);
    this.body1.setRotationPoint(0.0F, 9.5F, 0.0F);
    this.body1.addBox(-3.5F, -4.0F, -5.0F, 7.0F, 7.0F, 10.0F, 0.0F);
    this.rightWing3 = new ModelRenderer((Model)this, 91, 0);
    this.rightWing3.setRotationPoint(-12.0F, -0.7F, -1.0F);
    this.rightWing3.addBox(-6.5F, 0.0F, 0.0F, 6.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightWing3, 0.0F, 0.2443461F, 0.0F);
    this.beak4 = new ModelRenderer((Model)this, 7, 61);
    this.beak4.setRotationPoint(0.0F, 8.9F, -13.2F);
    this.beak4.addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F);
    setRotateAngle(this.beak4, -0.17453292F, -0.0F, 0.0F);
    this.beak1 = new ModelRenderer((Model)this, 0, 58);
    this.beak1.setRotationPoint(0.0F, 8.4F, -13.5F);
    this.beak1.addBox(-0.8F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F);
    setRotateAngle(this.beak1, 0.2664757F, -0.2617586F, -0.018068846F);
    this.rightWing2 = new ModelRenderer((Model)this, 70, 0);
    this.rightWing2.setRotationPoint(-3.1F, -0.1F, -0.5F);
    this.rightWing2.addBox(-9.5F, 0.0F, 0.5F, 9.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightWing2, -0.03577925F, -0.10035643F, 0.060737457F);
    this.leftfoot1 = new ModelRenderer((Model)this, 48, 5);
    this.leftfoot1.setRotationPoint(1.8F, 14.8F, 3.9F);
    this.leftfoot1.addBox(-1.0F, 1.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftfoot1, 1.3089969F, 0.17453292F, 0.0F);
    this.rightWing4 = new ModelRenderer((Model)this, 57, 5);
    this.rightWing4.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.rightWing4.addBox(-8.8F, 0.5F, 1.0F, 12.0F, 0.0F, 6.0F, 0.0F);
    setRotateAngle(this.rightWing4, 0.017453292F, 0.045553092F, 0.0F);
    this.leftWing4 = new ModelRenderer((Model)this, 57, 5);
    this.leftWing4.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.leftWing4.addBox(-2.8F, 0.5F, 1.0F, 12.0F, 0.0F, 6.0F, 0.0F);
    setRotateAngle(this.leftWing4, 0.017453292F, -0.045553092F, 0.0F);
    this.rightleg1 = new ModelRenderer((Model)this, 35, 0);
    this.rightleg1.setRotationPoint(-2.0F, 10.5F, 2.0F);
    this.rightleg1.addBox(-1.0F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F);
    setRotateAngle(this.rightleg1, 0.7853982F, -0.17453292F, 0.0F);
    this.tuft2 = new ModelRenderer((Model)this, 22, 51);
    this.tuft2.setRotationPoint(0.0F, 7.0F, -12.5F);
    this.tuft2.addBox(-1.0F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F);
    setRotateAngle(this.tuft2, 0.6981317F, -0.0F, -0.34906584F);
    this.leftleg1 = new ModelRenderer((Model)this, 35, 0);
    this.leftleg1.setRotationPoint(2.0F, 10.5F, 2.0F);
    this.leftleg1.addBox(-2.0F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F);
    setRotateAngle(this.leftleg1, 0.7853982F, 0.17453292F, 0.0F);
    this.leftWing3 = new ModelRenderer((Model)this, 91, 0);
    this.leftWing3.setRotationPoint(12.0F, -0.6F, -1.0F);
    this.leftWing3.addBox(0.5F, 0.0F, 0.0F, 6.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftWing3, 0.0F, -0.2443461F, 0.0F);
    this.head = new ModelRenderer((Model)this, 0, 51);
    this.head.setRotationPoint(0.0F, 8.0F, -11.5F);
    this.head.addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F);
    setRotateAngle(this.head, 0.08726646F, -0.0F, 0.0F);
    this.beak2 = new ModelRenderer((Model)this, 0, 61);
    this.beak2.setRotationPoint(0.0F, 8.4F, -13.5F);
    this.beak2.addBox(-0.2F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F);
    setRotateAngle(this.beak2, 0.2664757F, 0.2617586F, 0.018068846F);
    this.beak3 = new ModelRenderer((Model)this, 7, 58);
    this.beak3.setRotationPoint(0.0F, 8.3F, -13.5F);
    this.beak3.addBox(-0.5F, -0.5F, -1.2F, 1.0F, 1.0F, 2.0F, 0.0F);
    setRotateAngle(this.beak3, 0.2617994F, -0.0F, 0.0F);
    this.rightfoot1 = new ModelRenderer((Model)this, 48, 5);
    this.rightfoot1.setRotationPoint(-1.8F, 14.8F, 3.9F);
    this.rightfoot1.addBox(-1.0F, 1.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.rightfoot1, 1.3089969F, -0.17453292F, 0.0F);
    this.body2 = new ModelRenderer((Model)this, 0, 18);
    this.body2.setRotationPoint(0.0F, 9.0F, 5.5F);
    this.body2.addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 2.0F, 0.0F);
    this.tail3 = new ModelRenderer((Model)this, 59, 18);
    this.tail3.setRotationPoint(0.0F, 8.0F, 6.0F);
    this.tail3.addBox(0.2F, -0.5F, 0.0F, 3.0F, 1.0F, 7.0F, 0.0F);
    setRotateAngle(this.tail3, 0.2617994F, 0.2617994F, 0.0F);
    this.tail1 = new ModelRenderer((Model)this, 17, 18);
    this.tail1.setRotationPoint(0.0F, 8.0F, 6.0F);
    this.tail1.addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 8.0F, 0.0F);
    setRotateAngle(this.tail1, 0.2617994F, -0.0F, 0.0F);
    this.tail2 = new ModelRenderer((Model)this, 38, 18);
    this.tail2.setRotationPoint(0.0F, 8.0F, 6.0F);
    this.tail2.addBox(-3.2F, -0.5F, 0.0F, 3.0F, 1.0F, 7.0F, 0.0F);
    setRotateAngle(this.tail2, 0.2617994F, -0.2617994F, 0.0F);
    this.leftleg2 = new ModelRenderer((Model)this, 48, 0);
    this.leftleg2.setRotationPoint(2.0F, 10.5F, 2.0F);
    this.leftleg2.addBox(-1.5F, 4.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F);
    setRotateAngle(this.leftleg2, 0.7853982F, 0.17453292F, 0.0F);
    this.tuft1 = new ModelRenderer((Model)this, 13, 51);
    this.tuft1.setRotationPoint(0.0F, 7.0F, -12.5F);
    this.tuft1.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F);
    setRotateAngle(this.tuft1, 0.6981317F, -0.0F, 0.0F);
    this.rightWing1 = new ModelRenderer((Model)this, 57, 0);
    this.rightWing1.setRotationPoint(-2.9F, 6.1F, -4.0F);
    this.rightWing1.addBox(-4.0F, -0.5F, -0.5F, 4.0F, 2.0F, 2.0F, 0.0F);
    setRotateAngle(this.rightWing1, -0.037274048F, -0.25919265F, 0.1444847F);
    this.body4 = new ModelRenderer((Model)this, 0, 36);
    this.body4.setRotationPoint(0.0F, 8.0F, -7.5F);
    this.body4.addBox(-2.5F, -2.5F, -1.0F, 5.0F, 5.0F, 2.0F, 0.0F);
    setRotateAngle(this.body4, -0.05235988F, -0.0F, 0.0F);
    this.rightWing5 = new ModelRenderer((Model)this, 94, 5);
    this.rightWing5.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.rightWing5.addBox(-6.2F, 0.5F, 1.0F, 6.0F, 0.0F, 6.0F, 0.0F);
    setRotateAngle(this.rightWing5, -0.017453292F, 0.0F, 0.0F);
    this.body5 = new ModelRenderer((Model)this, 0, 44);
    this.body5.setRotationPoint(0.0F, 8.5F, -8.7F);
    this.body5.addBox(-2.0F, -2.5F, -1.5F, 4.0F, 4.0F, 2.0F, 0.0F);
    this.leftWing2 = new ModelRenderer((Model)this, 70, 0);
    this.leftWing2.setRotationPoint(3.0F, -0.1F, -0.5F);
    this.leftWing2.addBox(0.5F, 0.0F, 0.5F, 9.0F, 1.0F, 1.0F, 0.0F);
    setRotateAngle(this.leftWing2, -0.03577925F, 0.10035643F, -0.060737457F);
    this.leftWing1 = new ModelRenderer((Model)this, 57, 0);
    this.leftWing1.setRotationPoint(2.9F, 6.1F, -4.0F);
    this.leftWing1.addBox(0.0F, -0.5F, -0.5F, 4.0F, 2.0F, 2.0F, 0.0F);
    setRotateAngle(this.leftWing1, -0.037350047F, 0.25918138F, -0.14451326F);
    this.leftWing3.addChild(this.leftWing5);
    this.rightWing1.addChild(this.rightWing3);
    this.rightWing1.addChild(this.rightWing2);
    this.rightWing2.addChild(this.rightWing4);
    this.leftWing2.addChild(this.leftWing4);
    this.leftWing1.addChild(this.leftWing3);
    this.rightWing3.addChild(this.rightWing5);
    this.leftWing1.addChild(this.leftWing2);
  }



  
  public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scaleFactor, Entity ent) {
    double[] animationWingMovement = { 5.0D, 10.0D, 15.0D, 20.0D, 25.0D, 30.0D, 35.0D, 30.0D, 25.0D, 20.0D, 15.0D, 10.0D, 5.0D, 0.0D, -5.0D, -10.0D, -15.0D, -10.0D, -5.0D, 0.0D, 5.0D };
    
    int cycleIndexFly = (int)(ent.ticksExisted * 1.85D % animationWingMovement.length);
    
    if (!Minecraft.getInstance().isGamePaused()) {
      
      this.rightWing1.rotateAngleZ = degToRad(animationWingMovement[cycleIndexFly]);
      this.leftWing1.rotateAngleZ = degToRad(animationWingMovement[cycleIndexFly]) * -1.0F;
    } 
  }

  
  protected float degToRad(double degrees) {
    return (float)(degrees * Math.PI / 180.0D);
  }

  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    matrixStack.translate(0.0D, -0.75D, 0.0D);
    
    this.body3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.tuft3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.beak4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.beak1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftfoot1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.tuft2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftleg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.beak2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.beak3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightfoot1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.tail3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.tail1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.tail2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftleg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.tuft1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightWing1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftWing1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }
}


