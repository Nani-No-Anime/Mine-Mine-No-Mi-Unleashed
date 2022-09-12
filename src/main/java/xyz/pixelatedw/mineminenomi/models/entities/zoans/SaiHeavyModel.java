package xyz.pixelatedw.mineminenomi.models.entities.zoans;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;

public class SaiHeavyModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer body;
  private final ModelRenderer rightLeg;
  private final ModelRenderer rightLeg2;
  private final ModelRenderer rightLeg3;
  private final ModelRenderer rightArm;
  private final ModelRenderer rightArm2;
  private final ModelRenderer leftLeg;
  private final ModelRenderer leftLeg2;
  private final ModelRenderer leftLeg3;
  private final ModelRenderer leftArm;
  private final ModelRenderer leftArm2;
  private final ModelRenderer neck;
  private final ModelRenderer head;
  private final ModelRenderer lowerHead;
  private final ModelRenderer upperHead;
  private final ModelRenderer middleHead;
  private final ModelRenderer horn1;
  private final ModelRenderer horn2;
  private final ModelRenderer horn3;
  private final ModelRenderer horn4;
  private final ModelRenderer rightEar;
  private final ModelRenderer leftEar;
  
  public SaiHeavyModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 128;
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.0F, 4.6667F, 0.0F);
    this.body.setTextureOffset(0, 0).addBox(-4.0F, -10.6667F, -8.5F, 10.0F, 12.0F, 17.0F, 0.0F, false);
    this.body.setTextureOffset(0, 29).addBox(-6.0F, -9.6667F, -6.5F, 11.0F, 15.0F, 13.0F, 0.0F, false);
    this.body.setTextureOffset(37, 0).addBox(-5.0F, 4.3333F, -5.5F, 9.0F, 5.0F, 11.0F, 0.0F, false);
    
    this.rightLeg = new ModelRenderer((Model)this);
    this.rightLeg.setRotationPoint(1.45F, 12.5F, -3.5F);
    setRotationAngle(this.rightLeg, 0.0F, 0.0F, 0.1745F);
    this.rightLeg.setTextureOffset(66, 0).addBox(-4.45F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);
    
    this.rightLeg2 = new ModelRenderer((Model)this);
    this.rightLeg2.setRotationPoint(-2.3908F, 6.3707F, 0.0F);
    this.rightLeg.addChild(this.rightLeg2);
    setRotationAngle(this.rightLeg2, 0.0F, 0.0F, -0.3054F);
    this.rightLeg2.setTextureOffset(72, 11).addBox(-1.5092F, -1.1207F, -2.5F, 4.0F, 6.0F, 5.0F, -0.01F, false);
    
    this.rightLeg3 = new ModelRenderer((Model)this);
    this.rightLeg3.setRotationPoint(1.5408F, 5.1293F, 0.0F);
    this.rightLeg2.addChild(this.rightLeg3);
    setRotationAngle(this.rightLeg3, 0.0F, 0.0F, 0.1309F);
    this.rightLeg3.setTextureOffset(62, 28).addBox(-3.5F, -1.0F, -2.5F, 7.0F, 2.0F, 5.0F, 0.0F, true);
    
    this.rightArm = new ModelRenderer((Model)this);
    this.rightArm.setRotationPoint(1.0F, -1.25F, -7.5F);
    setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.1745F);
    this.rightArm.setTextureOffset(0, 57).addBox(-3.0F, -3.25F, -5.5F, 6.0F, 10.0F, 5.0F, 0.0F, false);
    
    this.rightArm2 = new ModelRenderer((Model)this);
    this.rightArm2.setRotationPoint(-1.1263F, 7.7688F, -3.0F);
    this.rightArm.addChild(this.rightArm2);
    setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.2618F);
    this.rightArm2.setTextureOffset(0, 72).addBox(-1.0737F, -1.5188F, -2.0F, 5.0F, 7.0F, 4.0F, 0.0F, false);
    
    this.leftLeg = new ModelRenderer((Model)this);
    this.leftLeg.setRotationPoint(-0.8F, 12.0F, 3.5F);
    setRotationAngle(this.leftLeg, 0.0F, 0.0F, 0.1745F);
    this.leftLeg.setTextureOffset(66, 0).addBox(-2.2F, 0.25F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, true);
    
    this.leftLeg2 = new ModelRenderer((Model)this);
    this.leftLeg2.setRotationPoint(-0.6408F, 6.1207F, 0.0F);
    this.leftLeg.addChild(this.leftLeg2);
    setRotationAngle(this.leftLeg2, 0.0F, 0.0F, -0.3054F);
    this.leftLeg2.setTextureOffset(72, 11).addBox(-1.0592F, -0.6207F, -2.5F, 4.0F, 6.0F, 5.0F, -0.01F, true);
    
    this.leftLeg3 = new ModelRenderer((Model)this);
    this.leftLeg3.setRotationPoint(1.5408F, 4.8793F, 0.0F);
    this.leftLeg2.addChild(this.leftLeg3);
    setRotationAngle(this.leftLeg3, 0.0F, 0.0F, 0.1309F);
    this.leftLeg3.setTextureOffset(62, 28).addBox(-3.0F, -0.25F, -2.5F, 7.0F, 2.0F, 5.0F, 0.0F, true);
    
    this.leftArm = new ModelRenderer((Model)this);
    this.leftArm.setRotationPoint(1.0F, -2.0F, 7.5F);
    setRotationAngle(this.leftArm, 0.0F, 0.0F, 0.1745F);
    this.leftArm.setTextureOffset(0, 57).addBox(-3.0F, -2.5F, 0.5F, 6.0F, 10.0F, 5.0F, 0.0F, true);
    
    this.leftArm2 = new ModelRenderer((Model)this);
    this.leftArm2.setRotationPoint(1.1883F, 6.7568F, 3.0F);
    this.leftArm.addChild(this.leftArm2);
    setRotationAngle(this.leftArm2, 0.0F, 3.1416F, -0.2618F);
    this.leftArm2.setTextureOffset(0, 72).addBox(-1.1383F, -0.7568F, -2.0F, 5.0F, 7.0F, 4.0F, 0.0F, true);
    
    this.neck = new ModelRenderer((Model)this);
    this.neck.setRotationPoint(-1.0F, 24.5F, 0.0F);
    setRotationAngle(this.neck, 0.0F, 0.0F, 0.2182F);
    this.neck.setTextureOffset(54, 16).addBox(-8.6F, -33.25F, -3.5F, 4.0F, 5.0F, 7.0F, 0.0F, false);
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(-1.0F, 24.0F, 0.0F);
    this.head.setTextureOffset(0, 0).addBox(2.0F, -34.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.lowerHead = new ModelRenderer((Model)this);
    this.lowerHead.setRotationPoint(4.1283F, -31.9102F, 0.0F);
    this.head.addChild(this.lowerHead);
    setRotationAngle(this.lowerHead, 0.0F, 0.0F, -0.4363F);
    this.lowerHead.setTextureOffset(48, 42).addBox(-4.0F, -4.0F, -3.5F, 8.0F, 8.0F, 7.0F, 0.01F, false);
    
    this.upperHead = new ModelRenderer((Model)this);
    this.upperHead.setRotationPoint(9.9143F, -30.4729F, 0.0F);
    this.head.addChild(this.upperHead);
    setRotationAngle(this.upperHead, 0.0F, 0.0F, 0.48F);
    this.upperHead.setTextureOffset(35, 29).addBox(-5.0F, -3.0F, -3.5F, 10.0F, 6.0F, 7.0F, 0.02F, false);
    
    this.middleHead = new ModelRenderer((Model)this);
    this.middleHead.setRotationPoint(7.0387F, -34.7554F, 0.0F);
    this.head.addChild(this.middleHead);
    setRotationAngle(this.middleHead, 0.0F, 0.0F, 0.5236F);
    this.middleHead.setTextureOffset(44, 57).addBox(-2.5F, -1.0F, -3.5F, 5.0F, 2.0F, 7.0F, 0.0F, false);
    
    this.horn1 = new ModelRenderer((Model)this);
    this.horn1.setRotationPoint(15.3463F, -32.0735F, 0.0F);
    this.head.addChild(this.horn1);
    setRotationAngle(this.horn1, 0.0F, 0.0F, 0.6109F);
    this.horn1.setTextureOffset(0, 5).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.horn2 = new ModelRenderer((Model)this);
    this.horn2.setRotationPoint(16.3124F, -34.0707F, 0.0F);
    this.head.addChild(this.horn2);
    setRotationAngle(this.horn2, 0.0F, 0.0F, 0.3491F);
    this.horn2.setTextureOffset(0, 0).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, 0.01F, false);
    
    this.horn3 = new ModelRenderer((Model)this);
    this.horn3.setRotationPoint(12.8548F, -32.4177F, 0.0F);
    this.head.addChild(this.horn3);
    setRotationAngle(this.horn3, 0.0F, 0.0F, 0.6109F);
    this.horn3.setTextureOffset(6, 3).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.horn4 = new ModelRenderer((Model)this);
    this.horn4.setRotationPoint(13.4789F, -33.4751F, 0.0F);
    this.head.addChild(this.horn4);
    setRotationAngle(this.horn4, 0.0F, 0.0F, 0.3491F);
    this.horn4.setTextureOffset(8, 0).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.01F, false);
    
    this.rightEar = new ModelRenderer((Model)this);
    this.rightEar.setRotationPoint(6.3332F, -37.2594F, -2.8669F);
    this.head.addChild(this.rightEar);
    setRotationAngle(this.rightEar, 0.1745F, 0.0F, 0.4363F);
    this.rightEar.setTextureOffset(0, 9).addBox(-0.5F, -1.5F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, true);
    
    this.leftEar = new ModelRenderer((Model)this);
    this.leftEar.setRotationPoint(6.3446F, -37.2837F, 2.9562F);
    this.head.addChild(this.leftEar);
    setRotationAngle(this.leftEar, -0.1745F, 0.0F, 0.4363F);
    this.leftEar.setTextureOffset(0, 9).addBox(-0.5F, -1.5F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, true);
    
    this.bipedBody = this.body;
    this.bipedHead = this.head;
    this.bipedRightArm = this.rightArm;
    this.bipedLeftArm = this.leftArm;
    this.bipedRightLeg = this.rightLeg;
    this.bipedLeftLeg = this.leftLeg;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.neck.render(matrixStack, buffer, packedLight, packedOverlay);
    this.head.render(matrixStack, buffer, packedLight, packedOverlay);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    float f = 1.0F;
    this.rightArm.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
    this.leftArm.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
    this.rightLeg.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
    this.leftLeg.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
    if (!entity.getHeldItemMainhand().isEmpty()) {
      this.rightArm.rotateAngleZ += -0.15F;
    }
    
    this.swingProgress = ((LivingEntity)entity).swingProgress;
    boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
    if (this.swingProgress > 0.0F)
    {
      if (isBlackLeg) {
        
        this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
        this.leftLeg.rotateAngleY += this.body.rotateAngleY;
        this.leftLeg.rotateAngleX += this.body.rotateAngleY;
        this.rightLeg.rotateAngleY += this.body.rotateAngleY * 3.0F;
        this.rightLeg.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -1.9F;
      }
      else {
        
        this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
        this.leftArm.rotateAngleY += this.body.rotateAngleY;
        this.leftArm.rotateAngleX += this.body.rotateAngleY;
        float f1 = 1.0F - this.swingProgress;
        f1 *= f1;
        f1 *= f1;
        f1 = 1.0F - f1;
        float f2 = MathHelper.sin(f1 * 3.1415927F);
        float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * 0.75F;
        this.rightArm.rotateAngleX -= (float)(this.rightArm.rotateAngleX - f2 * -0.8D + f3);
        this.rightArm.rotateAngleY += this.body.rotateAngleY * 1.0F;
        this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -1.9F;
      } 
    }
  }


  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    super.translateHand(side, matrixStack);
    matrixStack.translate(-0.1D, 0.3D, -0.12D);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees((side == HandSide.RIGHT) ? -20.0F : 20.0F));
    matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
  }


  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(-0.4D, -0.2D, -0.3D);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-270.0F));
      matrixStack.rotate(Vector3f.ZP.rotationDegrees(10.0F));
      this.rightArm2.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    }
    else {
      
      matrixStack.translate(0.4D, -0.2D, -0.3D);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-270.0F));
      matrixStack.rotate(Vector3f.ZP.rotationDegrees(10.0F));
      this.leftArm2.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    } 
  }


  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.rotate(Vector3f.YP.rotationDegrees(50.0F));
      matrixStack.rotate(Vector3f.XP.rotationDegrees(40.0F));
      matrixStack.translate(-0.1D, -0.9D, 0.2D);
      this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    }
    else {
      
      matrixStack.rotate(Vector3f.YP.rotationDegrees(150.0F));
      matrixStack.rotate(Vector3f.XP.rotationDegrees(-20.0F));
      matrixStack.translate(-0.1D, -0.9D, -0.2D);
      this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    } 
  }
}


