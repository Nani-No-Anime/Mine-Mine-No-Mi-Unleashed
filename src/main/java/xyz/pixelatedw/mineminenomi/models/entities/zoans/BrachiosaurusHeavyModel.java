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
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;

public class BrachiosaurusHeavyModel<T extends LivingEntity>
  extends ZoanMorphModel<T>
  implements IHasArm
{
  private final ModelRenderer head;
  private final ModelRenderer headBase;
  private final ModelRenderer headBase3_r1;
  private final ModelRenderer headBase1_r1;
  private final ModelRenderer neck;
  private final ModelRenderer neck5_r1;
  private final ModelRenderer neck4_r1;
  private final ModelRenderer neck3_r1;
  private final ModelRenderer neck2_r1;
  private final ModelRenderer upperMouth;
  private final ModelRenderer upperTeeth;
  private final ModelRenderer lowerMouth;
  private final ModelRenderer lowerTeeth;
  private final ModelRenderer leftArm;
  private final ModelRenderer leftArm2_r1;
  private final ModelRenderer leftArm1_r1;
  private final ModelRenderer rightArm;
  private final ModelRenderer rightArm2_r1;
  private final ModelRenderer rightArm1_r1;
  private final ModelRenderer rightLeg;
  private final ModelRenderer rightLeg2_r1;
  private final ModelRenderer rightLeg1_r1;
  private final ModelRenderer leftLeg;
  private final ModelRenderer leftLeg2_r1;
  private final ModelRenderer leftLeg1_r1;
  private final ModelRenderer tail;
  private final ModelRenderer tail2;
  private final ModelRenderer body;
  
  public BrachiosaurusHeavyModel() {
    super(1.0F);
    this.textureWidth = 128;
    this.textureHeight = 128;
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(0.0F, -2.0F, -5.75F);
    
    this.headBase = new ModelRenderer((Model)this);
    this.headBase.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.head.addChild(this.headBase);
    this.headBase.setTextureOffset(0, 0).addBox(-2.5F, -11.75F, -9.0F, 5.0F, 6.0F, 2.0F, 0.01F, false);
    
    this.headBase3_r1 = new ModelRenderer((Model)this);
    this.headBase3_r1.setRotationPoint(0.0F, -8.0F, -9.0F);
    this.headBase.addChild(this.headBase3_r1);
    setRotationAngle(this.headBase3_r1, -0.0873F, 0.0F, 0.0F);
    this.headBase3_r1.setTextureOffset(0, 60).addBox(-2.5F, -3.5F, -1.5728F, 5.0F, 5.0F, 2.0F, 0.0F, false);
    
    this.headBase1_r1 = new ModelRenderer((Model)this);
    this.headBase1_r1.setRotationPoint(0.0F, -8.0392F, -6.2777F);
    this.headBase.addChild(this.headBase1_r1);
    setRotationAngle(this.headBase1_r1, 1.3963F, 0.0F, 0.0F);
    this.headBase1_r1.setTextureOffset(78, 31).addBox(-2.0F, -1.4992F, -2.5824F, 4.0F, 3.0F, 6.0F, 0.0F, false);
    
    this.neck = new ModelRenderer((Model)this);
    this.neck.setRotationPoint(0.0F, 0.0F, -0.1878F);
    this.head.addChild(this.neck);
    
    this.neck5_r1 = new ModelRenderer((Model)this);
    this.neck5_r1.setRotationPoint(0.0F, -0.3992F, 0.1235F);
    this.neck.addChild(this.neck5_r1);
    setRotationAngle(this.neck5_r1, 0.48F, 0.0F, 0.0F);
    this.neck5_r1.setTextureOffset(29, 65).addBox(-2.5F, -4.0723F, -2.3627F, 5.0F, 4.0F, 5.0F, 0.0F, false);
    
    this.neck4_r1 = new ModelRenderer((Model)this);
    this.neck4_r1.setRotationPoint(0.0F, -1.9203F, -2.5618F);
    this.neck.addChild(this.neck4_r1);
    setRotationAngle(this.neck4_r1, 0.5672F, 0.0F, 0.0F);
    this.neck4_r1.setTextureOffset(93, 51).addBox(-2.0F, -3.7189F, -0.6203F, 4.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.neck3_r1 = new ModelRenderer((Model)this);
    this.neck3_r1.setRotationPoint(0.0F, -5.3328F, -2.302F);
    this.neck.addChild(this.neck3_r1);
    setRotationAngle(this.neck3_r1, 0.9599F, 0.0F, 0.0F);
    this.neck3_r1.setTextureOffset(0, 96).addBox(-2.0F, -2.8466F, -1.9423F, 4.0F, 3.0F, 4.0F, -0.01F, false);
    
    this.neck2_r1 = new ModelRenderer((Model)this);
    this.neck2_r1.setRotationPoint(0.0F, -6.4513F, -4.0017F);
    this.neck.addChild(this.neck2_r1);
    setRotationAngle(this.neck2_r1, 1.2217F, 0.0F, 0.0F);
    this.neck2_r1.setTextureOffset(92, 23).addBox(-2.0F, -2.2568F, -1.8199F, 4.0F, 2.0F, 5.0F, 0.01F, false);
    
    this.upperMouth = new ModelRenderer((Model)this);
    this.upperMouth.setRotationPoint(0.0F, -7.0F, -8.0F);
    this.head.addChild(this.upperMouth);
    setRotationAngle(this.upperMouth, -0.1309F, 0.0F, 0.0F);
    this.upperMouth.setTextureOffset(0, 35).addBox(-2.0F, -2.4731F, -4.5535F, 4.0F, 3.0F, 2.0F, 0.0F, false);
    this.upperMouth.setTextureOffset(0, 30).addBox(-2.0F, -2.4731F, -6.5535F, 4.0F, 3.0F, 2.0F, 0.01F, false);
    
    this.upperTeeth = new ModelRenderer((Model)this);
    this.upperTeeth.setRotationPoint(0.0F, -1.4731F, -0.5535F);
    this.upperMouth.addChild(this.upperTeeth);
    this.upperTeeth.setTextureOffset(0, 3).addBox(1.75F, 1.5F, -5.75F, 0.0F, 1.0F, 6.0F, 0.0F, false);
    this.upperTeeth.setTextureOffset(0, 2).addBox(-1.75F, 1.5F, -5.75F, 0.0F, 1.0F, 6.0F, 0.0F, false);
    this.upperTeeth.setTextureOffset(0, 13).addBox(-2.0F, 1.5F, -5.75F, 4.0F, 1.0F, 0.0F, 0.0F, false);
    
    this.lowerMouth = new ModelRenderer((Model)this);
    this.lowerMouth.setRotationPoint(0.0F, -5.3928F, -11.2171F);
    this.head.addChild(this.lowerMouth);
    setRotationAngle(this.lowerMouth, 0.2182F, 0.0F, 0.0F);
    this.lowerMouth.setTextureOffset(0, 45).addBox(-1.5F, -0.5F, -3.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);
    this.lowerMouth.setTextureOffset(0, 40).addBox(-2.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);
    this.lowerMouth.setTextureOffset(0, 10).addBox(-2.0F, -0.5F, 1.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.lowerTeeth = new ModelRenderer((Model)this);
    this.lowerTeeth.setRotationPoint(-0.004F, -0.4557F, -1.2455F);
    this.lowerMouth.addChild(this.lowerTeeth);
    this.lowerTeeth.setTextureOffset(8, 44).addBox(-1.756F, -0.5F, 0.4F, 0.0F, 1.0F, 2.0F, 0.0F, false);
    this.lowerTeeth.setTextureOffset(8, 43).addBox(-1.256F, -0.5F, -1.6F, 0.0F, 1.0F, 2.0F, 0.0F, false);
    this.lowerTeeth.setTextureOffset(10, 9).addBox(1.764F, -0.5F, 0.4F, 0.0F, 1.0F, 2.0F, 0.0F, false);
    this.lowerTeeth.setTextureOffset(10, 8).addBox(1.244F, -0.5F, -1.6F, 0.0F, 1.0F, 2.0F, 0.0F, false);
    this.lowerTeeth.setTextureOffset(8, 13).addBox(-1.496F, -0.5F, -1.6F, 3.0F, 1.0F, 0.0F, 0.0F, false);
    
    this.leftArm = new ModelRenderer((Model)this);
    this.leftArm.setRotationPoint(7.1414F, -1.5F, 0.7938F);
    setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.2618F);
    
    this.leftArm2_r1 = new ModelRenderer((Model)this);
    this.leftArm2_r1.setRotationPoint(1.0578F, 11.3381F, -2.2409F);
    this.leftArm.addChild(this.leftArm2_r1);
    setRotationAngle(this.leftArm2_r1, -0.0873F, 0.0F, 0.0F);
    this.leftArm2_r1.setTextureOffset(72, 77).addBox(-1.1991F, -4.5881F, -2.0528F, 5.0F, 9.0F, 6.0F, -0.01F, false);
    
    this.leftArm1_r1 = new ModelRenderer((Model)this);
    this.leftArm1_r1.setRotationPoint(1.3917F, 3.4646F, -2.2409F);
    this.leftArm.addChild(this.leftArm1_r1);
    setRotationAngle(this.leftArm1_r1, 0.1745F, 0.0F, 0.0F);
    this.leftArm1_r1.setTextureOffset(0, 81).addBox(-1.5331F, -4.4646F, -2.0528F, 5.0F, 9.0F, 6.0F, 0.0F, false);
    
    this.rightArm = new ModelRenderer((Model)this);
    this.rightArm.setRotationPoint(-6.8586F, -1.5F, -1.4562F);
    setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.2618F);
    
    this.rightArm2_r1 = new ModelRenderer((Model)this);
    this.rightArm2_r1.setRotationPoint(-4.2895F, 11.3116F, -1.0365F);
    this.rightArm.addChild(this.rightArm2_r1);
    setRotationAngle(this.rightArm2_r1, -0.0873F, 0.0F, 0.0F);
    this.rightArm2_r1.setTextureOffset(22, 81).addBox(-0.8519F, -4.5616F, -1.2573F, 5.0F, 9.0F, 6.0F, -0.01F, false);
    
    this.rightArm1_r1 = new ModelRenderer((Model)this);
    this.rightArm1_r1.setRotationPoint(-3.9555F, 3.4382F, -1.0365F);
    this.rightArm.addChild(this.rightArm1_r1);
    setRotationAngle(this.rightArm1_r1, 0.1745F, 0.0F, 0.0F);
    this.rightArm1_r1.setTextureOffset(83, 59).addBox(-1.1859F, -4.4382F, -1.7573F, 5.0F, 9.0F, 6.0F, 0.0F, false);
    
    this.rightLeg = new ModelRenderer((Model)this);
    this.rightLeg.setRotationPoint(-6.5F, 8.0F, -1.0F);
    this.rightLeg.setTextureOffset(89, 74).addBox(-2.0F, 12.0F, -3.25F, 4.0F, 4.0F, 5.0F, 0.01F, false);
    
    this.rightLeg2_r1 = new ModelRenderer((Model)this);
    this.rightLeg2_r1.setRotationPoint(11.5F, 41.6483F, -27.1858F);
    this.rightLeg.addChild(this.rightLeg2_r1);
    setRotationAngle(this.rightLeg2_r1, 0.1745F, 0.0F, 0.0F);
    this.rightLeg2_r1.setTextureOffset(90, 0).addBox(-13.5F, -29.1483F, 28.6858F, 4.0F, 5.0F, 5.0F, 0.0F, false);
    
    this.rightLeg1_r1 = new ModelRenderer((Model)this);
    this.rightLeg1_r1.setRotationPoint(11.5F, 27.0F, -35.0F);
    this.rightLeg.addChild(this.rightLeg1_r1);
    setRotationAngle(this.rightLeg1_r1, -0.1745F, 0.0F, 0.0F);
    this.rightLeg1_r1.setTextureOffset(88, 86).addBox(-14.0F, -31.0F, 27.0F, 5.0F, 7.0F, 6.0F, 0.0F, false);
    
    this.leftLeg = new ModelRenderer((Model)this);
    this.leftLeg.setRotationPoint(6.5F, 8.0F, -1.0F);
    this.leftLeg.setTextureOffset(60, 92).addBox(-2.0F, 12.0F, -3.25F, 4.0F, 4.0F, 5.0F, 0.01F, false);
    
    this.leftLeg2_r1 = new ModelRenderer((Model)this);
    this.leftLeg2_r1.setRotationPoint(11.5F, 41.6483F, -27.1858F);
    this.leftLeg.addChild(this.leftLeg2_r1);
    setRotationAngle(this.leftLeg2_r1, 0.1745F, 0.0F, 0.0F);
    this.leftLeg2_r1.setTextureOffset(92, 13).addBox(-13.5F, -29.1483F, 28.6858F, 4.0F, 5.0F, 5.0F, 0.0F, false);
    
    this.leftLeg1_r1 = new ModelRenderer((Model)this);
    this.leftLeg1_r1.setRotationPoint(11.5F, 27.0F, -35.0F);
    this.leftLeg.addChild(this.leftLeg1_r1);
    setRotationAngle(this.leftLeg1_r1, -0.1745F, 0.0F, 0.0F);
    this.leftLeg1_r1.setTextureOffset(38, 90).addBox(-14.0F, -31.0F, 27.0F, 5.0F, 7.0F, 6.0F, 0.0F, false);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(0.0F, 11.5F, 4.1667F);
    setRotationAngle(this.tail, -0.3491F, 0.0F, 0.0F);
    this.tail.setTextureOffset(0, 60).addBox(-3.5F, -1.5F, -0.1667F, 7.0F, 6.0F, 15.0F, -0.01F, false);
    
    this.tail2 = new ModelRenderer((Model)this);
    this.tail2.setRotationPoint(0.0F, 0.25F, 13.3333F);
    this.tail.addChild(this.tail2);
    setRotationAngle(this.tail2, 0.2182F, 0.0F, 0.0F);
    this.tail2.setTextureOffset(39, 41).addBox(-2.5F, -1.0F, 0.5F, 5.0F, 5.0F, 19.0F, -0.01F, false);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(0.0F, 5.8571F, -3.2143F);
    this.body.setTextureOffset(0, 0).addBox(-8.5F, -7.8571F, -4.7857F, 17.0F, 16.0F, 14.0F, 0.0F, false);
    this.body.setTextureOffset(0, 45).addBox(-8.0F, -9.3571F, -4.2857F, 16.0F, 2.0F, 13.0F, 0.0F, false);
    this.body.setTextureOffset(50, 18).addBox(-7.5F, -10.1071F, -3.5357F, 15.0F, 1.0F, 12.0F, 0.0F, false);
    this.body.setTextureOffset(44, 65).addBox(-7.0F, -10.8571F, -3.0357F, 14.0F, 1.0F, 11.0F, 0.0F, false);
    this.body.setTextureOffset(0, 30).addBox(-8.0F, 7.1429F, -4.2857F, 16.0F, 2.0F, 13.0F, 0.0F, false);
    this.body.setTextureOffset(48, 0).addBox(-7.5F, 8.8929F, -3.7857F, 15.0F, 1.0F, 12.0F, 0.0F, false);
    this.body.setTextureOffset(45, 31).addBox(-6.0F, -11.8571F, -2.0357F, 12.0F, 1.0F, 9.0F, 0.0F, false);
    this.body.setTextureOffset(68, 41).addBox(-7.0F, -6.8571F, -5.7857F, 14.0F, 14.0F, 1.0F, 0.0F, false);
    this.body.setTextureOffset(44, 77).addBox(-6.5F, -5.8571F, -6.5357F, 13.0F, 12.0F, 1.0F, 0.0F, false);
    
    this.bipedBody = this.body;
    this.bipedHead = this.head;
    this.bipedRightArm = this.rightArm;
    this.bipedLeftArm = this.leftArm;
    this.bipedRightLeg = this.rightLeg;
    this.bipedLeftLeg = this.leftLeg;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.head.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.tail.render(matrixStack, buffer, packedLight, packedOverlay);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
  }




































  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getTicksElytraFlying : ()I
    //   4: iconst_4
    //   5: if_icmple -> 12
    //   8: iconst_1
    //   9: goto -> 13
    //   12: iconst_0
    //   13: istore #7
    //   15: aload_1
    //   16: invokevirtual isActualySwimming : ()Z
    //   19: istore #8
    //   21: aload_0
    //   22: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   25: fload #5
    //   27: ldc_w 0.017453292
    //   30: fmul
    //   31: putfield rotateAngleY : F
    //   34: iload #7
    //   36: ifeq -> 52
    //   39: aload_0
    //   40: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   43: ldc_w -0.7853982
    //   46: putfield rotateAngleX : F
    //   49: goto -> 163
    //   52: aload_0
    //   53: getfield swimAnimation : F
    //   56: fconst_0
    //   57: fcmpl
    //   58: ifle -> 125
    //   61: iload #8
    //   63: ifeq -> 94
    //   66: aload_0
    //   67: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   70: aload_0
    //   71: aload_0
    //   72: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   75: getfield rotateAngleX : F
    //   78: ldc_w -0.7853982
    //   81: aload_0
    //   82: getfield swimAnimation : F
    //   85: invokevirtual rotLerpRad : (FFF)F
    //   88: putfield rotateAngleX : F
    //   91: goto -> 163
    //   94: aload_0
    //   95: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   98: aload_0
    //   99: aload_0
    //   100: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   103: getfield rotateAngleX : F
    //   106: fload #6
    //   108: ldc_w 0.017453292
    //   111: fmul
    //   112: aload_0
    //   113: getfield swimAnimation : F
    //   116: invokevirtual rotLerpRad : (FFF)F
    //   119: putfield rotateAngleX : F
    //   122: goto -> 163
    //   125: aload_0
    //   126: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   129: fload #6
    //   131: ldc_w 0.015707964
    //   134: fmul
    //   135: putfield rotateAngleX : F
    //   138: aload_0
    //   139: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   142: getfield rotateAngleX : F
    //   145: f2d
    //   146: ldc2_w 0.6
    //   149: dcmpl
    //   150: ifle -> 163
    //   153: aload_0
    //   154: getfield bipedHead : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   157: ldc_w 0.6
    //   160: putfield rotateAngleX : F
    //   163: fconst_1
    //   164: fstore #9
    //   166: aload_0
    //   167: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   170: fload_2
    //   171: ldc_w 0.6662
    //   174: fmul
    //   175: ldc_w 3.1415927
    //   178: fadd
    //   179: invokestatic cos : (F)F
    //   182: ldc 0.4
    //   184: fmul
    //   185: fload_3
    //   186: fmul
    //   187: ldc 0.5
    //   189: fmul
    //   190: fload #9
    //   192: fdiv
    //   193: putfield rotateAngleX : F
    //   196: aload_0
    //   197: getfield leftArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   200: fload_2
    //   201: ldc_w 0.6662
    //   204: fmul
    //   205: invokestatic cos : (F)F
    //   208: ldc 0.4
    //   210: fmul
    //   211: fload_3
    //   212: fmul
    //   213: ldc 0.5
    //   215: fmul
    //   216: fload #9
    //   218: fdiv
    //   219: putfield rotateAngleX : F
    //   222: aload_0
    //   223: getfield rightLeg : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   226: fload_2
    //   227: ldc_w 0.6662
    //   230: fmul
    //   231: invokestatic cos : (F)F
    //   234: ldc_w 0.3
    //   237: fmul
    //   238: fload_3
    //   239: fmul
    //   240: fload #9
    //   242: fdiv
    //   243: putfield rotateAngleX : F
    //   246: aload_0
    //   247: getfield leftLeg : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   250: fload_2
    //   251: ldc_w 0.6662
    //   254: fmul
    //   255: ldc_w 3.1415927
    //   258: fadd
    //   259: invokestatic cos : (F)F
    //   262: ldc_w 0.3
    //   265: fmul
    //   266: fload_3
    //   267: fmul
    //   268: fload #9
    //   270: fdiv
    //   271: putfield rotateAngleX : F
    //   274: aload_1
    //   275: invokevirtual getHeldItemMainhand : ()Lnet/minecraft/item/ItemStack;
    //   278: invokevirtual isEmpty : ()Z
    //   281: ifne -> 299
    //   284: aload_0
    //   285: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   288: dup
    //   289: getfield rotateAngleX : F
    //   292: ldc_w -0.15
    //   295: fadd
    //   296: putfield rotateAngleX : F
    //   299: aload_1
    //   300: invokevirtual isSprinting : ()Z
    //   303: ifeq -> 327
    //   306: aload_0
    //   307: getfield tail : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   310: fload_2
    //   311: ldc_w 0.6662
    //   314: fmul
    //   315: invokestatic cos : (F)F
    //   318: ldc_w 0.2
    //   321: fmul
    //   322: fload_3
    //   323: fmul
    //   324: putfield rotateAngleX : F
    //   327: aload_0
    //   328: aload_1
    //   329: getfield swingProgress : F
    //   332: putfield swingProgress : F
    //   335: aload_1
    //   336: invokestatic get : (Lnet/minecraft/entity/LivingEntity;)Lxyz/pixelatedw/mineminenomi/data/entity/entitystats/IEntityStats;
    //   339: invokeinterface isBlackLeg : ()Z
    //   344: istore #10
    //   346: aload_0
    //   347: getfield swingProgress : F
    //   350: fconst_0
    //   351: fcmpl
    //   352: ifle -> 837
    //   355: iload #10
    //   357: ifeq -> 544
    //   360: aload_0
    //   361: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   364: aload_0
    //   365: getfield swingProgress : F
    //   368: invokestatic sqrt : (F)F
    //   371: ldc_w 6.2831855
    //   374: fmul
    //   375: invokestatic sin : (F)F
    //   378: ldc_w 0.2
    //   381: fmul
    //   382: putfield rotateAngleY : F
    //   385: aload_0
    //   386: getfield rightLeg : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   389: dup
    //   390: getfield rotateAngleY : F
    //   393: aload_0
    //   394: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   397: getfield rotateAngleY : F
    //   400: fadd
    //   401: putfield rotateAngleY : F
    //   404: aload_0
    //   405: getfield leftLeg : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   408: dup
    //   409: getfield rotateAngleY : F
    //   412: aload_0
    //   413: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   416: getfield rotateAngleY : F
    //   419: fadd
    //   420: putfield rotateAngleY : F
    //   423: fconst_1
    //   424: aload_0
    //   425: getfield swingProgress : F
    //   428: fsub
    //   429: fstore #11
    //   431: fload #11
    //   433: fload #11
    //   435: fmul
    //   436: fstore #11
    //   438: fload #11
    //   440: fload #11
    //   442: fmul
    //   443: fstore #11
    //   445: fconst_1
    //   446: fload #11
    //   448: fsub
    //   449: fstore #11
    //   451: fload #11
    //   453: ldc_w 3.1415927
    //   456: fmul
    //   457: invokestatic sin : (F)F
    //   460: fstore #12
    //   462: aload_0
    //   463: getfield swingProgress : F
    //   466: ldc_w 3.1415927
    //   469: fmul
    //   470: invokestatic sin : (F)F
    //   473: aload_0
    //   474: getfield head : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   477: getfield rotateAngleX : F
    //   480: ldc_w 0.7
    //   483: fsub
    //   484: fneg
    //   485: fmul
    //   486: ldc_w 0.75
    //   489: fmul
    //   490: fstore #13
    //   492: aload_0
    //   493: getfield rightLeg : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   496: aload_0
    //   497: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   500: getfield rotateAngleX : F
    //   503: f2d
    //   504: fload #12
    //   506: f2d
    //   507: ldc2_w 0.5
    //   510: dmul
    //   511: fload #13
    //   513: f2d
    //   514: dadd
    //   515: dsub
    //   516: d2f
    //   517: putfield rotateAngleX : F
    //   520: aload_0
    //   521: getfield rightLeg : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   524: dup
    //   525: getfield rotateAngleY : F
    //   528: aload_0
    //   529: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   532: getfield rotateAngleY : F
    //   535: fconst_2
    //   536: fmul
    //   537: fadd
    //   538: putfield rotateAngleY : F
    //   541: goto -> 837
    //   544: aload_0
    //   545: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   548: aload_0
    //   549: getfield swingProgress : F
    //   552: invokestatic sqrt : (F)F
    //   555: ldc_w 6.2831855
    //   558: fmul
    //   559: invokestatic sin : (F)F
    //   562: ldc_w 0.2
    //   565: fmul
    //   566: putfield rotateAngleY : F
    //   569: aload_0
    //   570: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   573: aload_0
    //   574: getfield swingProgress : F
    //   577: invokestatic sqrt : (F)F
    //   580: ldc_w 6.2831855
    //   583: fmul
    //   584: invokestatic sin : (F)F
    //   587: ldc_w 0.2
    //   590: fmul
    //   591: putfield rotateAngleY : F
    //   594: aload_0
    //   595: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   598: aload_0
    //   599: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   602: getfield rotateAngleY : F
    //   605: invokestatic sin : (F)F
    //   608: ldc 8.0
    //   610: fmul
    //   611: putfield rotationPointZ : F
    //   614: aload_0
    //   615: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   618: aload_0
    //   619: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   622: getfield rotateAngleY : F
    //   625: invokestatic cos : (F)F
    //   628: fneg
    //   629: ldc 8.0
    //   631: fmul
    //   632: putfield rotationPointX : F
    //   635: aload_0
    //   636: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   639: dup
    //   640: getfield rotateAngleY : F
    //   643: aload_0
    //   644: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   647: getfield rotateAngleY : F
    //   650: fadd
    //   651: putfield rotateAngleY : F
    //   654: aload_0
    //   655: getfield leftArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   658: dup
    //   659: getfield rotateAngleY : F
    //   662: aload_0
    //   663: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   666: getfield rotateAngleY : F
    //   669: fadd
    //   670: putfield rotateAngleY : F
    //   673: aload_0
    //   674: getfield leftArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   677: dup
    //   678: getfield rotateAngleX : F
    //   681: aload_0
    //   682: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   685: getfield rotateAngleY : F
    //   688: fadd
    //   689: putfield rotateAngleX : F
    //   692: fconst_1
    //   693: aload_0
    //   694: getfield swingProgress : F
    //   697: fsub
    //   698: fstore #11
    //   700: fload #11
    //   702: fload #11
    //   704: fmul
    //   705: fstore #11
    //   707: fload #11
    //   709: fload #11
    //   711: fmul
    //   712: fstore #11
    //   714: fconst_1
    //   715: fload #11
    //   717: fsub
    //   718: fstore #11
    //   720: fload #11
    //   722: ldc_w 3.1415927
    //   725: fmul
    //   726: invokestatic sin : (F)F
    //   729: fstore #12
    //   731: aload_0
    //   732: getfield swingProgress : F
    //   735: ldc_w 3.1415927
    //   738: fmul
    //   739: invokestatic sin : (F)F
    //   742: aload_0
    //   743: getfield head : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   746: getfield rotateAngleX : F
    //   749: ldc_w 0.7
    //   752: fsub
    //   753: fneg
    //   754: fmul
    //   755: ldc_w 0.75
    //   758: fmul
    //   759: fstore #13
    //   761: aload_0
    //   762: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   765: aload_0
    //   766: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   769: getfield rotateAngleX : F
    //   772: f2d
    //   773: fload #12
    //   775: f2d
    //   776: ldc2_w 1.2
    //   779: dmul
    //   780: fload #13
    //   782: f2d
    //   783: dadd
    //   784: dsub
    //   785: d2f
    //   786: putfield rotateAngleX : F
    //   789: aload_0
    //   790: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   793: dup
    //   794: getfield rotateAngleY : F
    //   797: aload_0
    //   798: getfield body : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   801: getfield rotateAngleY : F
    //   804: fconst_2
    //   805: fmul
    //   806: fadd
    //   807: putfield rotateAngleY : F
    //   810: aload_0
    //   811: getfield rightArm : Lnet/minecraft/client/renderer/model/ModelRenderer;
    //   814: dup
    //   815: getfield rotateAngleZ : F
    //   818: aload_0
    //   819: getfield swingProgress : F
    //   822: ldc_w 3.1415927
    //   825: fmul
    //   826: invokestatic sin : (F)F
    //   829: ldc_w -0.4
    //   832: fmul
    //   833: fadd
    //   834: putfield rotateAngleZ : F
    //   837: return
    // Line number table:
    //   Java source line number -> byte code offset
    //   #242	-> 0
    //   #243	-> 15
    //   #244	-> 21
    //   #245	-> 34
    //   #246	-> 39
    //   #247	-> 52
    //   #249	-> 61
    //   #250	-> 66
    //   #252	-> 94
    //   #256	-> 125
    //   #257	-> 138
    //   #258	-> 153
    //   #262	-> 163
    //   #263	-> 166
    //   #264	-> 196
    //   #265	-> 222
    //   #266	-> 246
    //   #267	-> 274
    //   #268	-> 284
    //   #269	-> 299
    //   #270	-> 306
    //   #273	-> 327
    //   #274	-> 335
    //   #275	-> 346
    //   #277	-> 355
    //   #279	-> 360
    //   #280	-> 385
    //   #281	-> 404
    //   #282	-> 423
    //   #283	-> 431
    //   #284	-> 438
    //   #285	-> 445
    //   #286	-> 451
    //   #287	-> 462
    //   #288	-> 492
    //   #289	-> 520
    //   #290	-> 541
    //   #293	-> 544
    //   #294	-> 569
    //   #295	-> 594
    //   #296	-> 614
    //   #297	-> 635
    //   #298	-> 654
    //   #299	-> 673
    //   #300	-> 692
    //   #301	-> 700
    //   #302	-> 707
    //   #303	-> 714
    //   #304	-> 720
    //   #305	-> 731
    //   #306	-> 761
    //   #307	-> 789
    //   #308	-> 810
    //   #311	-> 837
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   431	110	11	f1	F
    //   462	79	12	f2	F
    //   492	49	13	f3	F
    //   700	137	11	f1	F
    //   731	106	12	f2	F
    //   761	76	13	f3	F
    //   0	838	0	this	Lxyz/pixelatedw/mineminenomi/models/entities/zoans/BrachiosaurusHeavyModel;
    //   0	838	1	entity	Lnet/minecraft/entity/LivingEntity;
    //   0	838	2	limbSwing	F
    //   0	838	3	limbSwingAmount	F
    //   0	838	4	ageInTicks	F
    //   0	838	5	netHeadYaw	F
    //   0	838	6	headPitch	F
    //   15	823	7	flag	Z
    //   21	817	8	flag1	Z
    //   166	672	9	f	F
    //   346	492	10	isBlackLeg	Z
    // Local variable type table:
    //   start	length	slot	name	signature
    //   0	838	0	this	Lxyz/pixelatedw/mineminenomi/models/entities/zoans/BrachiosaurusHeavyModel<TT;>;
    //   0	838	1	entity	TT;
  }




































  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    super.translateHand(side, matrixStack);
    matrixStack.translate(0.0D, 0.45D, -0.05D);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees((side == HandSide.RIGHT) ? 5.0F : -5.0F));
  }


  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(-0.3D, -0.3D, -0.2D);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
      matrixStack.rotate(Vector3f.ZP.rotationDegrees(10.0F));
      this.rightArm2_r1.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    }
    else {
      
      matrixStack.translate(0.3D, -0.3D, -0.2D);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
      matrixStack.rotate(Vector3f.ZP.rotationDegrees(-10.0F));
      this.leftArm2_r1.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    } 
  }


  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(0.2D, -0.5D, 0.4D);
      matrixStack.scale(1.5F, 1.5F, 1.5F);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-60.0F));
      matrixStack.rotate(Vector3f.XP.rotationDegrees(10.0F));
      this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    }
    else {
      
      matrixStack.translate(-0.3D, -0.5D, 0.3D);
      matrixStack.scale(1.5F, 1.5F, 1.5F);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(60.0F));
      matrixStack.rotate(Vector3f.XP.rotationDegrees(10.0F));
      this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
    } 
  }
}


