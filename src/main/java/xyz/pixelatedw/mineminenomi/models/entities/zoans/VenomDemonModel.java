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

public class VenomDemonModel<T extends LivingEntity>
  extends ZoanMorphModel<T> implements IHasArm {
  private final ModelRenderer head;
  private final ModelRenderer jaw;
  private final ModelRenderer mouth;
  private final ModelRenderer rightHorn;
  private final ModelRenderer rightHorn2;
  private final ModelRenderer rightHorn3;
  private final ModelRenderer leftHorn;
  private final ModelRenderer leftHorn2;
  private final ModelRenderer leftHorn3;
  private final ModelRenderer leftHorn3_r1;
  private final ModelRenderer body;
  private final ModelRenderer body2;
  private final ModelRenderer body3;
  private final ModelRenderer body4;
  private final ModelRenderer body5;
  private final ModelRenderer base;
  private final ModelRenderer base2;
  private final ModelRenderer base3;
  private final ModelRenderer shoulders;
  private final ModelRenderer spine;
  private final ModelRenderer spineb;
  private final ModelRenderer neck;
  private final ModelRenderer spine2;
  private final ModelRenderer spine2b;
  private final ModelRenderer rightWing;
  private final ModelRenderer leftWing;
  private final ModelRenderer leftArm;
  private final ModelRenderer leftArm2;
  private final ModelRenderer leftHand;
  private final ModelRenderer leftHandFinger1;
  private final ModelRenderer leftHandFinger1b;
  private final ModelRenderer leftHandFinger2;
  private final ModelRenderer leftHandFinger2b;
  private final ModelRenderer leftHandFinger3;
  private final ModelRenderer leftHandFinger3b;
  private final ModelRenderer leftHandFinger4;
  private final ModelRenderer leftHandFinger4b;
  private final ModelRenderer leftHandFinger5;
  private final ModelRenderer leftHandFinger5b;
  private final ModelRenderer rightArm;
  private final ModelRenderer rightArm2;
  private final ModelRenderer rightHand;
  private final ModelRenderer rightHandFinger1;
  private final ModelRenderer rightHandFinger1b;
  private final ModelRenderer rightHandFinger2;
  private final ModelRenderer rightHandFinger2b;
  private final ModelRenderer rightHandFinger3;
  private final ModelRenderer rightHandFinger3b;
  private final ModelRenderer rightHandFinger4;
  private final ModelRenderer rightHandFinger4b;
  private final ModelRenderer rightHandFinger5;
  private final ModelRenderer rightHandFinger5b;
  
  public VenomDemonModel() {
    super(1.0F);
    this.textureWidth = 256;
    this.textureHeight = 256;
    
    this.head = new ModelRenderer((Model)this);
    this.head.setRotationPoint(0.5F, -21.0F, -11.5F);
    this.head.setTextureOffset(202, 46).addBox(-3.0F, -3.0F, -6.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);
    
    this.jaw = new ModelRenderer((Model)this);
    this.jaw.setRotationPoint(-3.0F, -3.0F, -6.0F);
    this.head.addChild(this.jaw);
    setRotationAngle(this.jaw, 0.4363F, 0.0F, 0.0F);
    this.jaw.setTextureOffset(240, 49).addBox(0.5F, 7.0F, -2.5F, 4.0F, 2.0F, 4.0F, 0.0F, false);
    
    this.mouth = new ModelRenderer((Model)this);
    this.mouth.setRotationPoint(-3.0F, -3.0F, -6.0F);
    this.head.addChild(this.mouth);
    this.mouth.setTextureOffset(223, 49).addBox(0.5F, 3.9F, 0.6F, 4.0F, 2.0F, 4.0F, 0.0F, false);
    
    this.rightHorn = new ModelRenderer((Model)this);
    this.rightHorn.setRotationPoint(-2.0F, -2.0F, -2.5F);
    this.head.addChild(this.rightHorn);
    setRotationAngle(this.rightHorn, 0.8727F, -0.9599F, -0.1222F);
    this.rightHorn.setTextureOffset(230, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
    
    this.rightHorn2 = new ModelRenderer((Model)this);
    this.rightHorn2.setRotationPoint(0.0F, 0.0F, 2.8F);
    this.rightHorn.addChild(this.rightHorn2);
    setRotationAngle(this.rightHorn2, -0.4363F, 0.1222F, 0.0F);
    this.rightHorn2.setTextureOffset(241, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
    
    this.rightHorn3 = new ModelRenderer((Model)this);
    this.rightHorn3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.rightHorn2.addChild(this.rightHorn3);
    setRotationAngle(this.rightHorn3, -0.2967F, 0.0524F, 0.0F);
    this.rightHorn3.setTextureOffset(252, 1).addBox(0.4F, -2.5F, 1.9F, 1.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.leftHorn = new ModelRenderer((Model)this);
    this.leftHorn.setRotationPoint(1.0F, -2.0F, -2.5F);
    this.head.addChild(this.leftHorn);
    setRotationAngle(this.leftHorn, 0.8727F, 0.9599F, 0.1222F);
    this.leftHorn.setTextureOffset(230, 0).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
    
    this.leftHorn2 = new ModelRenderer((Model)this);
    this.leftHorn2.setRotationPoint(0.0F, 0.0F, 2.8F);
    this.leftHorn.addChild(this.leftHorn2);
    setRotationAngle(this.leftHorn2, -0.4363F, 0.1222F, 0.0F);
    this.leftHorn2.setTextureOffset(241, 0).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
    
    this.leftHorn3 = new ModelRenderer((Model)this);
    this.leftHorn3.setRotationPoint(-1.3145F, 0.4024F, 2.5599F);
    this.leftHorn2.addChild(this.leftHorn3);
    setRotationAngle(this.leftHorn3, -0.2967F, -0.0524F, 0.0F);
    
    this.leftHorn3_r1 = new ModelRenderer((Model)this);
    this.leftHorn3_r1.setRotationPoint(-0.142F, 0.2276F, -0.077F);
    this.leftHorn3.addChild(this.leftHorn3_r1);
    setRotationAngle(this.leftHorn3_r1, 0.0873F, 0.0F, 0.0F);
    this.leftHorn3_r1.setTextureOffset(252, 1).addBox(0.1012F, -2.3755F, -1.03F, 1.0F, 3.0F, 1.0F, 0.0F, false);
    
    this.body = new ModelRenderer((Model)this);
    this.body.setRotationPoint(-10.0F, -19.5F, 6.5F);
    setRotationAngle(this.body, 0.0524F, 0.0F, 0.0F);
    this.body.setTextureOffset(0, 142).addBox(0.0F, 0.0F, -18.0F, 20.0F, 10.0F, 18.0F, 0.0F, false);
    
    this.body2 = new ModelRenderer((Model)this);
    this.body2.setRotationPoint(0.0F, 9.5F, 0.0F);
    this.body.addChild(this.body2);
    setRotationAngle(this.body2, 0.0175F, 0.0F, 0.0F);
    this.body2.setTextureOffset(0, 116).addBox(0.0F, -0.0349F, -17.4988F, 20.0F, 8.0F, 17.0F, 0.0F, false);
    
    this.body3 = new ModelRenderer((Model)this);
    this.body3.setRotationPoint(1.0F, 7.9477F, -0.7482F);
    this.body2.addChild(this.body3);
    this.body3.setTextureOffset(0, 94).addBox(0.0F, 0.0F, -16.0F, 18.0F, 5.0F, 16.0F, 0.0F, false);
    
    this.body4 = new ModelRenderer((Model)this);
    this.body4.setRotationPoint(1.0F, 4.9651F, -0.4988F);
    this.body3.addChild(this.body4);
    this.body4.setTextureOffset(0, 73).addBox(0.0F, 0.0F, -15.0F, 16.0F, 5.0F, 15.0F, 0.0F, false);
    
    this.body5 = new ModelRenderer((Model)this);
    this.body5.setRotationPoint(1.0F, 4.9651F, -0.4988F);
    this.body4.addChild(this.body5);
    this.body5.setTextureOffset(0, 55).addBox(0.0F, 0.0F, -14.0F, 14.0F, 3.0F, 14.0F, 0.0F, false);
    
    this.base = new ModelRenderer((Model)this);
    this.base.setRotationPoint(1.0F, 2.2344F, -12.6971F);
    this.body5.addChild(this.base);
    setRotationAngle(this.base, -0.0524F, 0.0F, 0.0F);
    this.base.setTextureOffset(0, 34).addBox(0.0F, 0.0F, 0.0F, 12.0F, 8.0F, 12.0F, 0.0F, false);
    
    this.base2 = new ModelRenderer((Model)this);
    this.base2.setRotationPoint(-0.5F, 8.1029F, -0.5438F);
    this.base.addChild(this.base2);
    this.base2.setTextureOffset(0, 17).addBox(0.0F, 0.0F, 0.0F, 13.0F, 3.0F, 13.0F, 0.0F, false);
    
    this.base3 = new ModelRenderer((Model)this);
    this.base3.setRotationPoint(-0.5F, 2.9956F, -0.75F);
    this.base2.addChild(this.base3);
    this.base3.setTextureOffset(0, 0).addBox(0.0F, 0.0044F, 0.25F, 14.0F, 2.0F, 14.0F, 0.0F, false);
    
    this.shoulders = new ModelRenderer((Model)this);
    this.shoulders.setRotationPoint(0.5F, -2.4993F, -0.3738F);
    this.body.addChild(this.shoulders);
    setRotationAngle(this.shoulders, -0.1047F, 0.0F, 0.0F);
    this.shoulders.setTextureOffset(0, 171).addBox(0.0F, 0.0F, -17.0F, 19.0F, 5.0F, 17.0F, 0.0F, false);
    
    this.spine = new ModelRenderer((Model)this);
    this.spine.setRotationPoint(9.0F, 1.1399F, -8.8429F);
    this.shoulders.addChild(this.spine);
    setRotationAngle(this.spine, 1.5708F, 0.0F, 0.0F);
    this.spine.setTextureOffset(233, 9).addBox(0.0F, -7.191F, 1.0063F, 1.0F, 17.0F, 1.0F, 0.0F, false);
    
    this.spineb = new ModelRenderer((Model)this);
    this.spineb.setRotationPoint(0.5F, -8.1102F, 1.1063F);
    this.spine.addChild(this.spineb);
    this.spineb.setTextureOffset(243, 7).addBox(0.0F, 0.9193F, 0.0523F, 0.0F, 17.0F, 3.0F, 0.0F, false);
    
    this.neck = new ModelRenderer((Model)this);
    this.neck.setRotationPoint(7.5F, -0.0021F, -18.8738F);
    this.shoulders.addChild(this.neck);
    this.neck.setTextureOffset(189, 49).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
    
    this.spine2 = new ModelRenderer((Model)this);
    this.spine2.setRotationPoint(9.5F, -2.7F, -0.5F);
    this.body.addChild(this.spine2);
    setRotationAngle(this.spine2, -0.0087F, 0.0F, 0.0F);
    this.spine2.setTextureOffset(238, 7).addBox(0.0F, 0.0F, 0.0F, 1.0F, 19.0F, 1.0F, 0.0F, false);
    
    this.spine2b = new ModelRenderer((Model)this);
    this.spine2b.setRotationPoint(0.5F, 0.2F, 1.0F);
    this.spine2.addChild(this.spine2b);
    this.spine2b.setTextureOffset(250, 6).addBox(0.0F, 0.0F, 0.0F, 0.0F, 18.0F, 3.0F, 0.0F, false);
    
    this.rightWing = new ModelRenderer((Model)this);
    this.rightWing.setRotationPoint(4.0F, -0.5F, -0.5F);
    this.body.addChild(this.rightWing);
    setRotationAngle(this.rightWing, 0.203F, 0.2261F, 0.5387F);
    this.rightWing.setTextureOffset(151, 0).addBox(-14.0F, 0.0F, 0.0F, 14.0F, 9.0F, 0.0F, 0.0F, false);
    
    this.leftWing = new ModelRenderer((Model)this);
    this.leftWing.setRotationPoint(16.0F, -0.5F, -0.5F);
    this.body.addChild(this.leftWing);
    setRotationAngle(this.leftWing, 0.203F, -0.2261F, -0.5387F);
    this.leftWing.setTextureOffset(151, 10).addBox(0.0F, 0.0F, 0.0F, 14.0F, 9.0F, 0.0F, 0.0F, false);
    
    this.leftArm = new ModelRenderer((Model)this);
    this.leftArm.setRotationPoint(8.0F, -17.0F, 0.0F);
    setRotationAngle(this.leftArm, -0.5236F, -0.5236F, 0.0F);
    this.leftArm.setTextureOffset(180, 0).addBox(0.0F, 0.0F, -6.0F, 6.0F, 15.0F, 6.0F, 0.0F, false);
    
    this.leftArm2 = new ModelRenderer((Model)this);
    this.leftArm2.setRotationPoint(1.2067F, 13.3142F, 0.4276F);
    this.leftArm.addChild(this.leftArm2);
    setRotationAngle(this.leftArm2, -0.3665F, 0.0436F, 0.4363F);
    this.leftArm2.setTextureOffset(205, 0).addBox(-0.8613F, 0.2473F, -6.5225F, 6.0F, 15.0F, 6.0F, 0.0F, false);
    
    this.leftHand = new ModelRenderer((Model)this);
    this.leftHand.setRotationPoint(-0.9984F, 14.6745F, -0.5989F);
    this.leftArm2.addChild(this.leftHand);
    setRotationAngle(this.leftHand, 0.0262F, 0.0F, 0.0611F);
    this.leftHand.setTextureOffset(232, 28).addBox(0.0F, 0.0F, -6.0F, 6.0F, 2.0F, 6.0F, 0.0F, false);
    
    this.leftHandFinger1 = new ModelRenderer((Model)this);
    this.leftHandFinger1.setRotationPoint(1.4422F, 0.743F, -6.0F);
    this.leftHand.addChild(this.leftHandFinger1);
    setRotationAngle(this.leftHandFinger1, 0.8552F, 0.1309F, 0.0087F);
    this.leftHandFinger1.setTextureOffset(225, 33).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftHandFinger1b = new ModelRenderer((Model)this);
    this.leftHandFinger1b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
    this.leftHandFinger1.addChild(this.leftHandFinger1b);
    setRotationAngle(this.leftHandFinger1b, 0.3142F, 0.0F, 0.0524F);
    this.leftHandFinger1b.setTextureOffset(225, 29).addBox(-1.0F, 0.0828F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftHandFinger2 = new ModelRenderer((Model)this);
    this.leftHandFinger2.setRotationPoint(2.9283F, 0.7596F, -6.203F);
    this.leftHand.addChild(this.leftHandFinger2);
    setRotationAngle(this.leftHandFinger2, 0.8552F, 0.0873F, 0.0087F);
    this.leftHandFinger2.setTextureOffset(225, 33).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftHandFinger2b = new ModelRenderer((Model)this);
    this.leftHandFinger2b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
    this.leftHandFinger2.addChild(this.leftHandFinger2b);
    setRotationAngle(this.leftHandFinger2b, 0.3142F, 0.0F, 0.0524F);
    this.leftHandFinger2b.setTextureOffset(225, 29).addBox(-1.0F, 0.0828F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftHandFinger3 = new ModelRenderer((Model)this);
    this.leftHandFinger3.setRotationPoint(4.4405F, 0.9206F, -6.2036F);
    this.leftHand.addChild(this.leftHandFinger3);
    setRotationAngle(this.leftHandFinger3, 0.8552F, -0.0436F, 0.0087F);
    this.leftHandFinger3.setTextureOffset(225, 33).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftHandFinger3b = new ModelRenderer((Model)this);
    this.leftHandFinger3b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
    this.leftHandFinger3.addChild(this.leftHandFinger3b);
    setRotationAngle(this.leftHandFinger3b, 0.3142F, 0.0F, 0.0524F);
    this.leftHandFinger3b.setTextureOffset(225, 29).addBox(-1.0F, 0.0828F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftHandFinger4 = new ModelRenderer((Model)this);
    this.leftHandFinger4.setRotationPoint(5.9266F, 0.9372F, -6.4066F);
    this.leftHand.addChild(this.leftHandFinger4);
    setRotationAngle(this.leftHandFinger4, 0.9425F, -0.1309F, 0.0087F);
    this.leftHandFinger4.setTextureOffset(225, 33).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftHandFinger4b = new ModelRenderer((Model)this);
    this.leftHandFinger4b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
    this.leftHandFinger4.addChild(this.leftHandFinger4b);
    setRotationAngle(this.leftHandFinger4b, 0.3142F, 0.0F, 0.0524F);
    this.leftHandFinger4b.setTextureOffset(225, 29).addBox(-1.0F, 0.0828F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftHandFinger5 = new ModelRenderer((Model)this);
    this.leftHandFinger5.setRotationPoint(0.117F, 0.3919F, -2.7991F);
    this.leftHand.addChild(this.leftHandFinger5);
    setRotationAngle(this.leftHandFinger5, 0.9425F, 1.6581F, -0.2531F);
    this.leftHandFinger5.setTextureOffset(225, 33).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.leftHandFinger5b = new ModelRenderer((Model)this);
    this.leftHandFinger5b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
    this.leftHandFinger5.addChild(this.leftHandFinger5b);
    setRotationAngle(this.leftHandFinger5b, 0.3142F, 0.0F, 0.0524F);
    this.leftHandFinger5b.setTextureOffset(225, 29).addBox(-1.0F, 0.0828F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightArm = new ModelRenderer((Model)this);
    this.rightArm.setRotationPoint(-13.0F, -17.0F, 2.75F);
    setRotationAngle(this.rightArm, -0.5236F, 0.5236F, 0.0873F);
    this.rightArm.setTextureOffset(180, 0).addBox(0.0F, 0.0F, -6.0F, 6.0F, 15.0F, 6.0F, 0.0F, false);
    
    this.rightArm2 = new ModelRenderer((Model)this);
    this.rightArm2.setRotationPoint(0.7753F, 13.4765F, 0.2337F);
    this.rightArm.addChild(this.rightArm2);
    setRotationAngle(this.rightArm2, -0.3665F, 0.0436F, -0.5236F);
    this.rightArm2.setTextureOffset(205, 0).addBox(-1.1789F, 2.1831F, -5.4493F, 6.0F, 15.0F, 6.0F, 0.0F, false);
    
    this.rightHand = new ModelRenderer((Model)this);
    this.rightHand.setRotationPoint(-0.9984F, 14.6745F, -0.5989F);
    this.rightArm2.addChild(this.rightHand);
    setRotationAngle(this.rightHand, 0.0262F, 0.0F, 0.0611F);
    this.rightHand.setTextureOffset(232, 28).addBox(-0.1988F, 1.9791F, -4.9783F, 6.0F, 2.0F, 6.0F, 0.0F, false);
    
    this.rightHandFinger1 = new ModelRenderer((Model)this);
    this.rightHandFinger1.setRotationPoint(1.4422F, 0.743F, -6.0F);
    this.rightHand.addChild(this.rightHandFinger1);
    setRotationAngle(this.rightHandFinger1, 0.8552F, 0.1309F, 0.0087F);
    this.rightHandFinger1.setTextureOffset(225, 33).addBox(-1.3133F, 2.0461F, -2.8459F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightHandFinger1b = new ModelRenderer((Model)this);
    this.rightHandFinger1b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
    this.rightHandFinger1.addChild(this.rightHandFinger1b);
    setRotationAngle(this.rightHandFinger1b, 0.3142F, 0.0F, 0.0524F);
    this.rightHandFinger1b.setTextureOffset(225, 29).addBox(-1.2058F, 1.7802F, -3.4409F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightHandFinger2 = new ModelRenderer((Model)this);
    this.rightHandFinger2.setRotationPoint(2.9283F, 0.7596F, -6.203F);
    this.rightHand.addChild(this.rightHandFinger2);
    setRotationAngle(this.rightHandFinger2, 0.8552F, 0.0873F, 0.0087F);
    this.rightHandFinger2.setTextureOffset(225, 33).addBox(-1.2699F, 2.0557F, -2.8375F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightHandFinger2b = new ModelRenderer((Model)this);
    this.rightHandFinger2b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
    this.rightHandFinger2.addChild(this.rightHandFinger2b);
    setRotationAngle(this.rightHandFinger2b, 0.3142F, 0.0F, 0.0524F);
    this.rightHandFinger2b.setTextureOffset(225, 29).addBox(-1.1619F, 1.7898F, -3.4352F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightHandFinger3 = new ModelRenderer((Model)this);
    this.rightHandFinger3.setRotationPoint(4.4405F, 0.9206F, -6.2036F);
    this.rightHand.addChild(this.rightHandFinger3);
    setRotationAngle(this.rightHandFinger3, 0.8552F, -0.0436F, 0.0087F);
    this.rightHandFinger3.setTextureOffset(225, 33).addBox(-1.1368F, 2.0758F, -2.82F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightHandFinger3b = new ModelRenderer((Model)this);
    this.rightHandFinger3b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
    this.rightHandFinger3.addChild(this.rightHandFinger3b);
    setRotationAngle(this.rightHandFinger3b, 0.3142F, 0.0F, 0.0524F);
    this.rightHandFinger3b.setTextureOffset(225, 29).addBox(-1.028F, 1.8077F, -3.4227F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightHandFinger4 = new ModelRenderer((Model)this);
    this.rightHandFinger4.setRotationPoint(5.9266F, 0.9372F, -6.4066F);
    this.rightHand.addChild(this.rightHandFinger4);
    setRotationAngle(this.rightHandFinger4, 0.9425F, -0.1309F, 0.0087F);
    this.rightHandFinger4.setTextureOffset(225, 33).addBox(-1.0466F, 2.0029F, -2.9931F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightHandFinger4b = new ModelRenderer((Model)this);
    this.rightHandFinger4b.setRotationPoint(0.0131F, -0.0528F, -1.9653F);
    this.rightHandFinger4.addChild(this.rightHandFinger4b);
    setRotationAngle(this.rightHandFinger4b, 0.3142F, 0.0F, 0.0524F);
    this.rightHandFinger4b.setTextureOffset(225, 29).addBox(-0.9417F, 1.6805F, -3.5633F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightHandFinger5 = new ModelRenderer((Model)this);
    this.rightHandFinger5.setRotationPoint(4.642F, 1.0653F, -2.5333F);
    this.rightHand.addChild(this.rightHandFinger5);
    setRotationAngle(this.rightHandFinger5, 2.0769F, 1.6581F, -0.2531F);
    this.rightHandFinger5.setTextureOffset(225, 33).addBox(-1.9578F, -1.5821F, -3.2568F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.rightHandFinger5b = new ModelRenderer((Model)this);
    this.rightHandFinger5b.setRotationPoint(0.4204F, 0.3134F, -2.0771F);
    this.rightHandFinger5.addChild(this.rightHandFinger5b);
    setRotationAngle(this.rightHandFinger5b, -0.3142F, 0.0F, 0.0524F);
    this.rightHandFinger5b.setTextureOffset(225, 29).addBox(-2.4653F, -1.3458F, -3.4515F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    
    this.bipedBody = this.body;
    this.bipedHead = this.head;
    this.bipedRightArm = this.rightArm;
    this.bipedLeftArm = this.leftArm;
  }


  
  public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
  }



  
  public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
    
    this.swingProgress = ((LivingEntity)entity).swingProgress;
    if (this.swingProgress > 0.0F) {
      
      this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.02F;
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



  
  public void translateHand(HandSide side, MatrixStack matrixStack) {
    super.translateHand(side, matrixStack);
    matrixStack.translate(0.7D, 1.3D, -0.4D);
    matrixStack.rotate(Vector3f.YP.rotationDegrees(-20.0F));
    matrixStack.rotate(Vector3f.XP.rotationDegrees(-30.0F));
  }


  
  public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
    if (side == HandSide.RIGHT) {
      
      matrixStack.translate(-1.4D, -0.9D, 0.2D);
      matrixStack.rotate(Vector3f.YP.rotationDegrees(70.0F));
      matrixStack.rotate(Vector3f.XP.rotationDegrees(40.0F));
      this.rightArm2.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
    else {
      
      matrixStack.translate(0.7D, -0.6D, -0.5D);
      matrixStack.rotate(Vector3f.ZP.rotationDegrees(30.0F));
      matrixStack.rotate(Vector3f.XP.rotationDegrees(30.0F));
      matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
      this.leftArm2.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    } 
  }


  
  public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}


  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}


