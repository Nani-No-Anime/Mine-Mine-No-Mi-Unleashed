package xyz.pixelatedw.mineminenomi.models.entities.zoans;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;

public class PhoenixFlyModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer head;
  private final ModelRenderer head2;
  private final ModelRenderer beak;
  private final ModelRenderer beak2;
  private final ModelRenderer beak3;
  private final ModelRenderer beak4;
  private final ModelRenderer head3;
  private final ModelRenderer neck;
  private final ModelRenderer neckFire;
  private final ModelRenderer body;
  private final ModelRenderer body2;
  private final ModelRenderer body3;
  private final ModelRenderer body4;
  private final ModelRenderer backFire;
  private final ModelRenderer rightWing;
  private final ModelRenderer rightWing2;
  private final ModelRenderer leftWing;
  private final ModelRenderer leftWing2;
  private final ModelRenderer tail1;
  private final ModelRenderer tail1b;
  private final ModelRenderer tail2;
  private final ModelRenderer tail2b;
  private final ModelRenderer tail3;
  private final ModelRenderer tail3b;
  private final ModelRenderer tail4;
  private final ModelRenderer tail4b;
  private final ModelRenderer leftLeg;
  private final ModelRenderer leftLeg2;
  private final ModelRenderer leftLeg3;
  private final ModelRenderer leftTalon5;
  private final ModelRenderer rightTalon3_r1;
  private final ModelRenderer rightTalon3_r2;
  private final ModelRenderer rightTalon2_r1;
  private final ModelRenderer leftTalon1b;
  private final ModelRenderer leftTalon1b_r;
  private final ModelRenderer rightTalon3b_r3_r1;
  private final ModelRenderer rightTalon3b_r3_r2;
  private final ModelRenderer rightTalon2b_r2_r1;
  private final ModelRenderer leftTalon1c;
  private final ModelRenderer leftTalon1c_r;
  private final ModelRenderer rightTalon3c_r3_r1;
  private final ModelRenderer rightTalon3c_r3_r2;
  private final ModelRenderer rightTalon2c_r2_r1;
  private final ModelRenderer leftTalon6;
  private final ModelRenderer leftTalonb2;
  private final ModelRenderer leftTalonb_r2;
  private final ModelRenderer leftTalonc2;
  private final ModelRenderer leftTalonc_r2;
  private final ModelRenderer leftTalon7;
  private final ModelRenderer leftTalon3_r;
  private final ModelRenderer leftTalon3b;
  private final ModelRenderer leftTalon3c;
  private final ModelRenderer leftTalon8;
  private final ModelRenderer rightTalon5_r1;
  private final ModelRenderer leftTalon4b;
  private final ModelRenderer leftTalon4b_r;
  private final ModelRenderer rightTalon4b_r2_r1;
  private final ModelRenderer rightLeg;
  private final ModelRenderer rightLeg2;
  private final ModelRenderer rightLeg3;
  private final ModelRenderer rightTalon;
  private final ModelRenderer rightTalon4_r1;
  private final ModelRenderer rightTalon4_r2;
  private final ModelRenderer rightTalon3_r3;
  private final ModelRenderer rightTalon1b;
  private final ModelRenderer rightTalon1b_r;
  private final ModelRenderer rightTalon4b_r4_r1;
  private final ModelRenderer rightTalon4b_r4_r2;
  private final ModelRenderer rightTalon3b_r3_r3;
  private final ModelRenderer rightTalon1c;
  private final ModelRenderer rightTalon1c_r;
  private final ModelRenderer rightTalon4c_r4_r1;
  private final ModelRenderer rightTalon4c_r4_r2;
  private final ModelRenderer rightTalon3c_r3_r3;
  private final ModelRenderer rightTalon3;
  private final ModelRenderer rightTalonb3;
  private final ModelRenderer rightTalonb_r3;
  private final ModelRenderer rightTalonc3;
  private final ModelRenderer rightTalonc_r3;
  private final ModelRenderer rightTalon4;
  private final ModelRenderer rightTalon3_r;
  private final ModelRenderer rightTalon3b;
  private final ModelRenderer rightTalon3c;
  private final ModelRenderer rightTalon9;
  private final ModelRenderer rightTalon6_r1;
  private final ModelRenderer rightTalon4b;
  private final ModelRenderer rightTalon4b_r;
  private final ModelRenderer rightTalon4b_r3_r1;
  
  public PhoenixFlyModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 64;

    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(-1.5F, 1.2F, -16.0F);
    setRotationAngle(this.head, 0.0524F, 0.0F, 0.0F);
    this.head.setTextureOffset(0, 4).addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 2.0F, 0.0F, false);
    
    this.head2 = new ModelRenderer((Model)this);
    this.head2.setRotationPoint(0.0F, 1.0F, -1.7F);
    this.head.addChild(this.head2);
    setRotationAngle(this.head2, -0.0175F, 0.0F, 0.0F);
    this.head2.setTextureOffset(0, 10).addBox(0.0F, 0.0F, 0.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
    
    this.beak = new ModelRenderer((Model)this);
    this.beak.setRotationPoint(0.5F, 0.8F, -1.3F);
    this.head2.addChild(this.beak);
    setRotationAngle(this.beak, -0.0175F, 0.0F, 0.0F);
    this.beak.setTextureOffset(33, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.01F, false);
    
    this.beak2 = new ModelRenderer((Model)this);
    this.beak2.setRotationPoint(1.5F, 0.0F, 2.0F);
    this.beak.addChild(this.beak2);
    setRotationAngle(this.beak2, 0.0F, 0.2618F, 0.0F);
    this.beak2.setTextureOffset(33, 4).addBox(0.0F, 0.0F, -3.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
    
    this.beak3 = new ModelRenderer((Model)this);
    this.beak3.setRotationPoint(0.5F, 0.0F, 2.0F);
    this.beak.addChild(this.beak3);
    setRotationAngle(this.beak3, 0.0F, -0.2618F, 0.0F);
    this.beak3.setTextureOffset(33, 9).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
    
    this.beak4 = new ModelRenderer((Model)this);
    this.beak4.setRotationPoint(1.0F, 0.0F, -1.8F);
    this.beak.addChild(this.beak4);
    setRotationAngle(this.beak4, 0.0F, 0.7854F, 0.0F);
    this.beak4.setTextureOffset(33, 14).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, -0.01F, false);
    
    this.head3 = new ModelRenderer((Model)this);
    this.head3.setRotationPoint(0.0F, 1.1F, -1.7F);
    this.head.addChild(this.head3);
    setRotationAngle(this.head3, 0.5236F, 0.0F, 0.0F);
    this.head3.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 3.0F, 1.0F, 2.0F, -0.01F, false);
    
    this.neck = new ModelRenderer((Model)this);
    this.neck.setRotationPoint(-1.0F, 2.0F, -14.0F);
    setRotationAngle(this.neck, 0.0175F, 0.0F, 0.0F);
    this.neck.setTextureOffset(11, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.neckFire = new ModelRenderer((Model)this);
    this.neckFire.setRotationPoint(1.0F, -2.9956F, -1.75F);
    this.neck.addChild(this.neckFire);
    setRotationAngle(this.neckFire, -0.0175F, 0.0F, 0.0F);
    this.neckFire.setTextureOffset(78, 3).addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 7.0F, 0.0F, false);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(-2.5F, 6.0F, -9.0F);
    setRotationAngle(this.body, 1.5708F, 0.0F, 0.0F);
    this.body.setTextureOffset(0, 18).addBox(0.0F, 0.0F, 0.0F, 5.0F, 18.0F, 6.0F, 0.0F, false);
    
    this.body2 = new ModelRenderer((Model)this);
    this.body2.setRotationPoint(-2.0F, 1.5F, 1.0F);
    this.body.addChild(this.body2);
    this.body2.setTextureOffset(23, 19).addBox(0.0F, 0.0F, 0.0F, 9.0F, 15.0F, 4.0F, 0.0F, false);
    
    this.body3 = new ModelRenderer((Model)this);
    this.body3.setRotationPoint(-1.0F, 1.0F, 0.5F);
    this.body.addChild(this.body3);
    this.body3.setTextureOffset(0, 43).addBox(0.0F, 0.0F, 0.0F, 7.0F, 16.0F, 5.0F, 0.0F, false);
    
    this.body4 = new ModelRenderer((Model)this);
    this.body4.setRotationPoint(0.5F, -1.0F, 1.0F);
    this.body.addChild(this.body4);
    this.body4.setTextureOffset(25, 39).addBox(0.0F, 0.0F, 0.0F, 4.0F, 21.0F, 4.0F, 0.0F, false);
    
    this.backFire = new ModelRenderer((Model)this);
    this.backFire.setRotationPoint(2.5F, 0.0F, 9.0F);
    this.body.addChild(this.backFire);
    setRotationAngle(this.backFire, -1.5708F, 0.0F, 0.0F);
    this.backFire.setTextureOffset(78, -12).addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 18.0F, 0.0F, false);
    
    this.rightWing = new ModelRenderer((Model)this);
    this.rightWing.setRotationPoint(-4.0F, 2.0F, -7.0F);
    setRotationAngle(this.rightWing, 0.0F, -0.0436F, 0.0F);
    this.rightWing.setTextureOffset(65, 20).addBox(-13.0F, 0.5F, 0.0F, 13.0F, 0.0F, 10.0F, 0.0F, false);
    
    this.rightWing2 = new ModelRenderer((Model)this);
    this.rightWing2.setRotationPoint(-12.0F, 0.0F, 0.0F);
    this.rightWing.addChild(this.rightWing2);
    setRotationAngle(this.rightWing2, 0.0F, -0.0698F, 0.0F);
    this.rightWing2.setTextureOffset(55, 31).addBox(-15.0F, 0.5F, 0.0F, 15.0F, 0.0F, 12.0F, 0.0F, false);
    
    this.leftWing = new ModelRenderer((Model)this);
    this.leftWing.setRotationPoint(4.0F, 2.0F, -7.0F);
    setRotationAngle(this.leftWing, 0.0F, 0.0436F, 0.0F);
    this.leftWing.setTextureOffset(92, 20).addBox(0.0F, 0.5F, 0.0F, 13.0F, 0.0F, 10.0F, 0.0F, false);
    
    this.leftWing2 = new ModelRenderer((Model)this);
    this.leftWing2.setRotationPoint(12.0F, 0.0F, 0.0F);
    this.leftWing.addChild(this.leftWing2);
    setRotationAngle(this.leftWing2, 0.0F, 0.0524F, 0.0F);
    this.leftWing2.setTextureOffset(86, 31).addBox(0.0F, 0.5F, 0.0F, 15.0F, 0.0F, 12.0F, 0.0F, false);
    
    this.tail1 = new ModelRenderer((Model)this);
    this.tail1.setRotationPoint(0.5F, 3.0F, 11.0F);
    setRotationAngle(this.tail1, 0.0F, -0.0873F, 0.0F);
    this.tail1.setTextureOffset(110, 55).addBox(-2.5F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, 0.0F, false);
    
    this.tail1b = new ModelRenderer((Model)this);
    this.tail1b.setRotationPoint(0.0F, 0.0F, 8.5F);
    this.tail1.addChild(this.tail1b);
    this.tail1b.setTextureOffset(117, 57).addBox(-2.5F, 0.0F, 0.0F, 2.0F, 0.0F, 7.0F, 0.0F, false);
    
    this.tail2 = new ModelRenderer((Model)this);
    this.tail2.setRotationPoint(1.25F, 3.0F, 11.0F);
    this.tail2.setTextureOffset(110, 55).addBox(-1.4924F, 0.0F, -0.1744F, 2.0F, 0.0F, 9.0F, 0.0F, false);
    
    this.tail2b = new ModelRenderer((Model)this);
    this.tail2b.setRotationPoint(-1.3924F, 0.0F, 8.3256F);
    this.tail2.addChild(this.tail2b);
    this.tail2b.setTextureOffset(117, 57).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 7.0F, 0.0F, false);
    
    this.tail3 = new ModelRenderer((Model)this);
    this.tail3.setRotationPoint(3.0F, 3.0F, 10.0F);
    setRotationAngle(this.tail3, 0.0F, 0.0873F, 0.0F);
    this.tail3.setTextureOffset(110, 55).addBox(-1.4924F, 0.0F, -0.1744F, 2.0F, 0.0F, 9.0F, 0.0F, false);
    
    this.tail3b = new ModelRenderer((Model)this);
    this.tail3b.setRotationPoint(-1.3924F, 0.0F, 8.3256F);
    this.tail3.addChild(this.tail3b);
    setRotationAngle(this.tail3b, 0.0F, 0.1309F, 0.0F);
    this.tail3b.setTextureOffset(117, 57).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 7.0F, 0.0F, false);
    
    this.tail4 = new ModelRenderer((Model)this);
    this.tail4.setRotationPoint(-0.75F, 3.0F, 10.25F);
    setRotationAngle(this.tail4, 0.0F, -0.2618F, 0.0F);
    this.tail4.setTextureOffset(110, 55).addBox(-2.5F, 0.0F, 0.0F, 2.0F, 0.0F, 9.0F, 0.0F, false);
    
    this.tail4b = new ModelRenderer((Model)this);
    this.tail4b.setRotationPoint(0.0F, 0.0F, 8.5F);
    this.tail4.addChild(this.tail4b);
    this.tail4b.setTextureOffset(117, 57).addBox(-2.5F, 0.0F, 0.0F, 2.0F, 0.0F, 7.0F, 0.0F, false);
    
    this.leftLeg = new ModelRenderer((Model)this);
    this.leftLeg.setRotationPoint(2.0F, 10.0F, 1.0F);
    setRotationAngle(this.leftLeg, 0.48F, 0.0F, 0.0F);

    
    this.leftLeg2 = new ModelRenderer((Model)this);
    this.leftLeg2.setRotationPoint(0.0F, 4.75F, 0.0F);
    this.leftLeg.addChild(this.leftLeg2);
    this.leftLeg2.setTextureOffset(43, 46).addBox(-1.5F, -7.0F, 5.75F, 3.0F, 3.0F, 3.0F, -0.001F, true);
    
    this.leftLeg3 = new ModelRenderer((Model)this);
    this.leftLeg3.setRotationPoint(0.0F, -5.0F, 7.25F);
    this.leftLeg2.addChild(this.leftLeg3);
    setRotationAngle(this.leftLeg3, 0.5236F, 0.0F, 0.0F);
    this.leftLeg3.setTextureOffset(45, 54).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
    
    this.leftTalon5 = new ModelRenderer((Model)this);
    this.leftTalon5.setRotationPoint(0.75F, 10.5F, -7.0F);
    this.leftLeg3.addChild(this.leftTalon5);
    setRotationAngle(this.leftTalon5, 0.0437F, -0.1309F, 0.0F);

    
    this.rightTalon3_r1 = new ModelRenderer((Model)this);
    this.rightTalon3_r1.setRotationPoint(-0.0719F, -6.4278F, 7.1532F);
    this.leftTalon5.addChild(this.rightTalon3_r1);
    setRotationAngle(this.rightTalon3_r1, 0.2595F, 0.1338F, -0.0339F);
    this.rightTalon3_r1.setTextureOffset(57, 47).addBox(-0.3781F, -0.7566F, -1.9665F, 1.0F, 1.0F, 2.0F, 0.0F, true);
    
    this.rightTalon3_r2 = new ModelRenderer((Model)this);
    this.rightTalon3_r2.setRotationPoint(-1.4172F, -5.5126F, 5.2954F);
    this.leftTalon5.addChild(this.rightTalon3_r2);
    setRotationAngle(this.rightTalon3_r2, 0.2205F, 0.276F, 0.0041F);
    this.rightTalon3_r2.setTextureOffset(57, 47).addBox(-0.5F, -1.06F, 0.339F, 1.0F, 1.0F, 2.0F, 0.0F, true);
    
    this.rightTalon2_r1 = new ModelRenderer((Model)this);
    this.rightTalon2_r1.setRotationPoint(0.9606F, -5.5496F, 4.8723F);
    this.leftTalon5.addChild(this.rightTalon2_r1);
    setRotationAngle(this.rightTalon2_r1, 0.2595F, 0.0029F, -0.0339F);
    this.rightTalon2_r1.setTextureOffset(57, 47).addBox(-0.5143F, -1.06F, 0.339F, 1.0F, 1.0F, 2.0F, 0.0F, true);
    
    this.leftTalon1b = new ModelRenderer((Model)this);
    this.leftTalon1b.setRotationPoint(0.0F, 0.0F, -1.5F);
    this.leftTalon5.addChild(this.leftTalon1b);
    setRotationAngle(this.leftTalon1b, -0.0436F, 0.0F, 0.0F);

    
    this.leftTalon1b_r = new ModelRenderer((Model)this);
    this.leftTalon1b_r.setRotationPoint(-2.75F, 0.75F, 1.25F);
    this.leftTalon1b.addChild(this.leftTalon1b_r);
    setRotationAngle(this.leftTalon1b_r, 0.0436F, 0.0F, 0.0F);

    
    this.rightTalon3b_r3_r1 = new ModelRenderer((Model)this);
    this.rightTalon3b_r3_r1.setRotationPoint(2.7601F, -4.8487F, 4.5135F);
    this.leftTalon1b_r.addChild(this.rightTalon3b_r3_r1);
    setRotationAngle(this.rightTalon3b_r3_r1, 0.565F, 0.1338F, -0.0339F);
    this.rightTalon3b_r3_r1.setTextureOffset(57, 52).addBox(-0.7801F, -1.7606F, 0.4487F, 1.0F, 1.0F, 2.0F, -0.1F, true);
    
    this.rightTalon3b_r3_r2 = new ModelRenderer((Model)this);
    this.rightTalon3b_r3_r2.setRotationPoint(1.3328F, -6.3163F, 5.5793F);
    this.leftTalon1b_r.addChild(this.rightTalon3b_r3_r2);
    setRotationAngle(this.rightTalon3b_r3_r2, 0.526F, 0.276F, 0.0041F);
    this.rightTalon3b_r3_r2.setTextureOffset(57, 52).addBox(-0.5F, -0.8829F, -1.0195F, 1.0F, 1.0F, 2.0F, -0.1F, true);
    
    this.rightTalon2b_r2_r1 = new ModelRenderer((Model)this);
    this.rightTalon2b_r2_r1.setRotationPoint(3.7106F, -6.3534F, 5.1562F);
    this.leftTalon1b_r.addChild(this.rightTalon2b_r2_r1);
    setRotationAngle(this.rightTalon2b_r2_r1, 0.565F, 0.0029F, -0.0339F);
    this.rightTalon2b_r2_r1.setTextureOffset(57, 52).addBox(-0.5161F, -0.8829F, -1.0195F, 1.0F, 1.0F, 2.0F, -0.1F, true);
    
    this.leftTalon1c = new ModelRenderer((Model)this);
    this.leftTalon1c.setRotationPoint(0.0F, 0.0F, -1.0F);
    this.leftTalon1b.addChild(this.leftTalon1c);
    setRotationAngle(this.leftTalon1c, 0.0873F, 0.0F, 0.0F);

    
    this.leftTalon1c_r = new ModelRenderer((Model)this);
    this.leftTalon1c_r.setRotationPoint(-2.75F, 0.75F, 1.25F);
    this.leftTalon1c.addChild(this.leftTalon1c_r);
    setRotationAngle(this.leftTalon1c_r, 0.0436F, 0.0F, 0.0F);

    
    this.rightTalon3c_r3_r1 = new ModelRenderer((Model)this);
    this.rightTalon3c_r3_r1.setRotationPoint(2.7601F, -4.8487F, 5.5135F);
    this.leftTalon1c_r.addChild(this.rightTalon3c_r3_r1);
    setRotationAngle(this.rightTalon3c_r3_r1, 1.0014F, 0.1309F, -0.0341F);
    this.rightTalon3c_r3_r1.setTextureOffset(57, 52).addBox(-0.8401F, -0.5099F, -0.9865F, 1.0F, 1.0F, 2.0F, -0.25F, true);
    
    this.rightTalon3c_r3_r2 = new ModelRenderer((Model)this);
    this.rightTalon3c_r3_r2.setRotationPoint(1.3328F, -5.5723F, 7.0255F);
    this.leftTalon1c_r.addChild(this.rightTalon3c_r3_r2);
    setRotationAngle(this.rightTalon3c_r3_r2, 0.9589F, 0.2753F, -0.0206F);
    this.rightTalon3c_r3_r2.setTextureOffset(57, 52).addBox(-0.5F, -1.1089F, -2.3174F, 1.0F, 1.0F, 2.0F, -0.25F, true);
    
    this.rightTalon2c_r2_r1 = new ModelRenderer((Model)this);
    this.rightTalon2c_r2_r1.setRotationPoint(3.7106F, -5.6461F, 6.6073F);
    this.leftTalon1c_r.addChild(this.rightTalon2c_r2_r1);
    setRotationAngle(this.rightTalon2c_r2_r1, 1.0014F, 0.0F, -0.0341F);
    this.rightTalon2c_r2_r1.setTextureOffset(57, 52).addBox(-0.4857F, -1.1089F, -2.3174F, 1.0F, 1.0F, 2.0F, -0.25F, true);
    
    this.leftTalon6 = new ModelRenderer((Model)this);
    this.leftTalon6.setRotationPoint(0.0F, 10.5F, -7.0F);
    this.leftLeg3.addChild(this.leftTalon6);
    setRotationAngle(this.leftTalon6, 0.0437F, 0.0F, 0.0F);

    
    this.leftTalonb2 = new ModelRenderer((Model)this);
    this.leftTalonb2.setRotationPoint(0.0F, 0.0F, -1.5F);
    this.leftTalon6.addChild(this.leftTalonb2);
    setRotationAngle(this.leftTalonb2, 0.1833F, 0.0F, 0.0F);

    
    this.leftTalonb_r2 = new ModelRenderer((Model)this);
    this.leftTalonb_r2.setRotationPoint(-2.0F, 0.75F, 1.25F);
    this.leftTalonb2.addChild(this.leftTalonb_r2);
    setRotationAngle(this.leftTalonb_r2, -0.1745F, 0.0F, 0.0F);

    
    this.leftTalonc2 = new ModelRenderer((Model)this);
    this.leftTalonc2.setRotationPoint(0.0F, -0.25F, -2.75F);
    this.leftTalonb2.addChild(this.leftTalonc2);
    setRotationAngle(this.leftTalonc2, 0.0873F, 0.0F, 0.0F);

    
    this.leftTalonc_r2 = new ModelRenderer((Model)this);
    this.leftTalonc_r2.setRotationPoint(-2.0F, 0.8154F, 2.7486F);
    this.leftTalonc2.addChild(this.leftTalonc_r2);
    setRotationAngle(this.leftTalonc_r2, -0.1745F, 0.0F, 0.0F);

    
    this.leftTalon7 = new ModelRenderer((Model)this);
    this.leftTalon7.setRotationPoint(-0.75F, 10.5F, -7.0F);
    this.leftLeg3.addChild(this.leftTalon7);
    setRotationAngle(this.leftTalon7, 0.0873F, 0.1309F, 0.0F);

    
    this.leftTalon3_r = new ModelRenderer((Model)this);
    this.leftTalon3_r.setRotationPoint(-1.25F, 0.75F, -0.25F);
    this.leftTalon7.addChild(this.leftTalon3_r);
    setRotationAngle(this.leftTalon3_r, -0.0436F, 0.0F, 0.0F);

    
    this.leftTalon3b = new ModelRenderer((Model)this);
    this.leftTalon3b.setRotationPoint(0.0F, 0.0F, -2.0F);
    this.leftTalon7.addChild(this.leftTalon3b);
    setRotationAngle(this.leftTalon3b, -0.0436F, 0.0F, 0.0F);

    
    this.leftTalon3c = new ModelRenderer((Model)this);
    this.leftTalon3c.setRotationPoint(0.0F, 0.0F, -1.25F);
    this.leftTalon3b.addChild(this.leftTalon3c);
    setRotationAngle(this.leftTalon3c, 0.0436F, 0.0F, 0.0F);

    
    this.leftTalon8 = new ModelRenderer((Model)this);
    this.leftTalon8.setRotationPoint(0.0F, 10.5F, -7.25F);
    this.leftLeg3.addChild(this.leftTalon8);
    setRotationAngle(this.leftTalon8, 0.0873F, -3.1416F, 0.0F);

    
    this.rightTalon5_r1 = new ModelRenderer((Model)this);
    this.rightTalon5_r1.setRotationPoint(-1.0E-4F, -7.5837F, -7.363F);
    this.leftTalon8.addChild(this.rightTalon5_r1);
    setRotationAngle(this.rightTalon5_r1, 0.48F, 0.0F, 0.0F);
    this.rightTalon5_r1.setTextureOffset(57, 47).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, -0.1F, true);
    
    this.leftTalon4b = new ModelRenderer((Model)this);
    this.leftTalon4b.setRotationPoint(0.0F, 0.0F, -3.0F);
    this.leftTalon8.addChild(this.leftTalon4b);
    setRotationAngle(this.leftTalon4b, 0.1309F, 0.0F, 0.0F);

    
    this.leftTalon4b_r = new ModelRenderer((Model)this);
    this.leftTalon4b_r.setRotationPoint(-2.0F, 0.9665F, 1.4763F);
    this.leftTalon4b.addChild(this.leftTalon4b_r);
    setRotationAngle(this.leftTalon4b_r, -0.0873F, 0.0F, 0.0F);

    
    this.rightTalon4b_r2_r1 = new ModelRenderer((Model)this);
    this.rightTalon4b_r2_r1.setRotationPoint(1.9999F, -7.6703F, -7.0028F);
    this.leftTalon4b_r.addChild(this.rightTalon4b_r2_r1);
    setRotationAngle(this.rightTalon4b_r2_r1, 1.0908F, 0.0F, 0.0F);
    this.rightTalon4b_r2_r1.setTextureOffset(57, 52).addBox(-0.5F, -0.7456F, -1.6073F, 1.0F, 1.0F, 2.0F, -0.25F, true);
    
    this.rightLeg = new ModelRenderer((Model)this);
    this.rightLeg.setRotationPoint(-2.0F, 10.0F, 1.0F);
    setRotationAngle(this.rightLeg, 0.48F, 0.0F, 0.0F);

    
    this.rightLeg2 = new ModelRenderer((Model)this);
    this.rightLeg2.setRotationPoint(0.0F, 4.75F, 0.0F);
    this.rightLeg.addChild(this.rightLeg2);
    this.rightLeg2.setTextureOffset(43, 46).addBox(-1.5F, -7.0F, 5.75F, 3.0F, 3.0F, 3.0F, -0.001F, false);
    
    this.rightLeg3 = new ModelRenderer((Model)this);
    this.rightLeg3.setRotationPoint(0.0F, -5.0F, 7.25F);
    this.rightLeg2.addChild(this.rightLeg3);
    setRotationAngle(this.rightLeg3, 0.5236F, 0.0F, 0.0F);
    this.rightLeg3.setTextureOffset(45, 54).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
    
    this.rightTalon = new ModelRenderer((Model)this);
    this.rightTalon.setRotationPoint(-0.75F, 10.5F, -7.0F);
    this.rightLeg3.addChild(this.rightTalon);
    setRotationAngle(this.rightTalon, 0.0437F, 0.1309F, 0.0F);

    
    this.rightTalon4_r1 = new ModelRenderer((Model)this);
    this.rightTalon4_r1.setRotationPoint(0.0719F, -6.4278F, 7.1532F);
    this.rightTalon.addChild(this.rightTalon4_r1);
    setRotationAngle(this.rightTalon4_r1, 0.2595F, -0.1338F, 0.0339F);
    this.rightTalon4_r1.setTextureOffset(57, 47).addBox(-0.6219F, -0.7566F, -1.9665F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightTalon4_r2 = new ModelRenderer((Model)this);
    this.rightTalon4_r2.setRotationPoint(1.4172F, -5.5126F, 5.2954F);
    this.rightTalon.addChild(this.rightTalon4_r2);
    setRotationAngle(this.rightTalon4_r2, 0.2205F, -0.276F, -0.0041F);
    this.rightTalon4_r2.setTextureOffset(57, 47).addBox(-0.5F, -1.06F, 0.339F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightTalon3_r3 = new ModelRenderer((Model)this);
    this.rightTalon3_r3.setRotationPoint(-0.9606F, -5.5496F, 4.8723F);
    this.rightTalon.addChild(this.rightTalon3_r3);
    setRotationAngle(this.rightTalon3_r3, 0.2595F, -0.0029F, 0.0339F);
    this.rightTalon3_r3.setTextureOffset(57, 47).addBox(-0.4857F, -1.06F, 0.339F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightTalon1b = new ModelRenderer((Model)this);
    this.rightTalon1b.setRotationPoint(0.0F, 0.0F, -1.5F);
    this.rightTalon.addChild(this.rightTalon1b);
    setRotationAngle(this.rightTalon1b, -0.0436F, 0.0F, 0.0F);

    
    this.rightTalon1b_r = new ModelRenderer((Model)this);
    this.rightTalon1b_r.setRotationPoint(2.75F, 0.75F, 1.25F);
    this.rightTalon1b.addChild(this.rightTalon1b_r);
    setRotationAngle(this.rightTalon1b_r, 0.0436F, 0.0F, 0.0F);

    
    this.rightTalon4b_r4_r1 = new ModelRenderer((Model)this);
    this.rightTalon4b_r4_r1.setRotationPoint(-2.7601F, -4.8487F, 4.5135F);
    this.rightTalon1b_r.addChild(this.rightTalon4b_r4_r1);
    setRotationAngle(this.rightTalon4b_r4_r1, 0.565F, -0.1338F, 0.0339F);
    this.rightTalon4b_r4_r1.setTextureOffset(57, 52).addBox(-0.2199F, -1.7606F, 0.4487F, 1.0F, 1.0F, 2.0F, -0.1F, false);
    
    this.rightTalon4b_r4_r2 = new ModelRenderer((Model)this);
    this.rightTalon4b_r4_r2.setRotationPoint(-1.3328F, -6.3163F, 5.5793F);
    this.rightTalon1b_r.addChild(this.rightTalon4b_r4_r2);
    setRotationAngle(this.rightTalon4b_r4_r2, 0.526F, -0.276F, -0.0041F);
    this.rightTalon4b_r4_r2.setTextureOffset(57, 52).addBox(-0.5F, -0.8829F, -1.0195F, 1.0F, 1.0F, 2.0F, -0.1F, false);
    
    this.rightTalon3b_r3_r3 = new ModelRenderer((Model)this);
    this.rightTalon3b_r3_r3.setRotationPoint(-3.7106F, -6.3534F, 5.1562F);
    this.rightTalon1b_r.addChild(this.rightTalon3b_r3_r3);
    setRotationAngle(this.rightTalon3b_r3_r3, 0.565F, -0.0029F, 0.0339F);
    this.rightTalon3b_r3_r3.setTextureOffset(57, 52).addBox(-0.4839F, -0.8829F, -1.0195F, 1.0F, 1.0F, 2.0F, -0.1F, false);
    
    this.rightTalon1c = new ModelRenderer((Model)this);
    this.rightTalon1c.setRotationPoint(0.0F, 0.0F, -1.0F);
    this.rightTalon1b.addChild(this.rightTalon1c);
    setRotationAngle(this.rightTalon1c, 0.0873F, 0.0F, 0.0F);

    
    this.rightTalon1c_r = new ModelRenderer((Model)this);
    this.rightTalon1c_r.setRotationPoint(2.75F, 0.75F, 1.25F);
    this.rightTalon1c.addChild(this.rightTalon1c_r);
    setRotationAngle(this.rightTalon1c_r, 0.0436F, 0.0F, 0.0F);

    
    this.rightTalon4c_r4_r1 = new ModelRenderer((Model)this);
    this.rightTalon4c_r4_r1.setRotationPoint(-2.7601F, -4.8487F, 5.5135F);
    this.rightTalon1c_r.addChild(this.rightTalon4c_r4_r1);
    setRotationAngle(this.rightTalon4c_r4_r1, 1.0014F, -0.1309F, 0.0341F);
    this.rightTalon4c_r4_r1.setTextureOffset(57, 52).addBox(-0.1599F, -0.5099F, -0.9865F, 1.0F, 1.0F, 2.0F, -0.25F, false);
    
    this.rightTalon4c_r4_r2 = new ModelRenderer((Model)this);
    this.rightTalon4c_r4_r2.setRotationPoint(-1.3328F, -5.5723F, 7.0255F);
    this.rightTalon1c_r.addChild(this.rightTalon4c_r4_r2);
    setRotationAngle(this.rightTalon4c_r4_r2, 0.9589F, -0.2753F, 0.0206F);
    this.rightTalon4c_r4_r2.setTextureOffset(57, 52).addBox(-0.5F, -1.1089F, -2.3174F, 1.0F, 1.0F, 2.0F, -0.25F, false);
    
    this.rightTalon3c_r3_r3 = new ModelRenderer((Model)this);
    this.rightTalon3c_r3_r3.setRotationPoint(-3.7106F, -5.6461F, 6.6073F);
    this.rightTalon1c_r.addChild(this.rightTalon3c_r3_r3);
    setRotationAngle(this.rightTalon3c_r3_r3, 1.0014F, 0.0F, 0.0341F);
    this.rightTalon3c_r3_r3.setTextureOffset(57, 52).addBox(-0.5143F, -1.1089F, -2.3174F, 1.0F, 1.0F, 2.0F, -0.25F, false);
    
    this.rightTalon3 = new ModelRenderer((Model)this);
    this.rightTalon3.setRotationPoint(0.0F, 10.5F, -7.0F);
    this.rightLeg3.addChild(this.rightTalon3);
    setRotationAngle(this.rightTalon3, 0.0437F, 0.0F, 0.0F);

    
    this.rightTalonb3 = new ModelRenderer((Model)this);
    this.rightTalonb3.setRotationPoint(0.0F, 0.0F, -1.5F);
    this.rightTalon3.addChild(this.rightTalonb3);
    setRotationAngle(this.rightTalonb3, 0.1833F, 0.0F, 0.0F);

    
    this.rightTalonb_r3 = new ModelRenderer((Model)this);
    this.rightTalonb_r3.setRotationPoint(2.0F, 0.75F, 1.25F);
    this.rightTalonb3.addChild(this.rightTalonb_r3);
    setRotationAngle(this.rightTalonb_r3, -0.1745F, 0.0F, 0.0F);

    
    this.rightTalonc3 = new ModelRenderer((Model)this);
    this.rightTalonc3.setRotationPoint(0.0F, -0.25F, -2.75F);
    this.rightTalonb3.addChild(this.rightTalonc3);
    setRotationAngle(this.rightTalonc3, 0.0873F, 0.0F, 0.0F);

    
    this.rightTalonc_r3 = new ModelRenderer((Model)this);
    this.rightTalonc_r3.setRotationPoint(2.0F, 0.8154F, 2.7486F);
    this.rightTalonc3.addChild(this.rightTalonc_r3);
    setRotationAngle(this.rightTalonc_r3, -0.1745F, 0.0F, 0.0F);

    
    this.rightTalon4 = new ModelRenderer((Model)this);
    this.rightTalon4.setRotationPoint(0.75F, 10.5F, -7.0F);
    this.rightLeg3.addChild(this.rightTalon4);
    setRotationAngle(this.rightTalon4, 0.0873F, -0.1309F, 0.0F);

    
    this.rightTalon3_r = new ModelRenderer((Model)this);
    this.rightTalon3_r.setRotationPoint(1.25F, 0.75F, -0.25F);
    this.rightTalon4.addChild(this.rightTalon3_r);
    setRotationAngle(this.rightTalon3_r, -0.0436F, 0.0F, 0.0F);

    
    this.rightTalon3b = new ModelRenderer((Model)this);
    this.rightTalon3b.setRotationPoint(0.0F, 0.0F, -2.0F);
    this.rightTalon4.addChild(this.rightTalon3b);
    setRotationAngle(this.rightTalon3b, -0.0436F, 0.0F, 0.0F);

    
    this.rightTalon3c = new ModelRenderer((Model)this);
    this.rightTalon3c.setRotationPoint(0.0F, 0.0F, -1.25F);
    this.rightTalon3b.addChild(this.rightTalon3c);
    setRotationAngle(this.rightTalon3c, 0.0436F, 0.0F, 0.0F);

    
    this.rightTalon9 = new ModelRenderer((Model)this);
    this.rightTalon9.setRotationPoint(0.0F, 10.5F, -7.25F);
    this.rightLeg3.addChild(this.rightTalon9);
    setRotationAngle(this.rightTalon9, 0.0873F, 3.1416F, 0.0F);

    
    this.rightTalon6_r1 = new ModelRenderer((Model)this);
    this.rightTalon6_r1.setRotationPoint(1.0E-4F, -7.5837F, -7.363F);
    this.rightTalon9.addChild(this.rightTalon6_r1);
    setRotationAngle(this.rightTalon6_r1, 0.48F, 0.0F, 0.0F);
    this.rightTalon6_r1.setTextureOffset(57, 47).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, -0.1F, false);
    
    this.rightTalon4b = new ModelRenderer((Model)this);
    this.rightTalon4b.setRotationPoint(0.0F, 0.0F, -3.0F);
    this.rightTalon9.addChild(this.rightTalon4b);
    setRotationAngle(this.rightTalon4b, 0.1309F, 0.0F, 0.0F);

    
    this.rightTalon4b_r = new ModelRenderer((Model)this);
    this.rightTalon4b_r.setRotationPoint(2.0F, 0.9665F, 1.4763F);
    this.rightTalon4b.addChild(this.rightTalon4b_r);
    setRotationAngle(this.rightTalon4b_r, -0.0873F, 0.0F, 0.0F);

    
    this.rightTalon4b_r3_r1 = new ModelRenderer((Model)this);
    this.rightTalon4b_r3_r1.setRotationPoint(-1.9999F, -7.6703F, -7.0028F);
    this.rightTalon4b_r.addChild(this.rightTalon4b_r3_r1);
    setRotationAngle(this.rightTalon4b_r3_r1, 1.0908F, 0.0F, 0.0F);
    this.rightTalon4b_r3_r1.setTextureOffset(57, 52).addBox(-0.5F, -0.7456F, -1.6073F, 1.0F, 1.0F, 2.0F, -0.25F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.head.render(matrixStack, buffer, packedLight, packedOverlay);
    this.neck.render(matrixStack, buffer, packedLight, packedOverlay);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
    this.tail1.render(matrixStack, buffer, packedLight, packedOverlay);
    this.tail2.render(matrixStack, buffer, packedLight, packedOverlay);
    this.tail3.render(matrixStack, buffer, packedLight, packedOverlay);
    this.tail4.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
  }


  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    if ((entity.getMotion()).y < -1.7D) {
      
      this.leftWing.rotateAngleX = (float)(this.leftWing.rotateAngleX + Math.toRadians(-90.0D));
      this.leftWing.rotateAngleY = (float)(this.leftWing.rotateAngleY + Math.toRadians(-85.0D));
      this.leftWing2.rotateAngleZ = (float)(this.leftWing2.rotateAngleZ + Math.toRadians(-5.0D));
      this.leftWing.rotateAngleY += MathHelper.cos(((LivingEntity)entity).ticksExisted * 0.9F) / 50.0F;
      this.leftWing.rotateAngleZ += MathHelper.cos(((LivingEntity)entity).ticksExisted * 2.9F) / 50.0F;
      
      this.rightWing.rotateAngleX = (float)(this.rightWing.rotateAngleX + Math.toRadians(-90.0D));
      this.rightWing.rotateAngleY = (float)(this.rightWing.rotateAngleY + Math.toRadians(85.0D));
      this.rightWing2.rotateAngleZ = (float)(this.rightWing2.rotateAngleZ + Math.toRadians(-5.0D));
      this.rightWing.rotateAngleY += MathHelper.cos(((LivingEntity)entity).ticksExisted * 0.9F) / 50.0F;
      this.rightWing.rotateAngleZ += MathHelper.cos(((LivingEntity)entity).ticksExisted * 2.9F) / 50.0F;
    
    }
    else {
      
      this.leftWing.rotateAngleZ = MathHelper.cos(ageInTicks * 0.4F) * 0.7F;
      this.rightWing.rotateAngleZ = MathHelper.cos(ageInTicks * 0.4F + 3.1415927F) * 0.7F;
      this.leftWing2.rotateAngleZ = MathHelper.cos(ageInTicks * 0.4F) * 0.4F;
      this.rightWing2.rotateAngleZ = MathHelper.cos(ageInTicks * 0.4F + 3.1415927F) * 0.4F;
    } 
    
    this.tail1.rotateAngleX = 0.5F * MathHelper.cos(ageInTicks * 0.4F) * 0.3F;
    this.tail1.rotateAngleY = (float)(this.tail1.rotateAngleY + Math.sin(ageInTicks * 0.04D) / 10.0D);
    this.tail1b.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.5F) * 0.8F;
    this.tail1b.rotateAngleY = (float)(this.tail1b.rotateAngleY + Math.sin(ageInTicks * 0.02D) / 5.0D);
    
    this.tail2.rotateAngleX = 0.5F * MathHelper.cos(ageInTicks * 0.4F + 3.1415927F) * 0.1F;
    this.tail2.rotateAngleY = (float)(this.tail2.rotateAngleY + Math.sin(ageInTicks * 0.04D) / 10.0D);
    this.tail2b.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.5F + 3.1415927F) * 0.4F;
    this.tail2b.rotateAngleY = (float)(this.tail2b.rotateAngleY + Math.sin(ageInTicks * 0.02D) / 5.0D);
    
    this.tail3.rotateAngleX = 0.5F * MathHelper.cos(ageInTicks * 0.3F + 3.1415927F) * 0.4F;
    this.tail3.rotateAngleY = (float)(this.tail3.rotateAngleY - Math.sin(ageInTicks * 0.04D) / 10.0D);
    this.tail3b.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.5F + 3.1415927F) * 0.8F;
    this.tail3b.rotateAngleY = (float)(this.tail3b.rotateAngleY - Math.sin(ageInTicks * 0.02D) / 5.0D);
    
    this.tail4.rotateAngleX = 0.5F * MathHelper.cos(ageInTicks * 0.4F) * 0.1F;
    this.tail4.rotateAngleY = (float)(this.tail4.rotateAngleY - Math.sin(ageInTicks * 0.04D) / 9.0D);
    this.tail4b.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.5F) * 0.5F;
    this.tail4b.rotateAngleY = (float)(this.tail4b.rotateAngleY - Math.sin(ageInTicks * 0.02D) / 3.0D);

    
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



  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}



  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}



  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    this.head.translateRotate(matrixStack);
    matrixStack.scale(0.5F, 0.5F, 0.5F);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
    matrixStack.rotate(Vector3f.XP.rotationDegrees(260.0F));
    matrixStack.translate(0.35D, -0.1D, -0.1D);
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


