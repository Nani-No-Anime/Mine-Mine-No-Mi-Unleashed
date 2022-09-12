package xyz.pixelatedw.mineminenomi.models.entities.zoans;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;

public class KameGuardModel<T extends LivingEntity>
  extends ZoanMorphModel<T> {
  private final ModelRenderer body;
  private final ModelRenderer frontLeftFin;
  private final ModelRenderer frontRightFin;
  private final ModelRenderer backRightFin;
  private final ModelRenderer backLeftFin;
  private final ModelRenderer head;
  private final ModelRenderer shell;
  
  public KameGuardModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.0F, 22.0F, -1.0F);
    
    this.frontLeftFin = new ModelRenderer((Model)this);
    this.frontLeftFin.setRotationPoint(-1.0F, 0.0F, 0.0F);
    this.body.addChild(this.frontLeftFin);
    this.frontLeftFin.setTextureOffset(33, 8).addBox(5.0F, 0.85F, -4.0F, 6.0F, 0.0F, 3.0F, 0.0F, false);
    
    this.frontRightFin = new ModelRenderer((Model)this);
    this.frontRightFin.setRotationPoint(-1.0F, 0.0F, 0.0F);
    this.body.addChild(this.frontRightFin);
    this.frontRightFin.setTextureOffset(28, 23).addBox(-9.0F, 1.0F, -4.0F, 6.0F, 0.0F, 3.0F, 0.0F, false);
    
    this.backRightFin = new ModelRenderer((Model)this);
    this.backRightFin.setRotationPoint(-1.0F, 0.0F, 0.0F);
    this.body.addChild(this.backRightFin);
    this.backRightFin.setTextureOffset(27, 29).addBox(-3.0F, 1.0F, 6.0F, 3.0F, 0.0F, 6.0F, 0.0F, false);
    
    this.backLeftFin = new ModelRenderer((Model)this);
    this.backLeftFin.setRotationPoint(3.0F, 1.0F, 6.0F);
    this.body.addChild(this.backLeftFin);
    this.backLeftFin.setTextureOffset(28, 16).addBox(-2.0F, 0.0F, 0.0F, 3.0F, 0.0F, 6.0F, 0.0F, false);
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(0.0F, 0.0F, -4.0F);
    this.body.addChild(this.head);
    this.head.setTextureOffset(33, 0).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
    
    this.shell = new ModelRenderer((Model)this);
    this.shell.setRotationPoint(-1.0F, 0.0F, 0.0F);
    this.body.addChild(this.shell);
    this.shell.setTextureOffset(0, 0).addBox(-4.0F, -2.0F, -5.0F, 10.0F, 3.0F, 12.0F, 0.0F, false);
    this.shell.setTextureOffset(0, 29).addBox(-3.0F, -3.0F, -4.0F, 8.0F, 1.0F, 10.0F, 0.0F, false);
    this.shell.setTextureOffset(0, 16).addBox(-3.0F, 1.0F, -4.0F, 8.0F, 1.0F, 11.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    if (entity.isCrouching()) {
      
      this.head.showModel = false;
      this.frontRightFin.showModel = false;
      this.frontLeftFin.showModel = false;
      this.backRightFin.showModel = false;
      this.backLeftFin.showModel = false;
    } 

    
    this.frontRightFin.rotateAngleX = MathHelper.cos(limbSwing * 0.4F) * 0.1F;
    this.frontRightFin.rotateAngleZ = MathHelper.sin(limbSwing * 0.4F) * 0.1F;
    
    this.frontLeftFin.rotateAngleX = -MathHelper.cos(limbSwing * 0.4F) * 0.1F;
    this.frontLeftFin.rotateAngleZ = MathHelper.sin(limbSwing * 0.4F) * 0.1F;
    
    this.backRightFin.rotateAngleY = MathHelper.cos(limbSwing * 0.4F) * 0.1F;
    this.backRightFin.rotateAngleZ = MathHelper.sin(limbSwing * 0.4F) * 0.1F;
    
    this.backLeftFin.rotateAngleY = MathHelper.cos(limbSwing * 0.4F) * 0.2F;
    this.backLeftFin.rotateAngleZ = -MathHelper.sin(limbSwing * 0.4F) * 0.1F;

    
    this.swingProgress = ((LivingEntity)entity).swingProgress;
    if (this.swingProgress > 0.0F) {
      
      this.head.rotateAngleY += this.body.rotateAngleY;
      float f1 = 1.0F - this.swingProgress;
      f1 *= f1;
      f1 *= f1;
      f1 = 1.0F - f1;
      float f2 = MathHelper.sin(f1 * 3.1415927F);
      float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.1F) * 0.15F;
      this.head.rotateAngleX = (float)(this.head.rotateAngleX - f2 * 1.5D + f3);
      this.head.rotateAngleZ -= MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
    } 
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    if (!this.head.showModel)
      matrixStack.scale(-10.0F, -10.0F, -10.0F); 
    matrixStack.translate(-0.2D, 1.2D, -0.01D);
    this.head.translateRotate(matrixStack);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
    matrixStack.rotate(Vector3f.XP.rotationDegrees(280.0F));
    matrixStack.translate(0.3D, -0.3D, -0.2D);
  }
  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
}


