/*     */ package xyz.pixelatedw.mineminenomi.models.entities.zoans;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import net.minecraft.client.renderer.Vector3f;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.model.Model;
/*     */ import net.minecraft.client.renderer.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanMorphModel;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ 
/*     */ public class ZouHeavyModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail2;
/*     */   private final ModelRenderer rightLeg;
/*     */   private final ModelRenderer rightLeg2;
/*     */   private final ModelRenderer leftLeg;
/*     */   private final ModelRenderer leftLeg2;
/*     */   private final ModelRenderer rightArm;
/*     */   private final ModelRenderer rightArm2;
/*     */   private final ModelRenderer leftArm;
/*     */   private final ModelRenderer leftArm2;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer snout;
/*     */   private final ModelRenderer snout2;
/*     */   private final ModelRenderer snout3;
/*     */   private final ModelRenderer leftEar;
/*     */   private final ModelRenderer rightEar;
/*     */   private final ModelRenderer leftTusk;
/*     */   private final ModelRenderer leftTusk2;
/*     */   private final ModelRenderer rightTusk;
/*     */   private final ModelRenderer rightTusk2;
/*     */   
/*     */   public ZouHeavyModel() {
/*  43 */     super(1.0F);
/*  44 */     this.textureWidth = 128;
/*  45 */     this.textureHeight = 64;
/*     */     
/*  47 */     this.body = new ModelRenderer((Model)this);
/*  48 */     this.body.setRotationPoint(0.0F, -2.6F, 2.7F);
/*  49 */     setRotationAngle(this.body, 0.0873F, 0.0F, 0.0F);
/*  50 */     this.body.setTextureOffset(35, 0).addBox(-9.0F, -10.0F, -7.0F, 18.0F, 14.0F, 10.0F, 0.0F, false);
/*     */     
/*  52 */     this.body2 = new ModelRenderer((Model)this);
/*  53 */     this.body2.setRotationPoint(0.0F, 13.8F, 0.3F);
/*  54 */     this.body.addChild(this.body2);
/*  55 */     this.body2.setTextureOffset(35, 25).addBox(-8.5F, -10.0F, -7.0F, 17.0F, 10.0F, 10.0F, 0.0F, false);
/*     */     
/*  57 */     this.tail = new ModelRenderer((Model)this);
/*  58 */     this.tail.setRotationPoint(0.0F, -2.5F, 2.0F);
/*  59 */     this.body2.addChild(this.tail);
/*  60 */     setRotationAngle(this.tail, 0.4363F, 0.0F, 0.0F);
/*  61 */     this.tail.setTextureOffset(114, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, 0.0F, false);
/*     */     
/*  63 */     this.tail2 = new ModelRenderer((Model)this);
/*  64 */     this.tail2.setRotationPoint(0.0F, 5.2282F, -0.0718F);
/*  65 */     this.tail.addChild(this.tail2);
/*  66 */     this.tail2.setTextureOffset(119, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
/*     */     
/*  68 */     this.rightLeg = new ModelRenderer((Model)this);
/*  69 */     this.rightLeg.setRotationPoint(-5.0F, 11.1F, 1.0F);
/*  70 */     setRotationAngle(this.rightLeg, -0.0524F, 0.0F, 0.0F);
/*  71 */     this.rightLeg.setTextureOffset(42, 46).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F, 0.0F, false);
/*     */     
/*  73 */     this.rightLeg2 = new ModelRenderer((Model)this);
/*  74 */     this.rightLeg2.setRotationPoint(0.0F, 6.8F, 0.0F);
/*  75 */     this.rightLeg.addChild(this.rightLeg2);
/*  76 */     setRotationAngle(this.rightLeg2, 0.0524F, 0.0F, 0.0F);
/*  77 */     this.rightLeg2.setTextureOffset(63, 46).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);
/*     */     
/*  79 */     this.leftLeg = new ModelRenderer((Model)this);
/*  80 */     this.leftLeg.setRotationPoint(5.0F, 11.1F, 1.0F);
/*  81 */     setRotationAngle(this.leftLeg, -0.0524F, 0.0F, 0.0F);
/*  82 */     this.leftLeg.setTextureOffset(42, 46).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 7.0F, 5.0F, 0.0F, false);
/*     */     
/*  84 */     this.leftLeg2 = new ModelRenderer((Model)this);
/*  85 */     this.leftLeg2.setRotationPoint(0.0F, 6.8F, 0.0F);
/*  86 */     this.leftLeg.addChild(this.leftLeg2);
/*  87 */     setRotationAngle(this.leftLeg2, 0.0524F, 0.0F, 0.0F);
/*  88 */     this.leftLeg2.setTextureOffset(63, 46).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);
/*     */     
/*  90 */     this.rightArm = new ModelRenderer((Model)this);
/*  91 */     this.rightArm.setRotationPoint(-9.0F, -9.6F, 1.0F);
/*  92 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.0349F);
/*  93 */     this.rightArm.setTextureOffset(0, 46).addBox(-5.0F, -2.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
/*     */     
/*  95 */     this.rightArm2 = new ModelRenderer((Model)this);
/*  96 */     this.rightArm2.setRotationPoint(-2.5F, 7.8F, 0.0F);
/*  97 */     this.rightArm.addChild(this.rightArm2);
/*  98 */     setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.0349F);
/*  99 */     this.rightArm2.setTextureOffset(21, 46).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
/*     */     
/* 101 */     this.leftArm = new ModelRenderer((Model)this);
/* 102 */     this.leftArm.setRotationPoint(9.0F, -9.6F, 1.0F);
/* 103 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.0349F);
/* 104 */     this.leftArm.setTextureOffset(0, 46).addBox(0.0F, -2.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
/*     */     
/* 106 */     this.leftArm2 = new ModelRenderer((Model)this);
/* 107 */     this.leftArm2.setRotationPoint(2.5F, 7.8F, 0.0F);
/* 108 */     this.leftArm.addChild(this.leftArm2);
/* 109 */     setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.0349F);
/* 110 */     this.leftArm2.setTextureOffset(21, 46).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
/*     */     
/* 112 */     this.head = new ModelRenderer((Model)this);
/* 113 */     this.head.setRotationPoint(0.0F, -12.2F, -3.0F);
/* 114 */     setRotationAngle(this.head, 0.0524F, 0.0F, 0.0F);
/* 115 */     this.head.setTextureOffset(0, 0).addBox(-4.0F, -9.0F, -4.5F, 8.0F, 9.0F, 9.0F, 0.0F, false);
/*     */     
/* 117 */     this.snout = new ModelRenderer((Model)this);
/* 118 */     this.snout.setRotationPoint(-2.0F, -1.5F, -4.5F);
/* 119 */     this.head.addChild(this.snout);
/* 120 */     setRotationAngle(this.snout, -0.2094F, 0.0F, 0.0F);
/* 121 */     this.snout.setTextureOffset(112, 35).addBox(0.0F, 0.0F, 0.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
/*     */     
/* 123 */     this.snout2 = new ModelRenderer((Model)this);
/* 124 */     this.snout2.setRotationPoint(0.5F, 7.0F, 0.5F);
/* 125 */     this.snout.addChild(this.snout2);
/* 126 */     setRotationAngle(this.snout2, 0.2094F, 0.0F, 0.0F);
/* 127 */     this.snout2.setTextureOffset(116, 47).addBox(0.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 129 */     this.snout3 = new ModelRenderer((Model)this);
/* 130 */     this.snout3.setRotationPoint(0.5F, 5.5F, 0.5F);
/* 131 */     this.snout2.addChild(this.snout3);
/* 132 */     setRotationAngle(this.snout3, 0.2094F, 0.0F, 0.0F);
/* 133 */     this.snout3.setTextureOffset(120, 57).addBox(0.0F, 0.0F, 0.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
/*     */     
/* 135 */     this.leftEar = new ModelRenderer((Model)this);
/* 136 */     this.leftEar.setRotationPoint(3.5F, -3.8F, 0.0F);
/* 137 */     this.head.addChild(this.leftEar);
/* 138 */     setRotationAngle(this.leftEar, -0.2365F, -1.0306F, 0.274F);
/* 139 */     this.leftEar.setTextureOffset(0, 19).addBox(0.0F, -6.0F, 0.0F, 6.0F, 8.0F, 1.0F, 0.0F, true);
/*     */     
/* 141 */     this.rightEar = new ModelRenderer((Model)this);
/* 142 */     this.rightEar.setRotationPoint(-3.5F, -3.8F, -1.0F);
/* 143 */     this.head.addChild(this.rightEar);
/* 144 */     setRotationAngle(this.rightEar, -0.2365F, 1.0306F, -0.274F);
/* 145 */     this.rightEar.setTextureOffset(0, 19).addBox(-6.0F, -6.0F, 0.0F, 6.0F, 8.0F, 1.0F, 0.0F, false);
/*     */     
/* 147 */     this.leftTusk = new ModelRenderer((Model)this);
/* 148 */     this.leftTusk.setRotationPoint(2.3F, -1.0F, -3.5F);
/* 149 */     this.head.addChild(this.leftTusk);
/* 150 */     setRotationAngle(this.leftTusk, -0.3491F, -0.1745F, 0.0F);
/* 151 */     this.leftTusk.setTextureOffset(15, 19).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 153 */     this.leftTusk2 = new ModelRenderer((Model)this);
/* 154 */     this.leftTusk2.setRotationPoint(0.0F, 3.8F, 0.0F);
/* 155 */     this.leftTusk.addChild(this.leftTusk2);
/* 156 */     setRotationAngle(this.leftTusk2, -0.1745F, -0.0175F, 0.0F);
/* 157 */     this.leftTusk2.setTextureOffset(15, 25).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 159 */     this.rightTusk = new ModelRenderer((Model)this);
/* 160 */     this.rightTusk.setRotationPoint(-2.3F, -1.0F, -3.5F);
/* 161 */     this.head.addChild(this.rightTusk);
/* 162 */     setRotationAngle(this.rightTusk, -0.3491F, 0.1745F, 0.0F);
/* 163 */     this.rightTusk.setTextureOffset(15, 19).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 165 */     this.rightTusk2 = new ModelRenderer((Model)this);
/* 166 */     this.rightTusk2.setRotationPoint(0.0F, 3.9F, 0.0F);
/* 167 */     this.rightTusk.addChild(this.rightTusk2);
/* 168 */     setRotationAngle(this.rightTusk2, -0.1745F, 0.0175F, 0.0F);
/* 169 */     this.rightTusk2.setTextureOffset(15, 25).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
/*     */     
/* 171 */     this.bipedBody = this.body;
/* 172 */     this.bipedHead = this.head;
/* 173 */     this.bipedRightArm = this.rightArm;
/* 174 */     this.bipedLeftArm = this.leftArm;
/* 175 */     this.bipedRightLeg = this.rightLeg;
/* 176 */     this.bipedLeftLeg = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 182 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 183 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 184 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 185 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 186 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/* 187 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 194 */     boolean flag = (entity.getTicksElytraFlying() > 4);
/* 195 */     boolean flag1 = entity.isActualySwimming();
/* 196 */     this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;
/* 197 */     if (flag) {
/* 198 */       this.bipedHead.rotateAngleX = -0.7853982F;
/* 199 */     } else if (this.swimAnimation > 0.0F) {
/*     */       
/* 201 */       if (flag1) {
/* 202 */         this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, -0.7853982F, this.swimAnimation);
/*     */       } else {
/* 204 */         this.bipedHead.rotateAngleX = rotLerpRad(this.bipedHead.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
/*     */       } 
/*     */     } else {
/*     */       
/* 208 */       this.bipedHead.rotateAngleX = headPitch * 0.015707964F;
/* 209 */       if (this.bipedHead.rotateAngleX > 0.6D) {
/* 210 */         this.bipedHead.rotateAngleX = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/* 214 */     float f = 1.0F;
/* 215 */     this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 216 */     this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 217 */     this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
/* 218 */     this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 219 */     if (!entity.getHeldItemMainhand().isEmpty())
/* 220 */       this.rightArm.rotateAngleX += -0.15F; 
/* 221 */     if (entity.isSprinting()) {
/*     */       
/* 223 */       this.tail.rotateAngleX = 1.6F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 224 */       this.leftEar.rotateAngleY = -0.3F - MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 225 */       this.rightEar.rotateAngleY = 0.3F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/*     */     } 
/*     */ 
/*     */     
/* 229 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 230 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 231 */     if (this.swingProgress > 0.0F)
/*     */     {
/* 233 */       if (isBlackLeg) {
/*     */         
/* 235 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 236 */         this.rightLeg.rotateAngleY += this.body.rotateAngleY;
/* 237 */         this.leftLeg.rotateAngleY += this.body.rotateAngleY;
/* 238 */         float f1 = 1.0F - this.swingProgress;
/* 239 */         f1 *= f1;
/* 240 */         f1 *= f1;
/* 241 */         f1 = 1.0F - f1;
/* 242 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 243 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 244 */         this.rightLeg.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
/* 245 */         this.rightLeg.rotateAngleY += this.body.rotateAngleY * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 249 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 250 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 251 */         this.rightArm.rotationPointZ = MathHelper.sin(this.body2.rotateAngleY) * 8.0F;
/* 252 */         this.rightArm.rotationPointX = -MathHelper.cos(this.body2.rotateAngleY) * 8.0F;
/* 253 */         this.rightArm.rotateAngleY += this.body2.rotateAngleY;
/* 254 */         this.leftArm.rotateAngleY += this.body2.rotateAngleY;
/* 255 */         this.leftArm.rotateAngleX += this.body2.rotateAngleY;
/* 256 */         float f1 = 1.0F - this.swingProgress;
/* 257 */         f1 *= f1;
/* 258 */         f1 *= f1;
/* 259 */         f1 = 1.0F - f1;
/* 260 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 261 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 262 */         this.rightArm.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.2D + f3);
/* 263 */         this.rightArm.rotateAngleY += this.body.rotateAngleY * 2.0F;
/* 264 */         this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 269 */     if (entity.isSneaking()) {
/*     */       
/* 271 */       this.body.rotateAngleX = 0.5F;
/* 272 */       this.body.rotationPointZ -= 4.0F;
/* 273 */       this.rightArm.rotateAngleX += 0.4F;
/* 274 */       this.rightArm.rotationPointZ -= 8.5F;
/* 275 */       this.rightArm.rotationPointY++;
/* 276 */       this.leftArm.rotateAngleX += 0.4F;
/* 277 */       this.leftArm.rotationPointZ -= 8.5F;
/* 278 */       this.leftArm.rotationPointY++;
/* 279 */       this.rightLeg.rotationPointZ = 1.5F;
/* 280 */       this.leftLeg.rotationPointZ = 1.5F;
/* 281 */       this.rightLeg.rotationPointY = 9.0F;
/* 282 */       this.leftLeg.rotationPointY = 9.0F;
/* 283 */       this.head.rotationPointZ -= 7.0F;
/* 284 */       this.head.rotationPointY += 3.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 291 */     if (side == HandSide.RIGHT) {
/*     */       
/* 293 */       matrixStack.translate(-0.3D, -0.15D, 0.0D);
/* 294 */       this.rightArm2.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 298 */       matrixStack.translate(0.6D, -0.15D, 0.0D);
/* 299 */       this.rightArm2.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 306 */     if (side == HandSide.RIGHT) {
/*     */       
/* 308 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 309 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 310 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-60.0F));
/* 311 */       this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 315 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 316 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 317 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(60.0F));
/* 318 */       this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 325 */     super.translateHand(side, matrixStack);
/* 326 */     matrixStack.translate((side == HandSide.RIGHT) ? -0.06D : 0.06D, 0.5D, 0.0D);
/* 327 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees(0.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 332 */     modelRenderer.rotateAngleX = x;
/* 333 */     modelRenderer.rotateAngleY = y;
/* 334 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\ZouHeavyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */