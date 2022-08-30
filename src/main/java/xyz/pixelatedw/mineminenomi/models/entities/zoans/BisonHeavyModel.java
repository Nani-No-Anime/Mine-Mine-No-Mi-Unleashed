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
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ 
/*     */ public class BisonHeavyModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer rightHand;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer leftHand;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer rightLeg3;
/*     */   private final ModelRenderer rightFeet;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer leftLeg3;
/*     */   private final ModelRenderer leftFeet;
/*     */   private final ModelRenderer upperBody;
/*     */   private final ModelRenderer lowerBody;
/*     */   private final ModelRenderer hunch;
/*     */   private final ModelRenderer rightShoulder;
/*     */   private final ModelRenderer leftShoulder;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer leftHorn1;
/*     */   private final ModelRenderer leftHorn2;
/*     */   private final ModelRenderer rightHorn1;
/*     */   private final ModelRenderer rightHorn2;
/*     */   
/*     */   public BisonHeavyModel() {
/*  45 */     super(1.0F);
/*  46 */     this.textureWidth = 128;
/*  47 */     this.textureHeight = 64;
/*     */     
/*  49 */     this.rightArm = new ModelRenderer((Model)this);
/*  50 */     this.rightArm.setRotationPoint(-5.0F, -2.6F, 0.0F);
/*  51 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.2094F);
/*  52 */     this.rightArm.setTextureOffset(23, 30).addBox(-4.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, -0.01F, false);
/*     */     
/*  54 */     this.rightArm2 = new ModelRenderer((Model)this);
/*  55 */     this.rightArm2.setRotationPoint(-0.2445F, 0.052F, 0.0F);
/*  56 */     this.rightArm.addChild(this.rightArm2);
/*  57 */     setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.2793F);
/*  58 */     this.rightArm2.setTextureOffset(23, 42).addBox(-4.5F, 5.8F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);
/*     */     
/*  60 */     this.rightHand = new ModelRenderer((Model)this);
/*  61 */     this.rightHand.setRotationPoint(-2.5201F, 12.2906F, 0.25F);
/*  62 */     this.rightArm2.addChild(this.rightHand);
/*  63 */     setRotationAngle(this.rightHand, 0.1745F, 1.5708F, 0.0175F);
/*  64 */     this.rightHand.setTextureOffset(0, 25).addBox(-1.2353F, 0.3094F, -1.0712F, 3.0F, 3.0F, 1.0F, 0.2F, false);
/*     */     
/*  66 */     this.leftArm = new ModelRenderer((Model)this);
/*  67 */     this.leftArm.setRotationPoint(5.0F, -2.6F, 0.0F);
/*  68 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.2094F);
/*  69 */     this.leftArm.setTextureOffset(23, 30).addBox(0.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, -0.01F, false);
/*     */     
/*  71 */     this.leftArm2 = new ModelRenderer((Model)this);
/*  72 */     this.leftArm2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  73 */     this.leftArm.addChild(this.leftArm2);
/*  74 */     setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.2793F);
/*  75 */     this.leftArm2.setTextureOffset(23, 42).addBox(2.0F, 5.8F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);
/*     */     
/*  77 */     this.leftHand = new ModelRenderer((Model)this);
/*  78 */     this.leftHand.setRotationPoint(3.9781F, 1.9722F, 0.0F);
/*  79 */     this.leftArm2.addChild(this.leftHand);
/*  80 */     setRotationAngle(this.leftHand, 0.1745F, -1.5708F, 0.0F);
/*  81 */     this.leftHand.setTextureOffset(0, 25).addBox(-1.4781F, 10.6278F, -1.8489F, 3.0F, 3.0F, 1.0F, 0.2F, false);
/*     */     
/*  83 */     this.rightLeg = new ModelRenderer((Model)this);
/*  84 */     this.rightLeg.setRotationPoint(-3.0F, 11.6F, 0.5F);
/*  85 */     setRotationAngle(this.rightLeg, -0.3491F, 0.0F, 0.0F);
/*  86 */     this.rightLeg.setTextureOffset(9, 30).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);
/*     */     
/*  88 */     this.rightLeg2 = new ModelRenderer((Model)this);
/*  89 */     this.rightLeg2.setRotationPoint(0.0F, 5.9802F, 1.1124F);
/*  90 */     this.rightLeg.addChild(this.rightLeg2);
/*  91 */     setRotationAngle(this.rightLeg2, 1.8151F, 0.0F, 0.0F);
/*  92 */     this.rightLeg2.setTextureOffset(9, 41).addBox(-1.0F, -0.5326F, -0.7283F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/*  94 */     this.rightLeg3 = new ModelRenderer((Model)this);
/*  95 */     this.rightLeg3.setRotationPoint(0.0F, 0.2827F, 6.0278F);
/*  96 */     this.rightLeg2.addChild(this.rightLeg3);
/*  97 */     setRotationAngle(this.rightLeg3, -2.0071F, 0.0F, 0.0F);
/*  98 */     this.rightLeg3.setTextureOffset(0, 30).addBox(-1.0F, 3.1F, 4.8F, 2.0F, 6.0F, 2.0F, -0.01F, false);
/*     */     
/* 100 */     this.rightFeet = new ModelRenderer((Model)this);
/* 101 */     this.rightFeet.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 102 */     this.rightLeg3.addChild(this.rightFeet);
/* 103 */     setRotationAngle(this.rightFeet, 0.5236F, 0.0F, 0.0F);
/* 104 */     this.rightFeet.setTextureOffset(0, 39).addBox(-1.0F, 10.25F, -0.5044F, 2.0F, 2.0F, 2.0F, 0.01F, false);
/*     */     
/* 106 */     this.leftLeg = new ModelRenderer((Model)this);
/* 107 */     this.leftLeg.setRotationPoint(3.5F, 11.6F, 1.0F);
/* 108 */     setRotationAngle(this.leftLeg, -0.3491F, 0.0F, 0.0F);
/* 109 */     this.leftLeg.setTextureOffset(9, 30).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 7.0F, 3.0F, 0.0F, false);
/*     */     
/* 111 */     this.leftLeg2 = new ModelRenderer((Model)this);
/* 112 */     this.leftLeg2.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 113 */     this.leftLeg.addChild(this.leftLeg2);
/* 114 */     setRotationAngle(this.leftLeg2, 1.8151F, 0.0F, 0.0F);
/* 115 */     this.leftLeg2.setTextureOffset(9, 41).addBox(-1.5F, -1.5F, -6.8F, 2.0F, 4.0F, 2.0F, 0.0F, false);
/*     */     
/* 117 */     this.leftLeg3 = new ModelRenderer((Model)this);
/* 118 */     this.leftLeg3.setRotationPoint(0.0F, -0.163F, 0.2021F);
/* 119 */     this.leftLeg2.addChild(this.leftLeg3);
/* 120 */     setRotationAngle(this.leftLeg3, -2.0071F, 0.0F, 0.0F);
/* 121 */     this.leftLeg3.setTextureOffset(0, 30).addBox(-1.5F, 3.5143F, 4.5288F, 2.0F, 6.0F, 2.0F, -0.01F, false);
/*     */     
/* 123 */     this.leftFeet = new ModelRenderer((Model)this);
/* 124 */     this.leftFeet.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 125 */     this.leftLeg3.addChild(this.leftFeet);
/* 126 */     setRotationAngle(this.leftFeet, 0.5236F, 0.0F, 0.0F);
/* 127 */     this.leftFeet.setTextureOffset(0, 39).addBox(-1.5F, 10.5F, -1.0F, 2.0F, 2.0F, 2.0F, 0.01F, false);
/*     */     
/* 129 */     this.upperBody = new ModelRenderer((Model)this);
/* 130 */     this.upperBody.setRotationPoint(0.0F, -5.0F, 0.0F);
/* 131 */     this.upperBody.setTextureOffset(48, 0).addBox(-5.5F, 0.0F, -2.5F, 11.0F, 10.0F, 6.0F, 0.0F, false);
/*     */     
/* 133 */     this.lowerBody = new ModelRenderer((Model)this);
/* 134 */     this.lowerBody.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 135 */     this.upperBody.addChild(this.lowerBody);
/* 136 */     this.lowerBody.setTextureOffset(17, 0).addBox(-5.0F, 9.9F, -2.0F, 10.0F, 8.0F, 5.0F, 0.0F, false);
/*     */     
/* 138 */     this.hunch = new ModelRenderer((Model)this);
/* 139 */     this.hunch.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 140 */     this.upperBody.addChild(this.hunch);
/* 141 */     setRotationAngle(this.hunch, 0.4189F, 0.0F, 0.0F);
/* 142 */     this.hunch.setTextureOffset(83, 24).addBox(-4.5F, 0.0F, -0.5F, 9.0F, 10.0F, 6.0F, 0.0F, false);
/*     */     
/* 144 */     this.rightShoulder = new ModelRenderer((Model)this);
/* 145 */     this.rightShoulder.setRotationPoint(-3.5F, 4.0F, 0.0F);
/* 146 */     this.upperBody.addChild(this.rightShoulder);
/* 147 */     setRotationAngle(this.rightShoulder, 0.0F, 0.0F, -0.2618F);
/* 148 */     this.rightShoulder.setTextureOffset(83, 0).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 150 */     this.leftShoulder = new ModelRenderer((Model)this);
/* 151 */     this.leftShoulder.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 152 */     this.upperBody.addChild(this.leftShoulder);
/* 153 */     setRotationAngle(this.leftShoulder, 0.0F, 0.0F, 0.2618F);
/* 154 */     this.leftShoulder.setTextureOffset(83, 0).addBox(1.5F, 0.0F, -2.0F, 6.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 156 */     this.neck = new ModelRenderer((Model)this);
/* 157 */     this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 158 */     this.upperBody.addChild(this.neck);
/* 159 */     setRotationAngle(this.neck, 0.4887F, 0.0F, 0.0F);
/* 160 */     this.neck.setTextureOffset(83, 12).addBox(-3.5F, 0.0F, -5.0F, 7.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 162 */     this.head = new ModelRenderer((Model)this);
/* 163 */     this.head.setRotationPoint(0.0F, -4.0F, 0.0F);
/* 164 */     this.head.setTextureOffset(0, 0).addBox(-2.0F, -1.0F, -7.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
/*     */     
/* 166 */     this.leftHorn1 = new ModelRenderer((Model)this);
/* 167 */     this.leftHorn1.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 168 */     this.head.addChild(this.leftHorn1);
/* 169 */     setRotationAngle(this.leftHorn1, 0.0F, 0.0F, -0.733F);
/* 170 */     this.leftHorn1.setTextureOffset(115, 0).addBox(1.0F, 1.0F, -6.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 172 */     this.leftHorn2 = new ModelRenderer((Model)this);
/* 173 */     this.leftHorn2.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 174 */     this.leftHorn1.addChild(this.leftHorn2);
/* 175 */     setRotationAngle(this.leftHorn2, 0.0F, 0.0F, -1.2217F);
/* 176 */     this.leftHorn2.setTextureOffset(122, 0).addBox(-0.7F, 2.5F, -6.0F, 2.0F, 1.0F, 1.0F, 0.01F, false);
/*     */     
/* 178 */     this.rightHorn1 = new ModelRenderer((Model)this);
/* 179 */     this.rightHorn1.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 180 */     this.head.addChild(this.rightHorn1);
/* 181 */     setRotationAngle(this.rightHorn1, 0.0F, 0.0F, 0.733F);
/* 182 */     this.rightHorn1.setTextureOffset(115, 0).addBox(-3.0F, 1.0F, -6.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
/*     */     
/* 184 */     this.rightHorn2 = new ModelRenderer((Model)this);
/* 185 */     this.rightHorn2.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 186 */     this.rightHorn1.addChild(this.rightHorn2);
/* 187 */     setRotationAngle(this.rightHorn2, 0.0F, 0.0F, 1.2217F);
/* 188 */     this.rightHorn2.setTextureOffset(122, 0).addBox(-1.3F, 2.5F, -6.0F, 2.0F, 1.0F, 1.0F, 0.01F, false);
/*     */     
/* 190 */     this.bipedBody = this.upperBody;
/* 191 */     this.bipedHead = this.head;
/* 192 */     this.bipedRightArm = this.rightArm;
/* 193 */     this.bipedLeftArm = this.leftArm;
/* 194 */     this.bipedRightLeg = this.rightLeg;
/* 195 */     this.bipedLeftLeg = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 201 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 202 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 203 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 204 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 205 */     this.upperBody.render(matrixStack, buffer, packedLight, packedOverlay);
/* 206 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 213 */     boolean flag = (entity.getTicksElytraFlying() > 4);
/* 214 */     boolean flag1 = entity.isActualySwimming();
/* 215 */     this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;
/* 216 */     if (flag) {
/* 217 */       this.bipedHead.rotateAngleX = -0.7853982F;
/* 218 */     } else if (this.swimAnimation > 0.0F) {
/*     */       
/* 220 */       if (flag1) {
/* 221 */         this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, -0.7853982F, this.swimAnimation);
/*     */       } else {
/* 223 */         this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
/*     */       } 
/*     */     } else {
/*     */       
/* 227 */       this.bipedHead.rotateAngleX = headPitch * 0.017453292F;
/* 228 */       if (this.bipedHead.rotateAngleX > 0.4D) {
/* 229 */         this.bipedHead.rotateAngleX = 0.4F;
/* 230 */       } else if (this.bipedHead.rotateAngleX < -0.4D) {
/* 231 */         this.bipedHead.rotateAngleX = -0.4F;
/*     */       } 
/*     */     } 
/*     */     
/* 235 */     float f = 1.0F;
/* 236 */     this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 237 */     this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 238 */     this.rightLeg.rotateAngleX = -0.34F + MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 239 */     this.leftLeg.rotateAngleX = -0.34F + MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 240 */     if (!entity.getHeldItemMainhand().isEmpty()) {
/* 241 */       this.rightArm.rotateAngleX += -0.15F;
/*     */     }
/*     */     
/* 244 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 245 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 246 */     if (this.swingProgress > 0.0F)
/*     */     {
/* 248 */       if (isBlackLeg) {
/*     */         
/* 250 */         this.upperBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 251 */         this.rightLeg.rotateAngleY += this.upperBody.rotateAngleY;
/* 252 */         this.leftLeg.rotateAngleY += this.upperBody.rotateAngleY;
/* 253 */         float f1 = 1.0F - this.swingProgress;
/* 254 */         f1 *= f1;
/* 255 */         f1 *= f1;
/* 256 */         f1 = 1.0F - f1;
/* 257 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 258 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 259 */         this.rightLeg.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
/* 260 */         this.rightLeg.rotateAngleY += this.lowerBody.rotateAngleY * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 264 */         this.upperBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 265 */         this.rightArm.rotationPointZ = MathHelper.sin(this.upperBody.rotateAngleY) * 5.0F;
/* 266 */         this.rightArm.rotationPointX = -MathHelper.cos(this.upperBody.rotateAngleY) * 5.0F;
/* 267 */         this.leftArm.rotationPointZ = -MathHelper.sin(this.upperBody.rotateAngleY) * 5.0F;
/* 268 */         this.leftArm.rotationPointX = MathHelper.cos(this.upperBody.rotateAngleY) * 5.0F;
/* 269 */         this.rightArm.rotateAngleY += this.upperBody.rotateAngleY;
/* 270 */         this.leftArm.rotateAngleY += this.upperBody.rotateAngleY;
/* 271 */         this.leftArm.rotateAngleX += this.upperBody.rotateAngleY;
/* 272 */         float f1 = 1.0F - this.swingProgress;
/* 273 */         f1 *= f1;
/* 274 */         f1 *= f1;
/* 275 */         f1 = 1.0F - f1;
/* 276 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 277 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 278 */         this.rightArm.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
/* 279 */         this.rightArm.rotateAngleY += this.lowerBody.rotateAngleY * 2.0F;
/* 280 */         this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.9F;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 285 */     if (entity.isSneaking()) {
/*     */       
/* 287 */       this.upperBody.rotateAngleX = 0.5F;
/* 288 */       this.upperBody.rotationPointZ -= 4.0F;
/* 289 */       this.rightArm.rotateAngleX += 0.4F;
/* 290 */       this.rightArm.rotationPointZ -= 2.5F;
/* 291 */       this.leftArm.rotateAngleX += 0.4F;
/* 292 */       this.leftArm.rotationPointZ -= 2.5F;
/* 293 */       this.rightLeg.rotationPointZ = 4.0F;
/* 294 */       this.leftLeg.rotationPointZ = 4.5F;
/* 295 */       this.rightLeg.rotationPointY = 10.5F;
/* 296 */       this.leftLeg.rotationPointY = 10.5F;
/* 297 */       this.head.rotationPointY = -4.0F;
/* 298 */       this.head.rotationPointZ = -2.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 304 */     modelRenderer.rotateAngleX = x;
/* 305 */     modelRenderer.rotateAngleY = y;
/* 306 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 312 */     super.translateHand(side, matrixStack);
/* 313 */     matrixStack.translate(0.0D, 0.4D, 0.0D);
/* 314 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees((side == HandSide.RIGHT) ? -20.0F : 20.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 320 */     if (side == HandSide.RIGHT) {
/*     */       
/* 322 */       matrixStack.translate(-0.5D, -0.2D, -0.2D);
/* 323 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
/* 324 */       matrixStack.rotate(Vector3f.ZP.rotationDegrees(10.0F));
/* 325 */       this.rightArm2.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 329 */       matrixStack.translate(0.5D, -0.2D, -0.2D);
/* 330 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-10.0F));
/* 331 */       matrixStack.rotate(Vector3f.ZP.rotationDegrees(-10.0F));
/* 332 */       this.leftArm2.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 339 */     if (side == HandSide.RIGHT) {
/*     */       
/* 341 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 342 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 343 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-60.0F));
/* 344 */       this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 348 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 349 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 350 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(60.0F));
/* 351 */       this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\BisonHeavyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */