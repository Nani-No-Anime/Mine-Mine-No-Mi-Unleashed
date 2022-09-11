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

public class HitoDaibutsuModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer rightArm;
  private final ModelRenderer rightLeg;
  private final ModelRenderer head;
  private final ModelRenderer body;
  private final ModelRenderer leftArm;
  private final ModelRenderer leftLeg;
  
  public HitoDaibutsuModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.rightArm = new ModelRenderer((Model)this);
    this.rightArm.setRotationPoint(-9.5F, -2.0F, 0.0F);
    this.rightArm.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    
    this.rightLeg = new ModelRenderer((Model)this);
    this.rightLeg.setRotationPoint(-3.6F, 15.0F, 0.0F);
    this.rightLeg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(0.0F, -5.4F, 0.0F);
    this.head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.0F, -5.4F, 0.0F);
    this.body.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
    
    this.leftArm = new ModelRenderer((Model)this);
    this.leftArm.setRotationPoint(9.5F, -2.0F, 0.0F);
    this.leftArm.setTextureOffset(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    
    this.leftLeg = new ModelRenderer((Model)this);
    this.leftLeg.setRotationPoint(3.2F, 15.0F, 0.0F);
    this.leftLeg.setTextureOffset(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    
    this.bipedBody = this.body;
    this.bipedHead = this.head;
    this.bipedRightArm = this.rightArm;
    this.bipedLeftArm = this.leftArm;
    this.bipedRightLeg = this.rightLeg;
    this.bipedLeftLeg = this.leftLeg;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    float scale = 4.5F;
    matrixStack.push();
    matrixStack.scale(scale, scale, scale);
    matrixStack.translate(0.0D, (scale != 4.5D) ? -1.0D : -0.8D, 0.0D);
    
    matrixStack.push();
    matrixStack.scale(1.1F, 1.15F, 1.15F);
    matrixStack.translate(0.0D, -0.25D, 0.0D);
    this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    matrixStack.pop();
    
    matrixStack.push();
    matrixStack.scale(2.5F, 2.0F, 4.0F);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    matrixStack.pop();
    
    matrixStack.push();
    matrixStack.scale(1.75F, 1.75F, 1.75F);
    matrixStack.translate(0.2D, -0.1D, 0.0D);
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    matrixStack.pop();
    
    matrixStack.push();
    matrixStack.scale(1.75F, 1.75F, 1.75F);
    matrixStack.translate(-0.2D, -0.1D, 0.0D);
    this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    matrixStack.pop();
    
    matrixStack.push();
    matrixStack.scale(1.75F, 1.35F, 1.75F);
    matrixStack.translate(0.05D, -0.4D, 0.0D);
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    matrixStack.pop();
    
    matrixStack.push();
    matrixStack.scale(1.75F, 1.35F, 1.75F);
    matrixStack.translate(-0.05D, -0.4D, 0.0D);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    matrixStack.pop();
    matrixStack.pop();
  }



  
  public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
    
    if (entity.isSneaking()) {
      
      this.body.rotateAngleX = 0.2F;
      this.body.rotationPointZ -= 2.0F;
      this.body.rotationPointY -= 0.2F;
      this.rightArm.rotateAngleX += 0.3F;
      this.rightArm.rotationPointZ -= 3.5F;
      this.rightArm.rotationPointY += 1.2F;
      this.leftArm.rotateAngleX += 0.3F;
      this.leftArm.rotationPointZ -= 3.5F;
      this.leftArm.rotationPointY += 1.2F;
      this.rightLeg.rotationPointZ = 1.0F;
      this.rightLeg.rotationPointY = 14.0F;
      this.leftLeg.rotationPointZ = 1.0F;
      this.leftLeg.rotationPointY = 14.0F;
      this.head.rotationPointZ = -6.0F;
      this.head.rotationPointY = -5.0F;
    } 

    
    this.swingProgress = entity.swingProgress;
    boolean isBlackLeg = EntityStatsCapability.get(entity).isBlackLeg();
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
        
        this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
        this.rightArm.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 12.0F;
        this.rightArm.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 9.0F;
        this.rightArm.rotateAngleY += this.body.rotateAngleY;
        this.leftArm.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
        this.leftArm.rotateAngleY -= this.body.rotateAngleY;
        this.leftArm.rotateAngleX -= this.body.rotateAngleY;
        float f1 = 1.0F - this.swingProgress;
        f1 *= f1;
        f1 *= f1;
        f1 = 1.0F - f1;
        float f2 = MathHelper.sin(f1 * 3.1415927F);
        float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
        this.rightArm.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.2D + f3);
        this.rightArm.rotateAngleY += this.body.rotateAngleY * 2.0F;
        this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
      } 
    }
  }

  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }


  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(0.2D, 0.3D, 0.0D);
      this.rightArm.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
    }
    else {
      
      matrixStack.translate(-0.2D, 0.3D, 0.0D);
      this.leftArm.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
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
    matrixStack.translate((side == HandSide.RIGHT) ? -0.6D : 0.6D, -0.5D, -0.2D);
  }
}


