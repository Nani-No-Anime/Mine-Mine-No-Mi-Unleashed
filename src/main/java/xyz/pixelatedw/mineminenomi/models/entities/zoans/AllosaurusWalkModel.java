package xyz.pixelatedw.mineminenomi.models.entities.zoans;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;

public class AllosaurusWalkModel<T extends LivingEntity>
  extends ZoanMorphModel<T>
  implements IHasArm {
  private final ModelRenderer leftLeg;
  private final ModelRenderer leftLeg2;
  private final ModelRenderer leftFeet;
  private final ModelRenderer leftFeet2;
  private final ModelRenderer leftToe1;
  private final ModelRenderer leftToe2;
  private final ModelRenderer leftToe3;
  private final ModelRenderer leftToe4;
  private final ModelRenderer rightLeg;
  private final ModelRenderer rightLeg2;
  private final ModelRenderer rightFeet;
  private final ModelRenderer rightFeet2;
  private final ModelRenderer rightToe1;
  private final ModelRenderer rightToe2;
  private final ModelRenderer rightToe3;
  private final ModelRenderer rightToe4;
  private final ModelRenderer rightArm;
  private final ModelRenderer rightArm2;
  private final ModelRenderer rightHand;
  private final ModelRenderer rightFinger1;
  private final ModelRenderer rightFinger2;
  private final ModelRenderer rightFinger3;
  private final ModelRenderer leftArm;
  private final ModelRenderer leftArm2;
  private final ModelRenderer leftHand;
  private final ModelRenderer leftFinger1;
  private final ModelRenderer leftFinger2;
  private final ModelRenderer leftFinger3;
  private final ModelRenderer body;
  private final ModelRenderer head;
  private final ModelRenderer neck3;
  private final ModelRenderer neck2;
  private final ModelRenderer neck1;
  private final ModelRenderer upperHead;
  private final ModelRenderer upperTeeth;
  private final ModelRenderer lowerHead;
  private final ModelRenderer lowerTeeth;
  private final ModelRenderer tail;
  private final ModelRenderer tail2;
  private final ModelRenderer tail3;
  private final ModelRenderer tail4;
  private final ModelRenderer tail5;
  private final ModelRenderer tail6;
  private final ModelRenderer tail7;
  private final ModelRenderer tail8;
  
  public AllosaurusWalkModel() {
    super(1.0F);
    this.textureWidth = 256;
    this.textureHeight = 256;
    
    this.leftLeg = new ModelRenderer((Model)this);
    this.leftLeg.setRotationPoint(6.5F, -10.564F, 16.0191F);
    setRotationAngle(this.leftLeg, 0.2182F, 0.0F, 0.0F);
    this.leftLeg.setTextureOffset(52, 80).addBox(0.0F, -12.0F, -7.0F, 7.0F, 24.0F, 14.0F, 0.0F, false);
    
    this.leftLeg2 = new ModelRenderer((Model)this);
    this.leftLeg2.setRotationPoint(0.0F, 9.4613F, -5.3088F);
    this.leftLeg.addChild(this.leftLeg2);
    setRotationAngle(this.leftLeg2, -0.6981F, 0.0F, 0.0F);
    this.leftLeg2.setTextureOffset(46, 120).addBox(0.5F, -4.6299F, 0.6175F, 6.0F, 27.0F, 8.0F, 0.0F, false);
    
    this.leftFeet = new ModelRenderer((Model)this);
    this.leftFeet.setRotationPoint(0.0F, 21.6027F, 5.2897F);
    this.leftLeg2.addChild(this.leftFeet);
    setRotationAngle(this.leftFeet, 0.48F, 0.0F, 0.0F);
    this.leftFeet.setTextureOffset(95, 92).addBox(-1.0F, -2.5F, -5.0F, 9.0F, 5.0F, 10.0F, 0.0F, false);
    
    this.leftFeet2 = new ModelRenderer((Model)this);
    this.leftFeet2.setRotationPoint(0.0F, 0.5F, -3.0F);
    this.leftFeet.addChild(this.leftFeet2);
    this.leftFeet2.setTextureOffset(94, 109).addBox(-1.5F, -2.0F, -5.0F, 10.0F, 4.0F, 10.0F, 0.0F, false);
    
    this.leftToe1 = new ModelRenderer((Model)this);
    this.leftToe1.setRotationPoint(3.8689F, 1.01F, -6.0793F);
    this.leftFeet2.addChild(this.leftToe1);
    setRotationAngle(this.leftToe1, 0.2075F, -0.2612F, -0.0036F);
    this.leftToe1.setTextureOffset(104, 125).addBox(1.8812F, -1.6739F, -3.3871F, 3.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.leftToe2 = new ModelRenderer((Model)this);
    this.leftToe2.setRotationPoint(0.0F, 0.9569F, -6.7225F);
    this.leftFeet2.addChild(this.leftToe2);
    setRotationAngle(this.leftToe2, 0.1745F, 0.0F, 0.0F);
    this.leftToe2.setTextureOffset(104, 125).addBox(2.0F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.leftToe3 = new ModelRenderer((Model)this);
    this.leftToe3.setRotationPoint(-3.7618F, 0.7739F, -5.8025F);
    this.leftFeet2.addChild(this.leftToe3);
    setRotationAngle(this.leftToe3, 0.2188F, 0.1744F, 0.0077F);
    this.leftToe3.setTextureOffset(104, 125).addBox(1.9468F, -1.3945F, -2.4015F, 3.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.leftToe4 = new ModelRenderer((Model)this);
    this.leftToe4.setRotationPoint(0.0F, -2.4597F, 7.6541F);
    this.leftFeet2.addChild(this.leftToe4);
    setRotationAngle(this.leftToe4, -0.5672F, 0.0F, 0.0F);
    this.leftToe4.setTextureOffset(104, 125).addBox(2.0F, -1.5F, -1.5F, 3.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.rightLeg = new ModelRenderer((Model)this);
    this.rightLeg.setRotationPoint(-13.5F, -10.564F, 16.0191F);
    setRotationAngle(this.rightLeg, 0.2182F, 0.0F, 0.0F);
    this.rightLeg.setTextureOffset(52, 80).addBox(0.0F, -12.0F, -7.0F, 7.0F, 24.0F, 14.0F, 0.0F, false);
    
    this.rightLeg2 = new ModelRenderer((Model)this);
    this.rightLeg2.setRotationPoint(0.0F, 9.4613F, -5.3088F);
    this.rightLeg.addChild(this.rightLeg2);
    setRotationAngle(this.rightLeg2, -0.6981F, 0.0F, 0.0F);
    this.rightLeg2.setTextureOffset(46, 120).addBox(0.5F, -4.6299F, 0.6175F, 6.0F, 27.0F, 8.0F, 0.0F, false);
    
    this.rightFeet = new ModelRenderer((Model)this);
    this.rightFeet.setRotationPoint(0.0F, 21.6027F, 5.2897F);
    this.rightLeg2.addChild(this.rightFeet);
    setRotationAngle(this.rightFeet, 0.48F, 0.0F, 0.0F);
    this.rightFeet.setTextureOffset(95, 92).addBox(-1.0F, -2.5F, -5.0F, 9.0F, 5.0F, 10.0F, 0.0F, false);
    
    this.rightFeet2 = new ModelRenderer((Model)this);
    this.rightFeet2.setRotationPoint(0.0F, 0.5F, -3.0F);
    this.rightFeet.addChild(this.rightFeet2);
    this.rightFeet2.setTextureOffset(94, 109).addBox(-1.5F, -2.0F, -5.0F, 10.0F, 4.0F, 10.0F, 0.0F, false);
    
    this.rightToe1 = new ModelRenderer((Model)this);
    this.rightToe1.setRotationPoint(3.8689F, 1.01F, -6.0793F);
    this.rightFeet2.addChild(this.rightToe1);
    setRotationAngle(this.rightToe1, 0.2075F, -0.2612F, -0.0036F);
    this.rightToe1.setTextureOffset(104, 125).addBox(1.8812F, -1.6739F, -3.3871F, 3.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.rightToe2 = new ModelRenderer((Model)this);
    this.rightToe2.setRotationPoint(0.0F, 0.9569F, -6.7225F);
    this.rightFeet2.addChild(this.rightToe2);
    setRotationAngle(this.rightToe2, 0.1745F, 0.0F, 0.0F);
    this.rightToe2.setTextureOffset(104, 125).addBox(2.0F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.rightToe3 = new ModelRenderer((Model)this);
    this.rightToe3.setRotationPoint(-3.7618F, 0.7739F, -5.8025F);
    this.rightFeet2.addChild(this.rightToe3);
    setRotationAngle(this.rightToe3, 0.2188F, 0.1744F, 0.0077F);
    this.rightToe3.setTextureOffset(104, 125).addBox(1.9468F, -1.3945F, -2.4015F, 3.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.rightToe4 = new ModelRenderer((Model)this);
    this.rightToe4.setRotationPoint(0.0F, -2.4597F, 7.6541F);
    this.rightFeet2.addChild(this.rightToe4);
    setRotationAngle(this.rightToe4, -0.5672F, 0.0F, 0.0F);
    this.rightToe4.setTextureOffset(104, 125).addBox(2.0F, -1.5F, -1.5F, 3.0F, 3.0F, 5.0F, 0.0F, false);
    
    this.rightArm = new ModelRenderer((Model)this);
    this.rightArm.setRotationPoint(-13.0F, -7.0F, -16.0F);
    setRotationAngle(this.rightArm, 0.3054F, 0.0F, 0.0F);
    this.rightArm.setTextureOffset(68, 169).addBox(0.5F, -2.5559F, -3.1355F, 6.0F, 11.0F, 6.0F, 0.0F, false);
    
    this.rightArm2 = new ModelRenderer((Model)this);
    this.rightArm2.setRotationPoint(0.0F, 7.4026F, -1.1698F);
    this.rightArm.addChild(this.rightArm2);
    setRotationAngle(this.rightArm2, -1.309F, 0.0F, 0.0F);
    this.rightArm2.setTextureOffset(69, 187).addBox(1.0F, -2.6022F, -3.2765F, 5.0F, 11.0F, 5.0F, 0.0F, false);
    
    this.rightHand = new ModelRenderer((Model)this);
    this.rightHand.setRotationPoint(0.0F, 9.3131F, -0.4006F);
    this.rightArm2.addChild(this.rightHand);
    setRotationAngle(this.rightHand, 0.6545F, 0.0F, 0.0F);
    this.rightHand.setTextureOffset(69, 205).addBox(0.0F, -2.5F, -1.5F, 7.0F, 5.0F, 3.0F, 0.0F, false);
    
    this.rightFinger1 = new ModelRenderer((Model)this);
    this.rightFinger1.setRotationPoint(2.25F, 4.4447F, 0.6321F);
    this.rightHand.addChild(this.rightFinger1);
    setRotationAngle(this.rightFinger1, -1.2217F, 0.0F, 0.0F);
    this.rightFinger1.setTextureOffset(70, 215).addBox(2.5F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.rightFinger2 = new ModelRenderer((Model)this);
    this.rightFinger2.setRotationPoint(0.0F, 4.5397F, 0.4151F);
    this.rightHand.addChild(this.rightFinger2);
    setRotationAngle(this.rightFinger2, -1.2217F, 0.0F, 0.0F);
    this.rightFinger2.setTextureOffset(70, 215).addBox(2.5F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.rightFinger3 = new ModelRenderer((Model)this);
    this.rightFinger3.setRotationPoint(-2.25F, 4.3628F, 0.7644F);
    this.rightHand.addChild(this.rightFinger3);
    setRotationAngle(this.rightFinger3, -1.2217F, 0.0F, 0.0F);
    this.rightFinger3.setTextureOffset(70, 215).addBox(2.5F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.leftArm = new ModelRenderer((Model)this);
    this.leftArm.setRotationPoint(6.0F, -7.0F, -16.0F);
    setRotationAngle(this.leftArm, 0.3054F, 0.0F, 0.0F);
    this.leftArm.setTextureOffset(68, 169).addBox(0.5F, -2.5559F, -3.1355F, 6.0F, 11.0F, 6.0F, 0.0F, false);
    
    this.leftArm2 = new ModelRenderer((Model)this);
    this.leftArm2.setRotationPoint(0.0F, 7.4026F, -1.1698F);
    this.leftArm.addChild(this.leftArm2);
    setRotationAngle(this.leftArm2, -1.309F, 0.0F, 0.0F);
    this.leftArm2.setTextureOffset(69, 187).addBox(1.0F, -2.6022F, -3.2765F, 5.0F, 11.0F, 5.0F, 0.0F, false);
    
    this.leftHand = new ModelRenderer((Model)this);
    this.leftHand.setRotationPoint(0.0F, 9.3131F, -0.4006F);
    this.leftArm2.addChild(this.leftHand);
    setRotationAngle(this.leftHand, 0.6545F, 0.0F, 0.0F);
    this.leftHand.setTextureOffset(69, 205).addBox(0.0F, -2.5F, -1.5F, 7.0F, 5.0F, 3.0F, 0.0F, false);
    
    this.leftFinger1 = new ModelRenderer((Model)this);
    this.leftFinger1.setRotationPoint(2.25F, 4.4447F, 0.6321F);
    this.leftHand.addChild(this.leftFinger1);
    setRotationAngle(this.leftFinger1, -1.2217F, 0.0F, 0.0F);
    this.leftFinger1.setTextureOffset(70, 215).addBox(2.5F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.leftFinger2 = new ModelRenderer((Model)this);
    this.leftFinger2.setRotationPoint(0.0F, 4.5397F, 0.4151F);
    this.leftHand.addChild(this.leftFinger2);
    setRotationAngle(this.leftFinger2, -1.2217F, 0.0F, 0.0F);
    this.leftFinger2.setTextureOffset(70, 215).addBox(2.5F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.leftFinger3 = new ModelRenderer((Model)this);
    this.leftFinger3.setRotationPoint(-2.25F, 4.3628F, 0.7644F);
    this.leftHand.addChild(this.leftFinger3);
    setRotationAngle(this.leftFinger3, -1.2217F, 0.0F, 0.0F);
    this.leftFinger3.setTextureOffset(70, 215).addBox(2.5F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, 0.0F, false);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(-3.5F, -14.9286F, -4.2143F);
    this.body.setTextureOffset(0, 0).addBox(-7.0F, -13.0714F, -10.7857F, 21.0F, 27.0F, 16.0F, 0.0F, false);
    this.body.setTextureOffset(1, 156).addBox(-6.0F, -12.0714F, -16.7857F, 19.0F, 24.0F, 6.0F, 0.0F, false);
    this.body.setTextureOffset(3, 188).addBox(-5.0F, -11.0714F, -21.7857F, 17.0F, 21.0F, 5.0F, 0.0F, false);
    this.body.setTextureOffset(8, 217).addBox(-3.5F, -10.0714F, -23.7857F, 14.0F, 18.0F, 2.0F, 0.0F, false);
    this.body.setTextureOffset(0, 43).addBox(-6.0F, -12.0714F, 5.2143F, 19.0F, 25.0F, 12.0F, 0.0F, false);
    this.body.setTextureOffset(0, 81).addBox(-5.5F, -11.5714F, 17.2143F, 18.0F, 24.0F, 7.0F, 0.0F, false);
    this.body.setTextureOffset(0, 115).addBox(-4.5F, -10.5714F, 24.2143F, 16.0F, 22.0F, 5.0F, 0.0F, false);
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(-3.5F, -19.3015F, -23.9587F);
    this.head.setTextureOffset(75, 146).addBox(-3.5F, -14.1985F, -23.0413F, 14.0F, 15.0F, 5.0F, 0.03F, false);
    
    this.neck3 = new ModelRenderer((Model)this);
    this.neck3.setRotationPoint(3.5F, -6.3693F, -16.9473F);
    this.head.addChild(this.neck3);
    setRotationAngle(this.neck3, 1.3963F, 0.0F, 0.0F);
    this.neck3.setTextureOffset(106, 52).addBox(-6.5F, -3.5F, -7.0F, 13.0F, 7.0F, 14.0F, 0.02F, false);
    
    this.neck2 = new ModelRenderer((Model)this);
    this.neck2.setRotationPoint(3.5F, -4.5383F, -10.4505F);
    this.head.addChild(this.neck2);
    setRotationAngle(this.neck2, 1.2217F, 0.0F, 0.0F);
    this.neck2.setTextureOffset(74, 0).addBox(-6.5F, -4.5F, -7.0F, 13.0F, 9.0F, 14.0F, 0.01F, false);
    
    this.neck1 = new ModelRenderer((Model)this);
    this.neck1.setRotationPoint(3.5F, 1.606F, -0.0608F);
    this.head.addChild(this.neck1);
    setRotationAngle(this.neck1, 0.9599F, 0.0F, 0.0F);
    this.neck1.setTextureOffset(62, 29).addBox(-6.5F, -9.5F, -7.0F, 13.0F, 19.0F, 14.0F, 0.0F, false);
    
    this.upperHead = new ModelRenderer((Model)this);
    this.upperHead.setRotationPoint(0.0F, -9.0255F, -22.121F);
    this.head.addChild(this.upperHead);
    setRotationAngle(this.upperHead, -0.1309F, 0.0F, 0.0F);
    this.upperHead.setTextureOffset(117, 149).addBox(-3.5F, -5.1947F, -6.7478F, 14.0F, 12.0F, 7.0F, 0.0F, false);
    this.upperHead.setTextureOffset(116, 173).addBox(-2.5F, -4.1947F, -12.7478F, 12.0F, 11.0F, 6.0F, 0.0F, false);
    this.upperHead.setTextureOffset(117, 194).addBox(-1.5F, -3.1947F, -20.7478F, 10.0F, 10.0F, 8.0F, 0.0F, false);
    
    this.upperTeeth = new ModelRenderer((Model)this);
    this.upperTeeth.setRotationPoint(0.0F, 7.8053F, -11.0335F);
    this.upperHead.addChild(this.upperTeeth);
    this.upperTeeth.setTextureOffset(102, 20).addBox(7.5F, -1.5F, -8.7143F, 0.0F, 3.0F, 20.0F, 0.0F, false);
    this.upperTeeth.setTextureOffset(102, 17).addBox(-0.5F, -1.5F, -8.7143F, 0.0F, 3.0F, 20.0F, 0.0F, false);
    this.upperTeeth.setTextureOffset(58, 10).addBox(0.0F, -1.5F, -8.7143F, 7.0F, 3.0F, 0.0F, 0.0F, false);
    
    this.lowerHead = new ModelRenderer((Model)this);
    this.lowerHead.setRotationPoint(0.0F, -1.1857F, -21.4852F);
    this.head.addChild(this.lowerHead);
    setRotationAngle(this.lowerHead, 0.2182F, 0.0F, 0.0F);
    this.lowerHead.setTextureOffset(92, 241).addBox(-0.5F, -0.7355F, -21.9123F, 8.0F, 3.0F, 9.0F, 0.0F, false);
    this.lowerHead.setTextureOffset(94, 230).addBox(-1.5F, -0.7355F, -12.9123F, 10.0F, 3.0F, 6.0F, 0.0F, false);
    this.lowerHead.setTextureOffset(93, 219).addBox(-2.5F, -0.7355F, -6.9123F, 12.0F, 3.0F, 6.0F, 0.0F, false);
    
    this.lowerTeeth = new ModelRenderer((Model)this);
    this.lowerTeeth.setRotationPoint(0.0F, -1.7355F, -12.198F);
    this.lowerHead.addChild(this.lowerTeeth);
    this.lowerTeeth.setTextureOffset(102, 14).addBox(7.25F, -1.5F, -8.7143F, 0.0F, 3.0F, 20.0F, 0.0F, false);
    this.lowerTeeth.setTextureOffset(102, 11).addBox(-0.25F, -1.5F, -8.7143F, 0.0F, 3.0F, 20.0F, 0.0F, false);
    this.lowerTeeth.setTextureOffset(58, 13).addBox(0.0F, -1.5F, -8.7143F, 7.0F, 3.0F, 0.0F, 0.0F, false);
    
    this.tail = new ModelRenderer((Model)this);
    this.tail.setRotationPoint(-3.5F, -15.5F, 62.625F);
    this.tail.setTextureOffset(166, 4).addBox(-3.5F, -7.5F, -37.625F, 14.0F, 15.0F, 5.0F, 0.0F, false);
    
    this.tail2 = new ModelRenderer((Model)this);
    this.tail2.setRotationPoint(0.0F, 0.0F, -33.0F);
    this.tail.addChild(this.tail2);
    this.tail2.setTextureOffset(167, 26).addBox(-2.0F, -6.0F, 0.375F, 11.0F, 12.0F, 6.0F, 0.0F, false);
    
    this.tail3 = new ModelRenderer((Model)this);
    this.tail3.setRotationPoint(0.0F, 0.0F, 7.0F);
    this.tail2.addChild(this.tail3);
    this.tail3.setTextureOffset(165, 47).addBox(-1.5F, -5.0F, -0.625F, 10.0F, 10.0F, 10.0F, 0.0F, false);
    
    this.tail4 = new ModelRenderer((Model)this);
    this.tail4.setRotationPoint(0.0F, 0.0F, 9.0F);
    this.tail3.addChild(this.tail4);
    this.tail4.setTextureOffset(165, 69).addBox(-1.0F, -4.5F, -0.125F, 9.0F, 9.0F, 11.0F, 0.0F, false);
    
    this.tail5 = new ModelRenderer((Model)this);
    this.tail5.setRotationPoint(0.0F, 0.0F, 11.0F);
    this.tail4.addChild(this.tail5);
    this.tail5.setTextureOffset(160, 93).addBox(-0.5F, -4.0F, -0.125F, 8.0F, 8.0F, 16.0F, 0.0F, false);
    
    this.tail6 = new ModelRenderer((Model)this);
    this.tail6.setRotationPoint(0.0F, 0.0F, 16.0F);
    this.tail5.addChild(this.tail6);
    this.tail6.setTextureOffset(161, 119).addBox(0.0F, -3.5F, -0.625F, 7.0F, 7.0F, 15.0F, 0.0F, false);
    
    this.tail7 = new ModelRenderer((Model)this);
    this.tail7.setRotationPoint(0.0F, 0.0F, 14.0F);
    this.tail6.addChild(this.tail7);
    this.tail7.setTextureOffset(163, 144).addBox(0.5F, -3.0F, 0.375F, 6.0F, 6.0F, 15.0F, 0.0F, false);
    
    this.tail8 = new ModelRenderer((Model)this);
    this.tail8.setRotationPoint(0.0F, 0.0F, 15.0F);
    this.tail7.addChild(this.tail8);
    this.tail8.setTextureOffset(161, 169).addBox(1.0F, -2.5F, 0.375F, 5.0F, 5.0F, 16.0F, 0.0F, false);
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay);
    this.head.render(matrixStack, buffer, packedLight, packedOverlay);
    this.tail.render(matrixStack, buffer, packedLight, packedOverlay);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    float limbSpeed = 0.3F;
    if (entity.isSprinting())
      limbSpeed = 0.5F; 
    this.rightLeg.rotateAngleX = 0.2F + MathHelper.cos(limbSwing * limbSpeed) * 0.5F * limbSwingAmount;
    this.rightLeg2.rotateAngleX = -0.7F - MathHelper.sin(limbSwing * limbSpeed) * 0.5F * limbSwingAmount;
    this.leftLeg.rotateAngleX = 0.2F + MathHelper.cos(limbSwing * limbSpeed + 3.1415927F) * 0.5F * limbSwingAmount;
    this.leftLeg2.rotateAngleX = -0.7F - MathHelper.sin(limbSwing * limbSpeed + 3.1415927F) * 0.5F * limbSwingAmount;


    
    if (!(entity instanceof xyz.pixelatedw.mineminenomi.entities.DummyEntity))
    {
      if (entity.isSprinting()) {
        
        this.tail3.rotateAngleX = MathHelper.cos(limbSwing * 0.6F) * 0.1F * limbSwingAmount;
        this.tail5.rotateAngleY = (float)(this.tail5.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 20.0D);
        this.tail5.rotateAngleX = (float)(this.tail5.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
        this.tail6.rotateAngleY = (float)(this.tail6.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 20.0D);
        this.tail6.rotateAngleX = (float)(this.tail6.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
        this.tail7.rotateAngleY = (float)(this.tail7.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 20.0D);
        this.tail7.rotateAngleX = (float)(this.tail7.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
        this.tail8.rotateAngleY = (float)(this.tail8.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 20.0D);
        this.tail8.rotateAngleX = (float)(this.tail8.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
      }
      else {
        
        this.tail2.rotateAngleY = (float)(this.tail2.rotateAngleY + Math.sin(ageInTicks * 0.01D) / 20.0D);
        this.tail2.rotateAngleX = (float)(this.tail2.rotateAngleX + Math.sin(ageInTicks * 0.005D) / 10.0D);
        this.tail3.rotateAngleY = (float)(this.tail3.rotateAngleY + Math.sin(ageInTicks * 0.01D) / 20.0D);
        this.tail3.rotateAngleX = (float)(this.tail3.rotateAngleX + Math.sin(ageInTicks * 0.005D) / 10.0D);
        this.tail4.rotateAngleY = (float)(this.tail4.rotateAngleY + Math.sin(ageInTicks * 0.01D) / 20.0D);
        this.tail4.rotateAngleX = (float)(this.tail4.rotateAngleX + Math.sin(ageInTicks * 0.005D) / 10.0D);
        this.tail5.rotateAngleY = (float)(this.tail5.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 20.0D);
        this.tail5.rotateAngleX = (float)(this.tail5.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
        this.tail6.rotateAngleY = (float)(this.tail6.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 20.0D);
        this.tail6.rotateAngleX = (float)(this.tail6.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
        this.tail7.rotateAngleY = (float)(this.tail7.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 20.0D);
        this.tail7.rotateAngleX = (float)(this.tail7.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
        this.tail8.rotateAngleY = (float)(this.tail8.rotateAngleY + Math.sin(ageInTicks * 0.06D) / 20.0D);
        this.tail8.rotateAngleX = (float)(this.tail8.rotateAngleX + Math.sin(ageInTicks * 0.02D) / 10.0D);
      } 
    }

    
    this.swingProgress = ((LivingEntity)entity).swingProgress;
    if (this.swingProgress > 0.0F) {
      
      this.head.rotateAngleY += this.body.rotateAngleY;
      float f1 = 1.0F - this.swingProgress;
      f1 *= f1;
      f1 *= f1;
      f1 = 1.0F - f1;
      float f2 = MathHelper.sin(f1 * 3.1415927F);
      float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.1F) * 0.15F;
      this.head.rotateAngleX = (float)(this.head.rotateAngleX + f2 * 0.8D + f3);
      this.head.rotateAngleZ -= MathHelper.sin(this.swingProgress * 3.1415927F) * -0.8F;
    } 
  }

  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }


  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    this.head.translateRotate(matrixStack);
    matrixStack.scale(2.0F, 2.0F, 2.0F);
    matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
    matrixStack.rotate(Vector3f.XP.rotationDegrees(260.0F));
    matrixStack.translate(0.05D, 0.5D, 0.1D);
  }
  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
}


