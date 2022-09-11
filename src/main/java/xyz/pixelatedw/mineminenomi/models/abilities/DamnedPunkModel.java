package xyz.pixelatedw.mineminenomi.models.abilities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class DamnedPunkModel
  extends EntityModel<Entity>
{
  public final ModelRenderer rightArm;
  private final ModelRenderer cube_r1;
  private final ModelRenderer cube_r2;
  private final ModelRenderer cube_r3;
  private final ModelRenderer cube_r4;
  private final ModelRenderer cube_r5;
  private final ModelRenderer cube_r6;
  private final ModelRenderer cube_r7;
  private final ModelRenderer cube_r8;
  private final ModelRenderer cube_r9;
  private final ModelRenderer cube_r10;
  private final ModelRenderer cube_r11;
  private final ModelRenderer cube_r12;
  private final ModelRenderer cube_r13;
  private final ModelRenderer cube_r14;
  private final ModelRenderer cube_r15;
  private final ModelRenderer cube_r16;
  private final ModelRenderer cube_r17;
  private final ModelRenderer cube_r18;
  private final ModelRenderer cube_r19;
  private final ModelRenderer RightArm_r1;
  private final ModelRenderer RightArm_r2;
  private final ModelRenderer RightArm_r3;
  private final ModelRenderer octagon;
  private final ModelRenderer octagon_r1;
  private final ModelRenderer octagon3;
  private final ModelRenderer octagon_r2;
  private final ModelRenderer octagon2;
  private final ModelRenderer octagon_r3;
  
  public DamnedPunkModel() {
    this.textureWidth = 128;
    this.textureHeight = 128;
    
    this.rightArm = new ModelRenderer((Model)this);
    this.rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
    this.rightArm.setTextureOffset(0, 16).addBox(-9.0F, -4.0F, -4.0F, 5.0F, 5.0F, 8.0F, 0.002F, false);
    this.rightArm.setTextureOffset(24, 47).addBox(-9.0F, 1.0F, -4.0F, 3.0F, 1.0F, 8.0F, 0.0F, false);
    this.rightArm.setTextureOffset(14, 73).addBox(-9.0F, 2.0F, -3.0F, 3.0F, 1.0F, 6.0F, 0.0F, false);
    this.rightArm.setTextureOffset(84, 49).addBox(-10.0F, -3.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
    this.rightArm.setTextureOffset(75, 14).addBox(-10.0F, -1.0F, -4.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    this.rightArm.setTextureOffset(56, 51).addBox(-10.0F, 3.0F, 0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.rightArm.setTextureOffset(56, 51).addBox(-10.0F, 3.0F, -1.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.rightArm.setTextureOffset(56, 51).addBox(-10.0F, 3.0F, -2.75F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.rightArm.setTextureOffset(56, 51).addBox(-10.0F, 3.0F, 1.75F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    this.rightArm.setTextureOffset(75, 14).addBox(-10.0F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    this.rightArm.setTextureOffset(75, 14).addBox(-10.0F, -1.0F, 3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    this.rightArm.setTextureOffset(84, 41).addBox(-10.0F, 1.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
    this.rightArm.setTextureOffset(57, 0).addBox(-11.0F, -5.0F, -5.0F, 8.0F, 1.0F, 10.0F, 0.0F, false);
    this.rightArm.setTextureOffset(85, 11).addBox(-9.0F, 3.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
    this.rightArm.setTextureOffset(0, 0).addBox(-13.0F, -8.0F, -39.0F, 14.0F, 18.0F, 29.0F, 0.0F, false);
    this.rightArm.setTextureOffset(87, 23).addBox(-4.0F, -5.0F, -46.0F, 2.0F, 2.0F, 2.0F, -0.001F, false);
    this.rightArm.setTextureOffset(87, 23).addBox(-4.0F, 1.0F, -46.0F, 2.0F, 2.0F, 2.0F, -0.001F, false);
    this.rightArm.setTextureOffset(87, 23).addBox(-10.0F, 1.0F, -46.0F, 2.0F, 2.0F, 2.0F, -0.001F, false);
    this.rightArm.setTextureOffset(87, 23).addBox(-10.0F, -5.0F, -46.0F, 2.0F, 2.0F, 2.0F, -0.001F, false);
    this.rightArm.setTextureOffset(86, 29).addBox(-5.0F, -13.0F, -34.0F, 3.0F, 6.0F, 3.0F, 0.0F, false);
    this.rightArm.setTextureOffset(30, 67).addBox(-10.0F, -9.0F, -24.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
    this.rightArm.setTextureOffset(0, 47).addBox(-16.0F, -3.0F, -34.0F, 1.0F, 6.0F, 6.0F, -0.001F, false);
    this.rightArm.setTextureOffset(64, 47).addBox(-8.0F, -10.0F, -22.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
    this.rightArm.setTextureOffset(0, 47).addBox(2.5F, -4.0F, -20.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
    this.rightArm.setTextureOffset(0, 47).addBox(-16.5F, -1.0F, -32.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
    this.rightArm.setTextureOffset(0, 47).addBox(2.5F, 4.0F, -30.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.cube_r1 = new ModelRenderer((Model)this);
    this.cube_r1.setRotationPoint(-7.0F, -5.0F, 0.0F);
    this.rightArm.addChild(this.cube_r1);
    setRotationAngle(this.cube_r1, -0.7854F, 0.0F, 0.0F);
    this.cube_r1.setTextureOffset(83, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
    
    this.cube_r2 = new ModelRenderer((Model)this);
    this.cube_r2.setRotationPoint(-7.0F, -5.0F, 0.0F);
    this.rightArm.addChild(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.1968F, 0.3527F, 0.5763F);
    this.cube_r2.setTextureOffset(83, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
    
    this.cube_r3 = new ModelRenderer((Model)this);
    this.cube_r3.setRotationPoint(-15.5F, 0.0F, -31.0F);
    this.rightArm.addChild(this.cube_r3);
    setRotationAngle(this.cube_r3, 0.7854F, 0.0F, 0.0F);
    this.cube_r3.setTextureOffset(0, 47).addBox(-0.5F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, 0.0F, false);
    
    this.cube_r4 = new ModelRenderer((Model)this);
    this.cube_r4.setRotationPoint(-16.9153F, 6.0F, -11.7922F);
    this.rightArm.addChild(this.cube_r4);
    setRotationAngle(this.cube_r4, 2.5986F, 0.8367F, 2.459F);
    this.cube_r4.setTextureOffset(24, 56).addBox(-5.9F, -1.5F, -2.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.cube_r5 = new ModelRenderer((Model)this);
    this.cube_r5.setRotationPoint(-10.9153F, 3.0F, -6.8922F);
    this.rightArm.addChild(this.cube_r5);
    setRotationAngle(this.cube_r5, 0.0F, -2.7053F, 0.0F);
    this.cube_r5.setTextureOffset(25, 56).addBox(-2.5F, -1.0F, 0.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.cube_r6 = new ModelRenderer((Model)this);
    this.cube_r6.setRotationPoint(-13.9323F, 3.0F, -6.7628F);
    this.rightArm.addChild(this.cube_r6);
    setRotationAngle(this.cube_r6, 0.0F, 3.0543F, 0.0F);
    this.cube_r6.setTextureOffset(27, 56).addBox(-1.5F, -1.0F, -0.7255F, 3.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.cube_r7 = new ModelRenderer((Model)this);
    this.cube_r7.setRotationPoint(-16.9153F, 6.0F, -19.7922F);
    this.rightArm.addChild(this.cube_r7);
    setRotationAngle(this.cube_r7, 0.0F, 1.5708F, 0.0F);
    this.cube_r7.setTextureOffset(24, 56).addBox(-8.4F, -1.0F, -2.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.cube_r8 = new ModelRenderer((Model)this);
    this.cube_r8.setRotationPoint(-16.3634F, 6.0F, -21.6518F);
    this.rightArm.addChild(this.cube_r8);
    setRotationAngle(this.cube_r8, 0.0F, 1.0908F, 0.0F);
    this.cube_r8.setTextureOffset(25, 56).addBox(-3.9043F, -1.0F, -0.6308F, 4.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.cube_r9 = new ModelRenderer((Model)this);
    this.cube_r9.setRotationPoint(-14.0F, 6.0F, -23.0F);
    this.rightArm.addChild(this.cube_r9);
    setRotationAngle(this.cube_r9, 0.0F, 0.6545F, 0.0F);
    this.cube_r9.setTextureOffset(25, 56).addBox(-3.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.cube_r10 = new ModelRenderer((Model)this);
    this.cube_r10.setRotationPoint(-8.0F, 11.7425F, -9.2669F);
    this.rightArm.addChild(this.cube_r10);
    setRotationAngle(this.cube_r10, -2.3998F, 0.0F, 0.0F);
    this.cube_r10.setTextureOffset(57, 2).addBox(-1.0F, -1.6462F, -1.2493F, 2.0F, 5.0F, 2.0F, 0.11F, false);
    
    this.cube_r11 = new ModelRenderer((Model)this);
    this.cube_r11.setRotationPoint(-8.0F, 9.7425F, -4.2669F);
    this.rightArm.addChild(this.cube_r11);
    setRotationAngle(this.cube_r11, -1.1781F, 0.0F, 0.0F);
    this.cube_r11.setTextureOffset(57, 2).addBox(-1.0F, 0.7386F, -0.3149F, 2.0F, 5.0F, 2.0F, 0.0F, false);
    
    this.cube_r12 = new ModelRenderer((Model)this);
    this.cube_r12.setRotationPoint(-8.0F, 7.0F, -1.0F);
    this.rightArm.addChild(this.cube_r12);
    setRotationAngle(this.cube_r12, -0.829F, 0.0F, 0.0F);
    this.cube_r12.setTextureOffset(57, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.001F, false);
    
    this.cube_r13 = new ModelRenderer((Model)this);
    this.cube_r13.setRotationPoint(-8.5F, -7.0F, -2.0F);
    this.rightArm.addChild(this.cube_r13);
    setRotationAngle(this.cube_r13, 0.7854F, 0.0F, -0.6981F);
    this.cube_r13.setTextureOffset(25, 15).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    this.cube_r13.setTextureOffset(25, 15).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.cube_r14 = new ModelRenderer((Model)this);
    this.cube_r14.setRotationPoint(-7.8572F, -6.234F, 2.0F);
    this.rightArm.addChild(this.cube_r14);
    setRotationAngle(this.cube_r14, -0.6545F, 0.3054F, -0.6981F);
    this.cube_r14.setTextureOffset(25, 15).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.cube_r15 = new ModelRenderer((Model)this);
    this.cube_r15.setRotationPoint(-9.3893F, -4.9484F, 0.0F);
    this.rightArm.addChild(this.cube_r15);
    setRotationAngle(this.cube_r15, -0.1745F, 0.0F, -0.6981F);
    this.cube_r15.setTextureOffset(25, 16).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
    
    this.cube_r16 = new ModelRenderer((Model)this);
    this.cube_r16.setRotationPoint(-5.6823F, -6.7535F, 2.0F);
    this.rightArm.addChild(this.cube_r16);
    setRotationAngle(this.cube_r16, -0.8727F, 0.0F, 0.6109F);
    this.cube_r16.setTextureOffset(25, 15).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.cube_r17 = new ModelRenderer((Model)this);
    this.cube_r17.setRotationPoint(-5.6823F, -6.7535F, 0.0F);
    this.rightArm.addChild(this.cube_r17);
    setRotationAngle(this.cube_r17, 0.0F, 0.0F, 0.6109F);
    this.cube_r17.setTextureOffset(25, 15).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    this.cube_r17.setTextureOffset(25, 15).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.cube_r18 = new ModelRenderer((Model)this);
    this.cube_r18.setRotationPoint(-8.5F, -7.0F, 0.0F);
    this.rightArm.addChild(this.cube_r18);
    setRotationAngle(this.cube_r18, 0.0F, 0.0F, -0.6981F);
    this.cube_r18.setTextureOffset(25, 15).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.cube_r19 = new ModelRenderer((Model)this);
    this.cube_r19.setRotationPoint(-6.0747F, -6.6512F, -1.5F);
    this.rightArm.addChild(this.cube_r19);
    setRotationAngle(this.cube_r19, 0.4363F, 0.0F, 0.48F);
    this.cube_r19.setTextureOffset(25, 15).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.RightArm_r1 = new ModelRenderer((Model)this);
    this.RightArm_r1.setRotationPoint(-1.0F, 1.0F, 0.0F);
    this.rightArm.addChild(this.RightArm_r1);
    setRotationAngle(this.RightArm_r1, -0.7854F, 0.0F, 0.0F);
    this.RightArm_r1.setTextureOffset(0, 0).addBox(-3.0F, -4.5F, -4.5F, 5.0F, 8.0F, 8.0F, 0.0F, false);
    
    this.RightArm_r2 = new ModelRenderer((Model)this);
    this.RightArm_r2.setRotationPoint(-7.0F, 0.0F, -9.5263F);
    this.rightArm.addChild(this.RightArm_r2);
    setRotationAngle(this.RightArm_r2, 0.0F, -1.5708F, -1.5708F);
    this.RightArm_r2.setTextureOffset(36, 78).addBox(-36.5F, 4.0F, -4.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
    this.RightArm_r2.setTextureOffset(82, 67).addBox(-36.5F, -4.0F, -4.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
    this.RightArm_r2.setTextureOffset(24, 47).addBox(-36.5F, -2.0F, 2.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
    this.RightArm_r2.setTextureOffset(57, 11).addBox(-36.5F, -2.0F, -6.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
    this.RightArm_r2.setTextureOffset(0, 47).addBox(-34.5F, -5.0F, -7.0F, 5.0F, 12.0F, 14.0F, 0.0F, false);
    this.RightArm_r2.setTextureOffset(0, 73).addBox(-0.5F, -3.0F, -5.0F, 3.0F, 8.0F, 8.0F, 0.0F, false);
    this.RightArm_r2.setTextureOffset(38, 47).addBox(2.5F, -1.0F, -3.0F, 3.0F, 4.0F, 4.0F, 0.0F, false);
    
    this.RightArm_r3 = new ModelRenderer((Model)this);
    this.RightArm_r3.setRotationPoint(-1.0F, 1.0F, 0.0F);
    this.rightArm.addChild(this.RightArm_r3);
    setRotationAngle(this.RightArm_r3, -1.5708F, 0.0F, 0.0F);
    this.RightArm_r3.setTextureOffset(0, 0).addBox(-3.0F, -4.0F, -5.0F, 5.0F, 8.0F, 8.0F, -0.001F, false);
    
    this.octagon = new ModelRenderer((Model)this);
    this.octagon.setRotationPoint(5.0F, 5.0F, -27.0F);
    this.rightArm.addChild(this.octagon);
    this.octagon.setTextureOffset(74, 55).addBox(-4.0F, -9.2426F, 5.0F, 2.0F, 2.0F, 6.0F, -0.003F, false);
    this.octagon.setTextureOffset(0, 0).addBox(-4.0F, -11.0F, 6.7574F, 2.0F, 6.0F, 2.0F, 0.001F, false);
    
    this.octagon_r1 = new ModelRenderer((Model)this);
    this.octagon_r1.setRotationPoint(-8.0F, -8.0F, 8.0F);
    this.octagon.addChild(this.octagon_r1);
    setRotationAngle(this.octagon_r1, -0.7854F, 0.0F, 0.0F);
    this.octagon_r1.setTextureOffset(0, 0).addBox(4.0F, -3.0F, -1.2426F, 2.0F, 6.0F, 2.0F, 0.0F, false);
    this.octagon_r1.setTextureOffset(74, 55).addBox(4.0F, -1.2426F, -3.0F, 2.0F, 2.0F, 6.0F, -0.002F, false);
    
    this.octagon3 = new ModelRenderer((Model)this);
    this.octagon3.setRotationPoint(1.0F, 8.0F, -26.0F);
    this.rightArm.addChild(this.octagon3);
    this.octagon3.setTextureOffset(38, 47).addBox(-16.0F, -11.0F, -12.2426F, 2.0F, 6.0F, 14.0F, 0.002F, false);
    this.octagon3.setTextureOffset(56, 73).addBox(-16.0F, -15.2426F, -8.0F, 2.0F, 14.0F, 6.0F, -0.001F, false);
    
    this.octagon_r2 = new ModelRenderer((Model)this);
    this.octagon_r2.setRotationPoint(-8.0F, -8.0F, -5.0F);
    this.octagon3.addChild(this.octagon_r2);
    setRotationAngle(this.octagon_r2, -0.7854F, 0.0F, 0.0F);
    this.octagon_r2.setTextureOffset(56, 73).addBox(-8.0F, -7.2426F, -3.0F, 2.0F, 14.0F, 6.0F, -0.002F, false);
    this.octagon_r2.setTextureOffset(38, 47).addBox(-8.0F, -3.0F, -7.2426F, 2.0F, 6.0F, 14.0F, -0.004F, false);
    
    this.octagon2 = new ModelRenderer((Model)this);
    this.octagon2.setRotationPoint(5.0F, 13.0F, -37.0F);
    this.rightArm.addChild(this.octagon2);
    this.octagon2.setTextureOffset(74, 55).addBox(-4.0F, -9.2426F, 5.0F, 2.0F, 2.0F, 6.0F, 0.002F, false);
    this.octagon2.setTextureOffset(0, 0).addBox(-4.0F, -11.0F, 6.7574F, 2.0F, 6.0F, 2.0F, -0.001F, false);
    
    this.octagon_r3 = new ModelRenderer((Model)this);
    this.octagon_r3.setRotationPoint(-8.0F, -8.0F, 8.0F);
    this.octagon2.addChild(this.octagon_r3);
    setRotationAngle(this.octagon_r3, -0.7854F, 0.0F, 0.0F);
    this.octagon_r3.setTextureOffset(0, 0).addBox(4.0F, -3.0F, -1.2426F, 2.0F, 6.0F, 2.0F, -0.004F, false);
    this.octagon_r3.setTextureOffset(74, 55).addBox(4.0F, -1.2426F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
    this.octagon_r3.setTextureOffset(74, 55).addBox(4.0F, -1.2426F, -3.0F, 2.0F, 2.0F, 6.0F, -0.002F, false);
  }



  
  public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


