package xyz.pixelatedw.mineminenomi.models.armors;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.ZoomAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

@OnlyIn(Dist.CLIENT)
public class SniperGogglesModel<T extends LivingEntity>
  extends BipedModel<T> {
  public ModelRenderer base;
  public ModelRenderer frame1;
  public ModelRenderer frame2;
  public ModelRenderer frame3;
  public ModelRenderer frame4;
  public ModelRenderer frame5;
  public ModelRenderer frame6;
  public ModelRenderer frame7;
  public ModelRenderer frame8;
  public ModelRenderer frame9;
  public ModelRenderer right_ear_support;
  public ModelRenderer left_ear_support;
  public ModelRenderer right_eye;
  public ModelRenderer right_eye_extension;
  public ModelRenderer right_eye_circle_1;
  public ModelRenderer right_eye_circle_2;
  public ModelRenderer left_eye;
  public ModelRenderer left_eye_extension;
  public ModelRenderer left_eye_circle_1;
  public ModelRenderer left_eye_circle_2;
  
  public SniperGogglesModel() {
    super(1.0F);
    this.textureWidth = 32;
    this.textureHeight = 16;
    this.right_eye = new ModelRenderer((Model)this, 6, 8);
    this.right_eye.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.right_eye.addBox(-3.0F, 3.5F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F);
    this.right_eye_extension = new ModelRenderer((Model)this, 6, 12);
    this.right_eye_extension.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.right_eye_extension.addBox(-2.5F, 4.0F, -5.3F, 1.0F, 1.0F, 1.0F, 0.0F);
    this.left_eye_circle_1 = new ModelRenderer((Model)this, 6, 8);
    this.left_eye_circle_1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.left_eye_circle_1.addBox(0.5F, 4.0F, -5.0F, 3.0F, 1.0F, 1.0F, 0.0F);
    this.base = new ModelRenderer((Model)this, 0, 0);
    this.base.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.base.addBox(-4.0F, -8.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    this.left_ear_support = new ModelRenderer((Model)this, 10, 3);
    this.left_ear_support.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.left_ear_support.addBox(4.0F, 4.0F, -0.5F, 1.0F, 2.0F, 2.0F, 0.0F);
    this.right_ear_support = new ModelRenderer((Model)this, 10, 3);
    this.right_ear_support.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.right_ear_support.addBox(-5.0F, 4.0F, -0.5F, 1.0F, 2.0F, 2.0F, 0.0F);
    this.frame7 = new ModelRenderer((Model)this, 5, 3);
    this.frame7.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.frame7.addBox(1.5F, 1.0F, -4.5F, 1.0F, 3.0F, 1.0F, 0.0F);
    this.left_eye = new ModelRenderer((Model)this, 6, 8);
    this.left_eye.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.left_eye.addBox(1.0F, 3.5F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F);
    this.frame2 = new ModelRenderer((Model)this, 0, 3);
    this.frame2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.frame2.addBox(-4.5F, 0.9F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F);
    this.frame4 = new ModelRenderer((Model)this, 12, 6);
    this.frame4.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.frame4.addBox(-2.5F, 0.0F, -4.5F, 1.0F, 1.0F, 9.0F, 0.0F);
    this.left_eye_extension = new ModelRenderer((Model)this, 6, 12);
    this.left_eye_extension.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.left_eye_extension.addBox(1.5F, 4.0F, -5.3F, 1.0F, 1.0F, 1.0F, 0.0F);
    this.frame3 = new ModelRenderer((Model)this, 0, 3);
    this.frame3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.frame3.addBox(3.5F, 0.9F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F);
    this.frame5 = new ModelRenderer((Model)this, 12, 6);
    this.frame5.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.frame5.addBox(1.5F, 0.0F, -4.5F, 1.0F, 1.0F, 9.0F, 0.0F);
    this.left_eye_circle_2 = new ModelRenderer((Model)this, 6, 8);
    this.left_eye_circle_2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.left_eye_circle_2.addBox(1.5F, 3.0F, -5.0F, 1.0F, 3.0F, 1.0F, 0.0F);
    this.frame9 = new ModelRenderer((Model)this, 5, 3);
    this.frame9.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.frame9.addBox(-2.5F, 1.0F, 3.5F, 1.0F, 4.0F, 1.0F, 0.0F);
    this.frame6 = new ModelRenderer((Model)this, 5, 3);
    this.frame6.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.frame6.addBox(-2.5F, 1.0F, -4.5F, 1.0F, 3.0F, 1.0F, 0.0F);
    this.right_eye_circle_1 = new ModelRenderer((Model)this, 6, 8);
    this.right_eye_circle_1.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.right_eye_circle_1.addBox(-3.5F, 4.0F, -5.0F, 3.0F, 1.0F, 1.0F, 0.0F);
    this.right_eye_circle_2 = new ModelRenderer((Model)this, 6, 8);
    this.right_eye_circle_2.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.right_eye_circle_2.addBox(-2.5F, 3.0F, -5.0F, 1.0F, 3.0F, 1.0F, 0.0F);
    this.frame1 = new ModelRenderer((Model)this, 0, 0);
    this.frame1.setRotationPoint(0.0F, -8.5F, 0.0F);
    this.frame1.addBox(-4.5F, -0.1F, 0.0F, 9.0F, 1.0F, 1.0F, 0.0F);
    this.frame8 = new ModelRenderer((Model)this, 5, 3);
    this.frame8.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.frame8.addBox(1.5F, 1.0F, 3.5F, 1.0F, 4.0F, 1.0F, 0.0F);
    this.frame6.addChild(this.right_eye);
    this.right_eye.addChild(this.right_eye_extension);
    this.left_eye.addChild(this.left_eye_circle_1);
    this.frame3.addChild(this.left_ear_support);
    this.frame2.addChild(this.right_ear_support);
    this.frame1.addChild(this.frame7);
    this.frame7.addChild(this.left_eye);
    this.frame1.addChild(this.frame2);
    this.frame1.addChild(this.frame4);
    this.left_eye.addChild(this.left_eye_extension);
    this.frame1.addChild(this.frame3);
    this.frame1.addChild(this.frame5);
    this.left_eye.addChild(this.left_eye_circle_2);
    this.frame1.addChild(this.frame9);
    this.frame1.addChild(this.frame6);
    this.right_eye.addChild(this.right_eye_circle_1);
    this.right_eye.addChild(this.right_eye_circle_2);
    this.base.addChild(this.frame1);
    this.frame1.addChild(this.frame8);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.base.copyModelAngles(this.bipedHead);
    this.base.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    
    if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
      
      ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
      IAbilityData aprops = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
      
      this.left_eye.rotationPointY = -2.0F;
      this.right_eye.rotationPointY = -2.0F;
      
      if (aprops.getEquippedAbility((Ability)ZoomAbility.INSTANCE) != null) {
        
        Ability ability = aprops.getEquippedAbility((Ability)ZoomAbility.INSTANCE);
        
        if (ability.isContinuous()) {
          
          this.left_eye.rotationPointY += 2.0F;
          this.right_eye.rotationPointY += 2.0F;
        } 
      } 
    } 
  }

  
  public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
}


