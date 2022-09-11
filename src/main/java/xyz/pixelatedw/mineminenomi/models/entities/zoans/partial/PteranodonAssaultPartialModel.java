package xyz.pixelatedw.mineminenomi.models.entities.zoans.partial;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;

public class PteranodonAssaultPartialModel<T extends LivingEntity>
  extends ZoanMorphModel<T> {
  private final ModelRenderer leftWing;
  private final ModelRenderer leftWing2;
  private final ModelRenderer leftWingTip;
  private final ModelRenderer rightWing;
  private final ModelRenderer rightWing2;
  private final ModelRenderer rightWingTip;
  private final ModelRenderer headPiece;
  
  public PteranodonAssaultPartialModel() {
    super(1.0F);
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.leftWing = new ModelRenderer((Model)this);
    this.leftWing.setRotationPoint(3.8557F, 1.6409F, -1.0F);
    setRotationAngle(this.leftWing, -0.2967F, -0.0262F, -0.0873F);
    this.leftWing.setTextureOffset(24, 0).addBox(-0.0557F, -1.5F, -5.0F, 1.0F, 18.0F, 10.0F, 0.0F, true);
    
    this.leftWingTip = new ModelRenderer((Model)this);
    this.leftWingTip.setRotationPoint(1.0F, 15.75F, -4.5F);
    this.leftWing.addChild(this.leftWingTip);
    setRotationAngle(this.leftWingTip, -1.2217F, 0.0F, 0.0F);
    this.leftWingTip.setTextureOffset(4, 7).addBox(-0.8057F, -0.192F, -0.3995F, 1.0F, 2.0F, 1.0F, 0.0F, true);
    
    this.leftWing2 = new ModelRenderer((Model)this);
    this.leftWing2.setRotationPoint(1.25F, 16.25F, -4.5F);
    this.leftWing.addChild(this.leftWing2);
    setRotationAngle(this.leftWing2, -0.6977F, 0.028F, 0.0334F);
    this.leftWing2.setTextureOffset(0, 0).addBox(-0.8057F, -20.6626F, -0.2402F, 1.0F, 21.0F, 10.0F, 0.0F, true);
    
    this.rightWing = new ModelRenderer((Model)this);
    this.rightWing.setRotationPoint(-3.8943F, 1.6409F, -1.0F);
    setRotationAngle(this.rightWing, -0.2967F, -0.0262F, 0.0873F);
    this.rightWing.setTextureOffset(24, 0).addBox(-0.9557F, -1.5F, -5.0F, 1.0F, 18.0F, 10.0F, 0.0F, false);
    
    this.rightWingTip = new ModelRenderer((Model)this);
    this.rightWingTip.setRotationPoint(-1.0F, 15.75F, -4.5F);
    this.rightWing.addChild(this.rightWingTip);
    setRotationAngle(this.rightWingTip, -1.2217F, 0.0F, 0.0F);
    this.rightWingTip.setTextureOffset(0, 7).addBox(-0.2057F, -0.192F, -0.3995F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.rightWing2 = new ModelRenderer((Model)this);
    this.rightWing2.setRotationPoint(-1.75F, 16.25F, -4.5F);
    this.rightWing.addChild(this.rightWing2);
    setRotationAngle(this.rightWing2, -0.6977F, -0.028F, -0.0334F);
    this.rightWing2.setTextureOffset(0, 0).addBox(0.2943F, -20.6626F, -0.2402F, 1.0F, 21.0F, 10.0F, 0.0F, false);
    
    this.headPiece = new ModelRenderer((Model)this);
    this.headPiece.setRotationPoint(0.0F, 0.75F, 0.0F);
    setRotationAngle(this.headPiece, -0.8727F, 0.0F, 0.0F);
    this.headPiece.setTextureOffset(0, 0).addBox(-1.0F, -8.4532F, -8.5142F, 2.0F, 5.0F, 2.0F, 0.0F, false);
    this.headPiece.setTextureOffset(12, 0).addBox(-1.0F, -11.4532F, -8.5142F, 2.0F, 3.0F, 1.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.headPiece.copyModelAngles(this.bipedHead);
    this.bipedHead.rotateAngleX += (float)Math.toRadians(-40.0D);
    this.leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
    this.headPiece.render(matrixStack, buffer, packedLight, packedOverlay);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    
    if (!((LivingEntity)entity).onGround) {
      
      this.rightWing2.rotateAngleX = (float)Math.toRadians(190.0D);
      this.rightWing2.rotationPointZ = 4.5F;
      this.rightWing2.rotationPointY = 15.0F;
      this.rightWing2.rotationPointX = -1.5F;
      
      this.leftWing2.rotateAngleX = (float)Math.toRadians(190.0D);
      this.leftWing2.rotationPointZ = 4.5F;
      this.leftWing2.rotationPointY = 15.0F;
      this.leftWing2.rotationPointX = 0.8F;
      
      this.rightWing.rotateAngleY += 0.9F + MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.3D + Math.PI));
      this.rightWing.rotateAngleZ += 1.3F + MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.3D + Math.PI));
      this.rightWing2.rotateAngleZ += MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.3D + Math.PI)) / 3.0F;
      
      this.leftWing.rotateAngleY -= 0.9F + MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.3D + Math.PI));
      this.leftWing.rotateAngleZ -= 1.3F + MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.3D + Math.PI));
      this.leftWing2.rotateAngleZ -= MathHelper.cos((float)(((LivingEntity)entity).ticksExisted * 0.3D + Math.PI)) / 3.0F;
    
    }
    else {
      
      float f = 1.0F;
      this.rightWing.rotateAngleX = -0.3F + MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount / f;
      this.leftWing.rotateAngleX = -0.3F + MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount / f;

      
      this.swingProgress = ((LivingEntity)entity).swingProgress;
      boolean isBlackLeg = (EntityStatsCapability.get((LivingEntity)entity).isBlackLeg() && entity.getHeldItemMainhand().isEmpty());
      if (this.swingProgress > 0.0F && !isBlackLeg) {
        
        this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
        this.rightWing.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
        this.rightWing.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
        this.leftWing.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
        this.leftWing.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
        this.rightWing.rotateAngleY += this.bipedBody.rotateAngleY;
        this.leftWing.rotateAngleY += this.bipedBody.rotateAngleY;
        this.leftWing.rotateAngleX += this.bipedBody.rotateAngleY;
        float f1 = 1.0F - this.swingProgress;
        f1 *= f1;
        f1 *= f1;
        f1 = 1.0F - f1;
        float f2 = MathHelper.sin(f1 * 3.1415927F);
        float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
        this.rightWing.rotateAngleX = (float)(this.rightWing.rotateAngleX - f2 * 1.5D + f3);
        this.rightWing.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
        this.rightWing.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.9F;
      } 
    } 
  }



  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}


  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}


  
  public boolean renderItemInHand(T entity, HandSide side, MatrixStack matrixStack) {
    if (entity instanceof PlayerEntity)
    {
      if (!((PlayerEntity)entity).abilities.isFlying)
      {
        return false;
      }
    }
    this.bipedRightLeg.translateRotate(matrixStack);
    matrixStack.rotate(Vector3f.YP.rotationDegrees(-40.0F));
    matrixStack.rotate(Vector3f.XP.rotationDegrees(-30.0F));
    matrixStack.translate(-0.12D, -0.05D, 0.3D);
    return true;
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


