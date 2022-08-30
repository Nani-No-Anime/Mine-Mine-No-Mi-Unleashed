/*     */ package xyz.pixelatedw.mineminenomi.models.entities.zoans;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ 
/*     */ public class ZouGuardModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer snout;
/*     */   private final ModelRenderer snout2;
/*     */   private final ModelRenderer snout3;
/*     */   private final ModelRenderer rightTusk;
/*     */   private final ModelRenderer rightTusk2;
/*     */   private final ModelRenderer leftTusk;
/*     */   private final ModelRenderer leftTusk2;
/*     */   private final ModelRenderer leftEar;
/*     */   private final ModelRenderer rightEar;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer rightRearLeg;
/*     */   private final ModelRenderer leftRearLeg;
/*     */   private final ModelRenderer rightFrontLeg;
/*     */   private final ModelRenderer leftFrontLeg;
/*     */   
/*     */   public ZouGuardModel() {
/*  37 */     super(1.0F);
/*  38 */     this.textureWidth = 128;
/*  39 */     this.textureHeight = 64;
/*     */     
/*  41 */     this.head = new ModelRenderer((Model)this);
/*  42 */     this.head.setRotationPoint(0.0F, -1.0F, -14.0F);
/*  43 */     this.head.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 11.0F, 9.0F, 0.0F, false);
/*     */     
/*  45 */     this.snout = new ModelRenderer((Model)this);
/*  46 */     this.snout.setRotationPoint(0.0F, 6.0F, -4.5F);
/*  47 */     this.head.addChild(this.snout);
/*  48 */     setRotationAngle(this.snout, -0.1745F, 0.0F, 0.0F);
/*  49 */     this.snout.setTextureOffset(108, 8).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/*  51 */     this.snout2 = new ModelRenderer((Model)this);
/*  52 */     this.snout2.setRotationPoint(0.0F, 6.0F, 0.0F);
/*  53 */     this.snout.addChild(this.snout2);
/*  54 */     setRotationAngle(this.snout2, 0.1745F, 0.0F, 0.0F);
/*  55 */     this.snout2.setTextureOffset(108, 20).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/*  57 */     this.snout3 = new ModelRenderer((Model)this);
/*  58 */     this.snout3.setRotationPoint(0.0F, 5.5F, 0.5F);
/*  59 */     this.snout2.addChild(this.snout3);
/*  60 */     setRotationAngle(this.snout3, 0.1745F, 0.0F, 0.0F);
/*  61 */     this.snout3.setTextureOffset(108, 30).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
/*     */     
/*  63 */     this.rightTusk = new ModelRenderer((Model)this);
/*  64 */     this.rightTusk.setRotationPoint(2.3F, 5.0F, -5.0F);
/*  65 */     this.head.addChild(this.rightTusk);
/*  66 */     setRotationAngle(this.rightTusk, -0.3491F, -0.2094F, 0.0F);
/*  67 */     this.rightTusk.setTextureOffset(15, 21).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/*  69 */     this.rightTusk2 = new ModelRenderer((Model)this);
/*  70 */     this.rightTusk2.setRotationPoint(0.0F, 3.8F, 0.0F);
/*  71 */     this.rightTusk.addChild(this.rightTusk2);
/*  72 */     setRotationAngle(this.rightTusk2, -0.1745F, 0.0F, 0.0F);
/*  73 */     this.rightTusk2.setTextureOffset(15, 27).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/*  75 */     this.leftTusk = new ModelRenderer((Model)this);
/*  76 */     this.leftTusk.setRotationPoint(-2.3F, 5.0F, -5.0F);
/*  77 */     this.head.addChild(this.leftTusk);
/*  78 */     setRotationAngle(this.leftTusk, -0.3491F, 0.2094F, 0.0F);
/*  79 */     this.leftTusk.setTextureOffset(15, 21).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/*  81 */     this.leftTusk2 = new ModelRenderer((Model)this);
/*  82 */     this.leftTusk2.setRotationPoint(-0.039F, 5.5022F, 0.3754F);
/*  83 */     this.leftTusk.addChild(this.leftTusk2);
/*  84 */     setRotationAngle(this.leftTusk2, -0.1745F, 0.0F, 0.0F);
/*  85 */     this.leftTusk2.setTextureOffset(15, 27).addBox(-0.461F, -1.6998F, -1.2926F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/*  87 */     this.leftEar = new ModelRenderer((Model)this);
/*  88 */     this.leftEar.setRotationPoint(3.0F, -0.5F, -2.5F);
/*  89 */     this.head.addChild(this.leftEar);
/*  90 */     setRotationAngle(this.leftEar, -0.1368F, -0.4707F, 0.2946F);
/*  91 */     this.leftEar.setTextureOffset(0, 21).addBox(0.0F, -4.5F, -0.5F, 6.0F, 9.0F, 1.0F, 0.0F, true);
/*     */     
/*  93 */     this.rightEar = new ModelRenderer((Model)this);
/*  94 */     this.rightEar.setRotationPoint(-3.0F, -0.5F, -2.5F);
/*  95 */     this.head.addChild(this.rightEar);
/*  96 */     setRotationAngle(this.rightEar, -0.1368F, 0.4707F, -0.2946F);
/*  97 */     this.rightEar.setTextureOffset(0, 21).addBox(-6.0F, -4.5F, -0.5F, 6.0F, 9.0F, 1.0F, 0.0F, false);
/*     */     
/*  99 */     this.body = new ModelRenderer((Model)this);
/* 100 */     this.body.setRotationPoint(0.0F, 9.0F, -4.0F);
/* 101 */     this.body.setTextureOffset(36, 25).addBox(-7.5F, -12.0F, -8.0F, 15.0F, 15.0F, 24.0F, 0.0F, false);
/*     */     
/* 103 */     this.tail = new ModelRenderer((Model)this);
/* 104 */     this.tail.setRotationPoint(0.0F, -7.0F, 15.5F);
/* 105 */     this.body.addChild(this.tail);
/* 106 */     setRotationAngle(this.tail, 0.3491F, 0.0F, 0.0F);
/* 107 */     this.tail.setTextureOffset(108, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, 0.0F, false);
/*     */     
/* 109 */     this.tail2 = new ModelRenderer((Model)this);
/* 110 */     this.tail2.setRotationPoint(0.0F, 5.5F, 0.0F);
/* 111 */     this.tail.addChild(this.tail2);
/* 112 */     this.tail2.setTextureOffset(113, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
/*     */     
/* 114 */     this.body2 = new ModelRenderer((Model)this);
/* 115 */     this.body2.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 116 */     this.body.addChild(this.body2);
/* 117 */     this.body2.setTextureOffset(36, 0).addBox(-6.5F, -13.0F, -7.0F, 13.0F, 2.0F, 22.0F, 0.0F, false);
/*     */     
/* 119 */     this.rightRearLeg = new ModelRenderer((Model)this);
/* 120 */     this.rightRearLeg.setRotationPoint(-5.0F, 11.0F, 9.5F);
/* 121 */     this.rightRearLeg.setTextureOffset(0, 46).addBox(-2.25F, 0.0F, -2.75F, 5.0F, 13.0F, 5.0F, 0.0F, false);
/*     */     
/* 123 */     this.leftRearLeg = new ModelRenderer((Model)this);
/* 124 */     this.leftRearLeg.setRotationPoint(5.0F, 11.0F, 9.5F);
/* 125 */     this.leftRearLeg.setTextureOffset(0, 46).addBox(-2.75F, 0.0F, -2.75F, 5.0F, 13.0F, 5.0F, 0.0F, false);
/*     */     
/* 127 */     this.rightFrontLeg = new ModelRenderer((Model)this);
/* 128 */     this.rightFrontLeg.setRotationPoint(-5.0F, 11.0F, -9.5F);
/* 129 */     this.rightFrontLeg.setTextureOffset(0, 46).addBox(-2.25F, 0.0F, -2.25F, 5.0F, 13.0F, 5.0F, 0.0F, false);
/*     */     
/* 131 */     this.leftFrontLeg = new ModelRenderer((Model)this);
/* 132 */     this.leftFrontLeg.setRotationPoint(5.0F, 11.0F, -9.5F);
/* 133 */     this.leftFrontLeg.setTextureOffset(0, 46).addBox(-2.75F, 0.0F, -2.25F, 5.0F, 13.0F, 5.0F, 0.0F, false);
/*     */     
/* 135 */     this.bipedHead = this.head;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 141 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 142 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 143 */     this.rightRearLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 144 */     this.leftRearLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 145 */     this.rightFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 146 */     this.leftFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 153 */     boolean flag1 = entity.isActualySwimming();
/* 154 */     this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;
/* 155 */     if (this.swimAnimation > 0.0F) {
/*     */       
/* 157 */       if (flag1) {
/* 158 */         this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, -0.7853982F, this.swimAnimation);
/*     */       } else {
/* 160 */         this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
/*     */       } 
/*     */     } else {
/*     */       
/* 164 */       this.bipedHead.rotateAngleX = headPitch * 0.017453292F;
/* 165 */       if (this.bipedHead.rotateAngleX > 0.6D) {
/* 166 */         this.bipedHead.rotateAngleX = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/* 170 */     this.rightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
/* 171 */     this.leftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount;
/* 172 */     this.rightRearLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.3F * limbSwingAmount;
/* 173 */     this.leftRearLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.4F * limbSwingAmount;
/* 174 */     if (entity.isSprinting()) {
/*     */       
/* 176 */       this.tail.rotateAngleX = 0.6F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 177 */       this.leftEar.rotateAngleY = -0.3F - MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 178 */       this.rightEar.rotateAngleY = 0.3F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/*     */     } 
/*     */ 
/*     */     
/* 182 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 183 */     if (this.swingProgress > 0.0F) {
/*     */       
/* 185 */       this.head.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 186 */       this.head.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 187 */       this.snout.rotateAngleY += this.body2.rotateAngleY;
/* 188 */       float f1 = 1.0F - this.swingProgress;
/* 189 */       f1 *= f1;
/* 190 */       f1 *= f1;
/* 191 */       f1 = 1.0F - f1;
/* 192 */       float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 193 */       float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 194 */       this.snout.rotateAngleX = (float)(this.snout.rotateAngleX - f2 * 1.2D + f3);
/* 195 */       this.snout.rotateAngleY += this.body.rotateAngleY * 2.0F;
/* 196 */       this.snout.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */     } 
/*     */ 
/*     */     
/* 200 */     if (entity.isElytraFlying()) {
/*     */       
/* 202 */       this.rightEar.rotateAngleX = 1.2F;
/* 203 */       this.rightEar.rotateAngleY = 0.1F;
/* 204 */       this.rightEar.rotateAngleZ = MathHelper.cos(ageInTicks) * 0.6F;
/*     */       
/* 206 */       this.leftEar.rotateAngleX = 1.2F;
/* 207 */       this.leftEar.rotateAngleY = 0.1F;
/* 208 */       this.leftEar.rotateAngleZ = -MathHelper.cos(ageInTicks) * 0.6F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 214 */     modelRenderer.rotateAngleX = x;
/* 215 */     modelRenderer.rotateAngleY = y;
/* 216 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 232 */     this.head.translateRotate(matrixStack);
/* 233 */     this.snout.translateRotate(matrixStack);
/* 234 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
/* 235 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(260.0F));
/* 236 */     matrixStack.translate(0.8D, -0.6D, 0.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\ZouGuardModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */