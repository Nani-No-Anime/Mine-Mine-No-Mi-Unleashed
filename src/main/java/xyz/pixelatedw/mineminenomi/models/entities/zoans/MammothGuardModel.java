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
/*     */ public class MammothGuardModel<T extends LivingEntity>
/*     */   extends ZoanMorphModel<T> implements IHasArm {
/*     */   private final ModelRenderer rightFrontLeg;
/*     */   private final ModelRenderer leftFrontLeg;
/*     */   private final ModelRenderer rightBackLeg;
/*     */   private final ModelRenderer leftBackLeg;
/*     */   private final ModelRenderer body;
/*     */   private final ModelRenderer body2;
/*     */   private final ModelRenderer body3;
/*     */   private final ModelRenderer tail;
/*     */   private final ModelRenderer neck;
/*     */   private final ModelRenderer head;
/*     */   private final ModelRenderer rightEar;
/*     */   private final ModelRenderer leftEar;
/*     */   private final ModelRenderer trunk;
/*     */   private final ModelRenderer trunk2;
/*     */   private final ModelRenderer trunk3;
/*     */   private final ModelRenderer trunk4;
/*     */   private final ModelRenderer rightTusk;
/*     */   private final ModelRenderer rightTusk2;
/*     */   private final ModelRenderer rightTusk3;
/*     */   private final ModelRenderer rightTusk4;
/*     */   private final ModelRenderer rightTusk5;
/*     */   private final ModelRenderer leftTusk;
/*     */   private final ModelRenderer leftTusk2;
/*     */   private final ModelRenderer leftTusk3;
/*     */   private final ModelRenderer leftTusk4;
/*     */   private final ModelRenderer leftTusk5;
/*     */   
/*     */   public MammothGuardModel() {
/*  45 */     super(1.0F);
/*  46 */     this.textureWidth = 256;
/*  47 */     this.textureHeight = 256;
/*     */     
/*  49 */     this.rightFrontLeg = new ModelRenderer((Model)this);
/*  50 */     this.rightFrontLeg.setRotationPoint(-5.0F, 10.0F, -12.0F);
/*  51 */     this.rightFrontLeg.setTextureOffset(28, 96).addBox(-3.5F, 0.0F, -3.5F, 6.0F, 15.0F, 6.0F, 0.0F, false);
/*     */     
/*  53 */     this.leftFrontLeg = new ModelRenderer((Model)this);
/*  54 */     this.leftFrontLeg.setRotationPoint(5.0F, 10.0F, -13.0F);
/*  55 */     this.leftFrontLeg.setTextureOffset(28, 96).addBox(-2.5F, 0.0F, -2.5F, 6.0F, 15.0F, 6.0F, 0.0F, false);
/*     */     
/*  57 */     this.rightBackLeg = new ModelRenderer((Model)this);
/*  58 */     this.rightBackLeg.setRotationPoint(-5.0F, 10.0F, 12.0F);
/*  59 */     this.rightBackLeg.setTextureOffset(28, 96).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 15.0F, 6.0F, 0.0F, false);
/*     */     
/*  61 */     this.leftBackLeg = new ModelRenderer((Model)this);
/*  62 */     this.leftBackLeg.setRotationPoint(5.5F, 10.0F, 12.0F);
/*  63 */     this.leftBackLeg.setTextureOffset(28, 96).addBox(-3.5F, 0.0F, -2.5F, 6.0F, 15.0F, 6.0F, 0.0F, false);
/*     */     
/*  65 */     this.body = new ModelRenderer((Model)this);
/*  66 */     this.body.setRotationPoint(0.0F, -5.6F, -17.0F);
/*  67 */     setRotationAngle(this.body, 0.0782F, 0.0F, 0.0F);
/*  68 */     this.body.setTextureOffset(0, 0).addBox(-10.0F, -11.9126F, -1.199F, 20.0F, 30.0F, 20.0F, 0.0F, false);
/*     */     
/*  70 */     this.body2 = new ModelRenderer((Model)this);
/*  71 */     this.body2.setRotationPoint(-0.5F, 4.684F, 18.7307F);
/*  72 */     this.body.addChild(this.body2);
/*  73 */     setRotationAngle(this.body2, -0.1672F, 0.0F, 0.0F);
/*  74 */     this.body2.setTextureOffset(76, 32).addBox(-9.0F, -12.685F, -9.4673F, 19.0F, 27.0F, 18.0F, 0.0F, false);
/*     */     
/*  76 */     this.body3 = new ModelRenderer((Model)this);
/*  77 */     this.body3.setRotationPoint(0.4F, 2.9704F, 9.0416F);
/*  78 */     this.body2.addChild(this.body3);
/*  79 */     setRotationAngle(this.body3, -0.1564F, 0.0F, 0.0F);
/*  80 */     this.body3.setTextureOffset(0, 50).addBox(-9.0F, -13.6175F, -10.6404F, 18.0F, 26.0F, 20.0F, 0.0F, false);
/*     */     
/*  82 */     this.tail = new ModelRenderer((Model)this);
/*  83 */     this.tail.setRotationPoint(0.0F, -9.2051F, 5.6705F);
/*  84 */     this.body3.addChild(this.tail);
/*  85 */     setRotationAngle(this.tail, -0.9163F, 0.0F, 0.0F);
/*  86 */     this.tail.setTextureOffset(24, 117).addBox(-1.9F, -1.8493F, 2.5571F, 3.0F, 3.0F, 10.0F, 0.0F, false);
/*     */     
/*  88 */     this.neck = new ModelRenderer((Model)this);
/*  89 */     this.neck.setRotationPoint(0.0F, -0.516F, -1.8693F);
/*  90 */     this.body.addChild(this.neck);
/*  91 */     setRotationAngle(this.neck, -0.0436F, 0.0F, 0.0F);
/*  92 */     this.neck.setTextureOffset(80, 0).addBox(-5.5F, -8.0751F, -4.7215F, 10.0F, 15.0F, 10.0F, 0.0F, false);
/*     */     
/*  94 */     this.head = new ModelRenderer((Model)this);
/*  95 */     this.head.setRotationPoint(0.3F, -5.9F, 1.8F);
/*  96 */     this.head.setTextureOffset(76, 77).addBox(-8.0F, -13.0F, -36.0F, 15.0F, 20.0F, 15.0F, 0.0F, false);
/*     */     
/*  98 */     this.rightEar = new ModelRenderer((Model)this);
/*  99 */     this.rightEar.setRotationPoint(-7.95F, -8.1F, -28.7F);
/* 100 */     this.head.addChild(this.rightEar);
/* 101 */     setRotationAngle(this.rightEar, 0.0524F, 0.7679F, -0.0873F);
/* 102 */     this.rightEar.setTextureOffset(55, 115).addBox(-11.2484F, -4.4659F, -0.0486F, 12.0F, 17.0F, 0.0F, 0.0F, false);
/*     */     
/* 104 */     this.leftEar = new ModelRenderer((Model)this);
/* 105 */     this.leftEar.setRotationPoint(5.8F, -5.1F, -27.7F);
/* 106 */     this.head.addChild(this.leftEar);
/* 107 */     setRotationAngle(this.leftEar, 0.0524F, -0.7679F, 0.0873F);
/* 108 */     this.leftEar.setTextureOffset(55, 115).addBox(-1.2718F, -7.9778F, -1.4385F, 12.0F, 17.0F, 0.0F, 0.0F, true);
/*     */     
/* 110 */     this.trunk = new ModelRenderer((Model)this);
/* 111 */     this.trunk.setRotationPoint(-0.25F, -1.0F, -34.9F);
/* 112 */     this.head.addChild(this.trunk);
/* 113 */     setRotationAngle(this.trunk, 0.5061F, 0.0F, 0.0F);
/* 114 */     this.trunk.setTextureOffset(113, 18).addBox(-3.65F, -2.634F, -5.705F, 7.0F, 7.0F, 7.0F, 0.0F, false);
/*     */     
/* 116 */     this.trunk2 = new ModelRenderer((Model)this);
/* 117 */     this.trunk2.setRotationPoint(0.25F, 10.2166F, 16.8113F);
/* 118 */     this.trunk.addChild(this.trunk2);
/* 119 */     setRotationAngle(this.trunk2, -0.48F, 0.0F, 0.0F);
/* 120 */     this.trunk2.setTextureOffset(0, 96).addBox(-3.9F, -1.2635F, -25.8035F, 7.0F, 13.0F, 7.0F, 0.01F, false);
/*     */     
/* 122 */     this.trunk3 = new ModelRenderer((Model)this);
/* 123 */     this.trunk3.setRotationPoint(-0.5F, 11.5F, 1.4F);
/* 124 */     this.trunk2.addChild(this.trunk3);
/* 125 */     setRotationAngle(this.trunk3, 0.2346F, 0.0F, 0.0F);
/* 126 */     this.trunk3.setTextureOffset(0, 116).addBox(-2.9F, -6.3296F, -25.9225F, 6.0F, 10.0F, 6.0F, 0.0F, false);
/*     */     
/* 128 */     this.trunk4 = new ModelRenderer((Model)this);
/* 129 */     this.trunk4.setRotationPoint(0.1F, 9.6F, -1.45F);
/* 130 */     this.trunk3.addChild(this.trunk4);
/* 131 */     setRotationAngle(this.trunk4, 0.0719F, 0.0F, 0.0F);
/* 132 */     this.trunk4.setTextureOffset(0, 0).addBox(-1.9F, -7.8112F, -23.2052F, 4.0F, 6.0F, 4.0F, 0.0F, false);
/*     */     
/* 134 */     this.rightTusk = new ModelRenderer((Model)this);
/* 135 */     this.rightTusk.setRotationPoint(-4.8F, 1.9F, -10.8F);
/* 136 */     this.head.addChild(this.rightTusk);
/* 137 */     setRotationAngle(this.rightTusk, 2.0944F, 0.4363F, 0.0F);
/* 138 */     this.rightTusk.setTextureOffset(110, 0).addBox(8.1202F, -24.2825F, 9.3245F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 140 */     this.rightTusk2 = new ModelRenderer((Model)this);
/* 141 */     this.rightTusk2.setRotationPoint(-0.2F, -5.7F, 0.6F);
/* 142 */     this.rightTusk.addChild(this.rightTusk2);
/* 143 */     setRotationAngle(this.rightTusk2, 2.8274F, 0.0F, 0.0F);
/* 144 */     this.rightTusk2.setTextureOffset(110, 0).addBox(8.3702F, 20.0896F, -5.4339F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 146 */     this.rightTusk3 = new ModelRenderer((Model)this);
/* 147 */     this.rightTusk3.setRotationPoint(0.0F, 5.1F, 0.1F);
/* 148 */     this.rightTusk2.addChild(this.rightTusk3);
/* 149 */     setRotationAngle(this.rightTusk3, -0.384F, 0.0F, 0.0F);
/* 150 */     this.rightTusk3.setTextureOffset(110, 0).addBox(8.3702F, 20.1284F, 2.5197F, 3.0F, 6.0F, 3.0F, -0.01F, false);
/*     */     
/* 152 */     this.rightTusk4 = new ModelRenderer((Model)this);
/* 153 */     this.rightTusk4.setRotationPoint(-0.3F, 5.1F, 0.0F);
/* 154 */     this.rightTusk3.addChild(this.rightTusk4);
/* 155 */     setRotationAngle(this.rightTusk4, -0.1571F, 0.0F, 0.0F);
/* 156 */     this.rightTusk4.setTextureOffset(110, 0).addBox(8.7202F, 19.3094F, 5.6865F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 158 */     this.rightTusk5 = new ModelRenderer((Model)this);
/* 159 */     this.rightTusk5.setRotationPoint(0.7F, 5.1F, -0.25F);
/* 160 */     this.rightTusk4.addChild(this.rightTusk5);
/* 161 */     setRotationAngle(this.rightTusk5, -0.2793F, 0.0F, 0.0F);
/* 162 */     this.rightTusk5.setTextureOffset(110, 0).addBox(8.1202F, 16.6753F, 11.1062F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 164 */     this.leftTusk = new ModelRenderer((Model)this);
/* 165 */     this.leftTusk.setRotationPoint(4.45F, 1.9F, -10.8F);
/* 166 */     this.head.addChild(this.leftTusk);
/* 167 */     setRotationAngle(this.leftTusk, 2.0944F, -0.4363F, 0.0F);
/* 168 */     this.leftTusk.setTextureOffset(110, 0).addBox(-11.3202F, -24.2825F, 9.3245F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 170 */     this.leftTusk2 = new ModelRenderer((Model)this);
/* 171 */     this.leftTusk2.setRotationPoint(-0.2F, -5.7F, 0.6F);
/* 172 */     this.leftTusk.addChild(this.leftTusk2);
/* 173 */     setRotationAngle(this.leftTusk2, 2.8274F, 0.0F, 0.0F);
/* 174 */     this.leftTusk2.setTextureOffset(110, 0).addBox(-11.0702F, 20.0896F, -5.4339F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 176 */     this.leftTusk3 = new ModelRenderer((Model)this);
/* 177 */     this.leftTusk3.setRotationPoint(0.0F, 5.1F, 0.1F);
/* 178 */     this.leftTusk2.addChild(this.leftTusk3);
/* 179 */     setRotationAngle(this.leftTusk3, -0.384F, 0.0F, 0.0F);
/* 180 */     this.leftTusk3.setTextureOffset(110, 0).addBox(-11.0702F, 20.1284F, 2.5197F, 3.0F, 6.0F, 3.0F, -0.01F, false);
/*     */     
/* 182 */     this.leftTusk4 = new ModelRenderer((Model)this);
/* 183 */     this.leftTusk4.setRotationPoint(-0.3F, 5.1F, 0.0F);
/* 184 */     this.leftTusk3.addChild(this.leftTusk4);
/* 185 */     setRotationAngle(this.leftTusk4, -0.1571F, 0.0F, 0.0F);
/* 186 */     this.leftTusk4.setTextureOffset(110, 0).addBox(-10.7202F, 19.3094F, 5.6865F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */     
/* 188 */     this.leftTusk5 = new ModelRenderer((Model)this);
/* 189 */     this.leftTusk5.setRotationPoint(0.7F, 5.1F, -0.25F);
/* 190 */     this.leftTusk4.addChild(this.leftTusk5);
/* 191 */     setRotationAngle(this.leftTusk5, -0.2793F, 0.0F, 0.0F);
/* 192 */     this.leftTusk5.setTextureOffset(110, 0).addBox(-11.3202F, 16.6753F, 11.1062F, 3.0F, 6.0F, 3.0F, 0.0F, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
/* 198 */     this.rightFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 199 */     this.leftFrontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 200 */     this.rightBackLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 201 */     this.leftBackLeg.render(matrixStack, buffer, packedLight, packedOverlay);
/* 202 */     this.body.render(matrixStack, buffer, packedLight, packedOverlay);
/* 203 */     this.head.render(matrixStack, buffer, packedLight, packedOverlay);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
/* 210 */     boolean flag = (entity.getTicksElytraFlying() > 4);
/* 211 */     boolean flag1 = entity.isActualySwimming();
/* 212 */     this.head.rotateAngleY = MathHelper.clamp(netHeadYaw * 0.017453292F, -0.1F, 0.1F);
/* 213 */     if (flag) {
/* 214 */       this.head.rotateAngleX = -0.7853982F;
/* 215 */     } else if (this.swimAnimation > 0.0F) {
/*     */       
/* 217 */       if (flag1) {
/* 218 */         this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, -0.7853982F, this.swimAnimation);
/*     */       } else {
/* 220 */         this.head.rotateAngleX = rotLerpRad(this.head.rotateAngleX, headPitch * 0.017453292F, this.swimAnimation);
/*     */       } 
/*     */     } else {
/*     */       
/* 224 */       this.head.rotateAngleX = headPitch * 0.017453292F;
/* 225 */       if (this.head.rotateAngleX > 0.1D) {
/* 226 */         this.head.rotateAngleX = 0.1F;
/* 227 */       } else if (this.head.rotateAngleX < -0.1D) {
/* 228 */         this.head.rotateAngleX = -0.1F;
/*     */       } 
/*     */     } 
/*     */     
/* 232 */     this.rightFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.4F) * 0.3F * limbSwingAmount;
/* 233 */     this.leftFrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.4F) * 0.4F * limbSwingAmount;
/* 234 */     this.rightBackLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.4F + 3.1415927F) * 0.3F * limbSwingAmount;
/* 235 */     this.leftBackLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.4F + 3.1415927F) * 0.4F * limbSwingAmount;
/* 236 */     if (entity.isSprinting()) {
/*     */       
/* 238 */       this.tail.rotateAngleX = 0.1F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 239 */       this.leftEar.rotateAngleY = -0.3F - MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/* 240 */       this.rightEar.rotateAngleY = 0.3F + MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
/*     */     } 
/*     */ 
/*     */     
/* 244 */     this.swingProgress = ((LivingEntity)entity).swingProgress;
/* 245 */     if (this.swingProgress > 0.0F) {
/*     */       
/* 247 */       this.head.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 248 */       this.head.rotateAngleY = MathHelper.sin(MathHelper.sqrt(this.swingProgress) * 6.2831855F) * 0.2F;
/* 249 */       this.trunk.rotateAngleY += this.body2.rotateAngleY;
/* 250 */       float f1 = 1.0F - this.swingProgress;
/* 251 */       f1 *= f1;
/* 252 */       f1 *= f1;
/* 253 */       f1 = 1.0F - f1;
/* 254 */       float f2 = MathHelper.sin(f1 * 3.1415927F);
/* 255 */       float f3 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
/* 256 */       this.trunk.rotateAngleX = (float)(this.trunk.rotateAngleX - f2 * 1.2D + f3);
/* 257 */       this.trunk.rotateAngleY += this.body.rotateAngleY * 2.0F;
/* 258 */       this.trunk.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
/* 264 */     modelRenderer.rotateAngleX = x;
/* 265 */     modelRenderer.rotateAngleY = y;
/* 266 */     modelRenderer.rotateAngleZ = z;
/*     */   }
/*     */ 
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
/*     */   
/*     */   public void translateHand(HandSide side, MatrixStack matrixStack) {
/* 284 */     this.head.translateRotate(matrixStack);
/* 285 */     this.trunk.translateRotate(matrixStack);
/* 286 */     matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
/* 287 */     matrixStack.rotate(Vector3f.XP.rotationDegrees(260.0F));
/* 288 */     matrixStack.translate(1.3D, -0.02D, 0.1D);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\models\entities\zoans\MammothGuardModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */