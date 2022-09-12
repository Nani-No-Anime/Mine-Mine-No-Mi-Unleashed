package xyz.pixelatedw.mineminenomi.models.entities.zoans;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;

public class MoguMoleModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer head;
  private final ModelRenderer mouth3;
  private final ModelRenderer mouth4;
  private final ModelRenderer mouth2;
  private final ModelRenderer mouth1;
  private final ModelRenderer body;
  private final ModelRenderer body2;
  private final ModelRenderer body3;
  private final ModelRenderer rightArm;
  private final ModelRenderer rightArm2;
  private final ModelRenderer rightHand;
  private final ModelRenderer rightClaw1;
  private final ModelRenderer rightClaw1b;
  private final ModelRenderer rightClaw1c;
  private final ModelRenderer rightClaw2;
  private final ModelRenderer rightClaw2b;
  private final ModelRenderer rightClaw2c;
  private final ModelRenderer rightClaw3;
  private final ModelRenderer rightClaw3b;
  private final ModelRenderer rightClaw3c;
  private final ModelRenderer rightClaw4;
  private final ModelRenderer rightClaw4_1;
  private final ModelRenderer leftArm;
  private final ModelRenderer leftArm2;
  private final ModelRenderer leftHand;
  private final ModelRenderer leftClaw1;
  private final ModelRenderer leftClaw1b;
  private final ModelRenderer leftClaw1c;
  private final ModelRenderer leftClaw2;
  private final ModelRenderer leftClaw2b;
  private final ModelRenderer leftClaw2c;
  private final ModelRenderer leftClaw3;
  private final ModelRenderer leftClaw3b;
  private final ModelRenderer leftClaw3c;
  private final ModelRenderer leftClaw4;
  private final ModelRenderer leftClaw4b;
  private final ModelRenderer leftLeg;
  private final ModelRenderer leftFoot;
  private final ModelRenderer leftFootClaw1;
  private final ModelRenderer leftFootClaw1_r1;
  private final ModelRenderer leftFootClaw2;
  private final ModelRenderer leftFootClaw3;
  private final ModelRenderer leftFootClaw4;
  private final ModelRenderer rightLeg;
  private final ModelRenderer rightFoot2;
  private final ModelRenderer rightFootClaw1;
  private final ModelRenderer rightFootClaw1_r1;
  private final ModelRenderer rightFootClaw2;
  private final ModelRenderer rightFootClaw3;
  private final ModelRenderer rightFootClaw4;
  
  public MoguMoleModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 64;
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.head.setTextureOffset(0, 0).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 5.0F, 6.0F, 0.0F, false);
    
    this.mouth3 = new ModelRenderer((Model)this);
    this.mouth3.setRotationPoint(0.0F, -2.0F, -2.7F);
    this.head.addChild(this.mouth3);
    setRotationAngle(this.mouth3, 0.1745F, 0.0F, 0.0F);
    this.mouth3.setTextureOffset(36, 0).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);
    
    this.mouth4 = new ModelRenderer((Model)this);
    this.mouth4.setRotationPoint(0.0F, 1.0F, 0.0F);
    this.mouth3.addChild(this.mouth4);
    setRotationAngle(this.mouth4, -0.2443F, 0.0F, 0.0F);
    this.mouth4.setTextureOffset(36, 5).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, -0.01F, false);
    
    this.mouth2 = new ModelRenderer((Model)this);
    this.mouth2.setRotationPoint(1.0F, 0.0F, 0.2F);
    this.mouth3.addChild(this.mouth2);
    setRotationAngle(this.mouth2, 0.0F, 0.3491F, 0.0F);
    this.mouth2.setTextureOffset(25, 5).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, -0.01F, false);
    
    this.mouth1 = new ModelRenderer((Model)this);
    this.mouth1.setRotationPoint(-1.0F, 0.0F, 0.5F);
    this.mouth3.addChild(this.mouth1);
    setRotationAngle(this.mouth1, 0.0F, -0.3491F, 0.0F);
    this.mouth1.setTextureOffset(25, 0).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 3.0F, -0.01F, false);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.body.setTextureOffset(17, 12).addBox(-5.5F, 0.0F, -3.0F, 11.0F, 17.0F, 6.0F, 0.0F, false);
    
    this.body2 = new ModelRenderer((Model)this);
    this.body2.setRotationPoint(0.0F, 0.5F, -0.5F);
    this.body.addChild(this.body2);
    this.body2.setTextureOffset(17, 36).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 16.0F, 7.0F, 0.0F, false);
    
    this.body3 = new ModelRenderer((Model)this);
    this.body3.setRotationPoint(0.0F, 9.0F, -3.0F);
    this.body2.addChild(this.body3);
    this.body3.setTextureOffset(52, 12).addBox(-4.5F, -8.5F, -1.0F, 9.0F, 15.0F, 1.0F, 0.0F, false);
    
    this.rightArm = new ModelRenderer((Model)this);
    this.rightArm.setRotationPoint(-5.4F, 2.0F, 0.0F);
    setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.0698F);
    this.rightArm.setTextureOffset(73, 12).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
    
    this.rightArm2 = new ModelRenderer((Model)this);
    this.rightArm2.setRotationPoint(-2.0F, 5.6F, 0.0F);
    this.rightArm.addChild(this.rightArm2);
    setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.0698F);
    this.rightArm2.setTextureOffset(73, 25).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
    
    this.rightHand = new ModelRenderer((Model)this);
    this.rightHand.setRotationPoint(-0.2F, 6.8F, 0.0F);
    this.rightArm2.addChild(this.rightHand);
    setRotationAngle(this.rightHand, 0.0F, 0.0F, -0.0873F);
    this.rightHand.setTextureOffset(73, 37).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
    
    this.rightClaw1 = new ModelRenderer((Model)this);
    this.rightClaw1.setRotationPoint(-6.6273F, -1.2306F, 1.5F);
    this.rightHand.addChild(this.rightClaw1);
    setRotationAngle(this.rightClaw1, 0.0F, 0.0F, 0.4363F);
    this.rightClaw1.setTextureOffset(73, 44).addBox(5.7219F, -0.8395F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.rightClaw1b = new ModelRenderer((Model)this);
    this.rightClaw1b.setRotationPoint(0.0824F, 2.8796F, 0.0F);
    this.rightClaw1.addChild(this.rightClaw1b);
    setRotationAngle(this.rightClaw1b, 0.0F, 0.0F, -0.4363F);
    this.rightClaw1b.setTextureOffset(73, 49).addBox(5.4385F, 1.6334F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
    
    this.rightClaw1c = new ModelRenderer((Model)this);
    this.rightClaw1c.setRotationPoint(-0.2194F, 2.7529F, 0.5F);
    this.rightClaw1b.addChild(this.rightClaw1c);
    setRotationAngle(this.rightClaw1c, 0.0F, 0.0F, -0.9599F);
    this.rightClaw1c.setTextureOffset(73, 49).addBox(1.6987F, 5.6297F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.rightClaw2 = new ModelRenderer((Model)this);
    this.rightClaw2.setRotationPoint(-6.6273F, -1.2306F, 0.25F);
    this.rightHand.addChild(this.rightClaw2);
    setRotationAngle(this.rightClaw2, 0.0F, 0.0F, 0.4363F);
    this.rightClaw2.setTextureOffset(73, 44).addBox(5.7219F, -0.8395F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.rightClaw2b = new ModelRenderer((Model)this);
    this.rightClaw2b.setRotationPoint(0.0824F, 2.8796F, 0.0F);
    this.rightClaw2.addChild(this.rightClaw2b);
    setRotationAngle(this.rightClaw2b, 0.0F, 0.0F, -0.4363F);
    this.rightClaw2b.setTextureOffset(73, 49).addBox(5.4385F, 1.6334F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
    
    this.rightClaw2c = new ModelRenderer((Model)this);
    this.rightClaw2c.setRotationPoint(-0.2194F, 2.7529F, 0.5F);
    this.rightClaw2b.addChild(this.rightClaw2c);
    setRotationAngle(this.rightClaw2c, 0.0F, 0.0F, -0.9599F);
    this.rightClaw2c.setTextureOffset(73, 49).addBox(1.6987F, 5.6297F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.rightClaw3 = new ModelRenderer((Model)this);
    this.rightClaw3.setRotationPoint(-6.6273F, -1.2306F, -1.0F);
    this.rightHand.addChild(this.rightClaw3);
    setRotationAngle(this.rightClaw3, 0.0F, 0.0F, 0.4363F);
    this.rightClaw3.setTextureOffset(73, 44).addBox(5.7219F, -0.8395F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.rightClaw3b = new ModelRenderer((Model)this);
    this.rightClaw3b.setRotationPoint(0.0824F, 2.8796F, 0.0F);
    this.rightClaw3.addChild(this.rightClaw3b);
    setRotationAngle(this.rightClaw3b, 0.0F, 0.0F, -0.4363F);
    this.rightClaw3b.setTextureOffset(73, 49).addBox(5.4385F, 1.6334F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
    
    this.rightClaw3c = new ModelRenderer((Model)this);
    this.rightClaw3c.setRotationPoint(-0.2194F, 2.7529F, 0.5F);
    this.rightClaw3b.addChild(this.rightClaw3c);
    setRotationAngle(this.rightClaw3c, 0.0F, 0.0F, -0.9599F);
    this.rightClaw3c.setTextureOffset(73, 49).addBox(1.6987F, 5.6297F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.rightClaw4 = new ModelRenderer((Model)this);
    this.rightClaw4.setRotationPoint(9.4363F, -7.7041F, -1.5F);
    this.rightHand.addChild(this.rightClaw4);
    setRotationAngle(this.rightClaw4, -0.6981F, 0.0F, 0.0F);
    this.rightClaw4.setTextureOffset(80, 44).addBox(-9.5176F, 6.7226F, 5.1409F, 1.0F, 2.0F, 1.0F, 0.0F, true);
    
    this.rightClaw4_1 = new ModelRenderer((Model)this);
    this.rightClaw4_1.setRotationPoint(0.4358F, 1.9296F, -0.7285F);
    this.rightClaw4.addChild(this.rightClaw4_1);
    setRotationAngle(this.rightClaw4_1, 0.7741F, 0.0F, 0.0F);
    this.rightClaw4_1.setTextureOffset(80, 48).addBox(-9.9501F, 8.8318F, -0.5371F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftArm = new ModelRenderer((Model)this);
    this.leftArm.setRotationPoint(5.4F, 2.0F, 0.0F);
    setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.0698F);
    this.leftArm.setTextureOffset(73, 12).addBox(0.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
    
    this.leftArm2 = new ModelRenderer((Model)this);
    this.leftArm2.setRotationPoint(2.0F, 5.6F, 0.0F);
    this.leftArm.addChild(this.leftArm2);
    setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.0698F);
    this.leftArm2.setTextureOffset(73, 25).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
    
    this.leftHand = new ModelRenderer((Model)this);
    this.leftHand.setRotationPoint(0.0F, 6.8F, 0.0F);
    this.leftArm2.addChild(this.leftHand);
    setRotationAngle(this.leftHand, 0.0F, 0.0F, 0.0873F);
    this.leftHand.setTextureOffset(73, 37).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
    
    this.leftClaw1 = new ModelRenderer((Model)this);
    this.leftClaw1.setRotationPoint(10.0391F, -7.4945F, 1.5F);
    this.leftHand.addChild(this.leftClaw1);
    setRotationAngle(this.leftClaw1, 0.0F, 0.0F, -0.4363F);
    this.leftClaw1.setTextureOffset(73, 44).addBox(-13.4283F, 3.3539F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.leftClaw1b = new ModelRenderer((Model)this);
    this.leftClaw1b.setRotationPoint(-0.5F, 2.95F, 0.0F);
    this.leftClaw1.addChild(this.leftClaw1b);
    setRotationAngle(this.leftClaw1b, 0.0F, 0.0F, 0.4363F);
    this.leftClaw1b.setTextureOffset(73, 49).addBox(-10.5176F, 7.7757F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
    
    this.leftClaw1c = new ModelRenderer((Model)this);
    this.leftClaw1c.setRotationPoint(-1.2156F, 2.6658F, 0.5F);
    this.leftClaw1b.addChild(this.leftClaw1c);
    setRotationAngle(this.leftClaw1c, 0.0F, 0.0F, 0.9599F);
    this.leftClaw1c.setTextureOffset(73, 49).addBox(1.4797F, 10.6895F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.leftClaw2 = new ModelRenderer((Model)this);
    this.leftClaw2.setRotationPoint(10.0391F, -7.4945F, 0.25F);
    this.leftHand.addChild(this.leftClaw2);
    setRotationAngle(this.leftClaw2, 0.0F, 0.0F, -0.4363F);
    this.leftClaw2.setTextureOffset(73, 44).addBox(-13.4283F, 3.3539F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.leftClaw2b = new ModelRenderer((Model)this);
    this.leftClaw2b.setRotationPoint(-0.5F, 2.95F, 0.0F);
    this.leftClaw2.addChild(this.leftClaw2b);
    setRotationAngle(this.leftClaw2b, 0.0F, 0.0F, 0.4363F);
    this.leftClaw2b.setTextureOffset(73, 49).addBox(-10.5176F, 7.7757F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
    
    this.leftClaw2c = new ModelRenderer((Model)this);
    this.leftClaw2c.setRotationPoint(-1.2156F, 2.6658F, 0.5F);
    this.leftClaw2b.addChild(this.leftClaw2c);
    setRotationAngle(this.leftClaw2c, 0.0F, 0.0F, 0.9599F);
    this.leftClaw2c.setTextureOffset(73, 49).addBox(1.4797F, 10.6895F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.leftClaw3 = new ModelRenderer((Model)this);
    this.leftClaw3.setRotationPoint(10.0391F, -7.4945F, -1.0F);
    this.leftHand.addChild(this.leftClaw3);
    setRotationAngle(this.leftClaw3, 0.0F, 0.0F, -0.4363F);
    this.leftClaw3.setTextureOffset(73, 44).addBox(-13.4283F, 3.3539F, -0.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.leftClaw3b = new ModelRenderer((Model)this);
    this.leftClaw3b.setRotationPoint(-0.5F, 2.95F, 0.0F);
    this.leftClaw3.addChild(this.leftClaw3b);
    setRotationAngle(this.leftClaw3b, 0.0F, 0.0F, 0.4363F);
    this.leftClaw3b.setTextureOffset(73, 49).addBox(-10.5176F, 7.7757F, -0.5F, 2.0F, 3.0F, 1.0F, 0.01F, false);
    
    this.leftClaw3c = new ModelRenderer((Model)this);
    this.leftClaw3c.setRotationPoint(-1.2156F, 2.6658F, 0.5F);
    this.leftClaw3b.addChild(this.leftClaw3c);
    setRotationAngle(this.leftClaw3c, 0.0F, 0.0F, 0.9599F);
    this.leftClaw3c.setTextureOffset(73, 49).addBox(1.4797F, 10.6895F, -1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.leftClaw4 = new ModelRenderer((Model)this);
    this.leftClaw4.setRotationPoint(8.492F, -7.7291F, -1.5F);
    this.leftHand.addChild(this.leftClaw4);
    setRotationAngle(this.leftClaw4, -0.6981F, 0.0F, 0.0F);
    this.leftClaw4.setTextureOffset(80, 44).addBox(-9.5176F, 6.7226F, 5.1409F, 1.0F, 2.0F, 1.0F, 0.0F, true);
    
    this.leftClaw4b = new ModelRenderer((Model)this);
    this.leftClaw4b.setRotationPoint(0.4358F, 1.9296F, -0.7285F);
    this.leftClaw4.addChild(this.leftClaw4b);
    setRotationAngle(this.leftClaw4b, 0.7741F, 0.0F, 0.0F);
    this.leftClaw4b.setTextureOffset(80, 48).addBox(-9.9501F, 8.8318F, -0.5371F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    
    this.leftLeg = new ModelRenderer((Model)this);
    this.leftLeg.setRotationPoint(3.0F, 17.0F, 0.0F);
    this.leftLeg.setTextureOffset(0, 12).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
    
    this.leftFoot = new ModelRenderer((Model)this);
    this.leftFoot.setRotationPoint(0.0F, 5.5F, -1.9F);
    this.leftLeg.addChild(this.leftFoot);
    setRotationAngle(this.leftFoot, 0.7854F, 0.0F, 0.0F);
    this.leftFoot.setTextureOffset(0, 24).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 1.0F, 1.0F, -0.01F, false);
    
    this.leftFootClaw1 = new ModelRenderer((Model)this);
    this.leftFootClaw1.setRotationPoint(1.0F, 0.5768F, -0.2232F);
    this.leftFoot.addChild(this.leftFootClaw1);
    setRotationAngle(this.leftFootClaw1, -0.7418F, -0.1745F, 0.1745F);

    
    this.leftFootClaw1_r1 = new ModelRenderer((Model)this);
    this.leftFootClaw1_r1.setRotationPoint(-4.5F, 1.1F, 2.3F);
    this.leftFootClaw1.addChild(this.leftFootClaw1_r1);
    setRotationAngle(this.leftFootClaw1_r1, 0.0F, 0.0436F, 0.0F);
    this.leftFootClaw1_r1.setTextureOffset(0, 27).addBox(4.4F, -1.1F, -4.3F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftFootClaw2 = new ModelRenderer((Model)this);
    this.leftFootClaw2.setRotationPoint(0.0F, 0.4F, -0.4F);
    this.leftFoot.addChild(this.leftFootClaw2);
    setRotationAngle(this.leftFootClaw2, -0.7418F, 0.0F, 0.0F);
    this.leftFootClaw2.setTextureOffset(0, 27).addBox(0.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftFootClaw3 = new ModelRenderer((Model)this);
    this.leftFootClaw3.setRotationPoint(0.0F, 0.4F, -0.4F);
    this.leftFoot.addChild(this.leftFootClaw3);
    setRotationAngle(this.leftFootClaw3, -0.7418F, 0.0F, 0.0F);
    this.leftFootClaw3.setTextureOffset(0, 27).addBox(-1.1F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftFootClaw4 = new ModelRenderer((Model)this);
    this.leftFootClaw4.setRotationPoint(0.0F, 0.4F, -0.4F);
    this.leftFoot.addChild(this.leftFootClaw4);
    setRotationAngle(this.leftFootClaw4, -0.7418F, 0.1745F, -0.1745F);
    this.leftFootClaw4.setTextureOffset(0, 27).addBox(-1.8541F, 0.0077F, -2.2F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightLeg = new ModelRenderer((Model)this);
    this.rightLeg.setRotationPoint(-3.0F, 17.0F, 0.0F);
    this.rightLeg.setTextureOffset(0, 12).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
    
    this.rightFoot2 = new ModelRenderer((Model)this);
    this.rightFoot2.setRotationPoint(0.0F, 5.5F, -1.9F);
    this.rightLeg.addChild(this.rightFoot2);
    setRotationAngle(this.rightFoot2, 0.7854F, 0.0F, 0.0F);
    this.rightFoot2.setTextureOffset(0, 24).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 1.0F, 1.0F, -0.01F, false);
    
    this.rightFootClaw1 = new ModelRenderer((Model)this);
    this.rightFootClaw1.setRotationPoint(1.0F, 0.5768F, -0.2232F);
    this.rightFoot2.addChild(this.rightFootClaw1);
    setRotationAngle(this.rightFootClaw1, -0.7418F, -0.1745F, 0.1745F);

    
    this.rightFootClaw1_r1 = new ModelRenderer((Model)this);
    this.rightFootClaw1_r1.setRotationPoint(-4.5F, 1.1F, 2.3F);
    this.rightFootClaw1.addChild(this.rightFootClaw1_r1);
    setRotationAngle(this.rightFootClaw1_r1, 0.0F, 0.0436F, 0.0F);
    this.rightFootClaw1_r1.setTextureOffset(0, 27).addBox(4.4F, -1.1F, -4.3F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightFootClaw2 = new ModelRenderer((Model)this);
    this.rightFootClaw2.setRotationPoint(0.0F, 0.4F, -0.4F);
    this.rightFoot2.addChild(this.rightFootClaw2);
    setRotationAngle(this.rightFootClaw2, -0.7418F, 0.0F, 0.0F);
    this.rightFootClaw2.setTextureOffset(0, 27).addBox(0.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightFootClaw3 = new ModelRenderer((Model)this);
    this.rightFootClaw3.setRotationPoint(0.0F, 0.4F, -0.4F);
    this.rightFoot2.addChild(this.rightFootClaw3);
    setRotationAngle(this.rightFootClaw3, -0.7418F, 0.0F, 0.0F);
    this.rightFootClaw3.setTextureOffset(0, 27).addBox(-1.1F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightFootClaw4 = new ModelRenderer((Model)this);
    this.rightFootClaw4.setRotationPoint(0.0F, 0.4F, -0.4F);
    this.rightFoot2.addChild(this.rightFootClaw4);
    setRotationAngle(this.rightFootClaw4, -0.7418F, 0.1745F, -0.1745F);
    this.rightFootClaw4.setTextureOffset(0, 27).addBox(-1.8541F, 0.0077F, -2.2F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.bipedBody = this.body;
    this.bipedHead = this.head;
    this.bipedRightArm = this.rightArm;
    this.bipedLeftArm = this.leftArm;
    this.bipedRightLeg = this.rightLeg;
    this.bipedLeftLeg = this.leftLeg;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    boolean flag = (entity.getTicksElytraFlying() > 4);
    boolean flag1 = entity.isActualySwimming();
    this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;
    if (flag) {
      this.head.rotateAngleX = -0.7853982F;
    } else if (this.swimAnimation > 0.0F) {
      
      if (flag1) {
        this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, -0.7853982F, this.swimAnimation);
      } else {
        this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
      } 
    } else {
      
      this.bipedHead.rotateAngleX = headPitch * 0.017453292F;
      if (this.bipedHead.rotateAngleX > 0.6D) {
        this.bipedHead.rotateAngleX = 0.6F;
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
        
        this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
        this.rightArm.rotationPointZ = MathHelper.sin(this.body2.rotateAngleY) * 5.0F;
        this.rightArm.rotationPointX = -MathHelper.cos(this.body2.rotateAngleY) * 5.0F;
        this.leftArm.rotationPointZ = -MathHelper.sin(this.body2.rotateAngleY) * 5.0F;
        this.leftArm.rotationPointX = MathHelper.cos(this.body2.rotateAngleY) * 5.0F;
        this.rightArm.rotateAngleY += this.body2.rotateAngleY;
        this.leftArm.rotateAngleY += this.body2.rotateAngleY;
        this.leftArm.rotateAngleX += this.body2.rotateAngleY;
        float f1 = 1.0F - this.swingProgress;
        f1 *= f1;
        f1 *= f1;
        f1 = 1.0F - f1;
        float f2 = MathHelper.sin(f1 * 3.1415927F);
        float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 1.75F;
        this.rightArm.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.2D + f3);
        this.rightArm.rotateAngleY += this.bipedBody.rotateAngleY * 4.0F;
        this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
      } 
    }
    
    if (entity.isSneaking()) {
      
      this.body.rotateAngleX = 0.5F;
      this.body.rotationPointZ -= 4.0F;
      this.rightArm.rotateAngleX += 0.4F;
      this.rightArm.rotationPointZ -= 2.5F;
      this.leftArm.rotateAngleX += 0.4F;
      this.leftArm.rotationPointZ -= 2.5F;
      this.rightLeg.rotationPointZ = 3.0F;
      this.rightLeg.rotationPointY = 15.0F;
      this.leftLeg.rotationPointZ = 3.0F;
      this.leftLeg.rotationPointY = 15.0F;
      this.head.rotationPointZ = -6.0F;
      this.head.rotationPointY = 1.0F;
    } 
  }


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    super.translateHand(side, matrixStack);
    matrixStack.translate((side == HandSide.RIGHT) ? -0.1D : 0.1D, 0.5D, 0.0D);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees((side == HandSide.RIGHT) ? -10.0F : 10.0F));
  }


  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(-0.5D, -0.05D, 0.0D);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(20.0F));
      this.rightHand.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
    else {
      
      matrixStack.translate(0.5D, -0.05D, 0.0D);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-20.0F));
      this.leftHand.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
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

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


