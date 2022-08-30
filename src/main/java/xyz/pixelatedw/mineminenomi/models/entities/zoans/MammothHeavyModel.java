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
/*     */ public class MammothHeavyModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer tail_r1;
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
/*     */   private final ModelRenderer rightTusk;
/*     */   private final ModelRenderer rightTusk2;
/*     */   private final ModelRenderer rightTusk3;
/*     */   private final ModelRenderer leftTusk;
/*     */   private final ModelRenderer leftTusk2;
/*     */   private final ModelRenderer leftTusk3;
/*     */   
/*     */   public MammothHeavyModel() {
/*  45 */     super(1.0F);
/*  46 */     this.textureWidth = 128;
/*  47 */     this.textureHeight = 128;
/*     */     
/*  49 */     this.body = new ModelRenderer((Model)this);
/*  50 */     this.body.setRotationPoint(0.0F, -2.6F, 2.7F);
/*  51 */     setRotationAngle(this.body, 0.0873F, 0.0F, 0.0F);
/*  52 */     this.body.setTextureOffset(0, 0).addBox(-9.0F, -10.0F, -7.0F, 18.0F, 14.0F, 10.0F, 0.0F, false);
/*     */     
/*  54 */     this.body2 = new ModelRenderer((Model)this);
/*  55 */     this.body2.setRotationPoint(0.0F, 13.8F, 0.3F);
/*  56 */     this.body.addChild(this.body2);
/*  57 */     this.body2.setTextureOffset(0, 24).addBox(-8.5F, -10.0F, -7.0F, 17.0F, 10.0F, 10.0F, 0.0F, false);
/*     */     
/*  59 */     this.tail = new ModelRenderer((Model)this);
/*  60 */     this.tail.setRotationPoint(0.25F, -2.5F, 2.0F);
/*  61 */     this.body2.addChild(this.tail);
/*  62 */     setRotationAngle(this.tail, 0.4363F, 0.0F, 0.0F);
/*     */     
/*  64 */     this.tail_r1 = new ModelRenderer((Model)this);
/*  65 */     this.tail_r1.setRotationPoint(-1.0F, -2.7F, 0.0F);
/*  66 */     this.tail.addChild(this.tail_r1);
/*  67 */     setRotationAngle(this.tail_r1, 0.48F, 0.0F, 0.0F);
/*  68 */     this.tail_r1.setTextureOffset(56, 0).addBox(-0.75F, 0.587F, -3.0383F, 3.0F, 8.0F, 3.0F, 0.0F, false);
/*     */     
/*  70 */     this.rightLeg = new ModelRenderer((Model)this);
/*  71 */     this.rightLeg.setRotationPoint(-4.0F, 11.1F, 1.0F);
/*  72 */     setRotationAngle(this.rightLeg, -0.0524F, 0.0F, 0.0F);
/*  73 */     this.rightLeg.setTextureOffset(28, 60).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 7.0F, 6.0F, 0.0F, false);
/*     */     
/*  75 */     this.rightLeg2 = new ModelRenderer((Model)this);
/*  76 */     this.rightLeg2.setRotationPoint(0.0F, 6.8F, 0.0F);
/*  77 */     this.rightLeg.addChild(this.rightLeg2);
/*  78 */     setRotationAngle(this.rightLeg2, 0.0524F, 0.0F, 0.0F);
/*  79 */     this.rightLeg2.setTextureOffset(0, 62).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/*     */     
/*  81 */     this.leftLeg = new ModelRenderer((Model)this);
/*  82 */     this.leftLeg.setRotationPoint(5.0F, 11.1F, 1.0F);
/*  83 */     setRotationAngle(this.leftLeg, -0.0524F, 0.0F, 0.0F);
/*  84 */     this.leftLeg.setTextureOffset(28, 60).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 7.0F, 6.0F, 0.0F, false);
/*     */     
/*  86 */     this.leftLeg2 = new ModelRenderer((Model)this);
/*  87 */     this.leftLeg2.setRotationPoint(0.0F, 6.8F, 0.0F);
/*  88 */     this.leftLeg.addChild(this.leftLeg2);
/*  89 */     setRotationAngle(this.leftLeg2, 0.0524F, 0.0F, 0.0F);
/*  90 */     this.leftLeg2.setTextureOffset(0, 62).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 6.0F, 6.0F, 0.0F, false);
/*     */     
/*  92 */     this.rightArm = new ModelRenderer((Model)this);
/*  93 */     this.rightArm.setRotationPoint(-9.0F, -9.6F, 1.0F);
/*  94 */     setRotationAngle(this.rightArm, 0.0F, 0.0F, 0.0349F);
/*  95 */     this.rightArm.setTextureOffset(50, 18).addBox(-6.0F, -2.0F, -3.5F, 6.0F, 10.0F, 6.0F, 0.0F, false);
/*     */     
/*  97 */     this.rightArm2 = new ModelRenderer((Model)this);
/*  98 */     this.rightArm2.setRotationPoint(-2.5F, 7.8F, 0.0F);
/*  99 */     this.rightArm.addChild(this.rightArm2);
/* 100 */     setRotationAngle(this.rightArm2, 0.0F, 0.0F, -0.0349F);
/* 101 */     this.rightArm2.setTextureOffset(54, 34).addBox(-3.5F, 0.0F, -3.5F, 6.0F, 10.0F, 6.0F, 0.01F, false);
/*     */     
/* 103 */     this.leftArm = new ModelRenderer((Model)this);
/* 104 */     this.leftArm.setRotationPoint(9.0F, -9.6F, 1.0F);
/* 105 */     setRotationAngle(this.leftArm, 0.0F, 0.0F, -0.0349F);
/* 106 */     this.leftArm.setTextureOffset(50, 18).addBox(0.0F, -2.0F, -3.5F, 6.0F, 10.0F, 6.0F, 0.0F, true);
/*     */     
/* 108 */     this.leftArm2 = new ModelRenderer((Model)this);
/* 109 */     this.leftArm2.setRotationPoint(2.5F, 7.8F, 0.0F);
/* 110 */     this.leftArm.addChild(this.leftArm2);
/* 111 */     setRotationAngle(this.leftArm2, 0.0F, 0.0F, 0.0349F);
/* 112 */     this.leftArm2.setTextureOffset(54, 34).addBox(-2.5F, 0.0F, -3.5F, 6.0F, 10.0F, 6.0F, 0.01F, true);
/*     */     
/* 114 */     this.head = new ModelRenderer((Model)this);
/* 115 */     this.head.setRotationPoint(0.0F, -12.2F, -3.0F);
/* 116 */     setRotationAngle(this.head, 0.0524F, 0.0F, 0.0F);
/* 117 */     this.head.setTextureOffset(0, 44).addBox(-4.0F, -9.0F, -4.5F, 8.0F, 9.0F, 9.0F, 0.0F, false);
/*     */     
/* 119 */     this.snout = new ModelRenderer((Model)this);
/* 120 */     this.snout.setRotationPoint(-2.5F, -1.5F, -4.5F);
/* 121 */     this.head.addChild(this.snout);
/* 122 */     setRotationAngle(this.snout, -0.3839F, 0.0F, 0.0F);
/* 123 */     this.snout.setTextureOffset(34, 48).addBox(0.0F, 0.0F, 0.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);
/*     */     
/* 125 */     this.snout2 = new ModelRenderer((Model)this);
/* 126 */     this.snout2.setRotationPoint(1.5F, 7.0F, 0.5F);
/* 127 */     this.snout.addChild(this.snout2);
/* 128 */     setRotationAngle(this.snout2, 0.2094F, 0.0F, 0.0F);
/* 129 */     this.snout2.setTextureOffset(72, 30).addBox(-1.0F, 0.0F, 0.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
/*     */     
/* 131 */     this.snout3 = new ModelRenderer((Model)this);
/* 132 */     this.snout3.setRotationPoint(0.5F, 5.5F, 0.5F);
/* 133 */     this.snout2.addChild(this.snout3);
/* 134 */     setRotationAngle(this.snout3, 0.2094F, 0.0F, 0.0F);
/* 135 */     this.snout3.setTextureOffset(74, 22).addBox(-1.0F, 0.0F, 0.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
/*     */     
/* 137 */     this.leftEar = new ModelRenderer((Model)this);
/* 138 */     this.leftEar.setRotationPoint(3.5F, -3.55F, 0.0F);
/* 139 */     this.head.addChild(this.leftEar);
/* 140 */     setRotationAngle(this.leftEar, -0.2365F, -1.0306F, 0.274F);
/* 141 */     this.leftEar.setTextureOffset(68, 13).addBox(-1.0F, -6.0F, -0.5F, 8.0F, 8.0F, 1.0F, 0.0F, true);
/*     */     
/* 143 */     this.rightEar = new ModelRenderer((Model)this);
/* 144 */     this.rightEar.setRotationPoint(-3.5F, -3.8F, -1.0F);
/* 145 */     this.head.addChild(this.rightEar);
/* 146 */     setRotationAngle(this.rightEar, -0.2365F, 1.0306F, -0.274F);
/* 147 */     this.rightEar.setTextureOffset(68, 13).addBox(-8.0F, -6.0F, 0.0F, 8.0F, 8.0F, 1.0F, 0.0F, false);
/*     */     
/* 149 */     this.rightTusk = new ModelRenderer((Model)this);
/* 150 */     this.rightTusk.setRotationPoint(-3.25F, -1.8F, -1.0F);
/* 151 */     this.head.addChild(this.rightTusk);
/* 152 */     setRotationAngle(this.rightTusk, 2.5307F, 0.2618F, 0.0F);
/* 153 */     this.rightTusk.setTextureOffset(69, 0).addBox(-1.4352F, -6.5246F, -1.629F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 155 */     this.rightTusk2 = new ModelRenderer((Model)this);
/* 156 */     this.rightTusk2.setRotationPoint(0.2445F, -6.1921F, -0.3535F);
/* 157 */     this.rightTusk.addChild(this.rightTusk2);
/* 158 */     setRotationAngle(this.rightTusk2, -0.3054F, 0.0F, 0.0F);
/* 159 */     this.rightTusk2.setTextureOffset(69, 0).addBox(-1.6298F, -5.9104F, -1.4339F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 161 */     this.rightTusk3 = new ModelRenderer((Model)this);
/* 162 */     this.rightTusk3.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 163 */     this.rightTusk2.addChild(this.rightTusk3);
/* 164 */     setRotationAngle(this.rightTusk3, -0.3054F, 0.0F, 0.0F);
/* 165 */     this.rightTusk3.setTextureOffset(69, 0).addBox(-1.6298F, -11.1604F, -3.1839F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 167 */     this.leftTusk = new ModelRenderer((Model)this);
/* 168 */     this.leftTusk.setRotationPoint(2.5F, -1.8F, -1.0F);
/* 169 */     this.head.addChild(this.leftTusk);
/* 170 */     setRotationAngle(this.leftTusk, 2.5307F, -0.2618F, 0.0F);
/* 171 */     this.leftTusk.setTextureOffset(69, 0).addBox(-1.0F, -6.5246F, -1.629F, 3.0F, 6.0F, 3.0F, 0.0F, true);
/*     */     
/* 173 */     this.leftTusk2 = new ModelRenderer((Model)this);
/* 174 */     this.leftTusk2.setRotationPoint(0.2445F, -6.1921F, -0.3535F);
/* 175 */     this.leftTusk.addChild(this.leftTusk2);
/* 176 */     setRotationAngle(this.leftTusk2, -0.3054F, 0.0F, 0.0F);
/* 177 */     this.leftTusk2.setTextureOffset(69, 0).addBox(-1.2445F, -5.9104F, -1.4339F, 3.0F, 6.0F, 3.0F, 0.0F, true);
/*     */     
/* 179 */     this.leftTusk3 = new ModelRenderer((Model)this);
/* 180 */     this.leftTusk3.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 181 */     this.leftTusk2.addChild(this.leftTusk3);
/* 182 */     setRotationAngle(this.leftTusk3, -0.3054F, 0.0F, 0.0F);
/* 183 */     this.leftTusk3.setTextureOffset(69, 0).addBox(-1.2445F, -11.1604F, -3.1839F, 3.0F, 6.0F, 3.0F, 0.0F, true);
/*     */     
/* 185 */     this.bipedBody = this.body;
/* 186 */     this.bipedHead = this.head;
/* 187 */     this.bipedRightArm = this.rightArm;
/* 188 */     this.bipedLeftArm = this.leftArm;
/* 189 */     this.bipedRightLeg = this.rightLeg;
/* 190 */     this.bipedLeftLeg = this.leftLeg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 197 */     boolean flag = (entity.getTicksElytraFlying() > 4);
/* 198 */     boolean flag1 = entity.isActualySwimming();
/* 199 */     this.head.rotateAngleY = netHeadYaw * 0.011635529F;
/* 200 */     if (flag) {
/* 201 */       this.head.rotateAngleX = -0.7853982F;
/* 202 */     } else if (this.swimAnimation > 0.0F) {
/*     */       
/* 204 */       if (flag1) {
/* 205 */         this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, -0.7853982F, this.swimAnimation);
/*     */       } else {
/* 207 */         this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
/*     */       } 
/*     */     } else {
/*     */       
/* 211 */       this.head.rotateAngleX = headPitch * 0.015707964F;
/* 212 */       if (this.head.rotateAngleX > 0.6D) {
/* 213 */         this.head.rotateAngleX = 0.6F;
/*     */       }
/*     */     } 
/*     */     
/* 217 */     float f = 1.0F;
/* 218 */     this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.3F + 3.1415927F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 219 */     this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.3F) * 0.8F * limbSwingAmount * 0.5F / f;
/* 220 */     this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.3F) * 0.7F * limbSwingAmount / f;
/* 221 */     this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.3F + 3.1415927F) * 0.7F * limbSwingAmount / f;
/* 222 */     if (!entity.getHeldItemMainhand().isEmpty())
/* 223 */       this.rightArm.rotateAngleX += -0.15F; 
/* 224 */     if (entity.isSprinting()) {
/*     */       
/* 226 */       this.tail.rotateAngleX = 1.6F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 227 */       this.leftEar.rotateAngleY = -0.3F - MathHelper.cos(limbSwing * 0.6F) * 0.2F * limbSwingAmount;
/* 228 */       this.rightEar.rotationPointX = (float)(this.rightEar.rotationPointX + 0.8D);
/* 229 */       this.rightEar.rotateAngleY = 0.3F + MathHelper.cos(limbSwing * 0.6F) * 0.2F * limbSwingAmount;
/*     */     } 
/*     */ 
/*     */     
/* 233 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 234 */     boolean isBlackLeg = EntityStatsCapability.get((LivingEntity)entity).isBlackLeg();
/* 235 */     if (this.swingProgress > 0.0F)
/*     */     {
/* 237 */       if (isBlackLeg) {
/*     */         
/* 239 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 240 */         this.rightLeg.rotateAngleY += this.body.rotateAngleY;
/* 241 */         this.leftLeg.rotateAngleY += this.body.rotateAngleY;
/* 242 */         float f1 = 1.0F - this.swingProgress;
/* 243 */         f1 *= f1;
/* 244 */         f1 *= f1;
/* 245 */         f1 = 1.0F - f1;
/* 246 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 247 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 248 */         this.rightLeg.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.5D + f3);
/* 249 */         this.rightLeg.rotateAngleY += this.body.rotateAngleY * 2.0F;
/*     */       }
/*     */       else {
/*     */         
/* 253 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 254 */         this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 255 */         this.rightArm.rotationPointZ = MathHelper.sin(this.body2.rotateAngleY) * 8.0F;
/* 256 */         this.rightArm.rotationPointX = -MathHelper.cos(this.body2.rotateAngleY) * 8.0F;
/* 257 */         this.rightArm.rotateAngleY += this.body2.rotateAngleY;
/* 258 */         this.leftArm.rotateAngleY += this.body2.rotateAngleY;
/* 259 */         this.leftArm.rotateAngleX += this.body2.rotateAngleY;
/* 260 */         float f1 = 1.0F - this.swingProgress;
/* 261 */         f1 *= f1;
/* 262 */         f1 *= f1;
/* 263 */         f1 = 1.0F - f1;
/* 264 */         float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 265 */         float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 266 */         this.rightArm.rotateAngleX = (float)(this.rightArm.rotateAngleX - f2 * 1.2D + f3);
/* 267 */         this.rightArm.rotateAngleY += this.body.rotateAngleY * 2.0F;
/* 268 */         this.rightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 273 */     if (entity.isSneaking()) {
/*     */       
/* 275 */       this.body.rotateAngleX = 0.5F;
/* 276 */       this.body.rotationPointZ -= 4.0F;
/* 277 */       this.rightArm.rotateAngleX += 0.4F;
/* 278 */       this.rightArm.rotationPointZ -= 8.5F;
/* 279 */       this.rightArm.rotationPointY++;
/* 280 */       this.leftArm.rotateAngleX += 0.4F;
/* 281 */       this.leftArm.rotationPointZ -= 8.5F;
/* 282 */       this.leftArm.rotationPointY++;
/* 283 */       this.rightLeg.rotationPointZ = 1.5F;
/* 284 */       this.leftLeg.rotationPointZ = 1.5F;
/* 285 */       this.rightLeg.rotationPointY = 9.0F;
/* 286 */       this.leftLeg.rotationPointY = 9.0F;
/* 287 */       this.head.rotationPointZ -= 7.0F;
/* 288 */       this.head.rotationPointY += 3.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 295 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay);
/* 296 */     this.rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 297 */     this.leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 298 */     this.rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 299 */     this.leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
/* 300 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 305 */     modelRenderer.rotateAngleX = x;
/* 306 */     modelRenderer.rotateAngleY = y;
/* 307 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonArm(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 313 */     if (side == HandSide.RIGHT) {
/*     */       
/* 315 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(90.0F));
/* 316 */       matrixStack.translate(0.15D, -0.3D, -0.5D);
/* 317 */       this.rightArm2.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 321 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
/* 322 */       matrixStack.translate(0.15D, -0.3D, -0.5D);
/* 323 */       this.rightArm2.render(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderFirstPersonLeg(MatrixStack matrixStack, IVertexBuilder vertex, int packedLight, int overlay, float red, float green, float blue, float alpha, HandSide side) {
/* 330 */     if (side == HandSide.RIGHT) {
/*     */       
/* 332 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 333 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 334 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(-60.0F));
/* 335 */       this.rightLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     }
/*     */     else {
/*     */       
/* 339 */       matrixStack.translate(0.0D, -1.2D, 0.3D);
/* 340 */       matrixStack.scale(1.5F, 1.5F, 1.5F);
/* 341 */       matrixStack.rotate(Vector3f.YP.rotationDegrees(60.0F));
/* 342 */       this.leftLeg.render(matrixStack, vertex, packedLight, overlay, red, green, blue, alpha);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 349 */     super.translateHand(side, matrixStack);
/* 350 */     matrixStack.translate((side == HandSide.RIGHT) ? -0.1D : 0.1D, 0.4D, 0.0D);
/* 351 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees(0.0F));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\MammothHeavyModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */