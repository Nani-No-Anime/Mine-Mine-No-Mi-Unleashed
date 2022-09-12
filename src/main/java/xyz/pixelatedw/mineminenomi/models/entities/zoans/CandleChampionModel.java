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

public class CandleChampionModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer body;
  private final ModelRenderer spikes;
  private final ModelRenderer leftSpikes;
  private final ModelRenderer leftSpike1;
  private final ModelRenderer leftSpike2;
  private final ModelRenderer leftSpike3;
  private final ModelRenderer leftSpike4;
  private final ModelRenderer rightSpikes;
  private final ModelRenderer rightSpike1;
  private final ModelRenderer rightSpike2;
  private final ModelRenderer rightSpike3;
  private final ModelRenderer rightSpike4;
  private final ModelRenderer rightArm;
  private final ModelRenderer leftArm;
  private final ModelRenderer leftLeg;
  private final ModelRenderer leftLeg2_r1;
  private final ModelRenderer leftLeg1_r1;
  private final ModelRenderer rightLeg;
  private final ModelRenderer rightLeg2_r1;
  private final ModelRenderer rightLeg1_r1;
  
  public CandleChampionModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 128;
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.0F, 24.0F, 0.0F);
    this.body.setTextureOffset(0, 0).addBox(-8.5F, -31.1F, -8.5F, 17.0F, 11.0F, 12.0F, 0.0F, false);
    this.body.setTextureOffset(0, 23).addBox(-4.5F, -32.1F, -6.5F, 9.0F, 10.0F, 11.0F, 0.0F, false);
    this.body.setTextureOffset(0, 44).addBox(-11.5F, -30.5F, -8.0F, 3.0F, 10.0F, 11.0F, 0.0F, false);
    this.body.setTextureOffset(0, 44).addBox(8.5F, -30.5F, -8.0F, 3.0F, 10.0F, 11.0F, 0.0F, false);
    this.body.setTextureOffset(46, 23).addBox(-3.0F, -26.1F, -7.0F, 6.0F, 7.0F, 9.0F, 0.0F, false);
    this.body.setTextureOffset(67, 21).addBox(-2.5F, -20.1F, -5.5F, 5.0F, 5.0F, 6.0F, 0.0F, false);
    this.body.setTextureOffset(46, 0).addBox(-4.5F, -15.1F, -6.5F, 9.0F, 1.0F, 8.0F, 0.0F, false);
    this.body.setTextureOffset(0, 66).addBox(-4.0F, -15.0F, -6.0F, 8.0F, 4.0F, 7.0F, 0.0F, false);
    this.body.setTextureOffset(28, 54).addBox(-2.5F, -13.5F, -6.5F, 5.0F, 3.0F, 8.0F, 0.0F, false);
    
    this.spikes = new ModelRenderer((Model)this);
    this.spikes.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.body.addChild(this.spikes);
    
    this.leftSpikes = new ModelRenderer((Model)this);
    this.leftSpikes.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.spikes.addChild(this.leftSpikes);
    
    this.leftSpike1 = new ModelRenderer((Model)this);
    this.leftSpike1.setRotationPoint(-10.0F, -32.5F, -2.5F);
    this.leftSpikes.addChild(this.leftSpike1);
    this.leftSpike1.setTextureOffset(0, 8).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    this.leftSpike1.setTextureOffset(8, 8).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftSpike2 = new ModelRenderer((Model)this);
    this.leftSpike2.setRotationPoint(-10.0F, -18.5F, -2.5F);
    this.leftSpikes.addChild(this.leftSpike2);
    this.leftSpike2.setTextureOffset(0, 8).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    this.leftSpike2.setTextureOffset(8, 8).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftSpike3 = new ModelRenderer((Model)this);
    this.leftSpike3.setRotationPoint(-10.0F, -25.5F, -9.5F);
    this.leftSpikes.addChild(this.leftSpike3);
    setRotationAngle(this.leftSpike3, -1.5708F, 0.0F, 0.0F);
    this.leftSpike3.setTextureOffset(0, 8).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    this.leftSpike3.setTextureOffset(8, 8).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftSpike4 = new ModelRenderer((Model)this);
    this.leftSpike4.setRotationPoint(-10.0F, -25.5F, 4.5F);
    this.leftSpikes.addChild(this.leftSpike4);
    setRotationAngle(this.leftSpike4, 1.5708F, 0.0F, 0.0F);
    this.leftSpike4.setTextureOffset(0, 8).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    this.leftSpike4.setTextureOffset(8, 8).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightSpikes = new ModelRenderer((Model)this);
    this.rightSpikes.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.spikes.addChild(this.rightSpikes);
    
    this.rightSpike1 = new ModelRenderer((Model)this);
    this.rightSpike1.setRotationPoint(10.0F, -32.5F, -2.5F);
    this.rightSpikes.addChild(this.rightSpike1);
    this.rightSpike1.setTextureOffset(0, 8).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    this.rightSpike1.setTextureOffset(8, 8).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightSpike2 = new ModelRenderer((Model)this);
    this.rightSpike2.setRotationPoint(10.0F, -18.5F, -2.5F);
    this.rightSpikes.addChild(this.rightSpike2);
    this.rightSpike2.setTextureOffset(0, 8).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    this.rightSpike2.setTextureOffset(8, 8).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightSpike3 = new ModelRenderer((Model)this);
    this.rightSpike3.setRotationPoint(10.0F, -25.5F, -9.5F);
    this.rightSpikes.addChild(this.rightSpike3);
    setRotationAngle(this.rightSpike3, -1.5708F, 0.0F, 0.0F);
    this.rightSpike3.setTextureOffset(0, 8).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    this.rightSpike3.setTextureOffset(8, 8).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightSpike4 = new ModelRenderer((Model)this);
    this.rightSpike4.setRotationPoint(10.0F, -25.5F, 4.5F);
    this.rightSpikes.addChild(this.rightSpike4);
    setRotationAngle(this.rightSpike4, 1.5708F, 0.0F, 0.0F);
    this.rightSpike4.setTextureOffset(0, 8).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    this.rightSpike4.setTextureOffset(8, 8).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightArm = new ModelRenderer((Model)this);
    this.rightArm.setRotationPoint(12.0F, -0.5F, -2.5F);
    setRotationAngle(this.rightArm, 0.0F, 0.0F, -0.3927F);
    this.rightArm.setTextureOffset(0, 44).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, false);
    this.rightArm.setTextureOffset(17, 49).addBox(-1.5F, 5.5F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
    this.rightArm.setTextureOffset(48, 70).addBox(-3.0F, 7.5F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
    this.rightArm.setTextureOffset(35, 68).addBox(-3.5F, 8.5F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
    
    this.leftArm = new ModelRenderer((Model)this);
    this.leftArm.setRotationPoint(-11.5F, -1.5F, -2.5F);
    setRotationAngle(this.leftArm, 0.0F, 0.0F, 0.3927F);
    this.leftArm.setTextureOffset(0, 44).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, false);
    this.leftArm.setTextureOffset(17, 49).addBox(-1.5F, 6.5F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
    this.leftArm.setTextureOffset(48, 70).addBox(-3.0F, 8.5F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
    this.leftArm.setTextureOffset(35, 68).addBox(2.5F, 9.5F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
    
    this.leftLeg = new ModelRenderer((Model)this);
    this.leftLeg.setRotationPoint(-3.5F, 11.0F, -2.5F);
    this.leftLeg.setTextureOffset(17, 44).addBox(-3.5F, 5.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
    this.leftLeg.setTextureOffset(54, 58).addBox(-5.0F, 7.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
    this.leftLeg.setTextureOffset(47, 45).addBox(-5.0F, 7.0F, 5.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
    this.leftLeg.setTextureOffset(72, 64).addBox(-4.5F, 8.0F, 2.0F, 5.0F, 5.0F, 6.0F, 0.0F, false);
    
    this.leftLeg2_r1 = new ModelRenderer((Model)this);
    this.leftLeg2_r1.setRotationPoint(-1.4463F, 3.8114F, 0.0F);
    this.leftLeg.addChild(this.leftLeg2_r1);
    setRotationAngle(this.leftLeg2_r1, 0.0F, 0.0F, 0.1745F);
    this.leftLeg2_r1.setTextureOffset(35, 45).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    
    this.leftLeg1_r1 = new ModelRenderer((Model)this);
    this.leftLeg1_r1.setRotationPoint(-0.293F, 0.1536F, 0.0F);
    this.leftLeg.addChild(this.leftLeg1_r1);
    setRotationAngle(this.leftLeg1_r1, 0.0F, 0.0F, 0.4363F);
    this.leftLeg1_r1.setTextureOffset(30, 45).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    
    this.rightLeg = new ModelRenderer((Model)this);
    this.rightLeg.setRotationPoint(3.5F, 11.0F, -2.5F);
    this.rightLeg.setTextureOffset(17, 44).addBox(0.5F, 5.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
    this.rightLeg.setTextureOffset(54, 58).addBox(-1.0F, 7.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
    this.rightLeg.setTextureOffset(47, 45).addBox(-1.0F, 7.0F, 5.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
    this.rightLeg.setTextureOffset(72, 64).addBox(-0.5F, 8.0F, 2.0F, 5.0F, 5.0F, 6.0F, 0.0F, false);
    
    this.rightLeg2_r1 = new ModelRenderer((Model)this);
    this.rightLeg2_r1.setRotationPoint(1.6087F, 3.9529F, 0.0F);
    this.rightLeg.addChild(this.rightLeg2_r1);
    setRotationAngle(this.rightLeg2_r1, 0.0F, 0.0F, -0.1745F);
    this.rightLeg2_r1.setTextureOffset(35, 45).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
    
    this.rightLeg1_r1 = new ModelRenderer((Model)this);
    this.rightLeg1_r1.setRotationPoint(0.4555F, 0.2951F, 0.0F);
    this.rightLeg.addChild(this.rightLeg1_r1);
    setRotationAngle(this.rightLeg1_r1, 0.0F, 0.0F, -0.4363F);
    this.rightLeg1_r1.setTextureOffset(30, 45).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    matrixStack.push();
    matrixStack.scale(2.0F, 2.0F, 2.0F);
    matrixStack.translate(0.0D, -0.7D, 0.0D);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    matrixStack.pop();
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    float f = 1.0F;
    this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
    this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
    this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
    this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
    if (!entity.getHeldItemMainhand().isEmpty()) {
      this.rightArm.rotateAngleX += -0.15F;
    }
    
    this.swingProgress = ((LivingEntity)entity).swingProgress;
    if (this.swingProgress > 0.0F) {
      
      this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
      this.leftArm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
      this.leftArm.rotateAngleY += this.body.rotateAngleY;
      this.leftArm.rotateAngleX += this.body.rotateAngleY;
      float f1 = 1.0F - this.swingProgress;
      f1 *= f1;
      f1 *= f1;
      f1 = 1.0F - f1;
      float f2 = MathHelper.sin(f1 * 3.1415927F);
      float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * 0.75F;
      this.rightArm.rotateAngleX = -((float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3));
      this.rightArm.rotateAngleY += this.body.rotateAngleY * 2.0F;
      this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.9F;
    } 
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }


  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(-1.5D, -0.1D, 0.1D);
      matrixStack.rotate(Vector3f.XP.rotationDegrees(10.0F));
      this.rightArm.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    }
    else {
      
      matrixStack.translate(1.5D, -0.1D, 0.1D);
      matrixStack.rotate(Vector3f.XP.rotationDegrees(-10.0F));
      this.leftArm.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    } 
  }


  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.rotate(Vector3f.XP.rotationDegrees(30.0F));
      matrixStack.translate(0.2D, -1.2D, 0.3D);
      matrixStack.scale(1.5F, 1.5F, 1.5F);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(130.0F));
      this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    }
    else {
      
      matrixStack.rotate(Vector3f.XP.rotationDegrees(30.0F));
      matrixStack.translate(-0.3D, -1.0D, -0.3D);
      matrixStack.scale(1.5F, 1.5F, 1.5F);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-130.0F));
      this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    } 
  }


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    super.translateHand(side, matrixStack);
    matrixStack.translate((side == HandSide.RIGHT) ? -0.6D : 0.6D, -0.5D, -0.2D);
  }
}


