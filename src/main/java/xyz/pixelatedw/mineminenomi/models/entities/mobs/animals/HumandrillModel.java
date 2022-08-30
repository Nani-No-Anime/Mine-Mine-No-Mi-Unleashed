/*     */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.HumandrillEntity;
/*     */ 
/*     */ public class HumandrillModel<T extends HumandrillEntity> extends BipedModel<T> {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer head6_r1;
/*     */   private final ModelRenderer head5_r1;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer leftFeet;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer leftHand;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer righHand;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer rightFeet;
/*     */   
/*     */   public HumandrillModel() {
/*  34 */     super(1.0F);
/*  35 */     this.textureWidth = 128;
/*  36 */     this.textureHeight = 128;
/*     */     
/*  38 */     this.bipedBody.showModel = false;
/*  39 */     this.bipedHead.showModel = false;
/*  40 */     this.bipedHeadwear.showModel = false;
/*  41 */     this.bipedLeftArm.showModel = false;
/*  42 */     this.bipedLeftLeg.showModel = false;
/*  43 */     this.bipedRightArm.showModel = false;
/*  44 */     this.bipedRightLeg.showModel = false;
/*     */     
/*  46 */     this.head = new ModelRenderer((Model)this);
/*  47 */     this.head.setRotationPoint(0.0F, -11.0F, -1.0F);
/*  48 */     this.head.setTextureOffset(51, 0).addBox(-4.0F, -3.0F, -6.0F, 9.0F, 2.0F, 7.0F, 0.0F, false);
/*  49 */     this.head.setTextureOffset(84, 12).addBox(-2.0F, -6.0F, -6.0F, 5.0F, 3.0F, 6.0F, 0.0F, false);
/*  50 */     this.head.setTextureOffset(90, 76).addBox(-1.0F, -8.0F, -5.0F, 3.0F, 2.0F, 4.0F, 0.0F, false);
/*  51 */     this.head.setTextureOffset(71, 76).addBox(-2.0F, -1.0F, -7.0F, 5.0F, 2.0F, 8.0F, 0.0F, false);
/*  52 */     this.head.setTextureOffset(76, 2).addBox(-1.0F, 1.0F, -7.0F, 3.0F, 1.0F, 8.0F, 0.0F, false);
/*  53 */     this.head.setTextureOffset(82, 67).addBox(-1.0F, -2.0F, -7.0F, 3.0F, 1.0F, 7.0F, 0.0F, false);
/*     */     
/*  55 */     this.head6_r1 = new ModelRenderer((Model)this);
/*  56 */     this.head6_r1.setRotationPoint(3.082F, -2.1495F, -4.0F);
/*  57 */     this.head.addChild(this.head6_r1);
/*  58 */     setRotationAngle(this.head6_r1, 0.0F, 0.0F, -0.5672F);
/*  59 */     this.head6_r1.setTextureOffset(90, 39).addBox(-2.0F, 0.0F, -1.5F, 3.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/*  61 */     this.head5_r1 = new ModelRenderer((Model)this);
/*  62 */     this.head5_r1.setRotationPoint(-2.082F, -2.1495F, -4.0F);
/*  63 */     this.head.addChild(this.head5_r1);
/*  64 */     setRotationAngle(this.head5_r1, 0.0F, 0.0F, 0.5672F);
/*  65 */     this.head5_r1.setTextureOffset(25, 89).addBox(-1.0F, 0.0F, -1.5F, 3.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/*  67 */     this.body = new ModelRenderer((Model)this);
/*  68 */     this.body.setRotationPoint(0.0F, 24.0F, 0.0F);
/*  69 */     this.body.setTextureOffset(46, 39).addBox(-8.0F, -36.0F, -4.0F, 17.0F, 1.0F, 9.0F, 0.0F, false);
/*  70 */     this.body.setTextureOffset(0, 23).addBox(-9.0F, -35.0F, -4.0F, 19.0F, 4.0F, 11.0F, 0.0F, false);
/*  71 */     this.body.setTextureOffset(0, 0).addBox(-9.0F, -31.0F, -4.0F, 19.0F, 10.0F, 12.0F, 0.0F, false);
/*  72 */     this.body.setTextureOffset(0, 83).addBox(-5.0F, -31.0F, -5.0F, 11.0F, 10.0F, 1.0F, 0.0F, false);
/*  73 */     this.body.setTextureOffset(50, 74).addBox(-6.0F, -30.0F, -4.75F, 13.0F, 8.0F, 1.0F, 0.0F, false);
/*  74 */     this.body.setTextureOffset(0, 39).addBox(-8.0F, -21.0F, -4.0F, 17.0F, 1.0F, 11.0F, 0.0F, false);
/*  75 */     this.body.setTextureOffset(47, 50).addBox(-7.0F, -20.0F, -4.0F, 15.0F, 1.0F, 10.0F, 0.0F, false);
/*  76 */     this.body.setTextureOffset(50, 84).addBox(-4.0F, -32.0F, -4.75F, 9.0F, 12.0F, 1.0F, 0.0F, false);
/*     */     
/*  78 */     this.leftLeg = new ModelRenderer((Model)this);
/*  79 */     this.leftLeg.setRotationPoint(4.0F, 5.0F, 1.0F);
/*  80 */     this.leftLeg.setTextureOffset(25, 74).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);
/*     */     
/*  82 */     this.leftLeg2 = new ModelRenderer((Model)this);
/*  83 */     this.leftLeg2.setRotationPoint(3.0F, 19.0F, -1.0F);
/*  84 */     this.leftLeg.addChild(this.leftLeg2);
/*  85 */     this.leftLeg2.setTextureOffset(88, 88).addBox(-5.0F, -11.0F, -1.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
/*     */     
/*  87 */     this.leftFeet = new ModelRenderer((Model)this);
/*  88 */     this.leftFeet.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  89 */     this.leftLeg2.addChild(this.leftFeet);
/*  90 */     this.leftFeet.setTextureOffset(61, 31).addBox(-5.0F, -2.0F, -3.0F, 4.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/*  92 */     this.leftArm = new ModelRenderer((Model)this);
/*  93 */     this.leftArm.setRotationPoint(9.0F, -5.75F, 3.0F);
/*  94 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, 1.5708F);
/*  95 */     this.leftArm.setTextureOffset(55, 15).addBox(-3.0608F, -7.3054F, -5.0F, 10.0F, 7.0F, 8.0F, 0.0F, false);
/*     */     
/*  97 */     this.leftArm2 = new ModelRenderer((Model)this);
/*  98 */     this.leftArm2.setRotationPoint(-13.0608F, 26.6946F, -3.0F);
/*  99 */     this.leftArm.addChild(this.leftArm2);
/* 100 */     this.leftArm2.setTextureOffset(60, 62).addBox(20.0F, -33.0F, -1.0F, 8.0F, 5.0F, 6.0F, 0.0F, false);
/*     */     
/* 102 */     this.leftHand = new ModelRenderer((Model)this);
/* 103 */     this.leftHand.setRotationPoint(1.0608F, 0.0554F, 0.0F);
/* 104 */     this.leftArm2.addChild(this.leftHand);
/* 105 */     this.leftHand.setTextureOffset(88, 50).addBox(27.0F, -32.0F, 0.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
/*     */     
/* 107 */     this.rightArm = new ModelRenderer((Model)this);
/* 108 */     this.rightArm.setRotationPoint(-7.0F, -5.75F, 3.0F);
/* 109 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 1.5708F);
/* 110 */     this.rightArm.setTextureOffset(0, 52).addBox(-3.0608F, 1.6946F, -5.0F, 10.0F, 7.0F, 8.0F, 0.0F, false);
/*     */     
/* 112 */     this.rightArm2 = new ModelRenderer((Model)this);
/* 113 */     this.rightArm2.setRotationPoint(-13.0608F, 35.6946F, -3.0F);
/* 114 */     this.rightArm.addChild(this.rightArm2);
/* 115 */     this.rightArm2.setTextureOffset(31, 62).addBox(20.0F, -33.0F, -1.0F, 8.0F, 5.0F, 6.0F, 0.0F, false);
/*     */     
/* 117 */     this.righHand = new ModelRenderer((Model)this);
/* 118 */     this.righHand.setRotationPoint(1.0608F, 0.0554F, 0.0F);
/* 119 */     this.rightArm2.addChild(this.righHand);
/* 120 */     this.righHand.setTextureOffset(80, 31).addBox(27.0F, -32.0F, 0.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
/*     */     
/* 122 */     this.rightLeg = new ModelRenderer((Model)this);
/* 123 */     this.rightLeg.setRotationPoint(-3.0F, 5.0F, 1.0F);
/* 124 */     this.rightLeg.setTextureOffset(0, 68).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);
/*     */     
/* 126 */     this.rightLeg2 = new ModelRenderer((Model)this);
/* 127 */     this.rightLeg2.setRotationPoint(3.0F, 19.0F, -1.0F);
/* 128 */     this.rightLeg.addChild(this.rightLeg2);
/* 129 */     this.rightLeg2.setTextureOffset(71, 87).addBox(-5.0F, -11.0F, -1.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
/*     */     
/* 131 */     this.rightFeet = new ModelRenderer((Model)this);
/* 132 */     this.rightFeet.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 133 */     this.rightLeg2.addChild(this.rightFeet);
/* 134 */     this.rightFeet.setTextureOffset(29, 52).addBox(-5.0F, -2.0F, -3.0F, 4.0F, 2.0F, 5.0F, 0.0F, false);
/*     */     
/* 136 */     this.bipedBody = this.body;
/* 137 */     this.bipedHead = this.head;
/* 138 */     this.bipedRightArm = this.rightArm;
/* 139 */     this.bipedLeftArm = this.leftArm;
/* 140 */     this.bipedRightLeg = this.rightLeg;
/* 141 */     this.bipedLeftLeg = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 147 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay);
/* 148 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay);
/* 149 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 150 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 151 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 152 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 161 */     this.rightArm.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / 1.0F;
/* 162 */     this.leftArm.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / 1.0F;
/* 163 */     this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / 1.0F;
/* 164 */     this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / 1.0F;
/* 165 */     if (!entity.getHeldItemMainhand().isEmpty()) {
/* 166 */       this.rightArm.rotateAngleY -= -0.15F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 171 */     modelRenderer.rotateAngleX = x;
/* 172 */     modelRenderer.rotateAngleY = y;
/* 173 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 179 */     super.translateHand(side, matrixStack);
/* 180 */     matrixStack.rotate(Vector3f.ZN.rotationDegrees(90.0F));
/* 181 */     matrixStack.translate(-0.25D, 0.55D, -0.05D);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\animals\HumandrillModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */