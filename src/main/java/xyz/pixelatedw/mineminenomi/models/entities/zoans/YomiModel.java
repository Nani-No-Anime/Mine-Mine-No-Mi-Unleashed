package xyz.pixelatedw.mineminenomi.models.entities.zoans;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;

public class YomiModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  public ModelRenderer head;
  public ModelRenderer body;
  public ModelRenderer rightArm;
  public ModelRenderer leftArm;
  public ModelRenderer rightLeg;
  public ModelRenderer leftLeg;
  public ModelRenderer afro;
  
  public YomiModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.head = new ModelRenderer((Model)this, 0, 0);
    this.head.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F);
    this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.body = new ModelRenderer((Model)this, 16, 16);
    this.body.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F);
    this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.rightArm = new ModelRenderer((Model)this, 40, 16);
    this.rightArm.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F);
    this.rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
    this.leftArm = new ModelRenderer((Model)this, 40, 16);
    this.leftArm.mirror = true;
    this.leftArm.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F);
    this.leftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
    this.rightLeg = new ModelRenderer((Model)this, 0, 16);
    this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F);
    this.rightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
    this.leftLeg = new ModelRenderer((Model)this, 0, 16);
    this.leftLeg.mirror = true;
    this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F);
    this.leftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
    
    this.bipedBody = this.body;
    this.bipedHead = this.head;
    this.bipedRightArm = this.rightArm;
    this.bipedLeftArm = this.leftArm;
    this.bipedRightLeg = this.rightLeg;
    this.bipedLeftLeg = this.leftLeg;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    boolean flag = (entity.getTicksElytraFlying() > 4);
    boolean flag1 = entity.isActualySwimming();
    this.head.rotateAngleY = netHeadYaw * 0.017453292F;
    if (flag) {
      this.head.rotateAngleX = -0.7853982F;
    } else if (this.swimAnimation > 0.0F) {
      
      if (flag1) {
        this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, -0.7853982F, this.swimAnimation);
      } else {
        this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
      } 
    } else {
      
      this.head.rotateAngleX = headPitch * 0.017453292F;
      if (this.head.rotateAngleX > 0.6D) {
        this.head.rotateAngleX = 0.6F;
      }
    } 
    
    float f = 1.0F;
    this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
    this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
    this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
    this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
    if (!entity.getHeldItemMainhand().isEmpty()) {
      this.rightArm.rotateAngleX += -0.15F;
    }
    
    this.swingProgress = ((LivingEntity)entity).swingProgress;
    boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
    if (this.swingProgress > 0.0F)
    {
      if (isBlackLeg) {
        
        this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
        this.rightLeg.rotateAngleY += this.body.rotateAngleY;
        this.leftLeg.rotateAngleY += this.body.rotateAngleY;
        float f1 = 1.0F - this.swingProgress;
        f1 *= f1;
        f1 *= f1;
        f1 = 1.0F - f1;
        float f2 = MathHelper.sin(f1 * 3.1415927F);
        float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
        this.rightLeg.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
        this.rightLeg.rotateAngleY += this.body.rotateAngleY * 2.0F;
      }
      else {
        
        this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
        this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 4.0F;
        this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 4.0F;
        this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
        this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
        this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
        float f1 = 1.0F - this.swingProgress;
        f1 *= f1;
        f1 *= f1;
        f1 = 1.0F - f1;
        float f2 = MathHelper.sin(f1 * 3.1415927F);
        float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
        this.bipedRightArm.rotateAngleX = (float)(this.bipedRightArm.rotateAngleX - f2 * 1.2D + f3);
        this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
        this.bipedRightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
      } 
    }
    
    if (entity.isSneaking()) {
      
      this.bipedBody.rotateAngleX = 0.5F;
      this.bipedBody.rotationPointZ -= 4.0F;
      this.bipedRightArm.rotateAngleX += 0.4F;
      this.bipedRightArm.rotationPointZ -= 2.5F;
      this.bipedLeftArm.rotateAngleX += 0.4F;
      this.bipedLeftArm.rotationPointZ -= 2.5F;
      this.bipedRightLeg.rotationPointZ = 1.0F;
      this.bipedRightLeg.rotationPointY = 10.0F;
      this.bipedLeftLeg.rotationPointZ = 1.0F;
      this.bipedLeftLeg.rotationPointY = 10.0F;
      this.bipedHead.rotationPointZ = -4.0F;
      this.bipedHead.rotationPointY = 1.0F;
    } 
  }

  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }


  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(-0.2D, 0.0D, -0.0D);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
      matrixStack.rotate(Vector3f.ZP.rotationDegrees(10.0F));
      this.rightArm.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
    else {
      
      matrixStack.translate(0.2D, 0.0D, -0.2D);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
      matrixStack.rotate(Vector3f.ZP.rotationDegrees(-10.0F));
      this.leftArm.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    } 
  }


  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(0.0D, -1.2D, 0.3D);
      matrixStack.scale(1.5F, 1.5F, 1.5F);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-60.0F));
      this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    }
    else {
      
      matrixStack.translate(0.0D, -1.2D, 0.3D);
      matrixStack.scale(1.5F, 1.5F, 1.5F);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(60.0F));
      this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    } 
  }


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    super.translateHand(side, matrixStack);
    matrixStack.translate((side == HandSide.RIGHT) ? 0.06D : -0.06D, 0.1D, 0.0D);
  }
}


