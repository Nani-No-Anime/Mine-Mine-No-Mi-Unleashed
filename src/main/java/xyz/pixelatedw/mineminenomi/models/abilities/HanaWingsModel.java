package xyz.pixelatedw.mineminenomi.models.abilities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class HanaWingsModel<T extends LivingEntity>
  extends PlayerModel<T> {
  private final ModelRenderer wings;
  private final ModelRenderer rightWing;
  private final ModelRenderer rightWing1;
  private final ModelRenderer rightWing2;
  private final ModelRenderer rightWing3;
  private final ModelRenderer rightWing4;
  private final ModelRenderer rightWing5;
  private final ModelRenderer rightWing6;
  private final ModelRenderer rightWing7;
  private final ModelRenderer rightWing8;
  private final ModelRenderer rightWingBone1;
  private final ModelRenderer rightWingBone11;
  private final ModelRenderer rightWingBone2;
  private final ModelRenderer rightWingBone21;
  private final ModelRenderer rightWingBone3;
  private final ModelRenderer rightWingBone31;
  private final ModelRenderer leftWing;
  private final ModelRenderer leftWing1;
  private final ModelRenderer leftWing2;
  private final ModelRenderer leftWing3;
  private final ModelRenderer leftWing4;
  private final ModelRenderer leftWing5;
  private final ModelRenderer leftWing6;
  private final ModelRenderer leftWing7;
  private final ModelRenderer leftWing8;
  private final ModelRenderer leftWingBone1;
  private final ModelRenderer leftWingBone11;
  private final ModelRenderer leftWingBone2;
  private final ModelRenderer leftWingBone21;
  private final ModelRenderer leftWingBone3;
  private final ModelRenderer leftWingBone31;
  
  public HanaWingsModel() {
    super(1.0F, false);
    this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.wings = new ModelRenderer((Model)this);
    this.wings.setRotationPoint(0.0F, 4.0F, 2.0F);

    
    this.rightWing = new ModelRenderer((Model)this);
    this.rightWing.setRotationPoint(-1.0F, -1.0F, 0.0F);
    this.wings.addChild(this.rightWing);
    setRotationAngle(this.rightWing, 0.0F, 0.0F, 0.1745F);

    
    this.rightWing1 = new ModelRenderer((Model)this);
    this.rightWing1.setRotationPoint(-3.0F, -2.0F, 2.0F);
    this.rightWing.addChild(this.rightWing1);
    setRotationAngle(this.rightWing1, 0.0F, 0.0F, 0.0436F);
    this.rightWing1.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, false);
    
    this.rightWing2 = new ModelRenderer((Model)this);
    this.rightWing2.setRotationPoint(-7.0F, -3.0F, 2.0F);
    this.rightWing.addChild(this.rightWing2);
    setRotationAngle(this.rightWing2, 0.0F, 0.0F, -0.1309F);
    this.rightWing2.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.02F, false);
    
    this.rightWing3 = new ModelRenderer((Model)this);
    this.rightWing3.setRotationPoint(-10.0F, -3.0F, 2.0F);
    this.rightWing.addChild(this.rightWing3);
    this.rightWing3.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, false);
    
    this.rightWing4 = new ModelRenderer((Model)this);
    this.rightWing4.setRotationPoint(-13.5F, -2.0F, 2.0F);
    this.rightWing.addChild(this.rightWing4);
    setRotationAngle(this.rightWing4, 0.0F, 0.0F, -0.2182F);
    this.rightWing4.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.02F, false);
    
    this.rightWing5 = new ModelRenderer((Model)this);
    this.rightWing5.setRotationPoint(-16.5F, -2.0F, 2.0F);
    this.rightWing.addChild(this.rightWing5);
    setRotationAngle(this.rightWing5, 0.0F, 0.0F, -0.1309F);
    this.rightWing5.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, false);
    
    this.rightWing6 = new ModelRenderer((Model)this);
    this.rightWing6.setRotationPoint(-20.5F, -3.0F, 2.0F);
    this.rightWing.addChild(this.rightWing6);
    setRotationAngle(this.rightWing6, 0.0F, 0.0F, -0.2618F);
    this.rightWing6.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.02F, false);
    
    this.rightWing7 = new ModelRenderer((Model)this);
    this.rightWing7.setRotationPoint(-23.5F, -3.0F, 2.0F);
    this.rightWing.addChild(this.rightWing7);
    setRotationAngle(this.rightWing7, 0.0F, 0.0F, -0.1309F);
    this.rightWing7.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.03F, false);
    
    this.rightWing8 = new ModelRenderer((Model)this);
    this.rightWing8.setRotationPoint(-24.0F, -3.0F, 2.0F);
    this.rightWing.addChild(this.rightWing8);
    setRotationAngle(this.rightWing8, 0.0F, 0.0F, 0.2618F);
    this.rightWing8.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, false);
    
    this.rightWingBone1 = new ModelRenderer((Model)this);
    this.rightWingBone1.setRotationPoint(-3.0F, -1.0F, 2.0F);
    this.rightWing.addChild(this.rightWingBone1);
    setRotationAngle(this.rightWingBone1, 0.0F, 0.0F, 2.0508F);
    this.rightWingBone1.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    
    this.rightWingBone11 = new ModelRenderer((Model)this);
    this.rightWingBone11.setRotationPoint(-1.5F, 2.0F, 0.0F);
    this.rightWingBone1.addChild(this.rightWingBone11);
    setRotationAngle(this.rightWingBone11, 0.0F, 0.0F, 0.1309F);
    this.rightWingBone11.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, false);
    
    this.rightWingBone2 = new ModelRenderer((Model)this);
    this.rightWingBone2.setRotationPoint(-12.0F, -5.0F, 2.0F);
    this.rightWing.addChild(this.rightWingBone2);
    setRotationAngle(this.rightWingBone2, 0.0F, 0.0F, 1.3963F);
    this.rightWingBone2.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.015F, false);
    
    this.rightWingBone21 = new ModelRenderer((Model)this);
    this.rightWingBone21.setRotationPoint(-2.0F, 0.0F, 0.0F);
    this.rightWingBone2.addChild(this.rightWingBone21);
    setRotationAngle(this.rightWingBone21, 0.0F, 0.0F, 0.0873F);
    this.rightWingBone21.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.01F, false);
    
    this.rightWingBone3 = new ModelRenderer((Model)this);
    this.rightWingBone3.setRotationPoint(-22.75F, -2.5F, 2.0F);
    this.rightWing.addChild(this.rightWingBone3);
    setRotationAngle(this.rightWingBone3, 0.0F, 0.0F, 0.9599F);
    this.rightWingBone3.setTextureOffset(40, 16).addBox(-3.15F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    
    this.rightWingBone31 = new ModelRenderer((Model)this);
    this.rightWingBone31.setRotationPoint(-1.5F, -2.0F, 0.0F);
    this.rightWingBone3.addChild(this.rightWingBone31);
    setRotationAngle(this.rightWingBone31, 0.0F, 0.0F, 0.1309F);
    this.rightWingBone31.setTextureOffset(40, 16).addBox(-3.15F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.02F, false);
    
    this.leftWing = new ModelRenderer((Model)this);
    this.leftWing.setRotationPoint(1.0F, -1.0F, 4.0F);
    this.wings.addChild(this.leftWing);
    setRotationAngle(this.leftWing, 0.0F, 3.1416F, -0.1745F);

    
    this.leftWing1 = new ModelRenderer((Model)this);
    this.leftWing1.setRotationPoint(-3.0F, -2.0F, 2.0F);
    this.leftWing.addChild(this.leftWing1);
    setRotationAngle(this.leftWing1, 0.0F, 0.0F, 0.0436F);
    this.leftWing1.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, true);
    
    this.leftWing2 = new ModelRenderer((Model)this);
    this.leftWing2.setRotationPoint(-7.0F, -3.0F, 2.0F);
    this.leftWing.addChild(this.leftWing2);
    setRotationAngle(this.leftWing2, 0.0F, 0.0F, -0.1309F);
    this.leftWing2.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.02F, true);
    
    this.leftWing3 = new ModelRenderer((Model)this);
    this.leftWing3.setRotationPoint(-10.0F, -3.0F, 2.0F);
    this.leftWing.addChild(this.leftWing3);
    this.leftWing3.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, true);
    
    this.leftWing4 = new ModelRenderer((Model)this);
    this.leftWing4.setRotationPoint(-13.5F, -2.0F, 2.0F);
    this.leftWing.addChild(this.leftWing4);
    setRotationAngle(this.leftWing4, 0.0F, 0.0F, -0.2182F);
    this.leftWing4.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.02F, true);
    
    this.leftWing5 = new ModelRenderer((Model)this);
    this.leftWing5.setRotationPoint(-16.5F, -2.0F, 2.0F);
    this.leftWing.addChild(this.leftWing5);
    setRotationAngle(this.leftWing5, 0.0F, 0.0F, -0.1309F);
    this.leftWing5.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, true);
    
    this.leftWing6 = new ModelRenderer((Model)this);
    this.leftWing6.setRotationPoint(-20.5F, -3.0F, 2.0F);
    this.leftWing.addChild(this.leftWing6);
    setRotationAngle(this.leftWing6, 0.0F, 0.0F, -0.2618F);
    this.leftWing6.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.02F, true);
    
    this.leftWing7 = new ModelRenderer((Model)this);
    this.leftWing7.setRotationPoint(-23.5F, -3.0F, 2.0F);
    this.leftWing.addChild(this.leftWing7);
    setRotationAngle(this.leftWing7, 0.0F, 0.0F, -0.1309F);
    this.leftWing7.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.03F, true);
    
    this.leftWing8 = new ModelRenderer((Model)this);
    this.leftWing8.setRotationPoint(-24.0F, -3.0F, 2.0F);
    this.leftWing.addChild(this.leftWing8);
    setRotationAngle(this.leftWing8, 0.0F, 0.0F, 0.2618F);
    this.leftWing8.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, true);
    
    this.leftWingBone1 = new ModelRenderer((Model)this);
    this.leftWingBone1.setRotationPoint(-3.0F, -1.0F, 2.0F);
    this.leftWing.addChild(this.leftWingBone1);
    setRotationAngle(this.leftWingBone1, 0.0F, 0.0F, 2.0508F);
    this.leftWingBone1.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
    
    this.leftWingBone11 = new ModelRenderer((Model)this);
    this.leftWingBone11.setRotationPoint(-1.5F, 2.0F, 0.0F);
    this.leftWingBone1.addChild(this.leftWingBone11);
    setRotationAngle(this.leftWingBone11, 0.0F, 0.0F, 0.1309F);
    this.leftWingBone11.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.01F, true);
    
    this.leftWingBone2 = new ModelRenderer((Model)this);
    this.leftWingBone2.setRotationPoint(-12.0F, -5.0F, 2.0F);
    this.leftWing.addChild(this.leftWingBone2);
    setRotationAngle(this.leftWingBone2, 0.0F, 0.0F, 1.3963F);
    this.leftWingBone2.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.015F, true);
    
    this.leftWingBone21 = new ModelRenderer((Model)this);
    this.leftWingBone21.setRotationPoint(-2.0F, 0.0F, 0.0F);
    this.leftWingBone2.addChild(this.leftWingBone21);
    setRotationAngle(this.leftWingBone21, 0.0F, 0.0F, 0.0873F);
    this.leftWingBone21.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.01F, true);
    
    this.leftWingBone3 = new ModelRenderer((Model)this);
    this.leftWingBone3.setRotationPoint(-22.75F, -2.5F, 2.0F);
    this.leftWing.addChild(this.leftWingBone3);
    setRotationAngle(this.leftWingBone3, 0.0F, 0.0F, 0.9599F);
    this.leftWingBone3.setTextureOffset(40, 16).addBox(-3.15F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
    
    this.leftWingBone31 = new ModelRenderer((Model)this);
    this.leftWingBone31.setRotationPoint(-1.5F, -2.0F, 0.0F);
    this.leftWingBone3.addChild(this.leftWingBone31);
    setRotationAngle(this.leftWingBone31, 0.0F, 0.0F, 0.1309F);
    this.leftWingBone31.setTextureOffset(40, 16).addBox(-3.15F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, -0.02F, true);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    if (entity.isSneaking()) {
      
      this.wings.rotateAngleX = 0.4F;
      this.wings.rotationPointY = 5.5F;
      this.wings.rotationPointZ = 2.5F;
    }
    else {
      
      this.wings.rotateAngleX = 0.0F;
    } 
    
    this.rightWing.rotateAngleZ = 0.2F + (float)Math.sin((((LivingEntity)entity).ticksExisted * 0.2F)) / 2.0F;
    this.rightWing.rotateAngleY = 0.2F + (float)Math.sin((((LivingEntity)entity).ticksExisted * 0.2F)) / 2.0F;
    this.leftWing.rotateAngleZ = -0.2F - (float)Math.sin((((LivingEntity)entity).ticksExisted * 0.2F)) / 2.0F;
    this.leftWing.rotateAngleY = (float)(Math.toRadians(180.0D) - 0.20000000298023224D - Math.sin((((LivingEntity)entity).ticksExisted * 0.2F)) / 2.0D);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.wings.render(matrixStack, buffer, packedLight, packedOverlay);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


