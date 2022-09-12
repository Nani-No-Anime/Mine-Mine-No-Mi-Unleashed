package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

@OnlyIn(Dist.CLIENT)
public class HumanoidModel<T extends OPEntity>
  extends BipedModel<T> {
  protected float animationTimer = 0.0F;
  public HumanoidArmPose armsPose = HumanoidArmPose.EMPTY;

  
  public HumanoidModel() {
    super(0.0F, 0.0F, 64, 64);
    this.bipedHeadwear.showModel = false;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    super.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    
    if (this.armsPose == HumanoidArmPose.SENRIKU_HOLDING) {
      
      (getArmForSide(HandSide.LEFT)).rotateAngleX = (getArmForSide(HandSide.LEFT)).rotateAngleX * 0.5F - 0.31415927F;
      (getArmForSide(HandSide.LEFT)).rotateAngleY = 0.0F;
    }
    else if (this.armsPose == HumanoidArmPose.SENRIKU_POINTING) {
      
      this.bipedRightArm.rotateAngleY = 0.3F + this.bipedHead.rotateAngleY - 0.4F;
      this.bipedLeftArm.rotateAngleY = 0.8F + this.bipedHead.rotateAngleY;
      this.bipedRightArm.rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
      this.bipedLeftArm.rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
    }
    else if (this.armsPose == HumanoidArmPose.FLINTLOCK_POINTING) {
      
      HandSide side = (entity.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem() == ModWeapons.FLINTLOCK) ? HandSide.RIGHT : HandSide.LEFT;
      HandSide opposide = (entity.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem() == ModWeapons.FLINTLOCK) ? HandSide.LEFT : HandSide.RIGHT;
      (getArmForSide(side)).rotateAngleY = -0.1F + this.bipedHead.rotateAngleY - 0.4F;
      (getArmForSide(opposide)).rotateAngleY = 0.5F + this.bipedHead.rotateAngleY;
      (getArmForSide(side)).rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
      (getArmForSide(opposide)).rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
    }
    else if (this.armsPose == HumanoidArmPose.CANNON_HOLDING) {
      
      this.bipedRightArm.rotateAngleY = 1.2F;
      this.bipedLeftArm.rotateAngleY = 0.8F;
      this.bipedRightArm.rotateAngleX = -2.243995F;
      this.bipedLeftArm.rotateAngleX = -1.7453294F;
    } 
    
    if (Minecraft.getInstance().isGamePaused()) {
      return;
    }
    if (entity.getAnimation() == OPEntity.Animation.CLEAVE_ATTACK.getId()) {
      
      if (this.animationTimer < 5.0F) {
        this.animationTimer = (float)(this.animationTimer + 0.15D);
      }
      this.bipedRightArm.rotateAngleX = -1.3F + (float)Math.sin((this.animationTimer / 1.0F));
      this.bipedRightArm.rotateAngleY = -0.3F;
      this.bipedRightArm.rotateAngleZ = 1.5F;
    }
    else if (entity.getAnimation() == OPEntity.Animation.SHOCKWAVE.getId()) {
      
      if (this.animationTimer < 3.0F) {
        this.animationTimer = (float)(this.animationTimer + 0.1D);
      }
      this.bipedRightArm.rotateAngleX = -1.5F - (float)Math.sin((this.animationTimer * 2.0F)) * 2.0F;
      this.bipedRightArm.rotateAngleY = 0.0F;
      this.bipedRightArm.rotateAngleZ = 0.0F;
    }
    else if (entity.getAnimation() <= OPEntity.Animation.NONE.getId()) {
      
      this.animationTimer = 0.0F;
    } 
  }


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    super.translateHand(side, matrixStack);
    if (this.armsPose == HumanoidArmPose.FLINTLOCK_POINTING) {
      
      matrixStack.translate(0.3D, 0.15D, 0.0D);
      matrixStack.rotate(new Quaternion(Vector3f.ZP, 20.0F, true));
    }
    else if (this.armsPose == HumanoidArmPose.SENRIKU_HOLDING) {
      
      matrixStack.translate(-0.3D, 0.7D, 0.0D);
      matrixStack.rotate(new Quaternion(Vector3f.YP, -75.0F, true));
      matrixStack.rotate(new Quaternion(Vector3f.XP, -90.0F, true));
    }
    else if (this.armsPose == HumanoidArmPose.SENRIKU_POINTING) {
      
      matrixStack.translate(0.05D, 0.15D, 0.0D);
      matrixStack.rotate(new Quaternion(Vector3f.XP, 5.0F, true));
    }
    else if (this.armsPose == HumanoidArmPose.CANNON_HOLDING) {
      
      matrixStack.translate(-0.1D, -0.15D, -0.25D);
      matrixStack.rotate(new Quaternion(Vector3f.XP, 35.0F, true));
      matrixStack.rotate(new Quaternion(Vector3f.ZP, -75.0F, true));
      matrixStack.scale(1.25F, 1.25F, 1.25F);
    } 
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
  
  @OnlyIn(Dist.CLIENT)
  public enum HumanoidArmPose
  {
    EMPTY,
    FLINTLOCK_POINTING,
    SENRIKU_HOLDING,
    SENRIKU_POINTING,
    CANNON_HOLDING;
  }
}


